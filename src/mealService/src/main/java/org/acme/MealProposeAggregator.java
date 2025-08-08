package org.acme;

import jakarta.inject.Inject;

public class MealProposeAggregator {

    private final MealProposeFilter mealProposeFilter;
    private final MealDatabaseSaver mealDatabaseSaver;

    @Inject
    public MealProposeAggregator(MealProposeFilter mealProposeFilter, MealDatabaseSaver mealDatabaseSaver) {
        this.mealProposeFilter = mealProposeFilter;
        this.mealDatabaseSaver = mealDatabaseSaver;
    }

    public MealProposalResponse aggregateProposal(MealProposalRequest request) {
        // Lấy danh sách món đã lọc từ MealProposeFilter
        var filteredDishes = mealProposeFilter.filterDishes(request);

        // Thêm món mặc định nếu danh sách rỗng
        if (filteredDishes.isEmpty()) {
            var defaultDish = new Dish();
            defaultDish.setId("999");
            defaultDish.setName("cơm");
            defaultDish.setPrice(0.0);
            filteredDishes.add(defaultDish);
        }

        // Tạo response
        var response = new MealProposalResponse();
        response.setDishes(filteredDishes);
        response.setStatus("success");

        // Lưu tạm vào database qua MealDatabaseSaver
        mealDatabaseSaver.saveTemporary(response);

        return response;
    }
}

