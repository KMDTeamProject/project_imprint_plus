package imprintplus.method.labelprediction;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;
import imprintplus.data.LongitudinalObject;

/**
 * @author SHAREEF
 */

public class UnitaryNGram extends AbstractUnitary {

	private int n = 3;
	// nGram variable that will contain the labels along with the
	private Hashtable<String, Hashtable<String, Double>> nGram = new Hashtable<String, Hashtable<String, Double>>();

	@Override
	/**
	 * Unitary predictor doesn't learn a model. When a LongitudinalObject
	 * is provided to them, they learn the model on the fly and do the 
	 * prediction. The learning is also encapsulated inside this method. 
	 */
	public String predict(LongitudinalObject _obj)
			throws ImprintMethodMissingDataException {

		/* Get the label series of the longitudinal object _obj. */
		ArrayList<String> l_series = _obj.getLabelSeries();

		/* building the n-gram for the label series with n = 3 */
		for (int i = 0; i <= l_series.size() - n; i++) {
			if (nGram.containsKey(l_series.get(i) + " " + l_series.get(i + 1))) {

				if (nGram.get(l_series.get(i) + " " + l_series.get(i + 1))
						.containsKey(l_series.get(i + 2))) {
					double v = nGram.get(
							l_series.get(i) + " " + l_series.get(i + 1)).get(
							l_series.get(i + 2));
					v++;
					nGram.get(l_series.get(i) + " " + l_series.get(i + 1)).put(
							l_series.get(i + 2), v);
				} else {
					nGram.get(l_series.get(i) + " " + l_series.get(i + 1)).put(
							l_series.get(i + 2), 1.0);
				}
			} else {

				nGram.put(l_series.get(i) + " " + l_series.get(i + 1),
						create(l_series.get(i + 2)));
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
		 * Search the nGram for the label with the best confidence while current
		 * label is same as last n-label
		 */

		String predicate_label = null;
		double bestConfidence = Double.MIN_VALUE;

		for (Entry<String, Double> entry : nGram.get(last_label).entrySet()) {
			String currentPrediction = entry.getKey();
			double currentConfidence = entry.getValue();

			if (currentConfidence > bestConfidence) {
				bestConfidence = currentConfidence;
				predicate_label = currentPrediction;
			}
		}

		return predicate_label;
	}

	private Hashtable<String, Double> create(String s) {
		Hashtable<String, Double> result = new Hashtable<String, Double>();
		result.put(s, 1.0);
		return result;
	}
}
