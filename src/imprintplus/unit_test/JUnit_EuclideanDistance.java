package imprintplus.unit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import imprintplus.method.clustering.EuclideanDistance;

public class JUnit_EuclideanDistance {
	/**
     * Test of doClustering method, of class KMean.
     */
	@Test
	public void calDistance() {
		EuclideanDistance e=new EuclideanDistance();
		double[] x={1.2};
		double[] y={2.2};
		double expdist1=1.0000000000000002;
		double expdist2=2.0;
		double actualdist=e.calculateDistance(x, y);
		assertFalse(expdist1!=actualdist);
		assertFalse(expdist2==actualdist);
		assertTrue(expdist1==actualdist);
	}

}
