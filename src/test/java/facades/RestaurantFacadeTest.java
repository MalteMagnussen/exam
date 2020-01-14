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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author Malte
 */
public class RestaurantFacadeTest {

    private static EntityManagerFactory emf;
    private static RestaurantFacade facade;

    private static Item pasta;
    private static Item ris;
    private static Item kartofler;
    private static Item gulerod;
    private static Item ost;
    private static Item tomat;
    private static Item laks;

    private static Ingredient pasta_1;
    private static Ingredient pasta_2;
    private static Ingredient ris_;
    private static Ingredient kartofler_1;
    private static Ingredient kartofler_2;
    private static Ingredient gulerod_;
    private static Ingredient ost_1;
    private static Ingredient ost_2;
    private static Ingredient ost_3;
    private static Ingredient tomat_1;
    private static Ingredient tomat_2;
    private static Ingredient laks_1;
    private static Ingredient laks_2;
    private static Ingredient laks_3;

    private static Recipe risLaks;
    private static Recipe pastaLaks;
    private static Recipe kartoflerLaks;
    private static Recipe tomatGulerod;
    private static Recipe ostTomat;
    private static Recipe ostKartofler;
    private static Recipe pastaOst;

    public RestaurantFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = RestaurantFacade.getRestaurantFacade(emf);

        // Find items
        pasta = new Item("pasta", 1000);
        ris = new Item("ris", 1000);
        kartofler = new Item("kartofler", 900);
        gulerod = new Item("gulerod", 600);
        ost = new Item("ost", 4000);
        tomat = new Item("tomat", 1500);
        laks = new Item("laks", 10000);

        // Make ingredients
        pasta_1 = new Ingredient(10, pasta);
        pasta_2 = new Ingredient(34, pasta);
        ris_ = new Ingredient(20, ris);
        kartofler_1 = new Ingredient(30, kartofler);
        kartofler_2 = new Ingredient(38, kartofler);
        gulerod_ = new Ingredient(40, gulerod);
        ost_1 = new Ingredient(50, ost);
        ost_2 = new Ingredient(55, ost);
        ost_3 = new Ingredient(58, ost);
        tomat_1 = new Ingredient(60, tomat);
        tomat_2 = new Ingredient(61, tomat);
        laks_1 = new Ingredient(70, laks);
        laks_2 = new Ingredient(70, laks);
        laks_3 = new Ingredient(70, laks);

        // Make recipes
        risLaks = new Recipe("Put Ris i og så Laks", "Ris og Laks", 5000);
//            risLaks.add(laks_1);
//            risLaks.add(ris_);
        laks_1.setRecipe(risLaks);
        ris_.setRecipe(risLaks);

        pastaLaks = new Recipe("Put Pasta i og så Laks", "Pasta og Laks", 4000);
//            pastaLaks.add(laks_3);
//            pastaLaks.add(pasta_1);
        laks_3.setRecipe(pastaLaks);
        pasta_1.setRecipe(pastaLaks);

        kartoflerLaks = new Recipe("Put kartofler i og så Laks", "kartofler og Laks", 6000);
//            kartoflerLaks.add(laks_2);
//            kartoflerLaks.add(kartofler_1);
        laks_2.setRecipe(kartoflerLaks);
        kartofler_1.setRecipe(kartoflerLaks);

        tomatGulerod = new Recipe("Snit gulerod og tomat", "Gulerod og tomat", 1000);
//            tomatGulerod.add(gulerod_);
//            tomatGulerod.add(tomat_1);
        gulerod_.setRecipe(tomatGulerod);
        tomat_1.setRecipe(tomatGulerod);

        ostTomat = new Recipe("Ost henover tomater", "Tomat og Ost", 5000);
//            ostTomat.add(ost_1);
//            ostTomat.add(tomat_2);
        ost_1.setRecipe(ostTomat);
        tomat_2.setRecipe(ostTomat);
        
        ostKartofler = new Recipe("ost i kartolfer", "ost og kartofler", 5000);
//            ostKartofler.add(ost_2);
//            ostKartofler.add(kartofler_2);
        ost_2.setRecipe(ostKartofler);
        kartofler_2.setRecipe(ostKartofler);

        pastaOst = new Recipe("ost over pasta", "pasta og ost", 5000);
//            pastaOst.add(pasta_2);
//            pastaOst.add(ost_3);
        pasta_2.setRecipe(pastaOst);
        ost_3.setRecipe(pastaOst);

    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //  PERSIST TEST DATA HERE

        // Persist them.  
        em.persist(risLaks);
        em.persist(pastaLaks);
        em.persist(kartoflerLaks);
        em.persist(tomatGulerod);
        em.persist(ostTomat);
        em.persist(ostKartofler);
        em.persist(pastaOst);

