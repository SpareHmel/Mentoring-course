package reporting;

import driver_manager.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class AllureAttachmentListener extends TestListenerAdapter {

    @Attachment(value = "Attachment: {0}", type = "image/png")
    public byte[] makeScreenshot(String name) {
        byte[] array = {1};
        try {
            return ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            e.printStackTrace();
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
