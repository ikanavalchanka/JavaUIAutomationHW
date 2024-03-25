package homework15.step_definitions;

import homework15.driver.DriverSetUp;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import java.time.Duration;


public class LogInPageStepDefinition {
    private static class Strings {
        private static final String url = "https://qa-course-01.andersenlab.com/login";
        private static final String email = "mail@mail.com";
        private static final String password = "invalidPassword1";
    }
    @FindBy(name = "email")
    private WebElement emailField;
    @FindBy(name = "password")
    private WebElement passworfField;
    @FindBy(xpath = "//button[text()='Sign in']")
    private WebElement signInButton;
    @FindBy(xpath = "//span[text()='Email or password is not valid']")
    private WebElement errorMessage;
    @FindBy(xpath = "//span[contains(text(), 'Required')]")
    private WebElement requiredMessage;
    @FindBy(xpath = "//a[@href='/registration']")
    private WebElement registrationButton;
    @FindBy(xpath = "//a[@href='/editAccount']")
    private WebElement editAccountButton;
    private WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    @Given("set up driver")
    public void set_up_driver() {
       driver=DriverSetUp.startDriver();
       wait= new WebDriverWait(driver, Duration.ofSeconds(10));
       PageFactory.initElements(driver, this);
    }
    @Given("User is on LogIn page")
    public void user_is_on_log_in_page() {
        driver.get(Strings.url);
    }
    @When("I enter email")
    public void i_enter_email() {
        emailField.sendKeys(Strings.email);
    }
    @When("I enter incorrect password")
    public void i_enter_incorrect_password() {
        passworfField.sendKeys(Strings.password);
    }
    @When("I click on the SignIn button")
    public void i_click_on_the_sign_in_button() {
        signInButton.click();
    }
    @Then("I see an error message above the password string")
    public void i_see_an_error_message_above_the_password_string() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        Assert.assertTrue(errorMessage.isDisplayed());
    }
    @Then("I see an error message above the email and password strings")
    public void i_see_an_error_message_above_the_email_and_password_strings() {
        wait.until(ExpectedConditions.visibilityOf(requiredMessage));
        Assert.assertTrue(requiredMessage.isDisplayed());
    }
    @When("I click on the Registration button")
    public void i_click_on_the_registration_button() {
        registrationButton.click();
    }
    @Then("Registration page opens")
    public void registration_page_opens() {
        String expectedUrl = "https://qa-course-01.andersenlab.com/registration";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Registration page is not opened.");
    }
}
