/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk;

import java.io.IOException;
import java.util.Locale;
import javafx.application.Application;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static wbdk.WBDK_PropertyType.PROP_APP_TITLE;
import static wbdk.WBDK_StartupConstants.PATH_DATA;
import static wbdk.WBDK_StartupConstants.PROPERTIES_FILE_NAME;
import static wbdk.WBDK_StartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import wbdk.data.WBDKDataManager;
import wbdk.error.ErrorHandler;
import wbdk.file.JsonWBDKFileManager;
import wbdk.gui.WBDK_GUI;
import xml_utilities.InvalidXMLFileFormatException;

/**
 * Wolfie Ball Draft Kit application is JavaFX application.
 * @author Sheryar
 */
public class WolfieBallDraftKit extends Application{

    
    WBDK_GUI gui;
    WBDKDataManager dataManager;
    
    @Override
    public void start(Stage primaryStage) {
        // LET'S START BY GIVING THE PRIMARY STAGE TO OUR ERROR HANDLER
        ErrorHandler eH = ErrorHandler.getErrorHandler();
        eH.initMessageDialog(primaryStage);
        
        // LOAD APP SETTINGS INTO THE GUI AND START IT UP
        boolean success = loadProperties();
        if(success){
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String appTitle = props.getProperty(PROP_APP_TITLE);
            try{
                JsonWBDKFileManager jsonFileManager = new JsonWBDKFileManager();
                

                // AND NOW GIVE ALL OF THIS STUFF TO THE GUI
                // INITIALIZE THE USER INTERFACE COMPONENTS
                gui = new WBDK_GUI(primaryStage);
                gui.setDraftFileManager(jsonFileManager);
                
                dataManager = new WBDKDataManager(gui);
                gui.setDataManager(dataManager);
                
                 // FINALLY, START UP THE USER INTERFACE WINDOW AFTER ALL
                // REMAINING INITIALIZATION
                gui.initGUI(appTitle);
                
            }catch(IOException e){
                eH = ErrorHandler.getErrorHandler();
                eH.handlePropertiesFileError();
            }
        }
        
    }

    /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     * 
     * @return true if the properties file was loaded successfully, false otherwise.
     */
    public boolean loadProperties() {
        try {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            props.loadProperties(PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
       } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            ErrorHandler eH = ErrorHandler.getErrorHandler();
            eH.handlePropertiesFileError();
            return false;
        }        
    }
    
     /**
     * This is where program execution begins. Since this is a JavaFX app
     * it will simply call launch, which gets JavaFX rolling, resulting in
     * sending the properly initialized Stage (i.e. window) to our start
     * method in this class.
     * @param args
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        launch(args);
    }
    
}
