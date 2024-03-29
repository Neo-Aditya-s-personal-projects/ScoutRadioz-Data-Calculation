package calcs;

public class CalculateTable {
    
    public static double[][] getData(Team[] teams) {
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

    public static double[][] organizeData(double[][] teamData, int columnNumber) {
        double[][] organizedData = new double[teamData.length][];
        columnNumber--;
        for(int i = 0; i < teamData.length; i++) {
            int index = 0;
            for (int j = 0; j < teamData.length; j++) {
                if(j != i) {
                    if(teamData[i][columnNumber] < teamData[j][columnNumber]) {
                        index++;
                    }
                }
            }
            while (organizedData[index][0] != 0) {
                index++;
            }
            organizedData[index] = teamData[i];
        }
        return organizedData;
    }
}
