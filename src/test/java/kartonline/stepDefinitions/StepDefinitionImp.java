package kartonline.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kartonline.TestComponents.BaseTest;
import kartonline.pageobject.CartPage;
import kartonline.pageobject.CheckoutPage;
import kartonline.pageobject.ConformationPage;
import kartonline.pageobject.HomePage;
import kartonline.pageobject.LandingPage;
import kartonline.pageobject.LoginPage;
import kartonline.pageobject.ProductCataloguePage;

public class StepDefinitionImp extends BaseTest {
	public LandingPage lp;
	public LoginPage loginPage;
	public HomePage hp;
	public ProductCataloguePage pc;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConformationPage conformaPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		lp = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		loginPage = lp.getLogin();
		hp = loginPage.loginToApplicationWithValidData(username, password);
	}

	@When("^I clicked on product cetagory options (.+) and add the product (.+) to cart$")
	public void When_I_click_on_product_cetagory_options_and_add_product_to_cart(String cetagory, String prodName)
			throws InterruptedException {
		Thread.sleep(2000);
		pc = hp.prodCetagories(cetagory);
		pc.addProductToCart(prodName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String prodName) throws InterruptedException {
		cartPage = pc.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(prodName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckout();
		conformaPage = checkoutPage.submitCheckOutDetails("Nihar", "Sahu", "TCS", "Trident Galexy", "BBSR", "751005");
	}

	@Then("^(.+) message is displayed on conformation page$")
	public void message_is_displayed_on_conformation_page(String msg) {
		Assert.assertTrue(conformaPage.conformMessage().equalsIgnoreCase(msg));
		driver.quit();
	}
}
