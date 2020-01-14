/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Item;
import java.util.Objects;

/**
 *
 * @author Malte
 */
public class ItemDTO {

    private int id;
    private String name;
    private String price_pr_kg; // Price in 1/100 dkk. ( Pris i ører )

    public ItemDTO() {
    }

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price_pr_kg = String.valueOf(item.getPrice_pr_kg());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice_pr_kg() {
        return price_pr_kg;
    }

    public void setPrice_pr_kg(String price_pr_kg) {
        this.price_pr_kg = price_pr_kg;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.price_pr_kg);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemDTO other = (ItemDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price_pr_kg, other.price_pr_kg)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemDTO{" + "id=" + id + ", name=" + name + ", price_pr_kg=" + price_pr_kg + '}';
    }

}
