package stepdefs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import cucumber.api.java.en.Then;
import enums.WebsiteInfo;
import hooks.TestContext;
import org.openqa.selenium.By;

public class AssertionSteps extends BaseStep {

  @Then("Browser title equals '([^\"]*)'")
  public void browserTitleEquals(WebsiteInfo title) {
    isTitlePresentedWithText(title.getText());
  }

  @Then("^I logoff$")
  public void iLogoff() {
    TestContext.getDriver().findElement(By.id("PH_logoutLink")).click();
  }

  @Then("^Drafts addressee is displayed$")
  public void draftsAddresseeIsDisplayed() {
    assertTrue(draftsPage.isDraftsAddresseeDisplayed());
  }

  @Then("^Drafts subject contains '([^\"]*)'$")
  public void draftsSubjectContainsSubject(WebsiteInfo subject) {
    assertTrue(draftsPage.getDraftsSubjectText().contains(subject.getText()));
  }

  @Then("^Sent page contains '([^\"]*)'$")
  public void sentPageContainsMailSubject(WebsiteInfo subject) {
    assertTrue(sentPage.getMailDetailsText().contains(subject.getText()));
  }

  @Then("^I see in the Drafts box '([^\"]*)'$")
  public void iSeeInTheDraftsBoxMessage(WebsiteInfo message) {
    assertEquals(draftsPage.getNoDraftsMessageText(), message.getText());
  }

  @Then("^Mail details text contains '([^\"]*)'$")
  public void mailDetailsTextContainsSubject(WebsiteInfo subject) {
    assertTrue(sentPage.getMailDetailsText().contains(subject.getText()));
  }

  @Then("^I addressee is displayed in the copy field$")
  public void iAddresseeIsDisplayedInTheCopyField() {
    assertTrue(baseMailPage.checkAddresseeVisibility());
  }

  @Then("^the last sent mail is displayed$")
  public void theLastSentMailIsDisplayed() {
    assertTrue(sentPage.isSentMailDisplayed());
  }
}
