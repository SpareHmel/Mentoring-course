package model.service;

import static org.testng.Assert.assertEquals;

import model.user.User;

public class RestTypicodeAssertions {

  private final User[] users;

  public RestTypicodeAssertions(User[] users) {
    this.users = users;
  }

  public void checkUserName(int userIndex, String userName) {
    assertEquals(users[userIndex].getName(), userName);
  }

  public void checkUserCity(int userIndex, String userAddress) {
    assertEquals(users[userIndex].getAddress().getCity(), userAddress);
  }
}
