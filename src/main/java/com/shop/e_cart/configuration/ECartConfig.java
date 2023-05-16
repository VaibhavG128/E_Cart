package com.shop.e_cart.configuration;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.shop.e_cart.*"})
@EntityScan(basePackages = {"com.shop.e_cart.entity"}) 
@EnableJpaRepositories(basePackages = "com.shop.e_cart.dao")
public class ECartConfig {
	public static void main(String args[])
	{
		SpringApplication.run(ECartConfig.class,args );
	}
}
