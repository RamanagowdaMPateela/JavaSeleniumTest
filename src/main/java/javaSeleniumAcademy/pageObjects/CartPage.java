package javaSeleniumAcademy.pageObjects;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement>  cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public boolean checkProductInCart(String productName) {
		
		return  cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	  
	}


	public CheckoutPage checkoutCart() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutButton);
		return new CheckoutPage(driver);
		
	}
	
}
