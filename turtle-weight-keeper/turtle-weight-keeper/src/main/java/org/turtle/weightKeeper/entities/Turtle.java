package org.turtle.weightKeeper.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by kanovotn on 1/25/17.
 */

@Entity
@Table(name = "TURTLE")
@NamedQuery(name = "findByName", query = "select t from TURTLE where t.name = :tname")
public class Turtle implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "YEAR_OF_BIRTH")
    private Calendar yearOfBirth;

    public Turtle() {

    }

    public Turtle(String name, int yearOfBirth) {
        Calendar cal = new GregorianCalendar();
        cal.set(yearOfBirth, 0, 0);
        this.yearOfBirth = cal;
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

    public Calendar getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Calendar yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
