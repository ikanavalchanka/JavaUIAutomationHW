package homework16.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import homework16.ApiDemosConfig.ApiDemosCfg;
import homework16.ApiDemosConfig.Swiper;
import homework16.driver.DriverSetUp;
import io.appium.java_client.AppiumDriver;

public class ApiDemosTest {

    private AppiumDriver<WebElement> driver;
    private ApiDemosCfg apiDemos;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUpMethod() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Неявное ожидание
        apiDemos.clickOnViewsButton();
        new Swiper(driver);
    }

    @BeforeClass
    public void setUpClass() {
        driver = DriverSetUp.getDriver();
        apiDemos = new ApiDemosCfg(driver);
        wait = new WebDriverWait(driver, 10); // Явное ожидание
    }

    @Test(description = "Verifies that number of menu items on views page is 42.")
    public void testCheckNumberOfMenuItemsOnViewsPage() {
        int actualNumberOfMenuItems = apiDemos.countViewsMenuItems();
        assertEquals(actualNumberOfMenuItems, ApiDemosCfg.EXPECTED_NUMBER_OF_MENU_ITEMS,
                "Number of menu items on views page is not the same as expected.");
    }

    @Test(description = "Verifies that date can be set to the following day and time can be set to 11:11 PM.")
    public void testSettingDateAndTimeOnDialogPage() {
        apiDemos
                .clickOnDateWidgetsButton()
                .clickOnDialogButton()
                .clickOnChangeDateButton()
                .pickFollowingDay()
                .clickOnChangeTimeSpinnerButton()
                .setTime(ApiDemosCfg.HOURS, ApiDemosCfg.MINUTES, ApiDemosCfg.TIME_INDICATOR);

        String actualTimeString = apiDemos.getTimeString();
        String actualSelectedDay = apiDemos.getSelectedDay();
        String expectedSelectedDay = String.valueOf(LocalDate.now().getDayOfMonth() + 1);

        assertEquals(actualTimeString, ApiDemosCfg.EXPECTED_TIME_STRING, "Selected time is not the same as expected.");
        assertTrue(actualSelectedDay.equals(expectedSelectedDay) || actualSelectedDay.equals(ApiDemosCfg.FIRST_DAY),
                "Selected day is not the same as expected.");
    }

    @Test(description = "Verifies that counter increments after each click on 'next' button.")
    public void testCounterOnTextSwitcherPage() {
        int actualCounterValue = apiDemos
                .openTextSwitcher()
                .clickOnNextButton(ApiDemosCfg.EXPECTED_COUNTER_VALUE)
                .clickerValue();
        assertEquals(actualCounterValue, ApiDemosCfg.EXPECTED_COUNTER_VALUE, "Counter value is not the same as expected.");
    }

    @AfterMethod
    public void tearDownMethod() {
        driver.resetApp(); // Сброс приложения после каждого теста
    }
}
