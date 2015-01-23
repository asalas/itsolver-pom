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

public class InventiveStandardsClass5Init {
	// log
	protected static Logger log = Logger.getLogger("dbcreate/data/inventivestandards/InventiveStandardsClas5Init");
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
	public void testPersistGroup5_1()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-5-1");
		
		// 5-1-1-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-1-1");
		genericStandard.setDescription("If it is necessary to introduce a substance in the system, and it is not allowed, a 'void' can be used instead of the substance. NOTES: A 'void' is usually gaseous substance, like air, or empty space formed in a solid object. In some cases a 'void' may be formed by other substances, such as liquids (foam) or loose bodies.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-1-1-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-1-2");
		genericStandard.setDescription("If it is necessary to introduce a substance in the system, and it is not allowed, a field can be introduced instead of the substance.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-1-1-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-1-3");
		genericStandard.setDescription("If it is necessary to introduce a substance in the system, and it is not allowed, an external additive can be used instead of an internal one.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-1-1-4
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-1-4");
		genericStandard.setDescription("If it is necessary to introduce a substance in the system, and it is not allowed, a very active additive can be introduced in very small quantities.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-1-1-5
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-1-5");
		genericStandard.setDescription("If it is necessary to introduce a substance in the system, and it is not allowed, an additive can be introduced in very small quantities, and concentrated in certain parts of the object.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-1-1-6
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-1-6");
		genericStandard.setDescription("If it is necessary to introduce a substance in the system, and it is not allowed, the substance can be introduced temporarily and then removed.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-1-1-7
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-1-7");
		genericStandard.setDescription("If it is necessary to introduce a substance in the system, and it is not allowed, a copy of the object can be used instead of the object itself, where introduction of substances is allowed.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-1-1-8
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-1-8");
		genericStandard.setDescription("If it is necessary to introduce a substance in the system, and it is not allowed by the system's operating conditions, the substance can be introduced in a form of a chemical compound which can be later decomposed.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-1-1-9
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-1-9");
		genericStandard.setDescription("If it is necessary to introduce a substance in the system, and it is not allowed, the substance can be produced by decomposing the external environment or the object itself, for instance, by electrolysis, or by changing the aggregate state of a part of the object or external environment.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-1-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-2");
		genericStandard.setDescription("If a system is not easy to change as required, and the conditions do not allow to replace the component acting as an instrument or introduce additives, the artifact has to be used instead of the instrument, dividing the artifact into parts interacting with each other.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-1-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-3");
		genericStandard.setDescription("After the substance introduced in the system has fulfilled its function, it should either disappear or become indistinguishable from the substance that was in the system or in the external environment before. NOTE: The substance that has been introduced may disappear due to chemical reactions or change of phase.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-1-4
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-1-4");
		genericStandard.setDescription("If it is necessary to introduce a large quantity of a substance, but this is not allowed, a 'void' in the form of inflatable structures or foam should be used as the substance. NOTE:	Introduction of foam or inflatable structures resolves a contradiction 'much substance - little substance'.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
	
	@Test
	public void testPersistGroup5_2()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-5-2");
		
		// 5-2-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-2-1");
		genericStandard.setDescription("If a field has to be introduced in a SFM, one should use first of all the present fields for whom the media are those substances that form the system or its part. NOTES:	The use of substances and fields which already present in the system improves the system's ideality: number of functions performed by the system increases without increasing the number of used components.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-2-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-2-2");
		genericStandard.setDescription("If a field has to be introduced in a SFM and it is not possible to use the fields which already present in the system, one should use the fields of the external environment. NOTE: The use of external environment fields (gravitation, thermal field, pressure...) improves the system's ideality: the number of functions performed by the system increases without increasing the number of used components.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-2-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-2-3");
		genericStandard.setDescription("If a field has to be introduced in a SFM but it is impossible to use the fields which already present in the system or in the external environment, one should use the fields for whom the substances present in. the system or external environment can act as media or sources. NOTES:	In particular, if there are ferromagnetic substances in a system and they are used for mechanical purposes, it is possible to use their magnetic properties in order to obtain additional effects: improve interactions between components, obtain information on the state of the system, etc.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
	
	@Test
	public void testPersistGroup5_3()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-5-3");
		
		// 5-3-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-3-1");
		genericStandard.setDescription("Efficiency of the use of a substance without introducing other substances can be improved by changing its phase.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-3-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-3-2");
		genericStandard.setDescription("'Dual' properties are provided by using substances capable of converting from one phase to another according to operating conditions.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-3-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-3-3");
		genericStandard.setDescription("Efficiency of a system can be improved by the use of physical phenomena accompanying a phase transition. NOTES: Structure of a substance, density, thermal conductivity, etc. also change along with the change of aggregate state during all types of phase transitions. In addition, during phase transitions, energy may be released or absorbed.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-3-4
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-3-4");
		genericStandard.setDescription("'Dual' properties of a system are provided by replacing a single-phase state of the substance with a dual-phase state.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-3-5
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-3-5");
		genericStandard.setDescription("Efficiency of systems obtained as a result of replacing a substance's single-phase state with a dual-phase state can be improved by introducing interaction .(physical or chemical) between parts (phases) of the system.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
	
	@Test
	public void testPersistGroup5_4()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-5-4");
		
		// 5-4-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-4-1");
		genericStandard.setDescription("If an object is to be alternating between different physical states, the transition is performed by the object itself using reversible physical transformations, e.g. phase transitions, ionization-recombination, dissociation-association, etc. NOTE: A dynamic balance providing for the process self-adjustment or stabilization may be maintained in the dual-phase state.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-4-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-4-2");
		genericStandard.setDescription("If it is necessary to obtain a strong effect at the system's output, given a weak effect at the input, the transformer substance is placed to a condition close to critical. The energy is stored in the substance, and the input signal acts a 'trigger'.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
	
	@Test
	public void testPersistGroup5_5()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-5-5");
		
		// 5-5-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-5-1");
		genericStandard.setDescription("If substance particles (e. g. ions) are required to solve a problem and they are not available according to the problem conditions, the required particles can be obtained by decomposing a substance of a higher structural level (e.g. molecules).");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-5-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-5-2");
		genericStandard.setDescription("If substance particles (e.g. molecules) are required to solve a problem and they can not be produced by decomposing a substance of a higher structural level, the required particles can be obtained by combining particles of a lower structural level (e.g. ions).");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 5-5-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 5-5-3");
		genericStandard.setDescription("If a substance of a higher structural level has to be decomposed, the easiest way is to decompose the nearest higher element. When combining particles of a lower structural level, the easiest way is to complete the nearest lower element.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
}
