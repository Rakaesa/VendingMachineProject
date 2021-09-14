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
        while(changeDue > 0){
            if(changeDue > 25){
                numQuarters+=1;
                total -=25;
            }
                
        }
        
        return changeToReturn;
    }
}