        // persist items
        em.persist(pasta);
        em.persist(ris);
        em.persist(kartofler);
        em.persist(gulerod);
        em.persist(ost);
        em.persist(tomat);
        em.persist(laks);

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
        em.close();
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Item.deleteAllRows").executeUpdate();
            //  DELETE TEST DATA HERE
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Test
    public void testAdminAddItem() {
        System.out.println("adminAddItem");
        ItemDTO expResult = new ItemDTO(new Item("pasta", 1000));
        ItemDTO result = facade.addItem(expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRestaurantFacade method, of class RestaurantFacade.
     */
    @Test
    public void testGetRestaurantFacade() {
        System.out.println("getRestaurantFacade");
        EntityManagerFactory _emf = null;
        RestaurantFacade expResult = null;
        RestaurantFacade result = RestaurantFacade.getRestaurantFacade(_emf);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populateWithRecipes method, of class RestaurantFacade.
     */
    @Test
    public void testPopulateWithRecipes() {
        System.out.println("populateWithRecipes");
        RestaurantFacade instance = null;
        instance.populateWithRecipes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addItem method, of class RestaurantFacade.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        ItemDTO itemDTO = null;
        RestaurantFacade instance = null;
        ItemDTO expResult = null;
        ItemDTO result = instance.addItem(itemDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStorage method, of class RestaurantFacade.
     */
    @Test
    public void testAddStorage() {
        System.out.println("addStorage");
        StorageDTO storageDTO = null;
        RestaurantFacade instance = null;
        StorageDTO expResult = null;
        StorageDTO result = instance.addStorage(storageDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItems method, of class RestaurantFacade.
     */
    @Test
    public void testGetItems() {
        System.out.println("getItems");
        RestaurantFacade instance = null;
        List<ItemDTO> expResult = null;
        List<ItemDTO> result = instance.getItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStorage method, of class RestaurantFacade.
     */
    @Test
    public void testGetStorage() {
        System.out.println("getStorage");
        RestaurantFacade instance = null;
        List<StorageDTO> expResult = null;
        List<StorageDTO> result = instance.getStorage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateStorage method, of class RestaurantFacade.
     */
    @Test
    public void testUpdateStorage() {
        System.out.println("updateStorage");
        int amount = 0;
        int itemID = 0;
        RestaurantFacade instance = null;
        StorageDTO expResult = null;
        StorageDTO result = instance.updateStorage(amount, itemID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipes method, of class RestaurantFacade.
     */
    @Test
    public void testGetRecipes() {
        System.out.println("getRecipes");
        RestaurantFacade instance = null;
        List<RecipeDTO> expResult = null;
        List<RecipeDTO> result = instance.getRecipes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addWeek method, of class RestaurantFacade.
     */
    @Test
    public void testAddWeek() {
        System.out.println("addWeek");
        WeekDTO weekDTO = null;
        RestaurantFacade instance = null;
        WeekDTO expResult = null;
        WeekDTO result = instance.addWeek(weekDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeeks method, of class RestaurantFacade.
     */
    @Test
    public void testGetWeeks() {
        System.out.println("getWeeks");
        RestaurantFacade instance = null;
        List<WeekDTO> expResult = null;
        List<WeekDTO> result = instance.getWeeks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkStorageHelper method, of class RestaurantFacade.
     */
    @Test
    public void testCheckStorageHelper() {
        System.out.println("checkStorageHelper");
        List<IngredientDTO> ingredients = null;
        RestaurantFacade instance = null;
        String expResult = "";
        String result = instance.checkStorageHelper(ingredients);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRecipe method, of class RestaurantFacade.
     */
    @Test
    public void testAddRecipe() {
        System.out.println("addRecipe");
        RecipeDTO recipeDTO = null;
        RestaurantFacade instance = null;
        RecipeDTO expResult = null;
        RecipeDTO result = instance.addRecipe(recipeDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editRecipe method, of class RestaurantFacade.
     */
    @Test
    public void testEditRecipe() {
        System.out.println("editRecipe");
        RecipeDTO recipeDTO = null;
        RestaurantFacade instance = null;
        RecipeDTO expResult = null;
        RecipeDTO result = instance.editRecipe(recipeDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRecipe method, of class RestaurantFacade.
     */
    @Test
    public void testDeleteRecipe() {
        System.out.println("deleteRecipe");
        int id = 0;
        RestaurantFacade instance = null;
        RecipeDTO expResult = null;
        RecipeDTO result = instance.deleteRecipe(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addIngredient method, of class RestaurantFacade.
     */
    @Test
    public void testAddIngredient() {
        System.out.println("addIngredient");
        int itemId = 0;
        int amount = 0;
        int recipeId = 0;
        RestaurantFacade instance = null;
        IngredientDTO expResult = null;
        IngredientDTO result = instance.addIngredient(itemId, amount, recipeId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    

}
