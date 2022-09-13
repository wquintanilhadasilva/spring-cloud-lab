# Getting Started

[https://appsdeveloperblog.com/spring-authorization-server-tutorial](https://appsdeveloperblog.com/spring-authorization-server-tutorial)
[https://oidcdebugger.com/](https://oidcdebugger.com/)
[https://huongdanjava.com/implement-oauth-authorization-server-using-spring-authorization-server.html](https://huongdanjava.com/implement-oauth-authorization-server-using-spring-authorization-server.html)


[http://localhost:8181/oauth2/authorize](http://localhost:8181/oauth2/authorize)

```
curl --request POST \
  --url http://localhost:8181/oauth2/token \
  --header 'Authorization: Basic aHVvbmdkYW5qYXZhOjEyMzQ1Ng==' \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --cookie JSESSIONID=57974A49C38044E634653555F073DF05 \
  --data grant_type=authorization_code \
  --data client_id=huongdanjava \
  --data client_secret=123456 \
  --data code=70iDFyKea6ygri6Kl9gbwxyLM5HLKoTr9TBSh9143gHIVDUL2l6BMbwWAyQ9vF697J2T92OeEkptDJGwDleATss0HUvgUJLyubcNxkitEn3bmxhkhjQE2Hh5bWKnc3eX \
  --data redirect_uri=https://oidcdebugger.com/debug

```


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/#build-image)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web.security)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web)
* [Cloud Bootstrap](https://docs.spring.io/spring-cloud-commons/docs/current/reference/html/)

### Guides
The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

