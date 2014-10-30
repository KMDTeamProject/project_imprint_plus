package imprintplus.experiment;

/**
 * All exceptions/errors that renders an experiment void,
 * must inherit from this exception. This way they all can 
 * be handled efficiently.
 * 
 * @author siddiqui16
 */
public class ImprintExpException extends Exception {

	private static final long serialVersionUID = 1L;

	public ImprintExpException(String _msg){
		super(_msg);
	}
}
