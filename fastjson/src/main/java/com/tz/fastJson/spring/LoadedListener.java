package com.tz.fastJson.spring;

import org.springframework.context.ApplicationListener;

import com.tz.fastJson.spring.event.LoadedEvent;

public interface LoadedListener extends ApplicationListener<LoadedEvent> {

}
