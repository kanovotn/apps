package org.turtle.weightKeeper.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by kanovotn on 1/25/17.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "getTurtleById", query = "select t from Turtle t where t.id = :tid"),
        @NamedQuery(name = "findYearByName", query = "select t.yearOfBirth from Turtle t where t.name = :tname"),
        @NamedQuery(name = "getTurtleByName", query = "select t from Turtle t where t.name = :tname"),
        @NamedQuery(name = "getTurtles", query = "select t from Turtle t"),
})
public class Turtle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int yearOfBirth;

    // TODO for bidirectional relationship
    // https://howtoprogramwithjava.com/hibernate-onetomany-bidirectional-relationship/
    //private Set<TurtleWeight> turtleWeights;

    public Turtle() {
    }

    public Turtle(String name, int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    /*@OneToMany(cascade=CascadeType.ALL, mappedBy="employer")
    public Set<TurtleWeight> getTurtleWeights() {
        return turtleWeights;
    }

    public void setTurtleWeights(Set<TurtleWeight> turtleWeights) {
        this.turtleWeights = turtleWeights;
    }*/
}
