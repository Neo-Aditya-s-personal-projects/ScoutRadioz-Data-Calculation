package data;
import calcs.Team;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class DataRequest {
    public static String apiKey;

    public static void setAPIKey(String key) {
        apiKey = key;
    }
    
    public static String getTeamName(int teamNumber) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://www.thebluealliance.com/api/v3/team/frc" + teamNumber + "/simple"))
                .header("X-TBA-Auth-Key", apiKey)
                .build();
        String result = "Invalid Team";
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            result = response.body().substring(response.body().indexOf("nickname") + 12, response.body().indexOf("state_prov") - 6);
        }
        catch (Exception e) {
            System.out.println("Invalid Team");
        }
        return result;
    }

    public static void getTeams(String eventKey, String apiKey) {
        Team[] teams; 
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://www.thebluealliance.com/api/v3/team/" + eventKey + "/simple"))
                .header("X-TBA-Auth-Key", apiKey)
                .build();
        String result = null;
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            result = response.body();
            System.out.println(result);
            /*ArrayList<Team> teams = new ArrayList<Team>();

            Gson gson = new Gson();
            BlueAllianceTeam[] teamsAttending = gson.fromJson(result, BlueAllianceTeam[].class);
            teams = new Team[teamsAttending.length];
            for (int i = 0; i < teamsAttending.length; i++) {
                teams[i]= new Team(teamsAttending[i].getTeamNumber(), teamsAttending[i].getName());
            } */
        } catch (Exception e) {
            System.out.println("Something Broke");
        }
    }
}