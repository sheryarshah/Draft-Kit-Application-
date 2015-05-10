/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdk.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import static wbdk.WBDK_StartupConstants.PATH_DRAFT;
import wbdk.data.Draft;
import wbdk.data.Player;
import wbdk.data.Team;

/**
 * This is a JsonWBDKFileManager that uses the JSON file format to implement the
 * necessary functions for loading and saving different data for our players and
 * draft,
 *
 * @author Sheryar
 */
public class JsonWBDKFileManager implements WBDKFileManager {

    // JSON FILE READING AND WRITING CONSTANTS
    String JSON_PLAYERS = "Players";
    String JSON_FANTASY_TEAMS = "FantasyTeam";
    String JSON_DRAFT_NAME = "Draft Name";

    //Hitters
    String JSON_HITTERS = "Hitters";
    String JSON_QP = "QP";
    String JSON_AB = "AB";
    String JSON_R = "R";
    String JSON_HR = "HR";
    String JSON_RBI = "RBI";
    String JSON_SB = "SB";

    //Pitchers
    String JSON_PITCHERS = "Pitchers";
    String JSON_IP = "IP";
    String JSON_ER = "ER";
    String JSON_W = "W";
    String JSON_SV = "SV";
    String JSON_BB = "BB";
    String JSON_K = "K";

    //In both pitchers and hitters
    String JSON_TEAM = "TEAM";
    String JSON_LAST_NAME = "LAST_NAME";
    String JSON_FIRST_NAME = "FIRST_NAME";
    String JSON_H = "H";
    String JSON_NOTES = "NOTES";
    String JSON_NATION = "NATION";
    String JSON_YEAR_OF_BIRTH = "YEAR_OF_BIRTH";
    String JSON_NATION_OF_BIRTH = "NATION_OF_BIRTH";
    String JSON_RW = "R/W";
    String JSON_HRSV = "HR/SV";
    String JSON_RBIK = "RBI/K";
    String JSON_SBERA = "SB/ERA";
    String JSON_BAWHIP = "BA/WHIP";
    String JSON_ESTIMATED_VALUE = "Estimated Value";

    String JSON_TEAM_NAME = "TEAM_NAME";
    String JSON_TEAM_OWNER = "TEAM_OWNER";
    String JSON_POSITION_PLAYING = "POSITION";
    String JSON_PRO_TEAM = "PRO_TEAM";
    String JSON_CONTRACT = "CONTRACT";
    String JSON_SALARY = "SALARY";
    String JSON_PICK = "PICK";
    String JSON_TEAM_PLAYERS = "TEAM_PLAYERS";

    String JSON_EXT = ".json";
    String SLASH = "/";

    JsonArray jsonFantasyTeamsPlayersArray;

    @Override
    public void loadHitters(Draft playerToLoad, String jsonFilePath) throws IOException {
        // LOAD THE JSON FILE FOR HITTERS WITH ALL THE DATA
        JsonObject json = loadJSONFile(jsonFilePath);

        DecimalFormat dF = new DecimalFormat(".000");
        dF.setRoundingMode(RoundingMode.DOWN);

        // GET THE HITTERS
        JsonArray jsonHittersArray = json.getJsonArray(JSON_HITTERS);
        for (int i = 0; i < jsonHittersArray.size(); i++) {
            JsonObject jso = jsonHittersArray.getJsonObject(i);

            Player hitters = new Player();
            hitters.setTeam(jso.getString(JSON_TEAM));
            hitters.setLastName(jso.getString(JSON_LAST_NAME));
            hitters.setFirstName(jso.getString(JSON_FIRST_NAME));
            hitters.setQp(jso.getString(JSON_QP).concat("_U"));
            hitters.setAb(Integer.valueOf(jso.getString(JSON_AB)));
            hitters.setRW(Integer.valueOf(jso.getString(JSON_R)));
            hitters.setH(Integer.valueOf(jso.getString(JSON_H)));
            hitters.setHRSV(Integer.valueOf(jso.getString(JSON_HR)));
            hitters.setRBIK(Integer.valueOf(jso.getString(JSON_RBI)));
            hitters.setSBERA(Double.parseDouble(jso.getString(JSON_SB)));
            hitters.setNotes(jso.getString(JSON_NOTES));
            hitters.setYearOfBirth(Integer.valueOf(jso.getString(JSON_YEAR_OF_BIRTH)));
            hitters.setNation(jso.getString(JSON_NATION_OF_BIRTH));

            //calculating batting average while reading hitters json file
            if (hitters.getAb() > 0) {
                double ba1 = hitters.getH() / (hitters.getAb() + .0);
                String baS = dF.format(ba1);
                double ba = Double.parseDouble(baS);
                hitters.setBAWHIP(ba);
            } else {
                hitters.setBAWHIP(0.00);
            }

            playerToLoad.addPlayer(hitters);

        }
    }

