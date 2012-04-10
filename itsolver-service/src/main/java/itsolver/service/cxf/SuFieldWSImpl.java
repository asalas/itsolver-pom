package itsolver.service.cxf;

import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.AlgorithmQuestion;
import itsolver.model.entity.InventiveStandard;
import itsolver.model.entity.InventiveStandardClass;
import itsolver.model.entity.TreeField;
import itsolver.service.AlgorithmStandardsService;
import itsolver.service.SuFieldService;

import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface="itsolver.service.cxf.SuFieldWS")
public class SuFieldWSImpl implements SuFieldWS {

	private SuFieldService suFieldService;
	private AlgorithmStandardsService algorithmStandardsService;
	
	
	public Boolean updateSuFieldGraph(Long projectId, String suFieldGraph) {
		
		suFieldService = ServiceLocator.getSuFieldService();
		
		Boolean resultUpdate = false;		
		
		try {			
			resultUpdate = suFieldService.updateSuFieldGraphByProjId(projectId, suFieldGraph);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultUpdate;
	}
	
	
	public String getSuFieldGraph(Long projectId) {
		
		suFieldService = ServiceLocator.getSuFieldService();
		
		String suFieldGraph = null;
		
		try {
			suFieldGraph = suFieldService.getSuFieldGraphByProjId(projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return suFieldGraph;
	}
	
	
	public TreeField getTreeField() {
		suFieldService = ServiceLocator.getSuFieldService();
		TreeField treeField = suFieldService.getTreeField();
		return treeField;
	}
	
	
	public AlgorithmQuestion getAlgorithmQuestion(String questionId) {
		algorithmStandardsService = ServiceLocator.getAlgorithmStandardsService();
		AlgorithmQuestion question = algorithmStandardsService.getAlgorithmQuestionById(questionId);		
		return question;
	}
	
	
	public List<InventiveStandardClass> getStandardsClassification() {
		algorithmStandardsService = ServiceLocator.getAlgorithmStandardsService();
		List<InventiveStandardClass> allStandardClass = algorithmStandardsService.getAllStandardsClassification();
		return allStandardClass;
	}
	
	
	public InventiveStandard getInventiveStandardById(String id) {
		algorithmStandardsService = ServiceLocator.getAlgorithmStandardsService();
		
		InventiveStandard inventiveStandard = null;
		
		try {
			inventiveStandard = algorithmStandardsService.getInventiveStandardById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return inventiveStandard;
	}

	
	public Boolean setStandardToSuFieldProject(Long projId, String standardId) {
		Boolean resultSetStandard = false;
		
		try {
			resultSetStandard = algorithmStandardsService.setInventiveStandard2SuFieldProject(projId, standardId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultSetStandard;
	}
	
}
