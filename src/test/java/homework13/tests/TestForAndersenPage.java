package homework13.tests;

import homework13.config.AllureScreenshot;
import homework13.config.ConfigForLoginPage;
import homework13.config.ConfigForRegistrationPage;
import homework13.config.ScreenshotListener;
import homework13.driver.Driver2;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ScreenshotListener.class, AllureScreenshot.class})
public class TestForAndersenPage {
    private static WebDriver driver;
    //public static void maKeScreenshot(String method) {
    //File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    private ConfigForLoginPage loginPage;
    private ConfigForRegistrationPage registrationPage1;

    @BeforeClass
    public static void setUp() {
        driver = Driver2.startDriver();
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    @Feature(" login negative testing")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Confirm that the system handles an incorrect password correctly")
    public void testingLoginWithIncorrectPassword() {
        loginPage = new ConfigForLoginPage(driver);
        loginPage.openLoginPage();
        loginPage.enterEmail("mail@mail.com");
        loginPage.enterPassword("invalidPassword1");
        loginPage.clickSubmitButton();
        Assert.assertTrue(loginPage.checkIfErrorMessageIsDisplayed());
    }


    @Feature(" login negative testing")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if the system properly handles submiting login dorm with empty fields")
    @Test (priority = 2)
    public void testingSendingFormWithEmptyFields() {
        loginPage = new ConfigForLoginPage(driver);
        loginPage.openLoginPage();
        loginPage.clickSubmitButton();
        Assert.assertTrue(loginPage.checkIfRequiredMessageIsDisplayed());
        try {
            Thread.sleep(2000); // Подождать 2 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Feature(" login positive testing")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify Registration button redirects the user to the registration page")
    @Test( priority = 3)
    public void verifyRegistrationPageOpens() {
        loginPage = new ConfigForLoginPage(driver);
        loginPage.openLoginPage();
        loginPage.clickRegistrationButton();
        String expectedUrl = "https://qa-course-01.andersenlab.com/registration";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Registration page is not opened.");
        try {
            Thread.sleep(2000); // Подождать 2 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Feature(" registration negative testing")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Validate the registration form checks for a valid email format")
    @Test(priority = 4)
    public void checkInvalidEmailErrorMessage() {
        registrationPage1 = new ConfigForRegistrationPage(driver);
        registrationPage1.openRegistrationPage();
        registrationPage1.enterEmailR("invalidmail123@!!!");
        registrationPage1.pressTabKey();
        Assert.assertTrue(registrationPage1.checkErrorMessageForEmailField(), "Error message is not displayed");
        try {
            Thread.sleep(2000); // Подождать 2 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Feature(" registration negative testing")
    @Severity(SeverityLevel.NORMAL)
    @Description("Confirm error message that entered passwords must match")
    @Test(priority = 5)
    public void checkPasswordsMatchErrorMessage() {
        registrationPage1 = new ConfigForRegistrationPage(driver);
        registrationPage1.openRegistrationPage();
        registrationPage1.enterPassword("qwerty123");
        registrationPage1.confirmPasword("invalidpassword");
        registrationPage1.pressTabKey();
        Assert.assertTrue(registrationPage1.checkPasswordsErrorMessage(), "Error message is not displayed");
        try {
            Thread.sleep(2000); // Подождать 2 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Feature(" registration negative testing")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the system does not allow to send registration form with empty fields")
    @Test(priority = 6)
    public void chechRegistrationWithEmptyFields() {
        registrationPage1 = new ConfigForRegistrationPage(driver);
        registrationPage1.openRegistrationPage();
        registrationPage1.clickSubmitButton();
        Assert.assertTrue(registrationPage1.checkRequiredErrorMessage(), "Error message is not displayed");
        try {
            Thread.sleep(2000); // Подождать 2 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Feature(" edit account negative testing")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Edit Account page opens")
    @Test(priority = 7)
    public void editAccountPageCheck() {
        loginPage = new ConfigForLoginPage(driver);
        loginPage.openLoginPage();
        loginPage.logInAsRegisteredUser1AndOpenEditAccountPage("testuser1@mail", "12345678");
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/editAccount", " Page edit Account does not open");
        try {
            Thread.sleep(2000); // Подождать 2 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class TestNGListener implements ITestListener {
        @Override
        public void onTestStart(ITestResult result) {
            System.out.println("Test with name " + result.getMethod().getMethodName() + " started");
        }
    }
}