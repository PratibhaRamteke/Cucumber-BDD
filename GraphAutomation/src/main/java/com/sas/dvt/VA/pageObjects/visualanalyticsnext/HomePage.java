package com.sas.dvt.VA.pageObjects.visualanalyticsnext;

import java.text.MessageFormat;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sas.dvt.VA.managers.PageLocatorManager;
import com.sas.dvt.VA.runners.ApplicationConfig;
import com.sas.dvt.VA.runners.TestContext;
import com.sas.dvt.VA.util.ElementActionHandler;

public class HomePage {

	WebDriver driver;
	WebDriverWait webDriverWait;
	Properties homePageProperties;
	PageLocatorManager pageLocatorManager;
	ElementActionHandler elementActionHandler;
	
	public HomePage(WebDriver driver, TestContext testContext){
		this.driver = driver;
		webDriverWait = testContext.getWebDriverManager().getWebDriverWait();
		
		elementActionHandler = testContext.getElementActionHandler();
		pageLocatorManager = testContext.getPageLocatorManager();
		homePageProperties = pageLocatorManager.getPageProperties("HomePage.properties",true);
	}
	
	/** 
	 * PURPOSE: Drag the graph object into the required area
	 * @param graphName - the name of the graph object to be dragged (e.g. Bar Chart, Needle Plot)
	 * @param graphPosition - Position where the graph needs to be dragged (e.g. TOPLEFT, BOTTOMRIGHT)
	*/
	public void dragAndDropGraphObject(String graphName, String graphPosition) {							
		//Click on Objects Tab
		clickObjectsTab();
		
		//Search GraphName in the Objects textbox
		searchGraphObject(graphName);
		
		//Drag and drop graphObject on graphview/canvas
		dragAndDropGraph(graphName, graphPosition);
		
		System.out.println("Graph object dragged successfully");
	}
	
	private void clickObjectsTab() {
		WebElement objectsTab = driver.findElement(
				pageLocatorManager.getLocatorValue("objectsTab", homePageProperties));
		elementActionHandler.clickElement(objectsTab);	
	}
	
	private void searchGraphObject(String graphName){
		WebElement objectsSearchTextbox = driver.findElement(
				pageLocatorManager.getLocatorValue("objectsSearchTextbox", homePageProperties));
		elementActionHandler.setText(objectsSearchTextbox, graphName);
	}
	
	private void dragAndDropGraph(String graphName, String graphPosition){
		By graphObject = pageLocatorManager.getLocatorValue("graphObject", homePageProperties);
		String graphObjectLocatorValue = graphObject.toString().split(":")[1];
		
		graphObjectLocatorValue = MessageFormat.format(graphObjectLocatorValue, graphName);
		
		System.out.println("graphObject:" + graphObjectLocatorValue);
		
		/*WebElement sourceGraphElement = driver.findElement(
				pageLocatorManager.getLocatorValue("graphObject", homePageProperties));
		
		WebElement targetCanvasElement = driver.findElement(
				pageLocatorManager.getLocatorValue("canvas", homePageProperties));
		
		elementActionHandler.dragAndDropGraph(sourceGraphElement, targetCanvasElement, graphPosition);*/
	}
}
