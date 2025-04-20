package calcs;

import constants.Constants;
import data.Request;
import java.util.ArrayList;

public class Team {
    private final int teamNumber;
    private final String teamName;
    
    private final ArrayList<ArrayList<Number>> data;

    private final int defaultValue = -1;

    private static String[] dataNames = {
        "N / A"
    };

    private static String[] dataNamesCalculated = {
        "N / A"
    };

    public Team(int teamNumber, String teamName) {
        this.teamNumber = teamNumber;
        this.teamName = teamName;
        data = new ArrayList<>();
    }

    public Team(int teamNumber) {
        this(teamNumber, Constants.USE_TBA ? Request.getTeamName(teamNumber) : "N/A");
    }

    public Team(Team team) {
        teamNumber = team.getTeamNumber();
        teamName = team.getTeamName();
        data = new ArrayList<>(team.data);
    }

    /**
     * @param data The data that was noted down, use regular binary for booleans
     */
    public void appendData(double... data) throws Exception {
        if (data.length < this.data.size()) throw new Exception("Too few data types.");
        else {
            if (data.length > this.data.size()) for (int i = this.data.size(); i < data.length; i++) this.data.add(new ArrayList<>());
            for (int i = 0; i < data.length; i++) this.data.get(i).add(data[i]);
        }
    }

    

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTotalMatches() {
        return data.get(0).size();
    }

    public static String[] getTableDataNames() {
        return dataNamesCalculated;
    }

    public static void setTableDataNames(String[] dataNamesCalculated) {
        Team.dataNamesCalculated = dataNamesCalculated;
    }

    public double[] getDataAverage() {
        double[] result = new double[data.size() + 1];
        for (int i = 0; i < data.size(); i++) {
            result[i] = getAverage(data.get(i));
        }
        result[data.size()] = data.get(0).size();
        return result;
    }

    public double[] getDataMax() {
        double[] result = new double[data.size() + 1];
        for (int i = 0; i < data.size(); i++) {
            result[i] = getMax(data.get(i));
        }
        result[data.size()] = data.get(0).size();
        return result;
    }

    public double[] getDataMin() {
        double[] result = new double[data.size() + 1];
        for (int i = 0; i < data.size(); i++) {
            result[i] = getMin(data.get(i));
        }
        result[data.size()] = data.get(0).size();
        return result;
    }

    public static String[] getDataNames() {
        return dataNames;
    }

    public static void setDataNames(String[] dataNames) {
        Team.dataNames = dataNames;
    }

    public ArrayList<Number>[] getDataHistory() {
        ArrayList<Number>[] result = new ArrayList[data.size()];
        for (int i = 0; i < data.size(); i++) result[i] = data.get(i);
        return result;
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