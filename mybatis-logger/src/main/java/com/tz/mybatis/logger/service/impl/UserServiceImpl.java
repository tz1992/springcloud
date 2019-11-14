package com.tz.mybatis.logger.service.impl;

import java.util.List;

import org.apache.ibatis.session.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tz.mybatis.logger.dao.UserDao;
import com.tz.mybatis.logger.entity.User;
import com.tz.mybatis.logger.service.UserService;


@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  private  UserDao userDao;

  
  @Override
  public List<User> getUserList() {
   
    
    return userDao.getUserList();
  }

}
