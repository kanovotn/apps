package org.turtle.weightKeeper.ejb;

import org.turtle.weightKeeper.entities.Turtle;
import org.turtle.weightKeeper.entities.TurtleWeight;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by kanovotn on 1/25/17.
 */

@Stateless
public class TurtleBean {

    @PersistenceContext(name = "turtleDb")
    private EntityManager em;

    public Turtle storeTurtle(Turtle turtle) {
        List<Turtle> turtles = getAllTurtlesByName(turtle.getName());
        if (turtles.size() != 0)
            return null;
        em.persist(turtle);
        return turtle;
    }

    public int getYear(String name) {
        Query query = em.createNamedQuery("findYearByName").setParameter("tname", name);
        return (int) query.getSingleResult();
    }

    private List<Turtle> getAllTurtlesByName(String name) {
        Query query = em.createNamedQuery("findTurtle", Turtle.class).setParameter("tname", name);
        List<Turtle> turtles = query.getResultList();
        return  turtles;
    }

    public TurtleWeight recordWeight(String name, Date date, int weight) {
        List<Turtle> turtles = getAllTurtlesByName(name);
        if (turtles.size() == 0)
            return null;
        TurtleWeight tweight = new TurtleWeight(turtles.get(0), date, weight);
        em.persist(tweight);
        return tweight;
    }

    public List<TurtleWeight> getAllRecordsByName(String name) {
        List<Turtle> turtles = getAllTurtlesByName(name);
        if (turtles.size() == 0)
            return null;
        Query query = em.createNamedQuery("getRecordsByTurtle", TurtleWeight.class).setParameter("tturtle", turtles.get(0));
        List<TurtleWeight> turtleWeights = query.getResultList();
        return turtleWeights;
    }

    public List<TurtleWeight> getAllRecords() {
        Query query = em.createNamedQuery("getAllRecords", TurtleWeight.class);
        List<TurtleWeight> turtleWeights = query.getResultList();
        return turtleWeights;
    }
}