/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static wbdk.WBDK_PropertyType.NEW_DRAFT_CREATED_MESSAGE;
import static wbdk.WBDK_StartupConstants.PATH_FLAGS_IMAGES;
import static wbdk.WBDK_StartupConstants.PATH_PLAYER_IMAGES;
import wbdk.controller.PlayerScreenEditController;
import wbdk.data.Player;
import wbdk.data.Team;
import wbdk.data.WBDKDataManager;
import static wbdk.gui.FantasyTeamScreenDialog.COMPLETE;
import static wbdk.gui.WBDK_GUI.CLASS_HEADING_LABEL;
import static wbdk.gui.WBDK_GUI.CLASS_PROMPT_LABEL;
import static wbdk.gui.WBDK_GUI.CLASS_SUBHEADING_LABEL;
import static wbdk.gui.WBDK_GUI.PRIMARY_STYLE_SHEET;

/**
 *
 * @author Sheryar
 */
public class PlayerScreenDialog extends Stage {

    // THIS IS THE OBJECT DATA BEHIND THIS UI
    Player player;

    Team t;

    PlayerScreenEditController playerController;

    MessageDialog messageDialog;

    // GUI CONTROLS FOR OUR DIALOG
    GridPane gridPane;
    FlowPane checkPane;
    Scene dialogScene;

    Label headingLabel;
    Label firstNameLabel;
    TextField firstNameTextField;
    Label lastNameLabel;
    TextField lastNameTextField;
    Label teamLabel;
    ComboBox teamComboBox;

    CheckBox postionC;
    CheckBox postion1B;
    CheckBox postion3B;
    CheckBox postion2B;
    CheckBox postionSS;
    CheckBox postionOF;
    CheckBox postionP;
    Label postionPLabel;

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

    Button completeButton;
    Button cancelButton;

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
    public static final String FIRST_NAME_PROMPT = "First Name: ";
    public static final String LAST_NAME_PROMPT = "Last Name: ";
    public static final String TEAM_PROMPT = "Pro Team: ";
    public static final String POSITION_C_PROMPT = "C";
    public static final String POSITION_1B_PROMPT = "1B";
    public static final String POSITION_3B_PROMPT = "3B";
    public static final String POSITION_2B_PROMPT = "2B";
    public static final String POSITION_SS_PROMPT = "SS";
    public static final String POSITION_OF_PROMPT = "OF";
    public static final String POSITION_P_PROMPT = "P";
    public static final String PLAYER_HEADING = "Player Details";
    public static final String ADD_PLAYER_TITLE = "Add New Player";
    public static final String EDIT_PLAYER_TITLE = "Edit Player";

