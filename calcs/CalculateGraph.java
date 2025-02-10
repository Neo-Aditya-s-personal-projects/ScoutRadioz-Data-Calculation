package calcs;

import java.util.ArrayList;


public class CalculateGraph {
    
    public static int[] getTeamNumbers(Team[] teams) {
        int[] teamNumbers = new int[teams.length];
        for (int i = 0; i < teams.length; i++) teamNumbers[i] = teams[i].getTeamNumber();
        return teamNumbers;
    }

    public static ArrayList<Integer>[][] getDataList(Team[] teams) {
        ArrayList<Integer>[][] data = new ArrayList[teams.length][];
        for (int i = 0; i < teams.length; i++) {
            ArrayList<Integer> teamNumber = new ArrayList<>();
            teamNumber.add(teams[i].getTeamNumber());
            ArrayList<Integer>[] teamData = new ArrayList[Team.getDataNames().length];
            teamData[0] = teamNumber;
            teamData[1] = teams[i].getAutoHistory();
            teamData[2] = teams[i].getTrapHistory();
            teamData[3] = teams[i].getSpeakerHistory();
            teamData[4] = teams[i].getAmpHistory();
            teamData[5] = teams[i].getPassHistory();
            teamData[6] = teams[i].getClimbHistory();
            teamData[7] = teams[i].getDefenseRoleHistory();
            teamData[8] = teams[i].getSupportRoleHistory();
            teamData[9] = teams[i].getOffenseRoleHistory();
            teamData[10] = teams[i].getMainAmpRoleHistory();
            data[i] = teamData;
        }
        return data; 
    }
}
