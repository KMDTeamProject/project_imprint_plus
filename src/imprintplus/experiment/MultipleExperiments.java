package imprintplus.experiment;

import java.util.Collection;

public class MultipleExperiments {
//  ''' Runs an experiment based on the expObj. The results are stored in text 
//  files under the folder RESULT_FOLDER = <./experiments/result> '''
//  def __execute(self, _expInfo):
//      exp = Experiment(_expInfo)
//      exp.execute()
//      pass

	/**
	 * Reflective function used for handling incomplete input
	 * specification for reading experiment information.
	 * 
	 * @param _expRelDir
	 * @return
	 */
	public static Collection<Experiment> readExperiments(String _expRelDir) {
		return MultipleExperiments.readExperiments(_expRelDir, null);
	}
	
	/**
	 * Returns a collection of Experiment objects from the provided
	 * files. Experiments information are contained in text files. 
	 * By default, the information on experiment are stored 
	 * under <Commons.PATH_DEFAULT_EXPERIMENT> in separate text files.
	 * The location of the experiments file can also be provided via
	 * _expRelDir, however, this path must be relative to the default.
	 * If a list of files <_expFilenames> is not provided, 
	 * it reads all the files in the folder.
	 * 
	 * @param _expRelDir
	 * @param _expFilenames
	 * @return
	 */
	public static Collection<Experiment> readExperiments(String _expRelDir, Collection<String> _expFilenames) {
		String path_exp = Commons.PATH_DEFAULT_DATA;
		if (_expRelDir != null && _expRelDir.length()>0)
			path_exp+= "/" + _expRelDir; 
		
		// TODO: Implementation
		
		return null;
	}
	
	
//	class MultipleExperimentClass():
//    def __init__(self):
//        pass
//    
//        
//    ''' Generates a list of Experiment objects. The experiments are run for 
//    each Experiment Objects Experiments information are contained in text 
//    files. By default, the information on experiment are stored under 
//    EXP_SETTINGS_FOLDER = <./experiments/settings> in separate text files. 
//    If a list of files <_expFilenames> is not provided, it reads all the 
//    files in the folder. '''
//    def readExperiments(self, _expRelDir='', _expFilenames=[]):
//        listOfExpInfo = []
//        #-------------- Initialise path to settings directory with settings file
//        expDir = PATH_FOLDER_SETTINGS
//        if len(_expRelDir)>0:
//            expDir+=os.path.sep + _expRelDir
//        expFilenames = _expFilenames
//        if len(expFilenames)==0:
//            expFilenames = os.listdir(expDir)
//        #------------------- Save old path and change path to settings directory
//        oldPath = os.getcwd()
//        os.chdir(expDir)
//        for expFilename in expFilenames:
//            try:
//                #------------------ Create settings objects from text files only
//                if os.path.isfile(expFilename):
//                    expFilepath = expDir + os.path.sep + expFilename
//                    expSettingObj = self.readExperimentFile(expFilepath)
//                    listOfExpInfo.append(expSettingObj)
//            except Exception as e:
//                expMsg = '[IGNORE @ Error] %s. Read Error while reading %s'\
//                ''%(e, expFilename)
//                print expMsg
//        #------------------------------------------------------ Restore old path
//        os.chdir(oldPath)
//        return listOfExpInfo
//    
//    ''' Read each settings file and creates a setting object from it '''
//    def readExperimentFile(self, _expFilepath):
//        expSettingObj = ExperimentInfo(_expFilepath)
//        return expSettingObj
//    
//    ''' Reads information for multiple experiments and run them one by one '''
//    def runMultipleExperiments(self, _listOfExpInfo):
//        print 'Running Multiple Experiments: %d experiments object in total.'%len(_listOfExpInfo)
//        success = 0
//        for expInfo in _listOfExpInfo:
//            try:
//                self.__execute(expInfo)
//                success+=1
//                time.sleep(1)
//            except ExceptionSampleRatioTooBig as e:
//                filepath =  '.' + \
//                            os.path.sep + 'experiments' + \
//                            os.path.sep + 'settings' + \
//                            os.path.sep + '*' + \
//                            os.path.sep + expInfo.getParamVal(PARAM_NAME)
//                print e, ', value too big in experiment file %s.txt'%(filepath)
//                
//            #except Exception as e:
//                #print e
//                #raise e
//        print '%d/%d experiments successfully completed.'%(success,len(_listOfExpInfo))
//        
//    ''' Reads information for multiple experiments and run them one by one '''
//    def runSingleExperiment(self, _expSetting):
//        self.__execute(_expSetting)
	
}
