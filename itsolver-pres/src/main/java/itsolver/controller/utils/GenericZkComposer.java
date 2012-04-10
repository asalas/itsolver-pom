package itsolver.controller.utils;

import itsolver.utils.CurrentSession;

import java.lang.reflect.Field;
import java.util.Locale;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Column;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;


public class GenericZkComposer extends GenericForwardComposer {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_LANGUAGE = "ES";

    public void doAfterCompose(Component comp) throws Exception {    	
        super.doAfterCompose(comp);
        
        CurrentSession currentSession = (CurrentSession)session.getAttribute("currentSession");
		
        if ( currentSession == null ){			
			Window win = (Window) Executions.createComponents("/marketing/required-registration.zul", null, null);
			win.doModal();
		}else{
	        if ( session.getAttribute("preflang") != null ){
	        	String sessLang = (String) session.getAttribute("preflang");
	        	this.setLanguage(sessLang);
	        }else{
	        	this.setLanguage(DEFAULT_LANGUAGE);
	        }
		}
        
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
            // only set label if value found, otherwise it renders empty
            if (!(compLabel == null)) {	            	
                Object object = f.get(this);
				
                if (compType.equals("org.zkoss.zul.Button")) {
                	((Button) object).setLabel(compLabel);
                }
                else if (compType.equals("org.zkoss.zul.Label")) {
                	((Label) object).setValue(compLabel);
                }
                else if (compType.equals("org.zkoss.zul.Tab")) {
                	if (object != null) {
                		((Tab) object).setLabel(compLabel);
                	}
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
                else if(compType.equals("org.zkoss.zul.Listheader")) {
                	((Listheader) object).setLabel(compLabel);
                }else if(compType.equals("org.zkoss.zul.Radio")) {
                	((Radio) object).setLabel(compLabel);
                }else if(compType.equals("org.zkoss.zul.Caption")) {
                	((Caption) object).setLabel(compLabel);
                }
                else if(compType.equals("org.zkoss.zul.Panel")) {
                	if(object!=null){
                		((Panel) object).setTitle(compLabel);
                	}
                }else if(compType.equals("org.zkoss.zul.Listitem")) {
                	((Listitem)object).setLabel(compLabel);
                }else if(compType.equals("org.zkoss.zul.Menu")) {
                	((Menu)object).setLabel(compLabel);
                }else if(compType.equals("org.zkoss.zul.Menuitem")) {
                	((Menuitem)object).setLabel(compLabel);
                }else if(compType.equals("org.zkoss.zul.Column")) {
                	((Column)object).setLabel(compLabel);
                }
                // Other component types need to be implemented if required
            }
        }
    }
	
	public String getLanguage() {
		 // read the session language attribute
        String sessLang = (String) session.getAttribute("preflang");        
        
        return sessLang;
	}

}