package org.turtle.weightKeeper.rest;

import org.turtle.weightKeeper.ejb.TurtleBean;
import org.turtle.weightKeeper.entities.Turtle;
import org.turtle.weightKeeper.entities.TurtleWeight;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by kanovotn on 1/25/17.
 */

@Path("turtle")
public class TurtleResource {

    @Inject
    TurtleBean turtleBean;

    @POST
    @Path("create/{name}/{year}")
    public Response createTurtle(@PathParam("name") String name, @PathParam("year") int year) {
        Turtle newTurtle = new Turtle(name, year);
        if (turtleBean.storeTurtle(newTurtle) == null)
            return Response.ok().entity("Turtle " + name + " already exists.\n").type(MediaType.TEXT_HTML_TYPE).build();
        return Response.ok().entity("Turtle " + name + " of birth " + year + " created.\n").type(MediaType.TEXT_HTML_TYPE).build();
    }

    @GET
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
    @Path("records/{name}")
    @Produces("application/json")
    public Response getRecordsByName(@PathParam("name") String name) {
        List<TurtleWeight> turtleWeights = turtleBean.getAllRecordsByName(name);
        GenericEntity<List<TurtleWeight>> list = new GenericEntity<List<TurtleWeight>>(turtleWeights) {};
        return Response.ok(list).build();
    }

    @GET
    @Path("allRecords")
    @Produces("application/json")
    public Response getRecordsByName() {
        List<TurtleWeight> turtleWeights = turtleBean.getAllRecords();
        GenericEntity<List<TurtleWeight>> list = new GenericEntity<List<TurtleWeight>>(turtleWeights) {};
        return Response.ok(list).build();
    }
}
