package homework13.config;

import io.qameta.allure.Step;
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
    private  WebElement firstNameField;
    @FindBy(name = "lastName")
    private  WebElement lastNameField;
    @FindBy(name = "dateOfBirth")
    private  WebElement dateOfBirthField;
    @FindBy(name = "email")
    private WebElement emailFieldRegistrationPage;
    @FindBy(name = "password")
    private  WebElement passwordField;
    @FindBy(name = "passwordConfirmation")
    private  WebElement passwordConfirmationField;
    @FindBy(xpath = "//button[@class='mt-7 h-10 bg-[#feda00] rounded-3xl w-full' and text()='Submit']")
    private  WebElement submitButton;
    @FindBy(xpath = "//span[@class='absolute right-0 text-rose-500 text-sm' and text()='Invalid email address']")
    private  WebElement emailFieldErrorMessage;
    @FindBy(xpath = "//span[@class='absolute right-0 text-rose-500 text-sm' and text()='Passwords must match']")
    private  WebElement passwordFieldErrorMessage;
    @FindBy(xpath = "//span[@class='absolute right-0 text-rose-500 text-sm' and text()='Required']  ")
    private  WebElement requiredErrorMessage;
    private WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public ConfigForRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Opening Registration page")
    public void openRegistrationPage() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
    }


    @Step("Entering firstname")
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
        firstNameField.sendKeys(firstName);
    }

    @Step("Entering lastname")
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.elementToBeClickable(lastNameField));
        lastNameField.sendKeys(lastName);
    }

    //public void selectDateOfBirth() {
        //Select dateOfBirthDropdown = new Select(dateOfBirthField);
    //}

    @Step("Entering email")
    public void enterEmailR(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailFieldRegistrationPage));
        emailFieldRegistrationPage.sendKeys(email);
    }

    @Step("Entering password")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordField.sendKeys(password);
    }

    @Step("Entering password confirmation")
    public void confirmPasword(String passwordConfirm) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordConfirmationField));
        passwordConfirmationField.sendKeys(passwordConfirm);
    }

    @Step("Clicking submit button")
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    @Step("Checking if error message displayed")
    public boolean checkErrorMessageForEmailField() {
        wait.until(ExpectedConditions.visibilityOf(emailFieldErrorMessage));
        boolean isEmailErrorMessageDisplayed = emailFieldErrorMessage.isDisplayed();
        if (isEmailErrorMessageDisplayed) {
            System.out.println(emailFieldErrorMessage.getText());
        }
        return isEmailErrorMessageDisplayed;
    }

    @Step("Checking if error message displayed")
    public boolean checkPasswordsErrorMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordFieldErrorMessage));
        boolean isPasswordErrorMessageDisplayed = passwordFieldErrorMessage.isDisplayed();
        if (isPasswordErrorMessageDisplayed) {
            System.out.println(passwordFieldErrorMessage.getText());
        }
        return isPasswordErrorMessageDisplayed;
    }

    @Step("Checking if error message displayed")
    public boolean checkRequiredErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(requiredErrorMessage));
        boolean isRequiredMessageDisplayed = requiredErrorMessage.isDisplayed();
        if (isRequiredMessageDisplayed) {
            System.out.println(requiredErrorMessage.getText());
        }
        return isRequiredMessageDisplayed;
    }

    @Step("Pressing the tab key")
    public void pressTabKey() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
    }
}
