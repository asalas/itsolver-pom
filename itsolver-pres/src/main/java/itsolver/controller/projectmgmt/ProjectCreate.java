package itsolver.controller.projectmgmt;

import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.ContradictionProject;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Project;
import itsolver.model.entity.SuField;
import itsolver.service.ProjectMgmtService;
import itsolver.utils.CurrentSession;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class ProjectCreate extends GenericZkComposer{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 7779116552460958955L;

	private static final String SU_FIELD_TYPE = "SU-FIELD";
	private static final String CONTRADICTIONS_TYPE = "CONTRADICTIONS";
	public Textbox txtProjectName;	
	public Window winProjectCreate;
	
	public Label lblCreateProj;
	public Label lblAddNewProj;
	public Label lblProjName;
	public Label lblProjType;
	public Label lblProjectTypeRequired;
	
	public Button btnCreateProject;
	public Button btnCancelCreate;
	
	public Radio radioContradictions;
	public Radio radioSufield;
	
	public Radiogroup rgpProyectType;
	
	private ProjectMgmtService projectMgmtService;
	
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        projectMgmtService = ServiceLocator.getProjectMgmtService();
    }
	
	
	public void onClick$btnCreateProject(Event event) throws Exception{
		if (!doValidation()){
			return;
		}
		
		Project project = createProject();		
		
		if (project != null && project.getId() > 0 ){			
			
			CurrentSession currentSession = (CurrentSession)session.getAttribute("currentSession");
	        Profile profile = currentSession.getProfile();
	        
	        // se inicia el proceso de exclusion mutua
			Collaborator collaborator = MutualExclusionControl.onEditProject(project, profile);	
			// como es un proyecto recien creado no hay colaboradores editandolo, asi que se procede a trabajar con el proyecto
			if(collaborator==null) {
				onCreateProject(project);
			}		
			
		}else{
			  Messagebox.show("There was an error while saving the new project", "Notification",Messagebox.OK, Messagebox.INFORMATION);
		}
		
		winProjectCreate.detach();
	}


	// creacion de un nuevo proyecto
	private void onCreateProject(Project project) throws InterruptedException {
		desktop.setAttribute("currentProject", project);
		Window win = null;
		if ( project instanceof ContradictionProject){
			win = (Window)Executions.createComponents("/contradictions/project-edit.zul", null,null);
		}else if (project instanceof SuField) {
			win = (Window)Executions.createComponents("/sufield/project-edit.zul", null,null);
		}											

		win.setTitle(project.getProjectName().toUpperCase());
		win.doModal();	
		// refrescar la lista de proyectos
		addProjectToList(project);
	}

	private Project createProject() {
		String projectType = rgpProyectType.getSelectedItem().getValue();
		Project project= null;
		if (projectType.compareTo(CONTRADICTIONS_TYPE) == 0){
			projectType ="Contradictions";
			project = new ContradictionProject();
		}else if (projectType.compareTo(SU_FIELD_TYPE) == 0 ){
			projectType = "Su-Field";			
			project = new SuField();
		}
		
		project.setProjectName(txtProjectName.getText());
		
		CurrentSession currentSession = (CurrentSession)session.getAttribute("currentSession");
		project.setProfile(currentSession.getProfile());
		
		this.projectMgmtService.projectCreate(project);
		this.projectMgmtService.projectRefresh(project);
		return project;
	}


	private void addProjectToList(Project project) {
		// TODO: atencion! si el orden de los componentes de my-project-list cambia, se tienen q ajustar los indices
		Div myProjectList = (Div)winProjectCreate.getParent();
		
		List childrens = myProjectList.getChildren();
		// selecciona el listbox
		Listbox projectListBox = (Listbox)childrens.get(2);	
		
		Events.postEvent("onNewProject", projectListBox, project);
	}
	
	private boolean doValidation() {
		if(rgpProyectType.getSelectedItem() == null ) {
			lblProjectTypeRequired.setVisible(true);
			return false;
		}
		return true;		
	}
	
	public void onClick$btnCancelCreate(Event event) throws Exception{		
		winProjectCreate.detach();
	}	

}
