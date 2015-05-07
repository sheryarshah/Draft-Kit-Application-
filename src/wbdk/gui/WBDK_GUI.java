package wbdk.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import properties_manager.PropertiesManager;
import wbdk.WBDK_PropertyType;
import static wbdk.WBDK_PropertyType.REMOVE_TEAM_MESSAGE;
import static wbdk.WBDK_StartupConstants.CLOSE_BUTTON_LABEL;
import static wbdk.WBDK_StartupConstants.JSON_FILE_PATH_HITTERS;
import static wbdk.WBDK_StartupConstants.JSON_FILE_PATH_PITCHERS;
import static wbdk.WBDK_StartupConstants.PATH_CSS;
import static wbdk.WBDK_StartupConstants.PATH_IMAGES;
import wbdk.controller.DraftEditController;
import wbdk.controller.FantasyTeamScreenEditController;
import wbdk.controller.FileController;
import wbdk.controller.PlayerLastNameComparator;
import wbdk.controller.PlayerLastNameComparator;
import wbdk.controller.PlayerScreenEditController;
import wbdk.data.Draft;
import wbdk.data.Player;
import wbdk.data.Team;
import wbdk.data.WBDKDataManager;
import wbdk.data.WBDKDataView;
import wbdk.file.JsonWBDKFileManager;
import wbdk.file.WBDKFileManager;

/**
 * This class provides the Graphical User Interface for this application,
 * managing all the UI components for editing a Draft and exporting it to a
 * site.
 *
 * @author Sheryar
 */
public class WBDK_GUI implements WBDKDataView {

    static final String PRIMARY_STYLE_SHEET = PATH_CSS + "wbdk_style.css";
    static final String CLASS_BORDERED_PANE = "bordered_pane";
    static final String CLASS_HEADING_LABEL = "heading_label";
    static final String CLASS_SUBHEADING_LABEL = "subheading_label";
    static final String CLASS_PROMPT_LABEL = "prompt_label";
    static final String EMPTY_TEXT = "";
    static final int LARGE_TEXT_FIELD_LENGTH = 20;

    //THIS HELPS IN LOADING THE PLAYERS IN THE PLAYER SCREEN TABLE
    JsonWBDKFileManager jsonFileManager;

    // THIS MANAGES ALL OF THE APPLICATION'S DATA
    WBDKDataManager dataManager;

    //FOR GETTING PLAYERS ATTRIBUTES
    Player players;

    //FOR GETTING FANTASY TEAMS ATTRIBUTES
    Team team = new Team();

    // THIS MANAGES DRAFT FILE I/O
    WBDKFileManager wbdkFileManager;

    // THIS HANDLES INTERACTIONS WITH FILE-RELATED CONTROLS
    FileController fileController;

    // THIS HANDLES INTERACTIONS WITH DRAFT INFO CONTROLS
    DraftEditController draftController;

    // THIS HANDLES INTERACTIONS WITH PLAYER INFO CONTROLS
    PlayerScreenEditController playerController;

    // THIS HANDLES INTERACTIONS WITH PLAYER INFO CONTROLS
    PlayerScreenEditController playerController1;

    PlayerScreenDialog playerDialog;

    // THIS HANDLES INTERACTIONS WITH FANTASY TEAM INFO CONTROLS
    FantasyTeamScreenEditController fantasyTeamController;

    //THIS IS THE APPLICATION WINDOW
    Stage primaryStage;

    // THIS IS THE STAGE'S SCENE GRAPH
    Scene primaryScene;

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane wbdkPane;

    // WE'LL PUT THE FANTASY TEAM WORKSPACE INSIDE A SCROLL PANE
    ScrollPane workspaceScrollPane;
    SplitPane splitStartingLinePane;

    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarUpperPane;
    Button newDraftButton;
    Button loadDraftButton;
    Button saveDraftButton;
    Button exportSiteButton;
    Button exitButton;

    // THIS IS THE BOTTOM TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarLowerPane;
    Button draftScreenButton;
    Button fantasyTeamScreenButton;
    Button playerScreenButton;
    Button fantasyStandingScreenButton;
    Button MLBTeamScreenButton;

    // WE'LL ORGANIZE OUR WORKSPACE COMPONENTS USING A BORDER PANE
    BorderPane workspacePane;
    boolean workspaceActivated;

    BorderPane draftScreenPane;
    BorderPane playerScreenPane;
    BorderPane fantasyTeamScreenPane;
    BorderPane fantasyStandingScreenPane;
    BorderPane mlbTeamScreenPane;

    //FOR DRAFT SCREEN
    VBox topWorkspacePaneDraft;
    Label draftScreenHeadingLabel;
    SplitPane topWorkspaceSplitPaneDraft;

    //FOR FANTASY TEAM SCREEN 
    VBox topWorkspacePaneFantasyTeam;
    Label fantasyTeamScreenHeadingLabel;
    SplitPane topWorkspaceSplitPaneFantasyTeam;

    //FOR PLAYER SCREEN
    VBox topWorkspacePanePlayer;
    Label playerScreenHeadingLabel;
    SplitPane topWorkspaceSplitPanePlayer;

    //FOR FANTASY STANDING SCREEN
    VBox topWorkspacePaneFantasyStanding;
    Label fantasyStandingScreenHeadingLabel;
    SplitPane topWorkspaceSplitPaneFantasyStanding;

    //FOR MLB TEAM SCREEN
    VBox topWorkspacePaneMLB;
    Label MLBScreenHeadingLabel;
    SplitPane topWorkspaceSplitPaneMLB;

    VBox screenPane;
    VBox screenInfoPane;
    Label screenInfoHeadingLabel;
    SplitPane splitScreenInfoPane;

    // HERE ARE OUR DIALOGS
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;

    //BELOW IS THE STUFF FOR PLAYER SCREEN
    HBox playersToolbar;
    VBox playersBox;

    //PLAYER ADD OR REMOVE BUTTONS ORGANIZED USING FLOWPANE
    FlowPane addRemoveButtonPane;
    Button addPlayerButton;
    Button removePlayerButton;

    //PLAYER SEARCH TEXTFIELD
    Label playerSearchLabel;
    TextField searchPlayerTextField;
    GridPane playerSearchPane;

    //RADIO BUTTONS USED TO SORT PLAYERS ACCORDING TO POSITIONS
    HBox radioButtons;
    RadioButton All;
    RadioButton C;
    RadioButton B1;
    RadioButton CI;
    RadioButton B3;
    RadioButton B2;
    RadioButton MI;
    RadioButton SS;
    RadioButton OF;
    RadioButton U;
    RadioButton P;
    ToggleGroup group;

    //PLAYERS LOADED IN A TABLE
    TableView<Player> playerTable = new TableView<>();
    TableColumn playerFirstName;
    TableColumn playerLastName;
    TableColumn playerTeam;
    TableColumn playerQp;
    TableColumn playerR_W;
    TableColumn playerHr_Sv;
    TableColumn playerRbi_K;
    TableColumn playerSb_Era;
    TableColumn playerNotes;
    TableColumn playerYearOfBirth;
    TableColumn playerBa_Whip;
    TableColumn playerEstimatedValue;

    SortedList<Player> sortedData;
    FilteredList<Player> filteredData;

    //USED TO CHECK IF PLAYERS HAVE BEEN LOADED IN TABLE
    boolean screenFlag = false;

    //CONSTANTS FOR TABLE COLUMNS
    static final String COL_FIRST = "First";
    static final String COL_LAST = "Last";
    static final String COL_PRO_TEAM = "Pro Team";
    static final String COL_POSITIONS = "Positions";
    static final String COL_YEAR_OF_BIRTH = "Year of Birth";
    static final String COL_R_W = "R/W";
    static final String COL_HR_SV = "HR/SV";
    static final String COL_RBI_K = "RBI/K";
    static final String COL_SB_ERA = "SB/ERA";
    static final String COL_BA_WHIP = "BA/WHIP";
    static final String COL_ESTIMATED_VALUE = "Estimated Value";
    static final String COL_NOTES = "Notes";

