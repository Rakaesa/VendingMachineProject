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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author kavin
 */
@Component
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    @Autowired
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("Called getAllItems()");
        return dao.getAllItems();
    }
    
    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("Called getItem()");
        return dao.getItem(name);
    }
    
    @Override
    public Change purchaseItem(String code, BigDecimal money) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        auditDao.writeAuditEntry("Called purchaseItem()");
        Item item = dao.getItem(code);
        BigDecimal price = new BigDecimal(item.getPrice()).setScale(2, RoundingMode.HALF_UP);
        if (money.compareTo(price) < 0) {
            throw new InsufficientFundsException("You didn't put in enough money!");
        }
        if (item.getStock() <= 0) {
            throw new NoItemInventoryException("We ran out of this item. Sorry!");
        }
        
        dao.decreaseStock(item);
        
        auditDao.writeAuditEntry(item.getName() + " was purchased.");
        
        int totalPennies = new BigDecimal(100).multiply(money.subtract(price)).intValue();

        return new Change(totalPennies);
                
    }
    
    @Override
    public void writeMachine() throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("Attempt to write item data to file.");
        dao.writeMachine();
        auditDao.writeAuditEntry("Item data was successfully written to file.");
    }
    
    @Override
    public void loadMachine() throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry("Attempt to load item data from file.");
        dao.loadMachine();
        auditDao.writeAuditEntry("Item data was successfuly loaded from file.");
    }
}
