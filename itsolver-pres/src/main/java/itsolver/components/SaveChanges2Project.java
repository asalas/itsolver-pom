package itsolver.components;

import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.Project;
import itsolver.service.ProjectMgmtService;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.HtmlMacroComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class SaveChanges2Project extends HtmlMacroComponent {
	
	static Logger logger = Logger.getLogger(SaveChanges2Project.class);
	
	private Toolbarbutton btnSaveChanges;
	private Toolbarbutton btnCancel;
	private Label lblSaveMsg;
	private Timer timerNotification;
	private Window winParent;
	
	Vbox vboxNotification;
	
	private ProjectMgmtService projMgmtService;
	
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -1602466240634162660L;
	
	@Override
	public void afterCompose() {		
		super.afterCompose();

		btnSaveChanges = (Toolbarbutton)getFellow("btnSaveChanges");
		btnCancel = (Toolbarbutton)getFellow("btnCancel");
		vboxNotification = (Vbox)getFellow("vboxNotification");
		
		timerNotification = (Timer)getFellow("timerNotification");		
		timerNotification.addEventListener(Events.ON_TIMER, new EventListener() {			
			public void onEvent(Event event) throws Exception {
				vboxNotification.setVisible(false);
				lblSaveMsg.setValue("");
				timerNotification.setRunning(false);
			}
		});
		
		btnCancel.addEventListener(Events.ON_CLICK, new EventListener() {			
			public void onEvent(Event event) throws Exception {
				if ( winParent != null ){
					Events.postEvent(Events.ON_CLOSE, winParent, null);
				}else{
					 logger.warn("Window parent is not set");
				}
			}
		});
		
		// se propaga el evento click al componente "padre" que contiene a SaveChanges2Project
		btnSaveChanges.addEventListener(Events.ON_CLICK, new EventListener() {
			public void onEvent(Event event) throws Exception {				
				if (isListenerAvailable(Events.ON_CLICK, true)) {
		            Event clicked = new Event(Events.ON_CLICK, getParent());

		            Iterator listeners = getListenerIterator(Events.ON_CLICK);
		            while (listeners.hasNext()) {
		                EventListener listener = (EventListener) listeners.next();
		                listener.onEvent(clicked);
		            }		            
		        }								
			}
		});
	}
	
	public void setButtonLabel(String label){
		btnSaveChanges.setLabel(label);
	}
	
	public void setCancelButtonLabel(String label) {
		btnCancel.setLabel(label);
	}
	
	public void setMessage(String msg) {
		vboxNotification.setSclass("notification");
		setDynamicProperty("saveMsg", msg);
		lblSaveMsg = (Label)getFellow("lblSaveMsg");
		if(lblSaveMsg!=null) lblSaveMsg.setValue(msg);
	}
	
	public void setErrorMessage(String msg) {
		vboxNotification.setSclass("notification_error");
		setDynamicProperty("saveMsg", msg);
		lblSaveMsg = (Label)getFellow("lblSaveMsg");
		if(lblSaveMsg!=null) lblSaveMsg.setValue(msg);
		vboxNotification.setVisible(true);		
		timerNotification.setRunning(true);	
	}
	
	public void performProjectUpdate(Project project, Desktop desktop, 
										Collaborator collaborator, Object win)throws Exception {
		
		if(collaborator!=null) {
			boolean timedOut = collaborator.isTimedOut();
			
			if(timedOut) {
				Window editWin = (Window)win;
				editWin.detach();
				Messagebox.show("Timeout!!");
				return;
			}else {
				MutualExclusionControl.increaseTimeout(10L);
			}
		}
		
		try {
			projMgmtService = ServiceLocator.getProjectMgmtService();
			projMgmtService.projectUpdate(project);
			desktop.removeAttribute("currentProject");
			desktop.setAttribute("currentProject", project);
		} catch (Exception e) {
			setMessage("An error has ocurred!");
			e.printStackTrace();
		}
		
		vboxNotification.setVisible(true);
		
		timerNotification.setRunning(true);		
	}

	public void init(Window winParent) {
		this.winParent = winParent;
	}

}