package sections;

import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import pages.BaseMailPage;
import pages.DraftsPage;
import pages.HomePage;
import pages.SentPage;
import pages.TemplatePage;

@JSite("https://e.mail.ru")
public class MailRuStaticSite {

  @Url("https://mail.ru")
  public static HomePage homePage;

  public static BaseMailPage baseMailPage;

  @Url("/drafts")
  public static DraftsPage draftsPage;

  @Url("/sent")
  public static SentPage sentPage;

  @Url("/templates")
  public static TemplatePage templatePage;
}
