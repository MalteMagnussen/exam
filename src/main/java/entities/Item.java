/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.ItemDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Malte
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Item.getAll", query = "SELECT h FROM Item h"),
    @NamedQuery(name = "Item.deleteAllRows", query = "DELETE FROM Item")
})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int price_pr_kg; // Price in 1/100 dkk. ( Pris i ører )

    public Item() {
    }

    public Item(ItemDTO itemDTO) {
        this.id = itemDTO.getId();
        this.name = itemDTO.getName();
        this.price_pr_kg = Integer.parseInt(itemDTO.getPrice_pr_kg());
    }

    /**
     * Get the value of price_pr_kg
     *
     * @return the value of price_pr_kg
     */
    public int getPrice_pr_kg() {
        return price_pr_kg;
    }

    /**
     * Set the value of price_pr_kg
     *
     * @param price_pr_kg new value of price_pr_kg
     */
    public void setPrice_pr_kg(int price_pr_kg) {
        this.price_pr_kg = price_pr_kg;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.item[ id=" + id + " ]";
    }

}
