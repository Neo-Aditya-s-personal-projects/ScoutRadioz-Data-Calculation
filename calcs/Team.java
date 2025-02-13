package calcs;
import constants.Constants;
import data.Request;
import java.util.ArrayList;

public class Team {
    private final int teamNumber;
    private final String teamName;
    
    private final ArrayList<Number> auto;
    private final ArrayList<Number> missAuto;
    private final ArrayList<Number> net;
    private final ArrayList<Number> barge;
    private final ArrayList<Number> coral;
    private final ArrayList<Number> knock;
    private final ArrayList<Number> missTele;
    private final ArrayList<Number> shallow;
    private final ArrayList<Number> deep;
    private final ArrayList<Number> offense;
    private final ArrayList<Number> defense;
    private final ArrayList<Number> matchConsistency;

    private final int defaultValue = -1;

    private static final String[] dataNames = {
        "Auto Coral", 
        "Auto Miss",
        "Algae Scored in Net", 
        "Algae Scored in Barge",
        "Algae Kocked off Reef",
        "Teleop Coral", 
        "Teleop Coral Missed", 
        "Climbed Shallow", 
        "Climbed Deep", 
        "Main Offense",
        "Main Defense",
        "Match Weighted Consistency"
    };

    private static final String[] dataNamesCalculated = {
        "Auto Coral", 
        "Auto Miss",
        "Algae Scored in Net", 
        "Algae Scored in Barge",
        "Algae Kocked off Reef",
        "Teleop Coral", 
        "Teleop Coral Missed", 
        "Climbed Shallow", 
        "Climbed Deep", 
        "Main Offense",
        "Main Defense",
        "Match Weighted Consistency",
        "Attempt Weighted Consistency",
        "Auto Consistency"
    };

    public Team(int teamNumber, String teamName) {
        this.teamNumber = teamNumber;
        this.teamName = teamName;
        auto = new ArrayList<>();
        missAuto = new ArrayList<>();
        net = new ArrayList<>();
        barge = new ArrayList<>();
        knock = new ArrayList<>();
        coral = new ArrayList<>();
        missTele = new ArrayList<>();
        shallow = new ArrayList<>();
        deep = new ArrayList<>();
        offense = new ArrayList<>();
        defense = new ArrayList<>();
        matchConsistency = new ArrayList<>();
    }

    public Team(int teamNumber) {
        this(teamNumber, Constants.USE_TBA ? Request.getTeamName(teamNumber) : "N/A");
    }

    public Team(Team team) {
        teamNumber = team.getTeamNumber();
        teamName = team.getTeamName();
        
        auto = team.getAutoHistory();
        missAuto = team.getAutoMissHistory();
        net = team.getNetHistory();
        barge = team.getBargeHistory();
        knock = team.getKnockHistory();
        coral = new ArrayList<>();
        missTele = new ArrayList<>();
        shallow = new ArrayList<>();
        deep = new ArrayList<>();
        offense = new ArrayList<>();
        defense = new ArrayList<>();
        matchConsistency = new ArrayList<>();
    }

