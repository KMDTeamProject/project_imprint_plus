package imprintplus.method.clustering;

import java.util.Map;
import java.util.ArrayList;


import imprintplus.method.model.Cluster;
import imprintplus.method.model.ClusteringModel;

public class KMean {
	static final int MAX_RUNS = 10;
	static final int MAX_ITERATIONS = 100;

	int K; // number of clusters
	int Y; // number of dimensions
	int Z; // number of data points
	AbstractDistance distance;

	public KMean(int _K) {
		K = _K;
		distance = new EuclideanDistance();
	}

	public KMean(int _K, AbstractDistance _distance) {
		K = _K;
		distance = _distance;
	}

	public ClusteringModel doClustering(Map<String, Double[]> _inst_map) {
		// Randomly initialise K Clusters using the provided instances map
		ArrayList<String> inst_ids = new ArrayList<String>();
		inst_ids.addAll(_inst_map.keySet());

		ClusteringModel best_model = null;
		Double best_sse = Double.MAX_VALUE;

		for (int run = 0; run < KMean.MAX_RUNS; run++) {

			ClusteringModel curr_model = new ClusteringModel(this.K);
			for (int i = 0; i < this.K; i++) {
				int seed = (int) Math.round(Math.random() * inst_ids.size());
				String seed_id = inst_ids.remove(seed);
				Double[] seed_inst = _inst_map.get(seed_id);
				Cluster cluster = new Cluster(seed_inst);
				curr_model.addCluster(cluster);
			}


			// Calling EuclideanDistance CLass
			for (int itr = 0; itr < KMean.MAX_ITERATIONS; itr++) {

				// Finding Closest Center
				for (String id : inst_ids) {
					Double[] instance = _inst_map.get(id);
					int closestCenter = FindClosestCluster(curr_model, instance);
					curr_model.getClusterWithId(closestCenter).addInstanceId(id);
				}

				// Update the centroids
				for (int i = 0; i < K; i++)
					curr_model.getClusterWithId(i).updateCentroid(_inst_map);
			}

			// Once the stability is reached or max itrs reached, stop the
			// clustering
			// and check if the curr_model is the best model so far.

			Double curr_sse = computeSSE(curr_model, _inst_map);

			if (curr_sse < best_sse) {
				best_model = curr_model;
				best_sse = curr_sse;
			}
		}

		return best_model;
	}

	/**
	 * finding closest cluster
	 */
	private int FindClosestCluster(ClusteringModel _mdl, Double[] _instance) {
		int closestCenter = -1;
		double closestDistance = -1;
		for (int i = 0; i < K; i++) {
			Double[] curr_centroid = _mdl.getClusterWithId(i).getCentroid();
			double dist = distance.calculateDistance(curr_centroid, _instance); 
			if (closestCenter == -1 || dist < closestDistance) {
				closestCenter = i;
				closestDistance = dist;
			}
		}
		return closestCenter;
	}

	protected Double computeSSE(ClusteringModel _model,
			Map<String, Double[]> _inst_map) {
		Double sse = 0.0;
		// TODO IMplement the function
		return sse;
	}

}
