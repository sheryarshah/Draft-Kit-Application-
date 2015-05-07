package wbdk.data;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The player class contains all the attributes for the hitters and pitchers
 *
 * @author Sheryar
 */
public class Player {

    //for hitters
    final StringProperty qp;
    final IntegerProperty ab;

    //for pitchers
    final DoubleProperty ip;
    final IntegerProperty er;
    final IntegerProperty bb;

    //combining from both hitteres and pitchers
    final IntegerProperty RW;
    final IntegerProperty HR_SV;
    final IntegerProperty RBIK;
    final DoubleProperty SBERA;
    final DoubleProperty BAWHIP;

    //attributes in both hitters and pitchers
    final IntegerProperty h;
    final StringProperty team;
    final StringProperty lastName;
    final StringProperty firstName;
    final StringProperty notes;
    final IntegerProperty yearOfBirth;
    final DoubleProperty estimatedValue;
    final StringProperty nation;

   // final IntegerProperty R;
//    final IntegerProperty HR;

    public Player() {

        //DEFAULTS VALUE IS N/A AND -1
        team = new SimpleStringProperty("N/A");
        lastName = new SimpleStringProperty("N/A");
        firstName = new SimpleStringProperty("N/A");
        notes = new SimpleStringProperty("N/A");
        nation = new SimpleStringProperty("N/A");
        yearOfBirth = new SimpleIntegerProperty(-1);
        h = new SimpleIntegerProperty(-1);
        estimatedValue = new SimpleDoubleProperty(-1.0);

        qp = new SimpleStringProperty("");
        ab = new SimpleIntegerProperty(-1);

        ip = new SimpleDoubleProperty(-1.0);
        er = new SimpleIntegerProperty(-1);
        bb = new SimpleIntegerProperty(-1);

        RW = new SimpleIntegerProperty(-1);
        HR_SV = new SimpleIntegerProperty(-1);
        RBIK = new SimpleIntegerProperty(-1);
        SBERA = new SimpleDoubleProperty(-1.0);
        BAWHIP = new SimpleDoubleProperty(-1.0);

      //  R = new SimpleIntegerProperty(-1);
      //  HR = new SimpleIntegerProperty(-1);

    }

    public String getTeam() {
        return team.get();
    }

    public void setTeam(String initTeam) {
        team.set(initTeam);
    }

    public StringProperty teamProperty() {
        return team;
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

    public String getQp() {
        return qp.get();
    }

    public void setQp(String initQp) {
        qp.set(initQp);
    }

    public StringProperty qpProperty() {
        return qp;
    }

    public int getAb() {
        return ab.get();
    }

    public void setAb(int initAb) {
        ab.set(initAb);
    }

    public IntegerProperty abProperty() {
        return ab;
    }

    public int getH() {
        return h.get();
    }

    public void setH(int initH) {
        h.set(initH);
    }

    public IntegerProperty hProperty() {
        return h;
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

    public double getIp() {
        return ip.get();
    }

    public void setIp(double initIp) {
        ip.set(initIp);
    }

    public DoubleProperty ipProperty() {
        return ip;
    }

    public int getEr() {
        return er.get();
    }

    public void setEr(int initEr) {
        er.set(initEr);
    }

    public IntegerProperty erProperty() {
        return er;
    }

    public int getBb() {
        return bb.get();
    }

    public void setBb(int initBb) {
        bb.set(initBb);
    }

    public IntegerProperty bbProperty() {
        return bb;
    }

    public int getK() {
        return bb.get();
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

 /*   public int getR() {
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
*/
}
