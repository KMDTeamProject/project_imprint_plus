package imprintplus.experiment;

import java.io.File;
import java.util.ArrayList;

/**
 * List of functions that read info on experiments and generate
 * {@link Experiment} objects.
 * 
 * @author siddiqui16
 */
public class ExperimentReader {

	/**
	 * Reflective function used for handling incomplete input specification for
	 * reading experiment information.
	 * 
	 * @param _expRelDir
	 * @return
	 */
	public static ArrayList<Experiment> readMultipleExperiments(String _rel_path) {
		return ExperimentReader.readMultipleExperiments(_rel_path, null);
	}

	/**
	 * Returns a collection of Experiment objects from the provided files.
	 * Experiments information are contained in text files. By default, the info
	 * on experiment are stored under <PATH_DEFAULT_EXPERIMENT> in separate text
	 * files. The location of the experiments file can also be provided via
	 * _rel_path, however, this path must be relative to the default.
	 * 
	 * @param _expRelDir
	 * @param _expFilenames
	 * @return
	 */
	public static ArrayList<Experiment> readMultipleExperiments(
			String _rel_path, ArrayList<String> _file_names) {
		/*
		 * Initialize path to settings directory with Parameters file.
		 */
		String exp_path = Commons.PATH_DEFAULT_EXPERIMENT;
		File exp_path_file = null;
		if (_rel_path != null && _rel_path.length() > 0)
			exp_path += _rel_path + "/";
		exp_path_file = new File(exp_path);
		if (!exp_path_file.isDirectory()) {
			// TODO: Either generate an Exception or ignore.
		}

		/*
		 * Create File objects from the provided list of files in <_file_names>.
		 * If a list of files <_file_names> is not provided, read all the files
		 * in the folder <exp_path>.
		 */
		ArrayList<String> exp_files = new ArrayList<String>();
		if (_file_names != null && _file_names.size() > 0) {
			for (int i = 0; i < _file_names.size(); i++) {
				String file_path = exp_path + "/" + _file_names.get(i);
				exp_files.add(file_path);
			}
		} else {
			for (File f : exp_path_file.listFiles())
				if (f.isFile())
					exp_files.add(f.getAbsolutePath());
		}

		/*
		 * Generate Experiment objects from the list of provided parameter files
		 */
		ArrayList<Experiment> list_exp = new ArrayList<Experiment>();
		for (String file_path : exp_files) {
			try {
				list_exp.add(readExperimentFromParametersFile(file_path));
			} catch (ImprintParamFileException e) {
				System.out
						.print("\nWARNING: cannot create an Experiment from the parameter file "
								+ file_path);
			}
		}
		return list_exp;
	}

	/**
	 * Read each settings file and creates a setting object from it.
	 * 
	 * @param _file_path
	 * @return
	 * @throws ImprintParamFileException
	 */
	public static Experiment readExperimentFromParametersFile(String _file_path)
			throws ImprintParamFileException {
		File exp_file = new File(_file_path);
		Parameters params = new Parameters(exp_file);
		Experiment exp = new Experiment(params);
		return exp;
	}
}