    @Override
    public void loadPitchers(Draft playerToLoad, String jsonFilePath) throws IOException {
        // LOAD THE JSON FILE FOR PITCHERS WITH ALL THE DATA
        JsonObject json = loadJSONFile(jsonFilePath);

        DecimalFormat dF = new DecimalFormat(".00");
        dF.setRoundingMode(RoundingMode.UP);

        // GET THE PITCHERS
        JsonArray jsonHittersArray = json.getJsonArray(JSON_PITCHERS);
        for (int i = 0; i < jsonHittersArray.size(); i++) {
            JsonObject jso = jsonHittersArray.getJsonObject(i);

            Player pitchers = new Player();
            pitchers.setTeam(jso.getString(JSON_TEAM));
            pitchers.setLastName(jso.getString(JSON_LAST_NAME));
            pitchers.setFirstName(jso.getString(JSON_FIRST_NAME));
            pitchers.setQp("P");
            pitchers.setIp(Double.parseDouble(jso.getString(JSON_IP)));
            pitchers.setEr(Integer.valueOf(jso.getString(JSON_ER)));
            pitchers.setRW(Integer.valueOf(jso.getString(JSON_W)));
            pitchers.setHRSV(Integer.valueOf(jso.getString(JSON_SV)));
            pitchers.setH(Integer.valueOf(jso.getString(JSON_H)));
            pitchers.setBb(Integer.valueOf(jso.getString(JSON_BB)));
            pitchers.setRBIK(Integer.valueOf(jso.getString(JSON_K)));
            pitchers.setNotes(jso.getString(JSON_NOTES));
            pitchers.setYearOfBirth(Integer.valueOf(jso.getString(JSON_YEAR_OF_BIRTH)));
            pitchers.setNation(jso.getString(JSON_NATION_OF_BIRTH));

            //calculating WHIP
            if (pitchers.getIp() > 0) {
                double whip1 = (pitchers.getH() + pitchers.getBb()) / (pitchers.getIp() + .0);
                String whipS = dF.format(whip1);
                double whip = Double.parseDouble(whipS);
                pitchers.setBAWHIP(whip);
            } else {
                pitchers.setBAWHIP(0.00);
            }

            //calculating ERA
            if (pitchers.getIp() > 0) {
                double era1 = (pitchers.getEr() * 9) / (pitchers.getIp() + .0);
                String eraS = dF.format(era1);
                double era = Double.parseDouble(eraS);
                pitchers.setSBERA(era);
            } else {
                pitchers.setSBERA(0.00);
            }

            playerToLoad.addPlayer(pitchers);

        }
    }

    // LOADS A JSON FILE AS A SINGLE OBJECT AND RETURNS IT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }

