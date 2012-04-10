package itsolver.controller.utils;

import org.zkoss.zk.ui.event.Event;

public class ChangeLanguageEvent extends Event {

	public static final String CHANGE_LANGUAGE ="onChangeLanguage";
	
	public ChangeLanguageEvent(String typeEvent) {
		super(typeEvent);
	}
}
