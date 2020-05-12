package jdi_pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.TextField;
import entities.User;

public class HomeJdi extends WebPage {

  @Css("#mailbox:login")
  public static TextField loginField;
  @Css("#mailbox:password")
  public static TextField passwordField;
  @Css(".o-control[type='submit']")
  public static Button submitButton;

  public static void signIn(User user) {
    loginField.clear();
    loginField.sendKeys(user.getLogin());
    submitButton.click();
    passwordField.sendKeys(user.getPassword());
    submitButton.click();
  }
}
