/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.sort;

import java.util.Comparator;
import wbdk.data.Player;
import wbdk.data.Team;

/**
 *
 * @author Sheryar
 */
public class SortR implements Comparator<Player>{


    @Override
    public int compare(Player o1, Player o2) {
        
        return o2.getRW() - o1.getRW();
    }
    
}
