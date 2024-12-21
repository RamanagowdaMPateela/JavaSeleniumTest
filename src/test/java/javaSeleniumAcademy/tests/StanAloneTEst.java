package javaSeleniumAcademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StanAloneTEst {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";
		// launch Url
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		System.out.println(driver.getCurrentUrl());

		// login
		WebElement userName = driver.findElement(By.cssSelector("#userEmail"));
		userName.click();
		userName.sendKeys("jimmydoe@gmail.com");
		WebElement password = driver.findElement(By.cssSelector("#userPassword"));
		password.click();
		password.sendKeys("Santorini@123");
		driver.findElement(By.cssSelector("#login")).click();

		// find a product and add to cart
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		
		// add to cart
		
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//navigate to cart
		WebElement cartButton= driver.findElement(By.cssSelector("[routerlink*='cart']"));
		wait.until(ExpectedConditions.visibilityOf(cartButton));
		cartButton.click();

		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match = cartProducts.stream().anyMatch(cartProduct->
		cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		WebElement checkoutButton= driver.findElement(By.cssSelector(".totalRow button"));
		
		wait.until(ExpectedConditions.visibilityOf(checkoutButton));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutButton);
		//checkoutButton.click();
		
		WebElement selectCountry = driver.findElement(By.cssSelector("input[placeholder=\"Select Country\"]"));;
		
		 
		selectCountry.click();
		
		selectCountry.sendKeys("India");
		
	   
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
	    
	    driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
	    
	    
	    WebElement placeOrderButton= driver.findElement(By.cssSelector(".action__submit"));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderButton);
	    
	    placeOrderButton.click();
	    
	    String orderConfirmed= driver.findElement(By.cssSelector(".hero-primary")).getText();
	    
	    Assert.assertTrue(orderConfirmed.equalsIgnoreCase("Thankyou for the order."));
	    
	    System.out.println();
	    
		
				
	}

}
