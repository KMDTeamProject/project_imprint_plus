package imprintplus.method.labelprediction;

import java.util.ArrayList;

import imprintplus.data.LongitudinalObject;

public class UnitaryDummy extends AbstractUnitary {

	@Override
	public String predict(LongitudinalObject _obj) throws ImprintMethodMissingDataException {
		ArrayList<String> label_series = _obj.getLabelSeries();
		if (label_series.size() < 1) {
			String msg = "Object with id=" + _obj.getId()
					+ " doesn't have labels or are missing";
			throw new ImprintMethodMissingDataException(msg);
		}
		return label_series.get(label_series.size()-1);
	}
}
