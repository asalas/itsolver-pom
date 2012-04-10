package itsolver.service.cbr;

import itsolver.model.dao.CbrDAO;
import itsolver.model.entity.ContradictionProject;
import itsolver.model.entity.Project;
import itsolver.model.entity.ProjectResource;
import itsolver.model.entity.Restriction;
import itsolver.model.entity.SelectedResource;
import itsolver.model.entity.SuField;
import itsolver.model.entity.cbr.CBRCaseEntity;
import itsolver.model.entity.cbr.CBRConstraint;
import itsolver.model.entity.cbr.CBRContradiction;
import itsolver.model.entity.cbr.CBRResource;
import itsolver.model.entity.cbr.CBRSuField;
import itsolver.utils.CBRCaseType;

import java.util.Collection;
import java.util.List;

import jcolibri.cbrcore.CBRCase;

public class CbrServiceImpl implements CbrService {
	CbrDAO cbrDAO;
	public void createCbrEntity(CBRCaseEntity caseEntity){
		this.cbrDAO.persist(caseEntity);
	}
		
	
	public Collection<CBRCase> getCBRCaseCollection(CBRCaseType caseType) {
		Collection<CBRCase> casesCollection = new java.util.ArrayList<CBRCase>();
		List<CBRCaseEntity> caseList = this.cbrDAO.findAllCBRCaseByType(caseType);
		CBRCase cbrCase= null;
		for (CBRCaseEntity cbrCaseEntity : caseList) {
			cbrCase = new CBRCase();
			cbrCase.setDescription(cbrCaseEntity);
			casesCollection.add(cbrCase);
		}
		return casesCollection;
	}
	
	public CBRCaseEntity getCBRCaseEntity(Project project) {
		CBRCaseEntity queryDesc = new CBRCaseEntity();			
		
		CBRResource resources = new CBRResource();
		for (ProjectResource projectResource : project.getProjectResourceList()) {
			for (SelectedResource selectedResource : projectResource.getSelectedResourceList()) {
				resources.getResourceIdList().add(selectedResource.getResource().getId());
			}
		}		
		queryDesc.setCbrResource(resources);
		
		CBRConstraint constraints = new CBRConstraint();
		for (Restriction restriction : project.getRestrictionList()) {
			constraints.getConstrainIdList().add(restriction.getCharacteristic().getId());
		}		
		
		queryDesc.setCbrConstraint(constraints);
				
		//TODO: aqui se valida el tipo de proyecto si es matriz de contradicciones o es su-field para asignar la propiedad que corresponde		
		if(project instanceof ContradictionProject){
			setContradiction(project, queryDesc);
		}else if(project instanceof SuField) {
			setSuField(project, queryDesc);
		}
		
		queryDesc.setProblemNature(project.getProblemDescription().getProblemNature());
		return queryDesc;
	}

	private void setSuField(Project project, CBRCaseEntity queryDesc) {
		SuField suField = (SuField)project;
		CBRSuField cbrSuField = new CBRSuField();
		cbrSuField.setInventiveStandard(suField.getInventiveStandard());
		cbrSuField.setSuFieldGraph(suField.getSufieldGraph());
		queryDesc.setCbrSuField(cbrSuField);
		queryDesc.setCbrCaseType(CBRCaseType.SU_FIELD_CBR_TYPE);
	}

	private void setContradiction(Project project, CBRCaseEntity queryDesc) {
		ContradictionProject contradictionMatrix = (ContradictionProject) project;
		CBRContradiction cbrContradiction = new CBRContradiction();			
		cbrContradiction.setPositiveCharacteristicId(contradictionMatrix
				.getContradiction().getPositiveCharacteristic().getId());
		cbrContradiction.setNegativeCharacteristicId(contradictionMatrix
				.getContradiction().getNegativeCharacteristic().getId());		
		queryDesc.setCbrContradiction(cbrContradiction);
		queryDesc.setCbrCaseType(CBRCaseType.CONTRADICTION_CBR_TYPE);
	}
	
	public void CBRContradictionSearch(ContradictionProject contradictionMatrix){
		
	}
	
	public CbrDAO getCbrDAO() {
		return cbrDAO;
	}
	public void setCbrDAO(CbrDAO cbrDAO) {
		this.cbrDAO = cbrDAO;
	}
}
