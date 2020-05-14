package jdi_pages;

import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.complex.JList;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.XPath;
import com.epam.jdi.light.ui.html.elements.common.Text;

public class DraftsPage extends BaseMailPage {

  @XPath("//a[contains(@href, '/drafts/0')]")
  public JList<UIElement> draftsAddressee;

  @Css("span[class='ll-sj__normal']")
  public JList<UIElement> draftsSubjectsList;

  @XPath("//div[@class='dataset__items']//a[@data-id]")
  public JList<UIElement> draftsList;

  @Css(".letter-list .octopus__title")
  public Text noDraftsMessage;

  public void selectDraftAndSendMail() {
    draftsList.get(0).click();
    sendMail();
  }
}
