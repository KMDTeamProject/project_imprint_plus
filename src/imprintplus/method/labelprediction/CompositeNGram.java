package imprintplus.method.labelprediction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import imprintplus.data.LongitudinalObject;
import imprintplus.data.LongitudinalTable;

/**
 * @author SHAREEF
 */

public class CompositeNGram extends AbstractComposite {

	private int n;
	private NGram[] nGram;

	public CompositeNGram(int n) {
		this.n = n;
	}

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
		nGram = new NGram[_objs.size()];

		int counter = 0;
		for (String id : _objs.keySet()) {
			nGram[counter] = new NGram(this.n);
			LongitudinalObject obj = _objs.get(id);

			/* Get the label series of the longitudinal object _obj. */
			ArrayList<String> l_series = obj.getLabelSeries();

			// build the nGram of the longitudinal object
			nGram[counter].fillnGram(l_series);

			/*
			 * Get the index and then the last n-label
			 */
			int last_label_index = l_series.size() - n - 1;
			String last_label = "";
			
			if(last_label_index > 0 )
				last_label = l_series.get(last_label_index);
			else
				last_label = l_series.get(l_series.size()-1);

			/*
			 * Search the nGram for the label with the best confidence while
			 * current label is same as last n-label
			 */

			String predicate_label = nGram[counter]
					.getPredicateLabel(last_label);

			predictedValues.put(id, predicate_label);
			counter++;
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