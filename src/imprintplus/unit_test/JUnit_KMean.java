package imprintplus.unit_test;

import imprintplus.method.clustering.KMean;
import imprintplus.method.model.ClusteringModel;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Raj
 */
public class JUnit_KMean {
	/**
     * Test of doClustering method, of class KMean.
     */
    @Test
    public void testDoClustering() {
    	Double[] a=new Double[1];
    	a[0]=5.0;
        Map<String,Double[]> _inst_map = new HashMap<String,Double[]>();
        _inst_map.put("a",a);
        _inst_map.put("b",a);
        _inst_map.put("c",a);
        _inst_map.put("d",a); 
        _inst_map.put("e",a);
        _inst_map.put("f",a);
        KMean instance = new KMean(1);
        ClusteringModel expResult1 = new ClusteringModel(1);
        ClusteringModel expResult2 = new ClusteringModel(2);
        ClusteringModel best_model = instance.doClustering(_inst_map);
        assertTrue(expResult1==best_model);
        assertFalse(expResult2==best_model);
     }  
    
    /**
     * Test of computeSSE method, of class KMean.
     */
    @Test
    public void testComputeSSE() {
        
    	ClusteringModel _model = new ClusteringModel(1);
    	Map<String,Double[]> _inst_map = new HashMap<String,Double[]>();
    	KMean instance = new KMean(1);
        Double expsse1 = 0.0;
        Double expsse2 = 4.0;
        Double sse = instance.computeSSE(_model, _inst_map);
        assertFalse(expsse1==sse);
        assertTrue(expsse2==sse);
       
    }
    /**
     * Test of FindClosest method, of class KMean.
     */
    @Test
    public void testFindClosestCluster() {
        
        ClusteringModel _mdl =new ClusteringModel(1);
        Double[] _instance = new Double[0];
        _instance[0]=1.0; 
         KMean instance = new KMean(1);
        int expccenter1 = 0;
        int expccenter2 = 2;
        int clustercenter = instance.FindClosestCluster(_mdl, _instance);
        assertTrue(expccenter1==clustercenter);
        assertFalse(expccenter2==clustercenter);
       
    }		
}
