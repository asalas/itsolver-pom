package dbcreate.data;

import itsolver.controller.utils.ServiceLocator;
import itsolver.model.entity.MeasurementUnit;
import itsolver.model.entity.Profile;
import itsolver.model.entity.Resource;
import itsolver.model.entity.ResourceCategory;
import itsolver.model.entity.User;
import itsolver.service.ProjectMgmtService;
import itsolver.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ResourcesInit {

	// log
	protected static Logger log = Logger.getLogger("");
	static {
		log.setLevel(Level.INFO);
	}
	
	
	@Before
	public void setUp() throws Exception{
		System.out.println("setUp");
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
	}
	
	//@Test 
	public void createUSer(){
		UserService userService = ServiceLocator.getUserService();
		Profile profile = new Profile();
		profile.setName("Itsolver");
		profile.setLastName("TRIZ");
		
		User user = new User();		
		user.setProfile(profile);
		user.setEmail("user@mail.com");
		user.setUserName("user");
		user.setPassword(DigestUtils.md5Hex("user"));
		
		profile.setUser(user);
		boolean isRegistered = userService.registerUser(user);
	}
	
	
	
	
	@Test
	public void persistResources() throws Exception {
		
		ProjectMgmtService projectMgmtService = ServiceLocator.getProjectMgmtService();
		ResourceCategory resourceCategoryTree = new ResourceCategory();
		ResourceCategory categorySubstances = new ResourceCategory();
		resourceCategoryTree.setCategoryName("ResourceCategoryTree");
		resourceCategoryTree.setSubCategoryList(new ArrayList<ResourceCategory>());		
		categorySubstances.setCategoryName("Substances");
		categorySubstances.setParentCategory(resourceCategoryTree);
		resourceCategoryTree.getSubCategoryList().add(categorySubstances);
		
		categorySubstances.setSubCategoryList(new ArrayList<ResourceCategory>());
		ResourceCategory categoryPropertiesSubstance = new ResourceCategory("Properties of the substance");
		categoryPropertiesSubstance.setParentCategory(categorySubstances);
		categorySubstances.getSubCategoryList().add(categoryPropertiesSubstance);
		
		categoryPropertiesSubstance.setResourceList(new ArrayList<Resource>());
		categoryPropertiesSubstance.getResourceList().add(new Resource("Stiffness of steel", MeasurementUnit.NA));
		categoryPropertiesSubstance.getResourceList().add(new Resource("Ferromagnetic of steel", MeasurementUnit.KILO));
		

		
		ResourceCategory categoryFields = new ResourceCategory();
		categoryFields.setParentCategory(resourceCategoryTree);
		categoryFields.setCategoryName("Fields");
		resourceCategoryTree.getSubCategoryList().add(categoryFields);
		
		categoryFields.setSubCategoryList(new ArrayList<ResourceCategory>());
		ResourceCategory categoryMechanicals = new ResourceCategory("Mechanicals");
		categoryMechanicals.setParentCategory(categoryFields);
		categoryFields.getSubCategoryList().add(categoryMechanicals);
		
		categoryMechanicals.setResourceList(new ArrayList<Resource>());
		categoryMechanicals.getResourceList().add(new Resource("Pressure Kg/Cm2 estimated 10 kg/cm2", MeasurementUnit.NA));
		categoryMechanicals.getResourceList().add(new Resource("Friction", MeasurementUnit.NA));
		categoryMechanicals.getResourceList().add(new Resource("Sonic", MeasurementUnit.NA));
		
		
		ResourceCategory categorySpace = new ResourceCategory();
		categorySpace.setParentCategory(resourceCategoryTree);
		categorySpace.setCategoryName("Space");
		resourceCategoryTree.getSubCategoryList().add(categorySpace);
		categorySpace.setSubCategoryList(new ArrayList<ResourceCategory>());
		
		ResourceCategory categoryEmpty  = new ResourceCategory("Empty");
		categoryEmpty.setParentCategory(categorySpace);
		categorySpace.getSubCategoryList().add(categoryEmpty);
		ResourceCategory categoryInternalPlacement  = new ResourceCategory("Internal placement");
		categoryInternalPlacement.setParentCategory(categorySpace);
		categorySpace.getSubCategoryList().add(categoryInternalPlacement);
		
		
		
		ResourceCategory categoryTime = new ResourceCategory();
		categoryTime.setParentCategory(resourceCategoryTree);
		categoryTime.setCategoryName("Time");
		resourceCategoryTree.getSubCategoryList().add(categoryTime);
		categoryTime.setSubCategoryList(new ArrayList<ResourceCategory>());
		
		ResourceCategory categoryProgrammingSec  = new ResourceCategory("Programming and sequencing");
		categoryProgrammingSec.setParentCategory(categoryTime);
		categoryTime.getSubCategoryList().add(categoryProgrammingSec);
		
		
		projectMgmtService.resourceCategoryCreate(resourceCategoryTree);
		
	}
	
	@Test
	public void getResourceTree() throws Exception{
		ProjectMgmtService projectMgmtService = ServiceLocator.getProjectMgmtService();
		ResourceCategory resourceCategoryById = projectMgmtService.getResourceTree();
		System.out.println("Size " +resourceCategoryById.getSubCategoryList().size()); 
		List<ResourceCategory> subCategoryList = resourceCategoryById.getSubCategoryList();
	}
}
