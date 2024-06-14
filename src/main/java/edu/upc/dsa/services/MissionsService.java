package edu.upc.dsa.services;

import edu.upc.dsa.AbilitiesList;
import edu.upc.dsa.AbilitiesListImpl;
import edu.upc.dsa.MissionList;
import edu.upc.dsa.MissionListImpl;
import edu.upc.dsa.models.Ability;
import edu.upc.dsa.models.Mission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/missions", description = "Endpoint to Missions Service")
@Path("/missions")
public class MissionsService {
    private final MissionList ml;

    public MissionsService(MissionList ml) {
        this.ml = MissionListImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "get all Missions", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Mission.class, responseContainer = "List")
    })
    @Path("/getMissions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAbilities() {
        List<Mission> missions = ml.getMissions();
        GenericEntity<List<Mission>> entity = new GenericEntity<List<Mission>>(missions) {};
        return Response.status(200).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "create a new Mission", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Mission.class),
            @ApiResponse(code = 500, message = "Validation error")
    })
    @Path("/newAbility")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newMission(Mission mission) {
        Mission newMission = new Mission(mission.getReward(), mission.getDescription());
        if(newMission.getIdMission()==null || newMission.getDescription()==null) return Response.status(500).entity(newMission).build();
        this.ml.addMission(newMission);
        return Response.status(201).entity(newMission).build();
    }

    @DELETE
    @ApiOperation(value = "Delete Mission", notes = "Deletes a mission from list.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Missin not found"),
    })
    @Path("/{id}")
    public Response deleteMission(@PathParam("id") String id) {
        this.ml.deleteMission(id);
        return Response.status(200).build();
    }
}
