package com.example.dishservice.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.logging.Logger;

@ApplicationScoped
public class DishOperationManager {
    private static final Logger LOGGER = Logger.getLogger(DishOperationManager.class.getName());

    @Inject
    IdGenerator idGenerator;

    public void validateDishInput(Dish dish, boolean isUpdateByName) {
        LOGGER.info("Starting validateDishInput method with input: " + dish + ", isUpdateByName: " + isUpdateByName);

        if (dish.name == null || dish.name.isEmpty() || dish.name.length() > 255) {
            LOGGER.severe("Invalid name: " + dish.name);
            throw new WebApplicationException("Name must not be null, empty, or exceed 255 characters", Response.Status.BAD_REQUEST);
        }
        if (dish.dish_type == null || (!dish.dish_type.equals("đạm") && !dish.dish_type.equals("rau"))) {
            LOGGER.severe("Invalid dish_type: " + dish.dish_type);
            throw new WebApplicationException("dish_type must be 'đạm' or 'rau'", Response.Status.BAD_REQUEST);
        }
        if (dish.time_not_repeat == null || dish.time_not_repeat.compareTo(BigDecimal.ZERO) < 0 || dish.time_not_repeat.compareTo(new BigDecimal("7.00")) > 0) {
            LOGGER.severe("Invalid time_not_repeat: " + dish.time_not_repeat);
            throw new WebApplicationException("time_not_repeat must be between 0 and 7", Response.Status.BAD_REQUEST);
        }
        if (dish.price == null || dish.price.compareTo(BigDecimal.ZERO) < 0) {
            LOGGER.severe("Invalid price: " + dish.price);
            throw new WebApplicationException("price must be non-negative", Response.Status.BAD_REQUEST);
        }
        if (isUpdateByName && dish.id != null) {
            LOGGER.severe("ID provided in input: " + dish.id + ", but update by name should not include ID");
            throw new WebApplicationException("ID must not be provided for update by name", Response.Status.BAD_REQUEST);
        }
    }

    public void checkConflicts(Dish dish, Integer id, boolean isUpdateById) {
        LOGGER.info("Starting checkConflicts method for dish: " + dish + ", id: " + id + ", isUpdateById: " + isUpdateById);

        if (id != null) {
            Dish existingById = Dish.findById(id);
            if (isUpdateById && existingById == null) {
                LOGGER.severe("Dish not found for ID: " + id);
                throw new WebApplicationException("Dish not found", Response.Status.NOT_FOUND);
            }
            if (!isUpdateById && existingById != null) {
                LOGGER.severe("Conflict: ID " + id + " already exists");
                throw new WebApplicationException("ID already exists", Response.Status.CONFLICT);
            }
        }

        Dish existingByName = Dish.find("name", dish.name).firstResult();
        if (existingByName != null && (id == null || !existingByName.id.equals(id))) {
            LOGGER.severe("Conflict: Name " + dish.name + " already exists with different ID");
            throw new WebApplicationException("Name already exists with different ID", Response.Status.CONFLICT);
        }
    }

    public Integer generateIdIfNeeded(Dish dish) {
        LOGGER.info("Starting generateIdIfNeeded method for dish: " + dish);

        if (dish.id == null) {
            try {
                Integer newId = idGenerator.generateUniqueId();
                LOGGER.info("Generated new ID: " + newId);
                return newId;
            } catch (Exception e) {
                LOGGER.severe("Failed to generate ID: " + e.getMessage());
                throw e;
            }
        }
        return dish.id;
    }
}