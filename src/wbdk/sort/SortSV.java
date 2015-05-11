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
public class SortSV implements Comparator<Player>{
    
     @Override
    public int compare(Player o1, Player o2) {
        int j = 0;
        if(o2.getQp().equalsIgnoreCase("P") || o1.getQp().equalsIgnoreCase("P")){
            j = o2.getHR_SV() - o1.getHR_SV();
        }
        return j;
    }
    
}
