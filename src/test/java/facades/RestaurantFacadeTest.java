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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
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
    private static RestaurantFacade instance;

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
        instance = RestaurantFacade.getRestaurantFacade(emf);

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
        ItemDTO expResult = new ItemDTO(pasta);
        ItemDTO result = instance.addItem(expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of addStorage method, of class RestaurantFacade.
     */
    @Test
    public void testAddStorage() {
        System.out.println("addStorage");
        Storage storage = new Storage(111);
        storage.setItem(pasta);
        StorageDTO storageDTO = new StorageDTO(storage);
        StorageDTO expResult = new StorageDTO(storage);
        StorageDTO result = instance.addStorage(storageDTO);
        assertEquals(expResult, result);
    }

    /**
     * Test of getItems method, of class RestaurantFacade.
     */
    @Test
    public void testGetItems() {
        System.out.println("getItems");
        List<ItemDTO> expResult = new ArrayList();
        expResult.add(new ItemDTO(pasta));
        expResult.add(new ItemDTO(ris));
        expResult.add(new ItemDTO(kartofler));
        expResult.add(new ItemDTO(gulerod));
        expResult.add(new ItemDTO(ost));
        expResult.add(new ItemDTO(tomat));
        expResult.add(new ItemDTO(laks));
        List<ItemDTO> result = instance.getItems();
        System.out.println("RESULT: "+result);
        System.out.println("EXPECTED RESULT: " + expResult);
        assertThat(result, containsInAnyOrder(expResult.toArray()));
    }

    /**
     * Test of getStorage method, of class RestaurantFacade.
     */
    @Test
    public void testGetStorage() {
        System.out.println("getStorage");
        Storage storage = new Storage(111);
        storage.setItem(pasta);
        StorageDTO storageDTO = new StorageDTO(storage);

        List<StorageDTO> expResult = new ArrayList();
        expResult.add(storageDTO);

        List<StorageDTO> result = instance.getStorage();
        assertEquals(expResult, result);
    }

    // Can't test this. It requires the ID of the item. Which changes all the time in the test database.
//    /**
//     * Test of updateStorage method, of class RestaurantFacade.
//     */
//    @Test
//    public void testUpdateStorage() {
//        System.out.println("updateStorage");
//        Storage storage = new Storage(111);
//        storage.setItem(pasta);
//        StorageDTO expResult = new StorageDTO(storage);
//        
//        StorageDTO result = instance.updateStorage(111, 1);
//        
//        assertEquals(expResult, result);
//    }
    /**
     * Test of getRecipes method, of class RestaurantFacade.
     */
    @Test
    public void testGetRecipes() {
        System.out.println("getRecipes");
        List<RecipeDTO> expResult = new ArrayList();

        /*
        private static Recipe risLaks;
    private static Recipe pastaLaks;
    private static Recipe kartoflerLaks;
    private static Recipe tomatGulerod;
    private static Recipe ostTomat;
    private static Recipe ostKartofler;
    private static Recipe pastaOst;
         */
        expResult.add(new RecipeDTO(risLaks));
        expResult.add(new RecipeDTO(pastaLaks));
        expResult.add(new RecipeDTO(kartoflerLaks));
        expResult.add(new RecipeDTO(tomatGulerod));
        expResult.add(new RecipeDTO(ostTomat));
        expResult.add(new RecipeDTO(ostKartofler));
        expResult.add(new RecipeDTO(pastaOst));

        List<RecipeDTO> result = instance.getRecipes();
        assertEquals(expResult, result);
    }

    /**
     * Test of addWeek method, of class RestaurantFacade.
     */
    @Test
    public void testAddWeek() {
        System.out.println("addWeek");
        WeekDTO weekDTO = new WeekDTO();

        List<RecipeDTO> expResult = new ArrayList();
        expResult.add(new RecipeDTO(risLaks));
        expResult.add(new RecipeDTO(pastaLaks));
        expResult.add(new RecipeDTO(kartoflerLaks));
        expResult.add(new RecipeDTO(tomatGulerod));
        expResult.add(new RecipeDTO(ostTomat));
        expResult.add(new RecipeDTO(ostKartofler));
        expResult.add(new RecipeDTO(pastaOst));

        weekDTO.setRecipe_list(expResult);
        weekDTO.setWeek_num(10);
        weekDTO.setYear_(2000);

        WeekDTO result = instance.addWeek(weekDTO);
        assertEquals(weekDTO, result);
    }

    /**
     * Test of getWeeks method, of class RestaurantFacade.
     */
    @Test
    public void testGetWeeks() {
        System.out.println("getWeeks");
        
        WeekDTO week = new WeekDTO();
        week.setWeek_num(20);
        week.setYear_(2020);
        
        List<RecipeDTO> expResult = new ArrayList();
        expResult.add(new RecipeDTO(risLaks));
        expResult.add(new RecipeDTO(pastaLaks));
        expResult.add(new RecipeDTO(kartoflerLaks));
        expResult.add(new RecipeDTO(tomatGulerod));
        expResult.add(new RecipeDTO(ostTomat));
        expResult.add(new RecipeDTO(ostKartofler));
        expResult.add(new RecipeDTO(pastaOst));
        week.setRecipe_list(expResult);
        
        List<WeekDTO> expectedResult = new ArrayList();
        expectedResult.add(week);
        
        List<WeekDTO> result = instance.getWeeks();
        assertEquals(expectedResult, result);
    }

    
    // Kl er nu 01. 
//    /**
//     * Test of addRecipe method, of class RestaurantFacade.
//     */
//    @Test
//    public void testAddRecipe() {
//        System.out.println("addRecipe");
//        RecipeDTO recipeDTO = null;
//        RestaurantFacade instance = null;
//        RecipeDTO expResult = null;
//        RecipeDTO result = instance.addRecipe(recipeDTO);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of editRecipe method, of class RestaurantFacade.
//     */
//    @Test
//    public void testEditRecipe() {
//        System.out.println("editRecipe");
//        RecipeDTO recipeDTO = null;
//        RestaurantFacade instance = null;
//        RecipeDTO expResult = null;
//        RecipeDTO result = instance.editRecipe(recipeDTO);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteRecipe method, of class RestaurantFacade.
//     */
//    @Test
//    public void testDeleteRecipe() {
//        System.out.println("deleteRecipe");
//        int id = 0;
//        RestaurantFacade instance = null;
//        RecipeDTO expResult = null;
//        RecipeDTO result = instance.deleteRecipe(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addIngredient method, of class RestaurantFacade.
//     */
//    @Test
//    public void testAddIngredient() {
//        System.out.println("addIngredient");
//        int itemId = 0;
//        int amount = 0;
//        int recipeId = 0;
//        RestaurantFacade instance = null;
//        IngredientDTO expResult = null;
//        IngredientDTO result = instance.addIngredient(itemId, amount, recipeId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
