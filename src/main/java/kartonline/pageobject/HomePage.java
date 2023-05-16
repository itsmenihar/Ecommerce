package kartonline.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kartonline.abstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent {
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Registration successful msg element
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement RegdSuccessfulMsg;

	public String getMsgText() {
		return RegdSuccessfulMsg.getText();

	}

	// newsletter navigation bar
	@FindBy(xpath = "//aside//div//a[12]")
	WebElement NewsletterBar;

	// yes radio button of newletter subscription page
	@FindBy(xpath = "//input[@id='input-newsletter']")
	WebElement Newsletter;

	public WebElement subscribeBtn() {
		return Newsletter;
	}

	// submit button of newsletter subscription page
	@FindBy(css = "button[type='submit']")
	WebElement submitBtn;

	// right side bar logout
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
	WebElement sideBarLogout;

	public void validateSubscription() {
		scrollDown();
		waitForElementToClickable(NewsletterBar);
		NewsletterBar.click();
	}

	// logout option from myaccount drop down
	public LandingPage logOut() {
		MyAccountHeader.click();
		Logoutoption.click();
		LogoutContinue.click();
		LandingPage lp = new LandingPage(driver);
		return lp;
	}

	// logout option from myaccount drop down
	public LandingPage logOutfromSideBar() throws InterruptedException {
		scrollDown();
		sideBarLogout.click();
		LogoutContinue.click();
		LandingPage lp = new LandingPage(driver);
		return lp;
	}

}
