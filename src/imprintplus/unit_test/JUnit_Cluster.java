/**
 * @author Raj
 */
package imprintplus.unit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import imprintplus.method.model.Cluster;

public class JUnit_Cluster {
	/**
     * Test for all the get Methods , of class Cluster
     */
	@Test
	public void testAllGetaddFunctions() {
		Double[] a={1.0,2.4};
		Cluster c=new Cluster(a);
		c.addInstanceId("A");
		assertEquals(0,c.getClusterId());
		assertSame(a,c.getCentroid());
	}
	
}
