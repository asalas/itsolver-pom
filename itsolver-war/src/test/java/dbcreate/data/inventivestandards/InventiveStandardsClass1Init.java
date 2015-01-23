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

public class InventiveStandardsClass1Init {

	// log
	protected static Logger log = Logger.getLogger("dbcreate/data/inventivestandards/InventiveStandardsClass1Init");
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
	public void testPersistGroup1_1()throws Exception {
		
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-1-1");
		
		// 1-1-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-1-1");
		genericStandard.setDescription("If there is an object which is not easy to change as required, " +
				"and the conditions do not contain any limitations on the introduction of substances and fields, " +
				"the problem is to be solved by synthesizing a SFM: the object is subjected to the action of a " +
				"physical field which produces the necessary change in the object.");
		genericStandard.setImageURL("http://localhost:8080/itsolver/assets/img_standards/st1.png");
		genericStandard.setImageDesc("Example: To remove air from a powdered substance, " +
				"the substance is subjected to centrifugal forces.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 1-1-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-1-2");
		genericStandard.setDescription("If there is a SFM which is not easy to change as required, and the conditions " +
				"do not contain any limitations on the introduction of additives to given substances, the problem " +
				"is to be solved by a transition (permanent or temporary) to an internal complex SFM, introducing " +
				"additives in the present substances enhancing controllability OP imparting the required properties to the SFM.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: To detect very small drops of liquid, a luminescent substance is added to the liquid " +
				"in advance. Then, using ultraviolet light, it is easy to detect the drops.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 1-1-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-1-3");
		genericStandard.setDescription("If there is a SFM which is not easy to change as required, and the conditions contain " +
				"limitations on the introduction of additives into the existing substances, the problem can bo solved by a transition " +
				"(permanent or temporary) to an external complex SFM. attaching to one of these substances an external substance which " +
				"improves controllability or brings the required properties to the SFM.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: to detect a leakage of gases from a pipe, an outer surface of the pipe is covered with a " +
				"substance that reacts with the gas and produces visible bubbles.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);		
		// 1-1-4
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-1-4");
		genericStandard.setDescription("If there is a SFM that is not easy to change as required, and the conditions contain limitations " +
				"on the introduction or attachment of substances, the problem has to be solved by synthesizing a SFM using internal " +
				"environment as the substance.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 1-1-5
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-1-5");
		genericStandard.setDescription("If the external environment does not contain ready substances required to synthesize a SFM, these " +
				"substances can be obtained by replacing the external environment with another one, or by decomposing the environment, or " +
				"by introducing additives into the environment");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: To improve a coefficient of sliding effect, a liquid lubricant is aerated.");
		genericStandard.setGroup(standardGroup);
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 1-1-6
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-1-6");
		genericStandard.setDescription("If a minimum (measured, optimal) effect of action is required, but it is difficult or impossible to " +
				"provide it under the conditions of the problem, use a maximum action, while the excess of the action is then removed. " +
				"Excess of a substance is removed by a field, while excess of a field is removed by a substance.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: To paint a part accurately, the part first loaded into a container with the paint, and then " +
				"subjected to rotation. Excess of paint is removed due to centrifugal forces.");
		genericStandard.setGroup(standardGroup);
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 1-1-7
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-1-7");
		genericStandard.setDescription("If a maximum effect of action on a substance is required and this is not allowed, the maximum action " +
				"has to be preserved but directed to another substance attached to the first one.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: When manufacturing reinforced concrete, it is possible to use metal wire instead of rods. " +
				"But the wire has to be stretched. To do this it has to be heated up to 700C what is not allowed. The wire is connected to " +
				"the rod that is heated while the wire remains cold.");
		genericStandard.setGroup(standardGroup);
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 1-1-8-1
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-1-8-1");
		genericStandard.setDescription("If a selective effect of action is required (maximum in certain zones, while the minimum is " +
				"maintained in other zones), the field has to be maximal; then a protective substance is introduced in places where " +
				"a minimum effect is required.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: When sealing a glass ampoule with liquid medicine, an overheated glass might destroy the " +
				"medicine. The ampoule is put into water leaving the ampoule's tip above the water. Water protects the rest of the ampoule " +
				"from overheating.");
		genericStandard.setGroup(standardGroup);
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 1-1-8-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-1-8-2");
		genericStandard.setDescription("If a selective-maximum effect is required (maximum in certain zones, and minimum in other zones), the " +
				"field should be minimal; then a substance that produces a local effect interacting with a field (e.g. termite compounds for " +
				"thermal action or explosive ones for mechanical action) is introduced in places where a maximum effect is required.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: to weld two metal parts, an exothermic powder producing extra heat is introduced between the parts.");
		genericStandard.setGroup(standardGroup);
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
	
	@Test
	public void testPersistGroup1_2()throws Exception {
		standardGroup =	algorithmStandardsService.getInventiveStandardGroupById("GROUP-1-2");
		
		// 1-2-1
		// TODO: falta agregar NOTES a la descipcion
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-2-1");
		genericStandard.setDescription("If useful and harmful effects appear between two substances in a SFM and there is no need to maintain a " +
				"direct contact between the substances, the problem is solved by introducing a third substance between them.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: To compact walls of a borehole, gases produced during, explosion are used. However, the gases " +
				"also may cause cracks in the borehole's walls. It is proposed to cover the walls by plasticine that transmits pressure but " +
				"prevents the walls from crack formation.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);		
		// 1-2-2
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-2-2");
		genericStandard.setDescription("If there are a useful and a harmful effects between two substances, and there is no need to maintain " +
				"direct contact between the substances, and it is forbidden or inconvenient to use foreign substances, the problem can be solved " +
				"by introducing a third substance between the two. which is a modification of the first or the second substances.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: A hydrodynamic foil's surface might be destroyed by a cavitation produced by the friction between " +
				"the foil and the water when moving at a high speed. It's proposed to refrigerate the surface of the foil.  Surrounded water will " +
				"freeze and form an ice layer on the foil.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 1-2-3
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-2-3");
		genericStandard.setDescription("If it is required to eliminate the harmful effect of a field upon a substance, the problem can be solved " +
				"by introducing a second substance that draws off upon itself the harmful effect of the field.");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example: To protect underground cables from stresses of ground occurring during frost, cavities are formed " +
				"in the ground beforehand");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 1-2-4
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-2-4");
		genericStandard.setDescription("If useful and harmful effects appear between two substances in a SFM, and a direct contact between the " +
				"substances must be maintained, the problem can be solved by transition to a dual SFM, in which the useful effect is provided by " +
				"the existing field while a new field neutralizes the harmful effect (or transforms the harmful effect into a useful effect).");
		genericStandard.setImageURL("TODO_IMG");
		genericStandard.setImageDesc("Example:  to help with pollination of a flower, airflow is used. However, it also closes the flower. " +
				"It is proposed to open the flower with electrostatic discharge.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
		// 1-2-5
		genericStandard = new InventiveStandard();
		genericStandard.setInventiveStandardNumber("STANDARD 1-2-5");
		genericStandard.setDescription("If it is necessary to decompose a SFM with a magnetic field, the problem is solved by using physical effects, " +
				"which are capable of 'switching off' ferromagnetic properties of substances, e.g. by demagnetizing during an impact or during heating " +
				"above Curie point. Notes:	The magnetic field may appear at the right moment if a system of magnets compensating the effect of each " +
				"other's fields is used. When one of the magnets is demagnetized, a magnetic field arises in the system.");
		genericStandard.setImageDesc("Example:  During welding, it's difficult to insert a ferromagnetic powder in the welding zone: an electromagnetic " +
				"field of a welding current makes the particles move away from the welding zone. It is proposed to heat the powders above the Curie " +
				"point to make them non-magnetic.");
		genericStandard.setGroup(standardGroup);		
		algorithmStandardsService.persistInventiveStandard(genericStandard);
	}
}
