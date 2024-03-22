package homework13.config;

import homework13.driver.Driver2;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureScreenshot implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot(
                ((TakesScreenshot) Driver2.getInstance()).getScreenshotAs(OutputType.BYTES));
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    private byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }

}
