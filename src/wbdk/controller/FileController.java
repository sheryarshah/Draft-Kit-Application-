package wbdk.controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import properties_manager.PropertiesManager;
import static wbdk.WBDK_PropertyType.DRAFT_SAVED_MESSAGE;
import static wbdk.WBDK_PropertyType.NEW_DRAFT_CREATED_MESSAGE;
import static wbdk.WBDK_PropertyType.SAVE_UNSAVED_WORK_MESSAGE;
import static wbdk.WBDK_StartupConstants.JSON_FILE_PATH_HITTERS;
import static wbdk.WBDK_StartupConstants.JSON_FILE_PATH_PITCHERS;
import static wbdk.WBDK_StartupConstants.PATH_DRAFT;
import wbdk.data.Draft;
import wbdk.data.WBDKDataManager;
import wbdk.error.ErrorHandler;
import wbdk.file.JsonWBDKFileManager;
import wbdk.file.WBDKFileManager;
import wbdk.gui.MessageDialog;
import wbdk.gui.WBDK_GUI;
import wbdk.gui.YesNoCancelDialog;

/**
 * This controller class provides responses to interactions with the buttons in
 * the file toolbar.
 *
 * @author Sheryar
 */
public class FileController {

    // WE WANT TO KEEP TRACK OF WHEN SOMETHING HAS NOT BEEN SAVED
    private boolean saved;

    // THIS GUY KNOWS HOW TO READ AND WRITE COURSE DATA
    private WBDKFileManager draftIO;
    
    JsonWBDKFileManager jsonFileManager;

    // THIS WILL PROVIDE FEEDBACK TO THE USER WHEN SOMETHING GOES WRONG
    ErrorHandler errorHandler;

    // THIS WILL PROVIDE FEEDBACK TO THE USER AFTER
    // WORK BY THIS CLASS HAS COMPLETED
    MessageDialog messageDialog;

    // AND WE'LL USE THIS TO ASK YES/NO/CANCEL QUESTIONS
    YesNoCancelDialog yesNoCancelDialog;

    // WE'LL USE THIS TO GET OUR VERIFICATION FEEDBACK
    PropertiesManager properties;

    /**
     * This default constructor starts the program without a course file being
     * edited.
     *
     * @param initMessageDialog
     * @param initYesNoCancelDialog
     * @param initDraftIO
     */
    public FileController(
            MessageDialog initMessageDialog,
            YesNoCancelDialog initYesNoCancelDialog, WBDKFileManager initDraftIO) {
        // NOTHING YET
        saved = true;

        draftIO = initDraftIO;

        // BE READY FOR ERRORS
        errorHandler = ErrorHandler.getErrorHandler();

        // AND GET READY TO PROVIDE FEEDBACK
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
        properties = PropertiesManager.getPropertiesManager();
    }

    /**
     * This method marks the appropriate variable such that we know that the
     * current Draft has been edited since it's been saved. The UI is then
     * updated to reflect this.
     *
     * @param gui The user interface editing the Course.
     * @throws java.io.IOException
     */
    public void markAsEdited(WBDK_GUI gui) throws IOException {
        // THE DRAFT OBJECT IS NOW DIRTY
        saved = false;

        // LET THE UI KNOW
        gui.updateToolbarControls(saved);
    }

    /**
     * This method starts the process of editing a new Draft. If a draft is
     * already being edited, it will prompt the user to save it first.
     *
     * @param gui The user interface editing the Draft.
     */
    public void handleNewDraftRequest(WBDK_GUI gui) {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToMakeNew = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToMakeNew = promptToSave(gui);
            }

