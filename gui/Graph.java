package gui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Graph extends JPanel {
    private int padding = 100;
    private int sectionLineLengthIn = 12; //Inside the graph box
    private int sectionLineLengthOut = 12; //Outside graph box
    private final Random r = new Random();
    private final int[] teams;
    private final ArrayList<Integer>[] data;
    private final String xAxisLabel;
    private final String yAxisLabel;
    private final ArrayList<Color> lineColors = new ArrayList<>();
    private final int xSections;
    private final int ySections;

    public Graph(String xAxisLabel, String yAxisLabel, int[] teams, ArrayList<Integer>[] data, int xSections, int ySections) {
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.data = data;
        this.teams = teams;
        this.ySections = ySections;
        this.xSections = xSections;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        removeAll();

        int height = getHeight();
        int width = getWidth();

        Graphics2D graphics = (Graphics2D) g;
        graphics.drawLine(padding, height - padding, width - padding, height - padding); // X-axis
        graphics.drawLine(padding, padding, padding, height - padding); // Y-axis

        double maxY = 0;
        double maxX = 0;
        for (ArrayList<Integer> values : data) {
            for (Integer datapoint : values) maxY = maxY < datapoint ? datapoint : maxY;
            maxX = (values.size() - 1) > maxX ? (values.size() - 1) : maxX;
            lineColors.add(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), 180));
        }

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
