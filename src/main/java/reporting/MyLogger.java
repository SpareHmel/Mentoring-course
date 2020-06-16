package reporting;


import org.apache.log4j.Logger;

public class MyLogger {

  private static Logger logger = Logger.getLogger(MyLogger.class);

  public static void info(String message) {
    logger.info(message);
  }

  public static void warn(String message) {
    logger.warn(message);
  }
}
