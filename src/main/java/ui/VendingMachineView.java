/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dto.Change;
import dto.Item;
import java.util.List;

/**
 *
 * @author Sol
 */
public class VendingMachineView {

    private UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int displayItemsGetSelection(List<Item> items) {
        io.print("Vending Machine:");
        displayItems(items);
        return io.readInt("Please select: 1 = Enter Money. 2. Exit", 1, 2);
    }
    public double getMoneyEntered() {
        double credit = io.readDouble("Please enter your money:");
        return credit;
    }
    private void displayItems(List<Item> items) {
        int j = 1;
        for (Item i : items) {
            io.print(j + ". " + i.getName() + ", $" + i.getPrice());
            j++;
        }
     }
    public String getItemChoice(List<Item> items) {
        displayItems(items);
        int choice = io.readInt("Please select an item from 1-5", 1, 5);
        String item = new String();
        switch (choice) {
            case 1:
                item = "Snickers";
                break;
            case 2:
                item = "Kit Kat";
                break;
            case 3:
                item = "Mountain Dew";
                break;
            case 4:
                item = "Doritos";
                break;
            case 5:
                item = "Coca Cola";
                break;
        }
        return item;
    }
    public void displayPurchaseSuccess(Change change) {
        int numQuarters = change.getNumQuarters();
        int numDimes = change.getNumDimes();
        int numNickels = change.getNumNickels();
        int numPennies = change.getNumPennies();
        int total = change.getTotalChange();
        io.print("Your change is:");
        io.print("Quarters: " + numQuarters);
        io.print("Dimes: " + numDimes);
        io.print("Nickels: " + numNickels);
        io.print("Pennies: " + numPennies);
        io.print("Total: " + total);
    }
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
}