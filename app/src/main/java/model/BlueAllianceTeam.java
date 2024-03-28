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
    public void setKey(String key) {
        this.key = key;
    }
    public int getTeamNumber() {
        return team_number;
    }
    public void setTeam_number(int team_number) {
        this.team_number = team_number;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState_prov() {
        return state_prov;
    }
    public void setState_prov(String state_prov) {
        this.state_prov = state_prov;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    
}
