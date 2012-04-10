package itsolver.controller.contradiction;

import itsolver.components.SaveChanges2Project;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.Characteristic;
import itsolver.model.entity.Contradiction;
import itsolver.model.entity.ContradictionProject;
import itsolver.model.entity.ContradictionPrinciple;
import itsolver.model.entity.SubPrinciple;
import itsolver.service.ContradictionMatrixService;
import itsolver.utils.RadioPrinciple;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class SolutionContradictionController extends GenericZkComposer{
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -1043336528052613906L;	
	
	public Radiogroup rgpPrincipleList;
	//public Popup mesagePoppup;
	public SaveChanges2Project saveSolutionContradiction;
	public Vbox vBoxSubPrincipleList;
	private ContradictionProject project;
	private ContradictionMatrixService contradictionMatrixService;	
	private AnnotateDataBinder binder;
	@Override
	public void doAfterCompose(Component comp) throws Exception {	
		super.doAfterCompose(comp);
		saveSolutionContradiction.init((Window)arg.get("win"));
		contradictionMatrixService = ServiceLocator.getContradictionMatrixService();
		this.project = (ContradictionProject)desktop.getAttribute("currentProject");
		comp.setAttribute(comp.getId(), this, true);
		binder = new AnnotateDataBinder(comp);
        binder.loadAll(); 
		this.init();
	}	
	
	private void init(){
		rgpPrincipleList.addEventListener(Events.ON_CHECK, new EventListener() {			
			public void onEvent(Event arg0) throws Exception {
				if ( vBoxSubPrincipleList.getChildren() != null ){
					vBoxSubPrincipleList.getChildren().clear();
				}
				RadioPrinciple radioPrinciple = (RadioPrinciple)arg0.getTarget();
				List<SubPrinciple> subPrincipleList = radioPrinciple.getSolutionPrinciple().getSubPrincipleList();
				for (SubPrinciple subPrinciple : subPrincipleList) {
					vBoxSubPrincipleList.getChildren().add(new Label(subPrinciple.getSubprincipleDescription()));
				}
				
			}
		});
		this.initPrincipleList();
	}
	
	private void initPrincipleList(){
		Characteristic positiveCharacteristic = project.getContradiction().getPositiveCharacteristic();
		Characteristic negativeCharacteristic = project.getContradiction().getNegativeCharacteristic();
		Contradiction  contradiction=  contradictionMatrixService.findContradictionByCharacteristics(positiveCharacteristic, negativeCharacteristic);
		List<ContradictionPrinciple> contradictionPrincipleList = contradiction.getContradictionPrinciple();
		RadioPrinciple radioPrinciple;
		for (ContradictionPrinciple contradictionPrinciple : contradictionPrincipleList) {
			//radioPrinciple = new RadioPrinciple(contradictionPrinciple.getSolutionPrinciple(), mesagePoppup);
			radioPrinciple = new RadioPrinciple(contradictionPrinciple.getSolutionPrinciple());
			if ( project.getSolutionContradiction().getSolutionPrinciple() != null ){
				if ( project.getSolutionContradiction().getSolutionPrinciple().getId()
						.equals(contradictionPrinciple.getSolutionPrinciple().getId() )){
					radioPrinciple.setChecked(true);
				}
			}
			rgpPrincipleList.getChildren().add(radioPrinciple);
		}
	}
	
	public itsolver.model.entity.SolutionContradiction getSolutionContradiction(){
		return this.project.getSolutionContradiction();
	}
	
	public void onClick$saveSolutionContradiction(Event event) throws Exception {
		RadioPrinciple selectedItem = (RadioPrinciple)rgpPrincipleList.getSelectedItem();
		if ( selectedItem == null ){
			Messagebox.show("Solution principle is required", "Warning",Messagebox.OK, Messagebox.EXCLAMATION);
		}else{
			project.getSolutionContradiction().setSolutionPrinciple(selectedItem.getSolutionPrinciple());			
			updateProjectSolution();
		}
	}
	
	private void updateProjectSolution()throws Exception{
		Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();
		saveSolutionContradiction.setMessage("Has been saved solution principle for contradiction");
		saveSolutionContradiction.performProjectUpdate(project, desktop, collaborator, arg.get("win"));
	}

}
