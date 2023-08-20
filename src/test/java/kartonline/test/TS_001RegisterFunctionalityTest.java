package kartonline.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import kartonline.TestComponents.BaseTest;
import kartonline.pageobject.HomePage;
import kartonline.pageobject.LoginPage;
import kartonline.pageobject.RegistrationPage;

public class TS_001RegisterFunctionalityTest extends BaseTest {
	WebDriver driver;

	// Validate Registering an Account by providing only the Mandatory fields
	@Test
	public void TC_RF_001() {
		RegistrationPage rp = lp.getRegdOption();
		HomePage hp = rp.registrationToApplication("Nihar", "Sahu", "niharsahu22@gmail.com", "Niharsahu123@");
		String title = hp.getTitle();
		Assert.assertEquals(title, "My Account");
	}

	// Validate 'Thank you for registering' email is sent to the registered email
	// address as a confirmation for registering the account
	@Test
	public void TC_RF_002() {
		RegistrationPage rp = lp.getRegdOption();
		rp.registrationToApplication("Milan", "Sahu", "milansahu@gmail.com", "Milansahu123@");
		Assert.assertFalse(true);
	}

	// Validate Registering an Account by providing all the fields
	@Test
	public void TC_RF_003() {
		RegistrationPage rp = lp.getRegdOption();
		HomePage hp = rp.registrationToApplication("Anand", "Sahu", "anandsahu123@gmail.com.com", "Anandsahu123@");
		String title = hp.getTitle();
		Assert.assertEquals(title, "My Account");
	}

	// Validate proper notification messages are displayed for the mandatory fields,
	// when you don't provide any fields in the 'Register Account' page and submit
	@Test
	public void TC_RF_004() throws InterruptedException {
		RegistrationPage rp = lp.getRegdOption();
		rp.notificationMsgForMendatoryField();
		Assert.assertTrue(rp.getErrorMsgForFirstname().isDisplayed());
		Assert.assertTrue(rp.getErrorMsgForLastname().isDisplayed());
		Assert.assertTrue(rp.getErrorMsgForEmail().isDisplayed());
		Assert.assertTrue(rp.getErrorMsgForPasssword().isDisplayed());
		Assert.assertTrue(rp.getPrivacyWarning().isDisplayed());
	}

	// Validate Registering an Account when Newsletter
	// field is enabled
	@Test
	public void TC_RF_005() {
		RegistrationPage rp = lp.getRegdOption();
		HomePage hp = rp.registrationToApplicationwithSubscription("pratik", "naik", "pratiknaik@gmail.com",
				"pratiknaik@123");
		hp.validateSubscription();
		Assert.assertTrue(hp.subscribeBtn().isEnabled());
	}

	// Validate Registering an Account when Newsletter
	// field is disabled
	@Test
	public void TC_RF_006() throws InterruptedException {
		RegistrationPage rp = lp.getRegdOption();
		HomePage hp = rp.registrationToApplicationwithoutSubscription("tapan", "gada", "tapangada@gmail.com",
				"tapangada@123");
		Thread.sleep(5000);
		hp.validateSubscription();
		Assert.assertFalse(!hp.subscribeBtn().isEnabled());
	}

	// Validate different ways of navigating to 'Register Account' page
	// My Account Dropdown->Register
	@Test
	public void TC_RF_007() {
		RegistrationPage rp = lp.getRegdOption();
		String title = rp.getTitle();
		Assert.assertEquals(title, "Register Account");

	}

	// Validate different ways of navigating to 'Register Account' page
	// My Account Dropdown->Login->right column register option
	@Test
	public void TC_RF_008() {
		LoginPage loginPage = lp.getRegdThroughLoginOption();
		RegistrationPage rp = loginPage.rightColumnRegdOption();
		String title = rp.getTitle();
		Assert.assertEquals(title, "Register Account");

	}

	// Validate different ways of navigating to 'Register Account' page
	// My Account Dropdown->Login->continue option(New Customer)
	@Test
	public void TC_RF_009() {
		LoginPage loginPage = lp.getRegdThroughLoginOption();
		RegistrationPage rp = loginPage.continueOption();
		String title = rp.getTitle();
		Assert.assertEquals(title, "Register Account");

	}

	// Validate Registering an Account by providing the existing account details
	// (i.e. existing email address)
	@Test
	public void TC_RF_011() {
		String warningMsg = "Warning: E-Mail Address is already registered!";
		RegistrationPage rp = lp.getRegdOption();
		rp.registrationToApplicationWithExistingData("Nihar", "Sahu", "admin@gmail.com", "12345");
		Assert.assertEquals(rp.getEmailWarning().getText(), warningMsg);
	}

	// Validate Registering an Account by providing an invalid email address into
	// the E-Mail field
	@Test
	public void TC_RF_012() throws InterruptedException {
		String firstName = "Bikram";
		String lastName = "Dash";
		String password = "Bikramdash@123";
		List<String> invalidEmails = Arrays.asList("tapan", "tapan@", "tapan@gmail", "tapan@.com", "tapan.gmail.com");
		RegistrationPage rp = lp.getRegdOption();
		for (String email : invalidEmails) {
			rp.invalidInput(firstName, lastName, email, password);
			Thread.sleep(5000);
			String email_Tooltip = rp.emailInput().getAttribute("validationMessage");
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(email_Tooltip.contains("Please"));
			softAssert.assertEquals(rp.getErrorMsgForEmail().getText(), "E-Mail Address does not appear to be valid!");
			softAssert.assertTrue(email_Tooltip.contains("position"));
			rp.refresh();
		}
	}

