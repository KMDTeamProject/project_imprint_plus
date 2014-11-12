package imprintplus.method.labelprediction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import imprintplus.data.LongitudinalObject;

/**
 * TODO: Add the description of unitary mode predictor.
 * @author SHAREEF
 *
 */
public class UnitaryMode extends AbstractUnitary {

	@Override
	public String predict(LongitudinalObject _obj)
			throws ImprintMethodMissingDataException {
		/* Get the label series of the longitudinal object _obj. */
		ArrayList<String> l_series = _obj.getLabelSeries();

		/* Count the occurrence of each label in the label series. */
		Map<String, Integer> label_counts = new HashMap<String, Integer>();
		for (String l : l_series) {
			if (!label_counts.containsKey(l))
				label_counts.put(l, 0);
			label_counts.put(l, label_counts.get(l) + 1);
		}

		/*
		 * Search for the label with the maximum counts and then return the max
		 * label as the predicted label.
		 */
		String max_label = null;
		int max_count = Integer.MIN_VALUE;
		for (String l : label_counts.keySet()) {
			if (label_counts.get(l) > max_count) {
				max_label = l;
				max_count = label_counts.get(l);
			}
		}
		return max_label;
	}

}
