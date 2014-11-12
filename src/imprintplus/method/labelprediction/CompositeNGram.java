package imprintplus.method.labelprediction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import imprintplus.data.LongitudinalObject;
import imprintplus.data.LongitudinalTable;

public class CompositeNGram extends AbstractComposite {

	/**
	 * This method learns a prediction model from a LongitudinalTable. This must
	 * be called prior to the calling of the predict() methods.
	 * 
	 * @param _obj
	 */
	@Override
	public void learn(LongitudinalTable _table) {

		try{
		predict(_table.getBootstrapSampleOfPatients());
		}
		catch(ImprintMethodMissingDataException E){
			
		}
	}

	/**
	 * Iterates over a Map of the LongitudinalObject and get the prediction for
	 * each object one by one. The predicted values are returned as a Map<id,
	 * predLabel>.
	 * 
	 * @param _objs
	 * @return
	 */
	@Override
	public Map<String, String> predict(Map<String, LongitudinalObject> _objs)
			throws ImprintMethodMissingDataException {
		Map<String, String> predicted = new HashMap<String, String>();
		for (String id : _objs.keySet()) {
			LongitudinalObject obj = _objs.get(id);
			predicted.put(id, this.predict(obj));
		}
		return predicted;
	}

	@Override
	public String predict(LongitudinalObject _obj)
			throws ImprintMethodMissingDataException {

		/* Get the label series of the longitudinal object _obj. */
		ArrayList<String> l_series = _obj.getLabelSeries();

		/*
		 * Get the last index and then get the last label
		 */
		int last_label_index = l_series.size() - 1;
		String last_label = null;
		last_label = l_series.get(last_label_index);

		/*
		 * Count the occurrence of each label in the label series that goes with
		 * last label
		 */
		Map<String, Integer> label_counts = new HashMap<String, Integer>();
		for (int counter = 0; counter < l_series.size() - 1; counter++) {

			if (l_series.get(counter).equals(last_label)) {
				String l = l_series.get(counter + 1);
				if (!label_counts.containsKey(l))
					label_counts.put(l, 0);
				label_counts.put(l, label_counts.get(l) + 1);
			}
		}

		for (String l : l_series) {
			if (!label_counts.containsKey(l))
				label_counts.put(l, 0);
			label_counts.put(l, label_counts.get(l) + 1);
		}
		/*
		 * Search for the label with the maximum counts while label is same as
		 * last label and then return the max label as the predicted label.
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