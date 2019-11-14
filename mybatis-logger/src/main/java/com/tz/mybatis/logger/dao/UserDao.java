package com.tz.mybatis.logger.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tz.mybatis.logger.entity.User;

@Mapper
public interface UserDao {

  List<User> getUserList();
}
