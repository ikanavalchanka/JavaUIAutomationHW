package homework10.task3;

import homework10.task2.DriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = DriverSetUp.SetDriver();
        driver.get("https://www.google.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
WebElementCompare.compareElementsY(driver.findElement(By.cssSelector(".lnXdpd")), driver.findElement(By.cssSelector(".gNO89b")));
   WebElementCompare.compareElementsX(driver.findElement(By.cssSelector(".gNO89b")), driver.findElement(By.cssSelector(".RNmpXc")));
   WebElementCompare.comapreElementsSize(driver.findElement(By.cssSelector(".lnXdpd")), driver.findElement(By.cssSelector(".gNO89b")));
   driver.quit();
    }
}
