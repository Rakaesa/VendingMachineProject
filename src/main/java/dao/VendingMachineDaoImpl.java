/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import service.NoItemInventoryException;

/**
 *
 * @author conno
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    public static final String DELIMITER = "::";
    private final String MACHINE_FILE;

    private Map<String, Item> items = new HashMap<>();

    public VendingMachineDaoImpl() {
        MACHINE_FILE = "vendingMachine.txt";
    }

    private String marshallItem(Item i) {
        return i.getName() + DELIMITER + i.getPrice() + DELIMITER + i.getStock();
    }
    
    public Item unMarshallItem(String itemInf){

        String[] itemVariables = itemInf.split(DELIMITER);
        Item newItem = new Item(itemVariables[0], Double.parseDouble(itemVariables[1]), Integer.parseInt(itemVariables[2]));
        return newItem;
    }

    public void writeMachine() throws VendingMachinePersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(MACHINE_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Failed to write item data.", e);
        }

        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item i : itemList) {

            itemAsText = marshallItem(i);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

    public void loadMachine() throws VendingMachinePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(MACHINE_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "Could not load items into memory.", e);
        }
        
        String currentLine;
        Item currentItem;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unMarshallItem(currentLine);

            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        VendingMachinePersistenceException persistenceException = new VendingMachinePersistenceException("Persistence exception: No items");
        if(items.values()!=null)
        return new ArrayList(items.values());
        else
            throw persistenceException;
            
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        
        VendingMachinePersistenceException pointer = new VendingMachinePersistenceException("Persistence Exception: Null entry");
        if(items.get(name)!=null)
            return items.get(name);
        else
            throw pointer;
    }

    @Override
    public void decreaseStock(Item item) throws VendingMachinePersistenceException, NoItemInventoryException {
        
        NoItemInventoryException inventoryException = new NoItemInventoryException("No items exception");
        
        if(item.getStock()!=0){
            item.adjustStock(-1);
            items.put(item.getName(), item);
        }
        else
            throw inventoryException;
    }

}
