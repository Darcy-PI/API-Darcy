package com.example.darcy_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springframework.boot.SpringApplication;

@OpenAPIDefinition(servers = { @Server(url = "/", description = "Default Server URL")})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
