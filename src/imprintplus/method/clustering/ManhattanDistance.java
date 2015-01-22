package imprintplus.method.clustering;

public class ManhattanDistance extends AbstractDistance {

	@Override
	public double calculateDistance(double[] x, double[] y) {
		double dist = 0;
		for (int i = 0; i < x.length; i++) {

			dist += Math.abs(x[i] - y[i]);
		}
//		dist = Math.sqrt(dist);
		return dist;
	}

}
