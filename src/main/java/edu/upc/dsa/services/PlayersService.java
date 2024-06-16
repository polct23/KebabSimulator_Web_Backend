package edu.upc.dsa.services;

import edu.upc.dsa.ExceptionMapper.AbilityAlreadyPurchasedException;
import edu.upc.dsa.ExceptionMapper.UserNotFoundException;
import edu.upc.dsa.ExceptionMapper.WrongCredentialsException;
import edu.upc.dsa.models.Ability;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.PlayerList;
import edu.upc.dsa.PlayerListImpl;


import edu.upc.dsa.models.TransferClass;
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
    @Path("/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayer(@PathParam("userName") String userName) {
        Player player = this.pl.getPlayer(userName);
        if(player == null) return Response.status(404).build();
        else return Response.status(200).entity(player).build();
    }
    @POST
    @ApiOperation(value = "create a new Player", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Player.class),
            @ApiResponse(code = 500, message = "Validation error"),
            @ApiResponse(code = 409, message = "Player already exists")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPlayer(Player player) {
        Player newPlayer = new Player(player.getUserName(), player.getPassword(), player.getEmail());
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

    @PUT
    @ApiOperation(value = "update Player's Password", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Player not found")
    })
    @Path("/updateUserPassword")
    public Response updateUserPassword(TransferClass t) {
        Player p = this.pl.updatePassword(t.getUserName(), t.getNewPasword());
        if(p==null) return Response.status(404).build();
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "Player Login", notes = "Verifies user credentials.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login Successful"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(Player player) throws WrongCredentialsException {
        if (pl.authenticateUser(player.getUserName(), player.getPassword())) {
            return Response.status(Response.Status.OK).entity("{\"message\":\"Login Successful\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("{\"message\":\"Unauthorized - Incorrect username or password\"}").build();
        }
    }

    @GET
    @ApiOperation(value = "get Player", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/abilities/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayersAbilities(@PathParam("userName") String userName) throws UserNotFoundException {
        List<Ability> al= this.pl.getPlayersAbility(userName);
        if(al == null) return Response.status(404).build();
        else{
            GenericEntity<List<Ability>> entity = new GenericEntity<List<Ability>>(al){};
            return Response.status(200).entity(entity).build();
        }
    }

    @POST
    @ApiOperation(value = "Buy an ability", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Ability.class),
            @ApiResponse(code = 409, message = "Ability already purchased")
    })
    @Path("/buyAbility/{idAbility}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buyAbility(@PathParam("idAbility") String idAbility, Player player) {
        try {
            // Obtener el nombre del jugador del objeto Player
            String userName = player.getUserName();

            // Llamar al método buyAbilites con el nombre del jugador y el ID de la habilidad
            this.pl.buyAbilites(userName, idAbility);

            // Si se realizó la compra correctamente, devolver una respuesta exitosa (código 200)
            return Response.status(200).build();
        } catch (UserNotFoundException | AbilityAlreadyPurchasedException e) {
            // Si el jugador no se encuentra, devolver un código de estado 409 (conflicto)
            return Response.status(409).build();
        }
    }


}
