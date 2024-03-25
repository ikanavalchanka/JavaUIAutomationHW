package homework13.config;

import homework13.driver.Driver2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotListener implements ITestListener {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("_yyyy-MM-dd_HH-mm-ss");

    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result.getMethod().getMethodName());
    }

    private void takeScreenshot(String methodName) {
        File screenshotFile = ((TakesScreenshot) Driver2.getInstance()).getScreenshotAs(OutputType.FILE);
        String fileName = "./target/screenshots/" + methodName + LocalDateTime.now().format(formatter) + ".png";
        try {
            FileUtils.copyFile(screenshotFile, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}