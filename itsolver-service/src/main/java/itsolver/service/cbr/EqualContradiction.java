package itsolver.service.cbr;

import itsolver.model.entity.cbr.CBRContradiction;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class EqualContradiction implements LocalSimilarityFunction{
	
	public double compute(Object _caseBase, Object _comparisonCase)
			throws NoApplicableSimilarityFunctionException {
		if ((_caseBase == null) || (_comparisonCase == null))
            return 0;
		CBRContradiction caseBase 		= (CBRContradiction)_caseBase;
		CBRContradiction comparisonCase= (CBRContradiction) _comparisonCase;
		int contador = 0;
		if ( caseBase.getPositiveCharacteristicId().equals( comparisonCase.getPositiveCharacteristicId() ) ){
			contador++;
		}
		if ( caseBase.getNegativeCharacteristicId().equals( comparisonCase.getNegativeCharacteristicId() ) ){
			contador++;
		}
		return contador/2;
		
	}
	
	
	public boolean isApplicable(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return false;
	}
}
