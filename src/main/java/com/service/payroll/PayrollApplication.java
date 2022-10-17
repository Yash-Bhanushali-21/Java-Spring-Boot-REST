package com.service.payroll;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableJpaRepositories
@EnableSwagger2
@RestController
public class PayrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayrollApplication.class, args);
	}
	@RequestMapping("/")
	public String home() {
		return "Hello World!";
	}
	
	
	@RequestMapping("/loginSuccess")
	public Principal loginSuccess(Principal userInfo) {
		return userInfo;
	}
	@RequestMapping("/logoutSuccess")
	public String logoutSuccess(Principal userInfo) {
		return "You are Successfully logged out" + userInfo.getName();
	}
	@Bean
	public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())                          
.build();
	   }

}
