package homework12.task3;

import homework11.ChromeDriver.DriverForChrome;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AndersenCreateAcc {
    private static WebDriver driver;

    @Test
    public static void createAccount() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
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
        actions.keyDown(Keys.RETURN).build().perform();
        emailField.sendKeys("tinkov@mail.aaa");
        passwordField.sendKeys("12345678qwerty");
        confirmPasswordField.sendKeys("12345678qwerty");
        try {
            Thread.sleep(3000);  // Подождем 1 секунду (можно настроить под свои нужды)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @BeforeMethod
    public void addDriver() {
        driver = DriverForChrome.ChromeSetDriver();
    }
    @AfterMethod
    public void closeDriver() {
    if (driver != null) driver.quit();}
}
