/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author pbott
 */
public class VendingMachine {
    private Map mapOfItems;
    
    public void vendingMachine(Map<String, Item> itemsToLoad){
        mapOfItems = itemsToLoad;
    }

    public Map getMapOfItems() {
        return mapOfItems;
    }

    public void setMapOfItems(Map mapOfItems) {
        this.mapOfItems = mapOfItems;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.mapOfItems);
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
        final VendingMachine other = (VendingMachine) obj;
        if (!Objects.equals(this.mapOfItems, other.mapOfItems)) {
            return false;
        }
        return true;
    }
}
