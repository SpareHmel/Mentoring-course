package rest_assured_tests;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import model.User;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.RestTypicodeService;
import utils.PropertyReader;

public class PlaceholderTests {

  private final String baseURI = new PropertyReader().readPropertyFile("domain");
  private final RestTypicodeService service = new RestTypicodeService();

  @BeforeTest
  public void initTest() {
    RestAssured.baseURI = baseURI;
  }

  @Test
  public void statusCodeTest() {
    Response response = RestAssured.get("/users");
    assertEquals(response.getStatusCode(), 200);
  }

  @Test
  public void responseHeaderTest() {
    Response response = RestAssured.get("/users");
    String contentEncodingHeader = response.header("Content-Type");
    assertEquals(contentEncodingHeader, "application/json; charset=utf-8");
  }

  @Test
  public void responseBodyTest() {
    Response response = RestAssured.get("/users");
    ResponseBody<?> responseBody = response.getBody();
    User[] users = responseBody.as(User[].class);
    assertEquals(users.length, 10);
  }

  @Test
  public void userInfoTest() {
    service.getUsersResponse().then()
        .body("[0].address.city", equalTo("Gwenborough"))
        .and()
        .body("[3].name", equalTo("Patricia Lebsack"));
  }
}
