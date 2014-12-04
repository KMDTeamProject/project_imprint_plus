/**
 * 
 */
package imprintplus.method.model;

import imprintplus.experiment.ImprintExpException;

/**
 * @author Sandhya
 *
 */
public class ImprintTransitionAlreadyDefinedException extends ImprintExpException {

	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;

	    /**
	     * @param message
	     */
	    public ImprintTransitionAlreadyDefinedException(String _msg) {
	        super(_msg);
	    }
}
