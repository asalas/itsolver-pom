package itsolver.controller;

import itsolver.controller.utils.ItSolverEvents;
import itsolver.controller.utils.NoValidationZkComposer;
import itsolver.utils.CurrentSession;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class MarketingLayout extends NoValidationZkComposer {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	public Toolbarbutton homeToolBarbutton;
	public Toolbarbutton whatIsToolBarbutton;
	public Toolbarbutton signUpToolBarbutton;
	
	public Toolbarbutton btnLearnMore;
	public Toolbarbutton btnResolveProblem;
	public Toolbarbutton btnCollaboration;
	
	public Toolbarbutton btnLang;
	public Toolbarbutton btnAbout;
	
	public Label lblDescription;
	
	public Menuitem menuES;
	public Menuitem menuEN;
	public Menuitem menuFR;
	
	public Hbox hboxMainLayout;
	public Vbox divMarketingLayout;
	public Window winSignUp;
	
	public Popup login_popup;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {		
		super.doAfterCompose(comp);
		
		CurrentSession currentSession = (CurrentSession)session.getAttribute("currentSession");
		
		if(currentSession!=null) {
			Executions.sendRedirect("/home/");
		}
		
	}	
	
	public void onCreate$divMarketingLayout(Event event) throws Exception {
		winSignUp = (Window) Executions.createComponents("/marketing/signup.zul", null, null);
		winSignUp.setParent(hboxMainLayout);
		winSignUp.doEmbedded();
		
		login_popup = (Popup) Executions.createComponents("/login/login.zul", null, null);
		
		signUpToolBarbutton.setPopup(login_popup);
	}
	
	public void onClick$menuES() throws Exception {
		setLanguage("ES");
		onChangeLanguageMarketing("ES");
	}
	
	public void onClick$menuEN() throws Exception {
		setLanguage("EN");
		onChangeLanguageMarketing("EN");
	}
	
	public void onClick$menuFR(Event event) throws Exception {
		setLanguage("FR");
		onChangeLanguageMarketing("FR");
    }
	
	private void onChangeLanguageMarketing(String lang) {	
		Events.postEvent(ItSolverEvents.CHANGE_LANGUAGE_MARKETING, winSignUp, lang);
		Events.postEvent(ItSolverEvents.CHANGE_LANGUAGE_MARKETING, login_popup, lang);
	}

}