	// Validate Registering an Account by using the Keyboard keys
	@Test
	public void TC_RF_014() {
		RegistrationPage rp = lp.getRegdOption();
		HomePage hp = rp.regdUsingKeyboardKey("Dibya", "Shetty", "dibyashetty@gmail.com", "Dibyashetty@123");
		Assert.assertEquals(hp.getMsgText(), "Your Account Has Been Created!");
	}

	// Validate all the fields in the Register Account page have the proper
	// placeholders
	@Test
	public void TC_RF_015() {
		RegistrationPage rp = lp.getRegdOption();
		String firstName = rp.firstNameInput().getAttribute("placeholder");
		Assert.assertEquals("First Name", firstName);
		String lastName = rp.LastNameInput().getAttribute("placeholder");
		Assert.assertEquals("Last Name", lastName);
		String email = rp.emailInput().getAttribute("placeholder");
		Assert.assertEquals("E-Mail", email);
		String password = rp.passwordInput().getAttribute("placeholder");
		Assert.assertEquals("Password", password);
	}

	// Validate all the mandatory fields in the Register Account page are marked
	// with red color * symbol
	@Test
	public void TC_RF_016() {
		RegistrationPage rp = lp.getRegdOption();
		List<WebElement> mendatoryfields = rp.mendatoryFields();
		for (WebElement field : mendatoryfields) {
			String classValue = field.getAttribute("class");
			if (classValue.contains("required")) {
				String innerHTML = field.getAttribute("innerHTML");
				if (innerHTML.contains("*") && field.getCssValue("color").equals("rgb(255, 0, 0)")) {
					// Field is marked as mandatory with a red color * symbol
				} else {
					// Field is not marked as mandatory with a red color * symbol
					Assert.fail("Mandatory field is not marked with red color * symbol: " + field.getAttribute("name"));
				}
			}
		}
	}

	// Validate the details that are provided while Registering an Account are
	// stored in the Database
	@Test
	public void TC_RF_017() throws SQLException {
		// Data for registration
		String cust_firstName = "Rahul";
		String cust_lastName = "Tiwetia";
		String cust_email = "rahul123@gmail.com";
		String cust_password = "Rahul987";
		String url = "jdbc:mysql://localhost:3306/kartonline";
		String username = "root";
		String password = "";
		RegistrationPage rp = lp.getRegdOption();
		rp.registrationToApplication(cust_firstName, cust_lastName, cust_email, cust_password);
		// DataBase Validation
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement();
		String query = "select firstname,lastname,email from oc_customer";
		ResultSet rs = stmt.executeQuery(query);
		boolean status = false;
		while (rs.next()) {
			String firstname = rs.getString("firstname");
			String lastname = rs.getString("lastname");
			String email = rs.getString("email");
			if (cust_firstName.equals(firstname) && cust_lastName.equals(lastname) && cust_email.equals(email)) {
				status = true;
				break;
			}
		}
		if (status == false) {
			Assert.fail("Record not found || Test failed");
		}
	}

	// Validate whether the Mandatory fields in the Register Account page are
	// accepting only spaces
	@Test
	public void TC_RF_018() {
		String cust_firstName = " ";
		String cust_lastName = " ";
		String cust_email = " ";
		String cust_password = " ";
		RegistrationPage rp = lp.getRegdOption();
		rp.registrationToApplicationwithWhiteSpaces(cust_firstName, cust_lastName, cust_email, cust_password);
		Assert.assertTrue(rp.getErrorMsgForFirstname().isDisplayed());
		Assert.assertTrue(rp.getErrorMsgForLastname().isDisplayed());
		Assert.assertTrue(rp.getErrorMsgForEmail().isDisplayed());
		Assert.assertTrue(rp.getErrorMsgForPasssword().isDisplayed());
	}

	// Validate whether the Password fields in the Register Account page are
	// following Password Complexity Standards
	@Test
	public void TC_RF_019() throws InterruptedException {
		String errorPasswordNotification = "Password must be between 4 and 20 characters!";
		String firstName = "Bikram";
		String lastName = "Dash";
		String Email = "bikramdash@gmail.com";
		SoftAssert softAssert = new SoftAssert();
		List<String> password = Arrays.asList("bik", "bikram@gmail123", "bikram123", "Bikram@",
				"Bikramdash@98746531282");
		RegistrationPage rp = lp.getRegdOption();
		for (String pass : password) {
			rp.invalidInput(firstName, lastName, Email, pass);
			Thread.sleep(3000);
			String passError = rp.getErrorMsgForPasssword().getText();
			if (!passError.equalsIgnoreCase(errorPasswordNotification)) {
				softAssert.fail();
				driver.navigate().back();
			} else {
				rp.refresh();
			}

		}

	}

	// Validate whether the fields in the Register Account page are according the
	// Client requirements (Examples- Height, Width, Number of characters etc.)
	@Test
	public void TC_RF_020() {
		RegistrationPage rp = lp.getRegdOption();
		List<WebElement> textFields = rp.getAllTextField();
		for (WebElement textField : textFields) {
			int height = textField.getSize().getHeight();
			int width = textField.getSize().getWidth();
			Assert.assertTrue(height == 41.98, "Error: Input box height is not acceptable");
			Assert.assertTrue(width == 426, "Error: Input box width is not within acceptable range");

		}
	}

}
