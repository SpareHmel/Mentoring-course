package user;

import tests.BaseMailTest;

public class User {

    private static final String LOGIN = BaseMailTest.propertyReader.readPropertyFile("login");
    private static final String PASSWORD = BaseMailTest.propertyReader.readPropertyFile("password");

    public String getLogin() {
        return LOGIN;
    }

    public String getPassword() {
        return PASSWORD;
    }
}
