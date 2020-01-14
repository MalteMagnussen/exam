/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Storage;
import java.util.Objects;

/**
 *
 * @author Malte
 */
public class StorageDTO {

    private ItemDTO item;
    private int amount;
    private int id;

    public StorageDTO() {
    }

    public StorageDTO(ItemDTO item, int amount, int id) {
        this.item = item;
        this.amount = amount;
        this.id = id;
    }

    public StorageDTO(Storage storage) {
        this.item = new ItemDTO(storage.getItem());
        this.amount = storage.getAmount();
        this.id = storage.getId();
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.item);
        hash = 41 * hash + this.amount;
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
        final StorageDTO other = (StorageDTO) obj;
        if (this.amount != other.amount) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StorageDTO{" + "item=" + item + ", amount=" + amount + ", id=" + id + '}';
    }

}
