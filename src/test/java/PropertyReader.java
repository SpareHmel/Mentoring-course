import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {

  private static final Properties property = new Properties();
  private static final String login = readPropertyFile("login");
  private static final String password = readPropertyFile("password");
  private static final String mailLink = readPropertyFile("mailLink");
  private static final int implicitlyWaitDefault = Integer.parseInt(Objects
      .requireNonNull(readPropertyFile("implicitlyWaitDefault")));

  public static String readPropertyFile(String propertyName) {
    try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
      property.load(fis);
      switch (propertyName) {
        case "login":
          return property.getProperty("login");
        case "password":
          return property.getProperty("password");
        case "mailLink":
          return property.getProperty("mailLink");
        case "implicitlyWaitDefault":
          return property.getProperty("implicitlyWaitDefault");
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Property file is not found");
    }
    return null;
  }

  public static String getLogin() {
    return login;
  }

  public static String getPassword() {
    return password;
  }

  public static String getMailLink() {
    return mailLink;
  }

  public static int getImplicitlyWaitDefault() {
    return implicitlyWaitDefault;
  }
}
