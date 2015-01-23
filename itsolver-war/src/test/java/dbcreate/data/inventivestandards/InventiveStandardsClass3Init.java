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

public class InventiveStandardsClass3Init {

	// log
	protected static Logger log = Logger.getLogger("dbcreate/data/inventivestandards/InventiveStandardsClas3Init");
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
	public void testPersistGroup3_1()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-3-1");
		
		// 3-1-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 3-1-1");
		genericStandard.setDescription("System efficiency at any stage of its evolution can be improved by combining the system with another system (or systems) to form a bi- or polysystem. NOTES:	For a simple formation of bi- and polysystems, two and more components are combined. Components to be combined may be substances, fields, substance-field pairs and whole SFMs.");
		genericStandard.setImageDesc("Example: To process sides of thin glass plates, several plates are put together to prevent glass from breaking.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 3-1-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 3-1-2");
		genericStandard.setDescription("Efficiency of bi- and polysystems can be improved by developing links between system elements. NOTES: Links between elements of a bi- and polysystem may be made either more rigid or more dynamic.");
		genericStandard.setImageDesc("Example: To synchronize a process of lifting a very heavy part by three cranes, it is proposed to use a rigid triangle synchronizing the cranes moving parts.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);		
		// 3-1-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 3-1-3");
		genericStandard.setDescription("Efficiency of bi- and polysystems can be improved by increasing the difference between system components. The following line of evolution is recommended: similar components (pencils of the same color) --> components with biased characteristics (pencils of different colors) --> different components (set of drawing instruments) --> combinations of the 'component + component with opposite function' (pencil with rubber)");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 3-1-4
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 3-1-4");
		genericStandard.setDescription("Efficiency of bi- and polysystems can be improved by 'convolution' (integration of several components into a single component) by reducing auxiliary components. Completely convoluted bi- and polysystems become monosystems again, and integration can be repeated at another level of the system.");
		genericStandard.setImageDesc("Example: Instead of three separate indicators on a dashboard, a single indicator can be used in which indicating arrows are made of different colors.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 3-1-5
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 3-1-5");
		genericStandard.setDescription("Efficiency of bi- and polysystems can be improved by distributing incompatible properties among the system and its parts. This is achieved by using a two-level structure in which all the system as a whole has a certain property A, while its parts (particles) have property anti-A.");
		genericStandard.setImageDesc("Example: A working part of a vice is made of segmented plates capable of moving relatively each other. Parts of various shapes can be gripped quickly.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);		
	}
	
	@Test
	public void testPersistGroup3_2()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-3-2");
		
		// 3-2-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 3-2-1");
		genericStandard.setDescription("Efficiency of a system at any stage of its evolution can be improved by transition from a macrolevel to a microlevel: the system or its part is replaced by a substance capable of delivering the required function when interacting with a field. NOTES: There is a multitude of microlevel states of a substance (crystal lattice, molecules, ions, domains, atoms, fundamental particles, fields, etc.). Therefore, various options of transition to a microlevel and various options of transition from one microlevel to another, lower one, should be considered when solving a problem.");
		genericStandard.setImageDesc("Example: Instead of a microscrew, a microscopic table can be positioned by fixing it on a metal rod that is subjected to a thermal field. The rod expands and contracts relatively the value of the temperature due to the effect of thermal expansion.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
}