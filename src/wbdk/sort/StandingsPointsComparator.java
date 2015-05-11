/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.sort;

import java.util.Comparator;
import wbdk.data.Team;

/**
 *
 * @author Sheryar
 */
public class StandingsPointsComparator implements Comparator<Team> {

    @Override
    public int compare(Team o1, Team o2) {
        return o2.getTotalPoints() - o1.getTotalPoints();
    }

}
