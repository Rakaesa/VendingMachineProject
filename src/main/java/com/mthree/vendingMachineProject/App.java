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
import dao.VendingMachinePersistenceException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

    public static void main(String[] args) throws VendingMachinePersistenceException {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
            appContext.scan("com.mthree.vendingMachineProject");
            appContext.refresh();
        // Instantiate the UserIO implementation
        UserIO myIo = appContext.getBean("userIOConsoleFileImpl", UserIOConsoleFileImpl.class);
        // Instantiate the View and wire the UserIO implementation into it
        VendingMachineView myView = appContext.getBean("vendingMachineView", VendingMachineView.class);
        // Instantiate the DAO
        VendingMachineDao myDao = appContext.getBean("vendingMachineDaoImpl", VendingMachineDaoImpl.class);
        // Instantiate the Audit DAO
        VendingMachineAuditDao myAuditDao = appContext.getBean("vendingMachineAuditDaoImpl", VendingMachineAuditDaoImpl.class);
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        VendingMachineServiceLayer myService = appContext.getBean("vendingMachineServiceLayerImpl", VendingMachineServiceLayerImpl.class);
        // Instantiate the Controller and wire the Service Layer into it
        VendingMachineController controller = appContext.getBean("vendingMachineController", VendingMachineController.class);
        // Kick off the Controller
        controller.run();
    }  
}