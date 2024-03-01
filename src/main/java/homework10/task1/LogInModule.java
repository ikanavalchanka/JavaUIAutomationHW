package homework10.task1;

import homework10.task2.DriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInModule {
    public static void testCaseLogin1() {
        //TestCase 1-Check user login when email ID and password are entered
        //Expected result - User is successfully logged in and redirected to the home page or dashboard."
        System.out.println("Running testCase1 for login page ");
        WebDriver driver = DriverSetUp.SetDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://qa-course-01.andersenlab.com/login");
            WebElement emailField = driver.findElement(By.xpath("//input[@name='email']"));
            WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
            WebElement signInButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
            emailField.sendKeys("testacc@mail.ru");
            passwordField.sendKeys("testpassword");

            wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();

            WebElement editAccount = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='mt-3 block cursor-pointer hover:underline' and text()='Edit account']")));

            if (editAccount != null) {
                System.out.println("Testcase passed. User Logged In");
            } else {
                System.out.println("Testcase Failed");
            }
        } finally {
            driver.quit();
        }
    }

    public static void testCaseLogin2() {
        // TestCase2 - Ensure that the system handles an invalid username correctly."
        // Expected result - System displays an error message indicating that the email is incorrect."

        System.out.println("Running testCase2 for login page ");
        WebDriver driver = DriverSetUp.SetDriver();
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebElement emailField = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement signInButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
        emailField.sendKeys("invalidmail@mmm.com");
        passwordField.sendKeys("testpassword");
        signInButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By errorMessageRR = By.xpath("//span[text()='Email or password is not valid']");
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageRR));
            System.out.println("Error message is displayed. Testcase passed: " + errorMessage.getText());
        } catch (Exception e) {
            System.out.println("Error message is not displayed. Testcase failed.");
        }
        driver.quit();
    }
}

