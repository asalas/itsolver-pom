package itsolver.controller.projectmgmt;

import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ItSolverEvents;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.ContradictionProject;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;
import itsolver.model.entity.SuField;
import itsolver.service.ProjectMgmtService;
import itsolver.utils.CurrentSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Listbox;


public class ProjectList extends GenericZkComposer{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 9091948971183812805L;

	public Div myProjectList;
	
	public Toolbarbutton btnProjectAdd;
	public Toolbarbutton btnProjectEdit;
	public Toolbarbutton btnProjectInvite;
	
	public Listheader headerName;
	public Listheader headerStatus;
	public Listheader headerType;
	public Listheader headerDate;
	
	public Listbox projectListBox;
	
	private AnnotateDataBinder binder;
	
	private List<Project> modelProjectList = new ArrayList<Project>();
	private Project selectedProject;
	
	private ProjectMgmtService projectMgmtService;
	
	private Profile profile;	
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        /**
         * NOTA: para poder hacer el binding entre componentes es necesario agregar un atributo
         * el cual se compone al retomar el ID del contenedor principal (agregarle un sufijo, es opcional)
         * hacer la refenrecia al Composer
         */        
        comp.setAttribute(comp.getId() + "Ctrl", this, true);
        
        // get profile from the session
        CurrentSession currentSession = (CurrentSession)session.getAttribute("currentSession");
        profile = currentSession.getProfile();        
        
        if(profile!=null) {        	
        	Long profileId = profile.getId();
        	if(profileId!=null) {
        		projectMgmtService = ServiceLocator.getProjectMgmtService();
    			List<Project> projectsByProfileId = projectMgmtService.getProjectsByProfileId(profileId);
    			
    			if(projectsByProfileId != null) {
    				modelProjectList.addAll(projectsByProfileId);
    			}else {
    				modelProjectList = new ArrayList<Project>();
    			}   			
        	}
        }
        
        binder = new AnnotateDataBinder(comp);
        binder.loadAll(); 
        
        // event listener - crear un nuevo proyecto
        projectListBox.addEventListener("onNewProject", new ListboxEventListener());
        
        // cambiar de idioma
        myProjectList.addEventListener(ItSolverEvents.CHANGE_LANGUAGE_HOME, new EventListener() {
        	public void onEvent(Event event) throws Exception {
        		String lang = (String)event.getData();
        		setLanguage(lang);        		
        	}
        });
    }
	
	// accion para editar un proyecto
	public void onClick$btnProjectEdit() throws Exception {
		if(selectedProject!=null) {
			
			// se inicia el proceso de exclusion mutua
			Collaborator collaborator = MutualExclusionControl.onEditProject(selectedProject, profile);

			// si el proyecto no esta siendo editado se puede trabajar en el
			if(collaborator==null) {
				onEditProject();
			}else {
				notifyCollaboration(collaborator);
			}			
						
		}else {		
			Messagebox.show("Select a project", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);			
		}
	}

	private void onEditProject() throws InterruptedException {
		desktop.setAttribute("currentProject", selectedProject);
		Window win = null;
		if (selectedProject instanceof ContradictionProject){
			win = (Window)Executions.createComponents("/contradictions/project-edit.zul", null,null);
		}else if (selectedProject instanceof SuField) {
			win = (Window)Executions.createComponents("/sufield/project-edit.zul", null,null);
		}

		win.setTitle(selectedProject.getProjectName().toUpperCase());
		win.doModal();
		
	}

	private void notifyCollaboration(Collaborator collaborator) {
		// TODO: si se modifica el layout principal se tiene q cambiar el orden de estos elementos
		Tabpanel tabPanel = (Tabpanel)myProjectList.getParent();
		Tabpanels tabPanels = (Tabpanels)tabPanel.getParent();
		Tabbox tabBox = (Tabbox)tabPanels.getParent();
		Center center = (Center)tabBox.getParent();
		Borderlayout borderLayout = (Borderlayout)center.getParent();
		List children = borderLayout.getNorth().getChildren();
		Borderlayout subBorderLayout = (Borderlayout)children.get(0);
		East east = subBorderLayout.getEast();
		
		Events.postEvent("onNotifyCollaboration", east, collaborator);
	}
	
	// accion para invitar a otro usuario a colaborar en un proyecto
	public void onClick$btnProjectInvite() throws Exception{
		if(selectedProject!=null) {
			
			// parametros que se pasaran como argumentos al formulario de invitacion
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("project", selectedProject);			
			
			final Window win = 
				(Window)Executions.createComponents("/my_projects/invitation-form.zul", myProjectList, params);			
			win.doModal();
			
		}else {
			
			String language = this.getLanguage();
			if(language.equals("ES")) {
				Messagebox.show("Selecciona un proyecto", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
			}else if(language.equals("EN")) {
				Messagebox.show("Select a project", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}else if(language.equals("FR")) {
				Messagebox.show("Sélectionnez un projet", "Warning", Messagebox.OK, Messagebox.EXCLAMATION);
			}
						
		}		
		
	}
	
	public void onClick$btnProjectAdd(Event event) throws Exception{
		// Se crea el componente project-create.zul y se le relaciona como parent la lista de proyectos
		final Window win = 
			(Window)Executions.createComponents("/my_projects/project-create.zul", myProjectList, null);												
		win.doModal();
	}

	public List<Project> getModelProjectList() {
		return modelProjectList;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}
	
	/**
	 * crear un event listener para el evento personalizado "onCreateProject"
	 * que se dispara cuando se agrega un nuevo proyecto 
	 */		
	class ListboxEventListener implements EventListener {
		public void onEvent(Event event) throws Exception {
			// TODO: se obtiene el proyecto recien creado POR EL MOMENTO NO ES NECESARIO
			// Project newProject = (Project)event.getData();	        
	        if(profile!=null) {
	        	Long profileId = profile.getId();
	        	if(profileId!=null) {
	        		projectMgmtService = ServiceLocator.getProjectMgmtService();
	    			List<Project> projectsByProfileId = projectMgmtService.getProjectsByProfileId(profileId);
	    			
	    			modelProjectList = new ArrayList<Project>();
	    			modelProjectList.addAll(projectsByProfileId);
	        	}        	
	        }
			
			binder.loadAll();			
		}
		
		public boolean isAsap() {
			return true;
		}
	}

}