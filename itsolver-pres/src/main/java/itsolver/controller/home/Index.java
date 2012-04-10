package itsolver.controller.home;
import itsolver.components.NotifyCollaboration;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ItSolverEvents;
import itsolver.model.entity.Profile;
import itsolver.utils.CurrentSession;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Div;
import org.zkoss.zul.East;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;


public class Index extends GenericZkComposer {
	
	private static final long serialVersionUID = 1L;

	public Tabbox tabBoxMenu;
	public Borderlayout mainLayout;
	public Menu userMenu;
	
	public Menuitem btnES;
	public Menuitem btnEN; 
	public Menuitem btnFR;
	
	public Menuitem btnProfile;
	public Menu btnLang;
	public Menuitem btnExit;
	public Menuitem btnChat;
	
	public Tab tabDashboard;
	public Tabpanel tabPanelDashboard;
	
	public Tab tabMyProjects;
	public Tab tabMyCollaborations;
	public Tab tabEducational;
	
	public Window winChat;
	public Div divLogo;
	public Menu menuConfigBar;
	
	public Timer timerNotifyCollaboration;
	public NotifyCollaboration notifyCollaboration;
	public East notificationZone;
	
	private TabBoxListener tabBoxListener = new TabBoxListener();
	
	public Tabpanels tabBoxPanels;
	
	// cambio de idioma - componentes afectados
	public Div myProjectList;
	public Div invitationList;
	public Div divDashboard;
		
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		CurrentSession currentSession = (CurrentSession)session.getAttribute("currentSession");		
		
		if ( currentSession != null ) {			
			mainLayout.setVisible(true);
			tabBoxMenu.addEventListener(Events.ON_SELECT, tabBoxListener);			
			userMenu.setLabel(currentSession.getUserName());
			
			// add onExitChat listener			
			divLogo.addEventListener("onExitChat", new ExitChatListener());
			notificationZone.addEventListener("onNotifyCollaboration", new NotifyCollaborationListener());
			
			/**
			 * Se inicia el "control de exclusion mutua"
			 */
			Profile profile = currentSession.getProfile();
			MutualExclusionControl.start(profile);
		}
	}
	
	public void onClick$btnES(Event event) throws Exception {	        
		setLanguage("ES");
		changeLanguage("ES");
	}

    public void onClick$btnEN(Event event) throws Exception {
        setLanguage("EN");
        changeLanguage("EN");
    }

    public void onClick$btnFR(Event event) throws Exception {
        setLanguage("FR");
        changeLanguage("FR");
    }
    
    private void changeLanguage(String lang) {
    	if(myProjectList!=null) {
    		Events.postEvent(ItSolverEvents.CHANGE_LANGUAGE_HOME, myProjectList, lang);
    	}
    	if(invitationList!=null) {
    		Events.postEvent(ItSolverEvents.CHANGE_LANGUAGE_HOME, invitationList, lang);
    	}
    	if(divDashboard!=null) {
    		Events.postEvent(ItSolverEvents.CHANGE_LANGUAGE_HOME, divDashboard, lang);
    	}
    	
    }
    
    public void onClick$btnChat(Event event) throws Exception {
    	if(winChat!=null) {    		
    		boolean isWinChatMinimized = winChat.isMinimized();    		    		
    		if(isWinChatMinimized) {
    			winChat.setPosition("right, bottom");
    			winChat.doOverlapped();
    		}    		
    	}else {    		
    		Map<String, String> paramsChat = new HashMap<String, String>();
    		String userName = userMenu.getLabel();
    		paramsChat.put("userName", userName);    		
    		
    		winChat = (Window)Executions.createComponents("/chat/chat.zul", divLogo, paramsChat);
    		winChat.setPosition("right, bottom");
			winChat.doOverlapped();
    	}
    }
    
    class TabBoxListener implements EventListener {    	
    	public void onEvent(Event event) throws Exception {
    		// que tab se selecciono y su correspondiente tabpanel
    		Tab selectedTab = tabBoxMenu.getSelectedTab();
    		Tabpanel tabPanel = selectedTab.getLinkedPanel();
    		
    		// se remueve el contenido del tabpanel
    		tabPanel.getChildren().clear();
    		
    		// se obtiene el id del tab q se selecciono
    		String selectedTabId = selectedTab.getId();
    		
    		if(selectedTabId.equals("tabMyProjects")) {
    			myProjectList = (Div)Executions.createComponents("/my_projects/my-project-list.zul", tabPanel, null);    			
    		}else if(selectedTabId.equals("tabMyCollaborations")) {
    			invitationList = (Div)Executions.createComponents("/collaborations/collaborations.zul", tabPanel, null);
    		}else if(selectedTabId.equals("tabEducational")) {
    			// TODO: falta implementar el tab educacional
    		}else if(selectedTabId.equals("tabDashboard")) {
    			divDashboard = (Div)Executions.createComponents("/dashboard/dashboard.zul", tabPanel, null);
    		}
    		
    	}
    }
    
    // litener para para cuando se cierra la sesion del chat
    class ExitChatListener implements EventListener {
    	public void onEvent(Event event) throws Exception {    		
    		winChat = null;
    	}
    }
    
    // listener para cuando se notifica la edicion de un proyecto por otro usuario
    class NotifyCollaborationListener implements EventListener {
    	public void onEvent(Event event) throws Exception {
    		
    		Collaborator collaborator = (Collaborator)event.getData();
    		
    		Profile profile = collaborator.getProfile();
    		String userName = profile.getUser().getUserName();    		
    		
			notifyCollaboration.setNotification(userName+" is editing this project");
			notifyCollaboration.setVisible(true);
        	
        	timerNotifyCollaboration.setRunning(true);
    	}
    }
    
    // timer para mostrar por 5 segundos si algun proyecto esta siendo editado por otro usuario
    // y despues ocultar el mensaje
    public void onTimer$timerNotifyCollaboration(Event event) throws Exception {
    	notifyCollaboration.setNotification("");
    	notifyCollaboration.setVisible(false);
    	
    	timerNotifyCollaboration.setRunning(false);
    }
    
    // cerrar la sesion del usuario
    public void onClick$btnExit(Event event)throws Exception {
    	Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();
		
		if(collaborator!=null) {
			MutualExclusionControl.onExit();
		}
		
    	session.removeAttribute("currentSession");
		Executions.sendRedirect("/");
    }
    
    public void onCreate$mainLayout(Event event) throws Exception {
    	divDashboard = (Div)Executions.createComponents("/dashboard/dashboard.zul", tabPanelDashboard, null);
    	//divDashboard.setParent(tabPanelDashboard);
    }

}
