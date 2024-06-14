package edu.upc.dsa.services;

import edu.upc.dsa.EnemyList;
import edu.upc.dsa.EnemyListImpl;
import edu.upc.dsa.models.Enemy;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/enemies", description = "Endpoint to Enemy Service")
@Path("/enemies")
public class EnemiesService {
    private final EnemyList el;

    public EnemiesService() {
        this.el = EnemyListImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "get all Enemies", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Enemy.class, responseContainer = "List")
    })
    @Path("/getEnemies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnemies() {
        List<Enemy> enemies = el.getEnemies();
        GenericEntity<List<Enemy>> entity = new GenericEntity<List<Enemy>>(enemies) {};
        return Response.status(200).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "create a new Enemy", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Enemy.class),
            @ApiResponse(code = 500, message = "Validation error")
    })
    @Path("/newEnemy")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newEnemy(Enemy enemy) {
        Enemy newEnemy = new Enemy(enemy.getName(),enemy.getMeat(), enemy.getSpeed(),enemy.getDescription());
        if(newEnemy.getIdEnemy()==null || newEnemy.getName()==null || newEnemy.getSpeed() == 0 || newEnemy.getMeat()==0||
                newEnemy.getDescription() ==null) return Response.status(500).entity(newEnemy).build();
        this.el.addEnemy(newEnemy);
        return Response.status(201).entity(newEnemy).build();
    }

    @DELETE
    @ApiOperation(value = "Delete Enemy", notes = "Deletes an enemy from list.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Enemy not found"),
    })
    @Path("/{id}")
    public Response deleteEnemy(@PathParam("id") String id) {
        this.el.deleteEnemy(id);
        return Response.status(200).build();
    }
}