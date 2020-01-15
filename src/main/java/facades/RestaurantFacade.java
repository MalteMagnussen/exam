/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.IngredientDTO;
import dto.ItemDTO;
import dto.RecipeDTO;
import dto.StorageDTO;
import dto.WeekDTO;
import entities.Ingredient;
import entities.Item;
import entities.Recipe;
import entities.Storage;
import entities.Week_menu_plan;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Malte
 */
public class RestaurantFacade {

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

    public void populateWithRecipes() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            // Find items
            Item pasta = em.find(Item.class, 1);
            Item ris = em.find(Item.class, 2);
            Item kartofler = em.find(Item.class, 3);
            Item gulerod = em.find(Item.class, 4);
            Item ost = em.find(Item.class, 5);
            Item tomat = em.find(Item.class, 6);
            Item laks = em.find(Item.class, 7);

            // Make ingredients
            Ingredient pasta_1 = new Ingredient(10, pasta);
            Ingredient pasta_2 = new Ingredient(34, pasta);
            Ingredient ris_ = new Ingredient(20, ris);
            Ingredient kartofler_1 = new Ingredient(30, kartofler);
            Ingredient kartofler_2 = new Ingredient(38, kartofler);
            Ingredient gulerod_ = new Ingredient(40, gulerod);
            Ingredient ost_1 = new Ingredient(50, ost);
            Ingredient ost_2 = new Ingredient(55, ost);
            Ingredient ost_3 = new Ingredient(58, ost);
            Ingredient tomat_1 = new Ingredient(60, tomat);
            Ingredient tomat_2 = new Ingredient(61, tomat);
            Ingredient laks_1 = new Ingredient(70, laks);
            Ingredient laks_2 = new Ingredient(70, laks);
            Ingredient laks_3 = new Ingredient(70, laks);

            // Make recipes
            Recipe risLaks = new Recipe("Put Ris i og så Laks", "Ris og Laks", 5000);
//            risLaks.add(laks_1);
//            risLaks.add(ris_);
            laks_1.setRecipe(risLaks);
            ris_.setRecipe(risLaks);

            Recipe pastaLaks = new Recipe("Put Pasta i og så Laks", "Pasta og Laks", 4000);
//            pastaLaks.add(laks_3);
//            pastaLaks.add(pasta_1);
            laks_3.setRecipe(pastaLaks);
            pasta_1.setRecipe(pastaLaks);

            Recipe kartoflerLaks = new Recipe("Put kartofler i og så Laks", "kartofler og Laks", 6000);
//            kartoflerLaks.add(laks_2);
//            kartoflerLaks.add(kartofler_1);
            laks_2.setRecipe(kartoflerLaks);
            kartofler_1.setRecipe(kartoflerLaks);

            Recipe tomatGulerod = new Recipe("Snit gulerod og tomat", "Gulerod og tomat", 1000);
//            tomatGulerod.add(gulerod_);
//            tomatGulerod.add(tomat_1);
            gulerod_.setRecipe(tomatGulerod);
            tomat_1.setRecipe(tomatGulerod);

            Recipe ostTomat = new Recipe("Ost henover tomater", "Tomat og Ost", 5000);
//            ostTomat.add(ost_1);
//            ostTomat.add(tomat_2);
            ost_1.setRecipe(ostTomat);
            tomat_2.setRecipe(ostTomat);

            Recipe ostKartofler = new Recipe("ost i kartolfer", "ost og kartofler", 5000);
//            ostKartofler.add(ost_2);
//            ostKartofler.add(kartofler_2);
            ost_2.setRecipe(ostKartofler);
            kartofler_2.setRecipe(ostKartofler);

            Recipe pastaOst = new Recipe("ost over pasta", "pasta og ost", 5000);
//            pastaOst.add(pasta_2);
//            pastaOst.add(ost_3);
            pasta_2.setRecipe(pastaOst);
            ost_3.setRecipe(pastaOst);

