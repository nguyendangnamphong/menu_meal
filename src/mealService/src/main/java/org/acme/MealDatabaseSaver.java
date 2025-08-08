package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class MealDatabaseSaver implements PanacheRepository<Meal> {

    @Transactional
    public void saveTemporary(MealProposalRequest request, FilteredDishes filteredDishes) {
        // Lưu tạm vào meal_now
        MealNow mealNow = new MealNow();
        mealNow.mealId = request.getMealId();
        mealNow.dishes = filteredDishes.getProteinDishes().stream().map(Dish::getId).toArray(String[]::new);
        mealNow.date = LocalDate.parse(request.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        mealNow.mealTime = request.getMealTime();
        mealNow.persist();

        // Lưu tạm vào parent_eat
        for (String userId : request.getUserIds()) {
            ParentEat parentEat = new ParentEat();
            parentEat.userId = userId;
            parentEat.mealId = request.getMealId();
            parentEat.date = LocalDate.parse(request.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
            parentEat.mealTime = request.getMealTime();
            parentEat.persist();
        }

        // Lưu tạm vào dish_cache
        for (Dish dish : filteredDishes.getProteinDishes()) {
            DishCache dishCache = new DishCache();
            dishCache.dishId = dish.getId();
            dishCache.name = dish.getName();
            dishCache.type = dish.getType();
            dishCache.price = dish.getPrice();
            dishCache.persist();
        }
    }

    @Transactional
    public void savePermanent(MealSaveRequest request) {
        // Lưu vĩnh viễn vào meal
        Meal meal = new Meal();
        meal.id = request.getMealId();
        meal.dishes = new String[0]; // Placeholder, cần lấy từ meal_now hoặc client
        meal.date = LocalDate.parse(request.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        meal.mealTime = request.getMealTime();
        meal.persist();
    }
}
