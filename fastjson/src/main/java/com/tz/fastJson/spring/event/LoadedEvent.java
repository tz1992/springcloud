package com.tz.fastJson.spring.event;

public class LoadedEvent extends AppEvent {
	private static final long serialVersionUID = -248058420056456611L;

	public LoadedEvent(Object source) {
		super("loaded");
		
	}

}
