package itsolver.controller.projectmgmt;

import itsolver.components.SaveChanges2Project;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.ContradictionProject;
import itsolver.model.entity.InventiveStandard;
import itsolver.model.entity.Project;
import itsolver.model.entity.SolutionContradiction;
import itsolver.model.entity.SolutionSuField;
import itsolver.model.entity.SuField;
import itsolver.model.entity.SuFieldModel;
import itsolver.model.entity.cbr.CBRCaseEntity;
import itsolver.model.entity.sufield.SuFieldGraph;
import itsolver.service.ProjectMgmtService;
import itsolver.service.cbr.CbrService;
import itsolver.utils.ProjectStatus;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Window;

public class ProjectSolutionController extends GenericZkComposer{
	
	private static final String NOVALUE = "NOVALUE";

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 7374777997897507502L;
	
	public Tabbox tabMenu;
	public Window winProjectEdit;
	public Tabpanel tpnSolutionContradiction;
	public Tabpanel tpnSolutionSuField;
	public Tabpanel tpnSolutionImplementation;
	public Tabpanel tpnConfirmSolution;
	public Tab tabContradictionSolution;
	public Tab tabSufieldSolution;
	public Tab tabConfirmsolution;
	
	public SaveChanges2Project saveConfirmSolution;	
	public SaveChanges2Project saveSolutionDetails;
	private AnnotateDataBinder binder;	
	
	public Listbox lbxSolutionSatisfaction;
	
	private CbrService cbrService;
	private ProjectMgmtService projectMgmtService;
	private Project project;
	private Map<String, Component> params;
	
	public Tab tabProbSituation;
	public Tab tabImplementationSolution;
	public Tab tabDetails;
	
	public Label lblProjectName;
	public Label lblProjNature;
	public Label lblEstimatedRelease;
	public Label lblProbSituation;
	public Label lblImprovement;
	public Label lblDetailsSolution;
	public Label lblComments;
	public Label lblProbWithSolution;
	public Label lblInfoSources;
	public Label lblAlternativeSol;
	public Label lblExpertContact;
	public Label lblConfirmSolution;
	public Label lblSolutionSatisfaction;
	
	private String language;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {	
		super.doAfterCompose(comp);
		saveConfirmSolution.init((Window)arg.get("win"));
		saveSolutionDetails.init((Window)arg.get("win"));
		
		tabMenu.addEventListener(Events.ON_SELECT, new TabBoxListener());
		params = new HashMap<String, Component>();
		params.put("win", winProjectEdit);
		
		this.project= (Project)desktop.getAttribute("currentProject");
		cbrService 	= ServiceLocator.getCbrService();
		projectMgmtService = ServiceLocator.getProjectMgmtService();		
		
		if(project.getProjectStatus() == ProjectStatus.FINISHED_SUCCESS) {
			tabConfirmsolution.setVisible(false);
		}		
		
		comp.setAttribute(comp.getId(), this, true);
		binder = new AnnotateDataBinder(comp);
        binder.loadAll(); 
        
        language = this.getLanguage();
        
		this.init();
	}
	
	private void init(){
		String msgButton = "Confirm solution";
		String msgCancelButton = "Cancel";
		if(language.equals("ES")) {
			msgButton = "Confirmar la solucion";
			msgCancelButton = "Cancelar";
		}else if(language.equals("FR")) {
			msgButton = "Confirmer la solution";
			msgCancelButton = "Annuler";
		}
		
		saveConfirmSolution.setButtonLabel(msgButton);
		saveConfirmSolution.setCancelButtonLabel(msgCancelButton);
		
		if(project instanceof ContradictionProject){
			tabContradictionSolution.setVisible(true);			
		}else if (project instanceof SuField){
			tabSufieldSolution.setVisible(true);
		}
	}
	
	public void onClick$saveConfirmSolution(Event event) throws Exception {		
		confirmProjectSolution();
	}
	
	
	public void onClick$saveSolutionDetails(Event event) throws Exception {
		updateProjectDetails();
	}
	
	private void updateProjectDetails()throws Exception{
		Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();
		
		String msg = "Changes on project solution have been updated";
		if(language.equals("ES")) {
			msg = "Los cambios en la solucion del proyecto se han actualizado";
		}else if(language.equals("")) {
			msg = "finis";
		}
		
		saveSolutionDetails.setMessage(msg);
		saveSolutionDetails.performProjectUpdate(project, desktop, collaborator, arg.get("win"));
	}
	
