package com.example.justeat_project.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestaurantService {

    public void getRestaurantData(String postcode){
        String apiUrl = "https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/" + postcode;
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
    }

    public String getCuisines(JsonNode restaurant){
        StringBuilder cuisines = new StringBuilder();
        JsonNode cuisinesArray = restaurant.get("cuisines");
        for (int i = 0; i<cuisinesArray.size(); i++){
            JsonNode cuisine = cuisinesArray.get(i);
            cuisines.append(cuisine.get("name").asText());
        }
        return cuisines.toString().replaceAll(", ,", "");
    }


}
