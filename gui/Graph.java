package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class Graph extends JPanel {
    private int yOffset = 0;
    private int xOffset = 300;
    private int padding = 100;
    private int sectionLineLengthIn = 12; //Inside the graph box
    private int sectionLineLengthOut = 12; //Outside graph box
    private final Random r = new Random();
    private final ArrayList<Integer> teams;
    private final ArrayList<Integer>[] data;
    private final String xAxisLabel;
    private final String yAxisLabel;
    private final ArrayList<Color> lineColors = new ArrayList<>();
    private final int xSections;
    private final int ySections;
    private int maxX = 0;
    private int maxY = 0;

    public Graph(String xAxisLabel, String yAxisLabel, ArrayList<Integer> teams, ArrayList<Integer>[] data, int xSections, int ySections) {
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.data = data;
        this.teams = teams;
        this.ySections = ySections;
        this.xSections = xSections;

        for (ArrayList<Integer> values : data) {
            values.remove(null);
            for (Integer datapoint : values) maxY = maxY < datapoint ? datapoint : maxY;
            maxX = (values.size() - 1) > maxX ? (values.size() - 1) : maxX;
            lineColors.add(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), 180));
        }
        lineColors.remove(null);
    }

    public Graph(String xAxisLabel, String yAxisLabel, int[] teams, ArrayList<Integer>[] data, int xSections, int ySections) {
        ArrayList<Integer> teamsList = new ArrayList<>();
        for (int team : teams) teamsList.add(team);
        teamsList.remove(null);
        
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.data = data;
        this.teams = teamsList;
        this.ySections = ySections;
        this.xSections = xSections;

        for (ArrayList<Integer> values : data) {
            values.remove(null);
            for (Integer datapoint : values) maxY = maxY < datapoint ? datapoint : maxY;
            maxX = (values.size() - 1) > maxX ? (values.size() - 1) : maxX;
            lineColors.add(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), 180));
        }
        lineColors.remove(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        removeAll();

        int height = getHeight() - yOffset;
        int width = getWidth() - xOffset;

        Graphics2D graphics = (Graphics2D) g;
        for (double i = 0; i < lineColors.size(); i++) {
            int length = (int) ((height - 2 * padding) / (3 * 36));
            int xRect = padding + length * 3 * (int) (i / 36);
            Rectangle rect = new Rectangle(xRect, (int) (padding + 3 * length * i), length, length);
            graphics.setColor(lineColors.get((int) i));
            graphics.draw(rect);
            graphics.fill(rect);
            graphics.setColor(Color.BLACK);
            graphics.drawString(Integer.toString(teams.get((int) i)), xRect + length * 2, (int) (padding + 3 * length * i + length));
        }

        graphics.translate(xOffset, yOffset);

        graphics.drawLine(padding, height - padding, width - padding, height - padding); // X-axis
        graphics.drawLine(padding, padding, padding, height - padding); // Y-axis

        double xScale = (double) (width - 2 * padding) / maxX;
        double yScale = (double) (height - 2 * padding) / maxY;

        for (double i = 1; i <= ySections; i++) {
            int yLocation = (int) (((height - padding)) * (i / (ySections + 2)));
            graphics.drawLine(padding + sectionLineLengthOut, yLocation, padding - sectionLineLengthIn, yLocation); //Y Lines
            DecimalFormat df = new DecimalFormat("#.##");
            graphics.drawString(df.format((height - padding - yLocation) / yScale), padding / 2, yLocation - 1);
        } 

        for(int i = 0; i < data.length; i++) {
            graphics.setColor(lineColors.get(i));
            graphics.setStroke(new BasicStroke(2));
            for (int j = 0; j < data[i].size() - 1; j++) {
                int x1 = (int) (padding + j * xScale);
                int y1 = (int) (height - padding - data[i].get(j) * yScale);
                int x2 = (int) (padding + (j + 1) * xScale);
                int y2 = (int) (height - padding - data[i].get(j + 1) * yScale);
                graphics.drawLine(x1, y1, x2, y2);
            }
        }

        graphics.setColor(Color.BLACK);
        graphics.drawString(xAxisLabel, (width - padding) / 2, height - padding / 4);
        graphics.rotate(3 * Math.PI / 2, padding / 4, (height - padding) / 2);
        graphics.drawString(yAxisLabel, padding / 4, (height - padding) / 2);
    }
}
