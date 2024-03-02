package com.example.justeat_project.controller;


import com.example.justeat_project.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

    // autowires the service class for dependency injection
    @Autowired
    private RestaurantService restaurantService;

    // HTTP GET method to retrieve data for respective postcode input
    @GetMapping("/restaurants")

    // extracts value of postcode from the HTTP request URL
    public String getRestaurantByPostcode(@RequestParam String postcode){
        return restaurantService.getRestaurantData(postcode);
    }

}
