package itsolver.utils;

import itsolver.model.entity.Restriction;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;

@SuppressWarnings("serial")
public class RestrictionRow extends Row  {
	public Button btnRemove;
	private Restriction restriction;
	private String language;
	
	public RestrictionRow(Restriction _restriction, String language) {
		this.restriction = _restriction;
		this.language = language;
		init();
	}
	
	@SuppressWarnings("unchecked")
	public void init(){
		Hbox hbox = new Hbox();
		this.getChildren().add(hbox);		
		hbox.getChildren().add(new Label(this.restriction.getFormattedMessage()));
		
		if(language.equals("EN")) {
			btnRemove = new Button("Remove");
		}else if(language.equals("ES")) {
			btnRemove = new Button("Eliminar");
		}else if(language.equals("FR")) {
			btnRemove = new Button("Supprimer");
		}
		
		this.getChildren().add(btnRemove);
		
		btnRemove.addEventListener(Events.ON_CLICK, new EventListener() {			
			public void onEvent(Event arg0) throws Exception {
				removeMe();
			}
		});
	}
	
	private void removeMe(){
		this.getParent().removeChild(this);
	}

	public Restriction getRestriction() {
		return restriction;
	}

	public void setRestriction(Restriction restriction) {
		this.restriction = restriction;
	}	
}
