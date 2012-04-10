package itsolver.service;

import itsolver.model.dao.ContradictionDAO;
import itsolver.model.entity.Characteristic;
import itsolver.model.entity.Contradiction;

public class ContradictionMatrixServiceImpl implements ContradictionMatrixService{
	
	private ContradictionDAO contradictionDAO;
	
	
	public Contradiction findContradictionByCharacteristics(Characteristic positive,
			Characteristic negative) {
		return contradictionDAO.getContradiction(positive, negative);
	}

	public ContradictionDAO getContradictionDAO() {
		return contradictionDAO;
	}

	public void setContradictionDAO(ContradictionDAO contradictionDAO) {
		this.contradictionDAO = contradictionDAO;
	}
}
