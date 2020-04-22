import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

  private static final Properties property = new Properties();

  public static String readPropertyFile(String propertyName) {
    try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
      property.load(fis);
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Property file is not found");
    }
    return property.getProperty(propertyName);
  }
}
