/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Component;

/**
 *
 * @author conno
 */
@Component
public class VendingMachineAuditDaoImpl implements VendingMachineAuditDao{
    
    public static final String AUDIT_FILE = "audit.txt";

   
        public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
            PrintWriter out;
       
            try {
                out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
            } catch (IOException e) {
                throw new VendingMachinePersistenceException("Could not write audit information.", e);
            }
 
            LocalDateTime timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            out.println(timestamp.toString() + " | " + entry);
            out.flush();
    }
}
