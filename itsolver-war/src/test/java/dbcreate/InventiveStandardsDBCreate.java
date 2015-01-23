package dbcreate;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dbcreate.data.inventivestandards.AlgorithmUseInventiveStandardsInit;
import dbcreate.data.inventivestandards.InventiveStandardsClass1Init;
import dbcreate.data.inventivestandards.InventiveStandardsClass2Init;
import dbcreate.data.inventivestandards.InventiveStandardsClass3Init;
import dbcreate.data.inventivestandards.InventiveStandardsClass4Init;
import dbcreate.data.inventivestandards.InventiveStandardsClass5Init;
import dbcreate.data.inventivestandards.StandardsClassAndGroupsInit;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	StandardsClassAndGroupsInit.class,
	InventiveStandardsClass1Init.class,
	InventiveStandardsClass2Init.class,
	InventiveStandardsClass3Init.class,
	InventiveStandardsClass4Init.class,
	InventiveStandardsClass5Init.class,
	AlgorithmUseInventiveStandardsInit.class
})
public class InventiveStandardsDBCreate {
	public static Test suite() {
		return new JUnit4TestAdapter(SuiteDBCreate.class);
	}
}
