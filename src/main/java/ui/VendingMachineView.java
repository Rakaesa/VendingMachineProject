/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dto.Change;

/**
 *
 * @author Sol
 */
public class VendingMachineView {

    private UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int displayItemsGetSelection() {
        io.print("Vending Machine:");
        io.print("1. Snickers");
        io.print("2. Kit Kat");
        io.print("3. Mountain Dew");
        io.print("4. Doritos");
        io.print("5. Coca Cola");

        return io.readInt("Please select: 1 = Enter Money. 2. Exit", 1, 2);
    }
    public double getMoneyEntered() {
        double credit = io.readDouble("Please enter your money:");
        return credit;
    }
    
    public String getItemChoice() {
        io.print("Vending Machine:");
        io.print("1. Snickers, $2.00");
        io.print("2. Kit Kat, $2.00");
        io.print("3. Mountain Dew, $2.00");
        io.print("4. Doritos, $1.50");
        io.print("5. Coca Cola, $2.00");
        
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