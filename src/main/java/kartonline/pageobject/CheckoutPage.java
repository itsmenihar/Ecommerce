package kartonline.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import kartonline.abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "firstname")
	WebElement FirstNameInput;

	@FindBy(name = "lastname")
	WebElement LastNameInput;

	@FindBy(name = "company")
	WebElement CompanyInput;

	@FindBy(name = "address_1")
	WebElement Add_1_Input;

	@FindBy(name = "city")
	WebElement CityInput;

	@FindBy(name = "postcode")
	WebElement PostCodeInput;

	@FindBy(name = "country_id")
	WebElement CountryDropdown;

	@FindBy(name = "zone_id")
	WebElement stateDropdown;

	@FindBy(css = "#button-shipping-address")
	WebElement submitElement;

	public WebElement selectCountry(String name) {
		Select select = new Select(CountryDropdown);
		List<WebElement> countryList = select.getOptions();
		for (WebElement country : countryList) {
			if (country.getText().equalsIgnoreCase(name)) {
				country.click();
				return country;
			}

		}
		return null;
	}

	public WebElement selectState(String stateName) {
		Select select = new Select(stateDropdown);
		List<WebElement> stateList = select.getOptions();
		waitForElementsToVisible(stateList);
		for (WebElement state : stateList) {
			if (state.getText().equalsIgnoreCase(stateName)) {
				state.click();
				return state;
			}

		}
		return null;
	}

	@FindBy(id = "button-shipping-methods")
	WebElement chooseShippingMethod;

	@FindBy(xpath = "//input[@id='input-shipping-method-flat-flat']")
	WebElement ShippingMethodOption;

	@FindBy(xpath = "//button[@id='button-shipping-method']")
	WebElement ShippingMethodSubmit;

	@FindBy(id = "button-payment-methods")
	WebElement choosePaymentMethod;

	@FindBy(xpath = "//input[@id='input-payment-method-cod-cod']")
	WebElement PaymentMethodOption;

	@FindBy(xpath = "//button[@id='button-payment-method']")
	WebElement PaymentMethodSubmit;

	@FindBy(xpath = "//div[@id='checkout-confirm']//div//button[@id='button-confirm']")
	WebElement ConformOrder;

	public ConformationPage submitCheckOutDetails(String firstName, String lastName, String companyName, String address,
			String city, String postCode) throws InterruptedException {
		FirstNameInput.sendKeys(firstName);
		LastNameInput.sendKeys(lastName);
		CompanyInput.sendKeys(companyName);
		Add_1_Input.sendKeys(address);
		CityInput.sendKeys(city);
		PostCodeInput.sendKeys(postCode);
		selectCountry("India");
		Thread.sleep(2000);
		selectState("Odisha");
		scrollDown();
		submitElement.click();
		scrollUp();
		waitForElementToDisplay(chooseShippingMethod);
		chooseShippingMethod.click();
		ShippingMethodOption.click();
		ShippingMethodSubmit.click();
		waitForElementToDisplay(choosePaymentMethod);
		choosePaymentMethod.click();
		PaymentMethodOption.click();
		PaymentMethodSubmit.click();
		waitForElementToClickable(ConformOrder);
		scrollDown();
		ConformOrder.click();
		ConformationPage conformPage = new ConformationPage(driver);
		return conformPage;

	}

}
