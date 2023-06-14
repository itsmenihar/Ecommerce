package kartonline.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kartonline.abstractComponents.AbstractComponent;

public class ConformationPage extends AbstractComponent {
	WebDriver driver;

	public ConformationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[text()='Your order has been placed!']")
	WebElement ConformMessage;
	
	public String conformMessage() {
		return ConformMessage.getText();
	}
}
