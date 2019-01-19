package com.sas.dvt.VA.testrunners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sas.dvt.VA.runners.ApplicationConfig;

import cucumber.runtime.ClassFinder;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.Runtime;

/**
 * Class  : TestRunner
 * PURPOSE: Read command line parameters
 *          Set runtime options for Cucumber 
 *          Run the test   
 */
public class TestRunner {
	// Initialize logger
	//private static Logger logger = null;
		
	public static void main(String args[]) {
		//Read test specific parameters from commandLine
		Map<String,Object> applicationConfigOptions =  parseCommandLineParams(args);
		
		//Test Name and Image names are compulsory parameters
    	String testName = (String)applicationConfigOptions.get("testName");
    	
    	System.out.println("testName: " + testName);
    	
    	if(testName == null || "".equals(testName) || testName.length() < 3 || applicationConfigOptions.get("imageNames") == null){
    		System.out.println("Usage : TestRunner -d <testName> -f <Image names separated by space>");
    		System.exit(-1);
    	}
    	
    	//Set application specific configurations using command line parameters
    	ApplicationConfig applicationConfig = new ApplicationConfig();
    	applicationConfig.getApplicationConfigurations(applicationConfigOptions);
    	//applicationConfig.showConfigurations();
    	
    	try{
    		//Initialize driver
    		//WebDriverManager webDriverManager = new WebDriverManager();
    		//webDriverManager.initializeDriver();
    		
    		//run test
    		runTest(testName);
    		
    		//close the driver
        	//webDriverManager.closeDriver();
    	}catch(Exception exception){
    		System.out.println("main():" + exception.toString());
    	}
	}
	
	/** 
	 * PURPOSE : Set cucumber runtime options and run the test
	 * @param : testName - Name of the test to run
  	 */
	private static void runTest(String testName){
		System.out.println("Test: " + testName + " run started");
		
		try{
			ClassLoader classLoader = TestRunner.class.getClassLoader();
	        ResourceLoader resourceLoader = new MultiLoader(classLoader);
	        ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
	        List<String> pluginList = new ArrayList<String>();
	        RuntimeOptions runtimeOptions = new RuntimeOptions(pluginList);
	        runtimeOptions.getFilters().add("@"+testName);
	        
	        /*
	        String applicationFeaturePath = MessageFormat.format(
	        		ApplicationConfig.featurePath, ApplicationConfig.applicationName);
	        		
	        String applicationStepDefinitions = MessageFormat.format(
	        		ApplicationConfig.stepDefinitionsPath, ApplicationConfig.applicationName);
	        */
	        runtimeOptions.getFeaturePaths().add(System.getProperty("user.dir") + ApplicationConfig.featurePath);
	        runtimeOptions.getGlue().add(ApplicationConfig.stepDefinitionsPath);
	        Runtime runtime = new cucumber.runtime.Runtime(resourceLoader, 
	        		classFinder, classLoader, runtimeOptions);
	        runtime.run();
	       
		}catch(Exception exception){
			//logger.error(exception.toString());
			System.out.println("runTest():" + exception.toString());
		}
		
		System.out.println("Test run completed");
	}
	
	/**
     * PURPOSE : Parse command line parameters and store them into map
     * @param args : command line parameters
     * @return map : Collection containing command line parameters
     */
    private static Map<String,Object> parseCommandLineParams(String[] args){
    	Map<String,Object> options = new HashMap<String,Object>();
    	String option = null;
    	
    	for(int i=0;i<args.length;i++){
    		if(args[i].startsWith("-")){
    			option = args[i].replace("-", "");
    			//-d and -f options are used to pass test name and image names
    			if("d".equals(option))
    				option = "testName";
    			else if("f".equals(option))
    				option = "imageNames";
    			options.put(option,null);
    		}else {
    			if(options.get(option)==null){
    				if("imageNames".equals(option))
    					options.put(option, args[i].split("\\s+"));
    				else
    					options.put(option, args[i]);
    			}
    		}
    	}
    	
    	return options;
    }
}