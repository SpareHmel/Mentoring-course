package sections;

import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;
import jdi_pages.BaseMailPage;
import jdi_pages.DraftsPage;
import jdi_pages.HomePage;
import jdi_pages.SentPage;
import jdi_pages.TemplatePage;

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
