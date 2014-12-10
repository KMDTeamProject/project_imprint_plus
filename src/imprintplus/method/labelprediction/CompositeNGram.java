package imprintplus.method.labelprediction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import imprintplus.data.LongitudinalObject;
import imprintplus.data.LongitudinalTable;

/**
 * @author SHAREEF
 */

public class CompositeNGram extends AbstractComposite {

	private int n;
	private Hashtable<String, Hashtable<String, Double>>[] nGram;

	public CompositeNGram(int n) {
		for (int i = 0; i < n; i++)
			nGram[i] = new Hashtable<String, Hashtable<String, Double>>();
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

		Map<String, String> predictedValues = new HashMap<String, String>();
		int counter = 0;
		for (String id : _objs.keySet()) {
			LongitudinalObject obj = _objs.get(id);

			/* Get the label series of the longitudinal object _obj. */
			ArrayList<String> l_series = obj.getLabelSeries();

			/* building the n-gram for the label series with n = 3 */
			for (int i = 0; i <= l_series.size() - n; i++) {
				// n-Gram default value as startup
				String temp = l_series.get(i);

				/*
				 * for n value lager than 2 loop from the next value till the
				 * value (n - 1) that has index [n-2]
				 */
				for (int j = i + 1; j < i + n - 2; j++) {
					temp += " " + l_series.get(j);
				}
				// check if the nGram has the current value
				if (nGram[counter].containsKey(temp)) {
					if (nGram[counter].get(temp).contains(
							l_series.get(i + n - 1))) {
						double v = nGram[counter].get(temp).get(
								l_series.get(i + n - 1));
						v++;
						nGram[counter].get(temp)
								.put(l_series.get(i + n - 1), v);
					} else {
						nGram[counter].get(temp).put(l_series.get(i + n - 1),
								1.0);
					}
					// if nGram doesn't have the value then add it to nGram
				} else {

					nGram[counter].put(temp, create(l_series.get(i + n - 1)));
				}
			}

			/*
			 * Get the index and then the last n-label
			 */
			int last_label_index = l_series.size() - n - 1;
			String last_label = "";

			last_label = l_series.get(last_label_index) + " "
					+ l_series.get(last_label_index + 1);

			/*
			 * Search the nGram for the label with the best confidence while
			 * current label is same as last n-label
			 */

			String predicate_label = null;
			double bestConfidence = Double.MIN_VALUE;

			for (Entry<String, Double> entry : nGram[counter].get(last_label)
					.entrySet()) {
				String currentPrediction = entry.getKey();
				double currentConfidence = entry.getValue();

				if (currentConfidence > bestConfidence) {
					bestConfidence = currentConfidence;
					predicate_label = currentPrediction;
				}
			}
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

	/**
	 * initiate new hash table based on the input string
	 */

	private Hashtable<String, Double> create(String s) {
		Hashtable<String, Double> result = new Hashtable<String, Double>();
		result.put(s, 1.0);
		return result;
	}
}
