/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.controller;

import java.io.IOException;
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
    int hr;

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

}
