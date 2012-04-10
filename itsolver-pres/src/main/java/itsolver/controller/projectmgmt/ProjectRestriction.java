package itsolver.controller.projectmgmt;

import itsolver.components.SaveChanges2Project;
import itsolver.controller.collaborationmgmt.MutualExclusionControl;
import itsolver.controller.collaborationmgmt.Collaborator;
import itsolver.controller.utils.GenericZkComposer;
import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.Characteristic;
import itsolver.model.entity.Project;
import itsolver.model.entity.Restriction;
import itsolver.service.ProjectMgmtService;
import itsolver.utils.RestrictionRow;

import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

@SuppressWarnings("serial")
public class ProjectRestriction extends GenericZkComposer{

	private static final String NOVALUE = "NOVALUE";
	public Listbox lbxCategory;
	public Listbox lbxCharacteritic;
	public Listbox lbxCondition;
	public Hbox parameterForm;
	public Vbox valuesForm;
	public Label lblInitialValue;
	public Label lblFinalValue;
	public Label lblParameterRequiredError;
	public Radiogroup parameterType;
	public Textbox txtFinalValue;
	public Textbox txtInitialValue;
	public Textbox txtDescription;
	
	public SaveChanges2Project saveChangesConstraints;
	
	private Project project;
	public Rows rowsRestrictionsList;
	private ProjectMgmtService projectMgmtService;
	private boolean isSingleValue = true;
	
	List<Characteristic> characteristicList = null;
	
	public Label lblConstraints;
	public Label lblConstraintsAdded;
	public Label lblConstraintCategory;
	
	public Label lblConstParameter;
	
	public Listitem litemCategorySelect;
	public Listitem litemCategoryPhysical;
	public Listitem litemCategoryPerfomance;
	public Listitem litemCategoryEfficiency;
	public Listitem litemCategoryCapacity;
	public Listitem litemCategoryManufactoring;
	public Listitem litemCategoryMesuring;
	
	public Listitem litemConstCharacteristic;
	
	public Radio rbnSingleValue;
	public Radio rbnTwoValues;
	
	public Listitem litemNoCondition;
	public Listitem litemGreaterThan;
	public Listitem litemLessThan;
	public Listitem litemEquals;
	public Listitem litemDifferent;
	
	public Label lblValueType;
	public Label lblDescRelated;
	
	public Button btnAddRestriction;
	
	public Label lblConstCondition;
	
	private String language;
	private String msgSave;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {		
		super.doAfterCompose(comp);
		saveChangesConstraints.init((Window)arg.get("win"));
		this.project = (Project)desktop.getAttribute("currentProject");		
		this.projectMgmtService		= ServiceLocator.getProjectMgmtService();
		this.language = this.getLanguage();
		initRestriction();
		
		if(language.equals("EN")) {
			msgSave ="Has been saved the project constraints"; 
			saveChangesConstraints.setButtonLabel("Save");
			saveChangesConstraints.setCancelButtonLabel("Cancel");
		}else if(language.equals("ES")) {
			msgSave = "Se han guardado las restricciones del proyecto";
			saveChangesConstraints.setButtonLabel("Guardar");
			saveChangesConstraints.setCancelButtonLabel("Cancelar");
		}else if(language.equals("FR")) {
			msgSave = "finis";
			saveChangesConstraints.setButtonLabel("Sauver");
			saveChangesConstraints.setCancelButtonLabel("Annuler");
		}
	}
	
	public void onSelect$lbxCategory()throws Exception {		
		updateLbxCharacteritic();
	}
	
	public void onClick$btnAddRestriction()throws Exception{
		addResriction();
	}
	
	private void initRestriction(){
		List<Restriction> restrictionList = this.project.getRestrictionList();
		RestrictionRow restrictionRow = null;
		for (Restriction restriction : restrictionList) {
			restrictionRow = new RestrictionRow(restriction, language);
			rowsRestrictionsList.appendChild(restrictionRow);
		}		
	}
	
	private void addResriction(){
		String selectedCharacteristic = getSelectedCharacteristic();
		if (selectedCharacteristic != null && selectedCharacteristic != NOVALUE){
			Characteristic characteristic = this.projectMgmtService.getCharacteristicById(Long.parseLong(selectedCharacteristic));
			Restriction restriction = new Restriction();
			restriction.setCharacteristic(characteristic);
			restriction.setDescription(getDescription());
			restriction.setSingleValue(isSingleValue);
			restriction.setInitialValue(getInitialValue());
			restriction.setCondition(getCondition());
			if ( !isSingleValue  ){
				restriction.setFinalValue(getFinalValue());
			}
			RestrictionRow restrictionRow = new RestrictionRow(restriction, language);
			rowsRestrictionsList.appendChild(restrictionRow);
			clearRestrictionForm();
		}else{
			lblParameterRequiredError.setVisible(true);
		}
	}
	
