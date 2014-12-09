package imprintplus.method.labelprediction;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;
import imprintplus.data.LongitudinalObject;

/**
 * @author SHAREEF
 */

public class UnitaryNGram extends AbstractUnitary {

	// default value n for nGram
	private int n = 3;

	// nGram variable that will contain the labels along with the
	private Hashtable<String, Hashtable<String, Double>> nGram;

	/**
	 * default constructor
	 */
	public UnitaryNGram() {
		nGram = new Hashtable<String, Hashtable<String, Double>>();
	}

	/**
	 * normal constructor
	 */
	public UnitaryNGram(int n) {
		nGram = new Hashtable<String, Hashtable<String, Double>>();
		this.n = n;
	}

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

		/* building the n-gram for the label series */
		for (int i = 0; i <= l_series.size() - n; i++) {
			// n-Gram default value as startup
			String temp = l_series.get(i);

			/*
			 * for n value lager than 2: loop from the next value till the value
			 * (n - 1) that has index [n-2]
			 */
			for (int j = i + 1; j < i + n - 2; j++) {
				temp += " " + l_series.get(j);
			}
			// check if the nGram has the current value
			if (nGram.containsKey(temp)) {
				if (nGram.get(temp).contains(l_series.get(i + n - 1))) {
					double v = nGram.get(temp).get(l_series.get(i + n - 1));
					v++;
					nGram.get(temp).put(l_series.get(i + n - 1), v);
				} else {
					nGram.get(temp).put(l_series.get(i + n - 1), 1.0);
				}
				// if nGram doesn't have the value then add it to nGram
			} else {

				nGram.put(temp, create(l_series.get(i + n - 1)));
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

	/**
	 * initiate new hash table based on the input string
	 */

	private Hashtable<String, Double> create(String s) {
		Hashtable<String, Double> result = new Hashtable<String, Double>();
		result.put(s, 1.0);
		return result;
	}
}