    /**
     * Loads the courseToLoad argument using the data found in the json file.
     *
     * @param draftToLoad
     * @param courseToLoad Course to load.
     * @param jsonFilePath File containing the data to load.
     *
     * @throws IOException Thrown when IO fails.
     */
    @Override
    public void loadDraft(Draft draftToLoad, String jsonFilePath) throws IOException {
        // LOAD THE JSON FILE WITH ALL THE DATA
        JsonObject json = loadJSONFile(jsonFilePath);

        // NOW LOAD THE COURSE
        //draftToLoad.setDraftName(json.getString(JSON_NAME));
        // GET THE PLAYERS
        JsonArray jsonPlayersArray = json.getJsonArray(JSON_PLAYERS);
        draftToLoad.clearPlayers();

        for (int i = 0; i < jsonPlayersArray.size(); i++) {
            JsonObject jso = jsonPlayersArray.getJsonObject(i);
            Player p = new Player();
            p.setTeam(jso.getString(JSON_TEAM));
            p.setLastName(jso.getString(JSON_LAST_NAME));
            p.setFirstName(jso.getString(JSON_FIRST_NAME));
            p.setQp(jso.getString(JSON_QP));
            p.setYearOfBirth(jso.getInt(JSON_YEAR_OF_BIRTH));
            p.setRW(jso.getInt(JSON_RW));
            p.setHRSV(jso.getInt(JSON_HRSV));
            p.setRBIK(jso.getInt(JSON_RBIK));
            p.setSBERA(Double.parseDouble(jso.getString(JSON_SBERA)));
            p.setBAWHIP(Double.parseDouble(jso.getString(JSON_BAWHIP)));
            p.setNotes(jso.getString(JSON_NOTES));
            p.setNation(jso.getString(JSON_NATION));

            // ADD IT TO THE DRAFT
            draftToLoad.addPlayer(p);
        }

        //GET THE TEAM AND ITS PLAYERS
        JsonArray jsonFantasyTeamsArray = json.getJsonArray(JSON_FANTASY_TEAMS);

        for (int i = 0; i < draftToLoad.getTeam().size(); i++) {
            draftToLoad.getTeam().get(i).clearTeamPlayers();
        }

        for (int i = 0; i < jsonFantasyTeamsArray.size(); i++) {
            JsonObject jso = jsonFantasyTeamsArray.getJsonObject(i);
            Team t1 = new Team();
            t1.setName(jso.getString(JSON_TEAM_NAME));
            t1.setOwner(jso.getString(JSON_TEAM_OWNER));
            draftToLoad.addTeam(t1);

            jsonFantasyTeamsPlayersArray = jso.getJsonArray(JSON_TEAM_PLAYERS);

            for (int j = 0; j < jsonFantasyTeamsPlayersArray.size(); j++) {
                JsonObject jso1 = jsonFantasyTeamsPlayersArray.getJsonObject(j);
                Team t = new Team();
                t.setPositionPlaying(jso1.getString(JSON_POSITION_PLAYING));
                t.setFirstName(jso1.getString(JSON_FIRST_NAME));
                t.setLastName(jso1.getString(JSON_LAST_NAME));
                t.setProTeam(jso1.getString(JSON_PRO_TEAM));
                t.setQPosition(jso1.getString(JSON_QP));
                t.setRW(jso1.getInt(JSON_RW));
                t.setHRSV(jso1.getInt(JSON_HRSV));
                t.setRBIK(jso1.getInt(JSON_RBIK));
                t.setSBERA(Double.parseDouble(jso1.getString(JSON_SBERA)));
                t.setBAWHIP(Double.parseDouble(jso1.getString(JSON_BAWHIP)));
                t.setContract(jso1.getString(JSON_CONTRACT));
                t.setSalary(jso1.getInt(JSON_SALARY));
                t.setNation(jso1.getString(JSON_NATION));
                t.setPick(jso1.getInt(JSON_PICK));
                draftToLoad.getTeam().get(i).addTeamPlayers(t);
            }

        }
    }

