package calcs;

public class CalculateTable {
    
    public static double[][] getDataDoubles(Team[] teams, String setting) {
        double[][] data = new double[teams.length][];
        for (int i = 0; i < teams.length; i++) {
            switch (setting) {
                case  "Average" -> {
                    double[] teamData = {
                        teams[i].getTeamNumber(), 
                        teams[i].getCalculatedAverageAutoNotes(), 
                        teams[i].getCalculatedAverageAmpNotes(),
                        teams[i].getCalculatedAverageSpeakerNotes(),
                        teams[i].getCalculatedAverageClimb(),
                        teams[i].getCalculatedAverageNotesPassed(),
                        teams[i].getCalculatedAverageTrapNotes(),
                        teams[i].getTotalMatches(),
                        teams[i].getDefenseCount()
                    };
                    data[i] = teamData;
                }
                case "Max" -> {
                    double[] teamData = {
                        teams[i].getTeamNumber(), 
                        teams[i].getMaxAutoNotes(), 
                        teams[i].getMaxAmpNotes(),
                        teams[i].getMaxSpeakerNotes(),
                        teams[i].getMaxClimb(),
                        teams[i].getMaxNotesPassed(),
                        teams[i].getMaxTrapNotes(),
                        teams[i].getTotalMatches(),
                        teams[i].getDefenseCount()
                    };
                    data[i] = teamData;
                }
                case "Min" -> {
                    double[] teamData = {
                        teams[i].getTeamNumber(), 
                        teams[i].getMinAutoNotes(), 
                        teams[i].getMinAmpNotes(),
                        teams[i].getMinSpeakerNotes(),
                        teams[i].getMinClimb(),
                        teams[i].getMinNotesPassed(),
                        teams[i].getMinTrapNotes(),
                        teams[i].getTotalMatches(),
                        teams[i].getDefenseCount()
                    };
                    data[i] = teamData;
                }
            }
        }
        return data;
    }

    public static Object[][] getDataObjects(Team[] teams, String setting) {
        return doublestoObjects(getDataDoubles(teams, setting));
    }

    public static Object[][] doublestoObjects(double[][] doubles) {
        Object[][] objects = new Object[doubles.length][];
        for (int i = 0; i < doubles.length; i++) {
            objects[i] = new Object[doubles[i].length];
            for (int j = 0; j < doubles[i].length; j++) {
                objects[i][j] = doubles[i][j];
            }
        }
        return objects;
    }

    public static double[][] organizeData(double[][] teamData, int index, boolean highestToLowest) {
        double[][] organizedData = new double[teamData.length][];
        for(int i = 0; i < teamData.length; i++) {
            int tempIndex = 0;
            for (int j = 0; j < teamData.length; j++) {
                if(j != i) {
                    if((teamData[i][index] < teamData[j][index]) && highestToLowest) {
                        tempIndex++;
                    }
                    else if ((teamData[i][index] > teamData[j][index]) && !highestToLowest) {
                        tempIndex++;
                    }
                }
            }
            while (organizedData[tempIndex] != null) {
                tempIndex++;
            }
            organizedData[tempIndex] = teamData[i];
        }
        return organizedData;
    }
}
