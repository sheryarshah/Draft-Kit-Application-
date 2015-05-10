/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Sheryar
 */
public class Team {

    String name;
    String owner;

    //combining from both hitteres and pitchers
    final IntegerProperty RW;
    final IntegerProperty HR_SV;
    final IntegerProperty RBIK;
    final DoubleProperty SBERA;
    final DoubleProperty BAWHIP;
    final StringProperty qp3;

    final StringProperty proTeam;
    final StringProperty lastName;
    final StringProperty firstName;
    final DoubleProperty estimatedValue;

    final StringProperty notes;
    final IntegerProperty yearOfBirth;

    final StringProperty contract;
    final IntegerProperty salary;
    final StringProperty positionPlaying;

    final StringProperty nation;

    ObservableList<Team> myTeamPlayers;
    ObservableList<Team> taxiPlayers;

    final IntegerProperty pick;

    String draftName;

    final IntegerProperty playerSize;
    final IntegerProperty salaryLeft;
    final DoubleProperty salaryPP;
    final IntegerProperty R;
    final IntegerProperty HR;
    final IntegerProperty RBI;
    final DoubleProperty SB;
    final DoubleProperty BA;
    final IntegerProperty W;
    final IntegerProperty SV;
    final IntegerProperty K;
    final DoubleProperty ERA;
    final DoubleProperty WHIP;
    final IntegerProperty totalPoints;

    public Team() {
        positionPlaying = new SimpleStringProperty("N/A");
        lastName = new SimpleStringProperty("N/A");
        firstName = new SimpleStringProperty("N/A");
        proTeam = new SimpleStringProperty("N/A");
        qp3 = new SimpleStringProperty("N/A");

        RW = new SimpleIntegerProperty(-1);
        HR_SV = new SimpleIntegerProperty(-1);
        RBIK = new SimpleIntegerProperty(-1);
        SBERA = new SimpleDoubleProperty(-1.0);
        BAWHIP = new SimpleDoubleProperty(-1.0);
        estimatedValue = new SimpleDoubleProperty(-1.0);

        nation = new SimpleStringProperty("N/A");

        contract = new SimpleStringProperty("N/A");
        salary = new SimpleIntegerProperty(0);

        yearOfBirth = new SimpleIntegerProperty(-1);
        notes = new SimpleStringProperty("N/A");

        myTeamPlayers = FXCollections.observableArrayList();
        taxiPlayers = FXCollections.observableArrayList();

        pick = new SimpleIntegerProperty(-1);

        playerSize = new SimpleIntegerProperty(-10);
        salaryLeft = new SimpleIntegerProperty(-10);
        salaryPP = new SimpleDoubleProperty(-10);
        R = new SimpleIntegerProperty(-10);
        HR = new SimpleIntegerProperty(-1);
        RBI = new SimpleIntegerProperty(-1);
        SB = new SimpleDoubleProperty(-1.0);
        BA = new SimpleDoubleProperty(-1.0);
        W = new SimpleIntegerProperty(-1);
        SV = new SimpleIntegerProperty(-1);
        K = new SimpleIntegerProperty(-1);
        ERA = new SimpleDoubleProperty(-1);
        WHIP = new SimpleDoubleProperty(-1);
        totalPoints = new SimpleIntegerProperty(-1);

    }

    public String getDraftName() {
        return draftName;
    }

    public void setDraftName(String draftName) {
        this.draftName = draftName;
    }

    public void addTeamPlayers(Team tp) {
        myTeamPlayers.add(tp);
    }

    public ObservableList<Team> getTeamPlayers() {
        return myTeamPlayers;
    }

    public void removeTeamPlayers(Team teamPlayerToRemove) {
        myTeamPlayers.remove(teamPlayerToRemove);
    }

    public void clearTeamPlayers() {
        myTeamPlayers.clear();
    }

    public void addTaxiPlayers(Team ti) {
        taxiPlayers.add(ti);
    }

    public ObservableList<Team> getTaxiPlayers() {
        return taxiPlayers;
    }

    public void removeTaxiPlayers(Team taxiPlayerToRemove) {
        taxiPlayers.remove(taxiPlayerToRemove);
    }

    public void clearTaxiPlayers() {
        taxiPlayers.clear();
    }

    public int getPick() {
        return pick.get();
    }

    public void setPick(int initPick) {
        pick.set(initPick);
    }

    public IntegerProperty pickProperty() {
        return pick;
    }

    public double getEstimatedValue() {
        return estimatedValue.get();
    }

    public void setEstimatedValue(double initEstimatedValue) {
        estimatedValue.set(initEstimatedValue);
    }

