package user;

import utils.PropertyReader;

public class User {

    private final String login;
    private final String password;

    public User(String login, String password) {
        PropertyReader propertyReader = new PropertyReader("src/test/resources/config.properties");
        this.login = propertyReader.readPropertyFile(login);
        this.password = propertyReader.readPropertyFile(password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
