package dbcreate.data;

import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.cbr.CBRCaseEntity;
import itsolver.model.entity.cbr.CBRConstraint;
import itsolver.model.entity.cbr.CBRContradiction;
import itsolver.model.entity.cbr.CBRResource;
import itsolver.service.cbr.CbrService;
import itsolver.utils.CBRCaseType;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;


public class CBRCaseBaseInit {
	private CbrService cbrService;
	// log
	protected static Logger log = Logger.getLogger("");
	static {
		log.setLevel(Level.INFO);
	}
	
	@Before
	public void setUp() throws Exception {
		cbrService = ServiceLocator.getCbrService();
	}
	
	//@Test
	public void retrievingConstrainCases(){
		/*List<CBRCaseContradiction> caseList = cbrService.getContradictionCaseList();
		for (CBRCaseContradiction cbrCaseEntity : caseList) {
			System.out.println("Caso " + cbrCaseEntity.getCaseId());
			System.out.println("Contradiccion " + cbrCaseEntity.getCbrContradiction().getPositiveCharacteristicId());
		}*/
	}
	
	@Test
	public void persistTestCases() {	
		//CASO 1
		CBRCaseEntity caseEntity = new CBRCaseEntity();
		caseEntity.setCbrCaseType(CBRCaseType.CONTRADICTION_CBR_TYPE);
		
		caseEntity.setProblemNature("ELIMINATE_HARMFUL_FUNCTION");
		
		CBRResource resources = new CBRResource();
		resources.getResourceIdList().add(1L);
		resources.getResourceIdList().add(4L);
		resources.getResourceIdList().add(5L);
		caseEntity.setCbrResource(resources);
		
		CBRConstraint constrains = new CBRConstraint();
		constrains.getConstrainIdList().add(4L);
		constrains.getConstrainIdList().add(5L);
		constrains.getConstrainIdList().add(7L);
		constrains.getConstrainIdList().add(9L);
		constrains.getConstrainIdList().add(10L);
		caseEntity.setCbrConstraint(constrains);
		
		CBRContradiction cbrContradiction = new CBRContradiction();
		cbrContradiction.setNegativeCharacteristicId(3L);
		cbrContradiction.setPositiveCharacteristicId(5L);
		caseEntity.setCbrContradiction(cbrContradiction);
		
		cbrService.createCbrEntity(caseEntity);
		
		//CASO 2
		caseEntity = new CBRCaseEntity();
		caseEntity.setCbrCaseType(CBRCaseType.CONTRADICTION_CBR_TYPE);
		
		caseEntity.setProblemNature("ELIMINATE_HARMFUL_FUNCTION");
		
		resources = new CBRResource();
		resources.getResourceIdList().add(1L);
		resources.getResourceIdList().add(4L);
		resources.getResourceIdList().add(3L);
		caseEntity.setCbrResource(resources);
		
		constrains = new CBRConstraint();
		constrains.getConstrainIdList().add(4L);
		constrains.getConstrainIdList().add(5L);
		constrains.getConstrainIdList().add(12L);
		constrains.getConstrainIdList().add(14L);
		constrains.getConstrainIdList().add(16L);
		caseEntity.setCbrConstraint(constrains);
		
		cbrContradiction = new CBRContradiction();
		cbrContradiction.setNegativeCharacteristicId(3L);
		cbrContradiction.setPositiveCharacteristicId(22L);
		caseEntity.setCbrContradiction(cbrContradiction);
		
		cbrService.createCbrEntity(caseEntity);
		
		//CASO 3
		caseEntity = new CBRCaseEntity();
		caseEntity.setCbrCaseType(CBRCaseType.CONTRADICTION_CBR_TYPE);
		caseEntity.setProblemNature("TECHNOLOGY_FORESIGHT");
		
		resources = new CBRResource();
		resources.getResourceIdList().add(1L);
		resources.getResourceIdList().add(4L);
		caseEntity.setCbrResource(resources);
		
		constrains = new CBRConstraint();
		constrains.getConstrainIdList().add(4L);
		constrains.getConstrainIdList().add(5L);
		caseEntity.setCbrConstraint(constrains);
		
		cbrContradiction = new CBRContradiction();
		cbrContradiction.setNegativeCharacteristicId(3L);
		cbrContradiction.setPositiveCharacteristicId(5L);
		caseEntity.setCbrContradiction(cbrContradiction);
		
		cbrService.createCbrEntity(caseEntity);
		
		//CASO 4
		caseEntity = new CBRCaseEntity();
		caseEntity.setCbrCaseType(CBRCaseType.CONTRADICTION_CBR_TYPE);
		caseEntity.setProblemNature("COST_REDUCTION");
		
		resources = new CBRResource();
		resources.getResourceIdList().add(1L);
		resources.getResourceIdList().add(3L);
		resources.getResourceIdList().add(4L);
		resources.getResourceIdList().add(5L);
		caseEntity.setCbrResource(resources);
		
		constrains = new CBRConstraint();
		constrains.getConstrainIdList().add(9L);
		constrains.getConstrainIdList().add(10L);
		constrains.getConstrainIdList().add(11L);
		constrains.getConstrainIdList().add(12L);
		constrains.getConstrainIdList().add(13L);
		constrains.getConstrainIdList().add(14L);
		constrains.getConstrainIdList().add(16L);
		caseEntity.setCbrConstraint(constrains);
		
		cbrContradiction = new CBRContradiction();
		cbrContradiction.setNegativeCharacteristicId(2L);
		cbrContradiction.setPositiveCharacteristicId(9L);
		caseEntity.setCbrContradiction(cbrContradiction);
		
		cbrService.createCbrEntity(caseEntity);
		
		//CASO 5
		caseEntity = new CBRCaseEntity();
		caseEntity.setCbrCaseType(CBRCaseType.CONTRADICTION_CBR_TYPE);
		caseEntity.setProblemNature("COST_REDUCTION");
		
		resources = new CBRResource();		
		resources.getResourceIdList().add(5L);
		caseEntity.setCbrResource(resources);
		
		constrains = new CBRConstraint();
		constrains.getConstrainIdList().add(9L);
		constrains.getConstrainIdList().add(10L);
		constrains.getConstrainIdList().add(11L);
		constrains.getConstrainIdList().add(12L);
		constrains.getConstrainIdList().add(13L);
		constrains.getConstrainIdList().add(14L);
		constrains.getConstrainIdList().add(16L);
		caseEntity.setCbrConstraint(constrains);
		
		cbrContradiction = new CBRContradiction();
		cbrContradiction.setNegativeCharacteristicId(2L);
		cbrContradiction.setPositiveCharacteristicId(10L);
		caseEntity.setCbrContradiction(cbrContradiction);
		
		cbrService.createCbrEntity(caseEntity);
	}
}
