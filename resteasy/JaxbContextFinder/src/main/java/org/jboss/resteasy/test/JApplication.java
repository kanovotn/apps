package org.jboss.resteasy.test;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationPath("/")
public class JApplication extends Application {
    public Set<Class<?>> getClasses()
    {
        HashSet<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(JaxbCacheChild.class);
        classes.add(JaxbCacheParent.class);
        return classes;
    }

    }
