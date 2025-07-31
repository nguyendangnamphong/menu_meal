package com.example.dishservice.resource;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DishExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof WebApplicationException webAppException) {
            Response response = webAppException.getResponse();
            int status = response.getStatus();

            if (status == Response.Status.BAD_REQUEST.getStatusCode() ||
                    status == Response.Status.NOT_FOUND.getStatusCode() ||
                    status == Response.Status.CONFLICT.getStatusCode() ||
                    status == Response.Status.SERVICE_UNAVAILABLE.getStatusCode()) {
                return response;
            }
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Internal server error: " + exception.getMessage())
                .build();
    }
}