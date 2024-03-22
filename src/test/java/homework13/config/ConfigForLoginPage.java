package homework13.config;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfigForLoginPage {
    @FindBy(name = "email")
    private  WebElement emailField;
    @FindBy(name = "password")
    private  WebElement passworfField;
    @FindBy(xpath = "//button[text()='Sign in']")
    private  WebElement submitButton;
    @FindBy(xpath = "//span[text()='Email or password is not valid']")
    private  WebElement errorMessage;
    @FindBy(xpath = "//span[contains(text(), 'Required')]")
    private  WebElement requiredMessage;
    @FindBy(xpath = "//a[@href='/registration']")
    private  WebElement registrationButton;
    @FindBy(xpath = "//a[@href='/editAccount']")
    private  WebElement editAccountButton;
    private WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public ConfigForLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step ("Opening login page")
    public void openLoginPage() {
        driver.get("https://qa-course-01.andersenlab.com/login");
    }
    @Step ("Entering Email")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.sendKeys(email);
    }

    @Step ("Entering password")
    public void enterPassword(String password) {
        passworfField.sendKeys(password);
    }

    @Step ("Clicking on submit button") public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();

    }


    @Step ("Logging in as a registered user and opening edit account page")
    public void logInAsRegisteredUser1AndOpenEditAccountPage(String email1, String password1) {
        emailField.sendKeys(email1);
        passworfField.sendKeys(password1);
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(editAccountButton));
        editAccountButton.click();
        driver.getCurrentUrl();
    }

    @Step ("Checking Error message")
    public boolean checkIfErrorMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        boolean isErrorMessageDisplayed = errorMessage.isDisplayed();
        if (isErrorMessageDisplayed) {
            System.out.println(errorMessage.getText());
        }
        return isErrorMessageDisplayed;
    }

    @Step ("Checking Error message 'Required'")
    public boolean checkIfRequiredMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(requiredMessage));
        boolean isRequiredMessageDisplayed = requiredMessage.isDisplayed();
        if (isRequiredMessageDisplayed) {
            System.out.println(requiredMessage.getText());
        }
        return isRequiredMessageDisplayed;
    }

    @Step ("Clicking Registration button")
    public void clickRegistrationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        registrationButton.click();
    }
}