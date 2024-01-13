package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base{
	
	SearchPage searchPage;
	 HomePage homePage;
	
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		 driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		 homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void WindowClosed() {
		
		driver.quit();
		
	}
	
	
	@Test(priority=1)
	public void VerifySearchWithValidProduct() {
		
		searchPage = homePage.searchForAProduct(dataProp.getProperty("validPassword"));
		
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"valid Product HP Is Not Displayed In The Search Results");
		
	}
	
	@Test(priority=2)
	public void VerifySearchWithInvalidProduct() {

		searchPage = homePage.searchForAProduct(dataProp.getProperty("invalidPassword"));
	
		Assert.assertEquals(searchPage.retrieveNoSuchProductMessageText(),"abcd","No Product Message In Search Results Is Not Displayed");
	}
	
	@Test(priority=3,dependsOnMethods = {"VerifySearchWithValidProduct","VerifySearchWithInvalidProduct"})
	public void VerifySearchWithoutAnyProduct() {
		

		searchPage = homePage.clickOnSearchButton();
		
		Assert.assertEquals(searchPage.retrieveNoSuchProductMessageText(),dataProp.getProperty("noProductTextInSearchResults"),"No Product Message In Search Results Is Not Displayed");
		

	}

}
