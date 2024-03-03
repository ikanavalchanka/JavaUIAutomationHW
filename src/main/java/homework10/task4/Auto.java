package homework10.task4;

import homework10.task2.DriverSetUp;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Auto {
    public static void main(String[] args) {
        task4Automation();
    }

    public static void task4Automation() {
        WebDriver driver = DriverSetUp.SetDriver();
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("http://www.automationpractice.pl/index.php");
        WebElement searchField = driver.findElement(By.xpath("//input[@class='search_query form-control ac_input' and @type='text' and @id='search_query_top' and @name='search_query' and @placeholder='Search' and @value='' and @autocomplete='off']"));
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit' and @name='submit_search' and contains(@class, 'button-search')]"));
        searchField.sendKeys("Printed chiffon dress");
        searchButton.click();
        driver.getCurrentUrl();
        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.PAGE_DOWN);
        WebElement productItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line last-line first-item-of-tablet-line first-item-of-mobile-line last-mobile-line']")));
        actions.moveToElement(productItem).perform();
        WebElement addToCompare = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='add_to_compare' and contains(@href, 'id_product=7') and @data-id-product='7']")));
        addToCompare.click();
        body.sendKeys(Keys.PAGE_UP);
        WebElement womenLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Women' and @class='sf-with-ul']")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", womenLink);
        WebElement updatedSearchField = driver.findElement(By.xpath("//input[@class='search_query form-control ac_input' and @type='text' and @id='search_query_top' and @name='search_query' and @placeholder='Search' and @value='' and @autocomplete='off']"));
        updatedSearchField.sendKeys("Faded Short");
        WebElement updatedSearchButton = driver.findElement(By.xpath("//button[@type='submit' and @name='submit_search' and contains(@class, 'button-search')]"));
        updatedSearchButton.click();

        //body = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        //body.sendKeys(Keys.PAGE_DOWN);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 450);");
        WebElement productItem2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ajax_block_product col-xs-12 col-sm-6 col-md-4 first-in-line last-line first-item-of-tablet-line first-item-of-mobile-line last-mobile-line']")));
        actions.moveToElement(productItem2).perform();
        WebElement addToCompareButton = driver.findElement(By.linkText("Add to Compare"));
        addToCompareButton.click();

        WebElement compareLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Compare')]/strong[@class='total-compare-val']")));
        compareLink.click();
        body.sendKeys(Keys.PAGE_DOWN);
        driver.quit();
    }
}
