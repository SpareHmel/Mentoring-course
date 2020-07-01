package service;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestTypicodeService {

  public Response getUsersResponse() {
    return RestAssured.get("/users");
  }
}
