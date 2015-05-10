/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.gui;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wbdk.WBDK_StartupConstants.PATH_FLAGS_IMAGES;
import static wbdk.WBDK_StartupConstants.PATH_PLAYER_IMAGES;
import wbdk.controller.PlayerScreenEditController;
import wbdk.data.Draft;
import wbdk.data.Player;
import wbdk.data.Team;
import wbdk.data.WBDKDataManager;
import static wbdk.gui.PlayerScreenDialog.CANCEL;
import static wbdk.gui.PlayerScreenDialog.COMPLETE;
import static wbdk.gui.PlayerScreenDialog.EDIT_PLAYER_TITLE;
import static wbdk.gui.PlayerScreenDialog.PLAYER_HEADING;
import static wbdk.gui.WBDK_GUI.CLASS_HEADING_LABEL;
import static wbdk.gui.WBDK_GUI.CLASS_PROMPT_LABEL;
import static wbdk.gui.WBDK_GUI.CLASS_SUBHEADING_LABEL;
import static wbdk.gui.WBDK_GUI.PRIMARY_STYLE_SHEET;

/**
 *
 * @author Sheryar
 */
public class FantasyTeamScreenDialog extends Stage {

    Team team;
    WBDKDataManager addTeam;

    Team t;

    PlayerScreenEditController playerController;

    MessageDialog messageDialog;

    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    Scene dialogScene;
    Label headingLabel;
    Label nameLabel;
    TextField nameTextField;
    Label ownerLabel;
    TextField ownerTextField;

    Button completeButton;
    Button cancelButton;

    Scene editDialogScene;
    GridPane editGridPane;
    Label playerNameLabel;
    Label playerPositionLabel;
    Label fantasyTeamLabel;
    Label positionLabel;
    Label contractLabel;
    Label salaryLabel;
    ComboBox fantasyComboBox;
    ComboBox positionComboBox;
    ComboBox contractComboBox;
    TextField salaryTextField;

    String imageFlagPath;

    // THIS IS FOR KEEPING TRACK OF WHICH BUTTON THE USER PRESSED
    String selection;

    int i;
    boolean flag;
    int positionC_counter = 0;
    int counterC = 0;
    int position1B_counter = 0;
    int positionCI_counter = 0;
    int position3B_counter = 0;
    int position2B_counter = 0;
    int positionMI_counter = 0;
    int positionSS_counter = 0;
    int positionU_counter = 0;
    int positionOF_counter = 0;
    int positionP_counter = 0;

    int pickCounter = 0;

    // CONSTANTS FOR OUR UI
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    public static final String NAME_PROMPT = "Name: ";
    public static final String OWNER_PROMPT = "Owner: ";
    public static final String TEAM_HEADING = "Fantasy Team Details";
    public static final String ADD_FANTASY_TEAM_TITLE = "Add New Fantasy Team";
    public static final String EDIT_FANTASY_TEAM_TITLE = "Edit New Fantasy Team";

