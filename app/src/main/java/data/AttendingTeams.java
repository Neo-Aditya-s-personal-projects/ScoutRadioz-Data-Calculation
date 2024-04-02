package data;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;

import com.google.gson.Gson;

import model.BlueAllianceTeam;

public class AttendingTeams {
    public HashMap<Integer, String> getTeams(String eventKey, String apiKey) {
        HashMap<Integer, String> map = new HashMap<Integer, String>(); 
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
            BlueAllianceTeam[] teams = gson.fromJson(result, BlueAllianceTeam[].class);
            for (int i = 0; i < teams.length; i++) {
                map.put(teams[i].getTeamNumber(), teams[i].getName());
            }
        } catch (Exception e) {
            System.out.println("Something Broke");
        }
        return map;
    }
}
