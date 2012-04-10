package itsolver.model.dao;

import itsolver.model.entity.cbr.CBRCaseEntity;
import itsolver.utils.CBRCaseType;

import java.util.List;

public interface CbrDAO extends GenericDAO<CBRCaseEntity, Long> {
	
    public List<CBRCaseEntity> findAllCBRCaseByType(CBRCaseType caseType);
}
