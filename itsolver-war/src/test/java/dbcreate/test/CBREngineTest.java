package dbcreate.test;

import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.cbr.CBRCaseEntity;
import itsolver.model.entity.cbr.CBRCaseSolution;
import itsolver.model.entity.cbr.CBRConstraint;
import itsolver.model.entity.cbr.CBRContradiction;
import itsolver.model.entity.cbr.CBRResource;
import itsolver.service.cbr.CbrService;
import itsolver.service.cbr.EqualContradiction;
import itsolver.service.cbr.EqualList;
import itsolver.utils.CBRCaseType;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.selection.SelectCases;

import org.junit.Before;
import org.junit.Test;


public class CBREngineTest {

	private CbrService cbrService;
	private Collection<CBRCase> casesList = new java.util.ArrayList<CBRCase>();
	
	protected static Logger log = Logger.getLogger("");
	static {
		log.setLevel(Level.INFO);
	}
	
	@Before
	public void setUp() throws Exception {
		cbrService = ServiceLocator.getCbrService();
		//createCases();
	}
	
	
	public void createCases() {
		///CASO 1
		CBRCaseEntity caseEntity = new CBRCaseEntity();
		caseEntity.setCaseId(1L);	
		CBRResource resources = new CBRResource();
		resources.setId(1L);
		resources.getResourceIdList().add(1L);
		resources.getResourceIdList().add(2L);
		resources.getResourceIdList().add(3L);
		caseEntity.setCbrResource(resources);
		
		CBRConstraint constrains = new CBRConstraint();
		constrains.setId(2L);
		constrains.getConstrainIdList().add(5L);
		constrains.getConstrainIdList().add(6L);
		constrains.getConstrainIdList().add(7L);
		caseEntity.setCbrConstraint(constrains);
		
		CBRContradiction cbrContradiction = new CBRContradiction();
		cbrContradiction.setId(3L);
		cbrContradiction.setNegativeCharacteristicId(11L);
		cbrContradiction.setPositiveCharacteristicId(15L);
		caseEntity.setCbrContradiction(cbrContradiction);
		
		caseEntity.setProblemNature("TECHNOLOGY_FORESIGHT");
		
		CBRCaseSolution caseSolution = new CBRCaseSolution();
		caseSolution.setSolutionId(1L);
		caseSolution.setSolution("AA");
		
		CBRCase case1 = new CBRCase();
		case1.setDescription(caseEntity);
		case1.setSolution(caseSolution);
		
		//AGREGAMOS EL CASO 1
		casesList.add(case1);
		
		//CASO 2
		caseEntity = new CBRCaseEntity();
		caseEntity.setCaseId(2L);	
		resources = new CBRResource();
		resources.setId(3L);
		resources.getResourceIdList().add(1L);
		resources.getResourceIdList().add(2L);
		//resources.getResourceIdList().add(3L);
		caseEntity.setCbrResource(resources);
		
		constrains = new CBRConstraint();
		constrains.setId(4L);
		constrains.getConstrainIdList().add(9L);
		constrains.getConstrainIdList().add(10L);
		constrains.getConstrainIdList().add(11L);
		constrains.getConstrainIdList().add(12L);
		constrains.getConstrainIdList().add(13L);
		caseEntity.setCbrConstraint(constrains);
		
		cbrContradiction = new CBRContradiction();
		cbrContradiction.setId(4L);
		cbrContradiction.setNegativeCharacteristicId(12L);
		cbrContradiction.setPositiveCharacteristicId(15L);
		caseEntity.setCbrContradiction(cbrContradiction);
		
		caseEntity.setProblemNature("TECHNOLOGY_FORESIGHT");
		
		caseSolution = new CBRCaseSolution();
		caseSolution.setSolutionId(2L);
		caseSolution.setSolution("BB");
		
		case1 = new CBRCase();
		case1.setDescription(caseEntity);
		case1.setSolution(caseSolution);
		
		//AGREGAMOS EL CASO 2
		casesList.add(case1);
		
		///CASO 3
		caseEntity = new CBRCaseEntity();
		caseEntity.setCaseId(3L);	
		resources = new CBRResource();
		resources.setId(9L);
		resources.getResourceIdList().add(1L);
		resources.getResourceIdList().add(2L);
		resources.getResourceIdList().add(3L);
		caseEntity.setCbrResource(resources);
		
		constrains = new CBRConstraint();
		constrains.setId(12L);
		constrains.getConstrainIdList().add(5L);
		constrains.getConstrainIdList().add(6L);
		constrains.getConstrainIdList().add(8L);
		caseEntity.setCbrConstraint(constrains);
		
		cbrContradiction = new CBRContradiction();
		cbrContradiction.setId(3L);
		cbrContradiction.setNegativeCharacteristicId(11L);
		cbrContradiction.setPositiveCharacteristicId(15L);
		caseEntity.setCbrContradiction(cbrContradiction);
		
		caseEntity.setProblemNature("TECHNOLOGY_FORESIGHT33");
		
		caseSolution = new CBRCaseSolution();
		caseSolution.setSolutionId(9L);
		caseSolution.setSolution("CC");
		
		case1 = new CBRCase();
		case1.setDescription(caseEntity);
		case1.setSolution(caseSolution);
		
		//AGREGAMOS EL CASO 3
		casesList.add(case1);
		
	}
	
