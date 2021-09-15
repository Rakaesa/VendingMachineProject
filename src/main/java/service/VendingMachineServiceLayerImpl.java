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
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }
    
    @Override
    public Item getItem(String name){
        return dao.getItem(name);
    }
    
    @Override
    public Change purchaseItem(String code, BigDecimal money) throws InsufficientFundsException, NoItemInventoryException {
        Item item = getItem(code);
        BigDecimal price = new BigDecimal(item.getPrice()).setScale(2, RoundingMode.FLOOR);
        if (price.compareTo(money) < 0)
            throw new InsufficientFundsException("You didn't put in enough money!");
        if (item.getStock() <= 0)
            throw new NoItemInventoryException("We ran out of this item. Sorry!");

        return new dao.decreaseStock(item, money);
    }
}