    /**
     * Initializes this dialog so that it can be used for either adding new
     * assignments or editing existing ones.
     *
     * @param primaryStage The owner of this modal dialog.
     * @param course
     * @param messageDialog
     */
    public FantasyTeamScreenDialog(Stage primaryStage, MessageDialog initMessageDialog) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        messageDialog = initMessageDialog;

        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);

        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            FantasyTeamScreenDialog.this.selection = sourceButton.getText();
            FantasyTeamScreenDialog.this.hide();
        };
        completeButton.setOnAction(completeCancelHandler);
        cancelButton.setOnAction(completeCancelHandler);

    }

    /**
     * Accessor method for getting the selection the user made.
     *
     * @return Either YES, NO, or CANCEL, depending on which button the user
     * selected when this dialog was presented.
     */
    public String getSelection() {
        return selection;
    }

    public Team getTeam() {
        return t;
    }

    public Team getTeam1() {
        return team;
    }

    /**
     * This method loads a custom message into the label and then pops open the
     * dialog.
     *
     * @param initDate
     * @return
     */
    public Team showAddTeamDialog() {
        // SET THE DIALOG TITLE
        setTitle(ADD_FANTASY_TEAM_TITLE);

        // RESET THE ASSIGNMENTS OBJECT WITH DEFAULT VALUES
        team = new Team();

        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label(TEAM_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);

        // NOW THE NAME
        nameLabel = new Label(NAME_PROMPT);
        nameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        nameTextField = new TextField();
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            team.setName(newValue);
        });

        // NOW THE OWNER NAME
        ownerLabel = new Label(OWNER_PROMPT);
        ownerLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        ownerTextField = new TextField();
        ownerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            team.setOwner(newValue);
        });

        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);

        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {

            if (!nameTextField.getText().equalsIgnoreCase("") && !ownerTextField.getText().equalsIgnoreCase("")) {
                Button sourceButton = (Button) ae.getSource();
                selection = sourceButton.getText();
                hide();
            } else {
                System.out.println(selection);
                messageDialog.show("Incomplete Fields");
            }

        };

        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler cancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            selection = sourceButton.getText();
            hide();
        };

        completeButton.setOnAction(completeHandler);
        cancelButton.setOnAction(cancelHandler);

        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(nameLabel, 0, 1, 1, 1);
        gridPane.add(nameTextField, 1, 1, 1, 1);
        gridPane.add(ownerLabel, 0, 2, 1, 1);
        gridPane.add(ownerTextField, 1, 2, 1, 1);
        gridPane.add(completeButton, 0, 3, 1, 1);
        gridPane.add(cancelButton, 1, 3, 1, 1);

        // AND PUT THE GRID PANE IN THE WINDOW
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);

        // AND OPEN IT UP
        this.showAndWait();

        return team;
    }

    public void loadGUIData() {
        // LOAD THE UI STUFF
        nameTextField.setText(team.getName());
        ownerTextField.setText(team.getOwner());
    }

    public boolean wasCompleteSelected() {
        return selection.equals(COMPLETE);

    }

    public boolean wasCompleteSelected1() {
        return selection.equals(COMPLETE);

    }

    public void showEditTeamDialog(Team teamToEdit) {
        // SET THE DIALOG TITLE
        setTitle(EDIT_FANTASY_TEAM_TITLE);

        // LOAD THE ASSIGNMENTS INTO OUR LOCAL OBJECT
        team = new Team();
        team.setName(teamToEdit.getName());
        team.setOwner(teamToEdit.getOwner());

        // AND THEN INTO OUR GUI
        loadGUIData();

        // AND OPEN IT UP
        this.showAndWait();
    }

    public void showEditTeamPlayerDialog(Team playerToEdit, WBDKDataManager dataManager) {

        i = 0;
        playerController = new PlayerScreenEditController();
        t = new Team();
        // SET THE DIALOG TITLE
        setTitle(EDIT_PLAYER_TITLE);

        //player = new Player();
        // FIRST OUR CONTAINER
        editGridPane = new GridPane();
        editGridPane.setPadding(new Insets(10, 20, 20, 20));
        editGridPane.setHgap(10);
        editGridPane.setVgap(10);

        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label(PLAYER_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);

        String imagePlayerPath = "file:" + PATH_PLAYER_IMAGES + "/" + playerToEdit.getLastName().concat(playerToEdit.getFirstName().concat(".jpg"));
        if (playerToEdit.getNation().equalsIgnoreCase("Canada")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Canada.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("Colombia")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Colombia.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("Cuba")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Cuba.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("Dominican_Republic")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Dominican_Republic.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("Germany")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Germany.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("Japan")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Japan.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("Mexico")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Mexico.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("Netherlands_Antilles")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Netherlands_Antilles.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("Panama")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Panama.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("Puerto_Rico")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Puerto_Rico.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("South_Korea")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "South_Korea.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("Taiwan")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Taiwan.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("US_Virgin_Islands")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "US_Virgin_Islands.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("USA")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "USA.png";
        } else if (playerToEdit.getNation().equalsIgnoreCase("Venezula")) {
            imageFlagPath = "file:" + PATH_FLAGS_IMAGES + "/" + "Venezuela.png";
        }

        Image playerImage = new Image(imagePlayerPath);
        Image flagImage = new Image(imageFlagPath);
        ImageView iv1 = new ImageView();
        ImageView iv2 = new ImageView();
        iv1.setImage(playerImage);
        iv2.setImage(flagImage);

        String playerName = playerToEdit.getFirstName() + playerToEdit.getLastName();
        playerNameLabel = new Label(playerName);
        playerNameLabel.getStyleClass().add(CLASS_SUBHEADING_LABEL);
        String playerPosition = playerToEdit.getQPosition();
        playerPositionLabel = new Label(playerPosition);
        playerPositionLabel.getStyleClass().add(CLASS_SUBHEADING_LABEL);

        fantasyTeamLabel = new Label("Fantasy Team:");
        positionLabel = new Label("Position:");
        contractLabel = new Label("Contract:");
        salaryLabel = new Label("Salary ($):");

        ObservableList<String> fantasyTeam = FXCollections.observableArrayList();
        fantasyTeam.add("Free Agent");
        for (int i = 0; i < dataManager.getDraft().getTeam().size(); i++) {
            fantasyTeam.addAll(dataManager.getDraft().getTeam().get(i).getName());
        }

        positionComboBox = new ComboBox();
        fantasyComboBox = new ComboBox();
        fantasyComboBox.getItems().addAll(fantasyTeam);
        fantasyComboBox.setValue(fantasyTeam.get(0));
        fantasyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            i = fantasyComboBox.getSelectionModel().selectedIndexProperty().get();
            i--;

            //  positionComboBox.getSelectionModel().clearSelection();
            //   positionComboBox.getItems().clear();
            ObservableList<String> position = FXCollections.observableArrayList();
            String positions = playerToEdit.getQPosition();
            if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().size() != 23) {
                if (positions.contains("C")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("C")) {
                            positionC_counter++;
                        }
                    }
                    if (positionC_counter < 2) {
                        position.add("C");
                    }
                    if (positionC_counter > 2) {
                        positionC_counter = 0;
                    }
                }
                if (positions.contains("1B")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("1B")) {
                            position1B_counter++;

                        }
                    }
                    if (position1B_counter < 1) {
                        position.add("1B");
                    }
                    if (position1B_counter > 1) {
                        position1B_counter = 0;
                    }

                }

                if (positions.contains("3B")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("3B")) {
                            position3B_counter++;

                        }
                    }
                    if (position3B_counter < 1) {
                        position.add("3B");
                    }
                    if (position3B_counter > 1) {
                        position3B_counter = 0;
                    }

                }
                if (positions.contains("2B")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("2B")) {
                            position2B_counter++;

                        }
                    }
                    if (position2B_counter < 1) {
                        position.add("2B");
                    }
                    if (position2B_counter > 1) {
                        position2B_counter = 0;
                    }

                }

                if (positions.contains("SS")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("SS")) {
                            positionSS_counter++;

                        }
                    }
                    if (positionSS_counter < 1) {
                        position.add("SS");
                    }
                    if (positionSS_counter > 1) {
                        positionSS_counter = 0;
                    }
                }
                if (positions.contains("OF")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("OF")) {
                            positionOF_counter++;

                        }
                    }
                    if (positionOF_counter < 5) {
                        position.add("OF");
                    }
                    if (positionOF_counter > 5) {
                        positionOF_counter = 0;
                    }
                }
                if (positions.contains("U")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("U")) {
                            positionU_counter++;

                        }
                    }
                    if (positionU_counter < 1) {
                        position.add("U");
                    }
                    if (positionU_counter > 1) {
                        positionU_counter = 0;
                    }
                }
                if (positions.contains("P")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("P")) {
                            positionP_counter++;

                        }
                    }
                    if (positionP_counter < 9) {
                        position.add("P");
                    }
                    if (positionP_counter > 1) {
                        positionP_counter = 0;
                    }
                }
                if (positions.contains("2B") || positions.contains("SS")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("MI")) {
                            positionMI_counter++;

                        }
                    }
                    if (positionMI_counter < 1) {
                        position.add("MI");
                    }
                    if (positionMI_counter > 1) {
                        positionMI_counter = 0;
                    }
                }
                if (positions.contains("1B") || positions.contains("3B")) {
                    for (int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++) {
                        if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("CI")) {
                            positionCI_counter++;

                        }
                    }
                    if (positionCI_counter < 1) {
                        position.add("CI");
                    }
                    if (positionCI_counter > 1) {
                        positionCI_counter = 0;
                    }
                }

                positionComboBox.getSelectionModel().clearSelection();
                positionComboBox.getItems().clear();
                positionComboBox.getSelectionModel().clearSelection();
                positionComboBox.getItems().addAll(position);
                positionComboBox.setValue(position.get(0));
            } else {
                positionComboBox.getSelectionModel().clearSelection();
                positionComboBox.getItems().clear();
                positionComboBox.getSelectionModel().clearSelection();
                positionComboBox.setValue(playerToEdit.getPositionPlaying());
            }

            t.setPositionPlaying(positionComboBox.getValue().toString());
            positionComboBox.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                if (newValue1 != null) {
                    t.setPositionPlaying(newValue1.toString());
                }

            });
            this.setI(i);
        });

        t.setFirstName(playerToEdit.getFirstName());
        t.setLastName(playerToEdit.getLastName());
        t.setProTeam(playerToEdit.getProTeam());
        t.setQPosition(playerToEdit.getQPosition());
        t.setYearOfBirth(playerToEdit.getYearOfBirth());
        t.setRW(playerToEdit.getRW());
        t.setHRSV(playerToEdit.getHR_SV());
        t.setRBIK(playerToEdit.getRBIK());
        t.setSBERA(playerToEdit.getSBERA());
        t.setBAWHIP(playerToEdit.getBAWHIP());
        t.setNotes(playerToEdit.getNotes());
        t.setNation(playerToEdit.getNation());

        ObservableList<String> contract = FXCollections.observableArrayList("X", "S1", "S2");

        contractComboBox = new ComboBox();
        contractComboBox.getItems().addAll(contract);
        contractComboBox.setValue(contract.get(0));
        t.setContract(contractComboBox.getValue().toString());
        contractComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            t.setContract(contractComboBox.getValue().toString());
            if (contractComboBox.getValue().toString().equalsIgnoreCase("S2")) {
                pickCounter++;
                t.setPick(pickCounter);
            }
        });

        salaryTextField = new TextField();
        salaryTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String s = newValue;
            int salary = Integer.parseInt(s);
            t.setSalary(salary);
        });

        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);

        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {

            if (!salaryTextField.getText().equalsIgnoreCase("") && contractComboBox.getSelectionModel().getSelectedItem() != "X") {
                Button sourceButton = (Button) ae.getSource();
                selection = sourceButton.getText();
                hide();
            } else {
                System.out.println(selection);
                messageDialog.show("Incomplete Fields");
            }

        };

        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler cancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            selection = sourceButton.getText();
            hide();
        };

        completeButton.setOnAction(completeHandler);
        cancelButton.setOnAction(cancelHandler);

        //gridPane.add(iv2, columnIndex, rowIndex, colspan, rowspan);
        editGridPane.add(headingLabel, 0, 0, 2, 1);
        editGridPane.add(iv1, 0, 1, 1, 3);
        editGridPane.add(iv2, 1, 1, 1, 1);
        editGridPane.add(playerNameLabel, 1, 2, 1, 1);
        editGridPane.add(playerPositionLabel, 1, 3, 1, 1);
        editGridPane.add(fantasyTeamLabel, 0, 4, 1, 1);
        editGridPane.add(fantasyComboBox, 1, 4, 1, 1);
        editGridPane.add(positionLabel, 0, 5, 1, 1);
        editGridPane.add(positionComboBox, 1, 5, 1, 1);
        editGridPane.add(contractLabel, 0, 6, 1, 1);
        editGridPane.add(contractComboBox, 1, 6, 1, 1);
        editGridPane.add(salaryLabel, 0, 7, 1, 1);
        editGridPane.add(salaryTextField, 1, 7, 1, 1);
        editGridPane.add(completeButton, 0, 8, 1, 1);
        editGridPane.add(cancelButton, 1, 8, 1, 1);

        // AND PUT THE GRID PANE IN THE WINDOW
        editDialogScene = new Scene(editGridPane);
        editDialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(editDialogScene);

        for (int i = 0; i < dataManager.getDraft().getDraftPlayers().size(); i++) {
            if (dataManager.getDraft().getDraftPlayers().get(i).getLastName().equalsIgnoreCase(playerToEdit.getLastName())) {
                dataManager.getDraft().getDraftPlayers().get(i).setContract("S1");
            }
        }

        this.setT(t);

        // AND OPEN IT UP
        this.showAndWait();

    }

    public void removeTeamPlyer(WBDKDataManager dataManager) {
        dataManager.getDraft().getTeam().get(playerController.getTrackSelect()).removeTeamPlayers(t);
    }

    public void setI(int initI) {
        this.i = initI;
    }

    public int getI() {
        return i;
    }

    public void setT(Team initT) {
        this.t = initT;
    }

    public Team getAccessPlayer() {
        return t;
    }
}