	@Test
	public void testCBR(){
		CBRQuery query = prepateQueryDesc();		
		casesList = cbrService.getCBRCaseCollection(CBRCaseType.CONTRADICTION_CBR_TYPE);
		prepateQueryDesc();
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());		
		
		Attribute contrAttribute = new Attribute("cbrContradiction", CBRCaseEntity.class);
		simConfig.addMapping(contrAttribute, new EqualContradiction());
		simConfig.setWeight(contrAttribute, 0.5);
		
		Attribute problemNatureAttribute = new Attribute("problemNature", CBRCaseEntity.class);
		simConfig.addMapping(problemNatureAttribute, new Equal());
		simConfig.setWeight(problemNatureAttribute, 0.1);
		
		Attribute cbrResourceAttribute = new Attribute("cbrResource", CBRCaseEntity.class);
		simConfig.addMapping(cbrResourceAttribute, new EqualList());
		simConfig.setWeight(cbrResourceAttribute, 0.2);
		
		Attribute cbrContradictionAttribute = new Attribute("cbrConstrain", CBRCaseEntity.class);
		simConfig.addMapping(cbrContradictionAttribute, new EqualList());
		simConfig.setWeight(cbrContradictionAttribute, 0.2);		
		

		
		System.out.println("Query:");
		System.out.println(query);
		System.out.println();
		
		/********* Execute NN ************/
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(casesList, query, simConfig);
		CaseComponent description;
		for (RetrievalResult retrievalResult : eval) {
			description = retrievalResult.get_case().getDescription();
			System.out.println("[ El caso " + ((CBRCaseEntity)description).getCaseId() + " Tiene una similitud de " + retrievalResult.getEval()  + "]");
		}
		System.out.println("Numero de eval ppor NN " + eval.size());
		
		/********* Select cases **********/
		Collection<CBRCase> selectedcases = SelectCases.selectTopK(eval, 1);
	}


	private CBRQuery prepateQueryDesc() {
		CBRCaseEntity queryDesc = new CBRCaseEntity();
		queryDesc.setCaseId(1L);	
		CBRResource resources = new CBRResource();
		resources.setId(1L);
		resources.getResourceIdList().add(1L);
		resources.getResourceIdList().add(4L);
		resources.getResourceIdList().add(5L);
		queryDesc.setCbrResource(resources);
		
		CBRConstraint constrains = new CBRConstraint();
		constrains.setId(2L);
		constrains.getConstrainIdList().add(4L);
		constrains.getConstrainIdList().add(5L);
		constrains.getConstrainIdList().add(7L);
		constrains.getConstrainIdList().add(9L);
		constrains.getConstrainIdList().add(10L);
		queryDesc.setCbrConstraint(constrains);
		
		CBRContradiction cbrContradiction = new CBRContradiction();
		cbrContradiction.setId(3L);
		cbrContradiction.setNegativeCharacteristicId(3L);
		cbrContradiction.setPositiveCharacteristicId(5L);
		queryDesc.setCbrContradiction(cbrContradiction);
		
		queryDesc.setProblemNature("ELIMINATE_HARMFUL_FUNCTION");
		CBRQuery query = new CBRQuery();
		query.setDescription(queryDesc);
		return query;
	}
}
