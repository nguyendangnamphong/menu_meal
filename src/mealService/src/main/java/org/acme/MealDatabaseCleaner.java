package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MealDatabaseCleaner implements PanacheRepository<MealNow> {

    @Transactional
    public void cleanTemporaryData() {
        // Xóa dữ liệu tạm trong meal_now
        deleteAllFromMealNow();

        // Xóa dữ liệu tạm trong parent_eat
        deleteAllFromParentEat();

        // Xóa dữ liệu tạm trong dish_cache
        deleteAllFromDishCache();
    }

    private void deleteAllFromMealNow() {
        MealNow.deleteAll();
    }

    private void deleteAllFromParentEat() {
        ParentEat.deleteAll();
    }

    private void deleteAllFromDishCache() {
        DishCache.deleteAll();
    }
}

