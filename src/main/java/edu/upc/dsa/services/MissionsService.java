package edu.upc.dsa.services;

import edu.upc.dsa.MissionList;
import edu.upc.dsa.MissionListImpl;
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

    // Constructor corregido para aceptar inyección de dependencia
    public MissionsService() {
        this.ml = MissionListImpl.getInstance();
    }
    @GET
    @ApiOperation(value = "get all Missions", notes = "Retrieve all missions available")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Mission.class, responseContainer = "List")
    })
    @Path("/getMissions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMissions() {
        List<Mission> missions = ml.getMissions();
        if (missions == null || missions.isEmpty()) {
            return Response.status(404).entity("No missions found").build();
        }
        GenericEntity<List<Mission>> entity = new GenericEntity<List<Mission>>(missions) {};
        return Response.status(200).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "create a new Mission", notes = "Create a new mission and add it to the list")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Mission created successfully", response = Mission.class),
            @ApiResponse(code = 400, message = "Validation error")
    })
    @Path("/newMission")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newMission(Mission mission) {
        // Validar que los campos requeridos no sean nulos o inválidos
        if (mission == null || mission.getDescription() == null || mission.getDescription().isEmpty()) {
            return Response.status(400).entity("Mission description is required").build();
        }

        Mission newMission = new Mission(mission.getDescription(), mission.getReward(), mission.getIdMission());
        this.ml.addMission(newMission);
        return Response.status(201).entity(newMission).build();
    }

    @DELETE
    @ApiOperation(value = "Delete Mission", notes = "Delete a mission by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Mission deleted successfully"),
            @ApiResponse(code = 404, message = "Mission not found")
    })
    @Path("/{id}")
    public Response deleteMission(@PathParam("id") String id) {
        this.ml.deleteMission(id);
        return Response.status(200).build();
    }
}
