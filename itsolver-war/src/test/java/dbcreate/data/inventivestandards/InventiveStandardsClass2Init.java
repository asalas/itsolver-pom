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

public class InventiveStandardsClass2Init {
	// log
	protected static Logger log = Logger.getLogger("dbcreate/data/inventivestandards/InventiveStandardsClass2Init");
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
	public void testPersistGroup2_1()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-2-1");
		
		// 2-1-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-1-1");
		genericStandard.setDescription("Efficiency of SFM can be improved by transforming one of the parts of the SFM into an independently controllable SFM, thus forming a chain SFM.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: A tractor with movable center of gravity to work on steep slopes.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		
		// 2-1-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-1-2");
		genericStandard.setDescription("If it is necessary to improve the efficiency of SFM, and replacement of SFM elements is not allowed, the problem can be solved by the synthesis of a dual SFM through introducing a second SFM which is easy to control.");
		genericStandard.setImageDesc("Example: It is proposed to increase control over a melted metal by rotating the metal in a centrifuge.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
	
	@Test
	public void testPersistGroup2_2()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-2-2");
		
		// 2-2-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-2-1");
		genericStandard.setDescription("Efficiency of a SFM can be improved by replacing an uncontrolled (or poorly controlled) field with a well-controlled field, e.g. by replacing a gravitation field with mechanical field, mechanical field with an electric, etc."+
						"NOTES: In certain situations, controllability of a field may be improved not only by replacing a given field with another one, but al so by modifying the present field along the following line:"+
						"Permanent field --> monotonically changing one --> pulsed one -->"+
						"variable one --> variable in frequency and amplitude --> etc.");
		genericStandard.setImageDesc("Example: Instead of a metal blade for non-uniform metal cutting, a water jet can be used");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);		
		// 2-2-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-2-2");
		genericStandard.setDescription("Efficiency of a SFM can be improved by increasing the degree of fragmentation of the object which acts as an instrument in SFM. "+
						"NOTES:	The standard displays one of the major trends of the technology evolution, i.e. fragmentation of the object or its part interacting with the product.");
		genericStandard.setImageDesc("Example A: knife with teeth, then with the abrasive coating.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-2-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-2-3");
		genericStandard.setDescription("Efficiency of a SFM can be improved by transition from a solid object to a capillary porous one. The transition is performed as:"+
						"solid object--> "+
						"object with one cavity --> "+ 
						"object with multiple cavities (perforated) --> "+
						"capillary porous object --> "+
						"capillary porous object with a predefined porous structure. "+
						"NOTES:	Transition to a capillary porous object enables a liquid substance to be placed in the pores and use physical effects.");
		genericStandard.setImageDesc("Example: A bunch of capillaries apply liquid glue more accurately on a surface to be glued than a single large-sized tube.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-2-4
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-2-4");
		genericStandard.setDescription("Efficiency of a SFM can be improved by increasing the degree of dynamics of SFM, i.e. by transition to a more flexible, rapidly changing structure of the system. "+
						"NOTES: Making a substance dynamic starts with dividing it into two joint-coupled parts and continues along the following line: "+
						"One joint --> many joints --> flexible object. "+ 
						"A field can be made more dynamic by transition from a permanent field (or of the field together with a substance) to a pulsed field.");
		genericStandard.setImageDesc("Example: A door made of hinged segments.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-2-5
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-2-5");
		genericStandard.setDescription("Efficiency of SFM can be improved by transition from a uniform field or fields with a disordered structure to non-uniform fields or fields with a definite spatial-temporal structure (permanent or variable). "+
						"Notes: If a certain spatial structure is to be imparted to a substance object, the process can be conducted in a field having a structure that matches the required structure of the substance object.");
		genericStandard.setImageDesc("Example: To mix two magnetic powders, a layer of the first powder is put in the layer of the second powder and the non-uniform magnetic field is applied.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-2-6
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-2-6");
		genericStandard.setDescription("Efficiency of a SFM can be improved by transition from substances that are uniform or have a disordered structure to substances that are non-uniform or have a predefined spatial-temporal structure (permanent or variable). "+
						"NOTES:	In particular, if an intensive effect of a field is required in certain places of a system (points, lines), then substances that produce the required field are introduced in these spots beforehand.");
		genericStandard.setImageDesc("Example: To make a porous material with oriented spatial structure the field threats are inserted into the soft material beforehand. After the material solidifies these threats are burned out.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
	
	@Test
	public void testPersistGroup2_3()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-2-3");
		
		// 2-3-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-3-1");
		genericStandard.setDescription("Efficiency of a SFM can be improved by matching (or mismatching) the frequency of acting field with the natural frequency of a product (or tool).");
		genericStandard.setImageDesc("Example: 1. The rhythm of massage is synchronized with a pulse of a patient. 2. In arc welding, the frequency of magnetic field is equal to the natural frequency of a melting electrode.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);		
		// 2-3-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-3-2");
		genericStandard.setDescription("Efficiency of a complex SFM can be improved by matching (or mismatching) frequencies of the fields being used.");
		genericStandard.setImageDesc("Example: To coat a part with a material, the material is applied as a powder. To provide a high degree of regularity, the frequencies of pulses of an electrical current and pulses of magnetic field are made equal.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-3-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-3-3");
		genericStandard.setDescription("If we are given two incompatible actions, e.g. changing and measuring, one action should be performed during the pauses between another one. In general, pauses in one action should be filled with another useful action.");
		genericStandard.setImageDesc("Example: To provide accuracy of contact welding, measurements are conducted during the pauses between the pulses of an electrical current.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
	
	@Test
	public void testPersistGroup2_4()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-2-4");
		
		// 2-4-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-1");
		genericStandard.setDescription("Efficiency of a SFM is enhanced by using a ferromagnetic substance and a magnetic field.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("NOTES: 1. The standard indicates the use of a ferromagnetic substance that is not in a fragmented state. 2. F-SFM is a SFM system in which a disperse ferromagnetic substance and a magnetic field are interacting.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-4-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-2");
		genericStandard.setDescription("Efficiency of control over a SFM can be improved by replacing one of the substances with ferromagnetic particles (or adding ferromagnetic particles) -chips, granules, grains, etc. - and using magnetic or electromagnetic field. "+
						"NOTES:	Efficiency of control rises with a higher degree of fragmentation of ferromagnetic particles and of the substance in which they are introduced. "+
						"ferroparticles: granules -> powder -> finely dispersed particles -> magnetic liquid; substance: solid-> grains-> powder-> liquid.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-4-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-3");
		genericStandard.setDescription("Efficiency of a ferromagnetic SFM can be improved by using magnetic fluids - colloidal ferromagnetic particles suspended in kerosene, silicone or water.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-4-4
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-4");
		genericStandard.setDescription("Efficiency of a ferromagnetic SFM can be improved by using a capillary porous structure inherent in many F-SFMs.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-4-5
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-5");
		genericStandard.setDescription("If it is required to raise the efficiency of control and replacement of substances with ferromagnetic particles is not allowed, one has to compose internal or external complex ferromagnetic SFM, introducing additives in one of the substances.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-4-6
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-6");
		genericStandard.setDescription("If it is required to raise the efficiency of control and replacement of substances with ferromagnetic particles is not allowed, the ferromagnetic particles should be introduced in the external environment Then, using the magnetic field, the environment parameters should be changed so that the system becomes more controllable.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-4-7
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-7");
		genericStandard.setDescription("Controllability of a ferromagnetic system can be improved by the use of physical effects.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-4-8
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-8");
		genericStandard.setDescription("Efficiency of a F-SFM can be improved by increasing the degree of dynamics in the system, for instance, by transition to a more flexible, rapidly changing structure of the system. NOTES:	Making a substance more dynamic begins with dividing it into two joint-coupled parts and continues along the following line: One joint -> many joints -> flexible substance.A field is made dynamic by going over from a permanent effect of the field (or of the field together with a substance) to a pulsed effect.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-4-9
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-9");
		genericStandard.setDescription("Efficiency of F-SFM can be improved by transition from fields that are uniform or have a disordered structure to fields that are non-uniform or have a definite spatial-temporal structure (permanent or variable). NOTES: If a certain spatial structure is to be imparted to a substance object, the process can be conducted in a field having a structure that matches the required structure of the substance object.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-4-10
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-10");
		genericStandard.setDescription("Efficiency of F-SFM can be improved by matching the rhythms of the system's elements.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-4-11
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-11");
		genericStandard.setDescription("If it is not allowed to introduce ferromagnetics or to perform magnetization, an E-SFM has to be synthesized using: a) interaction of an external electromagnetic field with currents or b) fed through a contact or induced without a contact, or c) using interaction between these currents. NOTES:	An E-SFM is a SFM in which electric currents interact with each other. The evolution of E-SFMs repeats the line of evolution of complex-boosted SFMs: Simple E-SFM -> complex E-SFM -> E-SFM in the external environment -> E-SFM Dynamisation -> structuring -> matching the rhythms.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 2-4-12
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 2-4-12");
		genericStandard.setDescription("If a magnetic fluid cannot be used, one can use an electrorheologic fluid (a suspension of fine quartz powder in toluene, for instance, with viscosity being changed by the electric field). A SFM with an electrorheologic fluid is a special form of E-SFM.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		
	}
}
