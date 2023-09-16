package com.tuitorialninja.qa.testcases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends Base{
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public Register() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
		
	}
	
	
	@AfterMethod
	public void tearDown()	{
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword("abcd1234");
		registerPage.enterConfirmPassword("abcd1234");
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
;		assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page is Not Displayed");
		
	}
	
	@Test(priority = 2)
	
	public void verifyRegisteringAnAccountByProvidingAllFields()	{
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword("abcd1234");
		registerPage.enterConfirmPassword("abcd1234");
		registerPage.selectPrivacyPolicy();
		registerPage.selectYesNewsletterOption();
		accountSuccessPage = registerPage.clickOnContinueButton();
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page is Not Displayed");
		
	}
	
	@Test(priority = 3 )
	
	public void verifyRegesteringAccountWithExistingEmailAddress() {
		
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword("abcd1234");
		registerPage.enterConfirmPassword("abcd1234");
		registerPage.selectPrivacyPolicy();
		registerPage.selectYesNewsletterOption();
		registerPage.clickOnContinueButton();
		
		String actualWarningMessage = registerPage.retrieveDuplicateEmailAddressWarning();
		assertEquals(actualWarningMessage,dataProp.getProperty("duplicateEmailWarning"),"Warning Message about duplicate email is not displayed.");
		
		
	}
	
	@Test(priority =4 )
	
	public void verifyRegisteringAnAccountWithoutProvidingFillingAnyDetails() {
			
		registerPage.clickOnContinueButton();
		
		String actualPrivacyPolicyWarning = registerPage.retrievePrivacyWarning();
		assertEquals(actualPrivacyPolicyWarning,dataProp.getProperty("privacyPolicyWarning"),"Privacy policy warning message is not displayed");
		
		String firstNameWarning = registerPage.retrieveFirstNameWarning();
		assertEquals(firstNameWarning,dataProp.getProperty("firstNameWarning"),"First name warning not found.");
				
	}
	

}
