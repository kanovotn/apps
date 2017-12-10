package org.turtle.weightKeeper.rest;

import org.turtle.weightKeeper.ejb.TurtleBean;
import org.turtle.weightKeeper.entities.Turtle;
import org.turtle.weightKeeper.entities.TurtleWeight;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.*;
import java.util.Date;
import java.util.List;

/**
 * Created by kanovotn on 1/25/17.
 */
@Path("/turtle")
public class TurtleResource {

    @Inject
    TurtleBean turtleBean;

    //@Context
    //private ResourceContext resourceContext;

    @Inject
    private RecordsResource recordsResource;

    // Creates new turtle
    @POST
    @Consumes("application/json")
    public Response createTurtle(Turtle turtle, @Context UriInfo uriInfo) {
        Turtle storedTurtle = turtleBean.storeTurtle(turtle);
        if (storedTurtle == null)
            return Response.ok().entity("Turtle " + turtle.getName() + " already exists.\n").type(MediaType.TEXT_HTML_TYPE).build();
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(storedTurtle.getId()));
        return Response.created(uriBuilder.build()).entity("Turtle " + turtle.getName() + " of birth " + turtle.getYearOfBirth() + " created.\n")
                .type(MediaType.TEXT_HTML_TYPE).build();
    }

    // Returns all turtles or turtle filtered by name or year
    @GET
    @Produces("application/json")
    public Response getTurtleByName(@QueryParam("name") String name) {
        List<Turtle> turtles;
        if (name == null) {
            turtles = turtleBean.getAllTurtles();
        } else {
            turtles = turtleBean.getAllTurtlesByName(name);
        }
        if (turtles == null) {
            return Response.status(404).entity("No turtle with name " + name + " found.").type(MediaType.TEXT_HTML_TYPE).build();
        }

        GenericEntity<List<Turtle>> list = new GenericEntity<List<Turtle>>(turtles) {};
        return Response.ok(list).build();
    }

    // Get specific turtle
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response getTurtle(@PathParam("id") int id) {
        Turtle turtle = turtleBean.getTurtleById(id);
        if (turtle == null) {
            return Response.status(404).entity("No turtle with resource id " + id + " found.").type(MediaType.TEXT_HTML_TYPE).build();
        }
        return Response.ok().entity(turtle).build();
    }

    // Get records for specific turtle
    @Path("{id}/records")
    public RecordsResource getTurtleRecords(@PathParam("id") int id) {
        return recordsResource;
        /*RecordsResource recordsResource = resourceContext.getResource(RecordsResource.class);
        recordsResource.setTurtleid(id);
        return recordsResource;*/
    }
}
