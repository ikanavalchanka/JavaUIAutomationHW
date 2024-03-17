package homework13.tests;

import homework13.config.ConfigForLoginPage;
import homework13.config.ConfigForRegistrationPage;
import homework13.driver.Driver2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

@Listeners(TestForAndersenPage.TestNGListener.class)
public class TestForAndersenPage {
    private static WebDriver driver;
    private ConfigForLoginPage loginPage;
    private ConfigForRegistrationPage registrationPage1;

    @BeforeClass
    public static void setUp() {
        driver = Driver2.startDriver();
    }

    public static void maKeScreenshot(String method) {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File("./target/screenshots/" + method + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test //Confirm that the system handles an incorrect password correctly
    public void testingLoginWithIncorrectPassword() {
        loginPage = new ConfigForLoginPage(driver);
        loginPage.openLoginPage();
        loginPage.enterEmail("mail@mail.com");
        loginPage.enterPassword("invalidPassword1");
        loginPage.clickSubmitButton();
        Assert.assertTrue(loginPage.checkIfErrorMessageIsDisplayed());
    }

    @Test // Check if the system properly handles submiting login dorm with empty fields
    public void testingSendingFormWithEmptyFields() {
        loginPage = new ConfigForLoginPage(driver);
        loginPage.openLoginPage();
        loginPage.clickSubmitButton();
        Assert.assertTrue(loginPage.checkIfRequiredMessageIsDisplayed());
    }

    @Test //Verify Registration button redirects the user to the registration page
    public void verifyRegistrationPageOpens() {
        loginPage = new ConfigForLoginPage(driver);
        loginPage.openLoginPage();
        loginPage.clickRegistrationButton();
        String expectedUrl = "https://qa-course-01.andersenlab.com/registration";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Registration page is not opened.");
    }

    @Test // validate the registration form checks for a valid email format
    public void checkInvalidEmailErrorMessage() {
        registrationPage1 = new ConfigForRegistrationPage(driver);
        registrationPage1.openRegistrationPage();
        registrationPage1.enterEmailR("invalidmail123@!!!");
        registrationPage1.pressTabKey();
        Assert.assertTrue(registrationPage1.checkErrorMessageForEmailField(), "Error message is not displayed");
    }

    @Test // Confirm error message that entered passwords must match
    public void checkPasswordsMatchErrorMessage() {
        registrationPage1 = new ConfigForRegistrationPage(driver);
        registrationPage1.openRegistrationPage();
        registrationPage1.enterPassword("qwerty123");
        registrationPage1.confirmPasword("invalidpassword");
        registrationPage1.pressTabKey();
        Assert.assertTrue(registrationPage1.checkPasswordsErrorMessage(), "Error message is not displayed");
    }

    @Test // Verify that the system does not allow to send registration form with empty fields
    public void chechRegistrationWithEmptyFields() {
        registrationPage1 = new ConfigForRegistrationPage(driver);
        registrationPage1.openRegistrationPage();
        registrationPage1.clickSubmitButton();
        Assert.assertTrue(registrationPage1.checkRequiredErrorMessage(), "Error message is not displayed");
    }

    @Test //Verify that the Edit Account page opens
    public void editAccountPageCheck() {
        loginPage = new ConfigForLoginPage(driver);
        loginPage.openLoginPage();
        loginPage.logInAsRegisteredUser1AndOpenEditAccountPage("testuser1@mail", "12345678");
        Assert.assertTrue(driver.getCurrentUrl().equals("https://qa-course-01.andersenlab.com/editAccount") , " Page edit Account does not open");
    }

   @AfterClass
    public static void tearDown() {
     if (driver != null) {
         driver.quit();
    }
    }

    public static class TestNGListener implements ITestListener {
        @Override
        public void onTestStart(ITestResult result) {
            System.out.println("Test with name " + result.getMethod().getMethodName() + " started");
        }

        @Override
        public void onTestFailure(ITestResult result) {
            maKeScreenshot(result.getMethod().getMethodName());
        }
    }
}