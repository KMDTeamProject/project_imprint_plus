package imprintplus.experiment;

import java.io.File;

import imprintplus.data.ImprintDataException;
import imprintplus.data.LongitudinalTable;

/**
 * 
 * @author siddiqui16
 */
public class Experiment {

	Parameters params;
	LongitudinalTable long_table;
	String exp_results_path;

	/**
	 * Initializes the experiment from the provided {@link Parameters} object.
	 * 
	 * @param _param
	 */
	public Experiment(Parameters _param) {
		params = _param;
	}

	private void preExecute() throws ImprintDataException, ImprintParamMissing,
			ImprintResultException {
		System.out.print("\nExecuting " + this.toString());
		long_table = LongitudinalTable.createTableFromParameters(params);
		createResultsDirectory();
	}

	/**
	 * Executes the experiment.
	 * 
	 * Reads the data into a {@link LongitudinalTable}. Executes the desired
	 * method as indicate in {@link Parameters} object.
	 * 
	 * @throws ImprintExpException
	 */
	public void execute() throws ImprintExpException {
		this.preExecute();

		// TODO Implementation
		if (true)
			throw new ImprintExpException(
					"Not Experiment.execute() is not implemented.");
	}

	/**
	 * Creates the results' directory.
	 * 
	 * @throws ImprintResultException
	 */
	private void createResultsDirectory() throws ImprintResultException {
		boolean pathExists = true;
		String results_path = Commons.PATH_DEFAULT_RESULT;
		exp_results_path = results_path + "/" + params.getExperimentName();

		File dir = new File(exp_results_path);
		if (!dir.exists())
			pathExists = dir.mkdirs();

		if (!pathExists) {
			String msg = "Cannot create results dir " + exp_results_path + ".";
			throw new ImprintResultException(msg);
		}
	}

	// the
	// the data and creating samples of data that are then dissemated to the
	// EvolutionaryPredictor via creating of multiple child processes. '''
	// class Experiment():
	//
	// #--------------------------------------- Create directory for the
	//
	// def execute(self):
	// start_time = timeit.default_timer()
	// #------------------------------------------------- Main code starts
	// here
	// runsCount = self.__expInfo.getParamVal(PARAM_RUNS)
	// sampleRatio = self.__expInfo.getParamVal(PARAM_SAMPLE_RATIO)
	// duplicates = self.__expInfo.getParamVal(PARAM_DUPLICATES)
	// expRunProcessList = {}
	// noOfCores = multiprocessing.cpu_count()
	// locksForCores = []
	// for i in range(noOfCores):
	// locksForCores.append(Lock())
	// #print '#Locks =', len(locksForCores)
	// outputManager = OutputManager()
	// outputManager.start()
	// objOutput = outputManager.OutputObject(self.__expInfo)
	// #objOutput = OutputObject(self.__expInfo)
	// for i in range(runsCount):
	// sampledPatients, nonsampledPatients =
	// self.__dataHandler.getBootstrapSampleOfPatients(sampleRatio,
	// duplicates)
	// assignedCore = i%noOfCores
	// assignedLock = locksForCores[assignedCore]
	// assignedLock.acquire()
	// assignedLock.release()
	// #experimentRun(assignedLock, self.__expInfo, i, objOutput,
	// sampledPatients, nonsampledPatients)
	// expRunProcess = Process(target=experimentRun, args=(assignedLock,
	// self.__expInfo, i, objOutput, sampledPatients, nonsampledPatients))
	// expRunProcessList[i] = expRunProcess
	// expRunProcess.start()
	// if (i%10==500):
	// print i, 'active =', len(multiprocessing.active_children()),
	// 'duration=%f'%(timeit.default_timer() - start_time)
	// for i in expRunProcessList:
	// expRunProcessList[i].join()
	// #--------------------------------------------------- Main code ends
	// here
	// end_time = timeit.default_timer()
	// duration = end_time - start_time
	// print 'duration=%f'%(duration), objOutput
	//
	// #--------------------------- Write duration info to the main output
	// file
	// relResultDir = self.__expInfo.getParamVal(PARAM_NAME)
	// expName = self.__expInfo.getParamVal(PARAM_NAME)
	// outputFilename = '%s_main.txt'%(expName)
	// outputFilepath = PATH_FOLDER_RESULT + os.path.sep + relResultDir +
	// os.path.sep + outputFilename
	//
	// #--------------------------------------------------------- Write to
	// file
	// fW = open(outputFilepath, 'w')
	// with fW:
	// i=0
	// labelsInfo = objOutput.getLabelsInfo()
	// for xId in labelsInfo:
	// xLabelInfo = labelsInfo[xId]
	// if i==0:
	// fW.write('id,' + ','.join(sorted(xLabelInfo)) + os.linesep)
	// i+=1
	// vals = []
	// for ltype in sorted(xLabelInfo):
	// vals.append('%s'%(xLabelInfo[ltype]))
	// fW.write(xId + ',' + ','.join(vals) + os.linesep)
	//
	// print '%s: duration=%f'%(expName, duration)

	public String toString() {
		String str = "Exp: " + params.toString();
		return str;
	}
}
