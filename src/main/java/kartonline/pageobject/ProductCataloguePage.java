package kartonline.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kartonline.abstractComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {

	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	@FindBy(xpath ="//div[@id='narbar-menu']//ul//li//a[text()='\"+headerName+\"']")
//	WebElement headerName;
//	
//	public WebElement prodCatagories(String headerName) {
//		return headerName;
//	}
	@FindBy(css = ".product-thumb")
	List<WebElement> products;

//	@FindBy(css = ".product-thumb")
//	WebElement productsBy;
//	
//	@FindBy(css = ".button-group button:first-of-type")
//	WebElement addToCart;
//	
//	@FindBy(css = "#toast-container")
//	WebElement toastMessage;

	By productsBy = By.cssSelector(".product-thumb");
	By addToCart = By.cssSelector(".button-group button:first-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("h4")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		scrollDown();
		prod.findElement(addToCart).click();
		waitForElementToDisappear(toastMessage);
	}

}
