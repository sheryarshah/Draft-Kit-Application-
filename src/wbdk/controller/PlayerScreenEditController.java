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
import wbdk.gui.TeamPlayerComparator;
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
            counter++;
            if (counter > 23) {
                dataManager.getDraft().getTeam().get(psd.getI()).addTaxiPlayers(psd.getTeam());
                dataManager.getDraft().getTeam().get(psd.getI()).getTaxiPlayers().sort(new TeamPlayerComparator());
                dataManager.getDraft().removePlayer(playerToEdit);

            } else {
                dataManager.getDraft().getTeam().get(psd.getI()).addTeamPlayers(psd.getTeam());
                dataManager.getDraft().getTeam().get(psd.getI()).getTeamPlayers().sort(new TeamPlayerComparator());
                dataManager.getDraft().removePlayer(playerToEdit);
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

    public void handleSelectPlayerRequest(WBDK_GUI gui, Player player, WBDKDataManager dataManager, int pickCounter, int teamC) throws IOException {
        Team t = new Team();

        ObservableList<String> position = FXCollections.observableArrayList();
        String positions = player.getQp();

        if (positions.contains("P")) {
            for (int j = 0; j < dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size(); j++) {
                if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("P")) {
                    positionP_counter++;

                }
            }
            if (position3B_counter <= 9) {
                position.add("P");
            }
            if (positionP_counter > 1) {
                positionP_counter = 0;
            }
        }

        t.setPositionPlaying(positions);
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
        t.setNotes(player.getNotes());
        t.setNation(player.getNation());
        t.setPick(pickCounter);
        t.setName(dataManager.getDraft().getTeam().get(teamC).getName());

        if (dataManager.getDraft().getTeam().get(teamC) != null) {
            t.setContract("S2");
            t.setSalary(1);
            if (dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().size() < 23) {
                dataManager.getDraft().getTeam().get(teamC).addTeamPlayers(t);
                dataManager.getDraft().getTeam().get(teamC).getTeamPlayers().sort(new TeamPlayerComparator());
                dataManager.getDraft().removePlayer(player);
            } else {
                t.setContract("X");
                t.setSalary(0);
                dataManager.getDraft().getTeam().get(teamC).addTaxiPlayers(t);
                dataManager.getDraft().getTeam().get(teamC).getTaxiPlayers().sort(new TeamPlayerComparator());
                dataManager.getDraft().removePlayer(player);
            }
        }

        dataManager.getDraft().addDraftPlayers(t);

        /*  if (counter1 > 23) {
         dataManager.getDraft().getTeam().get(0).addTaxiPlayers(t);
         dataManager.getDraft().getTeam().get(0).getTaxiPlayers().sort(new TeamPlayerComparator());
         dataManager.getDraft().removePlayer(player);

         } else {
         dataManager.getDraft().getTeam().get(0).addTeamPlayers(t);
         dataManager.getDraft().getTeam().get(0).getTeamPlayers().sort(new TeamPlayerComparator());
         dataManager.getDraft().removePlayer(player);
         }*/
        gui.updateToolbarControls(saved);

    }

}
