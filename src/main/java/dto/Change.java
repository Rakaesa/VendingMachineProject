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
    private Map<String, Integer> change;
    private int totalChange;
    private int numQuarters = 0;
    private int numDimes = 0;
    private int numNickels = 0;
    private int numPennies = 0;

    public Change(int totalChange) {
        this.totalChange = totalChange;
        change = getChange(totalChange);
    }

    public Map<String, Integer> getChange() {
        return change;
    }

    public int getTotalChange() {
        return totalChange;
    }

    public int getNumQuarters() {
        return numQuarters;
    }

    public int getNumDimes() {
        return numDimes;
    }

    public int getNumNickels() {
        return numNickels;
    }

    public int getNumPennies() {
        return numPennies;
    }
    
    public Map<String, Integer> getChange(int changeDue){
        
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
