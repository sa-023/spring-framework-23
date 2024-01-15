package com.company;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
/*
 * 🦋 Consuming Rest Endpoints
 * · In application world, not only the client of a web app may call the backend, but also two backend components may
 *   communicate one with the other to accomplish their responsibilities.
 * · In backend solution composed of multiple services, these components need to speak to exchange data. When we implement
 *   such a service using Spring, we need to know how to call a REST endpoint exposed by another service.
 * · There are three ways to call REST endpoints from a Spring app:
 * 1. OpenFeign: A tool offered by the Spring Cloud project.
 * 2. RestTemplate: A tool developers use since Spring 3.
 * 3. WebClient: A Spring feature presented as an alternative to RestTemplate by the Spring documentation.
 *
 *
 * 📌 www.jsonschema2pojo.org: Generate Plain Old Java Objects from JSON or JSON-Schema.
 *    We used the above webpage for 3rd party API conversion to an entity.
 */
@SpringBootApplication
@EnableFeignClients
public class SpringRestConsumingApis {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestConsumingApis.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}





}
