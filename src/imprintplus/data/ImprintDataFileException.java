package imprintplus.data;

import imprintplus.experiment.ImprintExpException;

/**
 * Exceptions dealing with all the errors in data file.
 * 
 * @author siddiqui16
 */
public class ImprintDataFileException extends ImprintExpException {
	private static final long serialVersionUID = 1L;

	public ImprintDataFileException(String _msg) {
		super(_msg);
	}
}
