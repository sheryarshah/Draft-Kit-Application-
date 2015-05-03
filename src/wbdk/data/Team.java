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
    final StringProperty q_p;

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

    String draftName;

    public Team() {
        positionPlaying = new SimpleStringProperty("N/A");
        lastName = new SimpleStringProperty("N/A");
        firstName = new SimpleStringProperty("N/A");
        proTeam = new SimpleStringProperty("N/A");
        q_p = new SimpleStringProperty("N/A");

        RW = new SimpleIntegerProperty(-1);
        HR_SV = new SimpleIntegerProperty(-1);
        RBIK = new SimpleIntegerProperty(-1);
        SBERA = new SimpleDoubleProperty(-1.0);
        BAWHIP = new SimpleDoubleProperty(-1.0);
        estimatedValue = new SimpleDoubleProperty(-1.0);

        nation = new SimpleStringProperty("N/A");

        contract = new SimpleStringProperty("N/A");
        salary = new SimpleIntegerProperty(-1);

        yearOfBirth = new SimpleIntegerProperty(-1);
        notes = new SimpleStringProperty("N/A");

        myTeamPlayers = FXCollections.observableArrayList();

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
        return q_p.get();
    }

    public void setQPosition(String initQPosition) {
        q_p.set(initQPosition);
    }

    public StringProperty qpProperty() {
        return q_p;
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

}
