/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.gui;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import wbdk.data.Team;

/**
 *
 * @author Sheryar
 */
public class TeamPlayerComparator implements Comparator<Team> {

    //sort these items in this order to the front of the list 
    private static List<String> ORDER = Arrays.asList("C", "1B", "CI", "3B", "2B", "MI", "SS",
            "OF", "U", "P");

    @Override
    public int compare(Team o1, Team o2) {
        int result = 0;
        int o1Index = ORDER.indexOf(o1.getPositionPlaying());
        int o2Index = ORDER.indexOf(o2.getPositionPlaying());
        if (o1Index < 0 && o2Index < 0) {
            result = o1.getPositionPlaying().compareTo(o2.getPositionPlaying());
        } else if (o1Index < 0) {
            result = 1;
        } else if (o2Index < 0) {
            result = -1;
        } else {
            result = o1Index - o2Index;
        }
        return result;
    }

}
