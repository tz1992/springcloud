package com.tz.fastJson;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.tz.fastJson.boot.MyBootApplication;


@SpringBootApplication
@ComponentScan(basePackages="com.tz.fastjson")
public class FastJsonApplication {



  public static void main(String[] args) {

    MyBootApplication.run(FastJsonApplication.class,args);
    
  }
}
