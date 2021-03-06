/**
 * 
 * @author carlo,Raj
 *
 */
package imprintplus.method.model;

import java.util.ArrayList;
import java.util.Map;


public class Cluster {
	
	private static int CLUSTER_COUNT = 0;

	/** Unique identifier of the cluster */
	int clus_id;
	
	/** Centroid of the cluster with length according to the input data. */
	Double[] centroid;

	/** List of ids of the objects that belong to this cluster */
	ArrayList<String> inst_ids;

	/**
	 * A cluster can only be created on the basis of the provided data instance.
	 * 
	 * @param _centroid
	 */
	public Cluster(Double[] _centroid) {
		clus_id = Cluster.CLUSTER_COUNT++;
		centroid = _centroid;
		inst_ids = new ArrayList<String>();
	}
	
	//get cluster id
	public int getClusterId() {
		return clus_id;
	}
	
	//get centroid
	public Double[] getCentroid() {
		return centroid;
	}

	// add ids to the list of instances ids
	public void addInstanceId(String _inst_id) {
		inst_ids.add(_inst_id);
	}

	//update centroids
	public void updateCentroid(Map<String, Double[]> _obj_map) {
		int count = inst_ids.size();
		centroid = new Double[centroid.length];
		for (String id : inst_ids) {
			Double[] inst = _obj_map.get(id);
			for (int i = 0; i < centroid.length; i++)
				centroid[i] += (inst[i] / count);
		}
	}
}
