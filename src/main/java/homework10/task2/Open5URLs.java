package homework10.task2;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Open5URLs {
    public static void main(String[] args) {

        String[] urls = {
                "http://www.automationpractice.pl/index.php",
                "https://zoo.waw.pl/",
                "https://www.w3schools.com/",
                "https://www.clickspeedtester.com/click-counter/",
                "https://andersenlab.com/"
        };
        for ( String url : urls){
           WebDriver driver = DriverSetUp.SetDriver();
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            System.out.println("Page name " + driver.getTitle() + " Page url "  + driver.getCurrentUrl());
            if ( driver.getTitle().contains("Zoo")){
                System.out.println("The page " + driver.getTitle() + " contains the word Zoo");
                System.out.println("Closing the page " + driver.getCurrentUrl());
                driver.close();
        }
    }
}
}
