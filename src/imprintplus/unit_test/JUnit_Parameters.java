package imprintplus.unit_test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import imprintplus.experiment.Commons;
import imprintplus.experiment.ImprintParamIllegalValue;
import imprintplus.experiment.ImprintParamMissing;
import imprintplus.experiment.Parameters;

import org.junit.Before;
import org.junit.Test;

public class JUnit_Parameters {

	Parameters test_params;
	
	@Before
	public void setUp() throws Exception {
		String param_file_path = Commons.PATH_DEFAULT_EXPERIMENT + "/junit/params_exp_junit_test1.csv"; 
		File param_file = new File(param_file_path);
		test_params = new Parameters(param_file);
	}

	@Test
	public void testGetParamValAsString() {
		try {
			String exp_experiment_name = "Junit_Test1";
			String obs_experiment_name = test_params.getParamValAsString(Parameters.PARAM_EXP_NAME);
			
			String exp_data_rel_path = "junit";
			String obs_data_rel_path = test_params.getParamValAsString(Parameters.PARAM_DATA_REL_PATH);
			
			String exp_data_filename = "junit_longitudinaltable_test1.csv";
			String obs_data_filename = test_params.getParamValAsString(Parameters.PARAM_DATA_FILENAME);
			
			String exp_att_id = "identifier";
			String obs_att_id = test_params.getParamValAsString(Parameters.PARAM_ATT_ID);
			
			String exp_att_label = "class_label";
			String obs_att_label = test_params.getParamValAsString(Parameters.PARAM_ATT_LABEL);
			
			String exp_att_time = "time";
			String obs_att_time = test_params.getParamValAsString(Parameters.PARAM_ATT_TIMEPOINT);
			
			String exp_csv_separator = ",";
			String obs_csv_separator = test_params.getParamValAsString(Parameters.PARAM_CSV_SEP);
			
			assertEquals("Incorrect experiment name.", exp_experiment_name, obs_experiment_name);
			assertEquals("Incorrect relative data path.", exp_data_rel_path, obs_data_rel_path);
			assertEquals("Incorrect data file.", exp_data_filename, obs_data_filename);
			assertEquals("Incorrect id att.", exp_att_id, obs_att_id);
			assertEquals("Incorrect label att.", exp_att_label, obs_att_label);
			assertEquals("Incorrect timepoint att.", exp_att_time, obs_att_time);
			assertEquals("Incorrect csv separator.", exp_csv_separator, obs_csv_separator);
		} catch (ImprintParamMissing e) {
			System.out.println(e);
			fail("Unexpected exception");
		}
		
		try {
			test_params.getParamValAsString("unknown parameter");
			fail("An exception was expected.");
		} catch (ImprintParamMissing e) {
			assertTrue(true);
		}
		
		try {
			test_params.getParamValAsString("exp name");
			fail("An exception was expected.");
		} catch (ImprintParamMissing e) {
			assertTrue(true);
		}
		
		try {
			test_params.getParamValAsString("exp_name");
			assertTrue(true);
		} catch (ImprintParamMissing e) {
			fail("Unexpected Exception.");
		}
	}

	@Test
	public void testGetParamValAsInteger() {
		try {
			int exp_runs = 100;
			int obs_runs = test_params.getParamValAsInteger(Parameters.PARAM_RUNS);
			
			int exp_K = 4;
			int obs_K = test_params.getParamValAsInteger(Parameters.PARAM_K);
					
			assertEquals("Incorrect value for runs.", exp_runs, obs_runs);
			assertEquals("Incorrect value for K.", exp_K, obs_K);
		} catch (Exception e) {
			System.out.println(e);
			fail("Unexpected exception");
		}
		
		try {
			test_params.getParamValAsInteger("unknown parameter");
			fail("An exception was expected.");
		} catch (ImprintParamMissing e) {
			assertTrue(true);
		} catch (ImprintParamIllegalValue e) {
			fail("Incorrect Exception.");
		}
		
		try {
			test_params.getParamValAsInteger("exp name");
			fail("An exception was expected.");
		} catch (ImprintParamMissing e) {
			assertTrue(true);
		} catch (ImprintParamIllegalValue e) {
			fail("Incorrect Exception.");
		}
		
		try {
			test_params.getParamValAsInteger("runs");
			assertTrue(true);
		} catch (ImprintParamMissing e) {
			fail("Unexpected Exception.");
		} catch (ImprintParamIllegalValue e) {
			fail("Unexpected Exception.");
		}
	}

