/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Ingredient;
import entities.Recipe;
import java.util.List;

/**
 *
 * @author Malte
 */
public interface RestaurantInterface {

    /*
    For the client application, we would like the following features:
        (Has interface) The Chef should be able to see a list of all recipes
        The Chef should be able to search for a specific recipe (you decide how, and which fields can be used in your search (ingredients, price etc
        (Has interface) The Chef should be able to choose 7 recipes.
        (Has interface) When a recipe is chosen the app should check that all ingredients are available in storage or otherwise give a warning.
        (Has interface) Users logged in as admin can add/edit/delete recipes
        Optionally show a details page using routing and possibly include an image of the recipe
     */

    /**
     * Get all Recipes. The Chef should be able to see a list of all recipes.
     *
     * @return List of Recipes.
     */
    public List<Recipe> getAllRecipes();

    /**
     * The Chef should be able to search for a specific Recipe.
     */
    /**
     * Get seven Recipes. The Chef should be able to choose 7 recipes.
     *
     * @param names of the 7 recipes, in order.
     * @return The 7 recipes, in order.
     */
    public List<Recipe> getSevenRecipes(List<String> names);

    /**
     * Check Storage for ingredients. When a recipe is chosen the app should
     * check that all ingredients are available in storage or otherwise give a
     * warning.
     *
     * @param recipe
     * @return false if storage does not contain enough ingredients. True if it
     * does.
     */
    public boolean checkStorage(Recipe recipe);

    // CRUD RECIPE START
    /**
     * Add Recipe.
     *
     * @param name
     * @param directions
     * @param preparation_time
     * @param ingredient_list
     * @return The Recipe you just made.
     */
    public Recipe addRecipe(String name, String directions, int preparation_time, List<Ingredient> ingredient_list);

    /**
     * Edit Recipe.
     *
     * @param id of the Recipe you wish to edit.
     * @param name
     * @param directions
     * @param preparation_time
     * @param ingredient_list
     * @return The Recipe you just edited.
     */
    public Recipe editRecipe(int id, String name, String directions, int preparation_time, List<Ingredient> ingredient_list);

    /**
     * Delete Recipe.
     *
     * @param id of the Recipe you wish to delete.
     * @return The Recipe you just deleted.
     */
    public Recipe deleteRecipe(int id);
    // CRUD RECIPE END.
}
