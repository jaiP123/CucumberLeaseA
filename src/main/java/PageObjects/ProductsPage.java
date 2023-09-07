package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By searchFieldLocator = By.id("twotabsearchtextbox");
    private By searchIcon = By.id("nav-search-submit-text");
    private By resultLocator = By.xpath("//div[@class='sg-row']//h2)");
    private By addToCartLocator = By.id("add-to-cart-button");
    private By emailInputLocator = By.name("email");


    // Methods
    public void clickSearchField(String product) {
        WebElement SearchField = driver.findElement(searchFieldLocator);
        SearchField.click();
        SearchField.sendKeys(product);
    }

    public void clickSearch() {
        WebElement SearchIcon = driver.findElement(searchIcon);
        SearchIcon.click();
    }
    public void addToCart(int i) {
        for(int j=1;j<=i;j++) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[" + j + "]")));
            element.click();

            String parent = driver.getWindowHandle();
            List<String> windows = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(windows.get(j));
            // Inside the tab window
            WebElement AddToCartBtn = driver.findElement(addToCartLocator);
            AddToCartBtn.click();
           // switch back to parent
            driver.switchTo().window(parent);
        }
    }
}
