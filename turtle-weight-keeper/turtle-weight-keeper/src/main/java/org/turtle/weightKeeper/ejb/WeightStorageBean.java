package org.turtle.weightKeeper.ejb;

import org.turtle.weightKeeper.entities.Turtle;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 * Created by kanovotn on 1/25/17.
 */

@Stateless
public class WeightStorageBean {

    @PersistenceContext(name = "turtleDb")
    private EntityManager em;

    public void storeTurtle(Turtle turtle) {
        em.persist(turtle);
    }

    public Turtle getTurtle(String name) {
        Query query = (Query) em.createNamedQuery("findByName").setParameter("tname", name);
        return (Turtle) query.getSingleResult();
    }
}
