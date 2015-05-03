package wbdk.data;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Draft class is used where all the players all selected, added, edited or
 * removed.
 *
 * @author Sheryar
 */
public class Draft {

    ObservableList<Player> players;
    ObservableList<Player> pitchers;
    ObservableList<Player> hitters;
    ObservableList<Player> catchers;
    ObservableList<Player> firstBaseman;
    ObservableList<Player> secondBaseman;
    ObservableList<Player> thirdBaseman;
    ObservableList<Player> cornerInFielder;
    ObservableList<Player> midInField;
    ObservableList<Player> outFielder;
    ObservableList<Player> shortStop;

    ObservableList<Team> myTeam;
    
    Team t = new Team();
    
    String draftName;

    public Draft() {
        players = FXCollections.observableArrayList();
        pitchers = FXCollections.observableArrayList();
        hitters = FXCollections.observableArrayList();
        catchers = FXCollections.observableArrayList();
        firstBaseman = FXCollections.observableArrayList();
        secondBaseman = FXCollections.observableArrayList();
        thirdBaseman = FXCollections.observableArrayList();
        cornerInFielder = FXCollections.observableArrayList();
        midInField = FXCollections.observableArrayList();
        outFielder = FXCollections.observableArrayList();
        shortStop = FXCollections.observableArrayList();
        myTeam = FXCollections.observableArrayList();

    }

    public String getDraftName() {
        return draftName;
    }

    public void setDraftName(String draftName) {
        this.draftName = draftName;
       // t.setDraftName(draftName);
    }
    
    public void addTeam(Team t) {
        myTeam.add(t);
    }

    public ObservableList<Team> getTeam() {
        return myTeam;
    }

    public void removeTeam(Team teamToRemove) {
        myTeam.remove(teamToRemove);
    }

    public void clearTeam() {
        myTeam.clear();
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public ObservableList<Player> getPlayers() {
        return players;
    }

    public void removePlayer(Player playerToRemove) {
        players.remove(playerToRemove);
    }

    public void clearPlayers() {
        players.clear();
    }
    
    public void addPitcher(Player pi) {
        pitchers.add(pi);
    }

    public void clearPitchers() {
        pitchers.clear();
    }

    public ObservableList<Player> getPitchers() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getQp().contains("P")) {
                addPitcher(players.get(i));
            }
        }
        return pitchers;
    }

    public void addHitter(Player hi) {
        hitters.add(hi);
    }

    public void clearHitters() {
        hitters.clear();
    }

    public ObservableList<Player> getHitters() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getQp().contains("U")) {
                addHitter(players.get(i));
            }
        }
        return hitters;
    }

    public void addCatcher(Player c) {
        catchers.add(c);
    }

    public void clearCatchers() {
        catchers.clear();
    }

    public ObservableList<Player> getCatchers() {
        for (Player player : players) {
            if (player.getQp().contains("C")) {
                addCatcher(player);
            }
        }
        return catchers;
    }

    public void addFirstBaseman(Player b1) {
        firstBaseman.add(b1);
    }

    public void clearFirstBaseman() {
        firstBaseman.clear();
    }

    public ObservableList<Player> getFirstBaseman() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getQp().contains("1B")) {
                addFirstBaseman(players.get(i));
            }
        }
        return firstBaseman;
    }

    public void addSecondBaseman(Player b2) {
        secondBaseman.add(b2);
    }

    public void clearSecondBaseman() {
        secondBaseman.clear();
    }

    public ObservableList<Player> getSecondBaseman() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getQp().contains("2B")) {
                addSecondBaseman(players.get(i));
            }
        }
        return secondBaseman;
    }

    public void addThirdBaseman(Player b3) {
        thirdBaseman.add(b3);
    }

    public void clearThirdBaseman() {
        thirdBaseman.clear();
    }

    public ObservableList<Player> getThirdBaseman() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getQp().contains("3B")) {
                addThirdBaseman(players.get(i));
            }
        }
        return thirdBaseman;
    }

    public void addCornerInField(Player ci) {
        cornerInFielder.add(ci);
    }

    public void clearCornerInField() {
        cornerInFielder.clear();
    }

    public ObservableList<Player> getCornerInField() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getQp().contains("1B") || players.get(i).getQp().contains("3B")) {
                addCornerInField(players.get(i));
            }
        }
        return cornerInFielder;
    }

    public void addMidInField(Player mi) {
        midInField.add(mi);
    }

    public void clearMidInField() {
        midInField.clear();
    }

    public ObservableList<Player> getMidInField() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getQp().contains("SS") || players.get(i).getQp().contains("2B")) {
                addMidInField(players.get(i));
            }
        }
        return midInField;
    }

    public void addOutFielder(Player of) {
        outFielder.add(of);
    }

    public void clearOutFielder() {
        outFielder.clear();
    }

    public ObservableList<Player> getOutFielder() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getQp().contains("OF")) {
                addOutFielder(players.get(i));
            }
        }
        return outFielder;
    }

    public void addShortStop(Player ss) {
        shortStop.add(ss);
    }

    public void clearShortStop() {
        shortStop.clear();
    }

    public ObservableList<Player> getShortStop() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getQp().contains("SS")) {
                addShortStop(players.get(i));
            }
        }
        return shortStop;
    }

}
