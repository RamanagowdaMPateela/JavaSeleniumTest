
package stepDefs;
import io.cucumber.java.en.*;
import javaSeleniumAcademy.TestComponents.BasePage;
import javaSeleniumAcademy.pageObjects.CartPage;
import javaSeleniumAcademy.pageObjects.CheckoutPage;
import javaSeleniumAcademy.pageObjects.ConfirmationPage;
import javaSeleniumAcademy.pageObjects.LandingPage;
import javaSeleniumAcademy.pageObjects.ProductCatalogPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;


public class submitOrderStepDef extends BasePage {
	    LandingPage landingPage;
	    ProductCatalogPage productCatalogPage;
	    CartPage cartPage;
	    CheckoutPage checkoutPage;
	    ConfirmationPage confirmationPage;
	    
	    @Given("I am on the landing page")
	    public void i_am_on_the_landing_page() throws IOException {
	        // Initialize the driver and navigate to the landing page
	    	landingPage = launchApp();
	    }

	    @When("^I login with username (.+) and password (.+)$")
	    public void i_login_with_username_and_password(String username, String password) throws IOException {
	    	productCatalogPage= landingPage.loginToSite(username, password);
	    }

	    @Then("I should see an error message {string}")
	    public void i_should_see_an_error_message(String expectedMessage) {
	        String actualMessage = landingPage.getErrorMessage();
	        assertEquals(expectedMessage, actualMessage);
	        driver.quit();
	    }
	    @When("^I add product (.+) to cart$")
	    public void i_add_product_to_cart(String productName) {
	        productCatalogPage.selectProduct(productName);
	        cartPage = productCatalogPage.gotoCartPage();
	    }

	    @When("^I checkout (.+) and Submit the order$")
	    public void i_checkout_and_submit_the_order(String productName) {
	        assertTrue(cartPage.checkProductInCart(productName));
	        checkoutPage = cartPage.checkoutCart();
	        checkoutPage.selectCountry();
	        confirmationPage = checkoutPage.placeOrder();
	    }

	    @Then("I should see a confirmation message {string}")
	    public void i_should_see_a_confirmation_message(String expectedMessage) {
	        String confirmationMessage = confirmationPage.getSuccessfullOrder();
	        assertTrue(confirmationMessage.equalsIgnoreCase(expectedMessage));
	        driver.quit();
	    }
	

}
