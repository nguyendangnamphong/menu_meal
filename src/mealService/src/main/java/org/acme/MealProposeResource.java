package org.acme;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.inject.Inject;

@Path("/meals/propose")
public class MealProposeResource {

    private final MealProposeAggregator mealProposeAggregator;

    @Inject
    public MealProposeResource(MealProposeAggregator mealProposeAggregator) {
        this.mealProposeAggregator = mealProposeAggregator;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response proposeMeal(MealProposalRequest request) {
        try {
            MealProposalResponse response = mealProposeAggregator.aggregateProposal(request);
            return Response.status(Response.Status.CREATED).entity(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error processing proposal: " + e.getMessage())
                    .build();
        }
    }
}

