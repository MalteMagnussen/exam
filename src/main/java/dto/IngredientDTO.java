/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Ingredient;

/**
 *
 * @author Malte
 */
public class IngredientDTO {

    private int id;
    private int amount;
    private RecipeDTO recipeDTO;
    private ItemDTO itemDTO;

    public IngredientDTO() {
    }

    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.amount = ingredient.getAmount();
        this.recipeDTO = new RecipeDTO(ingredient.getRecipe());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public RecipeDTO getRecipeDTO() {
        return recipeDTO;
    }

    public void setRecipeDTO(RecipeDTO recipeDTO) {
        this.recipeDTO = recipeDTO;
    }

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

}
