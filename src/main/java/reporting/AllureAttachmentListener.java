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
        byte[] array = {1};
        try {
            array = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            final String screenshotPath = "screenshots/scr_" + System.nanoTime() + ".png";
            MyLogger.info("Saving screenshot: " + screenshotPath);
            FileUtils.writeByteArrayToFile(new File(screenshotPath), array);
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
