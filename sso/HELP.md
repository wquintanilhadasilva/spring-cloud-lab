# Getting Started

[https://www.youtube.com/watch?v=alv0YgPDdOQ](https://www.youtube.com/watch?v=alv0YgPDdOQ)

Gerar as chaves públicas e privadas

```
keytool -genkeypair -alias awblog -keyalg RSA -keypass 123456 -keystore awblog.jks -storepass 123456 -validity 3560
```

Verificar se a chave está dentro do repositório awblog.jks

```
keytool -list -keystore awblog.jks
```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/#build-image)
* [Cloud Bootstrap](https://docs.spring.io/spring-cloud-commons/docs/current/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web.security)
* [Config Client Quick Start](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_client_side_usage)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#actuator)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

