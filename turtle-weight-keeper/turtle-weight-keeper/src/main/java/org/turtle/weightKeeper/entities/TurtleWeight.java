package org.turtle.weightKeeper.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by kanovotn on 1/25/17.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getRecordsByTurtle", query = "select w from TurtleWeight w where w.turtle = :tturtle"),
        @NamedQuery(name = "getAllRecords", query = "select w from TurtleWeight w"),
})
public class TurtleWeight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date date;
    private int weight;
    private Turtle turtle;

    public TurtleWeight() {

    }

    public TurtleWeight(Turtle turtle, Date date, int weight) {
        this.turtle = turtle;
        this.date = date;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    // see https://howtoprogramwithjava.com/hibernate-manytoone-unidirectional-tutorial/
    @ManyToOne(cascade=CascadeType.ALL)
    public Turtle getTurtle() {
        return turtle;
    }

    public void setTurtle(Turtle turtle) {
        this.turtle = turtle;
    }
}
