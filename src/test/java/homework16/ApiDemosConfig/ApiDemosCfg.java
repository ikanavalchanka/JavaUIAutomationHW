package homework16.ApiDemosConfig;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class ApiDemosCfg {

    private static final By views = MobileBy.xpath("//android.widget.TextView[@content-desc='Views']");
    private static final By dateWidgets = MobileBy.xpath("//android.widget.TextView[@content-desc='Date Widgets']");
    private static final By dialog = MobileBy.xpath("//android.widget.TextView[@content-desc='1. Dialog']");
    private static final By changeTheDate = MobileBy.xpath("//android.widget.Button[@content-desc='change the date']");
    private static final By webView3 = MobileBy.xpath("//android.widget.TextView[@content-desc='WebView3']");
    private static final By animation = MobileBy.xpath("//android.widget.TextView[@content-desc='Animation']");
    private static final By viewsMenu = MobileBy.xpath("//android.widget.TextView[@resource-id='android:id/text1']");
    private static final By selectedDay = MobileBy.xpath("//android.view.View[contains(@content-desc,'selected')]");
    private static final By following = MobileBy.xpath("//android.view.View[contains(@content-desc,'selected')]/following-sibling::android.view.View[1]");
    private static final By dateOkButton = MobileBy.xpath("//android.widget.Button[@resource-id='android:id/button1']");
    private static final By changeTheTimeSpinner = MobileBy.xpath("//android.widget.Button[@content-desc='change the time (spinner)']");
    private static final By hoursSpinner = MobileBy.xpath("(//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[1]");
    private static final By minutesSpinner = MobileBy.xpath("(//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[2]");
    private static final By AMPMSpinner = MobileBy.xpath("(//android.widget.EditText[@resource-id='android:id/numberpicker_input'])[3]");
    private static final By timeSpinnerOkButton = MobileBy.xpath("//android.widget.Button[@resource-id='android:id/button1']");
    private static final By textSwitcher = MobileBy.xpath("//android.widget.TextView[@content-desc='TextSwitcher']");
    private static final By nextButton = MobileBy.xpath("//android.widget.Button[@content-desc='Next']");
    private static final By clickAmount = MobileBy.xpath("//android.widget.TextSwitcher[@resource-id='io.appium.android.apis:id/switcher']/android.widget.TextView");
    public static final int DEFAULT_WAIT_TIME = 50;
    public static final int EXPECTED_NUMBER_OF_MENU_ITEMS = 42;
    public static final int EXPECTED_COUNTER_VALUE = 5;
    public static final String HOURS = "11";
    public static final String MINUTES = "11";
    public static final String TIME_INDICATOR = "PM";
    public static final String EXPECTED_TIME_STRING = "11:11 PM";
    public static final String FIRST_DAY = "1";

    private AppiumDriver<WebElement> driver;
    private WebDriverWait wait;
    private Swiper swiper;

    public ApiDemosCfg(AppiumDriver<WebElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        swiper = new Swiper(driver);
    }


    public ApiDemosCfg clickOnViewsButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(views)).click();
        return this;
    }

    public ApiDemosCfg clickOnDateWidgetsButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateWidgets)).click();
        return this;
    }

    public ApiDemosCfg clickOnDialogButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dialog)).click();
        return this;
    }

    public ApiDemosCfg clickOnChangeDateButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(changeTheDate)).click();
        return this;
    }

    public ApiDemosCfg clickOnChangeTimeSpinnerButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(changeTheTimeSpinner)).click();
        return this;
    }

    public ApiDemosCfg pickFollowingDay() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(following)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateOkButton)).click();
        return this;
    }
    public ApiDemosCfg pickDay(String day) {
        String dynamicDateXpath = String.format("//android.view.View[@text='%s']", day);
        driver.findElement(By.xpath(dynamicDateXpath)).click();
        driver.findElement(dateOkButton).click();
        return this;
    }

    public ApiDemosCfg setTime(String hours, String minutes, String timeIndicator) {
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(hoursSpinner));
        longPressToElement(hoursSpinner);
        action.sendKeys(hours).perform();
        longPressToElement(minutesSpinner);
        action.sendKeys(minutes).perform();
        longPressToElement(AMPMSpinner);
        action.sendKeys(timeIndicator).perform();
        driver.findElement(timeSpinnerOkButton).click();
        return this;
    }

    public String getSelectedDay() {
        clickOnChangeDateButton();
        String selectedDay1 = driver.findElement(selectedDay).getAttribute("content-desc").split(" ")[0];
        driver.findElement(dateOkButton).click();
        return selectedDay1;
    }
    public boolean isDaySelected(String day) {
        String selectedDay = driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'selected')]")).getAttribute("content-desc");
        return selectedDay.equals(day);
    }
    public String getTimeString() {
        clickOnChangeTimeSpinnerButton();
        String hours = driver.findElement(hoursSpinner).getText();
        String minutes = driver.findElement(minutesSpinner).getText();
        String timeIndicator = driver.findElement(AMPMSpinner).getText();
        driver.findElement(timeSpinnerOkButton).click();
        return hours + ":" + minutes + " " + timeIndicator;
    }

    public ApiDemosCfg openTextSwitcher() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(animation));
        swiper.swipeUntilElementFound(Swiper.Direction.UP, textSwitcher);
        driver.findElement(textSwitcher).click();
        return this;
    }

    public ApiDemosCfg clickOnNextButton(int numberOfTimes) {
        WebElement nextButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nextButton));
        for (int i = 0; i < numberOfTimes; ++i) {
            nextButtonElement.click();
        }
        return this;
    }

    public int clickerValue() {
        return Integer.parseInt(driver.findElement(clickAmount).getText());
    }

    public static final int MAX_SWIPE_ATTEMPTS = 5;

    public int countViewsMenuItems() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(animation));
        Set<String> viewsMenuItems = new HashSet<>();
        addVisibleMenuItemsToSet(viewsMenuItems);
        Swiper swiper = new Swiper(driver);
        int attempts = 0;
        while (driver.findElements(webView3).isEmpty() && attempts < MAX_SWIPE_ATTEMPTS) {
            swiper.swipe(Swiper.Direction.UP);
            addVisibleMenuItemsToSet(viewsMenuItems);
            attempts++;
        }
        return viewsMenuItems.size();
    }

    private void addVisibleMenuItemsToSet(Set<String> viewsMenuItems) {
        for (WebElement menuItem : driver.findElements(viewsMenu)) {
            viewsMenuItems.add(menuItem.getText());
        }
    }

    private void longPressToElement(By locator) {
        TouchAction action = new TouchAction(driver);
        LongPressOptions longPressOptions = LongPressOptions.longPressOptions()
                .withElement(ElementOption.element(driver.findElement(locator)))
                .withDuration(Duration.ofSeconds(2));
        action.longPress(longPressOptions).release().perform();
    }
}