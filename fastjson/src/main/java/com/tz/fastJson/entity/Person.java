package com.tz.fastJson.entity;

import com.tz.fastJson.annotation.Lookup;

public class Person implements POJO {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Lookup(type="CITYTYPE")
  private String city;
  private String name;
  
  
  public Person(String city, String name) {
    super();
    this.city = city;
    this.name = name;
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
  
}
