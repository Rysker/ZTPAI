package com.example.modelbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ModelBaseApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ModelBaseApplication.class, args);
	}

}
