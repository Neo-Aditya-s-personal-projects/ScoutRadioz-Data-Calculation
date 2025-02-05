package data;

import calcs.Team;
import constants.Constants;

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

    public static void appendDataUpload(int teamNumber, String role, int autoCoral, int autoMissedCoral, int totalNet, int totalBarge, int totalKnocked, int teleCoral, int teleMissedCoral, String endState) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamNumber() == teamNumber) {
                teams.get(i).appendData(role, autoCoral, autoMissedCoral, totalNet, totalBarge, totalKnocked, teleCoral, teleMissedCoral, endState);
                return;
            }
        }
        if (Constants.USE_TBA && Request.isValidNumber(teamNumber)) {
            teams.add(new Team(teamNumber));
            teams.get(teams.size() - 1).appendData(role, autoCoral, autoMissedCoral, totalNet, totalBarge, totalKnocked, teleCoral, teleMissedCoral, endState);
        }
        else if (Constants.USE_TBA) {
            System.out.println("Invalid Team");
        }
        else {
            teams.add(new Team(teamNumber));
            teams.get(teams.size() - 1).appendData(role, autoCoral, autoMissedCoral, totalNet, totalBarge, totalKnocked, teleCoral, teleMissedCoral, endState);
        }
    }

    public static void appendData(int teamNumber, String role, int autoCoral, int autoMissedCoral, int totalNet, int totalBarge, int totalKnocked, int teleCoral, int teleMissedCoral, String endState) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamNumber() == teamNumber) {
                teams.get(i).appendData(role, autoCoral, autoMissedCoral, totalNet, totalBarge, totalKnocked, teleCoral, teleMissedCoral, endState);
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
