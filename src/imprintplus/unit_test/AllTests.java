package imprintplus.unit_test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ JUnit_LongitudinalTable_Test1.class, JUnit_Parameters.class,JUnit_AbstractModel.class,JUnit_Cluster.class,JUnit_ClusteringModel.class,JUnit_Experiment.class,JUnit_ExperimentReader.class,JUnit_KMean.class,JUnit_MarkovChain.class,JUnit_MarkovWithCompositePredictor.class,JUnit_MixtureModel.class,JUnit_State.class })
public class AllTests {
}
