/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.controller;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wbdk.WBDK_PropertyType.INCOMPLETE_FIELD_MESSAGE;
import static wbdk.WBDK_PropertyType.NEW_DRAFT_CREATED_MESSAGE;
import static wbdk.WBDK_PropertyType.REMOVE_PLAYER_MESSAGE;
import wbdk.data.Draft;
import wbdk.data.Player;
import wbdk.data.Team;
import wbdk.data.WBDKDataManager;
import wbdk.gui.MessageDialog;
import wbdk.gui.PlayerScreenDialog;
import wbdk.sort.TeamPlayerComparator;
import wbdk.gui.WBDK_GUI;
import wbdk.gui.YesNoCancelDialog;

/**
 *
 * @author Sheryar
 */
public class PlayerScreenEditController {

    PlayerScreenDialog psd;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;

    Team t = new Team();

    int trackSelect;
    int counter = 0;
    int counter1 = 0;
    int hr;

    int positionC_counter = 0;
    int position1B_counter = 0;
    int positionCI_counter = 0;
    int position3B_counter = 0;
    int position2B_counter = 0;
    int positionMI_counter = 0;
    int positionSS_counter = 0;
    int positionU_counter = 0;
    int positionOF_counter = 0;
    int positionP_counter = 0;
    boolean checkPlayerPos = false;

    boolean MIFlag = true;
    boolean CIFlag = true;
    boolean CFlag = true;
    boolean Flag1B = true;
    boolean Flag2B = true;
    boolean Flag3B = true;
    boolean FlagSS = true;
    boolean FlagU = true;
    boolean FlagOF = true;
    boolean stop = true, stop1 = true, stop2 = true, stop3 = true;

    boolean startTaxi = false;

    int pickCounter1 = 0;

    // WE WANT TO KEEP TRACK OF WHEN SOMETHING HAS NOT BEEN SAVED
    private boolean saved;

    public PlayerScreenEditController() {

    }

    public PlayerScreenEditController(Stage initPrimaryStage, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        psd = new PlayerScreenDialog(initPrimaryStage, initMessageDialog);
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
    }

    //ADDS THE LECTURE
    public void handleAddPlayerRequest(WBDK_GUI gui) throws IOException {
        WBDKDataManager pdm = gui.getDataManager();
        Draft draft = pdm.getDraft();
        psd.showAddPlayerDialog();

        // DID THE USER CONFIRM?
        if (psd.wasCompleteSelected()) {

            // GET THE LECTURE
            Player p = psd.getPlayer();

            // AND ADD IT AS A ROW TO THE TABLE
            draft.addPlayer(p);

            //update toolbar
            gui.updateToolbarControls(saved);
        } else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
    }

    public void setTrackSelect(int trackSelect) {
        this.trackSelect = trackSelect;
    }

    public int getTrackSelect() {
        return trackSelect;
    }

    //EDITS THE PLAYER
    public void handleEditPlayerRequest(WBDK_GUI gui, Player playerToEdit, WBDKDataManager dataManager, int select) throws IOException {
        WBDKDataManager pdm = gui.getDataManager();
        psd.showEditPlayerDialog(playerToEdit, dataManager);

        // DID THE USER CONFIRM?
        if (psd.wasCompleteSelected()) {
            if (dataManager.getDraft().getTeam().get(psd.getI()).getTeamPlayers().size() >= 23) {
                dataManager.getDraft().getTeam().get(psd.getI()).addTaxiPlayers(psd.getTeam());
                dataManager.getDraft().getTeam().get(psd.getI()).getTaxiPlayers().sort(new TeamPlayerComparator());
                dataManager.getDraft().removePlayer(playerToEdit);
            } else {
                dataManager.getDraft().getTeam().get(psd.getI()).addTeamPlayers(psd.getTeam());
                dataManager.getDraft().getTeam().get(psd.getI()).getTeamPlayers().sort(new TeamPlayerComparator());
                dataManager.getDraft().removePlayer(playerToEdit);

                if (psd.getTeam().getContract().equalsIgnoreCase("S2")) {
                    dataManager.getDraft().addDraftPlayers(psd.getTeam());
                }

            }

            for (int i = 0; i < dataManager.getDraft().getDraftPlayers().size(); i++) {
                dataManager.getDraft().getDraftPlayers().get(i).setPick(i + 1);
                dataManager.getDraft().getDraftPlayers().get(i).setName(dataManager.getDraft().getTeam().get(psd.getI()).getName());
            }

            //update toolbar
            gui.updateToolbarControls(saved);
        } else {
            psd.removeTeamPlyer(dataManager);
        }
    }

