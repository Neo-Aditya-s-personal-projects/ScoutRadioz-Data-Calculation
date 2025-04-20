package calcs;

public class CalculateTable {
    
    public static double[][] getDataDoubles(Team[] teams, String setting) {
        double[][] data = new double[teams.length][];
        for (int i = 0; i < teams.length; i++) {
            switch (setting) {
                case  "Average" -> {
                    data[i] = teams[i].getDataAverage();
                }
                case "Max" -> {
                    data[i] = teams[i].getDataMax();
                }
                case "Min" -> {
                    data[i] = teams[i].getDataMin();
                }
            }
        }
        return data;
    }

    public static Object[][] getDataObjects(Team[] teams, String setting) {
        return doublestoObjects(getDataDoubles(teams, setting));
    }

    public static Object[][] doublestoObjects(double[][] doubles) {
        Object[][] objects = new Object[doubles.length][];
        for (int i = 0; i < doubles.length; i++) {
            objects[i] = new Object[doubles[i].length];
            for (int j = 0; j < doubles[i].length; j++) {
                objects[i][j] = doubles[i][j];
            }
        }
        return objects;
    }

    public static double[][] organizeData(double[][] teamData, int index, boolean highestToLowest) {
        double[][] organizedData = new double[teamData.length][];
        for(int i = 0; i < teamData.length; i++) {
            int tempIndex = 0;
            for (int j = 0; j < teamData.length; j++) {
                if(j != i) {
                    if((teamData[i][index] < teamData[j][index]) && highestToLowest) {
                        tempIndex++;
                    }
                    else if ((teamData[i][index] > teamData[j][index]) && !highestToLowest) {
                        tempIndex++;
                    }
                }
            }
            while (organizedData[tempIndex] != null) {
                tempIndex++;
            }
            organizedData[tempIndex] = teamData[i];
        }
        return organizedData;
    }
}
