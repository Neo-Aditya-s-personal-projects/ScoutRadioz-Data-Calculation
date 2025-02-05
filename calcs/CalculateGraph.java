package calcs;

import java.util.ArrayList;


public class CalculateGraph {
    
    public static int[] getTeamNumbers(Team[] teams) {
        int[] teamNumbers = new int[teams.length];
        for (int i = 0; i < teams.length; i++) teamNumbers[i] = teams[i].getTeamNumber();
        return teamNumbers;
    }

    public static ArrayList<Number>[][] getDataList(Team[] teams) {
        ArrayList<Number>[][] data = new ArrayList[teams.length][];
        for (int i = 0; i < teams.length; i++) {
            ArrayList<Number> teamNumber = new ArrayList<>();
            teamNumber.add(teams[i].getTeamNumber());
            ArrayList<Number>[] teamData = new ArrayList[Team.getDataNames().length];
            try {
                teamData[0] = teamNumber;
                for (int j = 0; j < Team.getDataNames().length; j++) {
                    teamData[j + 1] = teams[i].getDataHistory()[j];
                }
                data[i] = teamData;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return data; 
    }
}
