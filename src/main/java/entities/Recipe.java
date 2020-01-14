/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Malte
 */
@Entity
@XmlRootElement
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int preparation_time; // in seconds
    private String directions; // instructions for the recipe..??
    private String name;
    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredient_list;
    @ManyToMany(mappedBy = "recipe_list")
    private List<Week_menu_plan> week_menu_plan;

    public Recipe() {
        this.week_menu_plan = new ArrayList();
        this.ingredient_list = new ArrayList();
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of directions
     *
     * @return the value of directions
     */
    public String getDirections() {
        return directions;
    }

    /**
     * Set the value of directions
     *
     * @param directions new value of directions
     */
    public void setDirections(String directions) {
        this.directions = directions;
    }

    /**
     * Get the value of preparation_time
     *
     * @return the value of preparation_time
     */
    public int getPreparation_time() {
        return preparation_time;
    }

    /**
     * Set the value of preparation_time
     *
     * @param preparation_time new value of preparation_time
     */
    public void setPreparation_time(int preparation_time) {
        this.preparation_time = preparation_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
