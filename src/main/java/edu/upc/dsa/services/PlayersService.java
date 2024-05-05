package edu.upc.dsa.services;

import edu.upc.dsa.models.Player;
import edu.upc.dsa.PlayerList;
import edu.upc.dsa.PlayerListImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/players", description = "Endpoint to Player Service")
@Path("/players")
public class PlayersService {
    private final PlayerList pl;

    public PlayersService() {
        this.pl = PlayerListImpl.getInstance();
    }
    @GET
    @ApiOperation(value = "get all Players", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Player.class, responseContainer = "List")
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayers() {
        List<Player> players = pl.getPlayers();
        GenericEntity<List<Player>> entity = new GenericEntity<List<Player>>(players){};
        return Response.status(200).entity(entity).build();
    }
    @GET
    @ApiOperation(value = "get Player", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayer(@PathParam("id") String idPlayer) {
        Player player = this.pl.getPlayer(idPlayer);
        if(player == null) return Response.status(404).build();
        else return Response.status(200).entity(player).build();
    }
    @POST
    @ApiOperation(value = "create a new Player", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Player.class),
            @ApiResponse(code = 500, message = "Validation error"),
            @ApiResponse(code = 409, message = "Validation conflict")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPlayer(Player player) {
        Player newPlayer = new Player(player.getIdPlayer());
        if(newPlayer.getIdPlayer() == null) return Response.status(500).build();
        Player pl = this.pl.addPlayer(newPlayer);
        if(pl == null) return Response.status(409).build();
        else return Response.status(201).entity(newPlayer).build();
    }
    @DELETE
    @ApiOperation(value = "Delete Player", notes = "Deletes a player from list.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    public Response deletePlayer(@PathParam("id") String idPlayer) {
        if(this.pl.getPlayer(idPlayer) == null) return Response.status(404).build();
        else{
            this.pl.deletePlayer(idPlayer);
            return Response.status(200).build();
        }
    }
}
