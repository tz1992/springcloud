package com.tz.fastJson.spring.event;

import org.springframework.context.ApplicationEvent;

public abstract class AppEvent extends ApplicationEvent {
	private static final long serialVersionUID = -892572421269110349L;
	public AppEvent(Object source) {
		super(source);
		
	}

}
