package factory;

import static factory.Mail.addresseeText;
import static factory.Mail.bodyText;
import static factory.Mail.subjectText;

public class MailFactory {

  public static Mail createSimpleMail() {
    Mail mail = new Mail();
    mail.setAddressee(addresseeText);
    mail.setSubject(subjectText);
    mail.setBody(bodyText);
    return mail;
  }

  public static Mail createMailWithoutSubject() {
    Mail mail = createSimpleMail();
    mail.setSubject(null);
    return mail;
  }

  public static Mail mailWithCustomBody(String body) {
    Mail mail = createSimpleMail();
    mail.setBody(body);
    return mail;
  }
}
