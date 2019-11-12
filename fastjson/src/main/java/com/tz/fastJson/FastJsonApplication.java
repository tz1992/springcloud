package com.tz.fastJson;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.tz.fastJson.boot.MyBootApplication;


@SpringBootApplication
public class FastJsonApplication {



  public static void main(String[] args) {

    MyBootApplication.run(FastJsonApplication.class,args);
    
  }
}
