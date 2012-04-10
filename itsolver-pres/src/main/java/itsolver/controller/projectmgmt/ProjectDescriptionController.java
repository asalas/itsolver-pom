package itsolver.controller.projectmgmt;

import itsolver.components.SaveChanges2Project;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.model.entity.Project;

import java.util.Date;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class ProjectDescriptionController extends GenericZkComposer{
	
		static Logger logger = Logger.getLogger(ProjectDescriptionController.class);
	
		public static final String ELIMINATE_HARMFUL_FUNCTION = "ELIMINATE_HARMFUL_FUNCTION";
	
		public static final String ADD_NEW_FUNCTION = "ADD_NEW_FUNCTION";

		public static final String COST_REDUCTION = "COST_REDUCTION";

		public static final String IMPROVE_PERFORMANCE = "IMPROVE_PERFORMANCE";

		private static final long serialVersionUID = 1018889902746621846L;
		
		public Label lblProjectName;
		public Radiogroup problemNature;
		public Datebox dbEstimatedRelase;
		public Textbox txtBriefDescription;
		public Textbox txtLongDescription;
		public Textbox txtImprovementDrawback;
		
		//Eriquetas efectadas por el i18
		public Label lblProyectName;
		public Label lblProjectNature;
		public Radio lblEliminateFunction;
		public Radio lblAddNewFunction;
		public Radio lblCostReduction;
		public Radio lblImprovePerformance;
		public Label lblEstimatedRelase;
		public Label lblBriefDesc;
		public Label lblProblemSituation;
		public Label lblImprovementDrawback;
		
		public SaveChanges2Project saveChangesDescription;	
		
		private Project project;
		
		private String msgSave ="";
		
		@Override
	    public void doAfterCompose(Component comp) throws Exception {
	        super.doAfterCompose(comp);	        
	        saveChangesDescription.init((Window)arg.get("win"));
	        this.project = (Project)desktop.getAttribute("currentProject");
	        
	        String language = this.getLanguage();	
			msgSave ="";
			String msgButton = "";
			String msgCancelButton = "";
			if(language.equals("EN")) {
				msgSave = "Has been saved the project description";
				msgButton = "Save";
				msgCancelButton = "Cancel";
			}else if(language.equals("ES")) {
				msgSave = "Se ha guardado la descripcion del proyecto";
				msgButton = "Guardar";
				msgCancelButton = "Cancelar";
			}else if(language.equals("FR")) {
				msgSave = "finis";
				msgButton = "Enregistrer";
				msgCancelButton = "Annuler";
			}			
			saveChangesDescription.setButtonLabel(msgButton);
			saveChangesDescription.setCancelButtonLabel(msgCancelButton);
		}		
		
		public void onCreate$projectDescription(Event event) throws Exception {
			//get the params map that are overhanded by creation.
			lblProjectName.setValue(project.getProjectName());	        
	        String problemNatur = project.getProblemDescription().getProblemNature();
	        if ( problemNatur != null && problemNatur.length() > 0){	        	
	        	problemNature.setSelectedIndex(getProblemNaturIndex(problemNatur));
	        }
	        dbEstimatedRelase.setValue(project.getProblemDescription().getEstimatedRelase());	        
	        txtBriefDescription.setValue(project.getProblemDescription().getBriefDescription());
	        txtLongDescription.setValue(project.getProblemDescription().getLongDescription());
	        txtImprovementDrawback.setValue(project.getProblemDescription().getImprovementDrawback());
		}
		
		public int getProblemNaturIndex(String projectNature){			
			if (projectNature.compareTo(ELIMINATE_HARMFUL_FUNCTION) == 0){
				return 0;
			}
			if (projectNature.compareTo(ADD_NEW_FUNCTION) == 0){
				return 1;
			}
			if (projectNature.compareTo(COST_REDUCTION) == 0){
				return 2;
			}
			if (projectNature.compareTo(IMPROVE_PERFORMANCE) == 0){
				return 3;
			}
			return -1;
		}
		
		public void onClick$saveChangesDescription(Event event) throws Exception {
			Date estimatedRelase = dbEstimatedRelase.getValue();
			project.setProjectName(lblProjectName.getValue());
			project.getProblemDescription().setEstimatedRelase(estimatedRelase);
			
			if ( problemNature.getSelectedItem() != null && problemNature.getSelectedItem().getValue() != null ){
				project.getProblemDescription().setProblemNature(problemNature.getSelectedItem().getValue());
			}
			project.getProblemDescription().setBriefDescription(txtBriefDescription.getValue());
			project.getProblemDescription().setLongDescription(txtLongDescription.getValue());
			project.getProblemDescription().setImprovementDrawback(txtImprovementDrawback.getValue());
			
			// parte de la validacion del proceso de exclusion mutua				
			Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();
			saveChangesDescription.setMessage(msgSave);
			saveChangesDescription.performProjectUpdate(project, desktop, collaborator, arg.get("win"));			
		}
	}

