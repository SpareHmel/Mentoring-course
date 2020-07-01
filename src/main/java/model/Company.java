package model;


import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Company {

    private String name;
    private String catchPhrase;
    private String bs;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
