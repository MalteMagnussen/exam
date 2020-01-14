/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.ItemDTO;
import entities.Ingredient;
import entities.Item;
import entities.Recipe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Malte
 */
public class RestaurantFacade implements RestaurantInterface {

    private static RestaurantFacade facade;
    private static EntityManagerFactory emf;

    private RestaurantFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static RestaurantFacade getRestaurantFacade(EntityManagerFactory _emf) {
        if (facade == null) {
            emf = _emf;
            facade = new RestaurantFacade();
        }
        return facade;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<Recipe> getAllRecipes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Recipe> getSevenRecipes(List<String> names) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkStorage(Recipe recipe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recipe addRecipe(String name, String directions, int preparation_time, List<Ingredient> ingredient_list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recipe editRecipe(int id, String name, String directions, int preparation_time, List<Ingredient> ingredient_list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recipe deleteRecipe(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemDTO addItem(ItemDTO itemDTO) {
        // Make item
        Item item = new Item(itemDTO);

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
            return new ItemDTO(item);
        } finally {
            em.close();
        }
    }

}
