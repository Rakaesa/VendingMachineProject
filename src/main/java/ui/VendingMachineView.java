/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dto.Change;
import dto.Item;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sol
 */

@Component
public class VendingMachineView {

    private UserIO io;
    @Autowired
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
            BigDecimal price = new BigDecimal(i.getPrice()).setScale(2, HALF_UP);
            io.print(j + ". " + i.getName() + ", $" + price + " # Remaining: " + i.getStock());
            j++;
        }
     }
    public String getItemChoice(List<Item> items) {
        displayItems(items);
        int numItems = items.size();
        int choice = io.readInt("Please select an item from 1-" + numItems, 1, numItems);
        String item = new String();
        item = items.get(choice-1).getName();
        return item;
    }
    public void displayPurchaseSuccess(Change change) {
        int numQuarters = change.getNumQuarters();
        int numDimes = change.getNumDimes();
        int numNickels = change.getNumNickels();
        int numPennies = change.getNumPennies();
        io.print("Your change is:");
        io.print("Quarters: " + numQuarters);
        io.print("Dimes: " + numDimes);
        io.print("Nickels: " + numNickels);
        io.print("Pennies: " + numPennies);
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