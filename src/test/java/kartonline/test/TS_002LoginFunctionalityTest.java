package kartonline.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import kartonline.TestComponents.BaseTest;
import kartonline.pageobject.ForgotPasswordPage;
import kartonline.pageobject.HomePage;
import kartonline.pageobject.LoginPage;

public class TS_002LoginFunctionalityTest extends BaseTest {
	WebDriver driver;

	// Validate logging into the Application using valid credentials
	@Test
	public void TC_LF_001() throws InterruptedException {
		LoginPage loginPage = lp.getLogin();
		HomePage hp = loginPage.loginToApplicationWithValidData("admin@gmail.com", "12345");
		Thread.sleep(5000);
		String title = hp.getTitle();
		Assert.assertEquals(title, "My Account");

	}

	// Validate logging into the Application using invalid credentials (i.e. Invalid
	// email address and Invalid Password)
	@Test
	public void TC_LF_002() throws InterruptedException {
		String errorMessage = "Warning: No match for E-Mail Address and/or Password.";
		LoginPage loginPage = lp.getLogin();
		loginPage.loginToApplicationWithInvalidData("xyzabc123@gmail.com", "xyzabc123");
		String title = loginPage.getLoginError().getText();
		Assert.assertEquals(title, errorMessage);
	}

	// Validate logging into the Application using invalid email address and valid
	// Password
	@Test
	public void TC_LF_003() {
		String errorMessage = "Warning: No match for E-Mail Address and/or Password.";
		LoginPage loginPage = lp.getLogin();
		loginPage.loginToApplicationWithInvalidData("xyzabc123@gmail.com", "12345");
		String title = loginPage.getLoginError().getText();
		Assert.assertEquals(title, errorMessage);
	}

	// Validate logging into the Application using valid email address and invalid
	// Password
	@Test
	public void TC_LF_004() {
		String errorMessage = "Warning: No match for E-Mail Address and/or Password.";
		LoginPage loginPage = lp.getLogin();
		loginPage.loginToApplicationWithInvalidData("niharranjansahu2@gmail.com", "xyzabc123");
		String title = loginPage.getLoginError().getText();
		Assert.assertEquals(title, errorMessage);
	}

	// Validate logging into the Application without providing any credentials
	@Test
	public void TC_LF_005() {
		String errorMessage = "Warning: No match for E-Mail Address and/or Password.";
		LoginPage loginPage = lp.getLogin();
		loginPage.loginToApplicationWithInvalidData("", "");
		String title = loginPage.getLoginError().getText();
		Assert.assertEquals(title, errorMessage);
	}

	// Validate 'Forgotten Password' link is available in the Login page and is
	// working
	@Test
	public void TC_LF_006() throws InterruptedException {
		LoginPage loginPage = lp.getLogin();
		ForgotPasswordPage fp = loginPage.forgotPassword();
		fp.inputForgotPassDetails("admin@gmail.com");
		Thread.sleep(3000);
		String resetMsg = loginPage.getPassResetMsg().getText();
		Assert.assertEquals(resetMsg, "Success: Your password has been successfully updated.");

	}

}
