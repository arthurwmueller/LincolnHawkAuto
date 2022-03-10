package com.cognixia.jump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info( title = "Lincoln Hawk Automotive Group", version = "1.0", description = "API that allows you to interact with the Lincoln Hawk Automotive Group's inventory"))
public class LincolnHawkAutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LincolnHawkAutoApplication.class, args);
	}

}
