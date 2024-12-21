
package javaSeleniumAcademy.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import javaSeleniumAcademy.TestComponents.BasePage;
import javaSeleniumAcademy.pageObjects.CartPage;
import javaSeleniumAcademy.pageObjects.CheckoutPage;
import javaSeleniumAcademy.pageObjects.ConfirmationPage;
import javaSeleniumAcademy.pageObjects.ProductCatalogPage;

import javaSeleniumAcademy.TestComponents.Retry;

public class ErrorValidationsTests extends BasePage {
	

	@Test(groups = {"ErrorHandling"}) 
	public void loginErrrorValidation() throws IOException {
		String userName = "jimmydoe@gmail.com";
		String password = "incorrect@123";
		String incorrectMessage="Incorrect email or password.";

		landingPage.loginToSite(userName, password);
		Assert.assertEquals(incorrectMessage, landingPage.getErrorMessage());

	}
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class) 
	public void loginErrrorValidationFail() throws IOException {
		String userName = "jimmydoe@gmail.com";
		String password = "incorrect@123";
		String incorrectMessage="Incorrect email password.";

		landingPage.loginToSite(userName, password);
		Assert.assertEquals(incorrectMessage, landingPage.getErrorMessage());

	}

	@Test(groups = {"ErrorHandling"})
	public void productValidation() {
		String userName = "jimmydoe@gmail.com";
		String password = "Santorini@123";
		String productName = "ZARA COAT 3";
		String inCorrectproductName = "ZARA COAT 33";
		
		ProductCatalogPage productCatalogPage = landingPage.loginToSite(userName, password);
		productCatalogPage.selectProduct(productName);
		productCatalogPage.gotoCartPage();
		CartPage cartPage = productCatalogPage.gotoCartPage();
		Assert.assertFalse(cartPage.checkProductInCart(inCorrectproductName));
	}
	
	
	@Test(dependsOnGroups = "loginErrrorValidationFail")
	public void loginErrrorValidation2() throws IOException {
		String userName = "jimmydoe@gmail.com";
		String password = "incorrect@123";
		String incorrectMessage="Incorrect email or password.";

		landingPage.loginToSite(userName, password);
		Assert.assertEquals(incorrectMessage, landingPage.getErrorMessage());

	}

	@Test
	public void productValidation2() {
		
		String userName = "jimmydoe@gmail.com";
		String password = "Santorini@123";
		String productName = "ZARA COAT 3";
		String inCorrectproductName = "ZARA COAT 33";
		
		ProductCatalogPage productCatalogPage = landingPage.loginToSite(userName, password);
		productCatalogPage.selectProduct(productName);
		productCatalogPage.gotoCartPage();
		CartPage cartPage = productCatalogPage.gotoCartPage();
		Assert.assertFalse(cartPage.checkProductInCart(inCorrectproductName));
		
	}
}
