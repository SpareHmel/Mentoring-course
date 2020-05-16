package jdi_pages;

import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.complex.JList;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;

public class TemplatePage extends BaseMailPage {

  @Css(".dataset-letters a")
  public JList<UIElement> templatesList;

  public void openTemplate() {
    templatesList.get(0).click();
  }
}
