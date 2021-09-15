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

    private void writeMachine() throws VendingMachinePersistenceException {

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

    private void loadMachine() throws VendingMachinePersistenceException {
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
        return new ArrayList(items.values());
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        return items.get(name);
    }

    @Override
    public void decreaseStock(Item item) throws VendingMachinePersistenceException {
        
        if(item.getStock()!=0){
            item.adjustStock(-1);
            items.put(item.getName(), item);
        }
        
    }

}
