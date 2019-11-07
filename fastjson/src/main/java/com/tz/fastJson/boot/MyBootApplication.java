package com.tz.fastJson.boot;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.tz.fastJson.spring.LoadedListener;
import com.tz.fastJson.util.BeanContext;

public class MyBootApplication {
	private static void shutdown(ApplicationContext ctx) {
	    Map<String, LoadedListener> listeners = ctx.getBeansOfType(LoadedListener.class);
	    if (listeners != null) {
	      listeners.forEach((key, listener) -> listener.onApplicationEvent(null));
	    }
	    
	    BeanContext.setBeanFactory(ctx);
	    BeanContext.init();
	  
	  }

	  
	  public static void run(Object source, String... args) {
	    SpringApplication app = new SpringApplication((Class<?>[]) new Object[] { source });
	    
	    shutdown(app.run(args));
	  }
}
