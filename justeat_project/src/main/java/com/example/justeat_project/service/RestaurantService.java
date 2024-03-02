package com.example.justeat_project.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

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
        for (JsonNode cuisine : cuisinesArray) {
            cuisines.append(cuisine.get("name").asText()).append(", ");
        }
        // convert the StringBuilder to a String and format
        return cuisines.toString().replaceAll(", $", " ");
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

    public String filterRestaurantData(String jsonResponse) {

        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder filteredData = new StringBuilder();

        try {

            // extract JSON response into object & extract the array of restaurants
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode restaurants = root.get("restaurants");

            // begin building HTML table
            filteredData.append("<table border =\"1\">");
            filteredData.append("<tr><th>Name</th><th>Cuisines</th><th>Rating</th><th>Address</th></tr>");

            // iterate over each restaurant in JSON array until 10 restaurants are processed
            int count = 0;
            for (JsonNode restaurant : restaurants) {
                if (count >= 10){
                    break;
                }

                // extract data for each restaurant
                String name = restaurant.get("name").asText();
                String cuisines = getCuisines(restaurant);
                double rating = getRating(restaurant);
                String address = getAddress(restaurant);

                // append a new row to the HTML table
                filteredData.append("<tr>");
                filteredData.append("<td>").append(name).append("</td>");
                filteredData.append("<td>").append(cuisines).append("</td>");
                filteredData.append("<td>").append(rating).append("</td>");
                filteredData.append("<td>").append(address).append("</td>");
                filteredData.append("</tr>");


                count++;
            }

            filteredData.append("</table>");
        } catch (IOException e) {

            // print the stack trace if an IO exception occurs during JSON parsing
            e.printStackTrace();
        }

        // return converted HTML formatted restaurant data
        return filteredData.toString();
    }

}
