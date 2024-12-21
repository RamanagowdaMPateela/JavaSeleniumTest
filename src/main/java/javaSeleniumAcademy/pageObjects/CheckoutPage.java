package javaSeleniumAcademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javaSeleniumAcademy.Utility.Utility;

public class CheckoutPage extends Utility {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".action__submit")
	WebElement placeOrderButton;
	
	@FindBy(css="input[placeholder=\"Select Country\"]")
	WebElement selectCountry;
	
	@FindBy(css=".ta-results")
	WebElement countryList;
	
	@FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]")
	WebElement selectCountryFromList;
	
	public void selectCountry() {
		
		selectCountry.click();
		selectCountry.sendKeys("india");
		waitForWebElementVisible(countryList);
		selectCountryFromList.click();
		
	}
	
	public ConfirmationPage placeOrder() {
		placeOrderButton.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	

}
