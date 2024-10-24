package data;

import java.util.ArrayList;

import calcs.Team;

import Constants.Constants;

public class TeamData {
    static ArrayList<Team> teams = new ArrayList<>();

    public static boolean isValidNumber(int teamNumber) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamNumber() == teamNumber)
                return true;
        }
        return false;
    }

    public static void appendDataUpload(int teamNumber, String role, int speakerNotes, int ampNotes, int autoNotes,
            int notesPassed, int trapNotes, boolean climbed) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamNumber() == teamNumber) {
                teams.get(i).appendData(role, speakerNotes, ampNotes, autoNotes, notesPassed, trapNotes, climbed);
                return;
            }
        }
        if (Constants.USE_TBA && Request.isValidNumber(teamNumber)) {
            teams.add(new Team(teamNumber));
            teams.get(teams.size() - 1).appendData(role, speakerNotes, ampNotes, autoNotes, notesPassed, trapNotes, climbed);
        }
        else if (Constants.USE_TBA) {
            System.out.println("Invalid Team");
        }
        else {
            teams.add(new Team(teamNumber));
            teams.get(teams.size() - 1).appendData(role, speakerNotes, ampNotes, autoNotes, notesPassed, trapNotes, climbed);
        }
    }

    public static void appendData(int teamNumber, String role, int speakerNotes, int ampNotes, int autoNotes,
            int notesPassed, int trapNotes, boolean climbed) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getTeamNumber() == teamNumber) {
                teams.get(i).appendData(role, speakerNotes, ampNotes, autoNotes, notesPassed, trapNotes, climbed);
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
