package model;


import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Geo {

    private String lat;
    private String lng;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
