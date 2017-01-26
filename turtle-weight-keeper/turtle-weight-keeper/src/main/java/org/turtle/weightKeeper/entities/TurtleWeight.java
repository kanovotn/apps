package org.turtle.weightKeeper.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by kanovotn on 1/25/17.
 */
@Entity
@Table(name = "TURTLE_WEIGHT")
public class TurtleWeight implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //@Column(name = "TURTLE_ID")
    //private int turtleId;

    @Column(name= "DATE")
    private Date date;

    @Column(name= "WEIGHT")
    private int weight;

    @ManyToOne
    @JoinColumn(name = "TURTLE_ID")
    private Turtle turtle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   /* public int getTurtleId() {
        return turtleId;
    }

    public void setTurtleId(int turtleId) {
        this.turtleId = turtleId;
    }*/

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public void setTurtle(Turtle turtle) {
        this.turtle = turtle;
    }
}
