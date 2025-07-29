package meal.app;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/users/{id}/role")
public class GetUserRoleResource {

    private static final Logger LOGGER = Logger.getLogger(GetUserRoleResource.class.getName());

    @Inject
    GetUserRoleService getUserRoleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserRole(@PathParam("id") Integer id) {
        try {
            String role = getUserRoleService.getUserRole(id);
            LOGGER.info("User role retrieved successfully in GetUserRoleResource.getUserRole for ID: " + id);
            return Response.status(Response.Status.OK)
                    .entity(new User(id, null, role, null))
                    .build();
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Error in GetUserRoleResource.getUserRole for ID: " + id + ": " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User with ID " + id + " not found")
                    .build();
        } catch (Exception e) {
            LOGGER.severe("Error in GetUserRoleResource.getUserRole for ID: " + id + ": " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to retrieve user role: " + e.getMessage())
                    .build();
        }
    }
}