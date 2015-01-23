package dbcreate;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dbcreate.data.ResourcesInit;
import dbcreate.data.TreeFieldInit;

@RunWith(Suite.class)
@Suite.SuiteClasses(
		{ ResourcesInit.class,
			TreeFieldInit.class})
public class SuiteDBCreate {
	public static Test suite() {
		return new JUnit4TestAdapter(SuiteDBCreate.class);
	}
}