package homework12.task2;

import homework11.ChromeDriver.DriverForChrome;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

import static org.openqa.selenium.By.className;

public class Automatisation {
    private static WebDriver driver;

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
        String searchwindow = driver.getWindowHandle();
        actions.keyDown(Keys.CONTROL).click(driver.findElement(By.cssSelector("h3.LC20lb.MBeuO.DKV0Md"))).perform();
        WebElement searchInput = driver.findElement(By.id("APjFqb"));
        searchInput.clear();

    }

    @Test
    public static void step3() {
        step1and2();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //String searchwindow = driver.getWindowHandle();
        //Set<String> currentwindows = driver.getWindowHandles();
        //for (String window : currentwindows) {
        // if (!window.equals(searchwindow)) {
        //  driver.switchTo().window(window);
        //  driver.switchTo().window(searchwindow);
        // }
        //}+
        WebElement textarea = driver.findElement(By.id("APjFqb"));
        textarea.click();
        textarea.sendKeys("jj");
        js.executeScript("arguments[0].value = '';", textarea);
        textarea.sendKeys("https://www.hyrtutorials.com/p/alertsdemo.html");
        WebElement searchButton = driver.findElement(By.cssSelector("button[jsname='Tg7LZd']"));
        searchButton.click();
    }

    @Test
    public static void step4() {
        step3();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        actions.keyDown(Keys.CONTROL).click(driver.findElement(By.cssSelector("cite.qLRx3b.tjvcx.GvPZzd.cHaqb"))).perform();
        String currentWindow = driver.getWindowHandle();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        //driver.switchTo().window(tabs.get(1));
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit");
        WebElement cookies = driver.findElement(By.id("accept-choices"));
        System.out.println(cookies.getText());
        cookies.click();
        WebElement iframeElement = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(iframeElement);
        js.executeScript("arguments[0].value = 'Ilya';", driver.findElement(By.id("fname")));
        js.executeScript("arguments[0].value = 'Konovalchenko';", driver.findElement(By.id("lname")));

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        js.executeScript("arguments[0].click();", submitButton);
        WebElement message = driver.findElement(By.cssSelector(".w3-panel"));
        System.out.println(message.getText());
        driver.switchTo().defaultContent();
        driver.switchTo().window(tabs.get(2));
    }

    @Test
    public static void step5() {
        step4();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '21';", driver.findElement(By.id("DateOfBirthDay")));
        js.executeScript("arguments[0].value = '8';", driver.findElement(By.id("DateOfBirthMonth")));
        js.executeScript("arguments[0].value = '1990';", driver.findElement(By.id("DateOfBirthYear")));
        js.executeScript("arguments[0].value = 'Kudaev';", driver.findElement(By.id("LastName")));
        js.executeScript("arguments[0].value = 'Siarhei';", driver.findElement(By.id("FirstName")));
        WebElement country = driver.findElement(By.id("Country"));
        country.click();
        Select countryDropDown = new Select(country);
        countryDropDown.selectByVisibleText("Ukraine");
        // добавил тк обычный wait не работал
        try {
            Thread.sleep(3000);  // Подождем 1 секунду (можно настроить под свои нужды)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement stateInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("State")));
        stateInput.click();
        js.executeScript("arguments[0].value = 'Dnipro';", stateInput);
        js.executeScript("arguments[0].value = 'mail@mail.ggg';", driver.findElement(By.id("EmailAddress")));
        js.executeScript("arguments[0].value = 'mail@mail.ggg';", driver.findElement(By.id("ConfirmEmailAddress")));
        js.executeScript("arguments[0].value = 'Password1';", driver.findElement(By.id("Password")));

        //js.executeScript("arguments[0].value = 'Password1';", driver.findElement(By.id("ConfirmPassword")));
        WebElement passwordConfirm = wait.until(ExpectedConditions.elementToBeClickable(By.id("ConfirmPassword")));
        js.executeScript("arguments[0].value = 'Password333333333';", driver.findElement(By.id("ConfirmPassword")));
        actions.keyDown(Keys.RETURN).build().perform();
        WebElement errorMeassage = driver.findElement(By.className("field-validation-error"));
        System.out.println(errorMeassage.getText());

    }

    @Test
    public static void step6() {
        step5();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("fc-button-label")));
            cookieButton.click();
        } catch (TimeoutException e) {
            System.out.println("Cookie button not found or not clickable.");
        }
        WebElement alertBoxButton = driver.findElement(By.id("alertBox"));
        alertBoxButton.click();
        Alert alert1 = driver.switchTo().alert();
        alert1.accept();
        System.out.println(driver.findElement(By.id("output")).getText());
        driver.switchTo().defaultContent();
        WebElement confirmBoxButton = driver.findElement(By.id("confirmBox"));
        confirmBoxButton.click();
        Alert alert2 = driver.switchTo().alert();
        alert2.dismiss();
        System.out.println(driver.findElement(By.id("output")).getText());
        driver.switchTo().defaultContent();
        WebElement promptBoxButton = driver.findElement(By.id("promptBox"));
        promptBoxButton.click();
        Alert alert3 = driver.switchTo().alert();
        alert3.sendKeys("Final step of this task");
        alert3.accept();
        System.out.println(driver.findElement(By.id("output")).getText());
    }

    @BeforeMethod
    public void addDriver() {
        driver = DriverForChrome.ChromeSetDriver();
    }
    @AfterMethod
    public void closeDriver() {
        if (driver != null) driver.quit();
    }
}

