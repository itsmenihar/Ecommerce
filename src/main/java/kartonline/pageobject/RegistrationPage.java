package kartonline.pageobject;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kartonline.abstractComponents.AbstractComponent;

public class RegistrationPage extends AbstractComponent {

	private WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Registration form
	// First Name field
	@FindBy(css = "#input-firstname")
	WebElement FirstName;

	public WebElement firstNameInput() {
		return FirstName;
	}

	// Last Name field
	@FindBy(css = "#input-lastname")
	WebElement LastName;

	public WebElement LastNameInput() {
		return LastName;
	}

	// E-Mail field
	@FindBy(css = "#input-email")
	WebElement Email;

	public WebElement emailInput() {
		return Email;
	}

	// Password field
	@FindBy(css = "#input-password")
	WebElement Password;

	public WebElement passwordInput() {
		return Password;
	}

	// Subscription radio button
	@FindBy(css = "#input-newsletter")
	WebElement SubscribeRadioBtn;

	// Privacy Policy checkbox
	@FindBy(xpath = "//input[@name='agree']")
	WebElement PrivacyPolicyCheckBox;

	public WebElement checkBox() {
		return PrivacyPolicyCheckBox;
	}

	@FindBy(xpath = "//div[@id='alert']")
	WebElement PrivacyWarning;

	public WebElement getPrivacyWarning() {
		return PrivacyWarning;
	}

	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement EmailWarning;

	public WebElement getEmailWarning() {
		return EmailWarning;
	}

	// Notification for firstname
	@FindBy(xpath = "//div[@id='error-firstname']")
	WebElement errorFirstname;

	public WebElement getErrorMsgForFirstname() {
		return errorFirstname;
	}

	// Notification for lastname
	@FindBy(xpath = "//div[@id='error-lastname']")
	WebElement errorLastname;

	public WebElement getErrorMsgForLastname() {
		return errorLastname;
	}

	// Notification for email
	@FindBy(xpath = "//div[@id='error-email']")
	WebElement errorEmail;

	public WebElement getErrorMsgForEmail() {
		return errorEmail;
	}

	// Notification for password
	@FindBy(xpath = "//div[@id='error-password']")
	WebElement errorPassword;

	public WebElement getErrorMsgForPasssword() {
		return errorPassword;
	}

	// Submit button
	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement SubmitButton;

	public WebElement submitBtn() {
		return SubmitButton;
	}

	// Registration successful message
	@FindBy(css = "div[id='content'] h1")
	WebElement AccountSuccessMsg;

	public String getMsg() {
		return AccountSuccessMsg.getText();
	}

	public String getTitle() {
		return driver.getTitle();

	}

	// Locate all the mendatory fields
	@FindBy(xpath = "//input[@class='form-control']")
	List<WebElement> MendatoryFields;

	public List<WebElement> mendatoryFields() {
		return MendatoryFields;
	}

	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement ContinueBtn;

	@FindBy(xpath = "//div[@class='invalid-feedback d-block']")
	List<WebElement> NotificationMsgMendatoryFields;

	@FindBy(xpath = "//input[@class='form-control is-invalid']")
	List<WebElement> AllTestFields;

	public List<WebElement> getAllTextField() {
		return AllTestFields;
	}

	public HomePage registrationToApplication(String userName, String lastName, String email, String password) {
		scrollDown();
		FirstName.sendKeys(userName);
		LastName.sendKeys(lastName);
		Email.sendKeys(email);
		Password.sendKeys(password);
		PrivacyPolicyCheckBox.click();
		SubmitButton.click();
		waitForElementToDisplay(ContinueBtn);
		ContinueBtn.click();
		HomePage hp = new HomePage(driver);
		return hp;

	}

	public void notificationMsgForMendatoryField() {
		scrollDown();
		SubmitButton.sendKeys(Keys.ENTER);
		waitForElementsToVisible(NotificationMsgMendatoryFields);
	}

	public HomePage registrationToApplicationwithSubscription(String userName, String lastName, String email,
			String password) {
		scrollDown();
		FirstName.sendKeys(userName);
		LastName.sendKeys(lastName);
		Email.sendKeys(email);
		Password.sendKeys(password);
		SubscribeRadioBtn.click();
		PrivacyPolicyCheckBox.click();
		SubmitButton.click();
		waitForElementToDisplay(ContinueBtn);
		ContinueBtn.click();
		HomePage hp = new HomePage(driver);
		return hp;
	}

	public HomePage registrationToApplicationwithoutSubscription(String userName, String lastName, String email,
			String password) {
		scrollDown();
		FirstName.sendKeys(userName);
		LastName.sendKeys(lastName);
		Email.sendKeys(email);
		Password.sendKeys(password);
		PrivacyPolicyCheckBox.click();
		SubmitButton.click();
		waitForElementToDisplay(ContinueBtn);
		ContinueBtn.click();
		HomePage hp = new HomePage(driver);
		return hp;
	}

	public void registrationToApplicationWithExistingData(String userName, String lastName, String email,
			String password) {
		scrollDown();
		FirstName.sendKeys(userName);
		LastName.sendKeys(lastName);
		Email.sendKeys(email);
		Password.sendKeys(password);
		PrivacyPolicyCheckBox.click();
		SubmitButton.click();

	}

	public HomePage regdUsingKeyboardKey(String userName, String lastName, String email, String password) {
		firstNameInput().sendKeys(userName);
		firstNameInput().sendKeys(Keys.TAB);
		LastNameInput().sendKeys(lastName);
		LastNameInput().sendKeys(Keys.TAB);
		Email.sendKeys(email);
		Email.sendKeys(Keys.TAB);
		Password.sendKeys(password);
		Password.sendKeys(Keys.TAB);
		if (!SubscribeRadioBtn.isEnabled()) {
			SubscribeRadioBtn.sendKeys(Keys.SPACE);
		} else
			SubscribeRadioBtn.sendKeys(Keys.TAB);
		PrivacyPolicyCheckBox.sendKeys(Keys.SPACE);
		PrivacyPolicyCheckBox.sendKeys(Keys.TAB);
		SubmitButton.sendKeys(Keys.ENTER);
		HomePage hp = new HomePage(driver);
		return hp;
	}

	public void registrationToApplicationwithWhiteSpaces(String userName, String lastName, String email,
			String password) {
		scrollDown();
		FirstName.sendKeys(userName);
		LastName.sendKeys(lastName);
		Email.sendKeys(email);
		Password.sendKeys(password);
		SubscribeRadioBtn.click();
		PrivacyPolicyCheckBox.click();
		SubmitButton.click();
		waitForElementsToVisible(NotificationMsgMendatoryFields);
	}

	public void invalidInput(String userName, String lastName, String email, String password) {
		scrollDown();
		FirstName.sendKeys(userName);
		LastName.sendKeys(lastName);
		Email.sendKeys(email);
		Password.sendKeys(password);
		PrivacyPolicyCheckBox.click();
		SubmitButton.click();
	}

	public void refresh() {
		driver.navigate().refresh();
	}
}
