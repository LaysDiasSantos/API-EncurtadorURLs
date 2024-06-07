package com.desafio.encurtadorurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API_EncurtadorDeUrl", version = "1", description = "API desenvolvida encurtar e gerenciar Urls"))
public class ApiEncurtadorDeUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEncurtadorDeUrlApplication.class, args);
	}

}
