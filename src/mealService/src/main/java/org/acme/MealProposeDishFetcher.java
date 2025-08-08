package org.acme;

import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MealProposeDishFetcher {

    private final DishServiceClient dishServiceClient;

    @Inject
    public MealProposeDishFetcher(@RestClient DishServiceClient dishServiceClient) {
        this.dishServiceClient = dishServiceClient;
    }

    public List<Dish> fetchDishes() {
        try {
            // Gọi Dish Service với timeout 10 giây
            return dishServiceClient.getDishes(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch dishes: " + e.getMessage());
        }
    }
}
