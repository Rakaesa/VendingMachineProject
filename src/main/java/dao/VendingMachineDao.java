/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import java.util.List;
import service.NoItemInventoryException;

/**
 *
 * @author conno
 */
public interface VendingMachineDao {
    
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    Item getItem(String name) throws VendingMachinePersistenceException;
    
    void decreaseStock(Item item) throws VendingMachinePersistenceException, NoItemInventoryException;

}
