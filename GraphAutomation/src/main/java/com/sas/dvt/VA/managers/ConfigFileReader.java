package com.sas.dvt.VA.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Class  : ConfigFileReader
 * PURPOSE: Manages all the operations of the web driver 
 */
public class ConfigFileReader {
	
	Properties properties;
	FileInputStream fileInput ;
	
	/** 
	 * PURPOSE : Read application configuration file
	 * @param configFilePath : Path of the file
	 * @param isAbsolutePath : Is Absolute Path
	 * @return Properties : Collection of properties
  	 */
	public Properties readApplicationConfiguration(String configFilePath, boolean isAbsolutePath){
		
		properties = new Properties();
		String filepath = "";
		String userDir = "";
		
		try{
			if(!isAbsolutePath)
				userDir = System.getProperty("user.dir") + File.separator;
			
			filepath =  userDir + configFilePath;
			fileInput = new FileInputStream(new File(filepath));									
			
			//Load properties file			
			properties.load(fileInput);
			fileInput.close();
		}catch (FileNotFoundException exception) {
			//log this
			System.out.println("FileNotFoundException : \n" + exception.toString());			
		}catch (IOException exception) {
			System.out.println("IOException : \n" + exception.toString());
		}
		
		return properties;
	}
	
	/** 
	 * PURPOSE : Retrieve value of the property 
	 * @param propertyKey : Name of the property
	 * @param properties : Collection of properties
	 * @return String : Value of the Key
  	 */
	public String getPropertyValue(String propertyKey, Properties properties){
		//retrieve the specified object from the object list
        String propertyValue = properties.getProperty(propertyKey);
        
        if(propertyValue != null)
        	return propertyValue;
       	else throw new RuntimeException(propertyKey + " not specified in the property file");
	}
}
