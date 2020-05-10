package user;

import tests.BaseMailTest;
import utils.PropertyReader;

public class User {

//    private static PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");
    private static final String LOGIN = BaseMailTest.propertyReader.readPropertyFile("login");
    private static final String PASSWORD = BaseMailTest.propertyReader.readPropertyFile("password");

    public String getLogin() {
        return LOGIN;
    }

    public String getPassword() {
        return PASSWORD;
    }
}
