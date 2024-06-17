package edu.upc.dsa.services;

import edu.upc.dsa.AbilitiesList;
import edu.upc.dsa.AbilitiesListImpl;
import edu.upc.dsa.models.Ability;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/abilities", description = "Endpoint to Ability Service")
@Path("/abilities")
public class AbilitiesService {
    private final AbilitiesList wl;

    public AbilitiesService() {
        this.wl = AbilitiesListImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "get all Abilities", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Ability.class, responseContainer = "List")
    })
    @Path("/getAbilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAbilities() {
        List<Ability> abilities = wl.getAbilities();
        GenericEntity<List<Ability>> entity = new GenericEntity<List<Ability>>(abilities) {};
        return Response.status(200).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "create a new Ability", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Ability.class),
            @ApiResponse(code = 500, message = "Validation error")
    })
    @Path("/newAbility")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newAbility(Ability ability) {
        Ability newAbility = new Ability(ability.getAbilityName(), ability.getDescription(), ability.getValue(), ability.getPrice(), ability.getImageURL());
        if(newAbility.getIdAbility()==null || newAbility.getAbilityName()==null) return Response.status(500).entity(newAbility).build();
        this.wl.addAbility(newAbility);
        return Response.status(201).entity(newAbility).build();
    }

    @DELETE
    @ApiOperation(value = "Delete Ability", notes = "Deletes a ablity from list.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Ability not found"),
    })
    @Path("/{id}")
    public Response deleteAbility(@PathParam("id") String id) {
        this.wl.deleteAbility(id);
        return Response.status(200).build();
    }
}
