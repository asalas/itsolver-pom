package itsolver.service.cbr;

import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.ContradictionProject;
import itsolver.model.entity.Project;
import itsolver.model.entity.cbr.CBRCaseEntity;
import itsolver.utils.CBRCaseType;

import java.util.Collection;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;

/**
 * Clase que implementa los algoritmos para hacer la busqueda de casos en el  CBR con la ayuda de clases de la implementación de jCOLIBRI
 * @author rene
 *
 */
public class CBREngine {
	//Atributo para almacenar la lista de casos
	private Collection<CBRCase> casesList;
	//Clase del servicio para acceder a la base de casos.
	private CbrService cbrService;

	public CBREngine() {
		casesList = new java.util.ArrayList<CBRCase>();
		cbrService = ServiceLocator.getCbrService();
	}
	/**
	 * Método que hace la busqueda en el modulo del CBR
	 * @param project. Recibe como parametro una entidad con los datos del proyecto que se va a comparar
	 * @return
	 */
	public  Collection<RetrievalResult> doCBRSearch(Project project){
		//Transforma el proyecto en un objeto de tipo CBRQuery para hacer la comparación
		CBRQuery query = getQueryDescription(project);
		NNConfig simConfig = null;
		
		if (project instanceof ContradictionProject){
			simConfig = getCBRContradictionConf();
			casesList = cbrService.getCBRCaseCollection(CBRCaseType.CONTRADICTION_CBR_TYPE);
		}else{
			simConfig = getCBRSufieldConf();
			casesList = cbrService.getCBRCaseCollection(CBRCaseType.SU_FIELD_CBR_TYPE);
		}

		/********* Execute NN ************/
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(casesList, query, simConfig);				
		return eval;		
	}
	
	/**
	 * Configuración de los atributos indexados para hacer la comparación de casos
	 * @return
	 */
	private NNConfig getCBRContradictionConf() {
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());		
		//Configuración del atributo contradicción.
		Attribute contrAttribute = new Attribute("cbrContradiction", CBRCaseEntity.class);
		simConfig.addMapping(contrAttribute, new EqualContradiction());
		simConfig.setWeight(contrAttribute, 0.5);//Se asigna el peso de 50% a la contradicción.
		//Configuración del atributo naturaleza del problema
		Attribute problemNatureAttribute = new Attribute("problemNature", CBRCaseEntity.class);
		simConfig.addMapping(problemNatureAttribute, new Equal());
		simConfig.setWeight(problemNatureAttribute, 0.05);//Se le asigna un peso del 5%
		//Configuración del atributo de los recursos
		Attribute cbrResourceAttribute = new Attribute("cbrResource", CBRCaseEntity.class);
		simConfig.addMapping(cbrResourceAttribute, new EqualList());
		simConfig.setWeight(cbrResourceAttribute, 0.25);//Se asigna un peso del 25%
		//Configuración del atributo de las restricciones
		Attribute cbrConstrainAttribute = new Attribute("cbrConstraint", CBRCaseEntity.class);
		simConfig.addMapping(cbrConstrainAttribute, new EqualList());
		simConfig.setWeight(cbrConstrainAttribute, 0.2);//Se le asigna un peso del 20%
		return simConfig;
	}
	

	private NNConfig getCBRSufieldConf() {
		NNConfig simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average());		
		
		Attribute sufieldAttribute = new Attribute("cbrSuField", CBRCaseEntity.class);
		simConfig.addMapping(sufieldAttribute, new EqualSuField()); 
		simConfig.setWeight(sufieldAttribute, 0.5);
		
		Attribute problemNatureAttribute = new Attribute("problemNature", CBRCaseEntity.class);
		simConfig.addMapping(problemNatureAttribute, new Equal());
		simConfig.setWeight(problemNatureAttribute, 0.05);
		
		Attribute cbrResourceAttribute = new Attribute("cbrResource", CBRCaseEntity.class);
		simConfig.addMapping(cbrResourceAttribute, new EqualList());
		simConfig.setWeight(cbrResourceAttribute, 0.25);
		
		Attribute cbrContradictionAttribute = new Attribute("cbrConstraint", CBRCaseEntity.class);
		simConfig.addMapping(cbrContradictionAttribute, new EqualList());
		simConfig.setWeight(cbrContradictionAttribute, 0.2);
		
		return simConfig;
	}	
	
	/**
	 * Transforma la entidad del proyecto en un objeto de tipo CBRQuery para que hacer la comparación contra los casos almacenados
	 * @param project Entidad con los datos del proyecto que se compara
	 * @return Regresa un objeto del tipo CBRQuery
	 */
	private CBRQuery getQueryDescription(Project project){
		CBRCaseEntity queryDesc = this.cbrService.getCBRCaseEntity(project);
		CBRQuery query = new CBRQuery();
		query.setDescription(queryDesc);
		return query;
	}
	
}
