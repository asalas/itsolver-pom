package dbcreate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dbcreate.test.CBREngineTest;
import dbcreate.test.ContradictionMatrixTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({CBREngineTest.class, ContradictionMatrixTest.class})
public class SuiteDBTest {
	public static Test suite() {
		return new JUnit4TestAdapter(SuiteDBTest.class);
	}
}
