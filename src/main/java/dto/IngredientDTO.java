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
    private ItemDTO itemDTO;

    public IngredientDTO() {
    }

    public IngredientDTO(Ingredient ingredient) {
        this.itemDTO = new ItemDTO(ingredient.getItem());
        this.id = ingredient.getId();
        this.amount = ingredient.getAmount();
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

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public void setItemDTO(ItemDTO itemDTO) {
        this.itemDTO = itemDTO;
    }

}
