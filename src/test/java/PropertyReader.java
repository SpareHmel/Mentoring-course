import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

  private final Properties property = new Properties();

  private final String propertyFileName;

  PropertyReader(String propertyFileName) {
    this.propertyFileName = propertyFileName;
  }

  public String readPropertyFile(String propertyName) {
    try (FileInputStream fis = new FileInputStream(propertyFileName)) {
      property.load(fis);
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Property file is not found");
    }
    return property.getProperty(propertyName);
  }
}
