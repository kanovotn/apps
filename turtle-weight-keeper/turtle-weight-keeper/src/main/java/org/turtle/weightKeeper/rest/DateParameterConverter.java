package org.turtle.weightKeeper.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ParamConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kanovotn on 2/1/17.
 */
public class DateParameterConverter implements ParamConverter<Date> {

    public static final String format = "yyyy-MM-dd";

    @Override
    public Date fromString(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException ex) {
            throw new WebApplicationException(ex);
        }
    }

    @Override
    public String toString(Date date) {
        return new SimpleDateFormat(format).format(date);
    }
}
