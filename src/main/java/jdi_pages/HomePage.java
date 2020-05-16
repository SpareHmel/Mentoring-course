package jdi_pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.XPath;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.TextField;
import entities.User;

public class HomePage extends WebPage {

  @XPath("//input[@id='mailbox:login']")
  public TextField loginField;

  @XPath("//input[@id='mailbox:password']")
  public TextField passwordField;

  @XPath("//input[@class='o-control']/..")
  public Button submitButton;

  public void signIn(User user) {
    loginField.clear();
    loginField.sendKeys(user.getLogin());
    submitButton.click();
    passwordField.sendKeys(user.getPassword());
    submitButton.click();
  }
}
