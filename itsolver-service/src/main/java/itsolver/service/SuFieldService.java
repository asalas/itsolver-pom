package itsolver.service;

import itsolver.model.entity.SuField;
import itsolver.model.entity.TreeField;

public interface SuFieldService {

	public TreeField getTreeField();
	
	public void createTreeField(TreeField treeField);
	
	public Boolean updateSuFieldGraphByProjId(Long id, String modelGraphML) throws Exception;
	
	public String getSuFieldGraphByProjId(Long id) throws Exception;
	
	public void mergeSuField(SuField suFieldProj);	
}
