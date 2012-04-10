package itsolver.controller.contradiction;

import itsolver.components.SaveChanges2Project;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.Characteristic;
import itsolver.model.entity.Contradiction;
import itsolver.model.entity.ContradictionPrinciple;
import itsolver.model.entity.ProjectContradiction;
import itsolver.model.entity.ContradictionProject;
import itsolver.service.ContradictionMatrixService;
import itsolver.service.ProjectMgmtService;

import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class Contradictions extends GenericZkComposer{

	private static final long serialVersionUID = -3170520953310697231L;
	private static final String NOVALUE = "NOVALUE";
	
	public Listbox lbxCategoryPositive;
	public Listbox lbxPositiveCharacteristic;	
	public Label lblPositiveCharacteristicDesc;
	public Textbox txtPositiveCharacteristic;
	
	public Listbox lbxCategoryNegative;
	public Listbox lbxNegativeCharacteristic;
	public Label lblNegativeCharacteristicDesc;
	public Textbox txtNegativeCharacteristic;
	
	public Vbox vboxPrincipleList;
	
	public SaveChanges2Project  saveChangesContradiction;
	
	private ContradictionProject project;	
	private ProjectMgmtService projectMgmtService;
	private ContradictionMatrixService contradictionMatrixService;	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {	
		super.doAfterCompose(comp);
		saveChangesContradiction.init((Window)arg.get("win"));
		this.project = (ContradictionProject)desktop.getAttribute("currentProject");		
		this.projectMgmtService		= ServiceLocator.getProjectMgmtService();
		contradictionMatrixService = ServiceLocator.getContradictionMatrixService();
		initContradiction();
	}
	
	private void initContradiction(){
		if ( this.project.getContradiction() == null ){
			return;
		}
		Characteristic positiveCharacteristic = this.project.getContradiction().getPositiveCharacteristic();
		if ( positiveCharacteristic != null && positiveCharacteristic.getId() != null){
			lbxCategoryPositive.setSelectedIndex(getSelectedIndex(lbxCategoryPositive, positiveCharacteristic.getCategory()));
			updatePositiveCharacteritic();
			lbxPositiveCharacteristic.setSelectedIndex(getSelectedIndex(lbxPositiveCharacteristic, positiveCharacteristic.getId().toString()));					
		}
		Characteristic negativeCharacteristic = this.project.getContradiction().getNegativeCharacteristic();
		if ( negativeCharacteristic != null && negativeCharacteristic.getId() != null){
			lbxCategoryNegative.setSelectedIndex(getSelectedIndex(lbxCategoryNegative, negativeCharacteristic.getCategory()));
			updateNegativeCharacteritic();
			lbxNegativeCharacteristic.setSelectedIndex(getSelectedIndex(lbxNegativeCharacteristic, negativeCharacteristic.getId().toString()));					
		}
		this.txtPositiveCharacteristic.setValue(this.project.getContradiction().getPositiveDescription());
		this.txtNegativeCharacteristic.setValue(this.project.getContradiction().getNegativeDescription());
	}
	
	@SuppressWarnings("unchecked")
	private int getSelectedIndex(Listbox listbox, String value){
		Listitem listitem;
		int counter = 0;
		for (Iterator iterator = listbox.getItems().iterator(); iterator.hasNext();) {
			listitem = (Listitem) iterator.next();			
			if ( listitem.getValue().toString().equals(value) ){
				break;
			}
			counter++;
		}
		return counter;
	}
	
	public void onSelect$lbxCategoryPositive()throws Exception {		
		updatePositiveCharacteritic();
	}
	
	private void updatePositiveCharacteritic(){
		lblPositiveCharacteristicDesc.setValue("");
		String selectedCategory = lbxCategoryPositive.getSelectedItem().getValue().toString();
		if (selectedCategory != NOVALUE){			
			lbxPositiveCharacteristic.getChildren().clear();
			List<Characteristic> characteristicList = this.projectMgmtService.getCharacteristicByCategory(selectedCategory);			
			lbxPositiveCharacteristic.appendItem("--Select--", NOVALUE);			
			for (Characteristic characteristic : characteristicList) {
				lbxPositiveCharacteristic.appendItem(characteristic.getName(), characteristic.getId().toString());
			}
			lbxPositiveCharacteristic.setSelectedIndex(0);
		}
	}
	
	public void onSelect$lbxPositiveCharacteristic() throws Exception{
		showPositiveCharacteristic();
	}
	
	private void showPositiveCharacteristic(){
		String selectedId = lbxPositiveCharacteristic.getSelectedItem().getValue().toString();
		if (selectedId != NOVALUE){	
			Characteristic characteristic = this.projectMgmtService.getCharacteristicById(Long.parseLong(selectedId));
			lblPositiveCharacteristicDesc.setValue(characteristic.getDescription());
		}else{
			lblPositiveCharacteristicDesc.setValue("");
		}		
	}
	
	public void onSelect$lbxCategoryNegative()throws Exception {		
		updateNegativeCharacteritic();
	}
	
	private void updateNegativeCharacteritic(){
		String selectedCategory = lbxCategoryNegative.getSelectedItem().getValue().toString();
		if (selectedCategory != NOVALUE){			
			lbxNegativeCharacteristic.getChildren().clear();
			lbxNegativeCharacteristic.appendItem("--Select--", NOVALUE);
			List<Characteristic> characteristicList = this.projectMgmtService.getCharacteristicByCategory(selectedCategory);
			for (Characteristic characteristic : characteristicList) {
				lbxNegativeCharacteristic.appendItem(characteristic.getName(), characteristic.getId().toString());
			}			
		}
	}
	
	public void onSelect$lbxNegativeCharacteristic() throws Exception{
		showNegativeCharacteristic();
	}
	
	private void showNegativeCharacteristic(){
		String selectedId = lbxNegativeCharacteristic.getSelectedItem().getValue().toString();
		if (selectedId != NOVALUE){	
			Characteristic characteristic = this.projectMgmtService.getCharacteristicById(Long.parseLong(selectedId));
			lblNegativeCharacteristicDesc.setValue(characteristic.getDescription());
			try{
				showContradictionPrinciple();
			}
			catch(Exception exception){}
		}else{
			lblNegativeCharacteristicDesc.setValue("");
		}	
	}	
	
	private void saveContradiction()throws Exception{
		
		if (this.project.getContradiction() == null){
			this.project.setContradiction(new ProjectContradiction());
		}
		String selectedId;
		Characteristic characteristic = getPositiveCharacteristic();
		this.project.getContradiction().setPositiveCharacteristic(characteristic);
		this.project.getContradiction().setPositiveDescription(txtPositiveCharacteristic.getValue());
		
		characteristic = getNegativeCharacteristic();
		this.project.getContradiction().setNegativeCharacteristic(characteristic);
		this.project.getContradiction().setNegativeDescription(txtNegativeCharacteristic.getValue());
		
		// parte de la validacion del proceso de colaboracion
		Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();
		saveChangesContradiction.setMessage("Has been saved the project contradictions");
		saveChangesContradiction.performProjectUpdate(project, desktop, collaborator, arg.get("win"));	
	}

	private Characteristic getNegativeCharacteristic() {
		String selectedId;
		Characteristic characteristic;
		selectedId = lbxNegativeCharacteristic.getSelectedItem().getValue().toString();
		characteristic	= null;
		if (selectedId != NOVALUE){	
			characteristic = this.projectMgmtService.getCharacteristicById(Long.parseLong(selectedId));			
		}
		return characteristic;
	}

	private Characteristic getPositiveCharacteristic() {
		String selectedId = lbxPositiveCharacteristic.getSelectedItem().getValue().toString();
		Characteristic characteristic	= null;
		if (selectedId != NOVALUE){	
			characteristic = this.projectMgmtService.getCharacteristicById(Long.parseLong(selectedId));			
		}
		return characteristic;
	}
	
	private void showContradictionPrinciple() throws Exception{
		vboxPrincipleList.getChildren().clear();		
		Characteristic positiveCharacteristic = getPositiveCharacteristic();
		Characteristic negativeCharacteristic = getNegativeCharacteristic();
		if ( positiveCharacteristic.getId().equals(negativeCharacteristic.getId())){
			Messagebox.show("Aplican principios de separacion", "Contradiccion fisica",
                    Messagebox.OK, Messagebox.INFORMATION);
		}else{
			Contradiction  contradiction=  contradictionMatrixService.findContradictionByCharacteristics(positiveCharacteristic, negativeCharacteristic);
			String principleName ="";
			if ( contradiction != null ){
				for (ContradictionPrinciple contradictionPrinciple : contradiction.getContradictionPrinciple()) {
					principleName = contradictionPrinciple.getSolutionPrinciple().getId()+" "+ contradictionPrinciple.getSolutionPrinciple().getPrincipleName();
					vboxPrincipleList.appendChild(new Label(principleName));
				}
			}
		}
	}
	
	public void onClick$saveChangesContradiction(Event event) throws Exception {
		saveContradiction();
	}

}
