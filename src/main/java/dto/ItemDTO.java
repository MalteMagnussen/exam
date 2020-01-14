/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Item;

/**
 *
 * @author Malte
 */
public class ItemDTO {

    private int id;
    private String name;
    private int kg_price; // Price in 1/100 dkk. ( Pris i ører )

    public ItemDTO() {
    }

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.kg_price = item.getPrice_pr_kg();
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

    public int getKg_price() {
        return kg_price;
    }

    public void setKg_price(int kg_price) {
        this.kg_price = kg_price;
    }

}
