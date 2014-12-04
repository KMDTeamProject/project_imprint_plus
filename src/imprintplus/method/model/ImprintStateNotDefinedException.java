/**
 * 
 */
package imprintplus.method.model;

import imprintplus.experiment.ImprintExpException;
/**
 * @author Sandhya
 *
 */
public class ImprintStateNotDefinedException extends ImprintExpException {

    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public ImprintStateNotDefinedException(String _msg) {
        super(_msg);
    }
}
