package com.tz.fastJson.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties({com.tz.fastJson.entity.AppEnv.class})
@Order(-2147483648)
public class CoreBeanAutoConfiguration {
	//
	// @Bean
	// public DefaultEventPublisher defaultEventPublisher() { return new
	// DefaultEventPublisher(); }
	//
	//
	//
	// @Bean
	// public ThisAwareBeanPostProcessor thisAwareBeanPostProcessor() { return
	// new ThisAwareBeanPostProcessor(); }
	//
	//
	//
	// @Bean
	// public MultipleMessageSource messageSource() {
	// MultipleMessageSource mms = new MultipleMessageSource();
	// mms.setBasenames(new String[] { "classpath*:/META-INF/i18n/Messages" });
	// mms.setDefaultEncoding("UTF-8");
	// return mms;
	// }


	  
	  @Bean
	  @Primary
	  public DefaultConversionService conversionService() { return new DefaultConversionService(); }







	  
	  @Bean
	  @Profile({"dev"})
	  public SerializerFeature[] devFeature() { return new SerializerFeature[] { SerializerFeature.WriteDateUseDateFormat, SerializerFeature.PrettyFormat }; }



	  
	  @Bean
	  @Profile({"!dev"})
	  public SerializerFeature[] nondevFeature() { return new SerializerFeature[] { SerializerFeature.WriteDateUseDateFormat }; }




	  
	  @Bean
	  @ConditionalOnMissingBean({JsonConversionService.class})
	  public JsonConversionService jsonConversionService(SerializerFeature[] feature) { return new FastJsonConversionService(feature); }

}
