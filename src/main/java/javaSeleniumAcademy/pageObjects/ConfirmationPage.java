package javaSeleniumAcademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector()).getText();
	@FindBy(css=".hero-primary")
	WebElement orderConfirmMessage;
	
	public String getSuccessfullOrder() {
		return orderConfirmMessage.getText();
	}
	
}