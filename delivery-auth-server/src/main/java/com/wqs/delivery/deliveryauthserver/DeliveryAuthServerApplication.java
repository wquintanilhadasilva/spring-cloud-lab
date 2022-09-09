package com.wqs.delivery.deliveryauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication  //(exclude = {DataSourceAutoConfiguration.class })
public class DeliveryAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryAuthServerApplication.class, args);
	}

}
