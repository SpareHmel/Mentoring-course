package tests;

import static com.epam.jdi.light.elements.composite.WebPage.openUrl;
import static org.testng.Assert.assertTrue;
import static sections.MailRuStaticSite.homePage;

import org.testng.annotations.Test;

public class JdiTest {

  @Test
  public void test() {
    openUrl("https://mail.ru");
    assertTrue(homePage.isOpened());
  }
}
