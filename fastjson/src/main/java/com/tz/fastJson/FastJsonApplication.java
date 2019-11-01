package com.tz.fastJson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;


@SpringBootApplication
public class FastJsonApplication {
  
  
  @Bean(name="conversionService")
  public ConversionService getConversionService(){
    ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
    bean.setConverters(null); //add converters
    bean.afterPropertiesSet();
    return bean.getObject();
  }
  
  
public static void main(String[] args) {
  
	new SpringApplication(FastJsonApplication.class).run(args);
	
}
}
