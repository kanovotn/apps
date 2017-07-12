package org.turtle.weightKeeper.rest;

import org.turtle.weightKeeper.ejb.TurtleBean;
import org.turtle.weightKeeper.entities.TurtleWeight;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by kanovotn on 1/25/17.
 */
@Path("records")
public class RecordsResource {

    @Inject
    TurtleBean turtleBean;
    int turtleId;

    public RecordsResource() {

    }

    public RecordsResource(int id) {
        this.turtleId = id;
    }

    @GET
    @Produces("application/json")
    public Response getRecordsById() {
        return Response.ok().build();
    }

    /*@GET
    @Produces("application/json")
    public Response getRecordsByName(@QueryParam("name") String name) {
        List<TurtleWeight> turtleWeights;
        if (name == null) {
            turtleWeights = turtleBean.getAllRecords();
        } else {
            turtleWeights = turtleBean.getAllRecordsByName(name);
        }
        GenericEntity<List<TurtleWeight>> list = new GenericEntity<List<TurtleWeight>>(turtleWeights) {};
        return Response.ok(list).build();
    }*/

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