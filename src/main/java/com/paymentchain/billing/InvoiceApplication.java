package com.paymentchain.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@SpringBootApplication
public class InvoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceApplication.class, args);
	}
	
	 @Bean
	  public OpenAPI customOpenAPI() {
	      return new OpenAPI()
	    		  	.components(new Components())
	    		  	.info(new Info()
	    		  		.title("Billing API")
	    		  		.version("1.0.0"));
	  }
}
