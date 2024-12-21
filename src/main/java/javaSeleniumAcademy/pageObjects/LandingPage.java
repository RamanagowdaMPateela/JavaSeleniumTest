package javaSeleniumAcademy.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javaSeleniumAcademy.Utility.Utility;

public class LandingPage extends Utility {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userName;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(css = "#login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public void navigateToURl() {
		driver.get("https://rahulshettyacademy.com/client");

	}

	public ProductCatalogPage loginToSite(String userNames, String passwords) {
		userName.sendKeys(userNames);
		password.sendKeys(passwords);
		loginButton.click();

		return new ProductCatalogPage(driver);
	}
	
	public String getErrorMessage() {
		waitForWebElementVisible(errorMessage);
		return errorMessage.getText();
	}

}
