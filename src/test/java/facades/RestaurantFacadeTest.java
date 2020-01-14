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

    public RestaurantFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = RestaurantFacade.getRestaurantFacade(emf);
        //  MAKE TEST DATA HERE
         //  MAKE TEST DATA HERE
         //  MAKE TEST DATA HERE
         //  MAKE TEST DATA HERE
        pasta = new Item("pasta", 1000);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //  PERSIST TEST DATA HERE
        em.persist(pasta);
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

    
}
