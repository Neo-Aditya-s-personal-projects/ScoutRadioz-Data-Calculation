package data;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import calcs.Team;
import model.BlueAllianceTeam;

public class AttendingTeams {
    public static Team[] getTeams(String eventKey, String apiKey) {
        Team[] teams; 
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://www.thebluealliance.com/api/v3/event/" + eventKey + "/teams/simple"))
                .header("X-TBA-Auth-Key", apiKey)
                .build();
        String result = null;
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            result = response.body();
            Gson gson = new Gson();
            BlueAllianceTeam[] teamsAttending = gson.fromJson(result, BlueAllianceTeam[].class);
            teams = new Team[teamsAttending.length];
            for (int i = 0; i < teamsAttending.length; i++) {
                teams[i]= new Team(teamsAttending[i].getTeamNumber(), teamsAttending[i].getName());
            }
        } catch (Exception e) {
            System.out.println("Something Broke");
        }
        return teams;
    }
}
