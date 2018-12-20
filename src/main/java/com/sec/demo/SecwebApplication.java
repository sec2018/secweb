package com.sec.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@EnableCaching
@SpringBootApplication
@EnableSwagger2
@EnableAsync
public class SecwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecwebApplication.class, args);
	}
}
