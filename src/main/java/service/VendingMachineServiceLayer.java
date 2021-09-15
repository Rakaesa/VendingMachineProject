/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.VendingMachinePersistenceException;
import dto.Change;
import dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kavin
 */
public interface VendingMachineServiceLayer {
    
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    Item getItem(String name) throws VendingMachinePersistenceException;
    
    Change purchaseItem(String code, BigDecimal money) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException;
    
    void writeMachine() throws VendingMachinePersistenceException;
      
    void loadMachine() throws VendingMachinePersistenceException;
}
