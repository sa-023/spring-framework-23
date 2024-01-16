package com.company;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Spring18RestOpenApi3 {

    public static void main(String[] args) {
        SpringApplication.run(Spring18RestOpenApi3.class, args);
    }

    @Bean
    public OpenAPI customOpenApi() { // We will use this bean to modify our swagger documentation.
        return new OpenAPI()
                .info(new Info().title("Cinema Application OpenAPI").version("v1").description("Cinema application API documentation"));
    }



}
