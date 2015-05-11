/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.sort;

import java.util.Comparator;
import wbdk.data.Player;

/**
 *
 * @author Sheryar
 */
public class PlayerLastNameComparator implements Comparator<Player>{
    int j;
    
    @Override
    public int compare(Player o1, Player o2) {
        
        j = o1.getLastName().compareTo(o2.getLastName());
        if(j == 0){
            j = o1.getFirstName().compareTo(o2.getFirstName());
        }
        return j;
    }
}
