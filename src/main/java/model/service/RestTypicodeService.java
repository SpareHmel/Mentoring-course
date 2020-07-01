package model.service;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import model.user.User;
import utils.PropertyReader;

public class RestTypicodeService {

  private final String baseURI = new PropertyReader().readPropertyFile("domain");

  public User[] getUsers() {
    return new Gson().fromJson(RestAssured.get(baseURI+ "/users").getBody().asString(),
        User[].class);
  }
}
