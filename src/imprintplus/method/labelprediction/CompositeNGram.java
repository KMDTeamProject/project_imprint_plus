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

	/**
	 * This method learns a prediction model from a LongitudinalTable. This must
	 * be called prior to the calling of the predict() methods.
	 * 
	 * @param _obj
	 */
	@Override
	public void learn(LongitudinalTable _table) {

	//how does the learn process work, should we build the n-gram for each object at the LongitudinalTable
		
	}

	
	/**
	 * Iterates over a Map of the LongitudinalObject and get the prediction for
	 * each object one by one. The predicted values are returned as a Map<id,
	 * predLabel>.
	 * 
	 * @param _objs
	 * @return
	 */
	public Map<String, String> predict(Map<String, LongitudinalObject> _objs) throws ImprintMethodMissingDataException {
		Map<String, String> predicted = new HashMap<String, String>();
		for (String id : _objs.keySet()) {
			LongitudinalObject obj = _objs.get(id);
			predicted.put(id, this.predict(obj));
		}
		return predicted;
	}
}