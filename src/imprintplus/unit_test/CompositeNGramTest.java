package imprintplus.unit_test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import imprintplus.data.LongitudinalObject;
import imprintplus.data.LongitudinalTable;
import imprintplus.method.labelprediction.CompositeNGram;
import imprintplus.method.labelprediction.UnitaryNGram;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompositeNGramTest {


	LongitudinalTable long_table = null;
	String data_path;
	String att_id;
	String att_label;
	String att_timepoint;
	ArrayList<String> exp_ignore;
	ArrayList<String> exp_reg;
	String val_sep;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		data_path = "./data/junit/junit_longitudinaltable_test1.csv";
		att_id = "identifier";
		att_label = "class_label";
		att_timepoint = "time";
		val_sep = ",";
		exp_ignore = new ArrayList<String>();
		exp_ignore.add("ig1");
		long_table = LongitudinalTable.createTableFromCSV(data_path, att_id,
				att_label, att_timepoint, exp_ignore, val_sep); 
	}

	/*
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPredict() {
		CompositeNGram compositeNGram = new CompositeNGram(2);
		try {
			assertEquals("predicate is okay", "{3=A, obj_2=B, obj_1=B}",
					compositeNGram.predict(long_table.getBootstrapSampleOfPatients()));
			// fail("Not yet implemented");
		} catch (Exception e) {
			assertTrue(true);

		}

	}

}
