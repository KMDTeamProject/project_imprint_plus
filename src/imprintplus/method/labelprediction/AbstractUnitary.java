package imprintplus.method.labelprediction;

import imprintplus.data.LongitudinalObject;

public abstract class AbstractUnitary implements InterfacePredictor{

	@Override
	/**
	 * Unitary predictor doesn't learn a model. When a LongitudinalObject
	 * is provided to them, they learn the model on the fly and do the 
	 * prediction. The learning is also encapsulated inside this method. 
	 */
	public abstract String predict(LongitudinalObject _obj);

}
