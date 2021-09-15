/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dto.Change;
import dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author kavin
 */
public interface VendingMachineServiceLayer {
    
    List<Item> getAllItems();
    
    Item getItem(String name);
    
    Change purchaseItem(String code, BigDecimal money) throws InsufficientFundsException, NoItemInventoryException;
    
}
