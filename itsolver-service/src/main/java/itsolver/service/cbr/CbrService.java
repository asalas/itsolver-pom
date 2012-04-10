package itsolver.service.cbr;

import itsolver.model.entity.ContradictionProject;
import itsolver.model.entity.Project;
import itsolver.model.entity.cbr.CBRCaseEntity;
import itsolver.utils.CBRCaseType;

import java.util.Collection;

import jcolibri.cbrcore.CBRCase;

public interface CbrService {
	public void createCbrEntity(CBRCaseEntity caseEntity);
	
	public Collection<CBRCase> getCBRCaseCollection(CBRCaseType caseType);
	
	public void CBRContradictionSearch(ContradictionProject contradictionMatrix);
	
	public CBRCaseEntity getCBRCaseEntity(Project project);
}
