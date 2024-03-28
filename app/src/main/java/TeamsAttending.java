import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class TeamsAttending {
    public void getTeams(String eventKey, String apiKey) {
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
        } catch (Exception e) {
            System.out.println("Something Broke");    
        }
        if (result != null) {
            
        }
    }
}
