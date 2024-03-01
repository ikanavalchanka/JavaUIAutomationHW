package homework10.task1;

import homework10.task2.DriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Registration {
    public static void testCase1() {
//Testcase1 - Verify that a user can successfully register by providing valid information
//  Expected result- The system should register the user successfully and provide feedback or redirect to the login page.
        System.out.println("Running testCase1 for Registration Module");
        WebDriver driver = DriverSetUp.SetDriver();
        driver.get("https://qa-course-01.andersenlab.com/registration");
        WebElement firstNameField = driver.findElement(By.xpath("//input[@name='firstName']"));
        WebElement lastNameField = driver.findElement(By.xpath("//input[@name='lastName']"));
        WebElement dateOfBirthField = driver.findElement(By.xpath("//input[@name='dateOfBirth']"));
        WebElement emailField = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement confirmPasswordField = driver.findElement(By.xpath("//input[@name='passwordConfirmation']"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        firstNameField.sendKeys("Oleg");
        lastNameField.sendKeys("Tinkov");
        dateOfBirthField.sendKeys("20/12/2000");
        emailField.sendKeys("tinkov@mail.aaa");
        passwordField.sendKeys("12345678qwerty");
        confirmPasswordField.sendKeys("12345678qwerty");

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", submitButton);
        System.out.println("Current url is " + driver.getCurrentUrl());
        driver.quit();

    }

    public static void testCase2() {
        // TestCase2 - Verify that the system does not allow the submission of the registration form with empty fields.
        //Expected result - The system should display error messages for all mandatory fields, indicating that they are required for registration.
        System.out.println("Running testCase2 for Registration Module");
        WebDriver driver = DriverSetUp.SetDriver();
        driver.get("https://qa-course-01.andersenlab.com/registration");
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit' and @class='mt-7 h-10 bg-[#feda00] rounded-3xl w-full']"));
        submitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By errorMessageR = By.xpath("//span[@class='absolute right-0 text-rose-500 text-sm' and text()='Required']");
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageR));

            System.out.println("Error message is displayed. Testcase passed: " + errorMessage.getText());
        } catch (Exception e) {

            System.out.println("Error message is not displayed.Testcase failed.");
        }
        driver.quit();
    }
}

