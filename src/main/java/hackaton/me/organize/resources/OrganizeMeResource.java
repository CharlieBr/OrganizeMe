package hackaton.me.organize.resources;

import hackaton.me.organize.clients.PostgreSQLClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;

@Path("/organize-me")
public class OrganizeMeResource {
    private final PostgreSQLClient postgreSQLClient;

    public OrganizeMeResource(final PostgreSQLClient client) {
        this.postgreSQLClient = client;
    }

    @POST
    @Timed
    @Path("/schedule")
    @Consumes("application/json")
    public Response estimateSchedule() {
        return Response.ok().build();
    }

    @POST
    @Timed
    @Path("/add-participant")
    public Response addUser(@QueryParam("name") final String name) {
        this.postgreSQLClient.addParticipant(name);
        return Response.ok().build();
    }

    @GET
    @Timed
    @Path("/participants")
    @Produces("application/json")
    public Response getUsers() {
        return Response.ok().entity(this.postgreSQLClient.fetchParticipants()).build();
    }
}
