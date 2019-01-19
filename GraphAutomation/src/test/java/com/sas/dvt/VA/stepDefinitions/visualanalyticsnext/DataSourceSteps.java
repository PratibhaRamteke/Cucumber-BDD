package com.sas.dvt.VA.stepDefinitions.visualanalyticsnext;

import com.sas.dvt.VA.pageObjects.visualanalyticsnext.DataSourcePage;
import com.sas.dvt.VA.runners.TestContext;

import cucumber.api.java.en.When;

public class DataSourceSteps {

	TestContext testContext;
	DataSourcePage dataSourcePage;
	
	public DataSourceSteps(TestContext context) {
		testContext = context;
		dataSourcePage = testContext.getPageObjectManager().getDataSourcePage(testContext); 
	}
	
	@When("^Select \"([^\"]*)\" dataset$")
	public void selectDataSource(String dataSetName){
	    dataSourcePage.clickDataButton();
	    dataSourcePage.selectDataSource(dataSetName);
	}
}
