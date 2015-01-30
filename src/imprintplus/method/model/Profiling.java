package imprintplus.method.model;

/**
 * @author carlo
 *
 */
import imprintplus.data.LongitudinalObject;
import imprintplus.method.model.Cluster;
import imprintplus.method.model.ClusteringModel;

import java.util.Map;
import java.util.ArrayList;


public class Profiling {
 
 

 public static void main(String[] args) {
  // TODO Auto-generated method stub
Double[][] Prof = Profiling(id,clus_series);
 }
   
 /**
  * Implementing the transition profiles from the clusters
 * @return 
  * 
  */
 
 public Double[][] Profiling(Double[] id ,LongitudinalObject clus_series) {
  
  Array<String> _clus_series = new Array(String);
  for (String id : inst_ids){

   Double[][] profile = _clus_series.getClusterSeries(id).getSeriesFromMap();
   for (int i=0; i<_inst_ids.size(); i++){
    if(_clus_series[i].equals(_clus_series[i])){
     profile = _clus_series.profile();
    }
   }
   return profile;
    } 
  }
}
