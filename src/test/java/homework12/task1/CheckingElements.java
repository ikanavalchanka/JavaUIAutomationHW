package homework12.task1;

import homework11.ChromeDriver.DriverForChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckingElements {
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
    // The test is checking if the Andersen logo is displayed on the main page
    @Test
    public void checkLogo() {
        driver.get("https://andersenlab.com/");
        Actions actions = new Actions(driver);
        WebElement logoElement = driver.findElement(By.cssSelector(".Header-module--logo--edb3a"));
        actions.moveToElement(logoElement).perform();
        Assert.assertTrue(logoElement.isDisplayed(), "Andersen logo is displayed on the main page");
    }
    // The test is checking if the chat button is displayed on the main page, and checks if the chat window opens after clicking the button
    @Test
    public void checkChatWithConsultant() {
        driver.get("https://andersenlab.com/");
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        //actions.moveToElement(driver.findElement(By.tagName("body"))).moveByOffset(200, 200).release().perform();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 500);"); // Прокрутка на 500 пикселей вниз
        WebElement chatIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("intercom-launcher")));
        Assert.assertTrue(chatIcon.isDisplayed(), "Chat button is displayed");
        actions.moveToElement(chatIcon).click().perform();
        WebElement chat = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.intercom-messenger-frame iframe")));
        Assert.assertTrue(chat.isDisplayed(), "Chat window is displayed after clicking on it");
    }
}
