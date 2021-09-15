/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.VendingMachinePersistenceException;
import dto.Change;
import dto.Item;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import java.util.List;
import service.InsufficientFundsException;
import service.NoItemInventoryException;
import service.VendingMachineServiceLayer;
import ui.UserIO;
import ui.UserIOConsoleFileImpl;
import ui.VendingMachineView;

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
    public void run() throws VendingMachinePersistenceException {
        boolean keepGoing = true;
        service.loadMachine();
        int menuSelection = -1;
        try {
            while (keepGoing) {
                // 1 = enter money, 2 = exit
                menuSelection = displayItemsGetSelection();

                switch (menuSelection) {
                    case 1:
                        enterMoneyAndSelectItem();
                        service.writeMachine();
                        break;
                    case 2:
                        exitMessage();
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
        } catch (VendingMachinePersistenceException | InsufficientFundsException | NoItemInventoryException e) {
            keepGoing = false;
            view.displayErrorMessage(e.getMessage());
            
        }
    }
    /*
       Calls view to display the items and their respective prices.
       Returns 1 = enter money, 2 = exit
    */
    private int displayItemsGetSelection() throws VendingMachinePersistenceException {
        List<Item> items = service.getAllItems();
        return view.displayItemsGetSelection(items);
    }
    
    private void enterMoneyAndSelectItem() throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException {
        // assumes that getMoneyEntered prompts user for amount of money
        BigDecimal credit = new BigDecimal(view.getMoneyEntered()).setScale(2, HALF_UP);
        // assumes that getItemChoice user for item choice
        List<Item> items = service.getAllItems();
        String itemChoice = view.getItemChoice(items);
        try {
            // assumes purchaseItem throws InsufficientCreditException if the user doesn't have enough money
            // assumes displayErrorMessage informs the user if they have insufficient funds
            Change change = service.purchaseItem(itemChoice, credit);
            // assumes that displayPurchaseSuccess tells the user their change (if successful)
            view.displayPurchaseSuccess(change);


        } catch (VendingMachinePersistenceException | InsufficientFundsException | NoItemInventoryException e) {

            view.displayErrorMessage(e.getMessage());
        }

   }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}