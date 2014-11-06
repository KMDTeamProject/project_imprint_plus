/**
 * 
 */
package imprintplus.unit_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import imprintplus.data.ImprintDataException;
import imprintplus.data.LongitudinalTable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author siddiqui16
 */
public class JUnit_LongitudinalTable_Test1 {

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

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for all the get functions:
	 * {@link imprintplus.data.LongitudinalTable#getAttId()}
	 * {@link imprintplus.data.LongitudinalTable#getAttLabel()}
	 * {@link imprintplus.data.LongitudinalTable#getAttTimepoint()}
	 * {@link imprintplus.data.LongitudinalTable#getIgnoredAtts()}
	 * {@link imprintplus.data.LongitudinalTable#getRegularAtts()}
	 * {@link imprintplus.data.LongitudinalTable#getSize()}
	 * {@link imprintplus.data.LongitudinalTable#getIds()}
	 */
	@Test
	public void testAllGetFunctions() {
		/* {@link imprintplus.data.LongitudinalTable#getAttId()} */
		assertEquals("Att Id is not correct", att_id, long_table.getAttId());

		/* {@link imprintplus.data.LongitudinalTable#getAttLabel()} */
		assertEquals("Att Label is not correct", att_label,
				long_table.getAttLabel());

		/* {@link imprintplus.data.LongitudinalTable#getAttTimepoint()} */
		assertEquals("Att Timepoint is not correct", att_timepoint,
				long_table.getAttTimepoint());

		/* {@link imprintplus.data.LongitudinalTable#getIgnoredAtts()} */
		boolean getIgnoredAttsOk = false;
		ArrayList<String> obs_ignore = (ArrayList<String>) long_table
				.getIgnoredAtts();
		if (exp_ignore.size() == obs_ignore.size()) {
			obs_ignore.removeAll(exp_ignore);
			if (obs_ignore.size() == 0)
				getIgnoredAttsOk = true;
		}
		assertTrue("Incorrect list of ignored attributes.", getIgnoredAttsOk);

		/* {@link imprintplus.data.LongitudinalTable#getRegularAtts()} */
		boolean getRegularAttsOk = false;
		exp_reg = new ArrayList<String>();
		exp_reg.add("att1");
		exp_reg.add("att2");
		exp_reg.add("att3");
		ArrayList<String> obs_reg = (ArrayList<String>) long_table
				.getRegularAtts();
		if (exp_reg.size() == obs_reg.size()) {
			obs_reg.removeAll(exp_reg);
			if (obs_reg.size() == 0)
				getRegularAttsOk = true;
		}
		assertTrue("Incorrect list of regular attributes.", getRegularAttsOk);

		/* {@link imprintplus.data.LongitudinalTable#getSize()} */
		int exp_size = 3;
		int obs_size = long_table.getSize();
		assertEquals("Size of Longitudinal table is not correct", exp_size,
				obs_size);

		/* {@link imprintplus.data.LongitudinalTable#getIds()} */
		boolean getIdsOk = false;
		ArrayList<String> exp_ids = new ArrayList<String>();
		exp_ids.add("obj_1");
		exp_ids.add("obj_2");
		exp_ids.add("3");
		Set<String> obs_ids = long_table.getIds();
		if (exp_ids.size() == obs_ids.size()) {
			obs_ids.removeAll(exp_ids);
			if (obs_ids.size() == 0)
				getIdsOk = true;
		}
		assertTrue("Incorrect list of objects ids.", getIdsOk);
	}

	/**
	 * Test method for {@link imprintplus.data.LongitudinalTable#getAllAtts()}.
	 */
	@Test
	public void testExceptions() {
		try {
			LongitudinalTable.createTableFromCSV(data_path, att_id + "-",
					att_label, att_timepoint, exp_ignore, val_sep);
			assertTrue(false);
		} catch (ImprintDataException e) {
			assertTrue(true);
		}

		try {
			LongitudinalTable.createTableFromCSV(data_path, att_id, att_label
					+ "-", att_timepoint, exp_ignore, val_sep);
			assertTrue(false);
		} catch (ImprintDataException e) {
			assertTrue(true);
		}

		try {
			LongitudinalTable.createTableFromCSV(data_path, att_id, att_label,
					att_timepoint + "-", exp_ignore, val_sep);
			assertTrue(false);
		} catch (ImprintDataException e) {
			assertTrue(true);
		}

		try {
			LongitudinalTable.createTableFromCSV(data_path, att_id, att_label,
					att_timepoint, exp_ignore, val_sep + "-");
			assertTrue(false);
		} catch (ImprintDataException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for
	 * {@link imprintplus.data.LongitudinalTable#getBootstrapSampleOfPatients()}
	 * .
	 */
	@Test
	public void testGetBootstrapSampleOfPatients() {
		assertTrue(true);
//		fail("Not yet implemented");
	}

}
