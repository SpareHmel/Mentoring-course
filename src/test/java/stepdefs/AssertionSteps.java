package stepdefs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

public class AssertionSteps extends BaseStep {

  @Then("Browser title equals {string}")
  public void browserTitleEquals(String title) {
    isTitlePresentedWithText(title);
  }

  @Then("I logoff")
  public void iLogoff() {
    driver.findElement(By.id("PH_logoutLink")).click();
  }

  @Then("Drafts addressee is displayed")
  public void draftsAddresseeIsDisplayed() {
    assertTrue(draftsPage.isDraftsAddresseeDisplayed());
  }

  @Then("Drafts subject contains {string}")
  public void draftsSubjectContainsSubject(String subject) {
    assertTrue(draftsPage.getDraftsSubjectText().contains(subject));
  }

  @Then("Sent page contains {string}")
  public void sentPageContainsMailSubject(String subject) {
    assertTrue(sentPage.getMailDetailsText().contains(subject));
  }

  @Then("I see in the Drafts box")
  public void iSeeInTheDraftsBoxMessage(String message) {
    assertEquals(draftsPage.getNoDraftsMessageText(), message);
  }

  @Then("Mail details text contains {string}")
  public void mailDetailsTextContainsSubject(String subject) {
    assertTrue(sentPage.getMailDetailsText().contains(subject));
  }

  @Then("I addressee is displayed in the copy field")
  public void iAddresseeIsDisplayedInTheCopyField() {
    assertTrue(baseMailPage.checkAddresseeVisibility());
  }

  @Then("The last sent mail is displayed")
  public void theLastSentMailIsDisplayed() {
    assertTrue(sentPage.isSentMailDisplayed());
  }
}
