package calcs;
import constants.Constants;
import data.Request;
import java.util.ArrayList;
public class Team {
    private final int teamNumber;
    private final String teamName;
    
    private final ArrayList<Integer> auto;
    private final ArrayList<Integer> trap;
    private final ArrayList<Integer> climb;
    private final ArrayList<Integer> defense;
    private final ArrayList<Integer> support;
    private final ArrayList<Integer> offense;
    private final ArrayList<Integer> mainAmp;
    private final ArrayList<Integer> speaker;
    private final ArrayList<Integer> amp;
    private final ArrayList<Integer> pass;

    private final ArrayList<Integer> speakerFiltered;
    private final ArrayList<Integer> ampFiltered;
    private final ArrayList<Integer> passFiltered;

    private final int speakerDefault = -1;
    private final int ampDefault =  -1;
    private final int passDefault = -1;

    private static final String[] dataNames = {
        "Auto Notes", 
        "Trap Notes", 
        "Speaker Notes", 
        "Amp Notes", 
        "Notes Passed", 
        "Climbed", 
        "Main Defense", 
        "Main Support", 
        "Main Speaker", 
        "Main Amp"
    };

    public Team(int teamNumber, String teamName) {
        this.teamNumber = teamNumber;
        this.teamName = teamName;
        auto = new ArrayList<>();
        trap = new ArrayList<>();
        climb = new ArrayList<>();
        defense = new ArrayList<>();
        support = new ArrayList<>();
        offense = new ArrayList<>();
        mainAmp = new ArrayList<>();
        speaker = new ArrayList<>();
        amp = new ArrayList<>();
        pass = new ArrayList<>();

        speakerFiltered = new ArrayList<>();
        ampFiltered = new ArrayList<>();
        passFiltered = new ArrayList<>();
    }

    public Team(int teamNumber) {
        this(teamNumber, Constants.USE_TBA ? Request.getTeamName(teamNumber) : "N/A");
    }

    public Team(Team team) {
        teamNumber = team.getTeamNumber();
        teamName = team.getTeamName();

        auto = team.getAutoHistory();
        trap = team.getTrapHistory();
        climb = team.getClimbHistory();
        defense = team.getDefenseRoleHistory();
        support = team.getSupportRoleHistory();
        offense = team.getOffenseRoleHistory();
        mainAmp = team.getMainAmpRoleHistory();
        speaker = team.getSpeakerHistory();
        amp = team.getAmpHistory();
        pass = team.getPassHistory();

        speakerFiltered = team.getFilteredSpeakerHistory();
        ampFiltered = team.getFilteredAmpHistory();
        passFiltered = team.getFilteredPassHistory();
    }

