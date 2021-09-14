/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author pbott
 */
public class Inventory {
    private int stock;
    
    public void Inventory(int i){
        stock = i;
    }
    
    public void adjustStock(int i){
        stock += i;
    }
    
    public int getStock(){
        return stock;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.stock;
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
        final Inventory other = (Inventory) obj;
        if (this.stock != other.stock) {
            return false;
        }
        return true;
    }
}