    //REMOVES THE PLAYER 
    public void handleRemovePlayerRequest(WBDK_GUI gui, Player playerToRemove) throws IOException {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_PLAYER_MESSAGE));

        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) {
            gui.getDataManager().getDraft().removePlayer(playerToRemove);

            //update toolbar
            gui.updateToolbarControls(saved);
        }
    }

    public void handleSelectPlayerRequest(Player player, WBDKDataManager dataManager, int pickCounter, int teamC) throws IOException {

        checkPlayerPos = false;

        if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size() >= 23) {
            checkPlayerPos = true;
        }
        positionC_counter = 0;
        position1B_counter = 0;
        position3B_counter = 0;
        position2B_counter = 0;
        positionSS_counter = 0;
        positionOF_counter = 0;
        positionU_counter = 0;
        positionP_counter = 0;
        positionMI_counter = 0;
        positionCI_counter = 0;

        t = new Team();

        String positions = player.getQp();

        //System.out.println(positions);
        resetPositionFlag(teamC);

        if (!startTaxi) {
            if (CFlag) {
                if (positions.contains("C")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("C")) {
                            positionC_counter++;
                        }
                    }
                    if (positionC_counter < 2) {
                        t.setPositionPlaying("C");
                        checkPlayerPos = true;
                    } else {
                        checkPlayerPos = false;
                        CFlag = false;
                    }

                }
            }

            if (!CIFlag && Flag1B) {
                if (positions.contains("1B")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("1B")) {
                            position1B_counter++;

                        }
                    }
                    if (position1B_counter < 1) {
                        t.setPositionPlaying("1B");
                        checkPlayerPos = true;
                    } else {
                        checkPlayerPos = false;
                        Flag1B = false;
                    }

                }
            }

            if (!CIFlag && Flag3B) {
                if (positions.contains("3B")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("3B")) {
                            position3B_counter++;

                        }
                    }
                    if (position3B_counter < 1) {
                        t.setPositionPlaying("3B");
                        checkPlayerPos = true;
                    } else {
                        checkPlayerPos = false;
                        Flag3B = false;
                    }

                }
            }

            if (!MIFlag && Flag2B) {
                if (positions.contains("2B")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("2B")) {
                            position2B_counter++;

                        }
                    }
                    if (position2B_counter < 1) {
                        t.setPositionPlaying("2B");
                        checkPlayerPos = true;
                    } else {
                        checkPlayerPos = false;
                        Flag2B = false;
                    }

                }
            }

            if (!MIFlag && FlagSS) {
                if (positions.contains("SS")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("SS")) {
                            positionSS_counter++;
                        }
                    }
                    if (positionSS_counter < 1) {
                        t.setPositionPlaying("SS");
                        checkPlayerPos = true;
                    } else {
                        checkPlayerPos = false;
                        FlagSS = false;
                    }

                }
            }

            if (FlagOF) {
                if (positions.contains("OF")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("OF")) {
                            positionOF_counter++;

                        }
                    }
                    if (positionOF_counter < 5) {
                        t.setPositionPlaying("OF");
                        checkPlayerPos = true;
                    } else {
                        checkPlayerPos = false;
                        FlagOF = false;
                    }

                }
            }

            if (FlagU) {
                if (positions.contains("U")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("U")) {
                            positionU_counter++;

                        }
                    }
                    if (positionU_counter < 1) {
                        t.setPositionPlaying("U");
                    } else {
                        checkPlayerPos = false;
                        FlagU = false;
                    }

                }
            }

            if (positions.contains("P")) {
                for (int j = 0; j < dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size(); j++) {
                    if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("P")) {
                        positionP_counter++;

                    }
                }
                if (positionP_counter < 9) {
                    t.setPositionPlaying("P");
                    checkPlayerPos = true;
                } else {
                    checkPlayerPos = false;
                }

            }

            if (MIFlag) {
                if (positions.contains("2B") || positions.contains("SS")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("MI")) {
                            positionMI_counter++;

                        }
                    }
                    if (positionMI_counter < 1) {
                        t.setPositionPlaying("MI");
                        checkPlayerPos = true;
                    } else {
                        checkPlayerPos = false;
                        MIFlag = false;
                    }

                }
            }

            if (CIFlag) {
                if (positions.contains("1B") || positions.contains("3B")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("CI")) {
                            positionCI_counter++;

                        }
                    }
                    if (positionCI_counter < 1) {
                        t.setPositionPlaying("CI");
                        checkPlayerPos = true;
                    } else {
                        checkPlayerPos = false;
                        CIFlag = false;
                    }
                }
            }
        }

        if (checkPlayerPos) {
            pickCounter1++;
            t.setContract("S2");
            t.setSalary(1);
            t.setFirstName(player.getFirstName());
            t.setLastName(player.getLastName());
            t.setProTeam(player.getTeam());
            t.setQPosition(player.getQp());
            t.setYearOfBirth(player.getYearOfBirth());
            t.setRW(player.getRW());
            t.setHRSV(player.getHR_SV());
            t.setRBIK(player.getRBIK());
            t.setSBERA(player.getSBERA());
            t.setBAWHIP(player.getBAWHIP());
            t.setEstimatedValue(player.getEstimatedValue());
            t.setNotes(player.getNotes());
            t.setNation(player.getNation());

            // t.setPick(pickCounter1);
            t.setName(dataManager.getDraft().getTeam().get(teamC).getName());

            if (startTaxi) {
                t.setPositionPlaying(positions);
            }

            if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size() < 23) {
                dataManager.getDraft().getTeam().get(teamC).addTeamPlayers(t);
                dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().sort(new TeamPlayerComparator());
                dataManager.getDraft().removePlayer(player);
            } else {

                startTaxi = true;
                t.setContract("X");
                t.setSalary(0);
                dataManager.getDraft().getTeam().get(teamC).addTaxiPlayers(t);
                dataManager.getDraft().getTeam().get(teamC).getTaxiPlayers().sort(new TeamPlayerComparator());
                dataManager.getDraft().removePlayer(player);
            }

            dataManager.getDraft().addDraftPlayers(t);
            for (int i = 0; i < dataManager.getDraft().getDraftPlayers().size(); i++) {
                dataManager.getDraft().getDraftPlayers().get(i).setPick(i + 1);
            }

        }

    }

    public void resetPositionFlag(int teamC) {
        if (stop) {
            if (teamC > 0) {

                MIFlag = true;
                CIFlag = true;
                CFlag = true;
                Flag1B = true;
                Flag2B = true;
                Flag3B = true;
                FlagSS = true;
                FlagU = true;
                FlagOF = true;
                stop = false;
            }
        }

        if (stop1) {
            if (teamC > 1) {

                MIFlag = true;
                CIFlag = true;
                CFlag = true;
                Flag1B = true;
                Flag2B = true;
                Flag3B = true;
                FlagSS = true;
                FlagU = true;
                FlagOF = true;
                stop1 = false;
            }
        }

        if (stop2) {
            if (teamC > 2) {

                MIFlag = true;
                CIFlag = true;
                CFlag = true;
                Flag1B = true;
                Flag2B = true;
                Flag3B = true;
                FlagSS = true;
                FlagU = true;
                FlagOF = true;
                stop2 = false;
            }
        }

        if (stop3) {
            if (teamC > 3) {

                MIFlag = true;
                CIFlag = true;
                CFlag = true;
                Flag1B = true;
                Flag2B = true;
                Flag3B = true;
                FlagSS = true;
                FlagU = true;
                FlagOF = true;
                stop3 = false;
            }
        }
    }

}
