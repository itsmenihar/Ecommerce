package kartonline.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kartonline.abstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Registration through login page(if you are a new customer)
	@FindBy(xpath = "//a[text()='Continue']")
	WebElement RegdContinueOption;

	public RegistrationPage continueOption() {
		RegdContinueOption.click();
		RegistrationPage rp = new RegistrationPage(driver);
		return rp;
	}

	// Registration through right column option in login page(if you are a new
	// customer)
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Register']")
	WebElement RightColumnRegdOption;

	public RegistrationPage rightColumnRegdOption() {
		RightColumnRegdOption.click();
		RegistrationPage rp = new RegistrationPage(driver);
		return rp;
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement inputLoginEmail;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement inputLoginPassword;

	@FindBy(xpath = "//button[text()='Login']")
	WebElement Submit;

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement LoginError;

	public WebElement getLoginError() {
		return LoginError;
	}

	@FindBy(xpath = "//div[@class='mb-3']//a[text()='Forgotten Password']")
	WebElement ForgotPassword;

	public ForgotPasswordPage forgotPassword() {
		ForgotPassword.click();
		ForgotPasswordPage fp = new ForgotPasswordPage(driver);
		return fp;
	}

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement PasswordResetMsg;

	public WebElement getPassResetMsg() {
		return PasswordResetMsg;
	}

	public HomePage loginToApplicationWithValidData(String email, String Password) {
		inputLoginEmail.sendKeys(email);
		inputLoginPassword.sendKeys(Password);
		Submit.click();
		HomePage hp = new HomePage(driver);
		return hp;
	}

	public void loginToApplicationWithInvalidData(String email, String Password) {
		inputLoginEmail.sendKeys(email);
		inputLoginPassword.sendKeys(Password);
		Submit.click();
		waitForElementToDisplay(getLoginError());
	}

}
