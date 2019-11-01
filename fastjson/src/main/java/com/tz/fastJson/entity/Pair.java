package com.tz.fastJson.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class Pair implements Serializable {
  private static final long serialVersionUID = 6687096517445965626L;
  private String key;
  private String value;

  public Pair() {}

  public Pair(String key, String value) {
    this.key = key;
    this.value = value;
  }



  public String getKey() {
    return this.key;
  }



  public void setKey(String key) {
    this.key = key;
  }



  public String getValue() {
    return this.value;
  }



  public void setValue(String value) {
    this.value = value;
  }



  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((this.key == null) ? 0 : this.key.hashCode());
    return 31 * result + ((this.value == null) ? 0 : this.value.hashCode());
  }



  public static class Q extends Pair {
    private static final long serialVersionUID = -1989046708103679564L;



    public Q(String key, String value) {
      super(key, value);
    }


    public static Q valueOf(String query) {
      int index = StringUtils.indexOf(query, 61);
      if (index < 0) {
        return new Q(query, null);
      }
      return new Q(StringUtils.substring(query, 0, index), StringUtils.substring(query, index + 1));
    }
  }



  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Pair other = (Pair) obj;
    if (this.key == null) {
      if (other.key != null) return false;
    } else if (!this.key.equals(other.key)) {
      return false;
    }
    if (this.value == null) {
      if (other.value != null) return false;
    } else if (!this.value.equals(other.value)) {
      return false;
    }
    return true;
  }
}
