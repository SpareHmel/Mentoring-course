package rest_assured_tests;

import static org.testng.Assert.assertEquals;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import model.service.RestTypicodeAssertions;
import model.service.RestTypicodeService;
import model.user.User;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.PropertyReader;

public class PlaceholderTests {

  @BeforeTest
  public void initTest() {
    RestAssured.baseURI = new PropertyReader().readPropertyFile("domain");
  }

  @Test
  public void statusCodeTest() {
    Response response = RestAssured.when().get("/users").andReturn();
    assertEquals(response.getStatusCode(), 200);
  }

  @Test
  public void responseHeaderTest() {
    Response response = RestAssured.when().get("/users").andReturn();
    String contentEncodingHeader = response.header("Content-Type");
    assertEquals(contentEncodingHeader, "application/json; charset=utf-8");
  }

  @Test
  public void responseBodyTest() {
    Response response = RestAssured.when().get("/users").andReturn();
    ResponseBody<?> responseBody = response.getBody();
    User[] users = responseBody.as(User[].class);
    assertEquals(users.length, 10);
  }

  @Test
  public void userInfoTest() {
    User[] users = new RestTypicodeService().getUsers();

    RestTypicodeAssertions result = new RestTypicodeAssertions(users);
    result.checkUserCity(0, "Gwenborough");
    result.checkUserName(3, "Patricia Lebsack");
  }
}