    /**
     * This adds a new Match's Data and updates the data to match
     * 
     * @param role         The robot's role "D" for Defense, "A" for Main Amp, "S" for Support, & "O" for Offense
     * @param speakerNotes Amount of Speaker notes the robot scored in Teleop
     * @param ampNotes     Amount of Amp notes the robot scored in Teleop
     * @param autoNotes    Amount of Notes the robot scored in Auto
     * @param notesPassed  Amount of notes the robot passed in Teleop
     * @param trapNotes    Amount of Trap notes the robot scored
     * @param climbed      If the robot sucessfully climbed
     */
    public void appendData(String role, int speakerNotes, int ampNotes, int autoNotes, int notesPassed, int trapNotes, boolean climbed) {
        auto.add(autoNotes);
        trap.add(trapNotes);
        speaker.add(speakerNotes);
        amp.add(ampNotes);
        pass.add(notesPassed);

        climb.add(climbed ? 1 : 0);
        offense.add(role.equalsIgnoreCase("O") ? 1 : 0);
        defense.add(role.equalsIgnoreCase("D") ? 1 : 0);
        support.add(role.equalsIgnoreCase("S") ? 1 : 0);
        mainAmp.add(role.equalsIgnoreCase("A") ? 1 : 0);

        if (role.equalsIgnoreCase("O")) speakerFiltered.add(speakerNotes);

        if (role.equalsIgnoreCase("A")) ampFiltered.add(ampNotes);

        if (role.equalsIgnoreCase("S")) passFiltered.add(notesPassed);
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTotalSpeakerNotes() {
        return getTotal(speakerFiltered);
    }

    public int getTotalAmpNotes() {
        return getTotal(ampFiltered);
    }

    public int getTotalAutoNotes() {
        return getTotal(auto);
    }

    public int getTotalNotesPassed() {
        return getTotal(passFiltered);
    }

    public int getTotalTrapNotes() {
        return getTotal(trap);
    }

    public int getDefenseCount() {
        return getTotal(defense);
    }

    public int getSupportCount() {
        return getTotal(support);
    }

    public int getOffenseCount() {
        return getTotal(offense);
    }

    public int getClimbCount() {
        return getTotal(climb);
    }

    public int getMainAmpCount() {
        return getTotal(mainAmp);
    }

    public int getTotalMatches() {
        return auto.size();
    }

    public double getMinSpeakerNotes() {
        return (getOffenseCount() == 0) ? speakerDefault : getMin(speakerFiltered);
    }

    public double getMinTrapNotes() {
        return (getTotalMatches() == 0) ? -1 : getMin(trap);
    }

    public double getMinAmpNotes() {
        return (getMainAmpCount() == 0) ? ampDefault : getMin(ampFiltered);
    }

    public double getMinAutoNotes() {
        return (getTotalMatches() == 0) ? -1 : getMin(auto);
    }

    public double getMinNotesPassed() {
        return (getSupportCount() == 0) ? passDefault : getMin(passFiltered);
    }

    public double getMinClimb() {
        return (getTotalMatches() == 0) ? -1 : getMin(climb);
    }

    public double getMaxSpeakerNotes() {
        return (getOffenseCount() == 0) ? speakerDefault : getMax(speakerFiltered);
    }

    public double getMaxTrapNotes() {
        return (getTotalMatches() == 0) ? -1 : getMax(trap);
    }

    public double getMaxAmpNotes() {
        return (getMainAmpCount() == 0) ? ampDefault : getMax(ampFiltered);
    }

    public double getMaxAutoNotes() {
        return (getTotalMatches() == 0) ? -1 : getMax(auto);
    }

    public double getMaxNotesPassed() {
        return (getSupportCount() == 0) ? passDefault : getMax(passFiltered);
    }

    public double getMaxClimb() {
        return (getTotalMatches() == 0) ? -1 : getMax(climb);
    }

    public double getCalculatedAverageSpeakerNotes() {
        return (getOffenseCount() == 0) ? -1 : getAverage(speakerFiltered);
    }

    /**
     * @return the average trap notes scored when the robot was playing offense or amp
     */
    public double getCalculatedAverageTrapNotes() {
        return (getTotalMatches() == 0) ? -1 : getAverage(trap);
    }

    /**
     * @return the average Amp notes scored when the robot was playing offense or amp
     */
    public double getCalculatedAverageAmpNotes() {
        return (getMainAmpCount() == 0) ? -1 : getAverage(ampFiltered);
    }

    /**
     * @return the average notes scored in Auto
     */
    public double getCalculatedAverageAutoNotes() {
        return (getTotalMatches() == 0) ? -1 : getAverage(auto);
    }

    /**
     * @return the average notes Passed when the robot was playing support
     */
    public double getCalculatedAverageNotesPassed() {
        return (getSupportCount() == 0) ? -1 : getAverage(passFiltered);
    }

    /**
     * @return the chance of the robot climbing 0-1
     */
    public double getCalculatedAverageClimb() {
        return (getTotalMatches() == 0) ? -1 : getAverage(climb);
    }

    public ArrayList<Integer> getSpeakerHistory() {
        return new ArrayList<>(speaker);
    }

    public ArrayList<Integer> getAmpHistory() {
        return new ArrayList<>(amp);
    }

    public ArrayList<Integer> getAutoHistory() {
        return new ArrayList<>(auto);
    }

    public ArrayList<Integer> getPassHistory() {
        return new ArrayList<>(pass);
    }

    public ArrayList<Integer> getTrapHistory() {
        return new ArrayList<>(trap);
    }

    public ArrayList<Integer> getClimbHistory() {
        return new ArrayList<>(climb);
    }

    public ArrayList<Integer> getDefenseRoleHistory() {
        return new ArrayList<>(defense);
    }

    public ArrayList<Integer> getSupportRoleHistory() {
        return new ArrayList<>(support);
    }

    public ArrayList<Integer> getOffenseRoleHistory() {
        return new ArrayList<>(offense);
    }

    public ArrayList<Integer> getMainAmpRoleHistory() {
        return new ArrayList<>(mainAmp);
    }

    public ArrayList<Integer> getFilteredAmpHistory() {
        return new ArrayList<>(ampFiltered);
    }

    public ArrayList<Integer> getFilteredSpeakerHistory() {
        return new ArrayList<>(speakerFiltered);
    }

    public ArrayList<Integer> getFilteredPassHistory() {
        return new ArrayList<>(passFiltered);
    }

    public static String[] getDataNames() {
        return dataNames;
    }

    public ArrayList<Integer>[] getDataHistory() {
        return (new ArrayList[]{
            getAutoHistory(), 
            getTrapHistory(), 
            getSpeakerHistory(), 
            getAmpHistory(), 
            getPassHistory(), 
            getClimbHistory(), 
            getDefenseRoleHistory(), 
            getSupportRoleHistory(), 
            getOffenseRoleHistory(), 
            getMainAmpRoleHistory()
        });
    }

    private double getAverage(ArrayList<Integer> list) {
        double result = 0;
        for (int i = 0; i < list.size(); i++) result += list.get(i);
        return result / (double) list.size();
    }

    private int getTotal(ArrayList<Integer> list) {
        int result = 0;
        for (int i = 0; i < list.size(); i++) result += list.get(i);
        return result;
    }

    private int getMax(ArrayList<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i++) max = max < list.get(i) ? list.get(i) : max;
        return max;
    }

    private int getMin(ArrayList<Integer> list) {
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) max = max > list.get(i) ? list.get(i) : max;
        return max;
    }
}