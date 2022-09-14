package com.example.auth.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class AuthorizationServerConfiguration {

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
		return http.formLogin(Customizer.withDefaults()).build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jWKSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jWKSource);
	}
	
	@Bean
	public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
		RSAKey rsaKey = generateRsa();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
	}
	
	@Bean
	public ProviderSettings providerSettings() {
		// declaração do emissor do token jwt, atributo iss
		// @formatter:off
		return ProviderSettings.builder()
				.issuer("http://localhost:8181") //http://auth-server:8181
				.build();
		// @formatter:on
	}
	
	/**
	 * Customiza os atributos gerados no token do usuário
	 * @return
	 */
	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> jwtEncodingContextOAuth2TokenCustomizer() {
		// Injeta o repositório de usuários ou dos dados que deseja obter
		
		return (context -> {
			
			// Verifica o tipo do Principal e se não for autenticação de serviço mas de usuário, incrementa o token
			Authentication authentication = context.getPrincipal();
//			if(authentication.getPrincipal() instance of User) {}
			
			
			Set<String> authorities = new HashSet<>();
			authorities.add("ADMIN");
			authorities.add("READ");
			
			context.getClaims().claim("user_id", "id-do-usuario");
			context.getClaims().claim("user_fullname", "Nome Completo do Usuário");
			context.getClaims().claim("authorities", authorities);
			
		});
	}

	
	@Bean
	public RegisteredClientRepository registeredClientRepository(PasswordEncoder encoder) {
		RegisteredClient registeredClient = RegisteredClient
				.withId(UUID.randomUUID().toString())
				.clientId("huongdanjava")
				.clientSecret(encoder.encode("123456"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUri("https://oidcdebugger.com/debug")
				.scope("users:read")
				.scope("users:write")
				.scope(OidcScopes.OPENID)
				.tokenSettings(TokenSettings.builder()
						.accessTokenTimeToLive(Duration.ofMinutes(5))
						.reuseRefreshTokens(false) // gerar um novo refresh token e invalidar o anterior
						.build())
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
				.build();
		
		RegisteredClient client1 = RegisteredClient
				.withId(UUID.randomUUID().toString())
				.clientId("client1")
				.clientSecret(encoder.encode("1234567"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.redirectUri("http://localhost:8181/login/oauth2/code/users-client-oidc")
				.redirectUri("http://localhost:8181/authorized")
				.scope(OidcScopes.OPENID)
				.scope("read")
				.build();
		
		RegisteredClient awblogClient = RegisteredClient
				.withId("2")
				.clientId("awblog")
				.clientSecret(encoder.encode("123456"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.redirectUri("http://localhost:3000/authorized")
				.redirectUri("https://oidcdebugger.com/debug")
				.scope("mysuser:read")
				.scope("mysuser:write")
				.scope("posts:write")
				.tokenSettings(TokenSettings.builder()
						.accessTokenTimeToLive(Duration.ofMinutes(15))
						.refreshTokenTimeToLive(Duration.ofDays(1))
						.reuseRefreshTokens(false) // gerar um novo refresh token e invalidar o anterior
						.build()).clientSettings(ClientSettings.builder()
								.requireAuthorizationConsent(true)
								.build())
				.build();
		
		return new InMemoryRegisteredClientRepository(Arrays.asList(client1, registeredClient, awblogClient));
		
	}
	
	
	private RSAKey generateRsa() throws NoSuchAlgorithmException {
		KeyPair keyPair = generateRSAKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		
		return new RSAKey
				.Builder(publicKey)
				.privateKey(privateKey)
				.keyID(UUID.randomUUID().toString())
				.build();
	}

	private KeyPair generateRSAKey() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		return keyPairGenerator.generateKeyPair();
	}
	
}
