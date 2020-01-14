/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.ItemDTO;
import dto.StorageDTO;
import entities.Ingredient;
import entities.Item;
import entities.Recipe;
import entities.Storage;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

}
