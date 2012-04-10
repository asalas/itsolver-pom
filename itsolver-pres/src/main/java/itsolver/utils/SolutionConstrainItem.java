package itsolver.utils;

import itsolver.model.entity.Restriction;

import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Vbox;

public class SolutionConstrainItem extends Row {
	
	private Checkbox cbxIsSelectedResource;
	private Restriction restriction;	
	public SolutionConstrainItem(Restriction _restriction) {
		this.restriction = _restriction;		
		init();		
	}
	
	@SuppressWarnings("unchecked")
	private void init(){
		Vbox vbox = new Vbox();
		cbxIsSelectedResource = new Checkbox("");
		this.getChildren().add(cbxIsSelectedResource);
		vbox.getChildren().add(new Label(this.restriction.getFormattedMessage()));
		vbox.getChildren().add(new Label(restriction.getDescription()));
		this.getChildren().add(vbox);
		//hbox.getChildren().add(vbox);
	}
	
	public Restriction getRestriction(){
		return this.restriction;		
	}	
	
	public boolean isSelectedConstrain(){
		return cbxIsSelectedResource.isChecked();
	}
	
	public void selectConstrain(){
		cbxIsSelectedResource.setChecked(true);	
	}	
}