	@Test
	public void testGetParamValAsDouble() {
		double delta = 0.001;
		try {
			double exp_sample_ratio = 0.555;
			double obs_sample_ratio = test_params.getParamValAsDouble(Parameters.PARAM_SAMPLE_RATIO);
			
			assertEquals("Incorrect value for sample ratio.", exp_sample_ratio, obs_sample_ratio, delta);
		} catch (Exception e) {
			System.out.println(e);
			fail("Unexpected exception");
		}
		
		try {
			test_params.getParamValAsDouble("unknown parameter");
			fail("An exception was expected.");
		} catch (ImprintParamMissing e) {
			assertTrue(true);
		} catch (ImprintParamIllegalValue e) {
			fail("Incorrect Exception.");
		}
		
		try {
			test_params.getParamValAsDouble("exp name");
			fail("An exception was expected.");
		} catch (ImprintParamMissing e) {
			assertTrue(true);
		} catch (ImprintParamIllegalValue e) {
			fail("Incorrect Exception.");
		}
		
		try {
			test_params.getParamValAsDouble("sample_ratio");
			assertTrue(true);
		} catch (ImprintParamMissing e) {
			fail("Unexpected Exception.");
		} catch (ImprintParamIllegalValue e) {
			fail("Unexpected Exception.");
		}
	}

	@Test
	public void testGetParamValAsBoolean() {
		try {
			boolean exp_duplicates = true;
			boolean obs_duplicates = test_params.getParamValAsBoolean(Parameters.PARAM_DUPLICATES);
			
			assertEquals("Incorrect value for sample ratio.", exp_duplicates, obs_duplicates);
		} catch (Exception e) {
			System.out.println(e);
			fail("Unexpected exception");
		}
		
		try {
			test_params.getParamValAsBoolean("unknown parameter");
			fail("An exception was expected.");
		} catch (ImprintParamMissing e) {
			assertTrue(true);
		} catch (ImprintParamIllegalValue e) {
			fail("Incorrect Exception.");
		}
		
		try {
			test_params.getParamValAsBoolean("exp name");
			fail("An exception was expected.");
		} catch (ImprintParamMissing e) {
			assertTrue(true);
		} catch (ImprintParamIllegalValue e) {
			fail("Incorrect Exception.");
		}
		
		try {
			test_params.getParamValAsBoolean("duplicates");
			assertTrue(true);
		} catch (ImprintParamMissing e) {
			fail("Unexpected Exception.");
		} catch (ImprintParamIllegalValue e) {
			fail("Unexpected Exception.");
		}
	}

	@Test
	public void testGetParamValAsArrayList() {
		try {
			ArrayList<String> exp_ignore = new ArrayList<String>();
			exp_ignore.add("ig1");
			ArrayList<String> obs_ignored = test_params.getParamValAsArrayList(Parameters.PARAM_IGNORE_LIST);
			
			assertEquals("Ignore attribute list do not match.", exp_ignore, obs_ignored);
		} catch (Exception e) {
			System.out.println(e);
			fail("Unexpected exception");
		}
		
		try {
			test_params.getParamValAsArrayList("unknown list");
			fail("An exception was expected.");
		} catch (ImprintParamMissing e) {
			assertTrue(true);
		}
		
		try {
			test_params.getParamValAsArrayList("exp name");
			fail("An exception was expected.");
		} catch (ImprintParamMissing e) {
			assertTrue(true);
		}
		
		try {
			test_params.getParamValAsArrayList("duplicates");
			assertTrue(true);
		} catch (ImprintParamMissing e) {
			fail("Unexpected Exception.");
		}
	}

	@Test
	public void testGetExperimentName() {
		String exp_experiment_name = "Junit_Test1";
		String obs_experiment_name = test_params.getExperimentName();
		assertEquals("Incorrect experiment name.", exp_experiment_name, obs_experiment_name);
	}

}
