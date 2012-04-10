package itsolver.service;

import itsolver.model.dao.TreeFieldDAO;
import itsolver.model.entity.TreeField;


public class TreeFieldServiceImpl implements TreeFieldService {

	private TreeFieldDAO treeFieldDAO;	
	
	
	public TreeField getTreeField() {		
		return treeFieldDAO.getTreeField();
	}
	
	
	public void createTreeField(TreeField treeField) {
		treeFieldDAO.persist(treeField);		
	}

	public void setTreeFieldDAO(TreeFieldDAO treeFieldDAO) {
		this.treeFieldDAO = treeFieldDAO;
	}

	public TreeFieldDAO getTreeFieldDAO() {
		return treeFieldDAO;
	}

}