    /**
     * @param role The robot's role "D" for Defense & "O" for Offense
     * @param autoCoral The Amount of Coral the Robot scored in Auto
     * @param autoMissedCoral The Amount of Coral the Robot missed in Auto
     * @param totalNet The Amount of Algae that the Robot scored in the Net
     * @param totalBarge The Amount of Algae that the Robot scored in the Barge
     * @param totalKnocked The Amount of Algae that the Robot removed from the reef
     * @param teleCoral The Amount of Coral the Robot scored in Auto
     * @param teleMissedCoral The Amount of Coral the Robot missed in Auto
     * @param endState The robot's end position when the match ends "D" for Deep "S" for Shallow, & "O" for Other
     */
    public void appendData(String role, int autoCoral, int autoMissedCoral, int totalNet, int totalBarge, int totalKnocked, int teleCoral, int teleMissedCoral, String endState) {
        auto.add(autoCoral);
        missAuto.add(autoMissedCoral);
        shallow.add(endState.equals("S") ? 1 : 0);
        deep.add(endState.equals("D") ? 1 : 0);
        if (role.equalsIgnoreCase("O")) {
            net.add(totalNet);
            barge.add(totalBarge);
            knock.add(totalKnocked);
            coral.add(teleCoral);
            missTele.add(teleMissedCoral);
            matchConsistency.add((double) teleMissedCoral / teleCoral);
            offense.add(1);
            defense.add(0);
        }
        else {
            offense.add(0);
            defense.add(1);
        }
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public double getAverageAuto() {
        return getAverage(auto);
    }

    public double getAverageAutoMiss() {
        return getAverage(missAuto);
    }

    public double getAverageNet() {
        return getAverage(net);
    }

    public double getAverageBarge() {
        return getAverage(barge);
    }

    public double getAverageCoral() {
        return getAverage(coral);
    }

    public double getAverageKnock() {
        return getAverage(knock);
    }

    public double getAverageMissTele() {
        return getAverage(missTele);
    }

    public double getAverageShallow() {
        return getAverage(shallow);
    }

    public double getAverageDeep() {
        return getAverage(deep);
    }

    public double getAverageOffense() {
        return getAverage(offense);
    }

    public double getAverageDefense() {
        return getAverage(defense);
    }

    public double getMatchWeightedConsistency() {
        return getAverage(matchConsistency);
    }

    public double getAverageAutoConsistency() {
        return (getTotal(auto) / (getTotal(auto) + getTotal(missAuto)));
    }

    public double getAttemptWeightedConsistency() {
        return (getTotal(coral) / (getTotal(coral) + getTotal(missTele)));
    }

    public double getMinAuto() {
        return getMin(auto);
    }

    public double getMinAutoMiss() {
        return getMin(missAuto);
    }

    public double getMinNet() {
        return getMin(net);
    }

    public double getMinBarge() {
        return getMin(barge);
    }

    public double getMinCoral() {
        return getMin(coral);
    }

    public double getMinKnock() {
        return getMin(knock);
    }

    public double getMinMissTele() {
        return getMin(missTele);
    }

    public double getMinShallow() {
        return getMin(shallow);
    }

    public double getMinDeep() {
        return getMin(deep);
    }

    public double getMinOffense() {
        return getMin(offense);
    }

    public double getMinDefense() {
        return getMin(defense);
    }

    public double getMinMatchConsistency() {
        return getMin(matchConsistency);
    }

    public double getMaxAuto() {
        return getMax(auto);
    }

    public double getMaxAutoMiss() {
        return getMax(missAuto);
    }

    public double getMaxNet() {
        return getMax(net);
    }

    public double getMaxBarge() {
        return getMax(barge);
    }

    public double getMaxCoral() {
        return getMax(coral);
    }

    public double getMaxKnock() {
        return getMax(knock);
    }

    public double getMaxMissTele() {
        return getMax(missTele);
    }

    public double getMaxShallow() {
        return getMax(shallow);
    }

    public double getMaxDeep() {
        return getMax(deep);
    }

    public double getMaxOffense() {
        return getMax(offense);
    }

    public double getMaxDefense() {
        return getMax(defense);
    }

    public double getMaxMatchConsistency() {
        return getMax(matchConsistency);
    }

    public ArrayList<Number> getAutoHistory() {
        return new ArrayList<>(auto);
    }

    public ArrayList<Number> getAutoMissHistory() {
        return new ArrayList<>(missAuto);
    }

    public ArrayList<Number> getNetHistory() {
        return new ArrayList<>(net);
    }

    public ArrayList<Number> getBargeHistory() {
        return new ArrayList<>(barge);
    }

    public ArrayList<Number> getCoralHistory() {
        return new ArrayList<>(coral);
    }

    public ArrayList<Number> getKnockHistory() {
        return new ArrayList<>(knock);
    }

    public ArrayList<Number> getMissTeleHistory() {
        return new ArrayList<>(missTele);
    }

    public ArrayList<Number> getShallowHistory() {
        return new ArrayList<>(shallow);
    }

    public ArrayList<Number> getDeepHistory() {
        return new ArrayList<>(deep);
    }

    public ArrayList<Number> getOffenseHistory() {
        return new ArrayList<>(offense);
    }

    public ArrayList<Number> getDefenseHistory() {
        return new ArrayList<>(defense);
    }

    public ArrayList<Number> getMatchConsistencyHistory() {
        return new ArrayList<>(matchConsistency);
    }

    public int getTotalMatches() {
        return auto.size();
    }

    public static String[] getDataNames() {
        return dataNames;
    }

    public double[] getDataAverage() {
        return (new double[]{
            getAverageAuto();
            getAverageAutoMiss(),
            getAverageNet(),
            getAverageBarge(),
            getAverageKnock(),
            getAverageCoral(),
            getAverageMissTele(),
            getAverageShallow(),
            getAverageDeep(),
            getAverageOffense(),
            getAverageDefense(),
            getMatchWeightedConsistency(),
            getAttemptWeightedConsistency(),
            getAverageAutoConsistency()
        });
    }

    public ArrayList<Number>[] getDataHistory() {
        return (new ArrayList[]{
            getAutoHistory(), 
            getAutoMissHistory(),
            getNetHistory(), 
            getBargeHistory(),
            getKnockHistory(),
            getCoralHistory(), 
            getMissTeleHistory(), 
            getShallowHistory(), 
            getDeepHistory(), 
            getOffenseHistory(),
            getDefenseHistory(),
            getMatchConsistencyHistory()
        });
    }

    private double getAverage(ArrayList<Number> list) {
        try {
            double result = 0;
            for (int i = 0; i < list.size(); i++) result += list.get(i).doubleValue();
            return result / (double) list.size();
        }
        catch (Exception E) {
            return -1;
        }
    }

    private double getTotal(ArrayList<Number> list) {
        try {
            double result = 0;
            for (int i = 0; i < list.size(); i++) result += list.get(i).doubleValue();
            return result;
        }
        catch (Exception e) {
            return -1;
        }
    }

    private double getMax(ArrayList<Number> list) {
        try {
            double max = defaultValue;
            for (int i = 0; i < list.size(); i++) max = max < list.get(i).doubleValue() ? list.get(i).doubleValue() : max;
            return max;
        }
        catch (Exception e) {
            return -1;
        }
    }

    private double getMin(ArrayList<Number> list) {
        try {
            double min = defaultValue;
            for (int i = 0; i < list.size(); i++) min = min > list.get(i).doubleValue() ? list.get(i).doubleValue() : min;
            return min;
        }
        catch (Exception e) {
            return -1;
        }
    }
}