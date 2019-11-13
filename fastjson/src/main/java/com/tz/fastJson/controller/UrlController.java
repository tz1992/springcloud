package com.tz.fastJson.controller;


import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.tz.fastJson.entity.Person;

@Component
@Consumes({"application/json", "text/json"})
@Produces({"application/json", "text/json"})
@Path("/fastjson/router")
public class UrlController {


  @GET
  @Path("/test")
  public Person test() {
    Person person = new Person("01", "jack",new Date());
    return person;
    
  }

}
