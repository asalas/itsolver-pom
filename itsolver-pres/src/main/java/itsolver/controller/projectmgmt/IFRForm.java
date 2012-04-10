package itsolver.controller.projectmgmt;

import itsolver.components.SaveChanges2Project;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.model.entity.IFR;
import itsolver.model.entity.Project;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Label;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class IFRForm extends GenericZkComposer{

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -8677204408110600179L;
	
	public Textbox txtEliminateDeficiencies;	
	public Textbox txtPreserveAdvantages;	
	public Textbox txtNotIncreaseComplexity;	
	public Textbox txtNotCreateDisadvantages;
	
	public SaveChanges2Project saveChangesIFR;
	
	private Project project;
	
	public Panel pnlIFRTitle;
	
	public Label lblStep1;
	public Label lblStep2;
	public Label lblStep3;
	public Label lblStep4;
	
	private String msgSave = "";
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);		
		saveChangesIFR.init((Window)arg.get("win"));
		this.project = (Project)desktop.getAttribute("currentProject");
		IFR ifr = this.project.getIfr();
		
		txtEliminateDeficiencies.setValue(ifr.getEliminateDeficiencies());
		txtPreserveAdvantages.setValue(ifr.getPreserveAdvantages());
		txtNotIncreaseComplexity.setValue(ifr.getNotIncreaseComplexity());
		txtNotCreateDisadvantages.setValue(ifr.getNotCreateDisadvantages());
		
		String language = this.getLanguage();
		msgSave = "";
		if(language.equals("EN")) {
			msgSave = "Has been saved the IFR";
			saveChangesIFR.setButtonLabel("Save");
			saveChangesIFR.setCancelButtonLabel("Cancel");
		}else if(language.equals("ES")) {
			msgSave = "Se ha guardado el RFI";
			saveChangesIFR.setButtonLabel("Guardar");
			saveChangesIFR.setCancelButtonLabel("Cancelar");
		}else if(language.equals("FR")) {
			msgSave = "finis";
			saveChangesIFR.setButtonLabel("Sauver");
			saveChangesIFR.setCancelButtonLabel("Annuler");
		}
	}
	
	public void onClick$saveChangesIFR(Event event)throws Exception {
		project.getIfr().setEliminateDeficiencies(txtEliminateDeficiencies.getText());
		project.getIfr().setPreserveAdvantages(txtPreserveAdvantages.getText());
		project.getIfr().setNotIncreaseComplexity(txtNotIncreaseComplexity.getText());
		project.getIfr().setObstacles(txtNotCreateDisadvantages.getText());		
		
		// parte de la validacion del proceso de colaboracion
		Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();		
		
		saveChangesIFR.setMessage(msgSave);
		saveChangesIFR.performProjectUpdate(project, desktop, collaborator, arg.get("win"));
	}

}
