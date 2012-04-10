package itsolver.service;

import itsolver.model.entity.Characteristic;
import itsolver.model.entity.Contradiction;

public interface ContradictionMatrixService {
	public Contradiction findContradictionByCharacteristics(Characteristic positive,Characteristic negative);
}
