/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Objects;

/**
 *
 * @author conno
 */
public class Item extends Inventory{
    private String name;
    private double price;
    private Inventory itemInventory;
    
    public Item(String name, double price, dto.Inventory itemInventory) {
        this.name = name;
        this.price = price;
        this.itemInventory = itemInventory;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public dto.Inventory getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(dto.Inventory itemInventory) {
        this.itemInventory = itemInventory;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.itemInventory);
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
        final Item other = (Item) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.itemInventory, other.itemInventory)) {
            return false;
        }
        return true;
    }
    
    
}
