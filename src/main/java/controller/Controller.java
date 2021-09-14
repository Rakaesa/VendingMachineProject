/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import ui.UserIO;
import ui.UserIOConsoleFileImpl;

/**
 *
 * @author conno
 */
public class VendingMachineController {
    
    private VendingMachineView view;
    private UserIO io = new UserIOConsoleFileImpl();
    private VendingMachineServiceLayer service;
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    public void run()  {
        boolean keepGoing = true;
        int menuSelection = -1;
        int
        try {
            while (keepGoing) {
                // 1 = enter money, 2 = exit
                menuSelection = displayItemsGetSelection();

                switch (menuSelection) {
                    case 1:
                        enterMoneyAndSelectItem();
                        break;
                    case 2:
                        exitMessage();
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    /*
       Calls view to display the items and their respective prices.
       Returns 1 = enter money, 2 = exit
    */
    private int displayItemsGetSelection() {
        return view.displayItemsGetSelection();
    }
    
    private void enterMoneyAndSelectItem() throws VendingMachinePersistenceException {
        // assumes that getMoneyEntered prompts user for amount of money
        BigDecimal credit = new BigDecimal(view.getMoneyEntered()).setScale(2, HALF_UP);
        // assumes that getItemChoice user for item choice
        int itemChoice = view.getItemChoice();
        boolean hasErrors = false;
        do {
            try {
                // assumes purchaseItem throws InsufficientCreditException if the user doesn't have enough money
                // assumes displayErrorMessage informs the user if they have insufficient funds
                Change change = service.purchaseItem(itemChoice, credit);
                // assumes that displayPurchaseSuccess tells the user their change (if successful)
                view.displayPurchaseSuccess(change);
                hasErrors = false;
            } catch (VendingMachinePersistenceException | InsufficientCreditException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while(hasErrors);
   }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}