package factory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail {

  static final String addresseeText = "hmel25@bk.ru";
  static final String subjectText = "Elimination details";
  static final String bodyText = "4815162342";

  private String addressee;
  private String subject;
  private String body;
}
