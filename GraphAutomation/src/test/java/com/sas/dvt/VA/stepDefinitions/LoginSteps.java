package com.sas.dvt.VA.stepDefinitions;

import com.sas.dvt.VA.pageObjects.LoginPage;
import com.sas.dvt.VA.runners.TestContext;

import cucumber.api.java.en.Given;

public class LoginSteps {
	
	TestContext testContext;
	LoginPage loginPage;
	
	public LoginSteps(TestContext context){
		testContext = context;
		loginPage = testContext.getPageObjectManager().getLoginPage(testContext);
	}

	@Given("^Login to VAN$")
	public void loginToVAN(){
		loginPage.navigateToURL();
		loginPage.loginToApplication();
	}
}
