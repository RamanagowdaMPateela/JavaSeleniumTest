package javaSeleniumAcademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javaSeleniumAcademy.TestComponents.BasePage;
import javaSeleniumAcademy.pageObjects.CartPage;
import javaSeleniumAcademy.pageObjects.CheckoutPage;
import javaSeleniumAcademy.pageObjects.ConfirmationPage;
import javaSeleniumAcademy.pageObjects.ProductCatalogPage;

public class SubmitOrderTests extends BasePage {

	@Test(dataProvider = "getData", groups="Purchase")
	public void SumbitOrder(HashMap<String, String> input) throws IOException {

		 System.out.printf("userName", input.get("email")); 
		 
		ProductCatalogPage productCatalogPage = landingPage.loginToSite(input.get("email"), input.get("password"));
		productCatalogPage.selectProduct(input.get("Product"));
		productCatalogPage.gotoCartPage();
		CartPage cartPage = productCatalogPage.gotoCartPage();
		Assert.assertTrue(cartPage.checkProductInCart(input.get("Product")));
		CheckoutPage checkoutPage = cartPage.checkoutCart();
		checkoutPage.selectCountry();
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		String confirmationMessage = confirmationPage.getSuccessfullOrder();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\javaSeleniumAcademy\\data\\PurchaseOrder.json", "UTF-8" );
		
		 System.out.println(data); 
		 
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
}
