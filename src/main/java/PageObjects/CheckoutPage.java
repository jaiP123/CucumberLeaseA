package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    private WebDriver driver;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By headerLocator = By.xpath(" //div[@id='header']//h1");

    public String getHeaderTextCheckout() {
        WebElement headerText = driver.findElement(headerLocator);
        return headerText.getText();
    }

    public void goBackToCart() {
        driver.navigate().back();
    }
}
