package com.tz.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/router")
public class UrlController {
  
  @Autowired
  private Environment env;
  
  @GetMapping("/test")
  public void test(){
          System.out.println(env.getProperty("app.Cache")); 
  }

}
