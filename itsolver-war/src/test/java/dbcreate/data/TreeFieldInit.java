package dbcreate.data;


import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.TreeField;
import itsolver.service.SuFieldService;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TreeFieldInit {
	
	// log
	protected static Logger log = Logger.getLogger("dbcreate/TreeFieldInit");
	static {
		log.setLevel(Level.INFO);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void persistTreeField()throws Exception {
		// arbol de campos
		
		// nodo principal
		TreeField rootElement = new TreeField();	
		rootElement.setName("AllFields");
		List<TreeField> rootList = new LinkedList<TreeField>();
		
		// nodo mechanical
		TreeField mechanical = new TreeField();
		mechanical.setName("Mechanical");
		mechanical.setParentField(rootElement);
		List<TreeField> mechanicalList = new LinkedList<TreeField>();
		
		// nodo thermal
		TreeField thermal = new TreeField();
		thermal.setName("Thermal");
		thermal.setParentField(rootElement);
		List<TreeField> thermalList = new LinkedList<TreeField>();
		
		// nodo pressure
		TreeField pressure = new TreeField();
		pressure.setName("Pressure");
		pressure.setParentField(rootElement);
		List<TreeField> pressureList = new LinkedList<TreeField>();
		
		// nodo chemical
		TreeField chemical = new TreeField();
		chemical.setName("Chemical");
		chemical.setParentField(rootElement);
		List<TreeField> chemicalList = new LinkedList<TreeField>();
		
		// nodo Electrical
		TreeField electrical = new TreeField();
		electrical.setName("Electrical");
		electrical.setParentField(rootElement);
		List<TreeField> electricalList = new LinkedList<TreeField>();
		
		// nodo Magnetic
		TreeField magnetic = new TreeField();
		magnetic.setName("Magnetic");
		magnetic.setParentField(rootElement);
		List<TreeField> magneticList = new LinkedList<TreeField>();
		
		// nodo Weak nuclear interaction
		TreeField weakNuclearInteraction = new TreeField();
		weakNuclearInteraction.setName("Weak Nuclear Interaction");
		weakNuclearInteraction.setParentField(rootElement);
		List<TreeField> weakNuclearInteractionList = new LinkedList<TreeField>();
		
		// nodo Strong nuclear interaction
		TreeField strongNuclearInteraction = new TreeField();
		strongNuclearInteraction.setName("Strong Nuclear Interaction");
		strongNuclearInteraction.setParentField(rootElement);
		List<TreeField> strongNuclearInteractionList = new LinkedList<TreeField>();
		
		// nodo Optical
		TreeField optical = new TreeField();
		optical.setName("Optical");
		optical.setParentField(rootElement);
		List<TreeField> opticalList = new LinkedList<TreeField>();
		
		// nodo Acoustic
		TreeField acoustic = new TreeField();
		acoustic.setName("Acoustic");
		acoustic.setParentField(rootElement);
		List<TreeField> acousticList = new LinkedList<TreeField>();
		
		// nodo Odour
		TreeField odour = new TreeField();
		odour.setName("Odour");
		odour.setParentField(rootElement);
		List<TreeField> odourList = new LinkedList<TreeField>();
		
		// nodo Biological
		TreeField biological = new TreeField();
		biological.setName("Biological");
		biological.setParentField(rootElement);
		List<TreeField> biologicalList = new LinkedList<TreeField>();

		// hijos - mechanical
		TreeField vibrationForce = new TreeField();
		vibrationForce.setName("Vibration Force");
		vibrationForce.setParentField(mechanical);
		
		TreeField frictionalForce = new TreeField();
		frictionalForce.setName("Frictional Force");
		frictionalForce.setParentField(mechanical);
		
		TreeField centrifugalForce = new TreeField();
		centrifugalForce.setName("Centrifugal Force");
		centrifugalForce.setParentField(mechanical);
		
		TreeField inertialForce = new TreeField();
		inertialForce.setName("Inertial Force");
		inertialForce.setParentField(mechanical);
		
		TreeField gravitationalForce = new TreeField();
		gravitationalForce.setName("Gravitational Force");
		gravitationalForce.setParentField(mechanical);
		
		TreeField coriolisForce = new TreeField();
		coriolisForce.setName("Coriolis Force");
		coriolisForce.setParentField(mechanical);
		
		TreeField tensionForce = new TreeField();
		tensionForce.setName("Tension Force");
		tensionForce.setParentField(mechanical);
		
		TreeField compressionForce = new TreeField();
		compressionForce.setName("Compression Force");
		compressionForce.setParentField(mechanical);
		
		TreeField elasticityForce = new TreeField();
		elasticityForce.setName("Elasticity Force");
		elasticityForce.setParentField(mechanical);
		
		TreeField reactionForce = new TreeField();
		reactionForce.setName("Reaction Force");
		reactionForce.setParentField(mechanical);
		
		// se agrupan los hijos - mechanical
		mechanicalList.add(vibrationForce);
		mechanicalList.add(frictionalForce);
		mechanicalList.add(centrifugalForce);
		mechanicalList.add(inertialForce);
		mechanicalList.add(gravitationalForce);
		mechanicalList.add(coriolisForce);
		mechanicalList.add(tensionForce);
		mechanicalList.add(compressionForce);
		mechanicalList.add(elasticityForce);
		mechanicalList.add(reactionForce);
		
		// hijos - thermal
		TreeField thermalRadiation = new TreeField();
		thermalRadiation.setName("Thermal radiation");
		thermalRadiation.setParentField(thermal);
		
		TreeField convection = new TreeField();
		convection.setName("Convection");
		convection.setParentField(thermal);
		
		TreeField heatConduction = new TreeField();
		heatConduction.setName("Heat conduction");
		heatConduction.setParentField(thermal);
		
		TreeField staticTemperatureGradient = new TreeField();
		staticTemperatureGradient.setName("Static temperature gradient");
		staticTemperatureGradient.setParentField(thermal);
		
		TreeField totalTemperatureGradient = new TreeField();
		totalTemperatureGradient.setName("Total temperature gradient");
		totalTemperatureGradient.setParentField(thermal);
		
		// se agrupan los hijos - thermal
		thermalList.add(thermalRadiation);
		thermalList.add(convection);
		thermalList.add(heatConduction);
		thermalList.add(staticTemperatureGradient);
		thermalList.add(totalTemperatureGradient);
				
		// hijos - Pressure
		TreeField buoyancyForce = new TreeField();
		buoyancyForce.setName("Buoyancy force");
		buoyancyForce.setParentField(pressure);
		
		TreeField staticPressure = new TreeField();
		staticPressure.setName("Static pressure");
		staticPressure.setParentField(pressure);
		
		TreeField dynamicPressure = new TreeField();
		dynamicPressure.setName("Dynamic pressure");
		dynamicPressure.setParentField(pressure);
		
		TreeField pressureGradient = new TreeField();
		pressureGradient.setName("Pressure gradient");
		pressureGradient.setParentField(pressure);
		
		TreeField lift = new TreeField();
		lift.setName("Lift");
		lift.setParentField(pressure);
		
		TreeField magnusForce = new TreeField();
		magnusForce.setName("Magnus force");
		magnusForce.setParentField(pressure);
		
		TreeField vaccum = new TreeField();
		vaccum.setName("Vaccum");
		vaccum.setParentField(pressure);
		
		// se agrupan los hijos - Pressure
		pressureList.add(buoyancyForce);
		pressureList.add(staticPressure);
		pressureList.add(dynamicPressure);
		pressureList.add(pressureGradient);
		pressureList.add(lift);
		pressureList.add(magnusForce);
		pressureList.add(vaccum);

		// hijos - Chemical
		TreeField diffusion = new TreeField();
		diffusion.setName("Diffusion");
		diffusion.setParentField(chemical);
		
		TreeField combustion = new TreeField();
		combustion.setName("Combustion");
		combustion.setParentField(chemical);
				
		TreeField reduction = new TreeField();
		reduction.setName("Reduction");
		reduction.setParentField(chemical);
				
		TreeField oxidation = new TreeField();
		oxidation.setName("Oxidation");
		oxidation.setParentField(chemical);
				
		// se agrupan los hijos - Chemical
		chemicalList.add(diffusion);
		chemicalList.add(combustion);
		chemicalList.add(reduction);
		chemicalList.add(oxidation);

		// hijos - Electrical
		TreeField electrostatic = new TreeField();
		electrostatic.setName("Electrostatic");
		electrostatic.setParentField(electrical);
						
		TreeField electrodynamic = new TreeField();
		electrodynamic.setName("Electrodynamic");
		electrodynamic.setParentField(electrical);
						
		TreeField alternatingElectric = new TreeField();
		alternatingElectric.setName("Alternating electric");
		alternatingElectric.setParentField(electrical);
						
		TreeField electrophoretic = new TreeField();
		electrophoretic.setName("Electrophoretic");
		electrophoretic.setParentField(electrical);
						
		TreeField electromagneticRadiation = new TreeField();
		electromagneticRadiation.setName("Electromagnetic radiation");
		electromagneticRadiation.setParentField(electrical);
						
		// se agrupan los hijos - Electrical
		electricalList.add(electrostatic);
		electricalList.add(electrodynamic);
		electricalList.add(alternatingElectric);
		electricalList.add(electrophoretic);
		electricalList.add(electromagneticRadiation);

		// hijos - Magnetic
		TreeField alternatingMagnetic = new TreeField();
		alternatingMagnetic.setName("Alternating magnetic");
		alternatingMagnetic.setParentField(magnetic);
						
		// se agrupan los hijos - Magnetic
		magneticList.add(alternatingMagnetic);

		// hijos - Weak nuclear interaction
		// hijos - Strong nuclear interaction
		// hijos - Optical
		
		// hijos - Acoustic
		TreeField sound = new TreeField();
		sound.setName("Sound");
		sound.setParentField(acoustic);
						
		TreeField ultrasound = new TreeField();
		ultrasound.setName("Ultrasound");
		ultrasound.setParentField(acoustic);
						
		// se agrupan los hijos - Magnetic
		acousticList.add(sound);
		acousticList.add(ultrasound);

		// hijos - Odour
		
		// hijos - Biological
		TreeField osmotic = new TreeField();
		osmotic.setName("Osmotic");
		osmotic.setParentField(biological);
						
		TreeField catabolic = new TreeField();
		catabolic.setName("Catabolic");
		catabolic.setParentField(biological);
						
		TreeField anabolic = new TreeField();
		anabolic.setName("Anabolic");
		anabolic.setParentField(biological);
						
		TreeField photosynthesis = new TreeField();
		photosynthesis.setName("Photosynthesis");
		photosynthesis.setParentField(biological);
						
		TreeField enzymeAction = new TreeField();
		enzymeAction.setName("Enzyme action");
		enzymeAction.setParentField(biological);
						
		// se agrupan los hijos - Biological
		biologicalList.add(osmotic);
		biologicalList.add(catabolic);
		biologicalList.add(anabolic);
		biologicalList.add(photosynthesis);
		biologicalList.add(enzymeAction);

		// se agregan los hijos a los nodos
		mechanical.setSubFields(mechanicalList);
		thermal.setSubFields(thermalList);
		pressure.setSubFields(pressureList);
		chemical.setSubFields(chemicalList);
		electrical.setSubFields(electricalList);
		magnetic.setSubFields(magneticList);
		//listas vacias!
		weakNuclearInteraction.setSubFields(weakNuclearInteractionList);
		strongNuclearInteraction.setSubFields(strongNuclearInteractionList);
		optical.setSubFields(opticalList);
		//
		acoustic.setSubFields(acousticList);
		//listas vacias
		odour.setSubFields(odourList);
		//
		biological.setSubFields(biologicalList);

		// se agregan los nodos a la raiz
		rootList.add(mechanical);
		rootList.add(thermal);
		rootList.add(pressure);
		rootList.add(chemical);
		rootList.add(electrical);
		rootList.add(magnetic);
		rootList.add(weakNuclearInteraction);
		rootList.add(strongNuclearInteraction);
		rootList.add(optical);
		rootList.add(acoustic);
		rootList.add(odour);
		rootList.add(biological);
		
		rootElement.setSubFields(rootList);
		
		SuFieldService suFieldService = ServiceLocator.getSuFieldService();
		suFieldService.createTreeField(rootElement);
		
		
	}

}
