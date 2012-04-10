package itsolver.controller.projectmgmt;

import java.util.HashMap;
import java.util.Map;

import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.model.entity.ContradictionProject;
import itsolver.model.entity.Project;
import itsolver.model.entity.SuField;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Window;

public class ProjectEdit extends GenericZkComposer {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -7923874458654771923L;
	
	public Window winProjectEdit;
	
	public Tabbox tabBoxMenu;	
	public Tabpanel tpnLastSelected;	
	public Tabpanel tpnProjectDescription;	
	public Tabpanel tpnProjectResources;
	public Tabpanel tpnSystemDescription;
	public Tabpanel tpnContradictionMatrix;
	public Tabpanel tpnCBRContradiction;
	public Tabpanel tpnIFR;
	public Tabpanel tpnDiagramTool;
	public Tabpanel tpnRestrictions;	
	public Tabpanel tpnProjectSolution;
	public Tabpanel tpnCBRSuField;
	
	private Collaborator collaborator;
	
	private Project project;	
	private Map<String, Component> params;
	
	// cambio de idioma - componentes afectados
	public Tab tabProjectDescription;
	public Tab tabSystemDescription;
	public Tab tabProjectResources;
	public Tab tabRestrictions;
	public Tab tabIFR;
	public Tab tabContradictionMatrix;
	public Tab tabCBRContradiction;
	public Tab tabCBRSuField;
	public Tab tabProjectSolution;
	
	public Label lblEditWarning;	
	
	@Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        tabBoxMenu.addEventListener(Events.ON_SELECT, new TabBoxListener());
        
