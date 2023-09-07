package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private WebDriver driver;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By cartLocator = By.id("nav-cart-count");

    private By subTotalLocator = By.id(("sc-subtotal-label-buybox"));
    private By checkoutLocator = By.name("proceedToRetailCheckout");
    private By cartitemsLocator = By.xpath("//div[@data-name='Active Items']//div[@class='sc-list-item-content']");

    //Methods

    public void openCart() {
        WebElement cart = driver.findElement(cartLocator);
        cart.click();
    }

    public void clearCart() {
        List<WebElement> cartItems = driver.findElements(cartitemsLocator);
        int noOfElements = cartItems.size();
        for (int i = 1; i <= noOfElements; i++) {
            WebElement ele = driver.findElement(By.xpath("(//div[@data-name='Active Items']//span[@data-action='delete']//input)[" + i + "]"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", ele);
        }
    }

    public String getCartItems() {
        WebElement cartCount = driver.findElement(subTotalLocator);
        return cartCount.getText();
    }

    public void checkout() {
        WebElement checkoutBtn = driver.findElement(checkoutLocator);
        checkoutBtn.click();
    }

}
