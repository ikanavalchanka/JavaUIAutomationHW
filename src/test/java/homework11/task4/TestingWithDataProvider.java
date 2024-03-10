package homework11.task4;

import homework11.ChromeDriver.DriverForChrome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TestingWithDataProvider {
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
    @Test(dataProvider = "credentials")
    public  void logInTest(String email, String password, boolean loginResult) {
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement signInButton = driver.findElement(By.cssSelector("button[type='submit']"));
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
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

    public static void dataProvider(String mail, String password){
        System.out.println("Account email "+ mail + "Account password " + password );
    }
    @DataProvider(name = "credentials")
    public Object[][] provideCredentials(){
        return new Object[][]{
                {"vasia@mail.ru", "account1",true},
                {"petya@mail.ru", "account2", true},
                {"gena@mail.ru", "account3",true}
        };
    }
}

