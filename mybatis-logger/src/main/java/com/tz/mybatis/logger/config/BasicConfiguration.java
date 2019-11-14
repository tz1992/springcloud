package com.tz.mybatis.logger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
public class BasicConfiguration {
  
  @Value("${spring.datasource.master.password}")
  private String password;
  
  @Value("${spring.datasource.master.username}")
  private String username;
  
  @Value("${spring.datasource.master.jdbc-url}")
  private String url;
  
  @Bean
  public MysqlDataSource MysqlDataSource(){
    MysqlDataSource dataSource=new MysqlDataSource();
    dataSource.setPassword(password);
    dataSource.setUser(username);
    dataSource.setUrl(url);
    return dataSource;
  }
  
}
