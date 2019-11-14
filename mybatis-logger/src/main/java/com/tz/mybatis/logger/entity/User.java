package com.tz.mybatis.logger.entity;

import java.io.Serializable;

public class User implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8432676331013094372L;

  private int id;
  private int age;
  private String userName;
  private String userTel;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserTel() {
    return userTel;
  }

  public void setUserTel(String userTel) {
    this.userTel = userTel;
  }


}
