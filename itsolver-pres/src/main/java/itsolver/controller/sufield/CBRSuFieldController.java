package itsolver.controller.sufield;

import java.util.ArrayList;
import java.util.Collection;

import jcolibri.cbrcore.CaseComponent;
import jcolibri.method.retrieve.RetrievalResult;

import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Column;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;

import itsolver.controller.utils.GenericZkComposer;
import itsolver.model.entity.Project;
import itsolver.model.entity.SuField;
import itsolver.model.entity.cbr.CBRCaseEntity;
import itsolver.service.cbr.CBREngine;

public class CBRSuFieldController extends GenericZkComposer {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -7938262954600231093L;
	
	private SuField project;
	private Component componentMe;
	
	public Grid grdCaseList;
	
	public Label lblTitle;
	public Button btnDoCBRSearch;
	
	public Column colProjName;
	public Column colProjDesc;
	public Column colSimilarity;
	public Column colSatisfactionPercent;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		componentMe = comp;
		this.project = (SuField)desktop.getAttribute("currentProject");
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
		
		CaseComponent cbrCase;
		
		for (RetrievalResult retrievalResult : resultado) {
			cbrCase = retrievalResult.get_case().getDescription();		
			
			double eval = retrievalResult.getEval();
			if (eval > 0.1) {				
				CBRCaseEntity caseEntity = (CBRCaseEntity)cbrCase;
				
				Project projectInCBRCase = caseEntity.getProject();
				
				if(!this.project.equals(projectInCBRCase)) {
					caseEntity.setLanguage(this.getLanguage());
					caseEntity.setSimilarityEvaluation(eval);
					caseEntityList.add(caseEntity);
				}				
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
