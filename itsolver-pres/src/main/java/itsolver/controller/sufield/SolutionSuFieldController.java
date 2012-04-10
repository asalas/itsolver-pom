package itsolver.controller.sufield;

import itsolver.components.SaveChanges2Project;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ItSolverEvents;
import itsolver.model.entity.InventiveStandard;
import itsolver.model.entity.SolutionSuField;
import itsolver.model.entity.SuField;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class SolutionSuFieldController extends GenericZkComposer {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 4520512979947581502L;

	public SaveChanges2Project saveSolutionSuField;
	public Vbox vboxSolutionSuField;
	public Label lblInvStandardNumber, lblInvStandardDesc, lblInvStandardImgdesc;
	public Image imgInvStandard;
	public Textbox txtHowWasItAdapted;
	
	public Label lblStandard, lblHowWasItAdapted;
	public Button btnAlgorithmUseStandards;

	private AnnotateDataBinder binder;

	private SuField suFieldProj;
	private InventiveStandard selectedInventiveStandard;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		comp.setAttribute(comp.getId(), this, true);

		saveSolutionSuField.init((Window) arg.get("win"));
		
		String language = this.getLanguage();
		String msgButton = "Save", msgButtonCancel="Cancel";
		if(language.equals("ES")) {
			msgButton = "Guardar"; msgButtonCancel="Cancelar";
		}else if(language.equals("FR")) {
			msgButton = "Enregister"; msgButtonCancel="Annuler";
		}
		saveSolutionSuField.setButtonLabel(msgButton);
		saveSolutionSuField.setCancelButtonLabel(msgButtonCancel);

		suFieldProj = (SuField) desktop.getAttribute("currentProject");

		vboxSolutionSuField.addEventListener(
				ItSolverEvents.ON_SELECT_INVENTIVE_STANDARD,
				new EventListener() {
					public void onEvent(Event event) throws Exception {
						selectedInventiveStandard = (InventiveStandard)event.getData();
						
						lblInvStandardNumber.setValue(selectedInventiveStandard.getInventiveStandardNumber());
						lblInvStandardDesc.setValue(selectedInventiveStandard.getDescription());
						lblInvStandardImgdesc.setValue(selectedInventiveStandard.getImageDesc());
						imgInvStandard.setSrc(selectedInventiveStandard.getImageURL());
					}
				});

		binder = new AnnotateDataBinder(comp);
		binder.loadAll();

	}

	public SuField getSuFieldProj() {
		return suFieldProj;
	}

	public SolutionSuField getSolutionSuField() {
		return suFieldProj.getSolutionSuField();
	}

	public void onClick$btnAlgorithmUseStandards(Event event) throws Exception {
		Window win = (Window) Executions.createComponents(
				"/sufield/algorithm_standards/algorithm-standards.zul",
				vboxSolutionSuField, null);
		win.doModal();
	}
	
	public void onClick$saveSolutionSuField(Event event) throws Exception {
		if(selectedInventiveStandard!=null) {
			suFieldProj.setInventiveStandard(selectedInventiveStandard);
			suFieldProj.getSolutionSuField().setInventiveStandard(selectedInventiveStandard);			
		}
		suFieldProj.getSolutionSuField().setHowWasItAdapted(txtHowWasItAdapted.getValue());
		
		
		updateProjectSolution();
	}
	
	private void updateProjectSolution()throws Exception{
		Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();
		
		String language = this.getLanguage();
		String msg="Has been saved the inventive standard";
		
		if(language.equals("ES")) {
			msg="Se ha guardado el estandar de inventiva";
		}else if(language.equals("FR")) {
			msg="finis!";
		}
		
		saveSolutionSuField.setMessage(msg);
		saveSolutionSuField.performProjectUpdate(suFieldProj, desktop, collaborator, arg.get("win"));
	}

}
