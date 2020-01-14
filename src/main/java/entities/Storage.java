/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.StorageDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Malte
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Storage.getAll", query = "SELECT h FROM Storage h"),
    @NamedQuery(name = "Storage.deleteAllRows", query = "DELETE FROM Storage"),
    @NamedQuery(name = "Storage.findStorage", query = "SELECT h FROM Storage h WHERE h.item = (SELECT y FROM Item y WHERE y.id = :id)")
})
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Item item;
    private int amount;

    public Storage() {
    }

    public Storage(int amount) {
        this.amount = amount;
    }

    public Storage(StorageDTO storageDTO) {
        this.amount = storageDTO.getAmount();
        this.item = new Item(storageDTO.getItem());
        this.id = storageDTO.getId();
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
