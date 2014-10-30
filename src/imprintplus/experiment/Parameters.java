package imprintplus.experiment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class stores the information on all the parameters that are in use
 * during an experiment.
 * 
 * @author siddiqui16
 */
public class Parameters {
	/* Name of Parameters */
	public static final String PARAM_EXP_NAME = "exp_name";
	public static final String PARAM_RUNS = "runs";
	public static final String PARAM_SAMPLE_RATIO = "sample_ratio";
	public static final String PARAM_DUPLICATES = "duplicates";
	public static final String PARAM_K = "K";
	public static final String PARAM_DATA_REL_PATH = "data_rel_path";
	public static final String PARAM_DATA_FILENAME = "data_filename";
	public static final String PARAM_ATT_ID = "att_id";
	public static final String PARAM_ATT_LABEL = "att_label";
	public static final String PARAM_ATT_TIMEPOINT = "att_timepoint";
	public static final String PARAM_IGNORE_LIST = "ignore_list";
	public static final String PARAM_CSV_SEP = "csv_separator";

	/* Default value of Parameters */
	public static final String PARAM_DEFAULT_RUNS = "1";
	public static final String PARAM_DEFAULT_SAMPLE_RATIO = "0.85";
	public static final String PARAM_DEFAULT_DUPLICATES = "false";
	public static final String PARAM_DEFAULT_DATA_REL_PATH = "./data";
	public static final String PARAM_DEFAULT_CSV_SEP = ",";

	/** Stores <param,val> pairs as String. */
	Map<String, String> param_vals;

	/**
	 * The constructor should be kept hidden. The object creation should be
	 * done via second constructor.
	 */
	protected Parameters() {
		param_vals = new HashMap<String, String>();
		initDefaults();
	}

	/**
	 * Read parameters from the provided File and create Parameter Objects
	 */
	public Parameters(File _file) throws ImprintParamFileException {
		this();
		this.readFromFile(_file);
	}

	/**
	 * This reads the parameter and returns the value of the parameter as
	 * String.
	 * 
	 * TODO: What happens if a parameter with the provided name doesn't
	 * exist? Implement the test case for the implementation.
	 */
	public String getParamValAsString(String _param) {
		return param_vals.get(_param);
	}

	/**
	 * This reads the parameter and returns the value of the parameter as
	 * Integer.
	 * 
	 * TODO: What happens if a parameter with the provided name doesn't
	 * exist? Implement the test case for the implementation.
	 * 
	 * TODO: What happens if value can't be type-casted as Integer?
	 * Implement the test case.
	 */
	public Integer getParamValAsInteger(String _param) {
		return Integer.valueOf(param_vals.get(_param));
	}

	/**
	 * This reads the parameter and returns the value of the parameter as
	 * Double.
	 * 
	 * TODO: What happens if a parameter with the provided name doesn't
	 * exist? Implement the test case for the implementation.
	 * 
	 * TODO: What happens if value can't be type-casted as Double? Implement
	 * the test case.
	 */
	public Double getParamValAsDouble(String _param) {
		return Double.valueOf(param_vals.get(_param));
	}

	/**
	 * This reads the parameter and returns the value of the parameter as
	 * Boolean.
	 * 
	 * TODO: What happens if a parameter with the provided name doesn't
	 * exist? Implement the test case for the implementation.
	 * 
	 * TODO: What happens if value can't be type-casted as Boolean?
	 * Implement the test case.
	 */
	public Boolean getParamValAsBoolean(String _param) {
		return Boolean.valueOf(param_vals.get(_param));
	}

	/**
	 * This reads the parameter and returns the value of the parameter as a
	 * list. The individual values in the parameter must be separate by a
	 * comma.
	 * 
	 * TODO: What happens if a parameter with the provided name doesn't
	 * exist? Implement the test case for the implementation.
	 */
	public ArrayList<String> getParamValAsArrayList(String _param) {
		String vals = param_vals.get(_param);
		ArrayList<String> val_list = new ArrayList<String>();
		Collections.addAll(val_list, vals.split(Commons.PARAM_LIST_SEP));
		return val_list;
	}

	/**
	 * Initializes the default values of the mandatory parameters.
	 */
	protected void initDefaults() {
		param_vals.put(PARAM_RUNS, PARAM_DEFAULT_RUNS);
		param_vals.put(PARAM_SAMPLE_RATIO, PARAM_DEFAULT_SAMPLE_RATIO);
		param_vals.put(PARAM_DUPLICATES, PARAM_DEFAULT_DUPLICATES);
		param_vals.put(PARAM_DATA_REL_PATH, PARAM_DEFAULT_DATA_REL_PATH);
		param_vals.put(PARAM_CSV_SEP, PARAM_DEFAULT_CSV_SEP);
	}

	/**
	 * Reads the paramters and their values from the provided file and
	 * stores them in param->val Map.
	 * 
	 * @throws ImprintDataFileException
	 */
	protected void readFromFile(File _file)
			throws ImprintParamFileException {
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(_file));
			while ((line = br.readLine()) != null) {
				// If line starts with a comment character, then
				// ignore the line
				if (!line.startsWith(Commons.PARAM_LINE_COMMENT)) {
					String[] vals = line
							.split(Commons.PARAM_VALUE_SEP);

					// Make sure that each line is the form
					// of key-value pair
					if (vals.length != 2)
						System.err.print("\n"
								+ "WARNING: Invalid parameter info @ line <"
								+ line
								+ "> in file "
								+ _file.getAbsolutePath());
					String name = vals[0];
					String value = vals[2];
					param_vals.put(name, value);
				}
			}
		} catch (FileNotFoundException e) {
			String exp_msg = "Parameters file or path doesn't exist: "
					+ _file.getAbsolutePath();
			throw new ImprintParamFileException(exp_msg);
		} catch (IOException e) {
			String exp_msg = "Cannot read from Parameters file: "
					+ _file.getAbsolutePath();
			throw new ImprintParamFileException(exp_msg);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
