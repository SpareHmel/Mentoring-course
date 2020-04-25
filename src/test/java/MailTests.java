import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MailTests extends BaseMail {

  @Test
  public void mailTest() {
    final String addressee = "hmel25@bk.ru";
    final String subject = "Elimination details";
    final String body = "4815162342";
    homePage.signIn(login, password);
    //Assert, that the login is successful.
    isTitlePresentedWithText("Входящие - Почта Mail.ru");
    //Create a new mail (fill addressee, subject and body fields).
    baseMailPage.startWritingLetter();
    baseMailPage.fillAddresseeField(addressee);
    baseMailPage.fillSubjectField(subject);
    baseMailPage.fillBodyField(body);
    //Save the mail as a draft.
    baseMailPage.saveMailAsDraft();
    baseMailPage.closeMessageWindow();
    //Verify the draft content (addressee, subject and body – should be the same as in 3).
    draftsPage.openPage();
    assertTrue(draftsPage.getDraftsAddressee().isDisplayed());
    assertEquals(draftsPage.getDraftsSubject().getText(), subject);
    //Send the mail.
    draftsPage.selectDraftAndSendMail();
    //Verify, that the mail is in ‘Sent’ folder
    sentPage.openPage();
    assertTrue(sentPage.getMail().getText().contains(subject));
    //Verify, that the mail disappeared from ‘Drafts’ folder.
    draftsPage.openPage();
    assertEquals(draftsPage.getNoDraftsMessage(), "У вас нет незаконченных\nили неотправленных писем");
    //Log off.
    baseMailPage.logOff();
  }
}
