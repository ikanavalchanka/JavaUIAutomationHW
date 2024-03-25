package homework16.ApiDemosConfig;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class Swiper {
    public AppiumDriver<WebElement> driver;

    public Swiper(AppiumDriver<WebElement> driver) {
        this.driver = driver;
    }

    public enum Direction {
        UP, DOWN
    }

    public void swipe(Direction direction) {
        int startY = 0;
        int finishY = 0;
        Dimension screenSize = driver.manage().window().getSize();
        int startX = screenSize.width / 2;
        int finishX = screenSize.width / 2;
        switch (direction) {
            case DOWN:
                startY = (int) (screenSize.height * 0.3);
                finishY = (int) (screenSize.height * 0.7);
                break;
            case UP:
                startY = (int) (screenSize.height * 0.7);
                finishY = (int) (screenSize.height * 0.3);
                break;
        }
        swipe(startX, startY, finishX, finishY);
    }

    public void swipeUntilElementFound(Direction direction, By locator) {
        while (driver.findElements(locator).isEmpty()) {
            swipe(direction);
        }
    }

    private void swipe(int startX, int startY, int finishX, int finishY) {
        new TouchAction<>(driver)
                .press(PointOption.point(startX, startY))
                .moveTo(PointOption.point(finishX, finishY))
                .release()
                .perform();
    }
}