            // Persist them.  
            em.persist(risLaks);
            em.persist(pastaLaks);
            em.persist(kartoflerLaks);
            em.persist(tomatGulerod);
            em.persist(ostTomat);
            em.persist(ostKartofler);
            em.persist(pastaOst);

            // Persist ingredients
            em.persist(pasta_1);
            em.persist(pasta_2);
            em.persist(ris_);
            em.persist(kartofler_1);
            em.persist(kartofler_2);
            em.persist(gulerod_);
            em.persist(ost_1);
            em.persist(ost_2);
            em.persist(ost_3);
            em.persist(tomat_1);
            em.persist(tomat_2);
            em.persist(laks_1);
            em.persist(laks_2);
            em.persist(laks_3);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

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

    public StorageDTO addStorage(StorageDTO storageDTO) {
        Storage storage = new Storage(storageDTO);
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(storage);
            em.getTransaction().commit();
            return new StorageDTO(storage);
        } finally {
            em.close();
        }
    }

    public List<ItemDTO> getItems() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            List<Item> items = em.createNamedQuery("Item.getAll").getResultList();
            em.getTransaction().commit();
            List<ItemDTO> itemsDTO = new ArrayList();
            items.forEach((item) -> {
                itemsDTO.add(new ItemDTO(item));
            });
            return itemsDTO;
        } finally {
            em.close();
        }
    }

    public List<StorageDTO> getStorage() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            List<Storage> storages = em.createNamedQuery("Storage.getAll").getResultList();
            em.getTransaction().commit();
            List<StorageDTO> storagesDTO = new ArrayList();
            storages.forEach((storage) -> {
                storagesDTO.add(new StorageDTO(storage));
            });
            return storagesDTO;
        } finally {
            em.close();
        }
    }

    public StorageDTO updateStorage(int amount, int itemID) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Storage storage = em.find(Storage.class, itemID);
            if (storage == null) {
                ItemDTO itemDTO;
                Item item = em.find(Item.class, itemID);
                itemDTO = new ItemDTO(item);
                StorageDTO storage_ = new StorageDTO(itemDTO, amount, itemID);
                return addStorage(storage_);
            }
            storage.setAmount(amount);
            em.merge(storage);
            em.getTransaction().commit();
            return new StorageDTO(storage);
        } catch (NoResultException e) {
            ItemDTO itemDTO;
            Item item = em.find(Item.class, itemID);
            itemDTO = new ItemDTO(item);
            StorageDTO storage = new StorageDTO(itemDTO, amount, itemID);
            return addStorage(storage);
        } finally {
            em.close();
        }
    }

    public List<RecipeDTO> getRecipes() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            List<Recipe> recipes = em.createNamedQuery("Recipe.getAll").getResultList();
            List<RecipeDTO> recipesDTO = new ArrayList();
            recipes.forEach((recipe) -> {
                List<Ingredient> ingredients = em.createNamedQuery("Ingredient.getForRecipe").setParameter("id", recipe.getId()).getResultList();
                List<IngredientDTO> ingredientsDTO = new ArrayList();
                ingredients.forEach((ingredient) -> {
                    Item item = em.createNamedQuery("Ingredient.getItemForRecipe", Item.class).setParameter("id", ingredient.getId()).getSingleResult();
                    ingredient.setItem(item);
                    IngredientDTO ingredientDTO = new IngredientDTO(ingredient);
                    ingredientsDTO.add(ingredientDTO);
                });
                RecipeDTO recipeDTO = new RecipeDTO(recipe);
                recipeDTO.setIngredient_listDTO(ingredientsDTO);
//                int price = em.createNamedQuery("Recipe.price", Integer.class).setParameter("id", recipe.getId()).getSingleResult();
//                recipeDTO.setPrice(price);
                recipesDTO.add(recipeDTO);
            });
            return recipesDTO;
        } finally {
            em.close();
        }
    }

    public WeekDTO addWeek(WeekDTO weekDTO) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Week_menu_plan week = new Week_menu_plan(weekDTO);
            
            // TODO find all recipes related to this week menu, then set it on. 
            // TODO in frontend - Send the week plan to backend. 
            // Very tired now. 
            
            List<Recipe> recipes = new ArrayList();
            for (Recipe recipe: week.getRecipe_list()) {
                Recipe recipe_ = em.find(Recipe.class, recipe.getId());
                recipes.add(recipe_);
            }
            week.setRecipe_list(recipes);
            
            em.persist(week);
            em.getTransaction().commit();
            return new WeekDTO(week);
        } finally {
            em.close();
        }
    }

    public List<WeekDTO> getWeeks() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            List<Week_menu_plan> weeks = em.createNamedQuery("Week_menu_plan.getAll").getResultList();
            for (Week_menu_plan week: weeks) {
               List<Recipe> recipes = em.createNamedQuery("Week_menu_plan.getRecipes").setParameter("id", week.getId()).getResultList();
               week.setRecipe_list(recipes);
            }
            em.getTransaction().commit();
            List<WeekDTO> weekDTO = new ArrayList();
            weeks.forEach(week -> {
                weekDTO.add(new WeekDTO(week));
            });
            return weekDTO;
        } finally {
            em.close();
        }
    }

    public String checkStorageHelper(List<IngredientDTO> ingredients) {

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            for (IngredientDTO ingredientDTO : ingredients) {
                String name = ingredientDTO.getItemDTO().getName();
                Integer ingredient_amount = em.createNamedQuery("Ingredient.checkStorage", Integer.class).setParameter("name", name).getSingleResult();
                Integer storage_amount = em.createNamedQuery("Storage.checkStorage", Integer.class).setParameter("name", name).getSingleResult();
                if (ingredient_amount > storage_amount) {
                    throw new WebApplicationException("Ingredient with name: " + name + " is not in storage at a high enough quantity.", Response.Status.BAD_REQUEST);
                }
            }

            em.getTransaction().commit();
            return "You have all the required ingredients for that recipe in storage.";
        } finally {
            em.close();
        }
    }

    public RecipeDTO addRecipe(RecipeDTO recipeDTO) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Recipe recipe = new Recipe(recipeDTO);
            em.persist(recipe);
            em.getTransaction().commit();
            return new RecipeDTO(recipe);
        } finally {
            em.close();
        }
    }

    public RecipeDTO editRecipe(RecipeDTO recipeDTO) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Recipe recipe = em.find(Recipe.class, recipeDTO.getId());
            recipe.setDirections(recipeDTO.getDirections());
            recipe.setName(recipeDTO.getName());
            recipe.setPreparation_time(recipeDTO.getPreparation_time());
            em.merge(recipe);
            em.getTransaction().commit();
            return new RecipeDTO(recipe);
        } finally {
            em.close();
        }
    }

    public RecipeDTO deleteRecipe(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Recipe recipe = em.find(Recipe.class, id);
            em.remove(recipe);
            em.getTransaction().commit();
            return new RecipeDTO(recipe);
        } finally {
            em.close();
        }
    }

    public IngredientDTO addIngredient(int itemId, int amount, int recipeId) {
        // id of the item.
        // find item. 
        // make ingredient with volume. new ingredient ( volume, item ) 
        // find recipe with id
        // set recipe on ingredient
        // then persist ingredient
        // Getting tired
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Item item = em.find(Item.class, itemId);
            Ingredient ingredient = new Ingredient(amount, item);
            Recipe recipe = em.find(Recipe.class, recipeId);
            ingredient.setRecipe(recipe);
            em.persist(ingredient);
            em.getTransaction().commit();
            return new IngredientDTO(ingredient);
        } finally {
            em.close();
        }
    }

}
