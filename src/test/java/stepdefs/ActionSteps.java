package stepdefs;

import cucumber.api.java.en.When;
import factory.Mail;
import factory.MailFactory;

public class ActionSteps extends BaseStep {

  @When("I start writing letter")
  public void iStartWritingLetter() {
    baseMailPage.startWritingLetter();
  }

  @When("I create mail with {string} using a template")
  public void iCreateMailWithTheDesiredTemplate(String template) {
    Mail mail;
    switch (template) {
      case ("default fields"):
        mail = MailFactory.simpleMail();
        baseMailPage.fillInDesiredFields(mail);
        break;
      case ("missing subject"):
        mail = MailFactory.mailWithoutSubject();
        baseMailPage.fillInDesiredFields(mail);
        break;
      case ("custom body"):
        mail = MailFactory.mailWithCustomBody("Important text");
        baseMailPage.fillInDesiredFields(mail);
        break;
      default:
        System.out.println("incorrect template for the mail");
        break;
    }
  }

  @When("I save mail as draft")
  public void iSaveMailAsDraft() {
    baseMailPage.saveMailAsDraft();
  }

  @When("I close the message window")
  public void iCloseTheMessageWindow() {
    baseMailPage.closeMessageWindow();
  }

  @When("I open Drafts page")
  public void iOpenDraftsPage() {
    draftsPage.get();
  }

  @When("I select draft and send mail")
  public void iSelectDraftAndSendMail() {
    draftsPage.selectDraftAndSendMail();
  }

  @When("I open Sent page")
  public void iOpenSentPage() {
    sentPage.get();
  }

  @When("I save mail as template")
  public void iSaveMailAsTemplate() {
    baseMailPage.saveMailAsTemplate();
  }

  @When("I open Template page")
  public void iOpenTemplatePage() {
    templatePage.get();
  }

  @When("I open template")
  public void iOpenTemplate() {
    templatePage.openTemplate();
  }

  @When("I send mail")
  public void iSendMail() {
    templatePage.sendMail();
  }

  @When("I delete saved draft")
  public void iDeleteSavedDraft() {
    draftsPage.deleteDraft();
  }

  @When("I move addressee to the copy field")
  public void iMoveAddresseeToTheCopyField() {
    baseMailPage.moveAddresseeToCopy();
  }

  @When("I scroll to the last sent mail")
  public void iScrollToTheLastSentMail() {
    sentPage.scrollToLastSentMail();
  }
}
