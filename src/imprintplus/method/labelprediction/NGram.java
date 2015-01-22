package imprintplus.method.labelprediction;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

public class NGram {

	private int n = 3;
	private Hashtable<String, Hashtable<String, Double>> nGram;

	public NGram() {
		nGram = new Hashtable<String, Hashtable<String, Double>>();
	}

	public NGram(int n) {
		nGram = new Hashtable<String, Hashtable<String, Double>>();
		this.n = n;
	}

	public String getPredicateLabel(String last_label) {
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

	public void fillnGram(ArrayList<String> l_series) {

		for (int o_i = 0; o_i < l_series.size() - (this.n - 1); o_i++) {
			String ngram = new String();

			for (int i_i = o_i; i_i < o_i + this.n; i_i++) {

				if (ngram.isEmpty()) {
					ngram = l_series.get(i_i);
				} else {
					ngram += "_" + l_series.get(i_i);
				}
			}
			// check if the nGram has the current value
			if (nGram.containsKey(l_series.get(o_i))) {
				if (nGram.get(l_series.get(o_i)).contains(ngram)) {
					double v = nGram.get(l_series.get(o_i)).get(ngram);
					v++;
					nGram.get(l_series.get(o_i)).put(ngram, v);
				} else {
					nGram.get(l_series.get(o_i)).put(ngram, 1.0);
				}

				// if nGram doesn't have the value then add it to nGram
			} else {

				nGram.put(l_series.get(o_i), create(ngram));
			}
		}
	}

	private Hashtable<String, Double> create(String s) {
		Hashtable<String, Double> result = new Hashtable<String, Double>();
		result.put(s, 1.0);
		return result;
	}

}
