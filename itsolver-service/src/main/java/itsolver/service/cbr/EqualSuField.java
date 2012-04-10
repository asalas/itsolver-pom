package itsolver.service.cbr;

import java.util.List;

import itsolver.model.entity.InventiveStandard;
import itsolver.model.entity.cbr.CBRSuField;
import itsolver.model.entity.sufield.Edge;
import itsolver.model.entity.sufield.Node;
import itsolver.model.entity.sufield.SuFieldGraph;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class EqualSuField implements LocalSimilarityFunction{
	
	public static final String APPLICATION_CONNECTOR  = "ApplicationConn";
	public static final String DESIRED_EFFECT_CONNECTOR = "DesiredEffectConn";
	public static final String INSUFICIENT_DESIRED_EFFECT_CONNECTOR = "InsufficientDesiredEffectConn";
	public static final String HARMFUL_EFFECT_CONNECTOR = "HarmfulEffectConn";
	
	
	public double compute(Object _caseBase, Object _comparisonCase)
			throws NoApplicableSimilarityFunctionException {
		
		if(_caseBase==null || _comparisonCase==null) {
			return 0;
		}
		
		Integer compute = 0;
		
		CBRSuField caseBase = (CBRSuField)_caseBase;
		CBRSuField comparisonCase = (CBRSuField)_comparisonCase;
		
		InventiveStandard caseBaseInventiveStandard = caseBase.getInventiveStandard();
		SuFieldGraph suFieldGraphBase = caseBase.getSuFieldGraph();
		
		InventiveStandard comparisonCaseInventiveStandard = comparisonCase.getInventiveStandard();
		SuFieldGraph suFieldGraphComparison = comparisonCase.getSuFieldGraph();
		
		// primer criterio de similitud, tener el mismo tipo de Estandar de Inventiva
		if(caseBaseInventiveStandard.equals(comparisonCaseInventiveStandard)) {
			compute++;
		}
		
		// segundo criterio de similitud, obtener la comparacion entre los SuFieldGraph
		compute += similaritySuFieldGraph(suFieldGraphBase, suFieldGraphComparison);
		
		double newCompute = Double.parseDouble(compute.toString());
		
		double similarity =  newCompute/4.0;
		
		return similarity;
	}
	
	
	public boolean isApplicable(Object arg0, Object arg1) {
		return false;
	}
	
	
	private Integer similaritySuFieldGraph(SuFieldGraph suFieldGraphBase, SuFieldGraph suFieldGraphComparison) {		
		Integer similarityDegree = 0;
		
		List<Node> suFieldGraphBaseNodes = suFieldGraphBase.getNodes();
		List<Node> suFieldGraphCompNodes = suFieldGraphComparison.getNodes();
		
		// el primer criterio de comparacion sera el mismo numero de nodos
		// sin importar su tipo		
		if(suFieldGraphBaseNodes!=null && suFieldGraphCompNodes!=null) {			
			if(suFieldGraphBaseNodes.size() == suFieldGraphCompNodes.size()) {
				similarityDegree++;
			}
			
		}

		// el segundo criterio de comparacion sera el mismo numero de conectores
		// y tambien que sean del mismo tipo
		List<Edge> suFieldGraphBaseEdges = suFieldGraphBase.getEdges();
		List<Edge> suFieldGraphCompEdges = suFieldGraphComparison.getEdges();	
		
		// Mismo numero de conectores?
		if(suFieldGraphBaseEdges.size() == suFieldGraphCompEdges.size()) {
			similarityDegree++;
		
			int countBaseAppConn = 0;
			int countBaseDesEffConn = 0;
			int countBaseInsDesEffConn = 0;
			int countBaseHarmEffConn = 0;
			
			int countCompAppConn = 0;
			int countCompDesEffConn = 0;
			int countCompInsDesEffConn = 0;
			int countCompHarmEffConn = 0;
			
			for(Edge edge: suFieldGraphBaseEdges) {
				String type = edge.getType();
				if(type.equals(EqualSuField.APPLICATION_CONNECTOR)) {
					countBaseAppConn++;
				}else if(type.equals(EqualSuField.DESIRED_EFFECT_CONNECTOR)) {
					countBaseDesEffConn++;
				}else if(type.equals(EqualSuField.INSUFICIENT_DESIRED_EFFECT_CONNECTOR)) {
					countBaseInsDesEffConn++;
				}else if(type.equals(EqualSuField.HARMFUL_EFFECT_CONNECTOR)) {
					countBaseHarmEffConn++;
				}
			}
			
			for(Edge edge: suFieldGraphCompEdges) {
				String type = edge.getType();
				if(type.equals(EqualSuField.APPLICATION_CONNECTOR)) {
					countCompAppConn++;
				}else if(type.equals(EqualSuField.DESIRED_EFFECT_CONNECTOR)) {
					countCompDesEffConn++;
				}else if(type.equals(EqualSuField.INSUFICIENT_DESIRED_EFFECT_CONNECTOR)) {
					countCompInsDesEffConn++;
				}else if(type.equals(EqualSuField.HARMFUL_EFFECT_CONNECTOR)) {
					countCompHarmEffConn++;
				}
			}
			// Mismo TIPO de conectores
			if((countBaseAppConn==countCompAppConn) && (countBaseDesEffConn==countCompDesEffConn) &&
				(countBaseInsDesEffConn==countCompInsDesEffConn) && (countBaseHarmEffConn==countCompHarmEffConn)) {
				similarityDegree++;
			}
			
			
		}		
		
		return similarityDegree;
	}
}
