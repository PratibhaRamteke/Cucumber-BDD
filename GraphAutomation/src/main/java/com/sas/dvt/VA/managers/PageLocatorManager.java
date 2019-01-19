package com.sas.dvt.VA.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.openqa.selenium.By;

import com.sas.dvt.VA.runners.ApplicationConfig;

/**
 * Class  : PageLocatorManager
 * PURPOSE: Manages operations of the Locators
 */
public class PageLocatorManager {

	Properties pageProperties;
	FileInputStream  fileInput ;
	
	/**
	 * Purpose: Get the properties of the page
	 * @param propertiesFile - Name of the property file 
	 * @return Properties
	 */
	public Properties getPageProperties(String propertiesFile, boolean isApplicationSpecific) {
		pageProperties = new Properties();		
		String locatorFilePath = "";
		
		try {	
			if(isApplicationSpecific)
				locatorFilePath = MessageFormat.format(
	        		ApplicationConfig.locatorsPath, ApplicationConfig.applicationName);
			else
				locatorFilePath = MessageFormat.format(ApplicationConfig.locatorsPath, "");
			
			String filepath = System.getProperty("user.dir")+ locatorFilePath + propertiesFile;

			//Read file
			fileInput = new FileInputStream(new File(filepath));									
		
			//load properties file			
			pageProperties.load(fileInput);
			fileInput.close();
		}catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException : \n" + e.toString());			
		}catch (IOException e) {
			System.out.println("IOException : \n" + e.toString());
		}
		
		return pageProperties;
	}
	
	/**
	 * Purpose: Get the locator value
	 * @param elementLocator - Name of the locator
	 * @param pageProperties - Properties of the page 
	 * @return By
	 */
	public By getLocatorValue(String elementLocator, Properties pageProperties) {
        
		String locatorKey = pageProperties.getProperty(elementLocator);
        locatorKey = locatorKey.substring(1, locatorKey.length()-1);
        
        int index = locatorKey.indexOf("=");
        String locatorType = locatorKey.substring(0, index);
        String locatorValue = locatorKey.substring(index+1);
		
        System.out.println("locatorType: " + locatorType + "\t locatorValue: " +locatorValue);
        
    	By value = null;
        switch (locatorType.toLowerCase()) {
			case "id":
					value = By.id(locatorValue);break;
			case "name":
					value = By.name(locatorValue);break;
			case "classname":
					value = By.className(locatorValue);break;
			case "tagname":
					value = By.tagName(locatorValue);break;
			case "cssselector":
					value = By.cssSelector(locatorValue);break;
			case "xpath":
					value = By.xpath(locatorValue);break;
			case "linktext":
					value = By.linkText(locatorValue);break;
			case "partiallinktext":
					value = By.partialLinkText(locatorValue);	break;		
			default:
				System.out.println("Unknown locator type '" + locatorType + "'");			
		}
        
        return value;
    }	
}
