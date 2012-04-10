package itsolver.service;

import itsolver.model.dao.AlgorithmQuestionDAO;
import itsolver.model.dao.InventiveStandardClassDAO;
import itsolver.model.dao.InventiveStandardDAO;
import itsolver.model.dao.InventiveStandardGroupDAO;
import itsolver.model.dao.ProjectDAO;
import itsolver.model.entity.AlgorithmQuestion;
import itsolver.model.entity.InventiveStandard;
import itsolver.model.entity.InventiveStandardClass;
import itsolver.model.entity.InventiveStandardGroup;
import itsolver.model.entity.SuField;

import java.util.List;

public class AlgorithmStandardsServiceImpl implements AlgorithmStandardsService {

	private AlgorithmQuestionDAO algorithmQuestionDAO;	
	private InventiveStandardDAO inventiveStandardDAO;
	private InventiveStandardClassDAO inventiveStandardClassDAO;
	private InventiveStandardGroupDAO inventiveStandardGroupDAO;
	private ProjectDAO projectDAO;

	
	public void persistAlgorithmQuestion(AlgorithmQuestion question) {
		algorithmQuestionDAO.persist(question);		
	}
	
	
	public void persistInventiveStandard(InventiveStandard standard) {
		inventiveStandardDAO.persist(standard);		
	}
	
	
	public AlgorithmQuestion getAlgorithmQuestionById(String id) {
		return algorithmQuestionDAO.findById(id);		
	}
	
	
	public void persistInventiveStandardClass(InventiveStandardClass standardClass) {
		inventiveStandardClassDAO.persist(standardClass);		
	}
	
	
	public void persistInventiveStandardGroup(InventiveStandardGroup standardGroup) {
		inventiveStandardGroupDAO.persist(standardGroup);	
	}
	
	
	public InventiveStandardGroup getInventiveStandardGroupById(String id) {
		return inventiveStandardGroupDAO.findById(id);		
	}
	
	
	public List<InventiveStandardClass> getAllStandardsClassification() {
		return inventiveStandardClassDAO.findAll();
	}
	
	
	public InventiveStandard getInventiveStandardById(String id) {
		return inventiveStandardDAO.findById(id);		
	}
	
	
	public Boolean setInventiveStandard2SuFieldProject(Long projId, String standardId)
			throws Exception {
		
		SuField suFieldProject = (SuField)projectDAO.findById(projId);		
		InventiveStandard standard = inventiveStandardDAO.findById(standardId);
		
		if(suFieldProject!=null && standard!=null) {
			suFieldProject.setInventiveStandard(standard);
			suFieldProject.getSolutionSuField().setInventiveStandard(standard);
			projectDAO.merge(suFieldProject);
			
			return true;
		}
		
		return false;
	}
	
	
	public void mergeAlgorithmQuestion(AlgorithmQuestion question) {
		algorithmQuestionDAO.merge(question);		
	}
	
	// getters y setters
	public AlgorithmQuestionDAO getAlgorithmQuestionDAO() {
		return algorithmQuestionDAO;
	}	
	
	public void setAlgorithmQuestionDAO(AlgorithmQuestionDAO algorithmQuestionDAO) {
		this.algorithmQuestionDAO = algorithmQuestionDAO;
	}

	public InventiveStandardDAO getInventiveStandardDAO() {
		return inventiveStandardDAO;
	}

	public void setInventiveStandardDAO(InventiveStandardDAO inventiveStandardDAO) {
		this.inventiveStandardDAO = inventiveStandardDAO;
	}

	public InventiveStandardClassDAO getInventiveStandardClassDAO() {
		return inventiveStandardClassDAO;
	}

	public void setInventiveStandardClassDAO(
			InventiveStandardClassDAO inventiveStandardClassDAO) {
		this.inventiveStandardClassDAO = inventiveStandardClassDAO;
	}

	public InventiveStandardGroupDAO getInventiveStandardGroupDAO() {
		return inventiveStandardGroupDAO;
	}

	public void setInventiveStandardGroupDAO(
			InventiveStandardGroupDAO inventiveStandardGroupDAO) {
		this.inventiveStandardGroupDAO = inventiveStandardGroupDAO;
	}

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
}
