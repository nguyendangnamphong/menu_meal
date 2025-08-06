package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class MealDatabaseReader implements PanacheRepository<Dish> {

    public List<Dish> getRecentDishes(LocalDate date) {
        return find("SELECT d FROM Dish d JOIN Meal m WHERE m.date = ?1", date).list();
    }

    public LocalDate getLastDateForDish(String dishId) {
        Dish dish = find("SELECT d FROM Dish d JOIN Meal m WHERE d.id = ?1 ORDER BY m.date DESC", dishId)
                .firstResult();
        return dish != null ? LocalDate.now() : null; // Placeholder logic, adjust based on actual join
    }
}

