package com.tuitorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class Search extends Base{
	
	SearchPage searchPage;
	HomePage homePage;
	
	public Search() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown()	{
		driver.quit();
	}
	
	
	@Test(priority = 1)
	
	public void verifySearchWithValidProduct () {
		
		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusofAppleProduct(),"Valid Apple product is not displayed in Search result");
		
		
	}

	@Test(priority = 2)
	
	public void verifySearchWithInvalidProduct() {
		
		searchPage = homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));	
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),"There is no product that matches the search criteria.","Expected message did not display");
		
		
	}
	
	@Test(priority = 3)
	
	public void verifySearchWithoutAnyProduct() {
		
		searchPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),"There is no product that matches the search criteria.","Expected message did not display");
		
	}
}
