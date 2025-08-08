package org.acme;

import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;

public class MealProposeDataFetcher {

    private final MealDatabaseReader mealDatabaseReader;

    @Inject
    public MealProposeDataFetcher(MealDatabaseReader mealDatabaseReader) {
        this.mealDatabaseReader = mealDatabaseReader;
    }

    public List<Dish> getRecentDishes(LocalDate date) {
        return mealDatabaseReader.getRecentDishes(date);
    }

    public LocalDate getLastDateForDish(String dishId) {
        return mealDatabaseReader.getLastDateForDish(dishId);
    }
}

// Placeholder classes and interface for compilation (to be defined later)
