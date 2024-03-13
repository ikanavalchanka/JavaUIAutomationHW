package homework11.task5;

import homework11.ChromeDriver.DriverForChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestingWithParameters {
    //Email: vasia@mail.ru, Password: account1
    //Email: petya@mail.ru, Password: account2
    //Email: gena@mail.ru, Password: account3
    private WebDriver driver;
    @BeforeMethod
    public void addDriver() {
        driver = DriverForChrome.ChromeSetDriver();
    }
    @AfterMethod
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    @org.testng.annotations.Parameters({    "email", "password", "loginResult"})
    public void testLoginWithParameters(String email , String password, boolean loginResult){
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement signInButton = driver.findElement(By.cssSelector("button[type='submit']"));
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement editAccountLink = driver.findElement(By.xpath("//a[@class=\"mt-3 block cursor-pointer hover:underline\" and @href=\"/editAccount\"]"));
        boolean actualLoginResult = editAccountLink.isDisplayed();

        Assert.assertEquals(actualLoginResult, loginResult, "Login test failed for email: " + email);
    }
}
