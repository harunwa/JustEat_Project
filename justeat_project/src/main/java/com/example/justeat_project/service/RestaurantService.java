package com.example.justeat_project.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestaurantService {

    public void getRestaurantData(String postcode){
        String apiUrl = "https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/" + postcode;
        RestTemplate restTemplate = new RestTemplate();

        // send a GET request to the API endpoint
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
    }

    public String getCuisines(JsonNode restaurant){
        StringBuilder cuisines = new StringBuilder();
        JsonNode cuisinesArray = restaurant.get("cuisines");

        // iterate through each cuisine in the array and add to StringBuilder
        for (int i = 0; i<cuisinesArray.size(); i++){
            JsonNode cuisine = cuisinesArray.get(i);
            cuisines.append(cuisine.get("name").asText());
        }
        // convert the StringBuilder to a String and remove commas
        return cuisines.toString().replaceAll(",", "");
    }

    public String getAddress(JsonNode restaurant){
        JsonNode addressNode = restaurant.get("address");
        if (addressNode.has("firstLine") && addressNode.has("postCode")){
            String firstLine = addressNode.get("firstLine").asText();
            String postCode = addressNode.get("postCode").asText();
            return firstLine + ", " + postCode;
        }
        return "";
    }


}
