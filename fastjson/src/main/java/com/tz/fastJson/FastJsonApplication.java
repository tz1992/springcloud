package com.tz.fastJson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;


@SpringBootApplication
public class FastJsonApplication {
  
  
  @Bean
  public ConversionService getConversionService(){
    return new DefaultConversionService();
  }
  
  
public static void main(String[] args) {
  
	new SpringApplication(FastJsonApplication.class).run(args);
	
}
}