	private void confirmProjectSolution()throws Exception{		
		if ( validateProposedSolution() ){
			final String strSolutionSatisfaction = lbxSolutionSatisfaction.getSelectedItem().getValue().toString();
			if ( strSolutionSatisfaction.compareTo(NOVALUE) != 0 ){
				
				String msg = "Once you confirm the solution future changes on the project will not be posible." +
				"Do you want to continue?";
				String msgQ = "Are you sure you want to confirm solution?";
				
				if(language.equals("ES")){
					msg = "Una vez confirmada la solucion, no sera posible realizarle cambios en el futuro." +
							"Desea continuar?";
					msgQ = "¿Esta seguro que quiere confirmar la solucion?";
				}else if(language.equals("FR")) {
					msg = "finis";
					msgQ = "Question finis";
				}
				
				Messagebox.show(msg, msgQ, 
				Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener() {
					
					public void onEvent(Event event) throws Exception {
						if (((Integer) event.getData()).intValue() == Messagebox.YES) {
							doCreateCBRCase(strSolutionSatisfaction);
							return;
						} 	
					}
				});				
			}else{
				String msg = "Solution satisfaction is required";
				if(language.equals("ES")){
					msg = "La satisfaccion de la solucion es requerida";
				}else if(language.equals("FR")) {
					msg = "finis";
				}
				saveConfirmSolution.setErrorMessage(msg);
			}
		}		
	}

	private void doCreateCBRCase(String strSolutionSatisfaction) throws Exception {
		Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();
		CBRCaseEntity cbrCaseEntity = cbrService.getCBRCaseEntity(project);
		if ( cbrCaseEntity != null ){
			project.getProjectSolution().setSatisfactionPercent(Integer.parseInt(strSolutionSatisfaction));
			projectMgmtService.projectRefresh(project);			
			cbrCaseEntity.setProject(project);
			cbrService.createCbrEntity(cbrCaseEntity);
			project.setProjectStatus(ProjectStatus.FINISHED_SUCCESS);
			
			String msg = "Changes on project solution have been updated";
			
			if(language.equals("ES")) {
				msg = "Se han actualizado los cambios en la solucion del proyecto";
			}else if(language.equals("FR")) {
				msg = "finis";
			}
			
			saveConfirmSolution.setMessage(msg);
			
			saveConfirmSolution.performProjectUpdate(project, desktop, collaborator, arg.get("win"));
			tpnConfirmSolution.setVisible(false);
			tabConfirmsolution.setVisible(false);
		}else{
			
			String msg = "There was a problem saving the case in CBR";
			
			if(language.equals("ES")) {
				msg = "Ha ocurrido un error guardando el caso en el CBR";
			}else if(language.equals("FR")) {
				msg = "error";
			}			
			
			saveConfirmSolution.setErrorMessage(msg);
		}
	}
		
	private boolean validateProposedSolution(){
		if ( project instanceof ContradictionProject ){
			ContradictionProject contradictionMatrix = (ContradictionProject)project;
			SolutionContradiction solutionContradiction = contradictionMatrix.getSolutionContradiction();
			if (solutionContradiction == null || solutionContradiction.getSolutionPrinciple() == null 
					|| solutionContradiction.getSolutionPrinciple().getId() == null){
				
				String msg = "Proposed solution is required";
				
				if(language.equals("ES")) {
					msg = "La solucion propuesta es requerida";
				}else if(language.equals("FR")) {
					msg = "finis";
				}
				
				saveConfirmSolution.setErrorMessage(msg);
				return false;
			}
		}else if ( project instanceof SuField ){
			SuField suField = (SuField)project;
			SolutionSuField solutionSuField = suField.getSolutionSuField();
			
			InventiveStandard standardInSolution = solutionSuField.getInventiveStandard();
			SuFieldModel sufieldModel = suField.getSufieldModel();
			SuFieldGraph sufieldGraph = suField.getSufieldGraph();
			
			if (standardInSolution == null){
				String msg = "Proposed solution is required";
				
				if(language.equals("ES")) {
					msg = "La solucion propuesta es requerida";
				}else if(language.equals("FR")) {
					msg = "finis";
				}
				saveConfirmSolution.setErrorMessage(msg);
				return false;
			}else if(sufieldGraph == null || sufieldModel == null) {
				String msg = "SuField model is required";
				
				if(language.equals("ES")) {
					msg = "El modelo SuField es requerido";
				}else if(language.equals("FR")) {
					msg = "finis";
				}
				saveConfirmSolution.setErrorMessage(msg);
				return false;
			}
		}
		return true;
	}
	
	
	class TabBoxListener implements EventListener {
		
		public void onEvent(Event event) throws Exception {
			// que tab se selecciono y su correspondiente tabpanel
    		Tab selectedTab = tabMenu.getSelectedTab();
    		// se obtiene el id del tab q se selecciono
    		String selectedTabId = selectedTab.getId();
    		if(selectedTabId.equals("tabContradictionSolution")) {
    			tpnSolutionContradiction.getChildren().clear();
    			Executions.createComponents("../contradictions/solution-contradiction.zul", tpnSolutionContradiction, params);    			
    		}else if(selectedTabId.equals("tabSufieldSolution")) {
    			tpnSolutionSuField.getChildren().clear();
    			Executions.createComponents("../sufield/solution-sufield.zul", tpnSolutionSuField, params);    			
    		}else if(selectedTabId.equals("tabImplementationSolution")) {
    			tpnSolutionImplementation.getChildren().clear();
    			Executions.createComponents("../my_projects/solution-implementation.zul", tpnSolutionImplementation, params);    			
    		} 
		}		
	}


	public Project getProject() {
		return project;
	}
	

}