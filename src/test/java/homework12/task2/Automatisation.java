package homework12.task2;

import homework11.ChromeDriver.DriverForChrome;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import static org.openqa.selenium.By.className;

public class Automatisation {
    private static WebDriver driver;

    @BeforeMethod
    public void addDriver() {
        driver = DriverForChrome.ChromeSetDriver();
    }

    //@AfterMethod
    //public void closeDriver() {
    //if (driver != null) {
    //driver.quit();
    // }
    // }

    @Test
    public static void step1and2() {
        driver.get("https://www.google.com/search");
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            WebElement acceptAllButton = driver.findElement(By.cssSelector(".QS5gu.sy4vM"));
            if (acceptAllButton.isDisplayed()) {
                acceptAllButton.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        actions
                .moveToElement(driver.findElement(className("gLFyf")))
                .click()
                .sendKeys("https://www.guinnessworldrecords.com/account/register?")
                .sendKeys(Keys.RETURN)
                .build()
                .perform();
        actions.keyDown(Keys.CONTROL).click(driver.findElement(By.cssSelector("h3.LC20lb.MBeuO.DKV0Md"))).perform();
        WebElement clearButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.BKRPef")));
        clearButton.click();
      }


    //@Test
    //public static void step3() {
        step1and2();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Set<String> currentwindows = driver.getWindowHandles();
        //System.out.println(currentwindows + driver.getCurrentUrl());
        //WebElement clearButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.BKRPef")));
        //clearButton.click();
    //}
}
