/**
 * 
 * @author carlo,Raj
 *
 */
package imprintplus.method.clustering;

public abstract class AbstractDistance {
	public abstract double calculateDistance(double[] x, double[] y);
	
	public double calculateDistance(Double[] x, Double[] y) {
		return calculateDistance(convert(x), convert(y));
	}
	
	public double calculateDistance(Double[] x, double[] y) {
		return calculateDistance(convert(x), y);
	}

	public double calculateDistance(double[] x, Double[] y) {
		return calculateDistance(x, convert(y));
	}

	// convert/cast object form double to Double
		private double[] convert(Double[] _d) {
		double[] new_d = new double[_d.length];
		for (int i = 0; i < _d.length; i++)
			new_d[i] = _d[i];
		return new_d;
	}

}
