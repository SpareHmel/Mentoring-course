package pages;

import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.XPath;
import com.epam.jdi.light.ui.html.elements.common.Button;
import com.epam.jdi.light.ui.html.elements.common.TextField;
import org.openqa.selenium.Keys;

public class BaseMailPage extends AbstractPage {

  @Css(".compose-button__txt")
  private Button writeLetterButton;

  @XPath("//div[@data-name='to']//input")
  private TextField addresseeField;

  @Css("div[class*=subject__container] input")
  private TextField subjectField;

  @XPath("//div[@role='textbox']")
  private TextField bodyField;

  @XPath("//*[@data-title-shortcut='Ctrl+S']/span")
  private Button saveAsDraftButton;

  @Css(".compose-windows button[tabindex='700']")
  private Button closeButton;

  @XPath("(//*[contains(@class, 'editor_container')]//div[contains(@class, 'withBorder')]//button)[last()]")
  private Button templateButton;

  @XPath("//div[contains(@class, 'datalist_visible')]//div[contains(@class, 'control')]")
  private Button templateSaveButton;

  public void startWritingLetter() {
    writeLetterButton.click();
  }

  public void fillAddresseeField(String field) {
    addresseeField.sendKeys(field);
  }

  public void fillSubjectField(String subject) {
    subjectField.sendKeys(subject);
  }

  public void fillBodyField(CharSequence... body) {
    bodyField.sendKeys(body);
  }

  public void saveMailAsDraft() {
    saveAsDraftButton.click();
  }

  public void closeMessageWindow() {
    closeButton.click();
    acceptAlertIfPresent();
  }

  public void sendMail() {
    bodyField.sendKeys(Keys.CONTROL, Keys.RETURN);
  }

  public void saveLetterAsTemplate() {
    templateButton.click();
    templateSaveButton.click();
  }
}
