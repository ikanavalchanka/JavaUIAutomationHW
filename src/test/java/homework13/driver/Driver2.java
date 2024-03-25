package homework13.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver2 {
    private static WebDriver driver;

    private static WebDriver chromeSetDriver2() {
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            try {
                driver = chromeSetDriver2();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return driver;
    }

    public static WebDriver startDriver() {
        driver = getInstance();
        return driver;
    }
}