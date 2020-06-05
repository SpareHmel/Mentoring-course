package enums;

public enum WebsiteInfo {

  MAIL_LINK("https://mail.ru"),
  TITLE("Входящие - Почта Mail.ru"),
  MAIL_SUBJECT("Elimination details"),
  EMPTY_DRAFTS_MESSAGE("У вас нет незаконченных\nили неотправленных писем");

  final String text;

  WebsiteInfo(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
