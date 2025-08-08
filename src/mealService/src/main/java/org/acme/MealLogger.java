package org.acme;

import org.jboss.logging.Logger;

public class MealLogger {

    private static final Logger LOG = Logger.getLogger(MealLogger.class);

    public void logUserServiceTimeout() {
        LOG.error("User Service timeout");
    }

    public void logDishServiceTimeout() {
        LOG.error("Dish Service timeout");
    }
}