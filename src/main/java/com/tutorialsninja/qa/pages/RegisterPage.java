package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	//Objects
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name=\"newsletter\"][@value='1']")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id=\"input-firstname\"]/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id=\"input-lastname\"]/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id=\"input-email\"]/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id=\"input-telephone\"]/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id=\"input-password\"]/following-sibling::div")
	private WebElement passwordWarning;
	
	
	public RegisterPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Actions
	public void enterFirstName(String firstNameText) {
		
		firstNameField.sendKeys(firstNameText);
	}
	public void enterLastName(String lastNameText) {
		
		lastNameField.sendKeys(lastNameText);
	}
	public void enterEmailAddress(String emailText) {
		
		emailField.sendKeys(emailText);
	}
	public void enterTelephone(String telephoneText) {
		
		telephoneField.sendKeys(telephoneText);
	}
	public void enterPassword(String passwordText) {
		
		passwordField.sendKeys(passwordText);
	}
	public void enterConfirmPassword(String passwordText) {
		
		confirmPasswordField.sendKeys(passwordText);
	}
	public void selectPrivacyPolicy() {
		
		privacyPolicyField.click();
	}
	public AccountSuccessPage clickOnContinueButton() {
		
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	public void selectYesNewsLetterOption() {
		
		yesNewsLetterOption.click();
	}
	public String retrieveDuplicateEmailAddressWarning() {
		
		String duplicateEmailAddressWarningText= duplicateEmailAddressWarning.getText();
		return duplicateEmailAddressWarningText;
	}
	public String retrievePrivacyPolicyWarning() {
		
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	public String retrieveFirstNameWarning() {
		
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}
	public String retrieveLastNameWarning() {
		
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
		
	}
    public String retrieveEmailWarning() {
		
		String emailAddressText = emailWarning.getText();
		return emailAddressText;
		
	}
    public String retrieveTelephoneWarning() {
		
		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
		
	}
    public String retrievePasswordWarning() {
		
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
		
	}
    public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText) {
    	
    	firstNameField.sendKeys(firstNameText);
    	lastNameField.sendKeys(lastNameText);
    	emailField.sendKeys(emailText);
    	telephoneField.sendKeys(telephoneText);
    	passwordField.sendKeys(passwordText);
    	confirmPasswordField.sendKeys(passwordText);
    	privacyPolicyField.click();
    	continueButton.click();
    	
		return new AccountSuccessPage(driver);

    	
    }
    public AccountSuccessPage registerWithAllFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText) {
    	
    	firstNameField.sendKeys(firstNameText);
    	lastNameField.sendKeys(lastNameText);
    	emailField.sendKeys(emailText);
    	telephoneField.sendKeys(telephoneText);
    	passwordField.sendKeys(passwordText);
    	confirmPasswordField.sendKeys(passwordText);
    	yesNewsLetterOption.click();
    	privacyPolicyField.click();
    	continueButton.click();
    	
		return new AccountSuccessPage(driver);
    	
    }
    public boolean displayStatusOfWarningMessage(String expectedPrivacyPolicyWarning,String expectedFirstNameWarning,String expectedLastNameWarning,String expectedEmailWarning,String expectedTelephoneWarning,String expectedPasswordWarning) {
    	
    	boolean privacyPolicyWarningStatus = privacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarning);
    	boolean firstNameWarningStatus = firstNameWarning.getText().equals(expectedFirstNameWarning);
    	boolean lastNameWarningStatus = lastNameWarning.getText().equals(expectedLastNameWarning);
    	boolean emailWarningStatus =  emailWarning.getText().equals(expectedEmailWarning);
    	boolean telephoneWarningStatus =  telephoneWarning.getText().equals(expectedTelephoneWarning);
    	boolean passwordWarningStatus = passwordWarning.getText().equals(expectedPasswordWarning);
    
    	return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;
    }
    

}