    public DoubleProperty estimatedValue() {
        return estimatedValue;
    }

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String initNotes) {
        notes.set(initNotes);
    }

    public StringProperty notesProperty() {
        return notes;
    }

    public int getYearOfBirth() {
        return yearOfBirth.get();
    }

    public void setYearOfBirth(int initYearOfBirth) {
        yearOfBirth.set(initYearOfBirth);
    }

    public IntegerProperty yearOfBirthProperty() {
        return yearOfBirth;
    }

    public String getNation() {
        return nation.get();
    }

    public void setNation(String initNation) {
        nation.set(initNation);
    }

    public StringProperty nationProperty() {
        return nation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPositionPlaying() {
        return positionPlaying.get();
    }

    public void setPositionPlaying(String initPositionPlaying) {
        positionPlaying.set(initPositionPlaying);
    }

    public StringProperty positionPlayingProperty() {
        return positionPlaying;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String initLastName) {
        lastName.set(initLastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String initFirstName) {
        firstName.set(initFirstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getProTeam() {
        return proTeam.get();
    }

    public void setProTeam(String initProTeam) {
        proTeam.set(initProTeam);
    }

    public StringProperty proTeamProperty() {
        return proTeam;
    }

    public String getQPosition() {
        return qp3.get();
    }

    public void setQPosition(String initQPosition) {
        qp3.set(initQPosition);
    }

    public StringProperty qpProperty() {
        return qp3;
    }

    public int getRW() {
        return RW.get();
    }

    public void setRW(int initRW) {
        RW.set(initRW);
    }

    public IntegerProperty rwProperty() {
        return RW;
    }

    public int getHR_SV() {
        return HR_SV.get();
    }

    public void setHRSV(int initHRSV) {
        HR_SV.set(initHRSV);
    }

    public IntegerProperty hrswProperty() {
        return HR_SV;
    }

    public int getRBIK() {
        return RBIK.get();
    }

    public void setRBIK(int initRBIK) {
        RBIK.set(initRBIK);
    }

    public IntegerProperty rbikProperty() {
        return RBIK;
    }

    public double getSBERA() {
        return SBERA.get();
    }

    public void setSBERA(double initSBERA) {
        SBERA.set(initSBERA);
    }

    public DoubleProperty sberaProperty() {
        return SBERA;
    }

    public double getBAWHIP() {
        return BAWHIP.get();
    }

    public void setBAWHIP(double initBAWHIP) {
        BAWHIP.set(initBAWHIP);
    }

    public DoubleProperty bawhipProperty() {
        return BAWHIP;
    }

    public String getContract() {
        return contract.get();
    }

    public void setContract(String initContract) {
        contract.set(initContract);
    }

    public StringProperty contractProperty() {
        return contract;
    }

    public int getSalary() {
        return salary.get();
    }

    public void setSalary(int initSalary) {
        salary.set(initSalary);
    }

    public IntegerProperty salaryProperty() {
        return salary;
    }

    public int getPlayerSize() {
        return playerSize.get();
    }

    public void setPlayerSize(int initPlayerSize) {
        playerSize.set(initPlayerSize);
    }

    public IntegerProperty playerSizeProperty() {
        return playerSize;
    }

    public int getSalaryLeft() {
        return salaryLeft.get();
    }

    public void setSalaryLeft(int initSalaryLeft) {
        salaryLeft.set(initSalaryLeft);
    }

    public IntegerProperty salaryLeftProperty() {
        return salaryLeft;
    }

    public double getSalaryPP() {
        return salaryPP.get();
    }

    public void setSalaryPP(double initSalaryPP) {
        salaryPP.set(initSalaryPP);
    }

    public DoubleProperty salaryPPProperty() {
        return salaryPP;
    }

    public int getR() {
        return R.get();
    }

    public void setR(int initR) {
        R.set(initR);
    }

    public IntegerProperty rProperty() {
        return R;
    }

    public int getHR() {
        return HR.get();
    }

    public void setHR(int initHR) {
        HR.set(initHR);
    }

    public IntegerProperty hrProperty() {
        return HR;
    }

    public int getRBI() {
        return RBI.get();
    }

    public void setRBI(int initRBI) {
        RBI.set(initRBI);
    }

    public IntegerProperty rbiProperty() {
        return RBI;
    }

    public double getSB() {
        return SB.get();
    }

    public void setSB(double initSB) {
        SB.set(initSB);
    }

    public DoubleProperty sbProperty() {
        return SB;
    }

    public double getBA() {
        return BA.get();
    }

    public void setBA(double initBA) {
        BA.set(initBA);
    }

    public DoubleProperty baProperty() {
        return BA;
    }

    public int getW() {
        return W.get();
    }

    public void setW(int initW) {
        W.set(initW);
    }

    public IntegerProperty wProperty() {
        return W;
    }

    public int getSV() {
        return SV.get();
    }

    public void setSV(int initSV) {
        SV.set(initSV);
    }

    public IntegerProperty svProperty() {
        return SV;
    }

    public int getK() {
        return HR.get();
    }

    public void setK(int initK) {
        K.set(initK);
    }

    public IntegerProperty kProperty() {
        return K;
    }

    public double getERA() {
        return ERA.get();
    }

    public void setERA(double initERA) {
        ERA.set(initERA);
    }

    public DoubleProperty eraProperty() {
        return ERA;
    }

    public double getWHIP() {
        return WHIP.get();
    }

    public void setWHIP(double initWHIP) {
        WHIP.set(initWHIP);
    }

    public DoubleProperty whipProperty() {
        return WHIP;
    }

    public int getTotalPoints() {
        return totalPoints.get();
    }

    public void setTotalPoints(int initTotalPoints) {
        totalPoints.set(initTotalPoints);
    }

    public IntegerProperty totalPointsProperty() {
        return totalPoints;
    }

    
    
}
