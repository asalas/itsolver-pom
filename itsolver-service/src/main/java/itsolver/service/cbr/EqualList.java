package itsolver.service.cbr;

import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class EqualList implements LocalSimilarityFunction {

	
	public double compute(Object _caseList, Object _comparisionList) throws NoApplicableSimilarityFunctionException {
		if ((_caseList == null) || (_comparisionList == null))
            return 0;
		
		ListComparator caseList = (ListComparator) _caseList;
		ListComparator comparisionList = (ListComparator) _comparisionList;
		double countEquals = 0;
		
		
		
		for (Long baseItem : caseList.getListToComparate()) {
			for (Long comparitionItem : comparisionList.getListToComparate()) {
				if ( baseItem.equals(comparitionItem) ){
					countEquals++;
					break;
				}
			}
		}
		
		if ( countEquals == 0 ){
			return countEquals;
		}
				
		System.err.println("countEquals: " + countEquals);
		
		//double factorBase =  countEquals / comparisionList.getListToComparate().size() ;
		//System.err.println("factorBase: " + factorBase);
		
		double factorSource =  countEquals / caseList.getListToComparate().size();
		
		//System.err.println("factorSource: " + factorSource);
		
		//double factorSimilitud = (factorBase + factorSource )/2;
		//System.err.println("Factor similitud : " + factorSimilitud);
		
		return factorSource;
	}

	
	public boolean isApplicable(Object caseList, Object comparisionList) {
		// TODO Auto-generated method stub
		return true;
	}

}
