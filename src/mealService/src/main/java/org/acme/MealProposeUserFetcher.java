package org.acme;

import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MealProposeUserFetcher {

    private final UserServiceClient userServiceClient;

    @Inject
    public MealProposeUserFetcher(@RestClient UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    public Set<String> fetchDislikedDishes(String userId) {
        try {
            // Gọi User Service với timeout 10 giây
            return userServiceClient.getDislikedDishes(userId, 10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch disliked dishes for user " + userId + ": " + e.getMessage());
        }
    }
}
