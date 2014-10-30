package imprintplus.experiment;

/**
 * 
 * @author siddiqui16
 */
public class Experiment {
	Parameters params;

	public Experiment() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Executes the experiment from the provided ExperimentInfo object.
	 */
	public void execute() {
		// TODO Auto-generated constructor stub
	}

	// ''' Initialises the experiment. It is also responsible for reading
	// the
	// the data and creating samples of data that are then dissemated to the
	// EvolutionaryPredictor via creating of multiple child processes. '''
	// class Experiment():
	// def __init__(self, _expInfo):
	// self.__expInfo = _expInfo
	// #------------------------ Initialise raw data handler for the
	// experiment
	// self.__dataHandler = RawDataHandler()
	// attId = self.__expInfo.getParamVal(PARAM_ATT_ID)
	// attLabel = self.__expInfo.getParamVal(PARAM_ATT_LABEL)
	// attTimepoint = self.__expInfo.getParamVal(PARAM_ATT_TIMEPOINT)
	// ignoreList = self.__expInfo.getParamVal(PARAM_IGNORE_LIST)
	// dataFilename = self.__expInfo.getParamVal(PARAM_DATA_FILE)
	// dataRelPath = self.__expInfo.getParamVal(PARAM_DATA_REL_PATH)
	// if len(dataRelPath)>0:
	// dataFilename = dataRelPath + os.path.sep + dataFilename
	// dataFilepath = PATH_FOLDER_DATA + os.path.sep + dataFilename
	//
	// #-------------------------------------- Read data from the provided
	// file
	// self.__dataHandler.readData(dataFilepath, _ignore=ignoreList, \
	// _id=attId, _label=attLabel, _timepoint=attTimepoint)
	//
	// #--------------------------------------- Create directory for the
	// output
	// relResultDir = self.__expInfo.getParamVal(PARAM_NAME)
	// oldpath = os.getcwd()
	// os.chdir(PATH_FOLDER_RESULT)
	// if not os.path.exists(relResultDir):
	// os.mkdir(relResultDir)
	// os.chdir(oldpath)
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
}
