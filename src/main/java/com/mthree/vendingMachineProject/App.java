/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.vendingMachineProject;

import controller.VendingMachineController;
import dao.VendingMachineAuditDao;
import dao.VendingMachineAuditDaoImpl;
import dao.VendingMachineDao;
import dao.VendingMachineDaoImpl;
import service.VendingMachineServiceLayer;
import service.VendingMachineServiceLayerImpl;
import ui.UserIO;
import ui.UserIOConsoleFileImpl;
import ui.VendingMachineView;

/**
 *
 * @author conno
 */
public class App {

    public static void main(String[] args) {
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleFileImpl();
        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView myView = new VendingMachineView(myIo);
        // Instantiate the DAO
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        // Instantiate the Audit DAO
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController controller = new VendingMachineController(myService, myView);
        // Kick off the Controller
        controller.run();
    }  
}