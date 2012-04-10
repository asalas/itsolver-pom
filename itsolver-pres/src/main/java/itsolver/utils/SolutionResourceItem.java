package itsolver.utils;

import itsolver.model.entity.Resource;
import itsolver.model.entity.SelectedResource;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

@SuppressWarnings("serial")
public class SolutionResourceItem extends Hbox {
	private Checkbox cbxIsSelectedResource;
	private SelectedResource selectedResource;	
	public SolutionResourceItem(SelectedResource _selectedResource) {
		this.selectedResource = _selectedResource;		
		init();		
	}
	
	private void init(){		
		cbxIsSelectedResource = new Checkbox("");
		this.getChildren().add(cbxIsSelectedResource);
		this.getChildren().add(new Label(this.selectedResource.getResource().getResourceName()));
		//this.getChildren().add(new Label(resource.getId().toString()));
	}
	
	public SelectedResource getSelectedResource(){
		return this.selectedResource;		
	}	
	
	public boolean isSelectedResource(){
		return cbxIsSelectedResource.isChecked();
	}
	
	public void selectResource(){
		cbxIsSelectedResource.setChecked(true);		
	}	
	
}
