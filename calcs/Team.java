package calcs;
import data.Request;
public class Team {
    private final int teamNumber;
    private final String teamName;
    private int totalSpeakerNotes = 0;
    private int totalAmpNotes = 0;
    private int totalAutoNotes = 0;
    private int totalNotesPassed = 0;
    private int totalTrapNotes = 0;
    private int defenseCounter = 0;
    private int supportCounter = 0;
    private int offenseCounter = 0;
    private int climbCounter = 0;
    private int mainAmpCounter = 0;
    private int totalMatches = 0;

    public Team(int teamNumber, String teamName) {
        this.teamNumber = teamNumber;
        this.teamName = teamName;
    }

    public Team(int teamNumber) {
        this(teamNumber, Request.getTeamName(teamNumber));
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
        totalAutoNotes += autoNotes;
        totalMatches++;
        totalTrapNotes += trapNotes;

        if (climbed)
            climbCounter++;

        if (role.equalsIgnoreCase("O")) {
            offenseCounter++;
            totalSpeakerNotes += speakerNotes;
            //totalAmpNotes += ampNotes;
        }

        if (role.equalsIgnoreCase("A")) {
            mainAmpCounter++;
            totalAmpNotes += ampNotes;
        }

        if (role.equalsIgnoreCase("D")) {
            defenseCounter++;
        }

        if (role.equalsIgnoreCase("S")) {
            supportCounter++;
            totalNotesPassed += notesPassed;
        }
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTotalSpeakerNotes() {
        return this.totalSpeakerNotes;
    }

    public int getTotalAmpNotes() {
        return this.totalAmpNotes;
    }

    public int getTotalAutoNotes() {
        return this.totalAutoNotes;
    }

    public int getTotalNotesPassed() {
        return this.totalNotesPassed;
    }

    public int getTotalTrapNotes() {
        return this.totalTrapNotes;
    }

    public int getDefenseCount() {
        return this.defenseCounter;
    }

    public int getSupportCount() {
        return this.supportCounter;
    }

    public int getOffenseCount() {
        return this.offenseCounter;
    }

    public int getClimbCount() {
        return this.climbCounter;
    }

    public int getMainAmpCount() {
        return this.mainAmpCounter;
    }

    public int getTotalMatches() {
        return this.totalMatches;
    }

    /**
     * @return the average speaker notes scored when the robot was playing offense or amp
     */
    public double getCalculatedAverageSpeakerNotes() {
        return (offenseCounter == 0) ? -1 : (double) totalSpeakerNotes / offenseCounter;
    }

    /**
     * @return the average trap notes scored when the robot was playing offense or amp
     */
    public double getCalculatedAverageTrapNotes() {
        return (totalMatches == 0) ? -1 : (double) (totalTrapNotes / totalMatches);
    }

    /**
     * @return the average Amp notes scored when the robot was playing offense or amp
     */
    public double getCalculatedAverageAmpNotes() {
        return (mainAmpCounter == 0) ? -1 : (double) (totalAmpNotes / mainAmpCounter);
    }

    /**
     * @return the average notes scored in Auto
     */
    public double getCalculatedAverageAutoNotes() {
        return (totalMatches == 0) ? -1 : (double) (totalAutoNotes / totalMatches);
    }

    /**
     * @return the average notes Passed when the robot was playing support
     */
    public double getCalculatedAverageNotesPassed() {
        return (supportCounter == 0) ? -1 : (double) (totalNotesPassed / supportCounter);
    }

    /**
     * @return the chance of the robot climbing 0-1
     */
    public double getCalculatedAverageClimb() {
        return (totalMatches == 0) ? -1 : ((double)climbCounter / (int)totalMatches);
    }
}