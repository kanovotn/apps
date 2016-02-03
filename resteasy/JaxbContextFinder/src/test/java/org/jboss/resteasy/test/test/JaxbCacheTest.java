package org.jboss.resteasy.test.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.resteasy.plugins.providers.jaxb.JAXBContextFinder;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.test.*;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBContext;

/**
 * @tpSubChapter Jaxb provider
 * @tpChapter Integration tests
 * @tpSince EAP 7.0.0
 */
@RunWith(Arquillian.class)
public class JaxbCacheTest {

    private static Logger logger = Logger.getLogger(JaxbCacheTest.class.getName());

    @Deployment
    public static Archive<?> deploy() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "jaxbcontextfinder.war")
                .addClasses(JApplication.class)
                .addClasses(JaxbCacheParent.class)
                .addClasses(JaxbCacheChild.class)
                .addAsWebInfResource("web.xml");
        System.out.println(war.toString(true));
        return war;
    }


    @Test
    public void testCache() throws Exception {
        ResteasyProviderFactory factory = ResteasyProviderFactory.getInstance();
        ResteasyProviderFactory.pushContext(Providers.class, factory);
        {
            ContextResolver<JAXBContextFinder> resolver = factory.getContextResolver(JAXBContextFinder.class, MediaType.APPLICATION_XML_TYPE);
            logger.info("xml resolver:" + resolver.toString());
            JAXBContextFinder finder = resolver.getContext(JaxbCacheChild.class);
            logger.info("xml finder:" + finder.toString());
            JAXBContext ctx = finder.findCachedContext(JaxbCacheChild.class, MediaType.APPLICATION_XML_TYPE, null);
            logger.info("xml ctx:" + ctx.toString());

            JAXBContext ctx2 = finder.findCachedContext(JaxbCacheChild.class, MediaType.APPLICATION_XML_TYPE, null);

            Assert.assertTrue(ctx == ctx2);
        }

        {
            ContextResolver<JAXBContextFinder> resolver = factory.getContextResolver(JAXBContextFinder.class, MediaType.APPLICATION_JSON_TYPE);
            logger.info("json resolver:" + resolver.toString());
            JAXBContextFinder finder = resolver.getContext(JaxbCacheChild.class);
            logger.info("json finder:" + finder.toString());
            JAXBContext ctx = finder.findCachedContext(JaxbCacheChild.class, MediaType.APPLICATION_JSON_TYPE, null);
            logger.info("json:" + ctx.toString());

            JAXBContext ctx2 = finder.findCachedContext(JaxbCacheChild.class, MediaType.APPLICATION_JSON_TYPE, null);

            Assert.assertTrue(ctx == ctx2);
        }
        /*{
            MediaType mediaType = new MediaType("application", "fastinfoset");
            ContextResolver<JAXBContextFinder> resolver = factory.getContextResolver(JAXBContextFinder.class, mediaType);
            logger.info("json resolver:" + resolver.toString());
            //ContextResolver<JAXBContextFinder> resolver = factory.getContextResolver(JAXBContextFinder.class, MediaType.APPLICATION_XML_TYPE);
            JAXBContextFinder finder = resolver.getContext(JaxbCacheChild.class);
            logger.info("json finder:" + finder.toString());
            JAXBContext ctx = finder.findCachedContext(JaxbCacheChild.class, mediaType, null);
            //JAXBContext ctx = finder.findCachedContext(JaxbCacheChild.class, MediaType.APPLICATION_XML_TYPE, null);

            JAXBContext ctx2 = finder.findCachedContext(JaxbCacheChild.class, mediaType, null);

            Assert.assertTrue(ctx == ctx2);
        }*/
    }

    // @Test
    public void testCache2() throws Exception {
        ResteasyProviderFactory factory = ResteasyProviderFactory.getInstance();
        ResteasyProviderFactory.pushContext(Providers.class, factory);
        {
            ContextResolver<JAXBContextFinder> resolver = factory.getContextResolver(JAXBContextFinder.class, MediaType.APPLICATION_XML_TYPE);
            JAXBContextFinder finder = resolver.getContext(JaxbCacheChild.class);
            JAXBContext ctx = finder.findCacheContext(MediaType.APPLICATION_XML_TYPE, null, JaxbCacheChild.class, JaxbCacheParent.class);


            JAXBContext ctx2 = finder.findCacheContext(MediaType.APPLICATION_XML_TYPE, null, JaxbCacheChild.class, JaxbCacheParent.class);

            Assert.assertTrue(ctx == ctx2);
        }

        {
            ContextResolver<JAXBContextFinder> resolver = factory.getContextResolver(JAXBContextFinder.class, MediaType.APPLICATION_JSON_TYPE);
            JAXBContextFinder finder = resolver.getContext(JaxbCacheChild.class);
            JAXBContext ctx = finder.findCacheContext(MediaType.APPLICATION_JSON_TYPE, null, JaxbCacheChild.class, JaxbCacheParent.class);


            JAXBContext ctx2 = finder.findCacheContext(MediaType.APPLICATION_JSON_TYPE, null, JaxbCacheChild.class, JaxbCacheParent.class);

            Assert.assertTrue(ctx == ctx2);
        }
        {
            MediaType mediaType = new MediaType("application", "fastinfoset");
            ContextResolver<JAXBContextFinder> resolver = factory.getContextResolver(JAXBContextFinder.class, mediaType);
            JAXBContextFinder finder = resolver.getContext(JaxbCacheChild.class);
            JAXBContext ctx = finder.findCacheContext(mediaType, null, JaxbCacheChild.class, JaxbCacheParent.class);


            JAXBContext ctx2 = finder.findCacheContext(mediaType, null, JaxbCacheChild.class, JaxbCacheParent.class);

            Assert.assertTrue(ctx == ctx2);
        }
    }
}
