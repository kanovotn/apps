package org.turtle.weightKeeper.rest;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by kanovotn on 2/1/17.
 */
@Provider
public class DateConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        if (Date.class.equals(type)) {
            @SuppressWarnings("unchecked")
            ParamConverter<T> paramConverter = (ParamConverter<T>) new DateParameterConverter();
            return paramConverter;
        }
        return null;
    }
}
