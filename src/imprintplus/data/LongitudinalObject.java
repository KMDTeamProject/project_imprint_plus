package imprintplus.data;

import java.util.HashMap;
import java.util.Map;

public class LongitudinalObject {
	
	String id;
	Map<Integer, Double []> instances;
	Map<Integer, String> labels;
	Map<Integer, String> clusters;
	
	boolean isControl = false;
	
	public LongitudinalObject(String _id) {
		id = _id;
		instances = new HashMap<Integer, Double[]>();
		labels = new HashMap<Integer, String>();
		clusters = new HashMap<Integer, String>();
	}
	
	/**
	 * Returns if the method is control or not.
	 * This is relevant for some of the methods.
	 * 
	 * @return
	 */
	public boolean isControl(){
		return isControl;
	}
	
	/**
	 * Adds the provided instance to the Map with timepoint
	 * as the key.
	 */
	public void addInstance(Double [] _inst, int _timepoint) {
		instances.put(_timepoint, _inst);
	}
	
	/**
	 * Returns the instance with the provided timepoints
	 * 
	 * @return instance
	 */
	public Double[] getInstance(int _timepoint) {
		return instances.get(_timepoint);
	}
		
	/**
	 * Adds the provided label to the Map with timepoint
	 * as the key.
	 */
	public void addLabel(String _label, int _timepoint) {
		labels.put(_timepoint, _label);
	}
	
	/**
	 * Returns the label of the object at the given timpoint
	 * 
	 * @return label
	 */
	public String getLabel(int _timepoint) {
		return labels.get(_timepoint);
	}
	
	/**
	 * Adds the provided cluster to the Map with timepoint
	 * as the key
	 */
	public void addCluster(String _cluster, int _timepoint) {
		clusters.put(_timepoint, _cluster);
	}
	
	/**
	 * Returns the cluster id of the object based the membership
	 * of the instance for the given timepoint
	 * 
	 * @return cluster
	 */
	public String getCluster(int _timepoint) {
		return clusters.get(_timepoint);
	}
}
