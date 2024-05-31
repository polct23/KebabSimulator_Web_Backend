package edu.upc.dsa.services;

import edu.upc.dsa.models.AbuseReport;
import edu.upc.dsa.models.Weapon;
import io.swagger.annotations.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
@Api(value = "/abuse", description = "Endpoint to AbuseReport Service")
@Path("/abuse")
public class AbuseReportService {

    @POST
    @ApiOperation(value = "Report an abuse", notes = "Report an abuse by sending a JSON object")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "abuseReport", value = "Abuse Report in JSON format", required = true, dataType = "edu.upc.dsa.models.AbuseReport", paramType = "body")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response reportAbuse(AbuseReport abuseReport) {
        System.out.println("adadadadad"+abuseReport.getReporter());
        return Response.status(Response.Status.OK).build();
    }
}*/
@Api(value = "/abuse", description = "Endpoint to AbuseReport Service")
@Path("/abuse")
public class AbuseReportService {

    @POST
    @ApiOperation(value = "create a new AbuseReport", notes = "---")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = AbuseReport.class),
            @ApiResponse(code = 500, message = "Validation error")
    })
    @Path("/reportAbuse")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newAbuseReport(AbuseReport abuseReport) {
        AbuseReport newAbuseReport = new AbuseReport(abuseReport.getReporter(),abuseReport.getDescription(),abuseReport.getDate());
        System.out.println("New abuse report: "+newAbuseReport + " from: "+newAbuseReport.getReporter() + " with description: "+newAbuseReport.getDescription());
        return Response.status(201).entity(newAbuseReport).build();

    }
}
