package org.acme;

import com.sun.net.httpserver.Request;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public class MealSaveExecutor {

    private final MealDatabaseSaver mealDatabaseSaver;
    private final MealDatabaseCleaner mealDatabaseCleaner;

    @Inject
    public MealSaveExecutor(MealDatabaseSaver mealDatabaseSaver, MealDatabaseCleaner mealDatabaseCleaner) {
        this.mealDatabaseSaver = mealDatabaseSaver;
        this.mealDatabaseCleaner = mealDatabaseCleaner;
    }

    @Transactional
    public MealSaveResponse executeSave(Request request) {
        try {
            // Lưu thực đơn vĩnh viễn vào bảng meal
            mealDatabaseSaver.savePermanent(request);

            // Xóa dữ liệu tạm sau khi lưu thành công
            mealDatabaseCleaner.cleanTemporaryData();


        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to save meal: " + e.getMessage());
        }
    }
}