    /**
     * Initializes this dialog so that it can be used for either adding new
     * lectures or editing existing ones.
     *
     * @param primaryStage The owner of this modal dialog.
     * @param course
     * @param messageDialog
     */
    public PlayerScreenDialog(Stage primaryStage, MessageDialog initMessageDialog) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);

        messageDialog = initMessageDialog;

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

    public Player getPlayer() {
        return player;
    }

    public Team getTeam() {
        return t;
    }

    /**
     * This method loads a custom message into the label and then pops open the
     * dialog.
     *
     * @return
     */
    public Player showAddPlayerDialog() {
        // SET THE DIALOG TITLE
        setTitle(ADD_PLAYER_TITLE);

        // RESET THE LECTURES OBJECT WITH DEFAULT VALUES
        player = new Player();

        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        checkPane = new FlowPane();

        // PUT THE HEADING IN THE GRID, NOTE THAT THE TEXT WILL DEPEND
        // ON WHETHER WE'RE ADDING OR EDITING
        headingLabel = new Label(PLAYER_HEADING);
        headingLabel.getStyleClass().add(CLASS_HEADING_LABEL);

        // NOW THE FIRST NAME OF THE PLAYER
        firstNameLabel = new Label(FIRST_NAME_PROMPT);
        firstNameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        firstNameTextField = new TextField();
        firstNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = Character.toUpperCase(newValue.charAt(0)) + newValue.substring(1);
            player.setFirstName(newValue);
        });

        // NOW THE LAST NAME OF THE PLAYER
        lastNameLabel = new Label(LAST_NAME_PROMPT);
        lastNameLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        lastNameTextField = new TextField();
        lastNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            newValue = Character.toUpperCase(newValue.charAt(0)) + newValue.substring(1);
            player.setLastName(newValue);
        });

        // NOW SELECTION OF THE PRO TEAM
        teamLabel = new Label(TEAM_PROMPT);
        teamLabel.getStyleClass().add(CLASS_PROMPT_LABEL);
        ObservableList<String> team = FXCollections.observableArrayList("ATL", "AZ", "CHC", "CIN", "COL", "LAD", "MIA",
                "MIL", "NYM", "PHI", "PIT", "SD", "SF", "STL", "TOR", "WSH");
        teamComboBox = new ComboBox();
        teamComboBox.getItems().addAll(team);
        teamComboBox.setValue(team.get(0));
        player.setTeam(team.get(0));
        teamComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            String s = newValue.toString();
            player.setTeam(s);
        });

        postionC = new CheckBox(POSITION_C_PROMPT);
        postionC.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (player.getQp() != null) {
                postionP.setSelected(false);
            }
        });

        postion1B = new CheckBox(POSITION_1B_PROMPT);
        postion1B.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (player.getQp() != null) {
                postionP.setSelected(false);
            }
        });

        postion3B = new CheckBox(POSITION_3B_PROMPT);
        postion3B.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (player.getQp() != null) {
                postionP.setSelected(false);
            }
        });

        postion2B = new CheckBox(POSITION_2B_PROMPT);
        postion2B.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (player.getQp() != null) {
                postionP.setSelected(false);
            }
        });

        postionSS = new CheckBox(POSITION_SS_PROMPT);
        postionSS.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (player.getQp() != null) {
                postionP.setSelected(false);
            }
        });

        postionOF = new CheckBox(POSITION_OF_PROMPT);
        postionOF.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (player.getQp() != null) {
                postionP.setSelected(false);
            }
        });

        postionP = new CheckBox(POSITION_P_PROMPT);
        postionPLabel = new Label("POSITION_P_PROMPT");
        postionP.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (postionP.isSelected()) {
                postionOF.setSelected(false);
                postionSS.setSelected(false);
                postion2B.setSelected(false);
                postion3B.setSelected(false);
                postion1B.setSelected(false);
                postionC.setSelected(false);
                postionP.setSelected(true);
            }
        });

        // AND FINALLY, THE BUTTONS
        completeButton = new Button(COMPLETE);
        cancelButton = new Button(CANCEL);

        // REGISTER EVENT HANDLERS FOR OUR BUTTONS
        EventHandler completeHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {

            if (!firstNameTextField.getText().equalsIgnoreCase("") && !lastNameTextField.getText().equalsIgnoreCase("")
                    && teamComboBox.getValue() != null && (postionC.isSelected() || postion1B.isSelected()
                    || postion2B.isSelected() || postion3B.isSelected() || postionSS.isSelected() || postionOF.isSelected()
                    || postionP.isSelected())) {

                if (postionP.isSelected()) {
                    String s = postionP.getText();
                    player.setQp(s);
                }

                if (postionC.isSelected()) {
                    if (player.getQp().isEmpty()) {
                        String s = postionC.getText();
                        player.setQp(s);
                    } else {
                        String s = player.getQp() + "_" + postionC.getText();
                        player.setQp(s);
                    }
                }

                if (postion1B.isSelected()) {
                    if (player.getQp().isEmpty()) {
                        String s = postion1B.getText();
                        player.setQp(s);
                    } else {
                        String s = postion1B.getText();
                        player.setQp(s);
                    }
                }
                if (postion2B.isSelected()) {
                    if (player.getQp().isEmpty()) {
                        player.setQp(postion2B.getText());
                    } else {
                        player.setQp(player.getQp() + "_" + postion2B.getText());
                    }
                }
                if (postion3B.isSelected()) {
                    if (player.getQp().isEmpty()) {
                        player.setQp(postion3B.getText());
                    } else {
                        player.setQp(player.getQp() + "_" + postion3B.getText());
                    }
                }
                if (postionSS.isSelected()) {
                    if (player.getQp().isEmpty()) {
                        player.setQp(postionSS.getText());
                    } else {
                        player.setQp(player.getQp() + "_" + postionSS.getText());
                    }
                }
                if (postionOF.isSelected()) {
                    if (player.getQp().isEmpty()) {
                        player.setQp(postionOF.getText());
                    } else {
                        player.setQp(player.getQp() + "_" + postionOF.getText());
                    }
                }
                if (!postionP.isSelected()) {
                    player.setQp(player.getQp() + "_" + "U");
                }
                Button sourceButton = (Button) ae.getSource();

                selection = sourceButton.getText();
                hide();
            } else {
                messageDialog.show("Incompelete Fields");
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

        checkPane.getChildren().addAll(postionC, postion1B, postion3B, postion2B,
                postionSS, postionOF, postionP);
        checkPane.setHgap(10.0);

        // NOW LET'S ARRANGE THEM ALL AT ONCE
        gridPane.add(headingLabel, 0, 0, 2, 1);
        gridPane.add(firstNameLabel, 0, 1, 1, 1);
        gridPane.add(firstNameTextField, 1, 1, 1, 1);
        gridPane.add(lastNameLabel, 0, 2, 1, 1);
        gridPane.add(lastNameTextField, 1, 2, 1, 1);
        gridPane.add(teamLabel, 0, 3, 1, 1);
        gridPane.add(teamComboBox, 1, 3, 1, 1);
        gridPane.add(checkPane, 0, 4, 2, 1);
        gridPane.add(completeButton, 0, 5, 1, 1);
        gridPane.add(cancelButton, 1, 5, 1, 1);

        // AND PUT THE GRID PANE IN THE WINDOW
        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);

        // AND OPEN IT UP
        this.showAndWait();

        return player;
    }

    public void loadGUIData() {
        // LOAD THE UI STUFF
        firstNameTextField.setText(player.getFirstName());
        lastNameTextField.setText(player.getLastName());

    }

    public boolean wasCompleteSelected() {

        return selection.equals(COMPLETE);
    }

    public boolean checkContents() {
        if (fantasyComboBox.getSelectionModel().getSelectedItem().equals("Free Agent")) {
            return false;
        } else {
            return true;
        }
    }

    public void showEditPlayerDialog(Player playerToEdit, WBDKDataManager dataManager) {

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
        String playerPosition = playerToEdit.getQp();
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

            positionComboBox.getSelectionModel().clearSelection();
            positionComboBox.getItems().clear();

            ObservableList<String> position = FXCollections.observableArrayList();
            String positions = playerToEdit.getQp();

            if (positions.contains("C")) {
                for (int j = 0; j < dataManager.getDraft().getTeam().get(i).getTeamPlayers().size(); j++) {
                    if (dataManager.getDraft().getTeam().get(i).getTeamPlayers().get(j).getPositionPlaying().equalsIgnoreCase("C")) {
                        positionC_counter++;
                        counterC++;
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
                if (positionMI_counter <= 1) {
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
        t.setProTeam(playerToEdit.getTeam());
        t.setQPosition(playerToEdit.getQp());
        t.setYearOfBirth(playerToEdit.getYearOfBirth());
        t.setRW(playerToEdit.getRW());
        t.setHRSV(playerToEdit.getHR_SV());
        t.setRBIK(playerToEdit.getRBIK());
        t.setSBERA(playerToEdit.getSBERA());
        t.setBAWHIP(playerToEdit.getBAWHIP());
        t.setNotes(playerToEdit.getNotes());
        t.setNation(playerToEdit.getNation());
        t.setEstimatedValue(playerToEdit.getEstimatedValue());

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
