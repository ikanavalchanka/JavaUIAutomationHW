package homework13.config;

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
    private static WebElement emailField;
    @FindBy(name = "password")
    private static WebElement passworfField;
    @FindBy(xpath = "//button[text()='Sign in']")
    private static WebElement submitButton;
    @FindBy(xpath = "//span[text()='Email or password is not valid']")
    private static WebElement errorMessage;
    @FindBy(xpath = "//span[contains(text(), 'Required')]")
    private static WebElement requiredMessage;
    @FindBy(xpath = "//a[@href='/registration']")
    private static WebElement registrationButton;
    @FindBy(xpath = "//a[@href='/editAccount']")
    private static WebElement editAccountButton;
    private WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public ConfigForLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openLoginPage() {
        driver.get("https://qa-course-01.andersenlab.com/login");
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passworfField.sendKeys(password);
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();

    }

    public void logInAsRegisteredUser1AndOpenEditAccountPage(String email1, String password1) {
        emailField.sendKeys(email1);
        passworfField.sendKeys(password1);
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(editAccountButton));
        editAccountButton.click();
        driver.getCurrentUrl();
    }

    public boolean checkIfErrorMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        boolean isErrorMessageDisplayed = errorMessage.isDisplayed();
        if (isErrorMessageDisplayed) {
            System.out.println(errorMessage.getText());
        }
        return isErrorMessageDisplayed;
    }

    public boolean checkIfRequiredMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(requiredMessage));
        boolean isRequiredMessageDisplayed = requiredMessage.isDisplayed();
        if (isRequiredMessageDisplayed) {
            System.out.println(requiredMessage.getText());
        }
        return isRequiredMessageDisplayed;
    }

    public void clickRegistrationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        registrationButton.click();
    }

    private static class LocatorsForLoginPage {
        private final static By emailField = By.name("email");
    }

}