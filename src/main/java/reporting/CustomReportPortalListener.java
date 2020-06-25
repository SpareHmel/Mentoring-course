package reporting;

import browser.Browser;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.testng.ITestResult;

public class CustomReportPortalListener extends ReportPortalTestNGListener {

  @Override
  public void onTestFailure(ITestResult testResult) {
    Browser.getInstance().takeScreenshot();
    super.onTestFailure(testResult);
  }
}
