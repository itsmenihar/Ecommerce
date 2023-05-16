package kartonline.pageobject;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

import kartonline.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	private WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LoginPage getLogin() {
		MyAccountHeader.click();
		Loginoption.click();
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}

	public RegistrationPage getRegdOption() {
		MyAccountHeader.click();
		RegisterOption.click();
		RegistrationPage rp = new RegistrationPage(driver);
		return rp;
	}

	public LoginPage getRegdThroughLoginOption() {
		MyAccountHeader.click();
		Loginoption.click();
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}

	public void goTo() {
		driver.get("http://localhost:8085/apps/kartonline");
	}

}
