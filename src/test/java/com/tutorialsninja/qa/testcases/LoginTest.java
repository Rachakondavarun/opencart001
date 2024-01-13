package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utilis.Utilities;


public class LoginTest extends Base {
	
	LoginPage loginPage;
	
	public LoginTest() {
		super();
	}
	
	public WebDriver driver ;
	
	@BeforeMethod
	public void setup() {
		
		
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
	}
	
	@AfterMethod
	public void WindowClosed() {
		  
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void VerifyLoginWithValidCredentials(String email,String password) {
		
		AccountPage accountPage=loginPage.login(email, password);
		
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit Your Account Information is not dispalyed");
		
		
	}
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void VerifyLoginWithInvalidCredentials() {
		
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("InvalidPassword"));

		Assert.assertTrue(loginPage.retrieveEmailPasswordNoMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")),"Expected Warning Message is not displayed");
		
		
	}
	
	@Test(priority=3)
	public void VerifyLoginWithInvalidEmailAndValidPassword() {
		
        loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		
		String actualWarningMessage= loginPage.retrieveEmailPasswordNoMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not displayed");
		
		
		
	}
	@Test(priority=4)
	public void VerifyLoginWithValidEmailAndInvalidPassword() {
		
	    loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("InvalidPassword"));

		String actualWarningMessage= loginPage.retrieveEmailPasswordNoMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not displayed");
		
		
		
	}
	@Test(priority=5)
	public void VerifyLoginWithoutProvidingAnyCredentials() {
		
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage= loginPage.retrieveEmailPasswordNoMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not displayed");
			
	}


}
