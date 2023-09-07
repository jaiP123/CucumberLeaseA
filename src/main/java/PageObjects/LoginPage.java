package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    //private By signInLocator = By.xpath("//span[contains(.,'Hello, sign in')]");
    private By signInLocator = By.id("nav-link-accountList");

    private By accountlistLocator = By.id("nav-link-accountList");
    private By userIdentityLocator = By.className("nav-line-1-container");
    private By emailInputLocator = By.name("email");
    private By continueLocator = By.id("continue");
    private By passwordInputLocator = By.name("password");
    private By loginButtonLocator = By.id("signInSubmit");
    private By forgottenPasswordLinkLocator = By.linkText("Forgot Password");
    private By logoutLinkLocator = By.xpath("//*[@id='nav-al-your-account']//a[@id='nav-item-signout']");


    // Methods
    public void clickLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable (By.xpath("//span[contains(.,'Hello, sign in')]")));
        //WebElement SignBtn = driver.findElement(signInLocator);
       // SignBtn.click();
        driver.findElement(By.xpath("//span[contains(.,'Hello, sign in')]")).click();
    }

    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(emailInputLocator);
        emailInput.sendKeys(email);
    }

    public void clickContinue() {
        WebElement continueBtn = driver.findElement(continueLocator);
        continueBtn.click();
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordInputLocator);
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();
    }

    public boolean checkLoggedIn() {
        WebElement userIdentity = driver.findElement(userIdentityLocator);
        String test = userIdentity.getText();

        if (test.contains("sign")) {
            return false;
        }
        return true;
    }

    public void sign_out() {
        Actions a = new Actions(driver);
        WebElement ele = driver.findElement(By.xpath("//*[@id='nav-link-accountList']"));
        a.moveToElement(ele).build().perform();
        WebElement signoutBtn = driver.findElement(logoutLinkLocator);
        signoutBtn.click();
    }
}
