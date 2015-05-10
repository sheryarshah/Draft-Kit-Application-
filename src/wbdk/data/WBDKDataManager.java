/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.data;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static wbdk.WBDK_StartupConstants.JSON_FILE_PATH_HITTERS;
import static wbdk.WBDK_StartupConstants.JSON_FILE_PATH_PITCHERS;
import wbdk.file.JsonWBDKFileManager;
import wbdk.file.WBDKFileManager;
import wbdk.gui.WBDK_GUI;

/**
 * This class manages a Draft Data, which means it knows how to reset one with
 * default values.
 *
 * @author Sheryar
 */
public class WBDKDataManager {

    // THIS IS THE DRAFT BEING EDITED
    Draft draft;

    //THIS HELPS IN LOADING THE PLAYERS IN THE PLAYER SCREEN TABLE
    JsonWBDKFileManager jsonFileManager;

    // THIS IS THE UI, WHICH MUST BE UPDATED
    // WHENEVER OUR MODEL'S DATA CHANGES
    WBDKDataView view;

    // THIS HELPS US LOAD THINGS FOR OUR DRAFT
    WBDKFileManager fileManager;

    public WBDKDataManager(WBDKDataView initView) {
        view = initView;
        draft = new Draft();

    }

    /**
     * Accessor method for getting the Draft that this class manages.
     *
     * @return
     */
    public Draft getDraft() {
        return draft;
    }

    /**
     * Accessor method for getting the file manager, which knows how to read and
     * write draft data from/to files.
     *
     * @return
     */
    public WBDKFileManager getFileManager() {
        return fileManager;
    }

    /**
     * Resets the course to its default initialized settings, triggering the UI
     * to reflect these changes.
     */
    public void reset() {

        jsonFileManager = new JsonWBDKFileManager();

        for (int i = 0; i < draft.getTeam().size(); i++) {
            draft.getTeam().get(i).getTeamPlayers().clear();
        }
        draft.getPlayers().clear();
        draft.getTeam().clear();
        draft.getDraftPlayers().clear();
        draft.getTeam2().clear();
        draft.getTeam1().clear();

        try {
            //LOADING THE PLAYERS
            jsonFileManager.loadPitchers(getDraft(), JSON_FILE_PATH_PITCHERS);
        } catch (IOException ex) {
            Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            jsonFileManager.loadHitters(getDraft(), JSON_FILE_PATH_HITTERS);
        } catch (IOException ex) {
            Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        // AND THEN FORCE THE UI TO RELOAD THE UPDATED DRAFT
        //view.reloadDraft(draft);
        view.resetComboBox();
    }
}
