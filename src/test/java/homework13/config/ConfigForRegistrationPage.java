package homework13.config;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfigForRegistrationPage {
    @FindBy(name = "firstName")
    private static WebElement firstNameField;
    @FindBy(name = "lastName")
    private static WebElement lastNameField;
    @FindBy(name = "dateOfBirth")
    private static WebElement dateOfBirthField;
    @FindBy(name = "email")
    private static WebElement emailFieldRegistrationPage;
    @FindBy(name = "password")
    private static WebElement passwordField;
    @FindBy(name = "passwordConfirmation")
    private static WebElement passwordConfirmationField;
    @FindBy(xpath = "//button[@class='mt-7 h-10 bg-[#feda00] rounded-3xl w-full' and text()='Submit']")
    private static WebElement submitButton;
    @FindBy(xpath = "//span[@class='absolute right-0 text-rose-500 text-sm' and text()='Invalid email address']")
    private static WebElement emailFieldErrorMessage;
    @FindBy(xpath = "//span[@class='absolute right-0 text-rose-500 text-sm' and text()='Passwords must match']")
    private static WebElement passwordFieldErrorMessage;
    @FindBy(xpath = "//span[@class='absolute right-0 text-rose-500 text-sm' and text()='Required']  ")
    private static WebElement requiredErrorMessage;
    private WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public ConfigForRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openRegistrationPage() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.elementToBeClickable(lastNameField));
        lastNameField.sendKeys(lastName);
    }

    public void selectDateOfBirth() {
        Select dateOfBirthDropdown = new Select(dateOfBirthField);
    }

    public void enterEmailR(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailFieldRegistrationPage));
        emailFieldRegistrationPage.sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.sendKeys(password);
    }

    public void confirmPasword(String passwordConfirm) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordConfirmationField));
        passwordConfirmationField.sendKeys(passwordConfirm);
    }

    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public boolean checkErrorMessageForEmailField() {
        wait.until(ExpectedConditions.visibilityOf(emailFieldErrorMessage));
        boolean isEmailErrorMessageDisplayed = emailFieldErrorMessage.isDisplayed();
        if (isEmailErrorMessageDisplayed) {
            System.out.println(emailFieldErrorMessage.getText());
        }
        return isEmailErrorMessageDisplayed;
    }

    public boolean checkPasswordsErrorMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordFieldErrorMessage));
        boolean isPasswordErrorMessageDisplayed = passwordFieldErrorMessage.isDisplayed();
        if (isPasswordErrorMessageDisplayed) {
            System.out.println(passwordFieldErrorMessage.getText());
        }
        return isPasswordErrorMessageDisplayed;
    }

    public boolean checkRequiredErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(requiredErrorMessage));
        boolean isRequiredMessageDisplayed = requiredErrorMessage.isDisplayed();
        if (isRequiredMessageDisplayed) {
            System.out.println(requiredErrorMessage.getText());
        }
        return isRequiredMessageDisplayed;
    }

    public void pressTabKey() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
    }

    private static class LocatorsForRegistrationPage {
        private final static By emailFieldRegistrationPage = By.name("email");
    }
}
