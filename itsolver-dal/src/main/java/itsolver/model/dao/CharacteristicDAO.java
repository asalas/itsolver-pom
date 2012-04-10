package itsolver.model.dao;

import java.util.List;

import itsolver.model.entity.Characteristic;
public interface CharacteristicDAO extends GenericDAO<Characteristic, Long>{
	public List<Characteristic> getCharacteristicByCategory(String category);
}
