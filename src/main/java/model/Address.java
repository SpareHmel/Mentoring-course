package model;


import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
