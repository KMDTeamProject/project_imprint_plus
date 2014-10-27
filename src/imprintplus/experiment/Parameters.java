package imprintplus.experiment;

import java.util.HashMap;
import java.util.Map;

/**
 * This class stores the information on all the parameters
 * that are in use during an experiment.
 * 
 * @author siddiqui16
 */
public class Parameters {
	public static final String PARAM_EXP_NAME      = "exp_name";
	public static final String PARAM_RUNS          = "runs";
	public static final String PARAM_SAMPLE_RATIO  = "sampleRatio";
	public static final String PARAM_DUPLICATES    = "duplicates";
	public static final String PARAM_K             = "K";
	public static final String PARAM_DATA_REL_PATH = "dataRelPath";
	public static final String PARAM_DATA_FILE     = "dataFile";
	public static final String PARAM_ATT_ID        = "att_id";
	public static final String PARAM_ATT_LABEL     = "att_label";
	public static final String PARAM_ATT_TIMEPOINT = "att_timepoint";
	public static final String PARAM_IGNORE_LIST   = "ignore_list";

	/** Stores <param,val> pairs as String. */
	Map<String, String> param_vals;

	/**
	 * The constructor should be kept hidden. The object creation
	 * should be done via a static method.
	 */
	protected Parameters() {
		param_vals = new HashMap<String, String>();
		initDefaults();
	};

	/**
	 * This reads the parameter and returns the value of the
	 * parameter as String.
	 * 
	 * TODO: What happens if a parameter with the provided
	 * name doesn't exist? Implement the test case for the implementation.
	 */
	public String getParamValAsString(String _param) {
		return param_vals.get(_param);
	}

	/**
	 * This reads the parameter and returns the value of the
	 * parameter as Integer.
	 * 
	 * TODO: 
	 * What happens if a parameter with the provided name 
	 * doesn't exist? Implement the test case for the implementation.
	 * 
	 * TODO: 
	 * What happens if value can't be type-casted as Integer?
	 * Implement the test case.
	 */
	public Integer getParamValAsInteger(String _param) {
		return Integer.valueOf(param_vals.get(_param));
	}
	
	/**
	 * This reads the parameter and returns the value of the
	 * parameter as Double.
	 * 
	 * TODO: 
	 * What happens if a parameter with the provided name 
	 * doesn't exist? Implement the test case for the implementation.
	 * 
	 * TODO: 
	 * What happens if value can't be type-casted as Double?
	 * Implement the test case.
	 */
	public Double getParamValAsDouble(String _param) {
		return Double.valueOf(param_vals.get(_param));
	}
	
	/**
	 * This reads the parameter and returns the value of the
	 * parameter as Boolean.
	 * 
	 * TODO: 
	 * What happens if a parameter with the provided name 
	 * doesn't exist? Implement the test case for the implementation.
	 * 
	 * TODO: 
	 * What happens if value can't be type-casted as Boolean?
	 * Implement the test case.
	 */
	public Boolean getParamValAsBoolean(String _param) {
		return Boolean.valueOf(param_vals.get(_param));
	}
	
	/**
	 * Initialises the default values of the mandatory parameters.
	 */
	protected void initDefaults() {
		param_vals.put(PARAM_RUNS, "1");
		param_vals.put(PARAM_SAMPLE_RATIO, "0.85");
		param_vals.put(PARAM_DUPLICATES, "false");
		param_vals.put(PARAM_DATA_REL_PATH, "./data");
	}

	/**
	 * Reads the paramters and their values from the provided file and stores
	 * them in param->val Map.
	 */
	public void readFromFile(String _path) {
		// TODO Implementations
	}
}
