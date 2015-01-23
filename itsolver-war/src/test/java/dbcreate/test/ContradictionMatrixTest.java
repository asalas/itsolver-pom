package dbcreate.test;

import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.Characteristic;
import itsolver.model.entity.Contradiction;
import itsolver.model.entity.ContradictionPrinciple;
import itsolver.service.ContradictionMatrixService;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class ContradictionMatrixTest {
	private ContradictionMatrixService contradictionMatrixService;	
	
	protected static Logger log = Logger.getLogger("");
	static {
		log.setLevel(Level.INFO);
	}
	
	@Before
	public void setUp() throws Exception {
		contradictionMatrixService = ServiceLocator.getContradictionMatrixService();
		//createCases();
	}
	
	@Test
	public void testContradictionMatrix(){
		Characteristic characteristicPositive = new Characteristic();
		characteristicPositive.setId(1L);
		Characteristic characteristicNegative = new Characteristic();
		characteristicNegative.setId(7L);
		Contradiction  contradiction=  contradictionMatrixService.findContradictionByCharacteristics(characteristicPositive, characteristicNegative);
		System.out.println("Principios asociados: " + contradiction.getContradictionPrinciple().size());
		for (ContradictionPrinciple contradictionPrinciple : contradiction.getContradictionPrinciple()) {
			System.out.println("Principio [" + contradictionPrinciple.getImportanceOrder() + "] " + contradictionPrinciple.getSolutionPrinciple().getPrincipleName());
		}
		
	}
}
