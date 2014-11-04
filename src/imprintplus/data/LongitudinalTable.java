package imprintplus.data;

import imprintplus.experiment.Commons;
import imprintplus.experiment.ImprintParamMissing;
import imprintplus.experiment.Parameters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LongitudinalTable stores the data in the form of LongitudinalObjects. This
 * class also comes with the functionality to read data from a csv file.
 * 
 * TODO: See if the data read functionality can be transfered to a separate
 * reader class.
 * 
 * @author siddiqui16
 */
public class LongitudinalTable {

	/** Complete path of the data file. */
	protected String data_filepath;

	/** Name of the attribute to used as identifier. */
	protected String name_att_id;

	/** Name of the attribute to used as label. */
	protected String name_att_label;

	/** Name of the attribute to used as timepoint. */
	protected String name_att_timepoint;

	/** List of attributes in the data file. */
	protected ArrayList<String> list_atts_all;

	/** List of attributes that are to be ignored. */
	protected ArrayList<String> list_atts_ignored;

	/** List of attributes that are used for learning. */
	protected ArrayList<String> list_atts_regular;

	/** Map of LongitudinalObject stored wrt their ids */
	protected Map<String, LongitudinalObject> long_objs;

	/**
	 * Creates a LongitudinalTable from the provided data. The data that must be
	 * supplied to the constructor includes - name of id, label, and timepoint
	 * attributes - list of attributes to be ignored (if any) - location of data
	 * source from where to read the data
	 */
	protected LongitudinalTable(String _data_filepath, String _att_id,
			String _att_label, String _att_timepoint,
			ArrayList<String> _ignore_list, String _val_sep)
			throws ImprintDataException {
		data_filepath = _data_filepath;
		name_att_id = _att_id;
		name_att_label = _att_label;
		name_att_timepoint = _att_timepoint;
		list_atts_ignored = _ignore_list;
		long_objs = new HashMap<String, LongitudinalObject>();
		this.readDataIntoTable(_val_sep);
	}

	/**
	 * Get the name of id attribute
	 * 
	 * @return the value of name_att_id
	 */
	public String getAttId() {
		return name_att_id;
	}

	/**
	 * Get the name of label attribute
	 * 
	 * @return the value of name_att_label
	 */
	public String getAttLabel() {
		return name_att_label;
	}

	/**
	 * Get the name of timepoint attribute
	 * 
	 * @return the value of name_att_timepoint
	 */
	public String getAttTimepoint() {
		return name_att_timepoint;
	}

	/**
	 * Get the list of ignored atts
	 * 
	 * @return the value of list_atts_ignored
	 */
	public Collection<String> getIgnoredAtts() {
		return list_atts_ignored;
	}

	/**
	 * Get the list of attributes
	 * 
	 * @return the value of list_atts_all
	 */
	public Collection<String> getAllAtts() {
		return list_atts_all;
	}

	/**
	 * Get the list of attributes that are to be used in the learning.
	 * 
	 * @return the value of LIST_REGULAR_ATTS
	 */
	public Collection<String> getRegularAtts() {
		return list_atts_regular;
	}

