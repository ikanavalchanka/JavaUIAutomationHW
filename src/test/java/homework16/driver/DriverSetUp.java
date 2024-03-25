package  homework16.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetUp {

    private  static AppiumDriver<WebElement> driver;

    public static AppiumDriver<WebElement> getDriver() {
        if (driver == null) {
            try {
                driver = setUpDriver();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    private static AppiumDriver<WebElement> setUpDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2_API_31");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability("appPackage", "io.appium.android.apis");
        desiredCapabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
        desiredCapabilities.setCapability("adbExecTimeout", 100000);
        desiredCapabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", 300000);
        desiredCapabilities.setCapability("appium:uiautomator2ServerInstallTimeout", 200000);
        driver = new io.appium.java_client.android.AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        return driver;
    }
}
