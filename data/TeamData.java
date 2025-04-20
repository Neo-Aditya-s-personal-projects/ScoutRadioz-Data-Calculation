package data;

import calcs.Team;
import constants.Constants;
import gui.GUI;
import java.util.ArrayList;

public class TeamData {
    static ArrayList<Team> teams = new ArrayList<>();

    public static boolean isValidNumber(int teamNumber) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamNumber() == teamNumber)
                return true;
        }
        return false;
    }

    public static void appendDataUpload(int teamNumber, double... data) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamNumber() == teamNumber) {
                try {
                    teams.get(i).appendData(data);
                }
                catch (Exception e) {
                    System.err.println("Your code sucks");
                }
                return;
            }
        }
        if (Constants.USE_TBA && Request.isValidNumber(teamNumber)) {
            teams.add(new Team(teamNumber));
            try {
                teams.get(teams.size() - 1).appendData(data);
            }
            catch (Exception e) {
                System.err.println("Your code sucks");
            }
        }
        else if (Constants.USE_TBA) {
            System.out.println("Invalid Team");
        }
        else {
            teams.add(new Team(teamNumber));
            try {
                teams.get(teams.size() - 1).appendData(data);
            }
            catch (Exception e) {
                System.err.println("Your code sucks");
            }
        }
    }

    public static void appendData(int teamNumber, double... data) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamNumber() == teamNumber) {
                try {
                    teams.get(i).appendData(data);
                }
                catch (Exception e) {
                    System.err.println("Your code sucks");
                }
                return;
            }
        }
    }

    public static Team[] getTeams() {
        int lengthOfArray = 0;

        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTotalMatches() > 0) {
                lengthOfArray++;
            }
        }

        Team[] validTeams = new Team[lengthOfArray];
        int index = 0;
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTotalMatches() > 0) {
                validTeams[index] = teams.get(i);
                index++;
            }
        }
        return validTeams;
    }
}
