package edu.upc.dsa.services;

import edu.upc.dsa.UserList;
import edu.upc.dsa.UserListImpl;
import edu.upc.dsa.models.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/users", description = "Endpoint to User Service")
@Path("/users")
public class UsersService {
    private UserList ul;

    public UsersService() {
        this.ul = UserListImpl.getInstance();
        if(ul.size()==0) {
            this.ul.addUser("admin", "admin");
        }
    }

    @GET
    @ApiOperation(value = "get all Users", notes = "---")//Que ponemos en notes?
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "List")
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> users = this.ul.getUsers();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build();
    }

}
