/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pbott
 */
public class Change {
    
    public static Map<String, Integer> getChange(int changeDue){
        int numQuarters = 0;
        int numDimes = 0;
        int numNickels = 0;
        int numPennies = 0;
        int total = changeDue;
        Map<String, Integer> changeToReturn = new HashMap<>();
        while(changeDue > 25){
                numQuarters+=1;
                total -=25; 
        }
        while(changeDue > 10){
                numDimes+=1;
                total -=10; 
        }
        while(changeDue > 5){
                numNickels+=1;
                total -=5; 
        }
        numPennies = total;
        
        changeToReturn.put("Quarters", numQuarters);
        changeToReturn.put("Dimes", numDimes);
        changeToReturn.put("Nickels", numNickels);
        changeToReturn.put("Pennies", numPennies);
        changeToReturn.put("Total", changeDue);
        
        return changeToReturn;
    }
}
