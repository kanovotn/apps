package org.turtle.weightKeeper.rest;

import org.turtle.weightKeeper.ejb.WeightStorageBean;
import org.turtle.weightKeeper.entities.Turtle;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;

/**
 * Created by kanovotn on 1/25/17.
 */

@Path("turtle")
public class TurtleResource {

    @Inject
    WeightStorageBean wb;

    @POST
    @Path("create/{name}/{year}")
    public Response createTurtle(@PathParam("name") String name, @PathParam("year") int year) {
        Turtle newTurtle = new Turtle(name, year);
        wb.storeTurtle(newTurtle);
        return Response.ok().entity("Turtle " + name + " created.").type(MediaType.TEXT_HTML_TYPE).build();
    }

    @GET
    public String get(String name) {
        //wb.storeTurtle("Zeytin");
        Turtle turtle = wb.getTurtle("Zeytin");
        return turtle.getName() + ": " + turtle.getYearOfBirth().get(Calendar.YEAR);
        //return "hello";
    }
}