        params = new HashMap<String, Component>();
		params.put("win", winProjectEdit);
		tpnLastSelected = tpnProjectDescription;
		this.project = (Project)desktop.getAttribute("currentProject");
    }
	
	public void onCreate$winProjectEdit(Event event)throws Exception {
		Executions.createComponents("../my_projects/project-description-form.zul", tpnProjectDescription, params);
	}
	
	public void onClose$winProjectEdit(Event event)throws Exception {
		collaborator = MutualExclusionControl.getCurrentCollaborator();
		if(collaborator!=null) {
			MutualExclusionControl.onExit();
		}		
	}
	
	class TabBoxListener implements EventListener {
		public void onEvent(Event event) throws Exception {
			
			collaborator = MutualExclusionControl.getCurrentCollaborator();
			if(collaborator!=null) {
				boolean timedOut = collaborator.isTimedOut();
				
				if(timedOut) {
					winProjectEdit.detach();
					Messagebox.show("Timeout!!");
					return;
				}else {
					MutualExclusionControl.increaseTimeout(10L);
				}
			}					
			
			// que tab se selecciono y su correspondiente tabpanel
    		Tab selectedTab = tabBoxMenu.getSelectedTab();
    		// se obtiene el id del tab q se selecciono
    		String selectedTabId = selectedTab.getId();
    		
    		if(selectedTabId.equals("tabProjectDescription")) {
    			tpnLastSelected.getChildren().clear();
    			tpnProjectDescription.getChildren().clear();    			
    			Executions.createComponents("../my_projects/project-description-form.zul", tpnProjectDescription, params);
    			tpnLastSelected = tpnProjectDescription;
    		}else if(selectedTabId.equals("tabProjectResources")) {
    			tpnProjectResources.getChildren().clear();
    			tpnLastSelected.getChildren().clear();
    			Executions.createComponents("../my_projects/project-resources.zul", tpnProjectResources, params);
    			tpnLastSelected = tpnProjectResources;
    		}else if(selectedTabId.equals("tabRestrictions")) {
    			tpnRestrictions.getChildren().clear();
    			tpnLastSelected.getChildren().clear();
    			Executions.createComponents("../my_projects/restrictions.zul", tpnRestrictions, params);
    			tpnLastSelected = tpnRestrictions;
    		}else if(selectedTabId.equals("tabIFR")) {
    			tpnIFR.getChildren().clear();
    			tpnLastSelected.getChildren().clear();
    			Executions.createComponents("../my_projects/ifr.zul", tpnIFR, params);
    			tpnLastSelected = tpnIFR;
    		}else if(selectedTabId.equals("tabDiagramTool")) {
    			showDiagramingTool();
    		}else if(selectedTabId.equals("tabContradictionMatrix")) {
    			showContradictionModule();
    		}else if(selectedTabId.equals("tabCBRContradiction")) {
    			if ( isRequiredContradiction() ){
    				tpnCBRContradiction.getChildren().clear();
    				tpnLastSelected.getChildren().clear();
    				Executions.createComponents("../contradictions/cbr-contradiction.zul", tpnCBRContradiction, params);
    				tpnLastSelected = tpnCBRContradiction;
    			}else{
    				Messagebox.show("Contradiction is required for doing the search in CBR", "Required information",
    	                    Messagebox.OK, Messagebox.INFORMATION);
    				tabBoxMenu.setSelectedPanel(tpnContradictionMatrix);
    				showContradictionModule();    				
    			}
    		}else if(selectedTabId.equals("tabProjectSolution")) {    						
    			if (isCompleatedProject()){
    				showSolutionOption();
    			}
    		}else if(selectedTabId.equals("tabSystemDescription")) {
    			tpnSystemDescription.getChildren().clear();
    			tpnLastSelected.getChildren().clear();
    			Executions.createComponents("../my_projects/system-description.zul", tpnSystemDescription, params);
    			tpnLastSelected = tpnSystemDescription;
    		}else if(selectedTabId.equals("tabCBRSuField")) {
    			tpnCBRSuField.getChildren().clear();
    			tpnLastSelected.getChildren().clear();
    			Executions.createComponents("../sufield/cbr-sufield.zul", tpnCBRSuField, params);
    		}
			
		}

		private boolean isCompleatedProject() throws Exception{			
			boolean isCompleated = true;
			if ( project instanceof ContradictionProject ){
				isCompleated = isRequiredContradiction();
				if ( !isCompleated ){
					Messagebox.show("Contradiction is required", "Required information",
    	                    Messagebox.OK, Messagebox.INFORMATION);
    				tabBoxMenu.setSelectedPanel(tpnContradictionMatrix);
    				showContradictionModule();
				}
			}else if (project instanceof SuField){
				isCompleated = isRequiredSuFieldModel();
				if ( !isCompleated ){
					Messagebox.show("SuField model is required", "Required information",
    	                    Messagebox.OK, Messagebox.INFORMATION);
    				tabBoxMenu.setSelectedPanel(tpnDiagramTool);
    				showDiagramingTool();  
				}
			}
			return isCompleated;
		}

		private void showDiagramingTool() {
			tpnDiagramTool.getChildren().clear();
			tpnLastSelected.getChildren().clear();
			Executions.createComponents("../sufield/diagram-tool.zul", tpnDiagramTool, params);
			tpnLastSelected = tpnDiagramTool;
		}

		private void showSolutionOption() {			
			tpnProjectSolution.getChildren().clear();
			tpnLastSelected.getChildren().clear();
			Executions.createComponents("../my_projects/project-solution.zul", tpnProjectSolution, params);
			tpnLastSelected = tpnProjectSolution;
		}

		private void showContradictionModule() {
			tpnContradictionMatrix.getChildren().clear();
			tpnLastSelected.getChildren().clear();
			Executions.createComponents("../contradictions/contradiction-matrix.zul", tpnContradictionMatrix, params);
			tpnLastSelected = tpnContradictionMatrix;
		}
	}
	
	private boolean isRequiredContradiction(){
		ContradictionProject contradictionMatrix = (ContradictionProject)this.project;
		if ( contradictionMatrix.getContradiction() == null 
				|| contradictionMatrix.getContradiction().getPositiveCharacteristic() == null 
				|| contradictionMatrix.getContradiction().getNegativeCharacteristic() == null){
			return false;
		}
		return true;
	}
	private boolean isRequiredSuFieldModel(){
		SuField suField = (SuField)this.project;
		if ( suField.getSufieldModel() == null 
				|| suField.getSufieldModel().getModelGraphML() == null 
				|| suField.getSufieldModel().getModelGraphML().length() == 0 ){
			return false;
		}
		return true;
	}
}