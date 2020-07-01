package model;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class User {

  private Integer id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
