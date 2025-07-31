package com.example.dishservice.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.logging.Logger;

@Path("/dishes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreateDishResource {
    private static final Logger LOGGER = Logger.getLogger(CreateDishResource.class.getName());

    @Inject
    IdGenerator idGenerator;

    @POST
    @Transactional
    public Response createDish(Dish dish) {
        LOGGER.info("Starting createDish method with input: " + dish);

        // Validate input
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

        // Check for conflicts
        if (dish.id != null) {
            Dish existingById = Dish.findById(dish.id);
            if (existingById != null) {
                LOGGER.severe("Conflict: ID " + dish.id + " already exists");
                throw new WebApplicationException("ID already exists", Response.Status.CONFLICT);
            }
        }

        Dish existingByName = Dish.find("name", dish.name).firstResult();
        if (existingByName != null) {
            LOGGER.severe("Conflict: Name " + dish.name + " already exists");
            throw new WebApplicationException("Name already exists", Response.Status.CONFLICT);
        }

        // Generate ID if not provided
        if (dish.id == null) {
            try {
                dish.id = idGenerator.generateUniqueId();
            } catch (Exception e) {
                LOGGER.severe("Failed to generate ID: " + e.getMessage());
                throw e;
            }
        }

        // Persist new dish
        try {
            dish.persist();
            LOGGER.info("Created dish successfully: " + dish);
            return Response.status(Response.Status.CREATED).entity(dish).build();
        } catch (Exception e) {
            LOGGER.severe("Database error while creating dish: " + e.getMessage());
            throw new WebApplicationException("Database error: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}