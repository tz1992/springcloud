package com.tz.mybatis.logger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tz.mybatis.logger.entity.User;
import com.tz.mybatis.logger.service.UserService;

@RestController
@RequestMapping("/router")
public class UrlController {

  @Autowired
  private UserService service;



  @GetMapping("/test")
  public List<User> test() {
    return service.getUserList();
  }

}
