package itsolver.controller.contradiction;

import itsolver.controller.utils.GenericZkComposer;
import itsolver.model.entity.ContradictionProject;
import itsolver.model.entity.cbr.CBRCaseEntity;
import itsolver.service.cbr.CBREngine;

import java.util.ArrayList;
import java.util.Collection;

import jcolibri.cbrcore.CaseComponent;
import jcolibri.method.retrieve.RetrievalResult;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;


public class CBRContradictionController extends GenericZkComposer{
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -8437079406405021349L;
	
	private ContradictionProject project;
	private Component componentMe;
	
	public Grid grdCaseList;
	
	public Label lblTitle;
	public Button btnDoCBRSearch;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		componentMe = comp;
		this.project = (ContradictionProject)desktop.getAttribute("currentProject");
	}

	public void onClick$btnDoCBRSearch()throws Exception {		
		this.doCBRSearch();
	}
	
	private void doCBRSearch(){
		java.util.List<CBRCaseEntity> caseEntityList = new  ArrayList<CBRCaseEntity>();
		CBREngine cbrEngine = new CBREngine();
		Collection<RetrievalResult> resultado = cbrEngine.doCBRSearch(project);
		if (resultado.size() == 0){
			grdCaseList.setVisible(false);
			return;
		}		
		
		CaseComponent description;
		
		for (RetrievalResult retrievalResult : resultado) {
			description = retrievalResult.get_case().getDescription();
			double eval = retrievalResult.getEval();
			if ( eval > .5){
				CBRCaseEntity caseEntity = (CBRCaseEntity)description;
				caseEntity.setLanguage(this.getLanguage());
				caseEntity.setSimilarityEvaluation(eval);
				caseEntityList.add(caseEntity);
			}
		}
		grdCaseList.setVisible(true);
		//prepare the AnnotateDataBinder
		AnnotateDataBinder binder = new AnnotateDataBinder(componentMe);
		binder.bindBean("caseList", caseEntityList);

		//initialize UI components
		binder.loadAll();


		
	}
}
