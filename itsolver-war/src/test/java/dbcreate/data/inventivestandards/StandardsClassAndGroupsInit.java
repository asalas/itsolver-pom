package dbcreate.data.inventivestandards;

import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.InventiveStandardClass;
import itsolver.model.entity.InventiveStandardGroup;
import itsolver.service.AlgorithmStandardsService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StandardsClassAndGroupsInit {

	// log
	protected static Logger log = Logger.getLogger("dbcreate/data/inventivestandards/StandardsClassAndGroupsInit");
	static {
		log.setLevel(Level.INFO);
	}

	private AlgorithmStandardsService algorithmStandardsService;
	
	@Before
	public void setUp() throws Exception {
		algorithmStandardsService = ServiceLocator.getAlgorithmStandardsService();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void persistStandardsClassification()throws Exception {
		InventiveStandardClass standardClass1 = new InventiveStandardClass();
		standardClass1.setClassificationNumber("CLASS-1");
		standardClass1.setName("CLASS 1. COMPOSITION AND DESCOMPOSITION OF SFMS");
		List<InventiveStandardGroup> groupListStandardClass1 = new ArrayList<InventiveStandardGroup>();
		
		InventiveStandardClass standardClass2 = new InventiveStandardClass();
		standardClass2.setClassificationNumber("CLASS-2");
		standardClass2.setName("CLASS 2. EVOLUTION OF SFMS");
		List<InventiveStandardGroup> groupListStandardClass2 = new ArrayList<InventiveStandardGroup>();
		
		InventiveStandardClass standardClass3 = new InventiveStandardClass();
		standardClass3.setClassificationNumber("CLASS-3");
		standardClass3.setName("CLASS 3. TRANSITIONS TO SUPERSYSTEM AND MICROLEVEL");
		List<InventiveStandardGroup> groupListStandardClass3 = new ArrayList<InventiveStandardGroup>();
		
		InventiveStandardClass standardClass4 = new InventiveStandardClass();
		standardClass4.setClassificationNumber("CLASS-4");
		standardClass4.setName("CLASS 4. MEASUREMENT AND DETECTION STANDARDS");
		List<InventiveStandardGroup> groupListStandardClass4 = new ArrayList<InventiveStandardGroup>();
		
		InventiveStandardClass standardClass5 = new InventiveStandardClass();
		standardClass5.setClassificationNumber("CLASS-5");
		standardClass5.setName("CLASS 5. HELPERS");
		List<InventiveStandardGroup> groupListStandardClass5 = new ArrayList<InventiveStandardGroup>();		
		
		// groups - inventive standards		
		// group 1
		InventiveStandardGroup standardGroup1_1 = new InventiveStandardGroup();
		standardGroup1_1.setGroupNumber("GROUP-1-1");
		standardGroup1_1.setName("GROUP 1-1: SYNTHESIS OF SFMS");
		standardGroup1_1.setClassification(standardClass1);
		
		InventiveStandardGroup standardGroup1_2 = new InventiveStandardGroup();
		standardGroup1_2.setGroupNumber("GROUP-1-2");
		standardGroup1_2.setName("GROUP 1-2: DECOMPOSITION OF SFMS");
		standardGroup1_2.setClassification(standardClass1);
		
		groupListStandardClass1.add(standardGroup1_1);
		groupListStandardClass1.add(standardGroup1_2);
		
		// group 2
		InventiveStandardGroup standardGroup2_1 = new InventiveStandardGroup();
		standardGroup2_1.setGroupNumber("GROUP-2-1");
		standardGroup2_1.setName("GROUP 2-1: TRANSITION TO COMPLEX SFMS");
		standardGroup2_1.setClassification(standardClass2);
		
		InventiveStandardGroup standardGroup2_2 = new InventiveStandardGroup();
		standardGroup2_2.setGroupNumber("GROUP-2-2");
		standardGroup2_2.setName("GROUP 2-2: EVOLUTION OF SFMS");
		standardGroup2_2.setClassification(standardClass2);
		
		InventiveStandardGroup standardGroup2_3 = new InventiveStandardGroup();
		standardGroup2_3.setGroupNumber("GROUP-2-3");
		standardGroup2_3.setName("GROUP 2-3: EVOLUTION BY COORDINATING RHYTHMS");
		standardGroup2_3.setClassification(standardClass2);
		
		InventiveStandardGroup standardGroup2_4 = new InventiveStandardGroup();
		standardGroup2_4.setGroupNumber("GROUP-2-4");
		standardGroup2_4.setName("GROUP 2-4: COMPLEX-FORCED SFMS (F-SFMS)");
		standardGroup2_4.setClassification(standardClass2);
		
		groupListStandardClass2.add(standardGroup2_1);
		groupListStandardClass2.add(standardGroup2_2);
		groupListStandardClass2.add(standardGroup2_3);
		groupListStandardClass2.add(standardGroup2_4);
		
		// group 3
		InventiveStandardGroup standardGroup3_1 = new InventiveStandardGroup();
		standardGroup3_1.setGroupNumber("GROUP-3-1");
		standardGroup3_1.setName("GROUP 3-1: TRANSITIONS TO BISYSTEM AND POLYSYSTEM");
		standardGroup3_1.setClassification(standardClass3);
		
		InventiveStandardGroup standardGroup3_2 = new InventiveStandardGroup();
		standardGroup3_2.setGroupNumber("GROUP-3-2");
		standardGroup3_2.setName("GROUP 3-2: TRANSITION TO MICROLEVEL");
		standardGroup3_2.setClassification(standardClass3);
		
		groupListStandardClass3.add(standardGroup3_1);
		groupListStandardClass3.add(standardGroup3_2);
		
		// group 4
		InventiveStandardGroup standardGroup4_1 = new InventiveStandardGroup();
		standardGroup4_1.setGroupNumber("GROUP-4-1");
		standardGroup4_1.setName("GROUP 4-1: CHANGE INSTEAD OF MEASUREMENT AND DETECTION");
		standardGroup4_1.setClassification(standardClass4);
		
		InventiveStandardGroup standardGroup4_2 = new InventiveStandardGroup();
		standardGroup4_2.setGroupNumber("GROUP-4-2");
		standardGroup4_2.setName("GROUP 4-2: SYNTHESIS OF MEASUREMENT SYSTEM");
		standardGroup4_2.setClassification(standardClass4);
		
		InventiveStandardGroup standardGroup4_3 = new InventiveStandardGroup();
		standardGroup4_3.setGroupNumber("GROUP-4-3");
		standardGroup4_3.setName("GROUP 4-3: IMPROVEMENT OF MEASUREMENT SYSTEMS");
		standardGroup4_3.setClassification(standardClass4);
		
		InventiveStandardGroup standardGroup4_4 = new InventiveStandardGroup();
		standardGroup4_4.setGroupNumber("GROUP-4-4");
		standardGroup4_4.setName("GROUP 4-4: TRANSITION TO FERROMAGNETIC MEASUREMENT SYSTEMS");
		standardGroup4_4.setClassification(standardClass4);
		
		InventiveStandardGroup standardGroup4_5 = new InventiveStandardGroup();
		standardGroup4_5.setGroupNumber("GROUP-4-5");
		standardGroup4_5.setName("GROUP 4-5: EVOLUTION OF MEASUREMENT SYSTEMS");
		standardGroup4_5.setClassification(standardClass4);
		
		groupListStandardClass4.add(standardGroup4_1);
		groupListStandardClass4.add(standardGroup4_2);
		groupListStandardClass4.add(standardGroup4_3);
		groupListStandardClass4.add(standardGroup4_4);
		groupListStandardClass4.add(standardGroup4_5);
		
		// group 5
		InventiveStandardGroup standardGroup5_1 = new InventiveStandardGroup();
		standardGroup5_1.setGroupNumber("GROUP-5-1");
		standardGroup5_1.setName("GROUP 5-1: INTRODUCTION OF SUBSTANCES UNDER RESTRICTED CONDITIONS");
		standardGroup5_1.setClassification(standardClass5);
		
		InventiveStandardGroup standardGroup5_2 = new InventiveStandardGroup();
		standardGroup5_2.setGroupNumber("GROUP-5-2");
		standardGroup5_2.setName("GROUP 5-2: INTRODUCTION OF FIELDS UNDER RESTRICTED CONDITIONS");
		standardGroup5_2.setClassification(standardClass5);
		
		InventiveStandardGroup standardGroup5_3 = new InventiveStandardGroup();
		standardGroup5_3.setGroupNumber("GROUP-5-3");
		standardGroup5_3.setName("GROUP 5-3: USE OF PHASE TRANSITIONS");
		standardGroup5_3.setClassification(standardClass5);
		
		InventiveStandardGroup standardGroup5_4 = new InventiveStandardGroup();
		standardGroup5_4.setGroupNumber("GROUP-5-4");
		standardGroup5_4.setName("GROUP 5-4: USE OF PHYSICAL EFFECTS");
		standardGroup5_4.setClassification(standardClass5);
		
		InventiveStandardGroup standardGroup5_5 = new InventiveStandardGroup();
		standardGroup5_5.setGroupNumber("GROUP-5-5");
		standardGroup5_5.setName("GROUP 5-5: OBTAINING SUBSTANCE PARTICLES");
		standardGroup5_5.setClassification(standardClass5);
		
		groupListStandardClass5.add(standardGroup5_1);
		groupListStandardClass5.add(standardGroup5_2);
		groupListStandardClass5.add(standardGroup5_3);
		groupListStandardClass5.add(standardGroup5_4);
		groupListStandardClass5.add(standardGroup5_5);
		
		standardClass1.setGroupsList(groupListStandardClass1);
		standardClass2.setGroupsList(groupListStandardClass2);
		standardClass3.setGroupsList(groupListStandardClass3);
		standardClass4.setGroupsList(groupListStandardClass4);
		standardClass5.setGroupsList(groupListStandardClass5);
		
		algorithmStandardsService.persistInventiveStandardClass(standardClass1);
		algorithmStandardsService.persistInventiveStandardClass(standardClass2);
		algorithmStandardsService.persistInventiveStandardClass(standardClass3);
		algorithmStandardsService.persistInventiveStandardClass(standardClass4);
		algorithmStandardsService.persistInventiveStandardClass(standardClass5);		
	}
	
}
