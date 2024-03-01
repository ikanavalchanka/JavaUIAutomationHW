package homework10.task1;

import homework10.task2.DriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditAccountModule {
    public static void testCaseEditAcc1() {
        //TestCase 1-"Verify that the page Edit account opens
        //Expected result -  The page EditAccount opens
        System.out.println("Running testCase1 for login page ");
        WebDriver driver = DriverSetUp.SetDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebElement emailField = driver.findElement(By.xpath("//input[@name='email']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement signInButton = driver.findElement(By.xpath("//button[text()='Sign in']"));
        emailField.sendKeys("testacc@mail.ru");
        passwordField.sendKeys("testpassword");
        signInButton.click();
        WebElement editAccountButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='mt-3 block cursor-pointer hover:underline' and text()='Edit account']")));
        String urlBefore = driver.getCurrentUrl();
        editAccountButton.click();
        String urlAfter = driver.getCurrentUrl();
        if (urlAfter.equals("https://qa-course-01.andersenlab.com/editAccount")) {
            System.out.println("Testcase1 passed. Edit account page opens");
        } else if (urlAfter.equals(urlBefore)) {
            System.out.println("Test case failed. Edit account page hasnt been opened");
        }
        driver.quit();
    }
    public static void testCaseEditAcc2(){
        //TestCase 2- "Verify that the page Edit account is not opening for not-logged in users"
        //  Expected result -  The page EditAccount  does not open
        System.out.println("Running testCase2 for login page ");
        WebDriver driver = DriverSetUp.SetDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qa-course-01.andersenlab.com/editAccount");
        String currentUrl = driver.getCurrentUrl();
        if( currentUrl!= "https://qa-course-01.andersenlab.com/editAccount"){
            System.out.println("Test case passed. Edit account page cant be open by not-logged in user ");
        } else{
            System.out.println("Test case failed ");
        }
        driver.quit();
    }
}
