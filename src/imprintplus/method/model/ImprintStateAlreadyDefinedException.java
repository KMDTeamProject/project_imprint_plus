/**
 * 
 */
package imprintplus.method.model;

import imprintplus.experiment.ImprintExpException;

/**
 * @author Sandhya
 *
 */
public class ImprintStateAlreadyDefinedException extends ImprintExpException {

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public ImprintStateAlreadyDefinedException(String _msg) {
        super(_msg);
    }
}
