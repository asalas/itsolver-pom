package itsolver.utils;

import itsolver.model.entity.Resource;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;

@SuppressWarnings("serial")
public class ResourceRow extends Row {
	
	private Checkbox cbxIsSelectedResource;
	private Resource resource;
	private Textbox txtResourceValue;
	public ResourceRow(Resource _resource) {
		this.resource = _resource;		
		init();		
	}
	
	private void init(){
		Hbox hbox = new Hbox();
		this.getChildren().add(hbox);
		cbxIsSelectedResource = new Checkbox("");
		hbox.getChildren().add(cbxIsSelectedResource);
		hbox.getChildren().add(new Label(this.resource.getResourceName()));
		txtResourceValue = new Textbox();
		txtResourceValue.setDisabled(true);
		cbxIsSelectedResource.addEventListener(Events.ON_CHECK, new EventListener() {			
			public void onEvent(Event arg0) throws Exception {
				if (cbxIsSelectedResource.isChecked()){
					txtResourceValue.setDisabled(false);
				}else{
					txtResourceValue.setDisabled(true);
				}
			}
		});
		txtResourceValue.setWidth("100px");
		this.getChildren().add(txtResourceValue);
		this.getChildren().add(new Label(resource.getId().toString()));
	}
	
	public Resource getResource(){
		return this.resource;		
	}
	
	public String getSelectedValue(){
		return txtResourceValue.getValue();		
	}
	
	public Component getComponentValue(){
		return this.txtResourceValue;
	}
	
	public boolean isSelectedResource(){
		return cbxIsSelectedResource.isChecked();
	}
	
	public void selectedResource(){
		cbxIsSelectedResource.setChecked(true);
		txtResourceValue.setDisabled(false);
	}
	
	public void setSelectedValue(String value){
		txtResourceValue.setValue(value);
	}
	
	
}