	/**
	 * This function reads the data from the provided data source. Currently
	 * implement to read data only from CSV format.
	 */
	protected void readDataIntoTable(String _val_sep)
			throws ImprintDataException {
		File data_file = new java.io.File(data_filepath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(data_file));
			String line = null;
			int line_no = 0;
			while ((line = br.readLine()) != null) {
				/* Read the attribute names from the header. */
				if (line_no == 0) {
					list_atts_all = new ArrayList<String>();
					for (String att : line.split(_val_sep))
						list_atts_all.add(att);

					/*
					 * Removes all the ignored atts, id, label, and timepoint in
					 * order to create a list of regular atts.
					 */
					Set<String> set_reg = new HashSet<String>();
					set_reg.addAll(list_atts_all);
					set_reg.removeAll(list_atts_ignored);
					set_reg.remove(this.name_att_id);
					set_reg.remove(this.name_att_label);
					set_reg.remove(this.name_att_timepoint);
					list_atts_regular = new ArrayList<String>(set_reg);

					/*
					 * Check if the names of att_id, att_label, att_timepoint,
					 * ignore_list exists in the attributes read from data file
					 */
					if (!list_atts_all.contains(name_att_id)
							|| !list_atts_all.contains(name_att_label)
							|| !list_atts_all.contains(name_att_timepoint)
							|| !list_atts_all.containsAll(list_atts_ignored)) {
						String msg = "One of the provided attributes "
								+ "do not match with the attributes read "
								+ "from data file.";
						throw new ImprintDataException(msg);
					}
				} else {
					String vals[] = line.split(_val_sep);

					/* Check if the number of values = number of attributes */
					if (vals.length != list_atts_all.size()) {
						String exp_msg = "Number of vals at line " + line_no
								+ "doesn't match the number of attributes.";
						throw new ImprintDataException(exp_msg);
					}

					/*
					 * Read all vals into an att val Map
					 */
					Map<String, String> att_val_map = new HashMap<String, String>();
					for (int i = 0; i < vals.length; i++)
						att_val_map.put(list_atts_all.get(i), vals[i]);

					/*
					 * Converting a String into a number can generate an
					 * exception if string doesn't represent a number.
					 */
					try {

						/*
						 * Create a Double array for storing the values of all
						 * the regular attributes
						 */
						Double[] vals_reg = new Double[list_atts_regular.size()];
						for (int i = 0; i < list_atts_regular.size(); i++) {
							String att_name = list_atts_regular.get(i);
							vals_reg[i] = Double.valueOf(att_val_map
									.get(att_name));
						}

						/*
						 * Extract the meta info on the LongitudinalObject from
						 * att_val_map
						 */
						String val_id = att_val_map.get(name_att_id);
						String val_label = att_val_map.get(name_att_label);
						int val_timepoint = Integer.valueOf(att_val_map
								.get(name_att_timepoint));

						/*
						 * Get the relevant LongitudinalObject from the map of
						 * LongitudinalObjects. If the object doesn't exist in
						 * the map, create a new object with the desired id and
						 * add it to the map.
						 */
						LongitudinalObject curr_long = long_objs.get(val_id);
						if (curr_long == null) {
							curr_long = new LongitudinalObject(val_id);
							long_objs.put(val_id, curr_long);
						}

						/*
						 * Add the instance and label the LongitudinalObject
						 */
						curr_long.addInstance(vals_reg, val_timepoint);
						curr_long.addLabel(val_label, val_timepoint);

					} catch (NumberFormatException e) {
						String exp_msg = "A value for regular att or timepoint "
								+ "att can't be parsed to a number in line "
								+ line_no + " '" + line + "'";
						throw new ImprintDataException(exp_msg);
					}
				}
				line_no++;
			}
		} catch (FileNotFoundException e) {
			String exp_msg = data_filepath
					+ ": Data file or path doesn't exist.";
			throw new ImprintDataException(exp_msg);
		} catch (IOException e) {
			String exp_msg = data_filepath + ": Cannot read from file.";
			throw new ImprintDataException(exp_msg);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@Deprecated
	/**
	 * Exxtract the info on the LongitudinalObject from the line
	 * and creates or add the info to the map of LongitudinalObjects.
	 */
	protected void constructLongitudinalObjects(String _line) {
		/*
		 * TODO This function is current because this functionality is currently
		 * implemented inside readDataIntoTable(). Check if the functionality
		 * can be put under this function, whence it would then be called from
		 * within readDataIntoTable().
		 */
	}

	/**
	 * Returns the number of LongitudinalObject stored inside the table.
	 */
	public int getSize() {
		return long_objs.size();
	}

	/**
	 * Returns a collection of the ids of LongitudinalObjects.
	 * 
	 * @return Collection<String>
	 */
	public Set<String> getIds() {
		return long_objs.keySet();
	}

	/**
	 * Returns the LongitudinalObject with the supplied id.
	 * 
	 * @return LongitudinalObject
	 * @throws ImprintDataException
	 */
	public LongitudinalObject getTBIObject(String _id)
			throws ImprintDataException {
		/* Throws a missing object exception if _id doesn't exist */
		if (!long_objs.containsKey(_id)) {
			String msg = "Longitudinal Object with id =" + _id
					+ " doesn't exist.";
			throw new ImprintDataException(msg);
		}
		return long_objs.get(_id);
	}

	@Deprecated
	/**
	 * @return 
	 */
	public Map<String, LongitudinalObject> getInstancesDataWRTTime() {
		return null;
	}

	/**
	 * Create a sample of LongitudinalObject stored in the table.
	 * 
	 * TODO: A better option would be to generate a new LongitudinalTable
	 * instead of returning a Map.
	 * 
	 * @return a Map of LongitudinalObject with key as their id
	 */
	public Map<String, LongitudinalObject> getBootstrapSampleOfPatients() {
		return null;
		// TODO Implement the method
	}

	/**
	 * Retrieves the info on data file from Parameters @param _param and calls
	 * createTable() for creating a LongitudinalTable.
	 * 
	 * @param _params
	 * @return LongitudinalTable
	 * @throws ImprintParamMissing
	 */
	public static LongitudinalTable createTableFromParameters(Parameters _params)
			throws ImprintDataException, ImprintParamMissing {
		/* Construct the correct path to the data file */
		String dataPath = Commons.PATH_DEFAULT_DATA;
		String dataRelPath = _params
				.getParamValAsString(Parameters.PARAM_DATA_REL_PATH);
		String dataFilename = _params
				.getParamValAsString(Parameters.PARAM_DATA_FILENAME);

		/* Extract all the information about the data file from _params */
		String data_filepath = dataPath + dataRelPath + dataFilename;
		String att_id = _params.getParamValAsString(Parameters.PARAM_ATT_ID);
		String att_label = _params
				.getParamValAsString(Parameters.PARAM_ATT_LABEL);
		String att_timepoint = _params
				.getParamValAsString(Parameters.PARAM_ATT_TIMEPOINT);
		ArrayList<String> ignore_list = _params
				.getParamValAsArrayList(Parameters.PARAM_IGNORE_LIST);
		String val_sep = _params.getParamValAsString(Parameters.PARAM_CSV_SEP);

		/* Call createTable() to construct the LongitudinalTable */
		return createTableFromCSV(data_filepath, att_id, att_label,
				att_timepoint, ignore_list, val_sep);
	}

	/**
	 * Gets the info on data file and loads the data from the provided file into
	 * a LongitudinalTable object. A LongitudinalTable can only be created with
	 * this function. Direct access to the LongitudinalTable constructor is kept
	 * hidden.
	 * 
	 * @param _dataFilepath
	 * @param _idAtt
	 * @param _labelAtt
	 * @param _timepointAtt
	 * @param _ignoreList
	 * @param _valSep
	 * 
	 * @return LongitudinalTable
	 */
	public static LongitudinalTable createTableFromCSV(String _data_filepath,
			String _att_id, String _att_label, String _att_timepoint,
			ArrayList<String> _ignore_list, String _val_sep)
			throws ImprintDataException {
		LongitudinalTable table = null;
		table = new LongitudinalTable(_data_filepath, _att_id, _att_label,
				_att_timepoint, _ignore_list, _val_sep);
		return table;
	}

}