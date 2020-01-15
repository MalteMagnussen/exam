/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Week_menu_plan;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Malte
 */
public class WeekDTO {

    private int id;
    private List<RecipeDTO> recipe_list;
    private int week_num; // Week Number
    private int year_;

    public WeekDTO() {
        this.recipe_list = new ArrayList();
    }

    public WeekDTO(Week_menu_plan week) {
        this.recipe_list = new ArrayList();
        week.getRecipe_list().forEach((recipe) -> {
            this.recipe_list.add(new RecipeDTO(recipe));
        });
        this.id = week.getId();
        this.week_num = week.getWeek_num();
        this.year_ = week.getYear_();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RecipeDTO> getRecipe_list() {
        return recipe_list;
    }

    public void setRecipe_list(List<RecipeDTO> recipe_list) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.week_num;
        hash = 83 * hash + this.year_;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WeekDTO other = (WeekDTO) obj;
        if (this.week_num != other.week_num) {
            return false;
        }
        if (this.year_ != other.year_) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WeekDTO{" + "id=" + id + ", recipe_list=" + recipe_list + ", week_num=" + week_num + ", year_=" + year_ + '}';
    }

}
