package org.acme;

import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;

import java.time.LocalDate;

public class MealCronJob {

    private final MealDatabaseCleaner mealDatabaseCleaner;

    @Inject
    public MealCronJob(MealDatabaseCleaner mealDatabaseCleaner) {
        this.mealDatabaseCleaner = mealDatabaseCleaner;
    }

    @Scheduled(cron = "0 0 1 * * ?") // Chạy lúc 1:00 AM mỗi ngày
    public void cleanOldMeals() {
        LocalDate cutoffDate = LocalDate.now().minusDays(7);
        mealDatabaseCleaner.cleanOldMeals(cutoffDate);
    }
}
