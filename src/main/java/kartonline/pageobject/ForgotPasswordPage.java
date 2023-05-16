package kartonline.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kartonline.abstractComponents.AbstractComponent;

public class ForgotPasswordPage extends AbstractComponent {

	private WebDriver driver;

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[type='submit']")
	WebElement Submit;
	
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement EmailInput;
	
	public LoginPage inputForgotPassDetails(String email) {
		EmailInput.sendKeys(email);
		waitForElementToClickable(Submit);
		Submit.click();
		waitForElementToDisplay(EmailInput);
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}
}
