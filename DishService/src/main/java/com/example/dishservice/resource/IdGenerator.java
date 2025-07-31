package com.example.dishservice.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.Random;
import java.util.logging.Logger;

@ApplicationScoped
public class IdGenerator {
    private static final Logger LOGGER = Logger.getLogger(IdGenerator.class.getName());
    private static final int MIN_ID = 100;
    private static final int MAX_ID = 999;
    private static final int MAX_ATTEMPTS = MAX_ID - MIN_ID + 1;

    public Integer generateUniqueId() {
        Random random = new Random();
        int attempts = 0;

        LOGGER.info("Starting generateUniqueId method");
        while (attempts < MAX_ATTEMPTS) {
            int id = random.nextInt(MAX_ID - MIN_ID + 1) + MIN_ID;
            if (Dish.findById(id) == null) {
                LOGGER.info("Generated unique ID: " + id);
                return id;
            }
            attempts++;
            LOGGER.info("ID " + id + " already exists, retrying (attempt " + attempts + ")");
        }

        LOGGER.severe("No available IDs in range 100-999 after " + MAX_ATTEMPTS + " attempts");
        throw new WebApplicationException("No available IDs in range 100-999", Response.Status.BAD_REQUEST);
    }
}