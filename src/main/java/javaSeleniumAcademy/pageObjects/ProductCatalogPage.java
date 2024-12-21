package javaSeleniumAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javaSeleniumAcademy.Utility.Utility;

public class ProductCatalogPage extends Utility {

	WebDriver driver;

	public ProductCatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productList;

	By product = By.cssSelector(".mb-3");
	By addToCartButton = By.cssSelector(".card-body button:last-of-type");
	By toastMesg = By.cssSelector("#toast-container");
	By ngAnimation = By.cssSelector(".ng-animating");
	
	

	public void selectProduct(String productName) {

		waitForElementVisible(product);
		List<WebElement> products = productList;
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(addToCartButton).click();
		waitForElementVisible(toastMesg);
		waitForElementInVisible(ngAnimation);
	}
	

}
