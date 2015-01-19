package imprintplus.method.clustering;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import imprintplus.method.model.Cluster;
import imprintplus.method.model.ClusteringModel;
import imprintplus.data.LongitudinalObject;

public class KMean {
	static final int MAX_RUNS = 10;
	static final int MAX_ITERATIONS = 100;

	int K;
	int Y;  // number of dimensions
	int Z;  // number of data points
	double[][] points;
	double[][] centroid;
	AbstractDistance distance;

	protected KMean(int _K) {
		K = _K;
		distance = new EuclideanDistance();
	}

	public KMean(int _K, AbstractDistance _distance) {
		this(_K);
		distance = _distance;
	}

	public ClusteringModel doClustering(Map<String, Double[]> _inst_map) {
		// Randomly initialise K Clusters using the provided instances map
		ArrayList<String> inst_ids = new ArrayList<String>();
		inst_ids.addAll(_inst_map.keySet());
		
		ClusteringModel best_model = null;
		Double best_sse = Double.MAX_VALUE;
		
		for (int run=0; run<KMean.MAX_RUNS; run++) {
			
			ClusteringModel curr_model = new ClusteringModel(this.K);
			for (int i=0; i<this.K; i++) {
				int seed = (int)Math.round(Math.random()*inst_ids.size());
				String seed_id = inst_ids.remove(seed);
				Double[] seed_inst = _inst_map.get(seed_id);			
				Cluster cluster = new Cluster(seed_inst);
				curr_model.addCluster(cluster);
			}
			
			// Initialise centroids
			this.chooseInitialCentroids();
			
	
			// Calling EuclideanDistance CLass
			AbstractDistance distance = new EuclideanDistance();

			for (int itr=0; itr<KMean.MAX_ITERATIONS; itr++) {
				
				//Finding Closest Center
				double closestCenter = FindClosestCenter(centroid, instances);
				
				// Update the centroids 
				updateCentroid(closestCenter);
				
			}
			
			// Once the stability is reached or max itrs reached, stop the clustering
			// and check if the curr_model is the best model so far.
			
			Double curr_sse = computeSSE(curr_model, _inst_map);
			
			if (curr_sse<best_sse) {
				best_model = curr_model;
				best_sse = curr_sse;
			}			
		}
		
		return best_model;
	}

	/**
	 * Assigning Initial Centroids
	 */
	private void chooseInitialCentroids() {
		centroid = new double[K][Y];
		double[][] copy = points;

		Random gen = new Random();

		int rand;
		for (int i = 0; i < K; i++) {
			rand = gen.nextInt(Z - i);
			for (int j = 0; j < Y; j++) {
				centroid[i][j] = copy[rand][j];

			}
		}
	}
	
	/**
	 * Finding Closest Center
	 */
	private double FindClosestCenter(double centroid, Map<Integer, Double[]> instances){
		
		double closestCenter = 0;
		double closestDistance = -1;
		for (int i=0; i<K; i++){
			distance = calculateDistance(i.getcentroid(), instances);   // i is integer and getcentroids for double....!!!
			if (closestCenter = 0 || distance<closestDistance){  
				closestCenter = i;
				closestDistance = distance;
			}
		}
		return closestCenter;					
	}
	
	/**
	 * Update Centroids
	 */
	private void updateCentroid(double closestCenter){
				
		centroid = new double[K][Y];
		
		//Calculate new centroids
		List<Double> newCentroid = new ArrayList<Double>(Y);
		double sum =0;
		int counter=0;
		for (int i=0; i<Z; i++){
			for (int j=0; j<Y; j++){
				List<Integer> index = Z.get(i);
				for (int q=0; q<index; q++){
					sum += points.get(q).get(Y);
					counter++;
				}
				newCentroid.add(j, sum/counter);
				sum=0;
				counter=0;
			}
			newCentroid = new ArrayList<Double>(Y);
		}
	}
	
	
	protected Double computeSSE(ClusteringModel _model,
			Map<String, Double[]> _inst_map) {
		Double sse = 0.0;
		// TODO IMplement the function
		return sse;
	}

}