    //BELOW IS THE STUFF FOR FANTASY TEAM SCREEN
    VBox fantasyTeamBox;
    HBox fantasyTeamToolbarBox;
    HBox fantasyTeamEditSelectBox;
    VBox fantasyTeamTableBox;

    //TEXTFIELD USED TO NAME AND SAVE A DRAFT 
    Label draftNameLabel;
    TextField draftNameTextField;
    GridPane draftNamePane;

    //BUTTONS FOR ADDING, REMOVING AND EDITING TEAM NAME AND OWNER
    FlowPane addRemoveEditPane;
    Button addTeamButton;
    Button removeTeamButton;
    Button editTeamButton;
    boolean activateButton = false;

    //COMBOBOX USED TO SELECT FANTASY TEAM
    GridPane selectTeamPane;
    Label selectTeamLabel;
    ComboBox<Team> teamSelectionCombo;
    int trackSelect;                //keeps track of team selected

    Label startingLineupLabel;
    VBox taxiTeamTableBox;

    //TABLE FOR FANTASY TEAM STARTING LINEUP
    TableView<Team> fantasyTeamTable;
    TableColumn positionSelected;
    TableColumn firstName;
    TableColumn lastName;
    TableColumn proTeam;
    TableColumn q_p;
    TableColumn r_w;
    TableColumn hr_sv;
    TableColumn rbi_k;
    TableColumn sb_era;
    TableColumn ba_whip;
    TableColumn estimated;
    TableColumn contract;
    TableColumn salary;

    Label taxiSquadLabel;

    //TABLE FOR FANTASY TEAM TAXI SQUAD
    TableView<Team> taxiTeamTable;
    TableColumn positionSelected1;
    TableColumn firstName1;
    TableColumn lastName1;
    TableColumn proTeam1;
    TableColumn q_p1;
    TableColumn r_w1;
    TableColumn hr_sv1;
    TableColumn rbi_k1;
    TableColumn sb_era1;
    TableColumn ba_whip1;
    TableColumn estimated1;
    TableColumn contract1;
    TableColumn salary1;

    //TABLE COLUMNS CONSTANTS FOR FANTASY TEAM
    static final String COL_POS = "Position";
    static final String COL_CONTRACT = "Contract";
    static final String COL_SALARY = "Salary ($)";

    //BELOW IS THE STUFF FOR MLB TEAM SCREEN
    VBox mlbTeamBox;
    HBox mlbTeamSelectBox;
    VBox mlbTeamTableBox;
    Label proTeamPlayerLabel;

    //COMBOBOX USED TO SELECT FANTASY TEAM
    GridPane selectProTeamPane;
    Label selectProTeamLabel;
    ComboBox proTeamCombo;
    int trackTeam;                //keeps track of proTeam selected

    TableView<Player> mlbTeamTable;
    TableColumn firstName2;
    TableColumn lastName2;
    TableColumn qualifyingPosition;

    //BELOW IS THE STUFF FOR FANTASY STANDINGS SCREEN
    VBox fantasyStandingBox;
    VBox fantasyStandingTableBox;

    //TABLE FOR FANTASY TEAM TAXI SQUAD
    TableView<Team> fantasyStandingTable;
    TableColumn teamName;
    TableColumn playersNeeded;
    TableColumn salaryLeft;
    TableColumn salaryPP;
    TableColumn r;
    TableColumn hr;
    TableColumn rbi;
    TableColumn sb;
    TableColumn ba;
    TableColumn w;
    TableColumn sv;
    TableColumn k;
    TableColumn era;
    TableColumn whip;
    TableColumn totalPoint;
    
    //TABLE COLUMNS CONSTANTS FOR FANTASY STANDING
    static final String COL_TEAM = "Team";
    static final String COL_PLAYER_NEEDED = "Players Needed";
    static final String COL_SALARY_LEFT = "$ Left";
    static final String COL_SALARY_PP = "$ PP";
    static final String COL_R = "R";
    static final String COL_HR = "HR";
    static final String COL_RBI = "RBI";
    static final String COL_SB = "SB";
    static final String COL_BA = "BA";
    static final String COL_W = "W";
    static final String COL_SV = "SV";
    static final String COL_K = "K";
    static final String COL_ERA = "ERA";
    static final String COL_WHIP = "WHIP";
    static final String COL_TOTAL_POINT = "Total Points";
    
    //BELOW IS THE STUFF FOR DRAFT SCREEN
    VBox draftBox;
    HBox draftToolbarBox;
    VBox draftTableBox;
    
    FlowPane draftButtonPane;
    
    Button selectPlayerButton;
    Button autoDraftButton;
    Button pauseDraftButton;
    
    //TABLE FOR FANTASY TEAM TAXI SQUAD
    TableView<Team> draftTable;
    TableColumn pick;
    TableColumn first;
    TableColumn last;
    TableColumn fantasyTeamName;
    TableColumn contractDraft;
    TableColumn salaryDraft;
    
    static final String COL_PICK = "Pick#";


    /**
     * Constructor for making this GUI, note that it does not initialize the UI
     * controls. To do that, call initGUI.
     *
     * @param initPrimaryStage Window inside which the GUI will be displayed.
     */
    public WBDK_GUI(Stage initPrimaryStage) {
        primaryStage = initPrimaryStage;
    }

    /**
     * Accessor method for the window (i.e. stage).
     *
     * @return The window (i.e. Stage) used by this UI.
     */
    public Stage getWindow() {
        return primaryStage;
    }

    /**
     * Displaying a message to user
     *
     * @return message dialog
     */
    public MessageDialog getMessageDialog() {
        return messageDialog;
    }

    /**
     * Gets user of input of whether to save,load or exit
     *
     * @return
     */
    public YesNoCancelDialog getYesNoCancelDialog() {
        return yesNoCancelDialog;
    }

    /**
     * Accessor method for the data manager.
     *
     * @return The WBDKDataManager used by this UI.
     */
    public WBDKDataManager getDataManager() {
        return dataManager;
    }

    /**
     * Accessor method for the file controller.
     *
     * @return The FileController used by this UI.
     */
    public FileController getFileController() {
        return fileController;
    }

    /**
     * Accessor method for the draft file manager.
     *
     * @return The WBDKFileManager used by this UI.
     */
    public WBDKFileManager getWBDKFileManager() {
        return wbdkFileManager;
    }

    /**
     * Mutator method for the data manager.
     *
     * @param initDataManager The WBDKDataManager to be used by this UI.
     */
    public void setDataManager(WBDKDataManager initDataManager) {
        dataManager = initDataManager;
    }

    /**
     * Mutator method for the draft file manager.
     *
     * @param initWBDKFileManager
     */
    public void setDraftFileManager(WBDKFileManager initWBDKFileManager) {
        wbdkFileManager = initWBDKFileManager;
    }

    /**
     * This method fully initializes the user interface for use.
     *
     * @param windowTitle The text to appear in the UI window's title bar.
     * @throws IOException Thrown if any initialization files fail to load.
     */
    public void initGUI(String windowTitle) throws IOException {

        // INIT THE DIALOGS
        initDialogs();

        // INIT THE UUPER TOOLBAR
        initUpperFileToolbar();

        // INIT THE BOTTOM TOOLBAR
        initBottomFileToolbar();

        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
        // TO THE WINDOW YET
        initWorkspace();

        // NOW SETUP THE EVENT HANDLERS
        initEventHandlers();

        // AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
        initWindow(windowTitle);
    }

    /**
     * When called this function puts the workspace into the window, revealing
     * the controls for editing a Draft.
     */
    public void activateWorkspace() throws IOException {
        if (!workspaceActivated) {
            // PUT THE WORKSPACE IN THE GUI
            wbdkPane.setCenter(workspaceScrollPane);
            wbdkPane.setCenter(workspacePane);
            initFantasyTeamScreenWorkspace();
            //ENABLE THE BOTTOM TOOLBAR
            wbdkPane.setBottom(fileToolbarLowerPane);
            workspaceActivated = true;

        }
    }

