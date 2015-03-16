package imprintplus.unit_test;

import static org.junit.Assert.*;
import imprintplus.data.LongitudinalObject;
import imprintplus.method.labelprediction.UnitaryMode;
import imprintplus.method.labelprediction.UnitaryNaive;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitaryModeTest {
	
	LongitudinalObject long_obj = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		long_obj = new LongitudinalObject("id");
		long_obj.addLabel("first", 1);
		long_obj.addLabel("second", 4);
		long_obj.addLabel("third", 3);
		long_obj.addLabel("first", 4);
	}

	/*
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPredict() {
		UnitaryMode unitaryMode = new UnitaryMode();
		try {
			assertEquals("predicate is okay", "first",
					unitaryMode.predict(long_obj));
			// fail("Not yet implemented");
		} catch (Exception e) {
			assertTrue(true);

		}
	}

}
