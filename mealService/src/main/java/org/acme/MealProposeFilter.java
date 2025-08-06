package org.acme;

import jakarta.inject.Inject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MealProposeFilter {

    private final MealProposeUserFetcher mealProposeUserFetcher;
    private final MealProposeDishFetcher mealProposeDishFetcher;
    private final MealProposeDataFetcher mealProposeDataFetcher;

    @Inject
    public MealProposeFilter(MealProposeUserFetcher mealProposeUserFetcher,
                             MealProposeDishFetcher mealProposeDishFetcher,
                             MealProposeDataFetcher mealProposeDataFetcher) {
        this.mealProposeUserFetcher = mealProposeUserFetcher;
        this.mealProposeDishFetcher = mealProposeDishFetcher;
        this.mealProposeDataFetcher = mealProposeDataFetcher;
    }

    public FilteredDishes filterDishes(MealProposalRequest request) {
        // Lấy danh sách món không thích từ tất cả userIds
        Set<String> dislikedDishes = new HashSet<>();
        for (String userId : request.getUserIds()) {
            Set<String> userDisliked = mealProposeUserFetcher.fetchDislikedDishes(userId);
            dislikedDishes.addAll(userDisliked);
        }

        // Lấy danh sách món từ Dish Service
        List<Dish> allDishes = mealProposeDishFetcher.fetchDishes();

        // Lấy ngày hiện tại từ request
        LocalDate requestDate = LocalDate.parse(request.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);

        // Lọc món ăn
        List<Dish> filteredDishes = new ArrayList<>();
        for (Dish dish : allDishes) {
            boolean isDisliked = dislikedDishes.contains(dish.getId());
            LocalDate lastDate = mealProposeDataFetcher.getLastDateForDish(dish.getId());
            boolean violatesRepetition = lastDate != null &&
                    requestDate.isBefore(lastDate.plusDays(dish.getRepetitionDays()));

            if (!isDisliked && !violatesRepetition) {
                filteredDishes.add(dish);
            }
        }

        // Chia thành danh sách đạm và rau
        List<Dish> proteinDishes = new ArrayList<>();
        List<Dish> veggieDishes = new ArrayList<>();
        for (Dish dish : filteredDishes) {
            if ("đạm".equalsIgnoreCase(dish.getType())) {
                proteinDishes.add(dish);
            } else if ("rau".equalsIgnoreCase(dish.getType())) {
                veggieDishes.add(dish);
            }
        }

        return new FilteredDishes(proteinDishes, veggieDishes);
    }
}
