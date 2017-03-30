package org.turtle.weightKeeper.rest;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by kanovotn on 3/30/17.
 */
@Provider
public class ExceptionMapperNoResult implements ExceptionMapper<NoResultException> {
    @Override
    public Response toResponse(NoResultException exception) {
        return Response.status(400).type("text/plain").entity("Record not found").build();
    }
}
