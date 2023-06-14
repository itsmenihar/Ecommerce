package kartonline.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import kartonline.TestComponents.BaseTest;
import kartonline.pageobject.CartPage;
import kartonline.pageobject.CheckoutPage;
import kartonline.pageobject.ConformationPage;
import kartonline.pageobject.HomePage;
import kartonline.pageobject.LoginPage;
import kartonline.pageobject.ProductCataloguePage;

public class OrderProduct extends BaseTest {
	WebDriver driver;
//	String headerName = "Phones & PDAs";
//	String productName = "iPhone";
//	String message = "Your order has been placed!";

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws InterruptedException {
		LoginPage loginPage = lp.getLogin();
		HomePage hp = loginPage.loginToApplicationWithValidData(input.get("email"), input.get("password"));
		Thread.sleep(3000);
		ProductCataloguePage pc = hp.prodCetagories(input.get("headerName"));
		pc.addProductToCart(input.get("productName"));
		CartPage cartPage = pc.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConformationPage conformaPage = checkoutPage.submitCheckOutDetails(input.get("firstname"),
				input.get("lastname"), input.get("company"), input.get("address_1"), input.get("city"),
				input.get("pincode"));
		Assert.assertTrue(conformaPage.conformMessage().equalsIgnoreCase(input.get("message")));

	}

	@DataProvider
	public Object[] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//kartonline//data//PurchaseOrder.json");
		return new Object[] { data.get(0) };

	}

}
