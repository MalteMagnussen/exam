/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.IngredientDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Malte
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Ingredient.getAll", query = "SELECT h FROM Ingredient h"),
    @NamedQuery(name = "Ingredient.deleteAllRows", query = "DELETE FROM Ingredient"),
    @NamedQuery(name = "Ingredient.checkStorage", query = "SELECT h.amount FROM Ingredient h WHERE h.id = (SELECT i.id FROM Item i WHERE i.name = :name)"),
    @NamedQuery(name = "Ingredient.getForRecipe", query = "SELECT h FROM Ingredient h WHERE h.recipe.id = :id"),
    @NamedQuery(name = "Ingredient.getItemForRecipe", query = "SELECT i FROM Item i WHERE i.id = (SELECT h.item.id FROM Ingredient h WHERE h.id = :id)")
})
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;
    @ManyToOne
    private Recipe recipe;
    @OneToOne
    private Item item;

    public Ingredient() {

    }

    public Ingredient(int amount, Item item) {
        this.amount = amount;
        this.item = item;
    }

    public Ingredient(IngredientDTO ingredient) {
        this.id = ingredient.getId();
        this.amount = ingredient.getAmount();
        this.item = new Item(ingredient.getItemDTO());
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Get the value of amount
     *
     * @return the value of amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Set the value of amount
     *
     * @param amount new value of amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
