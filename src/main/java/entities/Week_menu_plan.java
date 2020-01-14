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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Malte
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Week_menu_plan.getAll", query = "SELECT h FROM Week_menu_plan h"),
    @NamedQuery(name = "Week_menu_plan.deleteAllRows", query = "DELETE FROM Week_menu_plan")
})
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Recipe> getRecipe_list() {
        return recipe_list;
    }

    public void setRecipe_list(List<Recipe> recipe_list) {
        this.recipe_list = recipe_list;
    }

    public int getWeek_num() {
        return week_num;
    }

    public void setWeek_num(int week_num) {
        this.week_num = week_num;
    }

    public int getYear_() {
        return year_;
    }

    public void setYear_(int year_) {
        this.year_ = year_;
    }

}
