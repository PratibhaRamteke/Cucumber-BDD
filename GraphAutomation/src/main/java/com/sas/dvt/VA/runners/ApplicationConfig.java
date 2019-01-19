package com.sas.dvt.VA.runners;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import com.google.common.collect.Maps;
import com.sas.dvt.VA.enums.ApplicationType;
import com.sas.dvt.VA.managers.ConfigFileReader;

/**
 * Class  : ApplicationConfig
 * PURPOSE: Set application configurations  
 */
public class ApplicationConfig {
	
	final static String CONFIG_FILE_PATH = "/src/test/resources/com.sas.dvt.VA.configs/applicationConfiguration.properties";
	
	private static String vaserverConfigFile = "";
	public static String track = "";
	public static String currentServer = "";
	public static String browser = "";
	public static String chromeDriverPath = "";
	
	public static String user = "";
	public static String password = "";
	
	public static String testName = "";
	public static String applicationName = "";
	public static String applicationURL = "";

	public static String featurePath = "";
	public static String stepDefinitionsPath = "";
	public static String locatorsPath = "";

	public static String[] imageNames;
	public static int imageHeight;
	public static int imageWidth ;
	public static String screenshotPath = "";
	
	/** 
	 * PURPOSE: Get the application configurations
  	 */
	public void getApplicationConfigurations(Map<String, Object> applicationConfigOptions){
		//Read configuration properties from configuration file
		ConfigFileReader configReader = new ConfigFileReader();
        Properties applicationConfigProperties = configReader.readApplicationConfiguration(CONFIG_FILE_PATH,false);
        
		//Store properties to hashmap
        Map<String, Object> applicationConfigMap = new HashMap<String, Object>(Maps.fromProperties(applicationConfigProperties));
        setApplicationConfigurations(applicationConfigMap);

        //Get the current server from VA Server Config file
        Properties vaServerConfigProperties = configReader.readApplicationConfiguration(vaserverConfigFile,true);
        currentServer = vaServerConfigProperties.getProperty(track); 
        
        testName = (String)applicationConfigOptions.get("testName");
        imageNames = (String[]) applicationConfigOptions.get("imageNames");
        
        //Get the application type
        ApplicationType applicationType = getApplicationType(testName);
        applicationName = applicationType.toString().toLowerCase();
        
        //Get the application URL
        applicationURL = getApplicationURL(applicationType);
	}
	
	/** 
	 * PURPOSE: Set the application configurations
	 * @param : configOptions - Command Line options
  	 */
	public void setApplicationConfigurations(Map<String, Object> configOptions){
		//First get all the config options from config file and then override with
		//command line options
		Field[] fields = ApplicationConfig.class.getDeclaredFields();
		Object value = null;
		try {
			for(Field field : fields){
				value = configOptions.get(field.getName());
				if(value !=null) {
					//Initialize integer fields 
					if( value instanceof String && StringUtils.isNumeric((String)value))
						field.set(null, Integer.parseInt((String)value));
					else
						field.set(null,value);
				}
			}
		}catch(Exception exception){
			//logger.error(e.getMessage());
			System.out.println("setApplicationConfigurations:" + exception.toString());
		}
	}
	
	/** 
	 * PURPOSE: Display application configuration information
  	 */
	public void showConfigurations(){
		Field[] fields = ApplicationConfig.class.getDeclaredFields();
		String configuration = "";
		
		try {
			for(Field field : fields){
				configuration = field.getName()+" : " ;

				if(field.getType().isArray() && field.getType().getName().length() > 2){
					for(String fieldName : (String[])field.get(null)){
						configuration+=" "+fieldName;
					}
					configuration+=" ]";
				}else
					configuration+=field.get(null);

				//logger.info(configuration);
				System.out.println(configuration);
			}
		}catch(Exception exception){
			//logger.error(exception.toString());
			System.out.println("showConfigurations:" + exception.toString());
		}
	}
	
	/**
     * PURPOSE: Get application type from the test name
     * @param testName : Name of the test
     * @return ApplicationType : Type of the application
     */
    private ApplicationType getApplicationType(String testName){
		String testPrefix = testName.substring(0, 3);
		
		switch(testPrefix.toLowerCase()){
    		case "vgb": 
    			return ApplicationType.VISUALGRAPHBUILDER;
    		case "van": 
    			return ApplicationType.VISUALANALYTICSNEXT;
    		case "vtd": 
    			return ApplicationType.VISUALTHEMEDESIGNER;
    		case "vav": 
    			return ApplicationType.VISUALANALYTICSVIEWER;
    		default:
    			System.out.println("Error : Incorrect test name prefix...");
		}
		return null;
    }
    
    /**
	 * Purpose: This function is used to launch Application URL based on application type
	 * @param applicationType: type of application e.g VAN/VAV/VGB/VTD
	 */
	private String getApplicationURL(ApplicationType applicationType) {
		String applicationURL="http://"+ ApplicationConfig.currentServer + ApplicationConfig.applicationName;
		return applicationURL;
	}
}