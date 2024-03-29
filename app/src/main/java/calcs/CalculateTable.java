package calcs;

public class CalculateTable {
    
    public static double[][] getDataDoubles(Team[] teams) {
        double[][] data = new double[teams.length][];
        for (int i = 0; i < teams.length; i++) {
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
        return data;
    }

    public static Object[][] getDataObjects(Team[] teams) {
        return doublestoObjects(getDataDoubles(teams));
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
