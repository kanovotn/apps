package org.turtle.weightKeeper.rest;

import org.turtle.weightKeeper.ejb.TurtleBean;
import org.turtle.weightKeeper.entities.Turtle;
import org.turtle.weightKeeper.entities.TurtleWeight;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Date;
import java.util.List;

/**
 * Created by kanovotn on 1/25/17.
 */
@Path("/")
public class TurtleResource {

    @Inject
    TurtleBean turtleBean;

    // Creates new turtle
    @POST
    @Path("turtles")
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

    // Returns all turtles or turtle filtered by name
    @GET
    @Path("turtles")
    @Produces("application/json")
    public Response getTurtleByName(@QueryParam("name") String name) {
        List<Turtle> turtles;
        if (name == null) {
            turtles = turtleBean.getAllTurtles();
        } else {
            turtles = turtleBean.getAllTurtlesByName(name);
        }
        GenericEntity<List<Turtle>> list = new GenericEntity<List<Turtle>>(turtles) {};
        return Response.ok(list).build();
    }

    // Get specific turtle
    @GET
    @Path("turtles/{id}")
    @Produces("application/json")
    public Response getTurtle(@PathParam("id") int id) {
        Turtle turtle = turtleBean.getTurtleById(id);
        if (turtle == null) {
            return Response.status(404).entity("No turtle with resource id " + id + " found.").type(MediaType.TEXT_HTML_TYPE).build();
        }
        return Response.ok().entity(turtle).build();
    }


    /*@GET
    @Path("{name}")
    public String get(@PathParam("name") String name) {
        int date = turtleBean.getYear(name);
        return name + ": " + date;
    }

    @POST
    @Path("record/{name}/{date}/{weight}")
    public Response recordWeight(@PathParam("name") String name, @PathParam("date") Date date, @PathParam("weight") int weight) {
        if (turtleBean.recordWeight(name, date, weight) == null)
            return Response.ok().entity("Couldn't record weight for turtle " + name + ".\n").type(MediaType.TEXT_HTML_TYPE).build();
        return Response.ok().entity("Weight of " + weight + " grams of turtle " + name + " recorded.\n").type(MediaType.TEXT_HTML_TYPE).build();
    }

    @GET
    @Path("records")
    @Produces("application/json")
    public Response getRecordsByName(@QueryParam("name") String name) {
        List<TurtleWeight> turtleWeights = turtleBean.getAllRecordsByName(name);
        GenericEntity<List<TurtleWeight>> list = new GenericEntity<List<TurtleWeight>>(turtleWeights) {};
        return Response.ok(list).build();
    }

    @GET
    @Path("records/all")
    @Produces("application/json")
    public Response getRecordsByName() {
        List<TurtleWeight> turtleWeights = turtleBean.getAllRecords();
        GenericEntity<List<TurtleWeight>> list = new GenericEntity<List<TurtleWeight>>(turtleWeights) {};
        return Response.ok(list).build();   
    }*/
}
