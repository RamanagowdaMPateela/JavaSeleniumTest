package javaSeleniumAcademy.Utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javaSeleniumAcademy.pageObjects.CartPage;

public class Utility {

  WebDriver driver;
  public Utility(WebDriver driver) {
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
  }
  
  @FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
  
  public void waitForElementVisible(By element) {
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(element));	 	
  }
  
  public void waitForElementInVisible(By element) {
	  try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//	 	wait.until(ExpectedConditions.invisibilityOfElementLocated(element));	 	

	}
  
  
  public void waitForWebElementVisible(WebElement element) {
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	 	wait.until(ExpectedConditions.visibilityOf(element));	 	
  }
  
  
  //countryList
  
  public CartPage gotoCartPage() {
	  cartButton.click();
	return new CartPage(driver);
  }
  
  
}
