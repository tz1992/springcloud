package com.tz.fastJson.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.tz.fastJson.annotation.Lookup;

public class Person implements POJO {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Lookup(type = "CITYTYPE")
  private String city;
  private String name;
  @JSONField(format="yyyy-MM-dd")
  private Date date;


  public Person(String city, String name,Date date) {
    super();
    this.city = city;
    this.name = name;
    this.date=date;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
  
  

}
