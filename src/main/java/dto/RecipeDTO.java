/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Recipe;
import java.util.List;

/**
 *
 * @author Malte
 */
public class RecipeDTO {

    private int id;

    private int preparation_time; // in seconds
    private String directions; // instructions for the recipe..??
    private String name;
    private List<IngredientDTO> ingredient_listDTO;
    private int price;
//    private List<WeekDTO> week;
//    private List<Week_menu_planDTO> week_menu_plan;

    public RecipeDTO() {
    }

    public RecipeDTO(Recipe recipe) {
        this.directions = recipe.getDirections();
        this.name = recipe.getName();
        this.id = recipe.getId();
        this.preparation_time = recipe.getPreparation_time();
        recipe.getIngredient_list().forEach((ingredient) -> {
            this.ingredient_listDTO.add(new IngredientDTO(ingredient));
        });
//        recipe.getWeek_menu_plan().forEach((week) -> {
//            this.week.add(new WeekDTO(week));
//        });
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(int preparation_time) {
        this.preparation_time = preparation_time;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientDTO> getIngredient_listDTO() {
        return ingredient_listDTO;
    }

    public void setIngredient_listDTO(List<IngredientDTO> ingredient_listDTO) {
        this.ingredient_listDTO = ingredient_listDTO;
    }

}
