package imprintplus.method.labelprediction;

import imprintplus.data.LongitudinalObject;
import imprintplus.data.LongitudinalTable;
import imprintplus.method.model.ImprintStateAlreadyDefinedException;
import imprintplus.method.model.ImprintStateNotDefinedException;
import imprintplus.method.model.ImprintTransitionAlreadyDefinedException;
import imprintplus.method.model.MarkovChain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompositeMarkovChain extends AbstractComposite {

	private MarkovChain<String>[] markovChain;

	/**
	 * This method learns a prediction model from a LongitudinalTable. This must
	 * be called prior to the calling of the predict() methods.
	 * 
	 * @param _obj
	 */
	@Override
	public void learn(LongitudinalTable _table) {

		// how does the learn process work, should we build the n-gram for each
		// object at the LongitudinalTable

	}

	/**
	 * Iterates over a Map of the LongitudinalObject and get the prediction for
	 * each object one by one. The predicted values are returned as a Map<id,
	 * predLabel>.
	 * 
	 * @param _objs
	 * @return
	 */
	public Map<String, String> predict(Map<String, LongitudinalObject> _objs)
			throws ImprintMethodMissingDataException {

		// the result predicated values
		Map<String, String> predictedValues = new HashMap<String, String>();

		// initialize the NGram Array
		markovChain = new MarkovChain[_objs.size()];

		int counter = 0;
		for (String id : _objs.keySet()) {
			markovChain[counter] = new MarkovChain<String>();
			LongitudinalObject obj = _objs.get(id);

			/* Get the label series of the longitudinal object _obj. */
			ArrayList<String> l_series = obj.getLabelSeries();

			try {

				markovChain[counter] = MarkovChain.fromStrings(l_series
						.iterator());
				predictedValues.put(id, markovChain[counter].generate());

			} catch (ImprintStateAlreadyDefinedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ImprintStateNotDefinedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ImprintTransitionAlreadyDefinedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return predictedValues;
	}

	// this function must be implemented but should not be used for Composite
	// type
	@Override
	public String predict(LongitudinalObject _obj)
			throws ImprintMethodMissingDataException {
		// TODO Auto-generated method stub
		return null;
	}

}