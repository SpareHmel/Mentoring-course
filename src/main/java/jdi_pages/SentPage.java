package jdi_pages;

import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.complex.JList;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.XPath;

public class SentPage extends BaseMailPage {

  @XPath("//div[@class='dataset__items']//a[@data-id]")
  public JList<UIElement> mailsList;
}
