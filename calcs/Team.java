package calcs;
import Constants.Constants;
import data.Request;
import java.util.ArrayList;
public class Team {
    private final int teamNumber;
    private final String teamName;
    private final ArrayList<Integer> speaker = new ArrayList<>();
    private final ArrayList<Integer> amp = new ArrayList<>();
    private final ArrayList<Integer> auto = new ArrayList<>();
    private final ArrayList<Integer> pass = new ArrayList<>();
    private final ArrayList<Integer> trap = new ArrayList<>();
    private final ArrayList<Integer> climb = new ArrayList<>();
    private final ArrayList<Integer> defense = new ArrayList<>();
    private final ArrayList<Integer> support = new ArrayList<>();
    private final ArrayList<Integer> offense = new ArrayList<>();
    private final ArrayList<Integer> mainAmp = new ArrayList<>();
    private final int speakerDefault = -1;
    private final int ampDefault =  -1;
    private final int passDefault = -1;

    public Team(int teamNumber, String teamName) {
        this.teamNumber = teamNumber;
        this.teamName = teamName;
    }

    public Team(int teamNumber) {
        this(teamNumber, Constants.USE_TBA ? Request.getTeamName(teamNumber) : "N/A");
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

        climb.add(climbed ? 1 : 0);
        offense.add(role.equalsIgnoreCase("O") ? 1 : 0);
        defense.add(role.equalsIgnoreCase("D") ? 1 : 0);
        support.add(role.equalsIgnoreCase("S") ? 1 : 0);
        mainAmp.add(role.equalsIgnoreCase("A") ? 1 : 0);

        if (role.equalsIgnoreCase("O")) speaker.add(speakerNotes);

        if (role.equalsIgnoreCase("A")) amp.add(ampNotes);

        if (role.equalsIgnoreCase("S")) pass.add(notesPassed);
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTotalSpeakerNotes() {
        return getTotal(speaker);
    }

    public int getTotalAmpNotes() {
        return getTotal(amp);
    }

    public int getTotalAutoNotes() {
        return getTotal(auto);
    }

    public int getTotalNotesPassed() {
        return getTotal(pass);
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
        return (getOffenseCount() == 0) ? speakerDefault : getMin(speaker);
    }

    public double getMinTrapNotes() {
        return (getTotalMatches() == 0) ? -1 : getMin(trap);
    }

    public double getMinAmpNotes() {
        return (getMainAmpCount() == 0) ? ampDefault : getMin(amp);
    }

    public double getMinAutoNotes() {
        return (getTotalMatches() == 0) ? -1 : getMin(auto);
    }

    public double getMinNotesPassed() {
        return (getSupportCount() == 0) ? passDefault : getMin(pass);
    }

    public double getMinClimb() {
        return (getTotalMatches() == 0) ? -1 : getMin(climb);
    }

    public double getMaxSpeakerNotes() {
        return (getOffenseCount() == 0) ? speakerDefault : getMax(speaker);
    }

    public double getMaxTrapNotes() {
        return (getTotalMatches() == 0) ? -1 : getMax(trap);
    }

    public double getMaxAmpNotes() {
        return (getMainAmpCount() == 0) ? ampDefault : getMax(amp);
    }

    public double getMaxAutoNotes() {
        return (getTotalMatches() == 0) ? -1 : getMax(auto);
    }

    public double getMaxNotesPassed() {
        return (getSupportCount() == 0) ? passDefault : getMax(pass);
    }

    public double getMaxClimb() {
        return (getTotalMatches() == 0) ? -1 : getMax(climb);
    }

    public double getCalculatedAverageSpeakerNotes() {
        return (getOffenseCount() == 0) ? -1 : getAverage(speaker);
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
        return (getMainAmpCount() == 0) ? -1 : getAverage(amp);
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
        return (getSupportCount() == 0) ? -1 : getAverage(pass);
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