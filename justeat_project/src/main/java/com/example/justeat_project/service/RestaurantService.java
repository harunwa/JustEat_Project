package com.example.justeat_project.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestaurantService {

    public String getRestaurantData(String postcode){
        String apiUrl = "https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/" + postcode;
        RestTemplate restTemplate = new RestTemplate();

        // send a GET request to the API endpoint
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        // assign the JSON response
        String filteredData = filterRestaurantData(jsonResponse);

        return filteredData;

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

        // check if the addressNode contains both "firstLine" & "postCode" fields
        if (addressNode.has("firstLine") && addressNode.has("postalCode")){
            String firstLine = addressNode.get("firstLine").asText();
            String postalCode = addressNode.get("postalCode").asText();

            // format the address and return it
            return firstLine + ", " + postalCode;
        }

        // return an empty string if address fields are not available
        return "";
    }

    public double getRating(JsonNode restaurant){
        JsonNode ratingNode = restaurant.get("rating");

        // check if the ratingNode contains both "starRating" field
        if (ratingNode.has("starRating")){
            return ratingNode.get("starRating").asDouble();

        }

        // return 0.0 if rating field is not available
        return 0.0;
    }

    public String filterRestaurantData(String jsonResponse){

        return jsonResponse;
    }



}
