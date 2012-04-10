package itsolver.model.dao;

import itsolver.model.entity.Characteristic;
import itsolver.model.entity.Contradiction;

public interface ContradictionDAO extends GenericDAO<Contradiction, Long>{
	public Contradiction getContradiction(Characteristic positive,Characteristic negative);
}
