/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;
import dao.VendingMachinePersistenceException;
import dto.Change;
import dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 *
 * @author kavin
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry(getTime() + " | Called getAllItems()");
        return dao.getAllItems();
    }
    
    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry(getTime() + " | Called getItem()");
        return dao.getItem(name);
    }
    
    @Override
    public Change purchaseItem(String code, BigDecimal money) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        auditDao.writeAuditEntry(getTime() + " | Called purchaseItem()");
        Item item = dao.getItem(code);
        BigDecimal price = new BigDecimal(item.getPrice()).setScale(2, RoundingMode.FLOOR);
        if (money.compareTo(price) < 0) {
            throw new InsufficientFundsException("You didn't put in enough money!");
        }
        if (item.getStock() <= 0) {
            throw new NoItemInventoryException("We ran out of this item. Sorry!");
        }
        
        dao.decreaseStock(item);
        
        auditDao.writeAuditEntry(getTime() + " | " + item.getName() + " was purchased.");
        
        int totalPennies = new BigDecimal(100).multiply(money.subtract(price)).intValue();
        
        return new Change(totalPennies);
                
    }
    
    @Override
    public void writeMachine() throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry(getTime() + " | Attempt to write item data to file.");
        dao.writeMachine();
        auditDao.writeAuditEntry(getTime() + " | Item data was successfully written to file.");
    }
    
    @Override
    public void loadMachine() throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry(getTime() + " | Attempt to load item data from file.");
        dao.writeMachine();
        auditDao.writeAuditEntry(getTime() + " | Item data was successfuly loaded from file.");
    }
    
    private String getTime(){
        return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
    }
}