	private void clearRestrictionForm(){
		this.txtDescription.setValue("");
		this.txtInitialValue.setValue("");
		this.txtFinalValue.setValue("");
		this.lbxCharacteritic.setSelectedIndex(0);
		this.lbxCondition.setSelectedIndex(0);
		showSingleValue();
	}

	private String getSelectedCharacteristic() throws WrongValueException{
		if ( lblParameterRequiredError.isVisible() ){
			lblParameterRequiredError.setVisible(false);
		}
		if(lbxCharacteritic.getSelectedItem() == null) {
			lblParameterRequiredError.setVisible(true);
			return null;
		}
		return lbxCharacteritic.getSelectedItem().getValue().toString();
	}

	private String getDescription() {
		return txtDescription.getValue().trim();
	}
	
	private String getCondition() {
		return lbxCondition.getSelectedItem().getValue().toString();
	}
	
	private String getInitialValue() {
		return txtInitialValue.getValue().trim();
	}
	
	private String getFinalValue() {
		return txtFinalValue.getValue().trim();
	}
	
	private void saveResriction()throws Exception{
		RestrictionRow restrictionRow;
		List<Restriction> restrictionList = this.project.getRestrictionList();
		restrictionList.clear();
		for (Iterator iterator = rowsRestrictionsList.getChildren().iterator(); iterator.hasNext();) {
			restrictionRow = (RestrictionRow) iterator.next();
			restrictionList.add(restrictionRow.getRestriction());
		}		
		this.project.setRestrictionList(restrictionList);
		
		// parte de la validacion del proceso de exclusion mutua
		Collaborator collaborator = MutualExclusionControl.getCurrentCollaborator();		
		
		saveChangesConstraints.setMessage(msgSave);
		saveChangesConstraints.performProjectUpdate(project, desktop, collaborator, arg.get("win"));		
	}
	
	private void updateLbxCharacteritic(){
		
		String selectedCategory = lbxCategory.getSelectedItem().getValue().toString();
		if (selectedCategory != NOVALUE){			
			lbxCharacteritic.getChildren().clear();
			characteristicList = this.projectMgmtService.getCharacteristicByCategory(selectedCategory);
			if ( characteristicList.size() > 0 ){
				lbxCharacteritic.appendItem("--Select--", NOVALUE);
			}
			for (Characteristic characteristic : characteristicList) {
				lbxCharacteritic.appendItem(characteristic.getName(), characteristic.getId().toString());
			}
			parameterForm.setVisible(true);
			valuesForm.setVisible(true);
			showSingleValue();
		}else{
			parameterForm.setVisible(false);
			valuesForm.setVisible(false);
		}
	}
		
	public void onSelect$lbxCharacteritic()throws Exception {
		if ( lblParameterRequiredError.isVisible() ){
			lblParameterRequiredError.setVisible(false);
		}
	}
	
	public void onClick$rbnSingleValue() throws Exception{		
		showSingleValue();		
	}
	
	private void showSingleValue(){
		isSingleValue = true;		
		
		String language = this.getLanguage();
		if(language.equals("ES")) {
			lblInitialValue.setValue("Valor");
		}else if(language.equals("EN")) {
			lblInitialValue.setValue("Value");
		}else if(language.equals("FR")) {
			lblInitialValue.setValue("Valeur");
		}
		
		lblFinalValue.setVisible(false);
		txtFinalValue.setVisible(false);
		parameterType.setSelectedIndex(0);
	}
	
	public void onClick$rbnTwoValues() throws Exception{		
		showTowValues();
	}
	
	private void showTowValues(){
		isSingleValue = false;
		
		String language = this.getLanguage();
		if(language.equals("ES")) {
			lblInitialValue.setValue("Valor inicial");
		}else if(language.equals("EN")) {
			lblInitialValue.setValue("Initial value");
		}else if(language.equals("FR")) {
			lblInitialValue.setValue("valeur initiale");
		}
		
		lblFinalValue.setVisible(true);
		txtFinalValue.setVisible(true);
		parameterType.setSelectedIndex(1);
	}

	public void onClick$saveChangesConstraints(Event event) throws Exception {
		saveResriction();
	}
	
}
