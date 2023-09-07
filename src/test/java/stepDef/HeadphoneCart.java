package stepDef;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.LoginPage;
import PageObjects.ProductsPage;
import dataProvider.ConfigFileReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HeadphoneCart {
    private WebDriver driver;
    private LoginPage loginPage;
    ;
    private ProductsPage productsPage;
    ;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    ConfigFileReader configFileReader;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        configFileReader= new ConfigFileReader();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on Amazon website")
    public void i_am_on_amazon_website() {
        driver.get(configFileReader.getApplicationUrl());
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @When("I submit username and password")
    public void i_submit_username_and_password() {
        loginPage.clickLogin();
        loginPage.enterEmail(configFileReader.getUserName());
        loginPage.clickContinue();
        loginPage.enterPassword(configFileReader.getPassword());
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        Assert.assertEquals(loginPage.checkLoggedIn(), true);
    }

    @Given("I click on the {string} search filter")
    public void i_click_on_the_search_filter(String string) {
        cartPage.openCart();
        cartPage.clearCart();
        productsPage.clickSearchField(string);
        productsPage.clickSearch();

    }

    @When("I add first two results to cart")
    public void i_add_first_two_results_to_cart() {
        productsPage.addToCart(2);
    }

    @Then("cart should display added items")
    public void cart_should_display_added_items() {
        cartPage.openCart();
        String itemsAdded=cartPage.getCartItems();
        Assert.assertTrue(itemsAdded.contains("Subtotal"));
    }

    @Then("I logout from amazon website")
    public void i_logout_from_amazon_website() {
        loginPage.sign_out();
    }

    @Given("I click on cart")
    public void i_click_on_cart() {
        cartPage.openCart();
    }

    @Given("cart contains two headphones")
    public void cart_contains_two_headphones() {
        cartPage.getCartItems();
    }

    @When("I add click on checkout")
    public void i_add_click_on_checkout() {
        cartPage.checkout();
    }

    @Then("cart items should be checkout")
    public void cart_items_should_be_checkout() {
        String ExpectedText = "Checkout";
        String checkoutText = checkoutPage.getHeaderTextCheckout();
        Assert.assertEquals(ExpectedText, checkoutText);
        checkoutPage.goBackToCart();
    }
}
