package imprintplus.data;

import java.util.Collection;
import java.util.Map;

/**
 * Class LongitudinalTable
 */
public class LongitudinalTable {

	/*
	 * Meta Data
	 */
	protected String name_att_id;
	protected String name_att_label;
	protected String name_att_timepoint;
	protected Collection<String> list_atts_ignored;
	protected Collection<String> list_atts_all;
	protected Collection<String> list_atts_regular;

	@Deprecated
	protected Collection<LongitudinalObject> DATA;

	/** Map of LongitudinalObject stored wrt their ids */
	protected Map<String, LongitudinalObject> long_objs;

	/**
	 * Creates a LongitudinalTable from the provided data. The data that must be
	 * supplied to the constructor includes - name of id, label, and timepoint
	 * attributes - list of attributes to be ignored (if any) - name/location of
	 * data source from where to read the data
	 */
	public LongitudinalTable() {
		/*
		 * TODO Make the constructor protected and create the object using a
		 * public static method.
		 */

		// TODO Implement the method
	};

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
	protected void readData() {
		// TODO Implement the method
	}

	/**
	 */
	protected void constructLongitudinalObjects() {
		// TODO Implement the method
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
	public Collection<String> getIds() {
		return long_objs.keySet();
	}
	
	/**
	 * Returns the LongitudinalObject with the supplied id.
	 * 
	 * @return LongitudinalObject
	 */
	public LongitudinalObject getTBIObject(String _id) {
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
	 * @return
	 */
	public Map<String, LongitudinalObject> getBootstrapSampleOfPatients() {
		return null;
		// TODO Implement the method
	}

}