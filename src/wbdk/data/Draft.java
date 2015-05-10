package wbdk.data;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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
    ObservableList<Team> myTeam1;
    ObservableList<Team> myTeam2;

    ObservableList<Player> atl;
    ObservableList<Player> az;
    ObservableList<Player> chc;
    ObservableList<Player> cin;
    ObservableList<Player> col;
    ObservableList<Player> lad;
    ObservableList<Player> mia;
    ObservableList<Player> mil;
    ObservableList<Player> nym;
    ObservableList<Player> phi;
    ObservableList<Player> pit;
    ObservableList<Player> sd;
    ObservableList<Player> sf;
    ObservableList<Player> stl;
    ObservableList<Player> wsh;

    String draftName;

    ObservableList<Team> draftPlayers;

    Team t;
    Team t2;
    int catWin = 0;

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

        myTeam1 = FXCollections.observableArrayList();
        myTeam2 = FXCollections.observableArrayList();

        atl = FXCollections.observableArrayList();
        az = FXCollections.observableArrayList();
        chc = FXCollections.observableArrayList();
        cin = FXCollections.observableArrayList();
        col = FXCollections.observableArrayList();
        lad = FXCollections.observableArrayList();
        mia = FXCollections.observableArrayList();
        mil = FXCollections.observableArrayList();
        nym = FXCollections.observableArrayList();
        phi = FXCollections.observableArrayList();
        pit = FXCollections.observableArrayList();
        sd = FXCollections.observableArrayList();
        sf = FXCollections.observableArrayList();
        stl = FXCollections.observableArrayList();
        wsh = FXCollections.observableArrayList();

        draftPlayers = FXCollections.observableArrayList();

    }

    public ObservableList<Team> getDraftPlayers() {
        return draftPlayers;
    }

    public void addDraftPlayers(Team ti) {
        draftPlayers.add(ti);
    }

    public void removeDraftPlayers(Team draftPlayerToRemove) {
        draftPlayers.remove(draftPlayerToRemove);
    }

    public void clearDraftPlayers() {
        draftPlayers.clear();
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

    public ObservableList<Player> getATL() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getTeam().contains("ATL")) {
                addATL(players.get(i));
            }
        }
        return atl;
    }

    public void addATL(Player initATL) {
        atl.add(initATL);
    }

    public void clearATL() {
        atl.clear();
    }

    public ObservableList<Player> getAZ() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getTeam().contains("AZ")) {
                addAZ(players.get(i));
            }
        }
        return az;
    }

    public void addAZ(Player initAZ) {
        az.add(initAZ);
    }

    public void clearAZ() {
        az.clear();
    }

    public ObservableList<Player> getCHC() {
        for (Player player : players) {
            if (player.getTeam().contains("CHC")) {
                addCHC(player);
            }
        }
        return chc;
    }

    public void addCHC(Player initCHC) {
        chc.add(initCHC);
    }

    public void clearCHC() {
        chc.clear();
    }

    public ObservableList<Player> getCIN() {
        for (Player player : players) {
            if (player.getTeam().contains("CIN")) {
                addCIN(player);
            }
        }
        return cin;
    }

    public void addCIN(Player initCIN) {
        cin.add(initCIN);
    }

    public void clearCIN() {
        cin.clear();
    }

    public ObservableList<Player> getCOL() {
        for (Player player : players) {
            if (player.getTeam().contains("COL")) {
                addCOL(player);
            }
        }
        return col;
    }

    public void addCOL(Player initCOL) {
        col.add(initCOL);
    }

    public void clearCOL() {
        col.clear();
    }

    public ObservableList<Player> getLAD() {
        for (Player player : players) {
            if (player.getTeam().contains("LAD")) {
                addLAD(player);
            }
        }
        return lad;
    }

    public void addLAD(Player initLAD) {
        lad.add(initLAD);
    }

    public void clearLAD() {
        lad.clear();
    }

    public ObservableList<Player> getMIA() {
        for (Player player : players) {
            if (player.getTeam().contains("MIA")) {
                addMIA(player);
            }
        }
        return mia;
    }

    public void addMIA(Player initMIA) {
        mia.add(initMIA);
    }

    public void clearMIA() {
        mia.clear();
    }

    public ObservableList<Player> getMIL() {
        for (Player player : players) {
            if (player.getTeam().contains("MIL")) {
                addMIL(player);
            }
        }
        return mil;
    }

    public void addMIL(Player initMIL) {
        mil.add(initMIL);
    }

    public void clearMIL() {
        mil.clear();
    }

    public ObservableList<Player> getNYM() {
        for (Player player : players) {
            if (player.getTeam().contains("NYM")) {
                addNYM(player);
            }
        }
        return nym;
    }

    public void addNYM(Player initNYM) {
        nym.add(initNYM);
    }

    public void clearNYM() {
        nym.clear();
    }

    public ObservableList<Player> getPHI() {
        for (Player player : players) {
            if (player.getTeam().contains("PHI")) {
                addPHI(player);
            }
        }
        return phi;
    }

    public void addPHI(Player initPHI) {
        phi.add(initPHI);
    }

    public void clearPHI() {
        phi.clear();
    }

    public ObservableList<Player> getPIT() {
        for (Player player : players) {
            if (player.getTeam().contains("PIT")) {
                addPIT(player);
            }
        }
        return pit;
    }

    public void addPIT(Player initPIT) {
        pit.add(initPIT);
    }

    public void clearPIT() {
        pit.clear();
    }

    public ObservableList<Player> getSD() {
        for (Player player : players) {
            if (player.getTeam().contains("SD")) {
                addSD(player);
            }
        }
        return sd;
    }

    public void addSD(Player initSD) {
        sd.add(initSD);
    }

    public void clearSD() {
        sd.clear();
    }

    public ObservableList<Player> getSF() {
        for (Player player : players) {
            if (player.getTeam().contains("SF")) {
                addSF(player);
            }
        }
        return sf;
    }

    public void addSF(Player initSF) {
        sf.add(initSF);
    }

    public void clearSF() {
        sf.clear();
    }

    public ObservableList<Player> getSTL() {
        for (Player player : players) {
            if (player.getTeam().contains("STL")) {
                addSTL(player);
            }
        }
        return stl;
    }

    public void addSTL(Player initSTL) {
        stl.add(initSTL);
    }

    public void clearSTL() {
        stl.clear();
    }

    public ObservableList<Player> getWSH() {
        for (Player player : players) {
            if (player.getTeam().contains("WSH")) {
                addWSH(player);
            }
        }
        return wsh;
    }

    public void addWSH(Player initWSH) {
        wsh.add(initWSH);
    }

    public void clearWSH() {
        wsh.clear();
    }

    public void addTeam1(Team t1) {
        myTeam1.add(t1);
    }

    public ObservableList<Team> getTeam1() {

        DecimalFormat dF_ERA_WHIP = new DecimalFormat("#.00");
        dF_ERA_WHIP.setRoundingMode(RoundingMode.DOWN);
        
        DecimalFormat dF_BA = new DecimalFormat("#.00");
        dF_BA.setRoundingMode(RoundingMode.DOWN);
        
        for (int i = 0; i < getTeam().size(); i++) {
            int hrCounter = 0;
            int rCounter = 0;
            int rbiCounter = 0;
            double sbCounter = 0;
            double baCounter = 0;
            int wCounter = 0;
            int svCounter = 0;
            int kCounter = 0;
            double eraCounter = 0;
            double whipCounter = 0;
            int playerSize = 23;
            int salaryLeft = 260;
            double salaryPP = 0;

            playerSize -= getTeam().get(i).getTeamPlayers().size();

            t = new Team();
            t.setName(getTeam().get(i).getName());
            t.setPlayerSize(playerSize);
            t.setSalaryLeft(salaryLeft);

            for (int j = 0; j < getTeam().get(i).getTeamPlayers().size(); j++) {
                salaryLeft -= getTeam().get(i).getTeamPlayers().get(j).getSalary();
                if (playerSize != 0) {
                    salaryPP = salaryLeft / playerSize;
                }

                if (!getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("P")) {
                    rCounter += getTeam().get(i).getTeamPlayers().get(j).getRW();
                    hrCounter += getTeam().get(i).getTeamPlayers().get(j).getHR_SV();
                    rbiCounter += getTeam().get(i).getTeamPlayers().get(j).getRBIK();
                    sbCounter += getTeam().get(i).getTeamPlayers().get(j).getSBERA();
                    baCounter += getTeam().get(i).getTeamPlayers().get(j).getBAWHIP();
                    baCounter = Double.parseDouble(dF_BA.format(baCounter));
                } else {
                    wCounter += getTeam().get(i).getTeamPlayers().get(j).getRW();
                    svCounter += getTeam().get(i).getTeamPlayers().get(j).getHR_SV();
                    kCounter += getTeam().get(i).getTeamPlayers().get(j).getRBIK();
                    eraCounter += getTeam().get(i).getTeamPlayers().get(j).getSBERA();
                    eraCounter = Double.parseDouble(dF_ERA_WHIP.format(eraCounter));
                    whipCounter += getTeam().get(i).getTeamPlayers().get(j).getBAWHIP();
                    whipCounter = Double.parseDouble(dF_ERA_WHIP.format(whipCounter));
                }

                t.setSalaryLeft(salaryLeft);
                t.setSalaryPP(salaryPP);
                t.setR(rCounter);
                t.setHR(hrCounter);
                t.setRBI(rbiCounter);
                t.setSB(sbCounter);
                t.setBA(baCounter);
                t.setW(wCounter);
                t.setSV(svCounter);
                t.setK(kCounter);
                t.setERA(eraCounter);
                t.setWHIP(whipCounter);

            }

            addTeam1(t);
        }

        return myTeam1;
    }

    public void removeTeam1(Team teamToRemove1) {
        myTeam1.remove(teamToRemove1);
    }

    public void clearTeam1() {
        myTeam1.clear();
    }

    public void calculateTotalPoint() {

    }

    public ObservableList<Team> getTeam2() {
        ObservableList<Integer> r = FXCollections.observableArrayList();
        ObservableList<Integer> hr = FXCollections.observableArrayList();
        ObservableList<Integer> rbi = FXCollections.observableArrayList();
        ObservableList<Double> sb = FXCollections.observableArrayList();
        ObservableList<Double> ba = FXCollections.observableArrayList();
        ObservableList<Integer> w = FXCollections.observableArrayList();
        ObservableList<Integer> sv = FXCollections.observableArrayList();
        ObservableList<Integer> k1 = FXCollections.observableArrayList();
        ObservableList<Double> era = FXCollections.observableArrayList();
        ObservableList<Double> whip = FXCollections.observableArrayList();
        
        int catWin = 0;
        int size = getTeam().size();
        
        for (int k = 0; k < getTeam().size(); k++) {
            r.add(getTeam1().get(k).getR());
            hr.add(getTeam1().get(k).getHR());
            rbi.add(getTeam1().get(k).getRBI());
            sb.add(getTeam1().get(k).getSB());
            ba.add(getTeam1().get(k).getBA());
            w.add(getTeam1().get(k).getW());
            sv.add(getTeam1().get(k).getSV());
            k1.add(getTeam1().get(k).getK());
            era.add(getTeam1().get(k).getERA());
            whip.add(getTeam1().get(k).getWHIP());

        }

        for (int i = 0; i < getTeam().size(); i++) {
            t2 = new Team();
            t2.setName(getTeam1().get(i).getName());
            t2.setPlayerSize(getTeam1().get(i).getPlayerSize());
            t2.setSalaryLeft(getTeam1().get(i).getSalaryLeft());
            t2.setSalaryPP(getTeam1().get(i).getSalaryPP());
            t2.setR(getTeam1().get(i).getR());
            t2.setHR(getTeam1().get(i).getHR());
            t2.setRBI(getTeam1().get(i).getRBI());
            t2.setSB(getTeam1().get(i).getSB());
            t2.setBA(getTeam1().get(i).getBA());
            t2.setW(getTeam1().get(i).getW());
            t2.setSV(getTeam1().get(i).getSV());
            t2.setK(getTeam1().get(i).getK());
            t2.setERA(getTeam1().get(i).getERA());
            t2.setWHIP(getTeam1().get(i).getWHIP());

          //  Collections.sort(r);
            //  Collections.sort(hr);
            //  Collections.sort(rbi);
            //  Collections.sort(sb);
            if (t2.getR() > r.get(1)) {
                catWin++;
            }
            if (t2.getHR() > hr.get(1)) {
                catWin++;
            }
            if (t2.getRBI() > rbi.get(1)) {
                catWin++;
            }
            if (t2.getSB() > sb.get(1)) {
                catWin++;
            }
            if (t2.getBA() > sb.get(1)) {
                catWin++;
            }
            if (t2.getW() > w.get(1)) {
                catWin++;
            }
            if (t2.getSV() > sv.get(1)) {
                catWin++;
            }
            if (t2.getK() > k1.get(1)) {
                catWin++;
            }
            if (t2.getERA() > era.get(1)) {
                catWin++;
            }

            System.out.println(catWin);
            t2.setTotalPoints(10 * size);

            System.out.println(r + " " + hr + " " + rbi + " " + sb);

            addTeam2(t2);
            catWin = 1;
            size--;
        }

        return myTeam2;
    }

    public void addTeam2(Team t2) {
        myTeam2.add(t2);
    }

    public void removeTeam2(Team teamToRemove2) {
        myTeam2.remove(teamToRemove2);
    }

    public void clearTeam2() {
        myTeam2.clear();
    }

}
