package com.tz.fastJson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.tz.fastJson.annotation.Lookup;
import com.tz.fastJson.entity.Person;
import com.tz.fastJson.filter.LookupFilter;

@RestController
@RequestMapping("/router")
public class UrlController {
  
  @Autowired
  private Environment env;
  
  @GetMapping("/test")
  public String test(){
        Person person=new Person("01","jack");
        LookupFilter lookupFilter=new LookupFilter(Lookup.class);
       return JSON.toJSONString(person,lookupFilter );
  }

}
