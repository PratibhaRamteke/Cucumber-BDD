package com.sas.dvt.VA.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class  : ElementActionHandler
 * PURPOSE: Manages and performs all the actions of the web element
 */
public class ElementActionHandler {

	//private static Logger logger = LoggerFactory.getLogger(Controls.class);
	WebDriver driver;
	
	public ElementActionHandler(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * PURPOSE : Perform click event on 'Button/CheckBox/RadioButton'
	 * @param webElement - Component(Button/CheckBox/RadioButton) web element to perform action on it
	 */
	public void clickElement(WebElement webElement) {		
		try {			
			if(webElement.isDisplayed())
				webElement.click();
		}catch(Exception exception) {				
				//logger.error(exception.toString());
		}	
	}
	
	/**
	 * PURPOSE : Enter the text/value in the 'TextBox' 
	 * @param webElement -'TextBox' component web element to perform action on it
	 * @param textValue - string/text to enter in TextBox
	 */
	public void setText(WebElement webElement, String textValue){
		try {			
			if(webElement.isDisplayed()) {			
				webElement.sendKeys(textValue);
				//webElement.sendKeys(Keys.ENTER);
			}
		}catch(Exception exception) {
			//logger.error(exception.toString());
		}	
	}
	
	/**
	 * PURPOSE : To change/set any slider value 
	 * @param sliderLabelElement - 'Slider label' component web element
	 * @param sliderInputElement - 'Slider Input/TextBox' component web element
	 * @param textValue - value to enter in slider 'Input/TextBox' component
	 */
	public void setSliderValue(WebElement sliderLabelWebElement, WebElement sliderInputWebElement, String textValue) {
		try {
			// Perform a click on slider Label 
			clickElement(sliderLabelWebElement);			
			
			// Enter value in slider Input/TextBox
			setText(sliderInputWebElement, textValue);							
		}catch(Exception exception) {
			//logger.error(exception.toString());
		}
	}
		
	/**
	 * PURPOSE : To select value/item from 'DropDown'
	 * @param dropDownWebElement - 'DropDown' component web element
	 * @param popoverWebElement - 'Popover' component web element which comes after clicking 'DropDown'
	 * @param itemToSelect - item/value to select from 'DropDown' component 
 	 */
	public void selectValueFromDropDown(WebElement dropDownWebElement,WebElement popoverWebElement,String itemToSelect) {
		List<WebElement> popoverElements = null;		
		try {				
			// Click on DropDown
			clickElement(dropDownWebElement );
			
			// Fetch all values(list of elements) from Popover
			popoverElements = popoverWebElement.findElements(By.tagName("li"));			
			
			//Select item from the list
			for(WebElement popoverElement: popoverElements) {					
				if(popoverElement.getText().equalsIgnoreCase(itemToSelect)) {
					clickElement(popoverElement);
					break;
				}
			}		
		}catch(Exception exception) {
			//logger.error(exception.toString());
		}		
	}
	
	/** 
	 * PURPOSE: The method will drag the graph object into the required area.
	 * @param sourceGraphWebElement - the name of the graph object to be dragged (e.g. Bar Chart, Needle Plot)
	 * @param targetCanvasWebElement - the canvas element where the graph needs to be dragged
	 * @param graphPosition - the canvas position where the graph needs to be dragged (e.g. TOPLEFT, BOTTOMRIGHT)
	 */
	public void dragAndDropGraph(WebElement sourceGraphElement, WebElement targetCanvasElement, String graphPosition) {
		Actions action = new Actions(driver);
		action.clickAndHold(sourceGraphElement).perform();
						
		switch(graphPosition){
			case "TOPLEFT":
				action.moveToElement(targetCanvasElement, 580, 60).perform();
				break;
			case "BOTTOMLEFT":
				action.moveByOffset(150, 100).perform();
				break;
			case "TOPRIGHT":
				action.moveToElement(targetCanvasElement, 1800, 333).perform();
				break;
			case "BOTTOMRIGHT":
				action.moveByOffset(580, 900).perform();
				break;
			default:
				//logger.error("Please enter correct graph component and position");
				System.out.println("Please enter correct graph component and position");
		}

		action.release(targetCanvasElement).perform();
		//action.release().perform();		
		//logger.info("Graph object dragged successfully");
	}	
	
	/**
	 * PURPOSE : VisualAnalytics - To select color from color picker control
	 * @param selectColorButtonWebElement - select color picker web element
	 * @param colorIconButtonWebElement - color Icon button web element e.g 'Black (#000000)' Icon Button 
	 */
	public void selectColorVA(WebElement selectColorButtonWebElement, WebElement colorIconButtonWebElement) {
		try {
			// Click on select Color picker button
			clickElement(selectColorButtonWebElement);
			
			// Click on specific color button
			clickElement(colorIconButtonWebElement);						
		}catch(Exception exception) {
			//logger.error(exception.toString());
		}
	}
	
	/**
	 * PURPOSE : GraphBuilder - To select color value from 'DropDown'
	 * @param dropDownWebElement - 'DropDown' component web element
	 * @param popoverWebElement - 'Popover' component web element which comes after clicking 'DropDown'
	 * @param colorToSelect - color to select select from 'DropDown' component (e.g 'Overflow font color')
 	 */	
	public void selectColorGB(WebElement dropDownWebElement,WebElement popoverWebElement,String colorToSelect) {
		List<WebElement> popoverElements = null;
		try {							
			// Click on DropDown
			clickElement(dropDownWebElement );
			
			// Fetch all values(list of elements) from Popover - li 
			popoverElements = popoverWebElement.findElements(By.tagName("li"));			
			
			//Select item from the list >> span
			for(WebElement popoverElement: popoverElements) {									
				// Fetch all values(list of elements) from li > span tag
				List<WebElement> spanElements = popoverElement.findElements(By.tagName("span"));			
				for(WebElement spanElement : spanElements) {					
					if(spanElement.getText().equalsIgnoreCase(colorToSelect)){							
						clickElement(spanElement);
						break;							 		
					}
				}
			}
		}catch(Exception exception) {
			//logger.error(exception.toString());
		}	
	}
}