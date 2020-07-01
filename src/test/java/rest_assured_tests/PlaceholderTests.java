package rest_assured_tests;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import io.restassured.RestAssured;
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
    int statusCode = service.getUsersResponse().getStatusCode();
    assertEquals(statusCode, 200);
  }

  @Test
  public void responseHeaderTest() {
    String header = service.getUsersResponse().header("Content-Type");
    assertEquals(header, "application/json; charset=utf-8");
  }

  @Test
  public void responseBodyTest() {
    User[] users = service.getUsersResponse().getBody().as(User[].class);
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
