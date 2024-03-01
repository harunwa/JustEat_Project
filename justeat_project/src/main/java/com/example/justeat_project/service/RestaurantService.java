package com.example.justeat_project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestaurantService {

    public void getRestaurantData(String postcode){
        String apiUrl = "https://uk.api.just-eat.io/discovery/uk/restaurants/enriched/bypostcode/" + postcode;
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
    }


}
