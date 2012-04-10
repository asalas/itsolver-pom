package itsolver.utils;

import itsolver.model.entity.SolutionPrinciple;

import org.zkoss.zul.Popup;
import org.zkoss.zul.Radio;

@SuppressWarnings("serial")
public class RadioPrinciple extends Radio {
	private SolutionPrinciple solutionPrinciple;
	//public RadioPrinciple(SolutionPrinciple _solutionPrinciple, Popup popupMessage) {
	public RadioPrinciple(SolutionPrinciple _solutionPrinciple) {
		this.solutionPrinciple = _solutionPrinciple;
		//popupMessage.setAttribute("html", "<b>Prueba con el tool tip test</b><br/> sin format");
		//setTooltip("mesagePoppup");
		//setPopup(popupMessage);
		//setTooltiptext("<b>Prueba con el tool tip test</b><br/> sin format");		
		this.init();
	}
	
	private void init(){
		this.setLabel(this.solutionPrinciple.getPrincipleName());				
	}
	
	public SolutionPrinciple getSolutionPrinciple(){
		return this.solutionPrinciple;
	}

}