    /**
     * This method saves all the data associated with a course to a JSON file.
     *
     * @param draftToSave
     * @param courseToSave The course whose data we are saving.
     *
     * @throws IOException Thrown when there are issues writing to the JSON
     * file.
     */
    @Override
    public void saveDraft(Draft draftToSave) throws IOException {
        // BUILD THE FILE PATH
        String courseListing = "" + draftToSave.getDraftName();
        String jsonFilePath = PATH_DRAFT + SLASH + courseListing + JSON_EXT;

        // INIT THE WRITER
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonWriter = Json.createWriter(os);

        // MAKE A JSON ARRAY FOR THE PLAYERS ARRAY
        JsonArray playerJsonArray = makePlayersJsonArray(draftToSave.getPlayers());

        // MAKE A JSON ARRAY FOR THE TEAM ARRAY
        JsonArray teamJsonArray = makeTeamJsonArray(draftToSave.getTeam());

        // NOW BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
        JsonObject draftJsonObject = Json.createObjectBuilder()
                .add(JSON_PLAYERS, playerJsonArray)
                .add(JSON_FANTASY_TEAMS, teamJsonArray)
                .build();

        // AND SAVE EVERYTHING AT ONCE
        jsonWriter.writeObject(draftJsonObject);
    }

    // MAKES AND RETURNS A JSON OBJECT FOR THE PROVIDED LECTURE
    private JsonObject makePlayerJsonObject(Player player) {
        double sbEra = player.getSBERA();
        String sbera = Double.toString(sbEra);
        double baWhip = player.getBAWHIP();
        String bawhip = Double.toString(baWhip);
        JsonObject jso = Json.createObjectBuilder().add(JSON_TEAM, player.getTeam())
                .add(JSON_LAST_NAME, player.getLastName())
                .add(JSON_FIRST_NAME, player.getFirstName())
                .add(JSON_QP, player.getQp())
                .add(JSON_YEAR_OF_BIRTH, player.getYearOfBirth())
                .add(JSON_RW, player.getRW())
                .add(JSON_HRSV, player.getHR_SV())
                .add(JSON_RBIK, player.getRBIK())
                .add(JSON_SBERA, sbera)
                .add(JSON_BAWHIP, bawhip)
                .add(JSON_NOTES, player.getNotes())
                .add(JSON_NATION, player.getNation())
                .build();
        return jso;
    }

    // MAKE AN ARRAY OF PLAYERS
    private JsonArray makePlayersJsonArray(ObservableList<Player> data) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Player p : data) {
            jsb.add(makePlayerJsonObject(p));
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    // MAKE AN ARRAY OF TEAMS
    private JsonArray makeTeamJsonArray(ObservableList<Team> data) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Team t : data) {
            jsb.add(makeTeamJsonObject(t));
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    // MAKES AND RETURNS A JSON OBJECT FOR THE PROVIDED TEAM
    private JsonObject makeTeamJsonObject(Team team) {
        JsonArrayBuilder players = Json.createArrayBuilder();
        ObservableList<Team> teamPlayers = team.getTeamPlayers();
        for (Team ti : teamPlayers) {
            players.add(makeTeamJsonObject1(ti));
        }
        JsonObject jso = Json.createObjectBuilder().add(JSON_TEAM_NAME, team.getName())
                .add(JSON_TEAM_OWNER, team.getOwner())
                .add(JSON_TEAM_PLAYERS, players)
                .build();

        return jso;
    }

    // MAKES AND RETURNS A JSON OBJECT FOR THE PROVIDED TEAM
    private JsonObject makeTeamJsonObject1(Team team) {
        double sbEra = team.getSBERA();
        String sbera = Double.toString(sbEra);
        double baWhip = team.getBAWHIP();
        String bawhip = Double.toString(baWhip);
        JsonObject jso = Json.createObjectBuilder()
                .add(JSON_POSITION_PLAYING, team.getPositionPlaying())
                .add(JSON_FIRST_NAME, team.getFirstName())
                .add(JSON_LAST_NAME, team.getLastName())
                .add(JSON_PRO_TEAM, team.getProTeam())
                .add(JSON_QP, team.getQPosition())
                .add(JSON_RW, team.getRW())
                .add(JSON_HRSV, team.getHR_SV())
                .add(JSON_RBIK, team.getRBIK())
                .add(JSON_SBERA, sbera)
                .add(JSON_BAWHIP, bawhip)
                .add(JSON_CONTRACT, team.getContract())
                .add(JSON_SALARY, team.getSalary())
                .add(JSON_NATION, team.getNation())
                .add(JSON_PICK, team.getPick())
                .build();

        return jso;
    }

}
