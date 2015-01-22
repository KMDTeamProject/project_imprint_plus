package imprintplus.method.labelprediction;

import java.util.ArrayList;

import imprintplus.data.LongitudinalObject;
import imprintplus.method.model.ImprintStateAlreadyDefinedException;
import imprintplus.method.model.ImprintStateNotDefinedException;
import imprintplus.method.model.ImprintTransitionAlreadyDefinedException;
import imprintplus.method.model.MarkovChain;

;

public class UnitaryMarkovChain extends AbstractUnitary {

	private MarkovChain<String> markovChain;

	/**
	 * Unitary predictor doesn't learn a model. When a LongitudinalObject is
	 * provided to them, they learn the model on the fly and do the prediction.
	 * The learning is also encapsulated inside this method.
	 */
	@Override
	public String predict(LongitudinalObject _obj)
			throws ImprintMethodMissingDataException {

		/* Get the label series of the longitudinal object _obj. */
		ArrayList<String> l_series = _obj.getLabelSeries();

		try {

			markovChain = MarkovChain.fromStrings(l_series.iterator());
			return markovChain.generate();

		} catch (ImprintStateAlreadyDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImprintStateNotDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ImprintTransitionAlreadyDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
