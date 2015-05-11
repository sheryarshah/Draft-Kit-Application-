package wbdk.data;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wbdk.sort.SortBA;
import wbdk.sort.SortERA;
import wbdk.sort.SortHR;
import wbdk.sort.SortK;
import wbdk.sort.SortR;
import wbdk.sort.SortRBI;
import wbdk.sort.SortSB;
import wbdk.sort.SortSV;
import wbdk.sort.SortW;
import wbdk.sort.SortWHIP;

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

    int hitterSize = 0, pitcherSize = 0;

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
        for (Player t : players) {
            if (t.getQp().contains("C")) {
                addCatcher(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("CHC")) {
                addCHC(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("CIN")) {
                addCIN(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("COL")) {
                addCOL(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("LAD")) {
                addLAD(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("MIA")) {
                addMIA(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("MIL")) {
                addMIL(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("NYM")) {
                addNYM(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("PHI")) {
                addPHI(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("PIT")) {
                addPIT(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("SD")) {
                addSD(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("SF")) {
                addSF(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("STL")) {
                addSTL(t);
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
        for (Player t : players) {
            if (t.getTeam().contains("WSH")) {
                addWSH(t);
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

        DecimalFormat dF_ERA_WHIP = new DecimalFormat("#.00");
        dF_ERA_WHIP.setRoundingMode(RoundingMode.DOWN);

        DecimalFormat dF_BA = new DecimalFormat("#.000");
        dF_BA.setRoundingMode(RoundingMode.DOWN);

        int catWin1 = 0;
        int catWin2 = 0;
        int RWin = 0, HRWin = 0, RBIWin = 0, SBWin = 0, BAWin = 0;
        int WWin = 0, SVWin = 0, KWin = 0, ERAWIn = 0, WHIPWin = 0;

        int size = getTeam().size();

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

            r.add(t.getR());
            hr.add(t.getHR());
            rbi.add(t.getRBI());
            sb.add(t.getSB());
            ba.add(t.getBA());
            w.add(t.getW());
            sv.add(t.getSV());
            k1.add(t.getK());
            era.add(t.getERA());
            whip.add(t.getWHIP());

            Collections.sort(r);
            Collections.sort(hr);
            Collections.sort(rbi);
            Collections.sort(sb);
            Collections.sort(ba);
            Collections.sort(w);
            Collections.sort(sv);
            Collections.sort(k1);
            Collections.sort(era);
            Collections.sort(whip);

            /*  if (getTeam().size() > 0) {
             for (int j = 0; j < getTeam().size(); j++) {
             // if () {

             //  }
             }
             if (t.getR() > r.get(0)) {
             catWin1++;
             } else {
             catWin2++;
             }
             if (t.getHR() > hr.get(0)) {
             catWin1++;
             } else {
             catWin2++;
             }
             if (t.getRBI() > rbi.get(0)) {
             catWin1++;
             } else {
             catWin2++;
             }
             if (t.getSB() > sb.get(0)) {
             catWin1++;
             } else {
             catWin2++;
             }
             if (t.getBA() > ba.get(0)) {
             catWin1++;
             } else {
             catWin2++;
             }
             if (t.getW() > w.get(0)) {
             catWin1++;
             } else {
             catWin2++;
             }
             if (t.getSV() > sv.get(0)) {
             catWin1++;
             } else {
             catWin2++;
             }
             if (t.getK() > k1.get(0)) {
             catWin1++;
             } else {
             catWin2++;
             }
             if (t.getERA() > era.get(0)) {
             catWin1++;
             } else {
             catWin2++;
             }
             if (t.getWHIP() > whip.get(0)) {
             catWin1++;
             } else {
             catWin2++;
             }
             } else {
             t.setTotalPoints(10 * size);
             }

             if (getTeam().size() == 1) {
             t.setTotalPoints(10 * size);
             } else {
             if (catWin1 > catWin2) {
             t.setTotalPoints(catWin1 * size);
             } else {
             t.setTotalPoints(catWin2 * size);
             }

             }*/
            addTeam1(t);
            size--;
            catWin1 = 0;
            catWin2 = 0;

        }

        return myTeam1;
    }

    public void removeTeam1(Team teamToRemove1) {
        myTeam1.remove(teamToRemove1);
    }

    public void clearTeam1() {
        myTeam1.clear();
    }

    public void setTeamPoints() {
        int size = getTeam().size();
        for (int i = 0; i < getTeam().size(); i++) {
            int RWin = 0, HRWin = 0, RBIWin = 0, SBWin = 0, BAWin = 0;
            int WWin = 0, SVWin = 0, KWin = 0, ERAWin = 0, WHIPWin = 0;
            int totalPoint = 0;

            for (int j = 0; j < getTeam().size(); j++) {
                if (myTeam1.get(j).getR() > myTeam1.get(i).getR()) {
                    RWin++;
                }
                if (myTeam1.get(j).getHR() > myTeam1.get(i).getHR()) {
                    HRWin++;
                }
                if (myTeam1.get(j).getRBI() > myTeam1.get(i).getRBI()) {
                    RBIWin++;
                }
                if (myTeam1.get(j).getSB() > myTeam1.get(i).getSB()) {
                    SBWin++;
                }
                if (myTeam1.get(j).getBA() > myTeam1.get(i).getBA()) {
                    BAWin++;
                }
                if (myTeam1.get(j).getW() > myTeam1.get(i).getW()) {
                    WWin++;
                }
                if (myTeam1.get(j).getSV() > myTeam1.get(i).getSV()) {
                    SVWin++;
                }
                if (myTeam1.get(j).getK() > myTeam1.get(i).getK()) {
                    KWin++;
                }
                if (myTeam1.get(j).getERA() > myTeam1.get(i).getERA()) {
                    ERAWin++;
                }
                if (myTeam1.get(j).getWHIP() > myTeam1.get(i).getWHIP()) {
                    WHIPWin++;
                }

                totalPoint = RWin + HRWin + RBIWin + SBWin + BAWin
                        + WWin + SVWin + KWin + ERAWin + WHIPWin;
                myTeam1.get(j).setTotalPoints(myTeam1.get(j).getTotalPoints() + totalPoint * size);
            }

            if (!myTeam.get(i).getTeamPlayers().isEmpty()) {
                if (myTeam.size() == 1) {
                    myTeam1.get(i).setTotalPoints(10);
                } else {
                    //  myTeam1.get(i).setTotalPoints(myTeam1.get(i).getTotalPoints() + myTeam.size() * totalPoint);
                }

            }
        }
    }

    public void addTeam2(Team t2) {
        myTeam2.add(t2);
    }

    public ObservableList<Team> getTeam2() {

        return myTeam2;
    }

    public void removeTeam2(Team teamToRemove2) {
        myTeam2.remove(teamToRemove2);
    }

    public void clearTeam2() {
        myTeam2.clear();
    }

    public void setEstimatedValue() {
        int totalSalary = 0;

        DecimalFormat dF_Estimated_Value = new DecimalFormat("#.000");
        dF_Estimated_Value.setRoundingMode(RoundingMode.DOWN);

        if (!getTeam().isEmpty()) {
            for (int i = 0; i < getTeam().size(); i++) {
                totalSalary += getTeam1().get(i).getSalaryLeft();
                ObservableList<String> positions = FXCollections.observableArrayList();
                int posC = 0, pos2B = 0, B3count = 0, pos1B = 0, posMI = 0,
                        posCI = 0, posOF = 0, posP = 0, posSS = 0, posU = 0;
                for (int j = 0; j < getTeam().get(i).getTeamPlayers().size(); j++) {
                    Team t = getTeam().get(i).getTeamPlayers().get(j);
                    switch (t.getPositionPlaying()) {
                        case "C":
                            posC++;
                            break;
                        case "1B":
                            pos1B++;
                            hitterSize++;
                            break;
                        case "2B":
                            pos2B++;
                            hitterSize++;
                            break;
                        case "3B":
                            B3count++;
                            hitterSize++;
                            break;
                        case "MI":
                            posMI++;
                            hitterSize++;
                            break;
                        case "CI":
                            posCI++;
                            hitterSize++;
                            break;
                        case "U":
                            posU++;
                            hitterSize++;
                            break;
                        case "SS":
                            posSS++;
                            hitterSize++;
                            break;
                        case "OF":
                            posOF++;
                            hitterSize++;
                            break;
                        case "P":
                            posP++;
                            pitcherSize++;
                            break;
                    }
                }
                if (posC < 2) {
                    positions.add("C");
                }
                if (pos1B < 1) {
                    positions.add("1B");
                }
                if (pos2B < 1) {
                    positions.add("2B");
                }
                if (B3count < 1) {
                    positions.add("3B");
                }
                if (posMI < 1) {
                    positions.add("MI");
                }
                if (posCI < 1) {
                    positions.add("CI");
                }
                if (posSS < 1) {
                    positions.add("SS");
                }
                if (posU < 1) {
                    positions.add("U");
                }
                if (posSS < 1) {
                    positions.add("SS");
                }
                if (posOF < 5) {
                    positions.add("OF");
                }
                if (posP < 9) {
                    positions.add("P");
                }
            }
            for (int i = 0; i < getPlayers().size(); i++) {
                if (players.get(i).getQp().equals("P")) {
                    pitchers.add(players.get(i));
                } else {
                    hitters.add(players.get(i));
                }
            }
            
            pitchers.sort(new SortW());
            hitters.sort(new SortR());

            int rankCount = 0;

            for (Player hitter : hitters) {
                rankCount++;
                hitter.setRankR(rankCount);
            }

            pitchers.sort(new SortK());
            hitters.sort(new SortHR());

            rankCount = 0;
            for (Player pitcher : pitchers) {
                rankCount++;
                pitcher.setRankK(rankCount);
            }
            rankCount = 0;
            for (Player hitter : hitters) {
                rankCount++;
                hitter.setRankHR(rankCount);
            }

            pitchers.sort(new SortSV());
            hitters.sort(new SortSB());

            rankCount = 0;
            for (Player pitcher : pitchers) {
                rankCount++;
                pitcher.setRankSV(rankCount);
            }
            rankCount = 0;
            for (Player hitter : hitters) {
                rankCount++;
                hitter.setRankSB(rankCount);
            }

            pitchers.sort(new SortERA());
            hitters.sort(new SortRBI());

            rankCount = 0;
            for (Player pitcher : pitchers) {
                rankCount++;
                pitcher.setRankERA(rankCount);
            }
            rankCount = 0;
            for (Player hitter : hitters) {
                rankCount++;
                hitter.setRankRBI(rankCount);
            }
            pitchers.sort(new SortWHIP());
            hitters.sort(new SortBA());

            rankCount = 0;
            for (Player pitcher : pitchers) {
                rankCount++;
                pitcher.setRankWHIP(rankCount);
            }
            rankCount = 0;
            for (Player hitter : hitters) {
                rankCount++;
                hitter.setRankBA(rankCount);
            }

            hitterSize = getTeam().size() * 14 - hitterSize;
            pitcherSize = getTeam().size() * 9 - pitcherSize;

            for (Player pitcher : pitchers) {
                int value = pitcher.getRankW() + pitcher.getRankK() + pitcher.getRankSV() + pitcher.getRankERA() + pitcher.getRankWHIP();
                double rank = value / 5;
                double salary = (totalSalary / (2 * pitcherSize));
                double estimatedValue = salary * ((pitchers.size() * 2) / rank);
                pitcher.setAverageRank(rank);
                pitcher.setEstimatedValue(Double.parseDouble(dF_Estimated_Value.format(estimatedValue)));
            }

            for (Player hitter : hitters) {
                int value = hitter.getRankR() + hitter.getRankHR() + hitter.getRankRBI() + hitter.getRankSB() + hitter.getRankBA();
                double rank = (double) value / 5;
                double salary = (totalSalary / (2 * hitterSize));
                double estimatedValue = (double) salary * ((hitters.size() * 2) / rank);
                hitter.setAverageRank(rank);
                hitter.setEstimatedValue(Double.parseDouble(dF_Estimated_Value.format(estimatedValue)));
            }

            getPlayers().clear();
            players.addAll(hitters);
            players.addAll(pitchers);
            hitterSize = 0;
            pitcherSize = 0;
        }

    }
}
