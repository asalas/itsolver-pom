package itsolver.controller.collaborationmgmt;

import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ItSolverEvents;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.ContradictionProject;
import itsolver.model.entity.Invitation;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;
import itsolver.model.entity.SuField;
import itsolver.service.CollaborationMgmtService;
import itsolver.utils.CurrentSession;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Div;
import org.zkoss.zul.East;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

public class CollaborationController extends GenericZkComposer {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -8818667913159451059L;
	
	
	public String txtInvitationType;	
	
	public Div divInvitationsReceived;	

	public Listbox receivedInvitationListBox;	

	private AnnotateDataBinder receivedListBinder;
	
	private Invitation selectedMyInvitation;
	
	private List<Invitation> receivedInvitationList  = new ArrayList<Invitation>();
	private Invitation selectedReceivedInvitation;
	
	public Toolbarbutton btnAcceptInvitation;
	public Toolbarbutton btnRejectInvitation;
	public Toolbarbutton btnCollaboration;
	
	public Listheader headerProject;
	public Listheader headerSentBy;
	public Listheader headerMsg;
	public Listheader headerSendedOn;
	public Listheader headerCollabStatus;
	
	public Div invitationList;
	
	private CollaborationMgmtService collaborationMgmtService;
	
	private Profile profile;	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {		
		super.doAfterCompose(comp);
		
		/**
         * NOTA: Para poder hacer el binding entre componentes es necesario agregar un atributo
         * el cual se compone al retomar el ID del contenedor principal (agregarle un sufijo, es opcional)
         * hacer la referencia al Composer
         */
        comp.setAttribute(comp.getId() + "Ctrl", this, true);
        
        // get profile from the session
        CurrentSession currentSession = (CurrentSession)session.getAttribute("currentSession");
        profile = currentSession.getProfile();        
        	
        collaborationMgmtService = ServiceLocator.getCollaborationMgmtService();        
        
        receivedInvitationList = collaborationMgmtService.getReceivedInvitationsByProfile(profile);
        
        /**
         * NOTA: Los binder pueden asociarce al componente zul que esta relacionado con el composer o bien con
         * cualquier otro componente contenido en el archivo zul, por ejemplo; se usan binders en este caso para
         * La lista de invitaciones hechas por el usuario
         * La lista de invitaciones recibidas y
         * La lista de los tipo de invitacion.
         * 
         * Esto es para evitar que ocurra la excepcion "Cannot find associated CollectionItem"
         * para el caso de los Listbox
         */        
        receivedListBinder = new AnnotateDataBinder(receivedInvitationListBox);
        receivedListBinder.loadAll();
        
        invitationList.addEventListener(ItSolverEvents.CHANGE_LANGUAGE_HOME, new EventListener() {
        	public void onEvent(Event event) throws Exception {
        		String lang = (String)event.getData();
        		setLanguage(lang);
        	}
        });
	}
	
		
	public void onClick$btnAcceptInvitation() throws Exception {		
		this.setStatusInvitation(true);		
	}
	
	public void onClick$btnRejectInvitation() throws Exception {
		this.setStatusInvitation(false);
	}
	
	private void setStatusInvitation(boolean status) throws Exception{
		if(selectedReceivedInvitation != null) {			
			if(selectedReceivedInvitation.getId() != null) {				
				
				selectedReceivedInvitation.setIsAccepted(status);				
				collaborationMgmtService.mergeInvitation(selectedReceivedInvitation);
				
				receivedListBinder.loadAll();
				
				setDisableBtnActions(true);
			}
			
		}else {
			Messagebox.show("Select an invitation", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	private void setDisableBtnActions(boolean status) {
		btnAcceptInvitation.setDisabled(status);
		btnRejectInvitation.setDisabled(status);
		btnCollaboration.setDisabled(!status);
	}
	
	// si la invitacion ya fue aceptada o rechazada ya no se podra modificar su status
	public void onSelect$receivedInvitationListBox()throws Exception {
		Boolean statusInvitation = selectedReceivedInvitation.isAccepted();
		if(statusInvitation != null) {
			setDisableBtnActions(true);			
			// se verifica de nueva cuenta el estado de la invitacion
			// para permitir la colaboracion
			if(statusInvitation) {
				btnCollaboration.setDisabled(false);
			}else {
				btnCollaboration.setDisabled(true);
			}
			
		}else {
			setDisableBtnActions(false);
			btnCollaboration.setDisabled(true);
		}
	}

	// se incia la colaboracion en el proyecto seleccionado
	public void onClick$btnCollaboration() throws Exception {
		
		Project project = selectedReceivedInvitation.getProject();
		
		Collaborator collaborator = MutualExclusionControl.onEditProject(project, profile);
		
		if(collaborator==null) {
			onEditProject(project);
		}else {			
			notifyCollaboration(collaborator);
		}		
	}

	private void notifyCollaboration(Collaborator collaborator) {
		Div div = (Div)divInvitationsReceived.getParent();
		Tabpanel tabPanel = (Tabpanel)div.getParent();
		Tabpanels tabPanels = (Tabpanels)tabPanel.getParent();
		Tabbox tabBox = (Tabbox)tabPanels.getParent();
		Center center = (Center)tabBox.getParent();
		Borderlayout borderLayout = (Borderlayout)center.getParent();
		List children = borderLayout.getNorth().getChildren();
		Borderlayout subBorderLayout = (Borderlayout)children.get(0);
		East east = subBorderLayout.getEast();
		
		Events.postEvent("onNotifyCollaboration", east, collaborator);
	}


	private void onEditProject(Project project) throws InterruptedException {
		desktop.setAttribute("currentProject", project);
		Window win = null;
		if (project instanceof ContradictionProject){
			win = (Window)Executions.createComponents("/contradictions/project-edit.zul", null,null);
		}else if (project instanceof SuField) {
			win = (Window)Executions.createComponents("/sufield/project-edit.zul", null,null);
		}															
		win.doModal();
		win.setTitle(project.getProjectName().toUpperCase());
		win.setPosition("top, center");
	}
	
	public Invitation getSelectedMyInvitation() {
		return selectedMyInvitation;
	}


	public void setSelectedMyInvitation(Invitation selectedMyInvitation) {
		this.selectedMyInvitation = selectedMyInvitation;
	}


	public List<Invitation> getReceivedInvitationList() {
		return receivedInvitationList;
	}
	

	public Invitation getSelectedReceivedInvitation() {
		return selectedReceivedInvitation;
	}


	public void setSelectedReceivedInvitation(Invitation selectedReceivedInvitation) {
		this.selectedReceivedInvitation = selectedReceivedInvitation;
	}

}
