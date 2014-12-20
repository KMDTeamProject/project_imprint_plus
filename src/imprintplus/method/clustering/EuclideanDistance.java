package imprintplus.method.clustering;

public class EuclideanDistance implements AbstractDistance {

	@Override
	public double calculateDistance(double[] x, double[] y) {
		double dist = 0;
		for (int i = 0; i < x.length; i++) {

			dist += (x[i] - y[i]) * (x[i] - y[i]);
		}
		dist = Math.sqrt(dist);
		return dist;
	}
}
