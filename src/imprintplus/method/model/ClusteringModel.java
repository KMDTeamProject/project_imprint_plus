/**
 * @author Carlo,Raj
 *
 */
package imprintplus.method.model;



import java.util.ArrayList;

public class ClusteringModel {
	int K;
	
	ArrayList<Cluster> clusters;
	
	public ClusteringModel(int _K) {
		K=_K;
		clusters = new ArrayList<Cluster>();
	}
	// get cluster id
	public Cluster getClusterWithId(int _clus_id) {
		return clusters.get(_clus_id);
	}
	
	public void addCluster(Cluster _clus) {
		clusters.add(_clus);
	}
}
