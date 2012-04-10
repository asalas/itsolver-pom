package itsolver.controller.utils;

import itsolver.model.entity.Profile;
import itsolver.model.entity.User;
import itsolver.utils.CurrentSession;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Toolbarbutton;

public class NoValidationZkComposer extends GenericForwardComposer {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_LANGUAGE = "EN";	

    public void doAfterCompose(Component comp) throws Exception {    	
        super.doAfterCompose(comp);
        if ( session.getAttribute("preflang") != null ){
        	String sessLang = (String) session.getAttribute("preflang");
        	this.setLanguage(sessLang);
        }else{
        	this.setLanguage(DEFAULT_LANGUAGE);
        }
        
    }
    
    public CurrentSession createUserSession(Profile profile, User user) {
		session.removeAttribute("currentSession");
		CurrentSession currentSession = new CurrentSession();
		currentSession.setProfile(profile);
		currentSession.setUserName(user.getUserName());
		return currentSession;
	}	
	
	public void setLanguage(String setLang) throws Exception {
        // set session wide language to new value
        if (!setLang.isEmpty()) {
            session.setAttribute("preflang", setLang);
        }

        // read the session language attribute
        String sessLang = (String) session.getAttribute("preflang");        

        // set the new preferred locale
        // otherwise it will use the default language (no session attribute and/or language parameter
        if (!(sessLang == null)) {
            Locale preferredLocale = org.zkoss.util.Locales.getLocale(sessLang);
            session.setAttribute(org.zkoss.web.Attributes.PREFERRED_LOCALE, preferredLocale);
            org.zkoss.util.Locales.setThreadLocal(org.zkoss.util.Locales.getLocale(sessLang));
        }

        // Iterate through variables of the current class
        Field[] declaredFields = this.getClass().getDeclaredFields();
        
		for (Field f : declaredFields) {
            
            String compName = this.getClass().getName() + "." + f.getName();
            String compLabel = Labels.getLabel(compName);                    
            
            String compType = f.getType().getName();	            
            // only set lable if value found, otherwise it renders empty
            if (!(compLabel == null)) {	            	
                Object object = f.get(this);
				
                if (compType.equals("org.zkoss.zul.Button")) {
                	((Button) object).setLabel(compLabel);
                }
                else if (compType.equals("org.zkoss.zul.Label")) {
                	((Label) object).setValue(compLabel);
                }
                else if (compType.equals("org.zkoss.zul.Tab")) {
                	((Tab) object).setLabel(compLabel);
            	}
                else if(compType.equals("org.zkoss.zul.Toolbarbutton")) {
                	//TODO fix error null pointer exception
                	if (object != null){
                		((Toolbarbutton) object).setLabel(compLabel);
                	}
                }
                else if(compType.equals("org.zkoss.zul.Caption")) {
                	((Caption) object).setLabel(compLabel);
                }
                else if(compType.equals("org.zkoss.zul.Checkbox")) {
                	((Checkbox) object).setLabel(compLabel);
                }
                // Other component types need to be implemented if required
            }
        }
    }

}
