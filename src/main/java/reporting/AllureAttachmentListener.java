package reporting;

import driver_manager.DriverManager;
import io.qameta.allure.Attachment;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class AllureAttachmentListener extends TestListenerAdapter {

    private WebDriver driver = DriverManager.getDriver();

    @Attachment(value = "Attachment: {0}", type = "image/png")
    public byte[] makeScreenshot(String name) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] array = {1};
        try {
            String screenshotName = "screenshots/scr" + System.nanoTime();
            MyLogger.info("Saving screenshot: " + screenshotName);
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (IOException e) {
            MyLogger.error("Failed to make screenshot");
        }
        return array;
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        makeScreenshot("failed");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        makeScreenshot("success");
    }
}
