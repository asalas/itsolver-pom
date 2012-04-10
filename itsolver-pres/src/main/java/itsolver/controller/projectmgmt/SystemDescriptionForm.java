package itsolver.controller.projectmgmt;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Window;

import itsolver.components.SaveChanges2Project;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.model.entity.Project;

public class SystemDescriptionForm extends GenericZkComposer{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1381321808983201664L;

	public SaveChanges2Project saveSystemDescription;
	
	private Project project;
	
	// internacinoalizacion
	public Label lblSystemDesc;
	
	public Tab tabSystemComp;
	public Tab tabSuperSystemComp;
	
	public Label lblSysPowerSrc;
	public Label lblSysEngine;
	public Label lblSysTransmission;
	public Label lblSysWU;
	public Label lblSysCtrlUnit;
	
	public Label lblSSysPowerSrc;
	public Label lblSSysEngine;
	public Label lblSSysTransmission;
	public Label lblSSysWU;
	public Label lblSSysCtrlUnit;
	
	private String msgSave = ""; 
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		saveSystemDescription.init((Window)arg.get("win"));
		this.project = (Project)desktop.getAttribute("currentProject");
		AnnotateDataBinder binder = new AnnotateDataBinder(comp);
		binder.bindBean("project", project);
		//initialize UI components
		binder.loadAll();
		
		String language = this.getLanguage();
		
		if(language.equals("EN")) {
			msgSave = "Changes on system components have been saved";
			saveSystemDescription.setButtonLabel("Save");
			saveSystemDescription.setCancelButtonLabel("Cancel");
		}else if(language.equals("ES")) {
			msgSave = "Los cambios en los componentes del sistema han sido guardados";
			saveSystemDescription.setButtonLabel("Guardar");
			saveSystemDescription.setCancelButtonLabel("Cancelar");
		}else if(language.equals("FR")) {
			msgSave = "finis";
			saveSystemDescription.setButtonLabel("Enregistrer");
			saveSystemDescription.setCancelButtonLabel("Annuler");
		}
	}
	
	public void onClick$saveSystemDescription(Event event)throws Exception {				
		// parte de la validacion del proceso de colaboracion
		Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();				
		saveSystemDescription.setMessage(msgSave);
		saveSystemDescription.performProjectUpdate(project, desktop, collaborator, arg.get("win"));
	}
	
}
