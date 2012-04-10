package itsolver.service;

import java.util.List;

import itsolver.model.entity.AlgorithmQuestion;
import itsolver.model.entity.InventiveStandard;
import itsolver.model.entity.InventiveStandardClass;
import itsolver.model.entity.InventiveStandardGroup;

public interface AlgorithmStandardsService {

	public void persistAlgorithmQuestion(AlgorithmQuestion question);	
	public AlgorithmQuestion getAlgorithmQuestionById(String id);
	public void mergeAlgorithmQuestion(AlgorithmQuestion question);
	
	public void persistInventiveStandard(InventiveStandard standard);	
	public InventiveStandard getInventiveStandardById(String id);
	
	public void persistInventiveStandardClass(InventiveStandardClass standardClass);
	public List<InventiveStandardClass> getAllStandardsClassification();
	
	public void persistInventiveStandardGroup(InventiveStandardGroup standardGroup);
	public InventiveStandardGroup getInventiveStandardGroupById(String id);
	
	public Boolean setInventiveStandard2SuFieldProject(Long projId, String standardId)throws Exception;
}
