package kartonline.abstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import kartonline.pageobject.CartPage;


public class AbstractComponent {
	private WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "//div[@class='nav float-end']//ul//li//a[@title='Shopping Cart']")
	WebElement shoppingCartHeader;
	
	public CartPage goToCartPage() throws InterruptedException
	{
		scrollUp();
		shoppingCartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	// My Account drop menu
	@FindBy(xpath = "//span[text()='My Account']")
	protected WebElement MyAccountHeader;

	// Register option in My Account dropdown list
	@FindBy(xpath = "//a[text()='Register']")
	protected WebElement RegisterOption;

	// Login option in My Account dropdown list
	@FindBy(xpath = "//a[@class='dropdown-item'][text()='Login']")
	protected WebElement Loginoption;

	// Logout option in My Account dropdown list
	@FindBy(xpath = "//a[@class='dropdown-item'][text()='Logout']")
	protected WebElement Logoutoption;

	// Logout continue option
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	protected WebElement LogoutContinue;

	public String getTitle() {
		return driver.getTitle();
	}
	
	public void scrollDown() {
		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, 500).perform();
	}
	public void scrollUp() {
		Actions actions = new Actions(driver);
		actions.scrollByAmount(0, -500).perform();
	}

	public void waitForElementToClickable(WebElement FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(FindBy));
	}

	public void waitForElementToDisplay(WebElement FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}

	public void waitForElementsToVisible(List<WebElement> FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(FindBy));
	}
	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	public void waitForElementToDisappear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));

	}
}
