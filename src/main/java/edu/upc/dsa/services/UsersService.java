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
    private final UserList ul;

    public UsersService() {
        this.ul = UserListImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "get all Users", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class, responseContainer = "List")
    })
    @Path("/getUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        List<User> users = this.ul.getUsers();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(200).entity(entity).build();
    }
    @GET
    @ApiOperation(value = "get User", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/getUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User user = this.ul.getUser(id);
        if(user==null) return Response.status(404).build();
        else return Response.status(200).entity(user).build();
    }

    @POST
    @ApiOperation(value = "create a new User", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation error"),
            @ApiResponse(code = 409, message = "Validation conflict")
    })
    @Path("/newUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {
        User newUser = new User(user.getUserName(), user.getPassword(), user.getEmail());
        if(newUser.getUserName()==null || newUser.getPassword()==null) return Response.status(500).entity(newUser).build();
        User us = this.ul.addUser(newUser);
        if(us == null)
            return Response.status(409).build();
        else
            return Response.status(201).entity(newUser).build();
    }


    @PUT
    @ApiOperation(value = "update User Password", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/updateUserPassword")
    public Response updateUserPassword(User user) {
        User u = this.ul.updatePassword(user,user.getPassword());
        if(u==null) return Response.status(404).build();
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "User Login", notes = "Verifies user credentials.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login Successful"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(User user) {
        if (ul.authenticateUser(user.getUserName(), user.getPassword())) {
            return Response.status(Response.Status.OK).entity("{\"message\":\"Login Successful\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"message\":\"Unauthorized - Incorrect username or password\"}").build();
        }
    }
    @DELETE
    @ApiOperation(value = "Delete User", notes = "Deletes a user from list.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 403, message = "Forbidden - Incorrect username or password")
    })
    @Path("/deleteUser/{username}/{password}")
    public Response deleteUser(@PathParam("username") String username, @PathParam("password") String password) {
        if (ul.authenticateUser(username, password)) {
            this.ul.deleteUser(username);
            return Response.status(200).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).entity("{\"message\":\"Forbidden - Incorrect username or password\"}").build();
        }
    }





}
