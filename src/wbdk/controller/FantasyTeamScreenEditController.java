/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wbdk.WBDK_PropertyType.REMOVE_PLAYER_MESSAGE;
import static wbdk.WBDK_PropertyType.REMOVE_TEAM_MESSAGE;
import wbdk.data.Draft;
import wbdk.data.Player;
import wbdk.data.Team;
import wbdk.data.WBDKDataManager;
import wbdk.gui.FantasyTeamScreenDialog;
import wbdk.gui.MessageDialog;
import wbdk.sort.TeamPlayerComparator;
import wbdk.gui.WBDK_GUI;
import wbdk.gui.YesNoCancelDialog;

/**
 *
 * @author Sheryar
 */
public class FantasyTeamScreenEditController {

    FantasyTeamScreenDialog ftsd;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;

    // WE WANT TO KEEP TRACK OF WHEN SOMETHING HAS NOT BEEN SAVED
    private boolean saved;

    int counter = 0;

    public FantasyTeamScreenEditController(Stage initPrimaryStage, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        ftsd = new FantasyTeamScreenDialog(initPrimaryStage, initMessageDialog);
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
    }

    //ADDS THE TEAM
    public void handleAddTeamRequest(WBDK_GUI gui) throws IOException {
        WBDKDataManager pdm = gui.getDataManager();
        Draft team = pdm.getDraft();

        ftsd.showAddTeamDialog();

        // DID THE USER CONFIRM?
        if (ftsd.wasCompleteSelected1()) {
            // GET THE LECTURE
            // Player p = ftsd.getPlayer();
            Team t = ftsd.getTeam1();
            // AND ADD IT AS A ROW TO THE TABLE

            team.addTeam(t);

            gui.enableDrafting(true);

            //update toolbar
            gui.updateToolbarControls(saved);
        } else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
    }

    //REMOVES THE TEAM
    public void handleRemoveTeamRequest(WBDK_GUI gui, Team teamToRemove, int trackSelect, ComboBox<Team> teamSelectionCombo) throws IOException {
        WBDKDataManager dataManager = gui.getDataManager();
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_TEAM_MESSAGE));

        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) {
            for (int i = 0; i < dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().size(); i++) {
                Player p = new Player();
                p.setFirstName(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getFirstName());
                p.setLastName(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getLastName());
                p.setTeam(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getProTeam());
                p.setQp(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getQPosition());
                p.setYearOfBirth(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getYearOfBirth());
                p.setRW(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getRW());
                p.setHRSV(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getHR_SV());
                p.setRBIK(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getRBIK());
                p.setSBERA(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getSBERA());
                p.setBAWHIP(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getBAWHIP());
                p.setNotes(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getNotes());
                p.setNation(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getNation());
                p.setEstimatedValue(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i).getEstimatedValue());

                dataManager.getDraft().addPlayer(p);
            }

            for (int i = 0; i < dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().size(); i++) {
                Player p = new Player();
                p.setFirstName(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getFirstName());
                p.setLastName(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getLastName());
                p.setTeam(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getProTeam());
                p.setQp(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getQPosition());
                p.setYearOfBirth(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getYearOfBirth());
                p.setRW(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getRW());
                p.setHRSV(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getHR_SV());
                p.setRBIK(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getRBIK());
                p.setSBERA(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getSBERA());
                p.setBAWHIP(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getBAWHIP());
                p.setNotes(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getNotes());
                p.setNation(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getNation());
                p.setEstimatedValue(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers().get(i).getEstimatedValue());

                dataManager.getDraft().addPlayer(p);
            }

            dataManager.getDraft().getTeam().get(trackSelect).clearTeamPlayers();
            
            for (int i = 0; i < dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().size(); i++) {
                dataManager.getDraft().getDraftPlayers().get(i).clearTeamPlayers();
                dataManager.getDraft().removeDraftPlayers(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().get(i));
            }
            
            dataManager.getDraft().removeTeam(teamSelectionCombo.getSelectionModel().getSelectedItem());
            
            for(int i = 0; i < dataManager.getDraft().getTeam().size(); i++){
                for(int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++){
                    dataManager.getDraft().addDraftPlayers(dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j));
                }
            }

            
            

            for (int i = 0; i < dataManager.getDraft().getDraftPlayers().size(); i++) {
                dataManager.getDraft().getDraftPlayers().get(i).setPick(i + 1);
            }

            teamSelectionCombo.getItems().clear();

            for (int i = 0; i < dataManager.getDraft().getTeam().size(); i++) {
                teamSelectionCombo.getItems().add(dataManager.getDraft().getTeam().get(i));
            }
            teamSelectionCombo.getSelectionModel().selectFirst();

            //update toolbar
            gui.updateToolbarControls(false);
        }
    }

    //EDITS TEAM NAME AND OWNER
    public void handleEditTeamRequest(WBDK_GUI gui, Team teamToEdit) throws IOException {
        WBDKDataManager cdm = gui.getDataManager();
        // Course course = cdm.getCourse();
        ftsd.showEditTeamDialog(teamToEdit);

        // DID THE USER CONFIRM?
        if (ftsd.wasCompleteSelected()) {
            // UPDATE THE ASSIGNMENT
            Team t = ftsd.getTeam();
            teamToEdit.setName(t.getName());
            teamToEdit.setOwner(t.getOwner());
            //update toolbar
            gui.updateToolbarControls(saved);
        } else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
    }

    //EDITS THE PLAYER
    public void handleEditTeamPlayerRequest(WBDK_GUI gui, Team playerToEdit, int trackSelect, WBDKDataManager dataManager) throws IOException {
        WBDKDataManager pdm = gui.getDataManager();

        ftsd.showEditTeamPlayerDialog(playerToEdit, dataManager);

        // DID THE USER CONFIRM?
        if (ftsd.wasCompleteSelected()) {

            for (int i = 0; i < dataManager.getDraft().getDraftPlayers().size(); i++) {
                if (dataManager.getDraft().getDraftPlayers().get(i).getContract().equalsIgnoreCase("S1")) {
                    dataManager.getDraft().removeDraftPlayers(playerToEdit);
                }
            }

            for (int i = 0; i < dataManager.getDraft().getDraftPlayers().size(); i++) {
                dataManager.getDraft().getDraftPlayers().get(i).setPick(i + 1);
            }

            if (dataManager.getDraft().getTeam().get(ftsd.getI()).getTeamPlayers().size() >= 23) {
                dataManager.getDraft().getTeam().get(ftsd.getI()).addTaxiPlayers(ftsd.getTeam());
                dataManager.getDraft().getTeam().get(ftsd.getI()).getTaxiPlayers().sort(new TeamPlayerComparator());
                dataManager.getDraft().getTeam().get(trackSelect).removeTeamPlayers(playerToEdit);

            } else {
                dataManager.getDraft().getTeam().get(ftsd.getI()).addTeamPlayers(ftsd.getTeam());
                dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers().sort(new TeamPlayerComparator());
                dataManager.getDraft().getTeam().get(trackSelect).removeTeamPlayers(playerToEdit);
            }

            if (ftsd.getAccessPlayer().getContract().equalsIgnoreCase("S2")) {
                dataManager.getDraft().addDraftPlayers(ftsd.getTeam());
            }

            //update toolbar
            gui.updateToolbarControls(saved);
        } else {
            ftsd.removeTeamPlyer(dataManager);

        }
    }

}