            // IF THE USER REALLY WANTS TO MAKE A NEW DRAFT
            if (continueToMakeNew) {
                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
                WBDKDataManager dataManager = gui.getDataManager();
                dataManager.reset();
                saved = false;

                // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
                // THE APPROPRIATE CONTROLS
                gui.updateToolbarControls(saved);

                // TELL THE USER THE DRAFT HAS BEEN CREATED
                messageDialog.show(properties.getProperty(NEW_DRAFT_CREATED_MESSAGE));
            }
        } catch (IOException ioe) {
            // SOMETHING WENT WRONG, PROVIDE FEEDBACK
            errorHandler.handleNewDraftError();
        }
    }

    /**
     * This method will save the current course to a file. Note that we already
     * know the name of the file, so we won't need to prompt the user.
     *
     * @param gui The user interface editing the Course.
     * @param draftToSave
     */
    public void handleSaveDraftRequest(WBDK_GUI gui, Draft draftToSave) {
        try {
            // SAVE IT TO A FILE
            draftIO.saveDraft(draftToSave);

            // MARK IT AS SAVED
            saved = true;

            // TELL THE USER THE FILE HAS BEEN SAVED
            messageDialog.show(properties.getProperty(DRAFT_SAVED_MESSAGE));

            // AND REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
            // THE APPROPRIATE CONTROLS
            gui.updateToolbarControls(saved);
        } catch (IOException ioe) {
            errorHandler.handleSaveDraftError();
        }
    }

    /**
     * This method lets the user open a Course saved to a file. It will also
     * make sure data for the current Course is not lost.
     *
     * @param gui The user interface editing the course.
     */
    public void handleLoadDraftRequest(WBDK_GUI gui) {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToOpen = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToOpen = promptToSave(gui);
            }

            // IF THE USER REALLY WANTS TO OPEN A Course
            if (continueToOpen) {
                // GO AHEAD AND PROCEED LOADING A Course
                promptToOpen(gui);
            }
        } catch (IOException ioe) {
            // SOMETHING WENT WRONG
            errorHandler.handleLoadDraftError();
        }
    }

    /**
     * This method will exit the application, making sure the user doesn't lose
     * any data first.
     *
     * @param gui
     */
    public void handleExitRequest(WBDK_GUI gui) {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToExit = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE
                continueToExit = promptToSave(gui);
            }

            // IF THE USER REALLY WANTS TO EXIT THE APP
            if (continueToExit) {
                // EXIT THE APPLICATION
                System.exit(0);
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ErrorHandler.getErrorHandler();
            eH.handleExitError();
        }
    }

    /**
     * This method calls the draft screen method which contains draft screen
     * interface.
     *
     * @param gui The user interface editing the Draft.
     */
    public void handleDraftScreenRequest(WBDK_GUI gui) {
        gui.initDraftScreenWorkspace();
        gui.draftScreen();
    }

    /**
     * This method calls the fantasy team screen method which contains fantasy
     * team screen interface.
     *
     * @param gui The user interface editing the Course.
     * @throws java.io.IOException
     */
    public void handleFantasyTeamScreenRequest(WBDK_GUI gui) throws IOException {
        gui.initFantasyTeamScreenWorkspace();
        //gui.fantasyTeamScreenWork();
    }

    /**
     * This method calls the fantasy team standing screen method which contains
     * fantasy team standing screen interface.
     *
     * @param gui The user interface editing the Course.
     */
    public void handleFantasyStandingRequest(WBDK_GUI gui) {
        gui.initFantasyStandingScreenWorkspace();
        gui.fantasyStandingSceen();
    }

    /**
     * This method calls the player screen method which contains player screen
     * interface.
     *
     * @param gui The user interface editing the Course.
     * @throws java.io.IOException
     */
    public void handlePlayerScreenRequest(WBDK_GUI gui) throws IOException {

        gui.initPlayerScreenWorkspace();
        gui.activatePlayerScreen();
    }

    /**
     * This method calls the MLB team screen method which contains MLB team
     * screen interface.
     *
     * @param gui The user interface editing the Course.
     * @throws java.io.IOException
     */
    public void handleMLBTeamScreenRequest(WBDK_GUI gui) throws IOException {
        gui.initMLBTeamScreenWorkspace();
        gui.activateMLBTeamWorkspace();
    }

    /**
     * This helper method verifies that the user really wants to save their
     * unsaved work, which they might not want to do. Note that it could be used
     * in multiple contexts before doing other actions, like creating a new
     * Draft, or opening another Draft. Note that the user will be presented
     * with 3 options: YES, NO, and CANCEL. YES means the user wants to save
     * their work and continue the other action (we return true to denote this),
     * NO means don't save the work but continue with the other action (true is
     * returned), CANCEL means don't save the work and don't continue with the
     * other action (false is returned).
     *
     * @return true if the user presses the YES option to save, true if the user
     * presses the NO option to not save, false if the user presses the CANCEL
     * option to not continue.
     */
    private boolean promptToSave(WBDK_GUI gui) throws IOException {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(properties.getProperty(SAVE_UNSAVED_WORK_MESSAGE));

        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) {
            WBDKDataManager dataManager = gui.getDataManager();
            draftIO.saveDraft(dataManager.getDraft());
            saved = true;
        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
        else if (selection.equals(YesNoCancelDialog.CANCEL)) {
            return false;
        }

        // IF THE USER SAID NO, WE JUST GO ON WITHOUT SAVING
        // BUT FOR BOTH YES AND NO WE DO WHATEVER THE USER
        // HAD IN MIND IN THE FIRST PLACE
        return true;
    }

    /**
     * This helper method asks the user for a file to open. The user-selected
     * file is then loaded and the GUI updated. Note that if the user cancels
     * the open process, nothing is done. If an error occurs loading the file, a
     * message is displayed, but nothing changes.
     */
    private void promptToOpen(WBDK_GUI gui) {
        // AND NOW ASK THE USER FOR THE COURSE TO OPEN
        FileChooser draftFileChooser = new FileChooser();
        draftFileChooser.setInitialDirectory(new File(PATH_DRAFT));
        File selectedFile = draftFileChooser.showOpenDialog(gui.getWindow());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
        if (selectedFile != null) {
            try {
                Draft draftToLoad = gui.getDataManager().getDraft();
                draftIO.loadDraft(draftToLoad, selectedFile.getAbsolutePath());
                gui.reloadDraft(draftToLoad);
                saved = true;
                gui.updateToolbarControls(saved);
            } catch (Exception e) {
                ErrorHandler eH = ErrorHandler.getErrorHandler();
                eH.handleLoadDraftError();
            }
        }
    }

    /**
     * This mutator method marks the file as not saved, which means that when
     * the user wants to do a file-type operation, we should prompt the user to
     * save current work first. Note that this method should be called any time
     * the course is changed in some way.
     */
    public void markFileAsNotSaved() {
        saved = false;
    }

    /**
     * Accessor method for checking to see if the current draft has been saved
     * since it was last edited.
     *
     * @return true if the current draft is saved to the file, false otherwise.
     */
    public boolean isSaved() {
        return saved;
    }

}
