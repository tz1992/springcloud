package com.tz.fastJson.controller;


import java.util.Date;

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



  @GetMapping("/test")
  public Person test() {
    Person person = new Person("01", "jack",new Date());
//    LookupFilter lookupFilter = new LookupFilter(Lookup.class);
    return person;
  }

}
