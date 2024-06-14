package edu.upc.dsa.services;


import edu.upc.dsa.CentralService;
import edu.upc.dsa.CentralServiceImpl;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Player;

import edu.upc.dsa.models.Weapon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/central", description = "Endpoint to Central Service")
@Path("/central")
public class CentralSService {
    private final CentralService centralService;
    public CentralSService(){
        this.centralService = CentralServiceImpl.getInstance();
    }
    @POST
    @ApiOperation(value = "create a new User and Player", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation error"),
            @ApiResponse(code = 409, message = "Validation conflict")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUserAndPlayer(User user) {
        if(user == null) {
            return Response.status(500).build();
        }
        User newUser = new User(user.getUserName(), user.getPassword(), user.getEmail());
        User addedUserAndPlayer = this.centralService.addUserAndPlayer(newUser);
        if(addedUserAndPlayer == null) { return Response.status(409).build(); }
        return Response.status(201).entity(addedUserAndPlayer).build();

    }
    @DELETE
    @Path("/")
    @ApiOperation(value = "Delete User and Player", notes = "Deletes a user and a player from list.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User/Player not found")
    })
    public Response deleteUserAndPlayer(User user) {
        boolean response = this.centralService.deleteUserAndPlayer(user.getUserName(), user.getPassword());
        if(response) { return Response.status(200).build(); }
        else { return Response.status(404).build(); }
    }


}
