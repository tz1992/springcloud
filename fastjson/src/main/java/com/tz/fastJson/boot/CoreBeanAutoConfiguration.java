package com.tz.fastJson.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.support.DefaultConversionService;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tz.fastJson.service.JsonConversionService;
import com.tz.fastJson.service.impl.FastJsonConversionService;

@Configuration
@Order(Integer.MIN_VALUE)
public class CoreBeanAutoConfiguration {

  @Bean
  @Primary
  public DefaultConversionService conversionService() {
    return new DefaultConversionService();
  }



  @Bean
  @Profile({"dev"})
  public SerializerFeature[] devFeature() {
    return new SerializerFeature[] {SerializerFeature.WriteDateUseDateFormat,
        SerializerFeature.PrettyFormat};
  }



  @Bean
  @Profile({"!dev"})
  public SerializerFeature[] nondevFeature() {
    return new SerializerFeature[] {SerializerFeature.WriteDateUseDateFormat};
  }



  @Bean
  @ConditionalOnMissingBean({JsonConversionService.class})
  public JsonConversionService jsonConversionService(SerializerFeature[] feature) {
    return new FastJsonConversionService(feature);
  }

}
