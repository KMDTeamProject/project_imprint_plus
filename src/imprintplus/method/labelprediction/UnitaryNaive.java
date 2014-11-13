package imprintplus.method.labelprediction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import imprintplus.data.LongitudinalObject;

public class UnitaryNaive  extends AbstractUnitary {
	
	@Override
	public String predict(LongitudinalObject _obj)
			throws ImprintMethodMissingDataException {
		
		/* Get the label series of the longitudinal object _obj. */
		ArrayList<String> l_series = _obj.getLabelSeries();
		
		/*
		 * Get the maximum index and then return the max
		 * label as the predicted label.
		 */		
		int max_label_index = l_series.size()-1;
		String max_label = null;
		max_label =  l_series.get(max_label_index);

		return max_label;
	}
}