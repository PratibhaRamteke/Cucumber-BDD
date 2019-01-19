package com.sas.dvt.VA.stepDefinitions.visualanalyticsnext;

import com.sas.dvt.VA.pageObjects.visualanalyticsnext.HomePage;
import com.sas.dvt.VA.runners.TestContext;

import cucumber.api.java.en.When;

public class HomePageSteps {

	TestContext testContext;
	HomePage homePage;
	
	public HomePageSteps(TestContext context){
		testContext = context;
		homePage = testContext.getPageObjectManager().getHomePage(testContext);
	}
	
	@When("^Drag and drop \"([^\"]*)\" at \"([^\"]*)\"$")
	public void dragAndDropGraphObject(String graphName, String graphPosition) {
	    homePage.dragAndDropGraphObject(graphName, graphPosition);
	}
}
