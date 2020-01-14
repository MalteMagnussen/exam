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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Malte
 */
@Entity
@XmlRootElement
public class Week_menu_plan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // Ordered list of 7 recipes.
    @ManyToMany
    private List<Recipe> recipe_list;
    private int week_num; // Week Number
    private int year_;

    public Week_menu_plan() {
        this.recipe_list = new ArrayList();
    }

    /**
     * Get the value of year
     *
     * @return the value of year
     */
    public int getYear() {
        return year_;
    }

    /**
     * Set the value of year
     *
     * @param year_
     */
    public void setYear(int year_) {
        this.year_ = year_;
    }

    /**
     * Get the value of week_num
     *
     * @return the value of week_num
     */
    public int getWeek_num() {
        return week_num;
    }

    /**
     * Set the value of week_num
     *
     * @param week_num new value of week_num
     */
    public void setWeek_num(int week_num) {
        this.week_num = week_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
