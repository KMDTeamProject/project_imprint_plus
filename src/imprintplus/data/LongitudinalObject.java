package imprintplus.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores the information for an object that exists/appears across multiple
 * timepoints. The object itself is identified using its identifier, while its
 * individual instances/occurrences are identified using the timepoints at which
 * it appears. Additionally, each of its occurrences may also be associated with
 * a label that is stored separately and also identified using its timepoints.
 * 
 * 
 * @author siddiqui16
 */
public class LongitudinalObject {

	/** Identifier of a longitudinal object */
	String id;

	/** Map of instances stored wrt. timepoints */
	Map<Integer, Double[]> instances;

	/** Map of labels stored wrt. timepoints */
	Map<Integer, String> labels;

	/** Map of cluster membership stored wrt. timepoints */
	Map<Integer, String> clusters;

	boolean isControl = false;

	public LongitudinalObject(String _id) {
		id = _id;
		instances = new HashMap<Integer, Double[]>();
		labels = new HashMap<Integer, String>();
		clusters = new HashMap<Integer, String>();
	}

	/**
	 * Returns if the method is control or not. This is relevant for some of the
	 * methods.
	 * 
	 * @return
	 */
	public boolean isControl() {
		return isControl;
	}

	/**
	 * Returns the id of the {@link LongitudinalObject}
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Adds the provided instance to the Map with timepoint as the key.
	 */
	public void addInstance(Double[] _inst, int _timepoint) {
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
	 * Adds the provided label to the Map with timepoint as the key.
	 */
	public void addLabel(String _label, int _timepoint) {
		labels.put(_timepoint, _label);
	}

	/**
	 * Returns the label of the object at the given timepoint
	 * 
	 * @return label
	 */
	public String getLabel(int _timepoint) {
		return labels.get(_timepoint);
	}

	/**
	 * Adds the provided cluster to the Map with timepoint as the key
	 */
	public void addCluster(String _cluster, int _timepoint) {
		clusters.put(_timepoint, _cluster);
	}

	/**
	 * Returns the cluster id of the object based the membership of the instance
	 * for the given timepoint
	 * 
	 * @return cluster
	 */
	public String getCluster(int _timepoint) {
		return clusters.get(_timepoint);
	}

	/**
	 * Generate a series from the given map. For example one can use it to
	 * generate either a cluster-series or a label-series.
	 */
	protected ArrayList<String> getSeriesFromMap(Map<Integer, String> _map) {
		ArrayList<String> series = new ArrayList<String>();
		ArrayList<Integer> timepoints = new ArrayList<Integer>();
		timepoints.addAll(_map.keySet());
		Collections.sort(timepoints);
		for (Integer t : timepoints)
			series.add(_map.get(t));
		return series;

	}

	/**
	 * Generates a label-series.
	 */
	public ArrayList<String> getLabelSeries() {
		return getSeriesFromMap(labels);
	}

	/**
	 * Generates a cluster-series.
	 */
	public ArrayList<String> getClusterSeries() {
		return getSeriesFromMap(clusters);
	}

	/**
	 * 
	 */
	public LongitudinalObject clone() {
		// TODO Implementation
		return null;
	}
}
