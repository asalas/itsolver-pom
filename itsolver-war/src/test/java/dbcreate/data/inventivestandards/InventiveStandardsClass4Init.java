package dbcreate.data.inventivestandards;

import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.InventiveStandard;
import itsolver.model.entity.InventiveStandardGroup;
import itsolver.service.AlgorithmStandardsService;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventiveStandardsClass4Init {
	// log
	protected static Logger log = Logger.getLogger("dbcreate/data/inventivestandards/InventiveStandardsClas4Init");
	static {
		log.setLevel(Level.INFO);
	}

	private AlgorithmStandardsService algorithmStandardsService;
	private InventiveStandard genericStandard;
	private InventiveStandardGroup standardGroup;
	
	@Before
	public void setUp() throws Exception {
		algorithmStandardsService = ServiceLocator.getAlgorithmStandardsService();
		genericStandard = null;
		standardGroup = null;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testPersistGroup4_1()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-4-1");
		
		// 4-1-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-1-1");
		genericStandard.setDescription("If a problem involves detection or measurement, it is proposed to change the problem in such a way, so that there should be no need to perform detection or measurement at all.");
		genericStandard.setImageDesc("Example: To prevent a permanent electric motor from overheating, its temperature is measured by a temperature sensor. If to make the poles of the motor of an alloy with a Curie point equal to the critical value of the temperature, the motor will stop itself.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 4-1-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-1-2");
		genericStandard.setDescription("If a problem involves detection or measurement, and it is impossible to change the problem to eliminate the need for detection or measurement, it is proposed to change/detect properties of a copy of the object (e.g. picture).");
		genericStandard.setImageDesc("Example: It might be dangerous to measure the length of a snake. It is safe to measure its length on a photographic image of the snake, and then recalculate the obtained result.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 4-1-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-1-3");
		genericStandard.setDescription("If a problem involves detection or measurement, and the problem cannot be changed to eliminate the need for measurement, and it is impossible to use copies or pictures, it is proposed to transform this problem into a problem of successive detection of changes. NOTES:	Any measurement is conducted with a certain degree of accuracy. Therefore, even if the problem deals with continuous measurement, one can always single out a simple act of measurement that involves two successive detections. This makes the problem much simpler.");
		genericStandard.setImageDesc("Example: To measure a temperature, it is possible to use a material that changes its color depending on the current value of the temperature. Alternatively, several materials can be used to indicate different temperatures.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);		
	}
	
	@Test
	public void testPersistGroup4_2()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-4-2");
		
		// 4-2-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-2-1");
		genericStandard.setDescription("If a non-SFM is not easy to detect or measure, the problem is solved by synthesizing a simple or dual SFM with a field at the output. Instead of direct measurement or detection of a parameter, another parameter identified with the field is measured or detected. The field to be introduced should have a parameter that we can easily detect or measure, and which can indicate the state of the parameter we need to detect or measure.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: To detect a moment when a liquid starts to boil, an electrical current is passed through the liquid. During boiling, air bubbles are formed - they dramatically reduce electrical resistance of the liquid.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 4-2-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-2-2");
		genericStandard.setDescription("If a system (or its part) does not provide detection or measurement, the problem is solved by transition to an internal or external complex measuring SFM, introducing easily detectable additives.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: To detect leakage in a refrigerator, a cooling agent is mixed with a luminescent powder.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 4-2-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-2-3");
		genericStandard.setDescription("If a system is difficult to detect or to measure at a given moment of time, and it is not allowed or not possible to introduce additives into the object, then additives that create an easily detectable and measurable field should be introduced in the external environment. Changing the state of the environment will indicate the state of the object.");
		genericStandard.setImageDesc("Example: To detect wearing of a rotating metal disc contacting with another disk, it is proposed to introduce luminescent powder into the oil lubricant, which already exists in the system. Metal particles collecting in the oil will reduce luminosity of the oil.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 4-2-4
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-2-4");
		genericStandard.setDescription("If it is impossible to introduce easily detectable additives in the external environment, these can be obtained in the environment itself, for instance, by decomposing the environment or by changing the aggregate state of the environment. NOTES:	In particular, gas or vapor bubbles produced by electrolysis, cavitation or by any other method may often be used as additives obtained by decomposing the external environment.");
		genericStandard.setImageDesc("Example: The speed of a water flow in a pipe might be measured by amount of air bubbles resulting from cavitation.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
	
	@Test
	public void testPersistGroup4_3()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-4-3");
		
		// 4-3-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-3-1");
		genericStandard.setDescription("Efficiency of a measuring SFM can be improved by the use of physical effects.");
		genericStandard.setImageDesc("Example: Temperature of liquid media can be measured by measuring a change of a coefficient of retraction, which depends on the value of the temperature.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);		
		// 4-3-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-3-2");
		genericStandard.setDescription("If it is impossible to detect or measure directly the changes in the system, and no field can be passed through the system, the problem can be solved by exciting resonance oscillations (of the entire system or of its part), whose frequency change is an indication of the changes taking place.");
		genericStandard.setImageDesc("Example: To measure the mass of a substance in a container, the container is subjected to mechanically forced resonance oscillations. The frequency of the oscillations depends on the mass of the system.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 4-3-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-3-3");
		genericStandard.setDescription("If resonance oscillations may not be excited in a system, its state can be determined by a change in the natural frequency of the object (external environment) connected with the system.");
		genericStandard.setImageDesc("Example: The mass of boiling liquid can be measured by measuring the natural frequency of gas resulting from evaporation.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
	
	@Test
	public void testPersistGroup4_4()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-4-4");
		
		// 4-4-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-4-1");
		genericStandard.setDescription("Efficiency of a measuring SFM can be improved by using a ferromagnetic substance and a magnetic field. NOTES:	The standard indicates the use of a non-fragmented ferromagnetic object.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);		
		// 4-4-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-4-2");
		genericStandard.setDescription("Efficiency of detection or measurement can be improved by transition to ferromagnetic SFMs, replacing one of the substances with ferromagnetic particles (or adding ferromagnetic particles) and by detecting or measuring the magnetic field.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 4-4-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-4-3");
		genericStandard.setDescription("If it is required to improve the efficiency of detection or measurement by transition to a ferromagnetic SFM, and replacement of the substance with ferromagnetic particles is not allowed, the transition to the F-SFM is performed by synthesizing a complex ferromagnetic SFM, introducing (or attaching) ferromagnetic additives in the substance.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 4-4-4
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-4-4");
		genericStandard.setDescription("If it is required to improve efficiency of detection or measurement by transition to F-SFM, and introduction of ferromagnetic particles is not allowed, ferromagnetic particles are introduced in the external environment.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 4-4-5
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-4-5");
		genericStandard.setDescription("Efficiency of a F-SFM measuring system can be improved by using physical effects, for instance, Curie point, Hopkins and Barkhausen effects, magnetoelastic effect, etc.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
	
	@Test
	public void testPersistGroup4_5()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-4-5");
		
		// 4-5-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-5-1");
		genericStandard.setDescription("Efficiency of a measuring system at any stage of its evolution can be improved by forming bi- or polysystem. NOTES:	To form bi- and polysystems, two or more components are combined. The components to be combined may be substances, fields, substance-field pairs and SFMs.");
		genericStandard.setImageDesc("Example: It is difficult to accurately measure the temperature of a small beetle. However, if there are many beetles put together, the temperature can be measured easily.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 4-5-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 4-5-2");
		genericStandard.setDescription("Measuring systems evolve towards measuring the derivatives of the function under control. The transition is performed along the following line: measurement of a function --> measurement of the first derivative of the function --> measurement of the second derivative of the function.");
		genericStandard.setImageDesc("Example: Change of stress in the rack are defined by the speed of changing the electrical resistance of the rack.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
}
