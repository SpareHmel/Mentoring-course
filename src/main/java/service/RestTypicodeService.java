package service;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import model.User;

public class RestTypicodeService {

  public Response getUsersResponse() {
    return RestAssured.get("/users");
  }

  public User[] getUsers(Response response) {
    return response.getBody().as(User[].class, ObjectMapperType.GSON);
  }
}
