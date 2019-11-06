package com.tz.fastJson.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import com.tz.fastJson.entity.AppEnv;
import com.tz.fastJson.service.JsonConversionService;

public abstract class BeanContext {
  private static final Logger log = LoggerFactory.getLogger(BeanContext.class);
  private static ApplicationContext beanFactory;
  private static AppEnv appEnv;
  private static JsonConversionService jsonConversionService;
  private static volatile boolean inited = false;

  public static void setBeanFactory(ApplicationContext beanFactory) {
      beanFactory = beanFactory;
      appEnv = (AppEnv) beanFactory.getBean(AppEnv.class);

      try {
          jsonConversionService = (JsonConversionService) beanFactory.getBean(JsonConversionService.class);
      } catch (BeansException var2) {
          log.warn("JsonConversionService bean can not find...");
      }

  }

  public static <T> T getBean(Class<T> requiredType) throws BeansException {
      return beanFactory.getBean(requiredType);
  }

  public static <T> List<T> getBeansOfType(Class<T> type) throws BeansException {
      List<T> beans = new ArrayList(beanFactory.getBeansOfType(type).values());
      AnnotationAwareOrderComparator.sort(beans);
      return beans;
  }

  public static AppEnv getAppEnv() {
      return appEnv;
  }

  public static JsonConversionService getJsonConversionService() {
      return jsonConversionService;
  }

  public static void init() {
      inited = true;
  }

  public static boolean isInit() {
      return inited;
  }
}