    @Override
    public void reloadDraft(Draft draftToReload) {
        // FIRST ACTIVATE THE WORKSPACE IF NECESSARY
        if (!workspaceActivated) {
            try {
                activateWorkspace();
            } catch (IOException ex) {
                Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // WE DON'T WANT TO RESPOND TO EVENTS FORCED BY
        // OUR INITIALIZATION SELECTIONS
        draftController.enable(false);

        //THIS ALLOWS TO SEARCH PLAYERS BY TYPING EITHER FIRST OR LAST NAME
        filteredData = new FilteredList<>(draftToReload.getPlayers(), p -> true);
        searchPlayerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(players -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (players.getFirstName().toLowerCase().startsWith(lowerCaseFilter)) {
                    return true;
                } else if (players.getLastName().toLowerCase().startsWith(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        //ADDED THE FILTERD PLAYED TO SORTED DATA
        sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

        //SET THE SORTED DATA TO PLAYER'S TABLE
        playerTable.setItems(sortedData);

        teamSelectionCombo.getItems().clear();
        for (int i = 0; i < draftToReload.getTeam().size(); i++) {
            teamSelectionCombo.getItems().add(draftToReload.getTeam().get(i));
        }
        teamSelectionCombo.getSelectionModel().select(draftToReload.getTeam().get(0));
        fantasyTeamTable.setItems(draftToReload.getTeam().get(0).getTeamPlayers());

        draftController.enable(true);
    }

    @Override
    public void resetComboBox() {
        if (!workspaceActivated) {
            try {
                activateWorkspace();
            } catch (IOException ex) {
                Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        teamSelectionCombo.getItems().clear();
    }

    /**
     * This method is used to activate/deactivate toolbar buttons when they can
     * and cannot be used so as to provide foolproof design.
     *
     * @param saved Describes whether the loaded Draft has been saved or not.
     */
    public void updateToolbarControls(boolean saved) throws IOException {
        // THIS TOGGLES WITH WHETHER THE CURRENT COURSE
        // HAS BEEN SAVED OR NOT
        saveDraftButton.setDisable(saved);

        // ALL THE OTHER BUTTONS ARE ALWAYS ENABLED
        // ONCE EDITING THAT FIRST DRAFT BEGINS
        loadDraftButton.setDisable(false);
        exportSiteButton.setDisable(false);
    }

    /**
     * *************************************************************************
     */
    /* BELOW ARE ALL THE PRIVATE HELPER METHODS WE USE FOR INITIALIZING OUR GUI */
    /**
     * *************************************************************************
     */
    private void initDialogs() {
        messageDialog = new MessageDialog(primaryStage, CLOSE_BUTTON_LABEL);
        yesNoCancelDialog = new YesNoCancelDialog(primaryStage);
    }

    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    private void initUpperFileToolbar() {
        fileToolbarUpperPane = new FlowPane();

        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        newDraftButton = initChildButton(fileToolbarUpperPane, WBDK_PropertyType.NEW_DRAFT_ICON, WBDK_PropertyType.NEW_DRAFT_TOOLTIP, false);
        loadDraftButton = initChildButton(fileToolbarUpperPane, WBDK_PropertyType.LOAD_DRAFT_ICON, WBDK_PropertyType.LOAD_DRAFT_TOOLTIP, false);
        saveDraftButton = initChildButton(fileToolbarUpperPane, WBDK_PropertyType.SAVE_DRAFT_ICON, WBDK_PropertyType.SAVE_DRAFT_TOOLTIP, true);
        exportSiteButton = initChildButton(fileToolbarUpperPane, WBDK_PropertyType.EXPORT_DRAFT_ICON, WBDK_PropertyType.EXPORT_DRAFT_TOOLTIP, true);
        exitButton = initChildButton(fileToolbarUpperPane, WBDK_PropertyType.EXIT_ICON, WBDK_PropertyType.EXIT_TOOLTIP, false);
    }

    /**
     * This function initializes all the buttons in the toolbar at the bottom of
     * the application window. These are related to screen selection.
     */
    private void initBottomFileToolbar() {
        fileToolbarLowerPane = new FlowPane();

        draftScreenButton = initChildButton(fileToolbarLowerPane, WBDK_PropertyType.DRAFT_SCREEN_ICON, WBDK_PropertyType.DRAFT_SCREEN_TOOLTIP, false);
        playerScreenButton = initChildButton(fileToolbarLowerPane, WBDK_PropertyType.PLAYER_SCREEN_ICON, WBDK_PropertyType.PLAYER_SCREEN_TOOLTIP, false);
        fantasyTeamScreenButton = initChildButton(fileToolbarLowerPane, WBDK_PropertyType.FANTASY_TEAM_SCREEN_ICON, WBDK_PropertyType.FANTASY_TEAM_SCREEN_TOOLTIP, false);
        fantasyStandingScreenButton = initChildButton(fileToolbarLowerPane, WBDK_PropertyType.FANTASY_TEAM_STANDING_SCREEN_ICON, WBDK_PropertyType.FANTASY_STANDING_SCREEN_TOOLTIP, false);
        MLBTeamScreenButton = initChildButton(fileToolbarLowerPane, WBDK_PropertyType.MLB_TEAM_SCREEN_ICON, WBDK_PropertyType.MLB_TEAM_SCREEN_TOOLTIP, false);
    }

    // CREATES AND SETS UP ALL THE CONTROLS TO GO IN THE APP WORKSPACE
    private void initWorkspace() throws IOException {

        // THE TOP WORKSPACE HOLDS BOTH THE BASIC COURSE INFO
        // CONTROLS AS WELL AS THE PAGE SELECTION CONTROLS
        workspacePane = new BorderPane();
        workspacePane.setCenter(topWorkspacePaneFantasyTeam);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        // AND NOW PUT IT IN THE WORKSPACE
        workspaceScrollPane = new ScrollPane();
        workspaceScrollPane.setContent(workspacePane);
        workspaceScrollPane.setFitToWidth(true);

        initAllScreens();

        workspaceActivated = false;

    }

    private void initAllScreens() throws IOException {
        initMLBTeamScreenWorkspace();
        initPlayerScreenWorkspace();
        fantasyTeamScreenWork();
        fantasyStandingSceen();
        draftScreen();
    }

    // INITIALIZES THE TOP PORTION OF THE FANTASY TEAM SCREEN WORKSPACE
    public void initFantasyTeamScreenWorkspace() throws IOException {

        //SCREEN HEADING LABEL
        topWorkspacePaneFantasyTeam = new VBox();

        topWorkspacePaneFantasyTeam.getStyleClass().add(CLASS_BORDERED_PANE);
        fantasyTeamScreenHeadingLabel = initChildLabel(topWorkspacePaneFantasyTeam, WBDK_PropertyType.FANTASY_TEAM_HEADING_LABEL, CLASS_HEADING_LABEL);

        topWorkspacePaneFantasyTeam.getChildren().add(fantasyTeamBox);
        topWorkspacePaneFantasyTeam.getChildren().add(fantasyTeamTableBox);
        topWorkspacePaneFantasyTeam.getChildren().add(taxiTeamTableBox);

        workspacePane.setTop(topWorkspacePaneFantasyTeam);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);

    }

    public void updateFantasyTeamTable(Draft draft) {
        draft.setDraftName(draftNameTextField.getText());
        trackSelect = teamSelectionCombo.getSelectionModel().getSelectedIndex();
        fantasyTeamTable.setItems(dataManager.getDraft().getTeam().get(trackSelect).getTeamPlayers());
        taxiTeamTable.setItems(dataManager.getDraft().getTeam().get(trackSelect).getTaxiPlayers());

    }

    public void fantasyTeamScreenWork() throws IOException {
        fantasyTeamBox = new VBox();
        fantasyTeamToolbarBox = new HBox();

        draftNamePane = new GridPane();
        draftNameLabel = initGridLabel(draftNamePane, WBDK_PropertyType.DRAFT_NAME_LABEL, CLASS_PROMPT_LABEL, 1, 1, 1, 1);
        draftNameTextField = initGridTextField(draftNamePane, 50, EMPTY_TEXT, true, 2, 1, 1, 1);
        fantasyTeamToolbarBox.getChildren().add(draftNamePane);

        fantasyTeamEditSelectBox = new HBox();
        addRemoveEditPane = new FlowPane();
        addTeamButton = initChildButton(addRemoveEditPane, WBDK_PropertyType.ADD_ICON, WBDK_PropertyType.ADD_TEAM_TOOLTIP, false);
        removeTeamButton = initChildButton(addRemoveEditPane, WBDK_PropertyType.MINUS_ICON, WBDK_PropertyType.REMOVE_TEAM_TOOLTIP, activateButton);
        editTeamButton = initChildButton(addRemoveEditPane, WBDK_PropertyType.EDIT_ICON, WBDK_PropertyType.EDIT_TEAM_TOOLTIP, activateButton);

        selectTeamPane = new GridPane();
        selectTeamLabel = initGridLabel(selectTeamPane, WBDK_PropertyType.SELECT_TEAM_LABEL, CLASS_PROMPT_LABEL, 0, 1, 1, 1);
        teamSelectionCombo = initGridComboBox(selectTeamPane, 1, 1, 1, 1);

        fantasyTeamEditSelectBox.getChildren().add(addRemoveEditPane);

        fantasyTeamEditSelectBox.getChildren().add(selectTeamPane);

        fantasyTeamBox.getChildren().add(fantasyTeamToolbarBox);
        fantasyTeamBox.getChildren().add(fantasyTeamEditSelectBox);

        fantasyTeamTableBox = new VBox();

        startingLineupLabel = initLabel(WBDK_PropertyType.STARTING_LINEUP_HEADING_LABEL, CLASS_SUBHEADING_LABEL);

        fantasyTeamTable = new TableView();
        //NOW SETUP THE TABLE COLUMNS
        positionSelected = new TableColumn(COL_POS);
        firstName = new TableColumn(COL_FIRST);
        lastName = new TableColumn(COL_LAST);
        proTeam = new TableColumn(COL_PRO_TEAM);
        q_p = new TableColumn(COL_POSITIONS);
        r_w = new TableColumn(COL_R_W);
        hr_sv = new TableColumn(COL_HR_SV);
        rbi_k = new TableColumn(COL_RBI_K);
        sb_era = new TableColumn(COL_SB_ERA);
        ba_whip = new TableColumn(COL_BA_WHIP);
        estimated = new TableColumn(COL_ESTIMATED_VALUE);
        contract = new TableColumn(COL_CONTRACT);
        salary = new TableColumn(COL_SALARY);

        //MAKE FANTASY TABLE EDITABLE
        fantasyTeamTable.setEditable(false);

        //START ADDING THE COLUMNS
        fantasyTeamTable.getColumns().add(positionSelected);
        fantasyTeamTable.getColumns().add(firstName);
        fantasyTeamTable.getColumns().add(lastName);
        fantasyTeamTable.getColumns().add(proTeam);
        fantasyTeamTable.getColumns().add(q_p);
        fantasyTeamTable.getColumns().add(r_w);
        fantasyTeamTable.getColumns().add(hr_sv);
        fantasyTeamTable.getColumns().add(rbi_k);
        fantasyTeamTable.getColumns().add(sb_era);
        fantasyTeamTable.getColumns().add(ba_whip);
        fantasyTeamTable.getColumns().add(estimated);
        fantasyTeamTable.getColumns().add(contract);
        fantasyTeamTable.getColumns().add(salary);

        // AND LINK THE COLUMNS TO THE DATA
        positionSelected.setCellValueFactory(new PropertyValueFactory<>("positionPlaying"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        proTeam.setCellValueFactory(new PropertyValueFactory<>("proTeam"));
        q_p.setCellValueFactory(new PropertyValueFactory<>("qp3"));
        r_w.setCellValueFactory(new PropertyValueFactory<>("RW"));
        hr_sv.setCellValueFactory(new PropertyValueFactory<>("HR_SV"));
        rbi_k.setCellValueFactory(new PropertyValueFactory<>("RBIK"));
        sb_era.setCellValueFactory(new PropertyValueFactory<>("SBERA"));
        ba_whip.setCellValueFactory(new PropertyValueFactory<>("BAWHIP"));
        contract.setCellValueFactory(new PropertyValueFactory<>("contract"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        fantasyTeamTableBox.getChildren().add(startingLineupLabel);
        fantasyTeamTableBox.setSpacing(10);
        fantasyTeamTableBox.getChildren().add(fantasyTeamTable);

        fantasyTeamTable.setPrefHeight(350);

        //BELOW IS THE TABLE STUFF FOR TAXI SQUAD
        taxiTeamTableBox = new VBox();

        taxiSquadLabel = initLabel(WBDK_PropertyType.TAXI_SQUAD_HEADING_LABEL, CLASS_SUBHEADING_LABEL);

        taxiTeamTable = new TableView();
        //NOW SETUP THE TABLE COLUMNS
        positionSelected1 = new TableColumn(COL_POS);
        firstName1 = new TableColumn(COL_FIRST);
        lastName1 = new TableColumn(COL_LAST);
        proTeam1 = new TableColumn(COL_PRO_TEAM);
        q_p1 = new TableColumn(COL_POSITIONS);
        r_w1 = new TableColumn(COL_R_W);
        hr_sv1 = new TableColumn(COL_HR_SV);
        rbi_k1 = new TableColumn(COL_RBI_K);
        sb_era1 = new TableColumn(COL_SB_ERA);
        ba_whip1 = new TableColumn(COL_BA_WHIP);
        estimated1 = new TableColumn(COL_ESTIMATED_VALUE);
        contract1 = new TableColumn(COL_CONTRACT);
        salary1 = new TableColumn(COL_SALARY);

        //MAKE FANTASY TABLE EDITABLE
        taxiTeamTable.setEditable(true);

        //START ADDING THE COLUMNS
        taxiTeamTable.getColumns().add(positionSelected1);
        taxiTeamTable.getColumns().add(firstName1);
        taxiTeamTable.getColumns().add(lastName1);
        taxiTeamTable.getColumns().add(proTeam1);
        taxiTeamTable.getColumns().add(q_p1);
        taxiTeamTable.getColumns().add(r_w1);
        taxiTeamTable.getColumns().add(hr_sv1);
        taxiTeamTable.getColumns().add(rbi_k1);
        taxiTeamTable.getColumns().add(sb_era1);
        taxiTeamTable.getColumns().add(ba_whip1);
        taxiTeamTable.getColumns().add(estimated1);
        taxiTeamTable.getColumns().add(contract1);
        taxiTeamTable.getColumns().add(salary1);

        // AND LINK THE COLUMNS TO THE DATA
        positionSelected1.setCellValueFactory(new PropertyValueFactory<>("positionPlaying"));
        firstName1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName1.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        proTeam1.setCellValueFactory(new PropertyValueFactory<>("proTeam"));
        q_p1.setCellValueFactory(new PropertyValueFactory<>("qp3"));
        r_w1.setCellValueFactory(new PropertyValueFactory<>("RW"));
        hr_sv1.setCellValueFactory(new PropertyValueFactory<>("HR_SV"));
        rbi_k1.setCellValueFactory(new PropertyValueFactory<>("RBIK"));
        sb_era1.setCellValueFactory(new PropertyValueFactory<>("SBERA"));
        ba_whip1.setCellValueFactory(new PropertyValueFactory<>("BAWHIP"));
        contract1.setCellValueFactory(new PropertyValueFactory<>("contract"));
        salary1.setCellValueFactory(new PropertyValueFactory<>("salary"));

        taxiTeamTableBox.getChildren().add(taxiSquadLabel);
        taxiTeamTableBox.setSpacing(10);
        taxiTeamTableBox.getChildren().add(taxiTeamTable);

        taxiTeamTable.setPrefHeight(350);

    }

    // INITIALIZE THE WINDOW (i.e. STAGE) PUTTING ALL THE CONTROLS
    // THERE EXCEPT THE WORKSPACE, WHICH WILL BE ADDED THE FIRST
    // TIME A NEW Course IS CREATED OR LOADED
    private void initWindow(String windowTitle) {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // ADD THE TOOLBAR ONLY, NOTE THAT THE WORKSPACE
        // HAS BEEN CONSTRUCTED, BUT WON'T BE ADDED UNTIL
        // THE USER STARTS EDITING A COURSE
        wbdkPane = new BorderPane();
        wbdkPane.setTop(fileToolbarUpperPane);
        //wbdkPane.setCenter(workspacePane);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        primaryScene = new Scene(wbdkPane);

        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        primaryScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    // INITIALIZES THE TOP PORTION OF THE DRAFT SCREEN WORKWPACE UI
    public void initDraftScreenWorkspace() {
        topWorkspacePaneDraft = new VBox();
        topWorkspacePaneDraft.getStyleClass().add(CLASS_BORDERED_PANE);
        draftScreenHeadingLabel = initChildLabel(topWorkspacePaneDraft, WBDK_PropertyType.DRAFT_HEADING_LABEL, CLASS_HEADING_LABEL);

        topWorkspacePaneDraft.getChildren().add(draftBox);
        
        workspacePane.setTop(topWorkspacePaneDraft);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);

    }
    
    public void draftScreen(){
        draftBox = new VBox();
        draftToolbarBox = new HBox();
        
        draftButtonPane = new FlowPane();
        selectPlayerButton = initChildButton(draftButtonPane, WBDK_PropertyType.SELECT_PLAYER_ICON, WBDK_PropertyType.SELECT_PLAYER_TOOLTIP, false);
        autoDraftButton = initChildButton(draftButtonPane, WBDK_PropertyType.AUTO_DRAFT_ICON, WBDK_PropertyType.AUTO_DRAFT_TOOLTIP, activateButton);
        pauseDraftButton = initChildButton(draftButtonPane, WBDK_PropertyType.PAUSE_DRAFT_ICON, WBDK_PropertyType.PAUSE_DRAFT_TOOLTIP, activateButton);
        
        draftToolbarBox.getChildren().add(draftButtonPane);
        
        draftTableBox = new VBox();
        
        draftTable = new TableView();
        
        //NOW SETUP THE TABLE COLUMNS
        pick = new TableColumn(COL_PICK);
        first = new TableColumn(COL_FIRST);
        last = new TableColumn(COL_LAST);
        fantasyTeamName = new TableColumn(COL_PRO_TEAM);
        contractDraft = new TableColumn(COL_CONTRACT);
        salaryDraft = new TableColumn(COL_SALARY);
        
        //START ADDING THE COLUMNS
        draftTable.getColumns().add(pick);
        draftTable.getColumns().add(first);
        draftTable.getColumns().add(last);
        draftTable.getColumns().add(fantasyTeamName);
        draftTable.getColumns().add(contractDraft);
        draftTable.getColumns().add(salaryDraft);
        
        
        draftTable.setPrefHeight(600);
        
        draftTableBox.getChildren().add(draftTable);
        
        draftBox.getChildren().add(draftToolbarBox);
        draftBox.getChildren().add(draftTableBox);

    }

    // INITIALIZES THE PLAYER SCREEN WORKWPACE UI
    public void initPlayerScreenWorkspace() throws IOException {
        playerScreenPane = new BorderPane();

        while (!screenFlag) {
            players = new Player();
            jsonFileManager = new JsonWBDKFileManager();

            //SCREEN HEADING LABEL
            topWorkspacePanePlayer = new VBox();
            topWorkspacePanePlayer.getStyleClass().add(CLASS_BORDERED_PANE);
            playerScreenHeadingLabel = initChildLabel(topWorkspacePanePlayer, WBDK_PropertyType.PLAYER_HEADING_LABEL, CLASS_HEADING_LABEL);

            //ADD OR REMOVE BUTTONS AND PLAYER SEARCH TEXTFIELD
            playersBox = new VBox();
            playersToolbar = new HBox();
            addRemoveButtonPane = new FlowPane();
            playerSearchPane = new GridPane();
            addPlayerButton = initChildButton(addRemoveButtonPane, WBDK_PropertyType.ADD_ICON, WBDK_PropertyType.ADD_PLAYER_TOOLTIP, false);
            removePlayerButton = initChildButton(addRemoveButtonPane, WBDK_PropertyType.MINUS_ICON, WBDK_PropertyType.REMOVE_PLAYER_TOOLTIP, false);

            playerSearchLabel = initGridLabel(playerSearchPane, WBDK_PropertyType.SEARCH_PLAYERS_LABEL, CLASS_PROMPT_LABEL, 1, 1, 1, 1);
            searchPlayerTextField = initGridTextField(playerSearchPane, 50, EMPTY_TEXT, true, 2, 1, 1, 1);
            playersToolbar.getChildren().addAll(addRemoveButtonPane, playerSearchPane);

            //RADIO BUTTON INITILIZATIONS
            All = new RadioButton("All");
            C = new RadioButton("C");
            B1 = new RadioButton("1B");
            CI = new RadioButton("CI");
            B3 = new RadioButton("3B");
            B2 = new RadioButton("2B");
            MI = new RadioButton("MI");
            SS = new RadioButton("SS");
            OF = new RadioButton("OF");
            U = new RadioButton("U");
            P = new RadioButton("P");

            group = new ToggleGroup();
            All.setToggleGroup(group);
            C.setToggleGroup(group);
            B1.setToggleGroup(group);
            CI.setToggleGroup(group);
            B3.setToggleGroup(group);
            B2.setToggleGroup(group);
            MI.setToggleGroup(group);
            SS.setToggleGroup(group);
            OF.setToggleGroup(group);
            U.setToggleGroup(group);
            P.setToggleGroup(group);
            //PRESELECT ALL
            All.setSelected(true);
            radioButtons = new HBox(25, All, C, B1, CI, B3, B2, MI, SS, OF, U, P);
            radioButtons.setPadding(new Insets(20));

            //ADD IT TO PLAYERBOX
            playersBox.getChildren().add(playersToolbar);
            playersBox.getChildren().add(playerTable);
            playersBox.getStyleClass().add(CLASS_BORDERED_PANE);

            //PRE-SET THE TABLE WITH ALL THE PLAYERS
            if (All.isSelected()) {
                //NOW SETUP THE TABLE COLUMNS
                playerFirstName = new TableColumn(COL_FIRST);
                playerLastName = new TableColumn(COL_LAST);
                playerTeam = new TableColumn(COL_PRO_TEAM);
                playerQp = new TableColumn(COL_POSITIONS);
                playerYearOfBirth = new TableColumn(COL_YEAR_OF_BIRTH);
                playerEstimatedValue = new TableColumn(COL_ESTIMATED_VALUE);
                playerNotes = new TableColumn(COL_NOTES);

                playerR_W = new TableColumn(COL_R_W);
                playerHr_Sv = new TableColumn(COL_HR_SV);
                playerRbi_K = new TableColumn(COL_RBI_K);
                playerSb_Era = new TableColumn(COL_SB_ERA);
                playerBa_Whip = new TableColumn(COL_BA_WHIP);
            }

            //CALL RADIO BUTTON METHOD, WHICH HANDLES ALL THE RADIO BUTTON 
            //LISTENERS
            radioButton();

            //MAKE PLAYER TABLE EDITABLE
            playerTable.setEditable(true);

            //START ADDING THE COLUMNS
            playerTable.getColumns().add(playerFirstName);
            playerTable.getColumns().add(playerLastName);
            playerTable.getColumns().add(playerTeam);
            playerTable.getColumns().add(playerQp);
            playerTable.getColumns().add(playerYearOfBirth);
            playerTable.getColumns().add(playerR_W);
            playerTable.getColumns().add(playerHr_Sv);
            playerTable.getColumns().add(playerRbi_K);
            playerTable.getColumns().add(playerSb_Era);
            playerTable.getColumns().add(playerBa_Whip);
            playerTable.getColumns().add(playerEstimatedValue);
            playerTable.getColumns().add(playerNotes);

            // AND LINK THE COLUMNS TO THE DATA
            playerFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            playerLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            playerTeam.setCellValueFactory(new PropertyValueFactory<>("team"));
            playerQp.setCellValueFactory(new PropertyValueFactory<>("qp"));
            playerYearOfBirth.setCellValueFactory(new PropertyValueFactory<>("yearOfBirth"));
            playerR_W.setCellValueFactory(new PropertyValueFactory<>("RW"));
            playerHr_Sv.setCellValueFactory(new PropertyValueFactory<>("HR_SV"));
            playerRbi_K.setCellValueFactory(new PropertyValueFactory<>("RBIK"));
            playerSb_Era.setCellValueFactory(new PropertyValueFactory<>("SBERA"));
            playerBa_Whip.setCellValueFactory(new PropertyValueFactory<>("BAWHIP"));
            playerNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));

            //THIS ALLOWS TO SEARCH PLAYERS BY TYPING EITHER FIRST OR LAST NAME
            filteredData = new FilteredList<>(dataManager.getDraft().getPlayers(), p -> true);
            searchPlayerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(players -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (players.getFirstName().toLowerCase().startsWith(lowerCaseFilter)) {
                        return true;
                    } else if (players.getLastName().toLowerCase().startsWith(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            //THIS ALLOWS ONLY THE NOTES COLUMN TO BE EDITABLE BY DOUBLE CLICKING ON IT
            playerNotes.setCellFactory(TextFieldTableCell.forTableColumn());
            playerNotes.setOnEditCommit(new EventHandler<CellEditEvent<Player, String>>() {
                @Override
                public void handle(CellEditEvent<Player, String> t) {
                    ((Player) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setNotes(t.getNewValue());
                }
            });

            //ADDED THE FILTERD PLAYED TO SORTED DATA
            sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

            //SET THE SORTED DATA TO PLAYER'S TABLE
            playerTable.setItems(sortedData);

            topWorkspacePanePlayer.getChildren().add(playersToolbar);
            topWorkspacePanePlayer.getChildren().add(radioButtons);
            topWorkspacePanePlayer.getChildren().add(playersBox);
            screenFlag = true;
        }

    }

    //THIS HELPER METHOD UPDATES THE TABLE COLUMN ACCORDING TO RADIO BUTTON
    //SELECTED
    private void updateTableColumn(RadioButton pos) {

        if (pos.getText().equalsIgnoreCase("P")) {
            //NOW SETUP THE TABLE COLUMNS
            playerR_W.setText("W");
            playerHr_Sv.setText("SV");
            playerRbi_K.setText("K");
            playerSb_Era.setText("ERA");
            playerBa_Whip.setText("WHIP");
        } else if (pos.getText().equalsIgnoreCase("All")) {
            playerR_W.setText("R/W");
            playerHr_Sv.setText("HR/SV");
            playerRbi_K.setText("RBI/K");
            playerSb_Era.setText("SB/ERA");
            playerBa_Whip.setText("BA/WHIP");
        } else {
            playerR_W.setText("R");
            playerHr_Sv.setText("HR");
            playerRbi_K.setText("RBI");
            playerSb_Era.setText("SB");
            playerBa_Whip.setText("BA");
        }

    }

    //THIS IS WHERE THE RADIO BUTTON LISTENER IS HANDLED. IT FILTERS THE TABLE
    //ACCORDING TO THE POSITION
    private void radioButton() {

        //radio button listeners
        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            RadioButton pos = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
            if (pos.getText().equals("P")) {
                updateTableColumn(pos);
                searchPlayerTextField.clear();
                dataManager.getDraft().clearPitchers();
                filteredData = new FilteredList<>(dataManager.getDraft().getPitchers(), p -> true);
                //adding the filtered list to table
                sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

                playerTable.setItems(sortedData);

            } else if (pos.getText().equals("C")) {
                updateTableColumn(pos);
                searchPlayerTextField.clear();
                dataManager.getDraft().clearCatchers();
                filteredData = new FilteredList<>(dataManager.getDraft().getCatchers(), p -> true);
                sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

                playerTable.setItems(sortedData);

            } else if (pos.getText().equals("1B")) {
                updateTableColumn(pos);
                searchPlayerTextField.clear();
                dataManager.getDraft().clearFirstBaseman();
                filteredData = new FilteredList<>(dataManager.getDraft().getFirstBaseman(), p -> true);
                sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

                playerTable.setItems(sortedData);

            } else if (pos.getText().equals("CI")) {
                updateTableColumn(pos);
                searchPlayerTextField.clear();
                dataManager.getDraft().clearCornerInField();
                filteredData = new FilteredList<>(dataManager.getDraft().getCornerInField(), p -> true);
                sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

                playerTable.setItems(sortedData);

            } else if (pos.getText().equals("2B")) {
                updateTableColumn(pos);
                searchPlayerTextField.clear();
                dataManager.getDraft().clearSecondBaseman();
                filteredData = new FilteredList<>(dataManager.getDraft().getSecondBaseman(), p -> true);
                sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

                playerTable.setItems(sortedData);

            } else if (pos.getText().equals("3B")) {
                updateTableColumn(pos);
                searchPlayerTextField.clear();
                dataManager.getDraft().clearThirdBaseman();
                filteredData = new FilteredList<>(dataManager.getDraft().getThirdBaseman(), p -> true);
                sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

                playerTable.setItems(sortedData);

            } else if (pos.getText().equals("MI")) {
                updateTableColumn(pos);
                searchPlayerTextField.clear();
                dataManager.getDraft().clearMidInField();
                filteredData = new FilteredList<>(dataManager.getDraft().getMidInField(), p -> true);
                sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

                playerTable.setItems(sortedData);
            } else if (pos.getText().equals("SS")) {
                updateTableColumn(pos);
                searchPlayerTextField.clear();
                dataManager.getDraft().clearShortStop();
                filteredData = new FilteredList<>(dataManager.getDraft().getShortStop(), p -> true);
                sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

                playerTable.setItems(sortedData);
            } else if (pos.getText().equals("OF")) {
                updateTableColumn(pos);
                searchPlayerTextField.clear();
                dataManager.getDraft().clearOutFielder();
                filteredData = new FilteredList<>(dataManager.getDraft().getOutFielder(), p -> true);
                sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

                playerTable.setItems(sortedData);
            } else if (pos.getText().equals("U")) {
                updateTableColumn(pos);
                searchPlayerTextField.clear();
                dataManager.getDraft().clearHitters();
                filteredData = new FilteredList<>(dataManager.getDraft().getHitters(), p -> true);
                sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

                playerTable.setItems(sortedData);
            } else if (pos.getText().equals("All")) {
                updateTableColumn(pos);
                searchPlayerTextField.clear();
                filteredData = new FilteredList<>(dataManager.getDraft().getPlayers(), p -> true);
                sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

                playerTable.setItems(sortedData);
            }

        });
    }

    public void activatePlayerScreen() {
        //ADD THE PLAYER SCREEN WORKPACE TO MAIN WORKSPACE PANE
        workspacePane.setTop(topWorkspacePanePlayer);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
        playerTable.setPrefHeight(600);

    }

    // INITIALIZES THE FANTASY STANDING WORKWPACE UI
    public void initFantasyStandingScreenWorkspace() {
        topWorkspacePaneFantasyStanding = new VBox();

        topWorkspacePaneFantasyStanding.getStyleClass().add(CLASS_BORDERED_PANE);
        fantasyStandingScreenHeadingLabel = initChildLabel(topWorkspacePaneFantasyStanding, WBDK_PropertyType.FANTASY_STANDING_HEADING_LABEL, CLASS_HEADING_LABEL);

        topWorkspacePaneFantasyStanding.getChildren().add(fantasyStandingTableBox);

        workspacePane.setTop(topWorkspacePaneFantasyStanding);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);

    }

    public void fantasyStandingSceen() {

        dataManager.getDraft().clearTeam1();

        fantasyStandingBox = new VBox();

        fantasyStandingTableBox = new VBox();

        fantasyStandingTable = new TableView();

        //NOW SETUP THE TABLE COLUMNS
        teamName = new TableColumn(COL_TEAM);
        playersNeeded = new TableColumn(COL_PLAYER_NEEDED);
        salaryLeft = new TableColumn(COL_SALARY_LEFT);
        salaryPP = new TableColumn(COL_SALARY_PP);
        r = new TableColumn(COL_R);
        hr = new TableColumn(COL_HR);
        rbi = new TableColumn(COL_RBI);
        sb = new TableColumn(COL_SB);
        ba = new TableColumn(COL_BA);
        w = new TableColumn(COL_W);
        sv = new TableColumn(COL_SV);
        k = new TableColumn(COL_K);
        era = new TableColumn(COL_ERA);
        whip = new TableColumn(COL_WHIP);
        totalPoint = new TableColumn(COL_TOTAL_POINT);

        playersNeeded.setPrefWidth(50);
        //MAKE FANTASY STANDING TABLE EDITABLE
        fantasyStandingTable.setEditable(true);

        //START ADDING THE COLUMNS
        fantasyStandingTable.getColumns().add(teamName);
        fantasyStandingTable.getColumns().add(playersNeeded);
        fantasyStandingTable.getColumns().add(salaryLeft);
        fantasyStandingTable.getColumns().add(salaryPP);
        fantasyStandingTable.getColumns().add(r);
        fantasyStandingTable.getColumns().add(hr);
        fantasyStandingTable.getColumns().add(rbi);
        fantasyStandingTable.getColumns().add(sb);
        fantasyStandingTable.getColumns().add(ba);
        fantasyStandingTable.getColumns().add(w);
        fantasyStandingTable.getColumns().add(sv);
        fantasyStandingTable.getColumns().add(k);
        fantasyStandingTable.getColumns().add(era);
        fantasyStandingTable.getColumns().add(whip);
        fantasyStandingTable.getColumns().add(totalPoint);

        // AND LINK THE COLUMNS TO THE DATA
        teamName.setCellValueFactory(new PropertyValueFactory<>("name"));
        playersNeeded.setCellValueFactory(new PropertyValueFactory<>("playerSize"));
        salaryLeft.setCellValueFactory(new PropertyValueFactory<>("salaryLeft"));
        salaryPP.setCellValueFactory(new PropertyValueFactory<>("salaryPP"));
        r.setCellValueFactory(new PropertyValueFactory<>("R"));
        hr.setCellValueFactory(new PropertyValueFactory<>("HR"));
        rbi.setCellValueFactory(new PropertyValueFactory<>("RBI"));
        sb.setCellValueFactory(new PropertyValueFactory<>("SB"));
        ba.setCellValueFactory(new PropertyValueFactory<>("BA"));
        w.setCellValueFactory(new PropertyValueFactory<>("W"));
        sv.setCellValueFactory(new PropertyValueFactory<>("SV"));
        k.setCellValueFactory(new PropertyValueFactory<>("K"));
        era.setCellValueFactory(new PropertyValueFactory<>("ERA"));
        whip.setCellValueFactory(new PropertyValueFactory<>("WHIP"));
        totalPoint.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

        fantasyStandingTableBox.getChildren().add(fantasyStandingTable);

        fantasyStandingTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        fantasyStandingTable.setPrefHeight(600);

        fantasyStandingTable.getItems().clear();
        
        fantasyStandingTable.setItems(dataManager.getDraft().getTeam1());

    }

    // INITIALIZES THE MLB TEAM SCREEN WORKWPACE UI
    public void initMLBTeamScreenWorkspace() throws IOException {

        topWorkspacePaneMLB = new VBox();
        topWorkspacePaneMLB.getStyleClass().add(CLASS_BORDERED_PANE);
        MLBScreenHeadingLabel = initChildLabel(topWorkspacePaneMLB, WBDK_PropertyType.MLB_TEAM_HEADING_LABEL, CLASS_HEADING_LABEL);

        mlbTeamBox = new VBox();
        mlbTeamSelectBox = new HBox();

        selectProTeamPane = new GridPane();
        selectProTeamLabel = initGridLabel(selectProTeamPane, WBDK_PropertyType.SELECT_MLB_TEAM_LABEL, CLASS_PROMPT_LABEL, 0, 1, 1, 1);
        proTeamCombo = initGridComboBox(selectProTeamPane, 1, 1, 1, 1);

        mlbTeamSelectBox.getChildren().add(selectProTeamPane);

        ObservableList<String> proTeam2 = FXCollections.observableArrayList();
        proTeam2.addAll("ATL", "AZ", "CHC", "CIN", "LAD", "MIA", "MIL", "PHI", "PIT",
                "SD", "SF", "STL", "WSH");
        proTeamCombo.getItems().addAll(proTeam2);
        proTeamCombo.setValue(proTeam2.get(0));
        proTeamCombo.setVisibleRowCount(3);

        mlbTeamTableBox = new VBox();

        proTeamPlayerLabel = initLabel(WBDK_PropertyType.PRO_TEAM_HEADING_LABEL, CLASS_SUBHEADING_LABEL);

        mlbTeamTable = new TableView();
        firstName2 = new TableColumn(COL_FIRST);
        lastName2 = new TableColumn(COL_LAST);
        qualifyingPosition = new TableColumn(COL_POSITIONS);

        //MAKE MLB TEAM TABLE EDITABLE
        mlbTeamTable.setEditable(false);

        //START ADDING THE COLUMNS
        mlbTeamTable.getColumns().add(firstName2);
        mlbTeamTable.getColumns().add(lastName2);
        mlbTeamTable.getColumns().add(qualifyingPosition);

        mlbTeamTable.setPrefHeight(600);
        mlbTeamTableBox.getChildren().add(proTeamPlayerLabel);
        mlbTeamTableBox.setSpacing(20);
        mlbTeamTableBox.getChildren().add(mlbTeamTable);

        firstName2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        qualifyingPosition.setCellValueFactory(new PropertyValueFactory<>("qp"));

        dataManager.getDraft().clearATL();
        mlbTeamTable.setItems(dataManager.getDraft().getATL().sorted(new PlayerLastNameComparator()));
        proTeamCombo.valueProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.equals("ATL")) {
                dataManager.getDraft().clearATL();
                mlbTeamTable.setItems(dataManager.getDraft().getATL().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("AZ")) {
                dataManager.getDraft().clearAZ();
                mlbTeamTable.setItems(dataManager.getDraft().getAZ().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("CHC")) {
                dataManager.getDraft().clearCHC();
                mlbTeamTable.setItems(dataManager.getDraft().getCHC().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("CIN")) {
                dataManager.getDraft().clearCIN();
                mlbTeamTable.setItems(dataManager.getDraft().getCIN().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("COL")) {
                dataManager.getDraft().clearCOL();
                mlbTeamTable.setItems(dataManager.getDraft().getCOL().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("LAD")) {
                dataManager.getDraft().clearLAD();
                mlbTeamTable.setItems(dataManager.getDraft().getLAD().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("MIA")) {
                dataManager.getDraft().clearMIA();
                mlbTeamTable.setItems(dataManager.getDraft().getMIA().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("MIL")) {
                dataManager.getDraft().clearMIL();
                mlbTeamTable.setItems(dataManager.getDraft().getMIL().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("NYM")) {
                dataManager.getDraft().clearNYM();
                mlbTeamTable.setItems(dataManager.getDraft().getNYM().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("PHI")) {
                dataManager.getDraft().clearPHI();
                mlbTeamTable.setItems(dataManager.getDraft().getPHI().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("PIT")) {
                dataManager.getDraft().clearPIT();
                mlbTeamTable.setItems(dataManager.getDraft().getPIT().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("SD")) {
                dataManager.getDraft().clearSD();
                mlbTeamTable.setItems(dataManager.getDraft().getSD().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("SF")) {
                dataManager.getDraft().clearSF();
                mlbTeamTable.setItems(dataManager.getDraft().getSF().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("STL")) {
                dataManager.getDraft().clearSTL();
                mlbTeamTable.setItems(dataManager.getDraft().getSTL().sorted(new PlayerLastNameComparator()));
            }

            if (newValue.equals("WSH")) {
                dataManager.getDraft().clearWSH();
                mlbTeamTable.setItems(dataManager.getDraft().getWSH().sorted(new PlayerLastNameComparator()));
            }

        });

        //ADD IT TO MLB TEAM WORKSPACE
        topWorkspacePaneMLB.getChildren().add(mlbTeamSelectBox);
        topWorkspacePaneMLB.getChildren().add(mlbTeamTableBox);

        //ADD IT TO ENTIRE WORKSPACE
        workspacePane.setTop(topWorkspacePaneMLB);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
    }

    public void activateMLBTeamWorkspace() {

        workspacePane.setTop(topWorkspacePaneMLB);
        workspacePane.getStyleClass().add(CLASS_BORDERED_PANE);
    }

    // INIT ALL THE EVENT HANDLERS
    private void initEventHandlers() {
        // FIRST THE FILE CONTROLS
        fileController = new FileController(messageDialog, yesNoCancelDialog, wbdkFileManager);
        newDraftButton.setOnAction(e -> {
            fileController.handleNewDraftRequest(this);
            //ENABLE THE BOTTOM TOOLBAR
            wbdkPane.setBottom(fileToolbarLowerPane);
            try {
                initFantasyTeamScreenWorkspace();
            } catch (IOException ex) {
                Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        loadDraftButton.setOnAction(e -> {
            fileController.handleLoadDraftRequest(this);
        });
        saveDraftButton.setOnAction(e -> {
            fileController.handleSaveDraftRequest(this, dataManager.getDraft());
        });
        /* exportSiteButton.setOnAction(e -> {
         fileController.handleExportCourseRequest(this);
         });*/
        exitButton.setOnAction(e -> {
            fileController.handleExitRequest(this);
        });

        registerTextFieldController(draftNameTextField);

        fantasyTeamScreenButton.setOnAction(e -> {
            try {
                fileController.handleFantasyTeamScreenRequest(this);
            } catch (IOException ex) {
                Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        draftScreenButton.setOnAction(e -> {
            fileController.handleDraftScreenRequest(this);
        });
        playerScreenButton.setOnAction(e -> {
            try {
                fileController.handlePlayerScreenRequest(this);
            } catch (IOException ex) {
                Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        fantasyStandingScreenButton.setOnAction(e -> {
            fileController.handleFantasyStandingRequest(this);
        });
        MLBTeamScreenButton.setOnAction(e -> {
            try {
                fileController.handleMLBTeamScreenRequest(this);
            } catch (IOException ex) {
                Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //DRAFT TEAM COMBOBOX LISTENERS
        draftController = new DraftEditController();
        teamSelectionCombo.setOnAction(e -> {
            draftController.handleDraftChangeRequest(this);
        });

        //PLAYER SCREEN CONTROLLER
        playerController = new PlayerScreenEditController(primaryStage, messageDialog, yesNoCancelDialog);
        addPlayerButton.setOnAction(e -> {
            try {
                playerController.handleAddPlayerRequest(this);
            } catch (IOException ex) {
                Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        removePlayerButton.setOnAction(e -> {
            try {
                playerController.handleRemovePlayerRequest(this, playerTable.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        playerTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Player p = playerTable.getSelectionModel().getSelectedItem();

                try {
                    playerController.handleEditPlayerRequest(this, p, dataManager, trackSelect);
                } catch (IOException ex) {
                    Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            All.setSelected(true);

        });

        teamSelectionCombo.setCellFactory(new Callback<ListView<Team>, ListCell<Team>>() {
            @Override
            public ListCell<Team> call(ListView<Team> team) {
                return new ListCell<Team>() {
                    @Override
                    protected void updateItem(Team item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });
        teamSelectionCombo.setConverter(new StringConverter<Team>() {
            @Override
            public String toString(Team team) {
                if (team == null) {
                    return null;
                } else {
                    return team.getName();
                }
            }

            @Override
            public Team fromString(String teamName) {
                return null;
            }
        });

        //  teamSelectionCombo.getItems().clear();
        //   teamSelectionCombo.getSelectionModel().select(dataManager.getDraft().getTeam().get(0));
        //FANTASY TEAM SCREEN CONTROLLER
        fantasyTeamController = new FantasyTeamScreenEditController(primaryStage, messageDialog, yesNoCancelDialog);
        addTeamButton.setOnAction(e -> {
            try {
                fantasyTeamController.handleAddTeamRequest(this);
                teamSelectionCombo.getItems().clear();

                for (int i = 0; i < dataManager.getDraft().getTeam().size(); i++) {
                    teamSelectionCombo.getItems().add(dataManager.getDraft().getTeam().get(i));
                }
                teamSelectionCombo.getSelectionModel().select(dataManager.getDraft().getTeam().get(0));

            } catch (IOException ex) {
                Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        removeTeamButton.setOnAction(e -> {
            try {
                fantasyTeamController.handleRemoveTeamRequest(this, teamSelectionCombo.getSelectionModel().getSelectedItem(), trackSelect, teamSelectionCombo);

            } catch (IOException ex) {
                Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        editTeamButton.setOnAction(e -> {
            try {
                fantasyTeamController.handleEditTeamRequest(this, teamSelectionCombo.getSelectionModel().getSelectedItem());
            } catch (IOException ex) {
                Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        fantasyTeamTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Team t = fantasyTeamTable.getSelectionModel().getSelectedItem();

                try {
                    fantasyTeamController.handleEditTeamPlayerRequest(this, t, trackSelect, dataManager);
                } catch (IOException ex) {
                    Logger.getLogger(WBDK_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    // REGISTER THE EVENT LISTENER FOR A TEXT FIELD
    private void registerTextFieldController(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            draftController.handleDraftChangeRequest(this);
        });
    }

    // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
    private Button initChildButton(Pane toolbar, WBDK_PropertyType icon, WBDK_PropertyType tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }

    // INIT A LABEL AND SET IT'S STYLESHEET CLASS
    private Label initLabel(WBDK_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }

    // INIT A LABEL AND PLACE IT IN A GridPane INIT ITS PROPER PLACE
    private Label initGridLabel(GridPane container, WBDK_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }

    // INIT A TEXT FIELD AND PUT IT IN A GridPane
    private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }

    // INIT A LABEL AND PUT IT IN A TOOLBAR
    private Label initChildLabel(Pane container, WBDK_PropertyType labelProperty, String styleClass) {
        Label label = initLabel(labelProperty, styleClass);
        container.getChildren().add(label);
        return label;
    }

    // INIT A COMBO BOX AND PUT IT IN A GridPane
    private ComboBox initGridComboBox(GridPane container, int col, int row, int colSpan, int rowSpan) throws IOException {
        ComboBox comboBox = new ComboBox();
        container.add(comboBox, col, row, colSpan, rowSpan);
        return comboBox;
    }

}
