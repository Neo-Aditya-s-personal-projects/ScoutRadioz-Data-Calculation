package model;

public class BlueAllianceTeam {
    /*
     * "key": "string",
        "team_number": 0,
        "nickname": "string",
        "name": "string",
        "city": "string",
        "state_prov": "string",
        "country": "string"
     */
    String key;
    int team_number;
    String nickname;
    String name;
    String city;
    String state_prov;
    String country;
    
    public String getKey() {
        return key;
    }
    public int getTeamNumber() {
        return team_number;
    }
    public String getNickname() {
        return nickname;
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public String getState_prov() {
        return state_prov;
    }
    public String getCountry() {
        return country;
    }

    
}
