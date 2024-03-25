public class Team {
    private final int teamNumber;
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

    public Team(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    /**
     * This adds a new Match's Data and updates the data to match
     * 
     * @param role the robot's role "D" for Defense, "A" for Main Amp, "S" for
     *             Support, & "O" for Offense
     */
    public void appendData(String role, int speakerNotes, int ampNotes, int autoNotes, int notesPassed, int trapNotes, boolean climbed) {
        totalMatches++;
        if (climbed)
            climbCounter++;

        if (role.equals("O")) {
            offenseCounter++;
            totalSpeakerNotes += speakerNotes;
            totalAmpNotes += ampNotes;
            totalAutoNotes += autoNotes;
            totalTrapNotes += trapNotes;
        }

        if (role.equals("A")) {
            offenseCounter++;
            mainAmpCounter++;
            totalSpeakerNotes += speakerNotes;
            totalAmpNotes += ampNotes;
            totalAutoNotes += autoNotes;
            totalTrapNotes += trapNotes;
        }

        if (role.equals("D"))
            defenseCounter++;

        if (role.equals("S")) {
            supportCounter++;
            totalNotesPassed += notesPassed;
        }
    }

    public int teamNumber() {
        return teamNumber;
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

    public double getCalculatedAverageSpeakerNotes() {
        return (double) totalSpeakerNotes / offenseCounter; 
    }

    public double getCalculatedAverageTrapNotes() {
        return (double) totalTrapNotes / offenseCounter; 
    }

    public double getCalculatedAverageAmpNotes() {
        return (double) totalAmpNotes / offenseCounter; 
    }

    public double getCalculatedAverageAutoNotes() {
        return (double) totalAutoNotes / totalMatches; 
    }

    public double getCalculatedAverageNotesPassed() {
        return (double) totalNotesPassed / supportCounter; 
    }
}