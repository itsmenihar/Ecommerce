package kartonline.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import kartonline.TestComponents.BaseTest;
import kartonline.pageobject.HomePage;
import kartonline.pageobject.LandingPage;
import kartonline.pageobject.LoginPage;

public class TS_003LogoutFunctionalityTest extends BaseTest {
	WebDriver driver;

	// Validate Logging out by selecting Logout option from 'My Account' dropmenu
	@Test
	public void TC_LG_001() throws InterruptedException {
		LoginPage loginPage = lp.getLogin();
		HomePage hp = loginPage.loginToApplicationWithValidData("admin@gmail.com", "12345");
		Thread.sleep(2000);
		LandingPage lp = hp.logOut();
		String title = lp.getTitle();
		Assert.assertEquals(title, "Your Store");
	}

	// Validate Logging out by selecting Logout option from 'Right Column'
	@Test
	public void TC_LG_002() throws InterruptedException {
		LoginPage loginPage = lp.getLogin();
		HomePage hp = loginPage.loginToApplicationWithValidData("admin@gmail.com", "12345");
		Thread.sleep(3000);
		hp.logOutfromSideBar();
		String title = lp.getTitle();
		Assert.assertEquals(title, "Your Store");
	}

}
