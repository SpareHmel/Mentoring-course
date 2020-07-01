package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

  private final Properties properties = new Properties();

  public String readPropertyFile(String propertyName) {
    try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
      properties.load(fis);
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Property file is not found");
    }
    return properties.getProperty(propertyName);
  }
}
