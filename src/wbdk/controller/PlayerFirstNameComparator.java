/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.controller;

import java.util.Comparator;
import wbdk.data.Player;

/**
 *
 * @author Sheryar
 */
public class PlayerFirstNameComparator implements Comparator<Player>{

    @Override
    public int compare(Player o1, Player o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }
    
}
