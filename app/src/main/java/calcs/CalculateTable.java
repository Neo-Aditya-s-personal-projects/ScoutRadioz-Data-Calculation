package calcs;

public class CalculateTable {
    
    public Object[][] getData(Team[] teams) {
        Object[][] data = new Object[teams.length][];
        for (int i = 0; i < teams.length; i++) {
            Object[] teamData = {
                teams[i].getTeamNumber(), 
                teams[i].getCalculatedAverageAutoNotes(), 
                teams[i].getCalculatedAverageAmpNotes(),
                teams[i].getCalculatedAverageAutoNotes(),
                teams[i].getCalculatedAverageSpeakerNotes(),
                
            }
        }
        return data;
    }
}
