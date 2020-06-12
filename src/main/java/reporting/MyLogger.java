package reporting;


import org.apache.log4j.Logger;

public class MyLogger {

  //33:54 - Clicking element etd

  public static Logger logger = Logger.getLogger(MyLogger.class);

  public static void info(String message) {
    logger.info(message);
  }

  public static void error(String message) {
    logger.error(message);
  }

  public static void error(String message, Throwable throwable) {
    logger.error(message, throwable);
  }

}
