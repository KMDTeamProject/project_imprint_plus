package imprintplus.method.labelprediction;

import java.util.ArrayList;
import imprintplus.data.LongitudinalObject;

/**
 * @author SHAREEF
 */

public class UnitaryNaive  extends AbstractUnitary {
	
	@Override
	public String predict(LongitudinalObject _obj)
			throws ImprintMethodMissingDataException {
		
		/* Get the label series of the longitudinal object _obj. */
		ArrayList<String> l_series = _obj.getLabelSeries();
		
		/*
		 * Get the maximum index and then return the last
		 * label as the predicted label.
		 */		
		int last_label_index = l_series.size()-1;
		String last_label = null;
		last_label =  l_series.get(last_label_index);

		return last_label;
	}
}