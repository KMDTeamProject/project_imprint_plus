package imprintplus.method.labelprediction;

import java.util.ArrayList;
import imprintplus.data.LongitudinalObject;

/**
 * @author SHAREEF
 */

public class UnitaryNGram extends AbstractUnitary {

	// default value n for nGram
	private int n = 3;

	// nGram variable that will contain the labels along with the
	private NGram nGram;

	/**
	 * default contractor
	 */
	public UnitaryNGram() {
		nGram = new NGram();
	}

	/**
	 * normal contractor
	 */
	public UnitaryNGram(int n) {
		this.n = n;
		nGram = new NGram(n);

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

		// build the nGram of the longitudinal object
		nGram.fillnGram(l_series);

		/*
		 * Get the index and then the last n-label
		 */
		
		int last_label_index = l_series.size() - n - 1;
		String last_label = "";

		last_label = l_series.get(last_label_index);

		/*
		 * Search the nGram for the label with the best confidence while current
		 * label is same as last n-label
		 */
		return nGram.getPredicateLabel(last_label);

	}
}
