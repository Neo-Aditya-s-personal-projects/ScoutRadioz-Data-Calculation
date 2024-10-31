package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class Graph extends JPanel {
    private int padding = 25;
    private int labelPadding = 25;
    private final Random r = new Random();
    private final int[] teams;
    private final ArrayList<Integer>[] data;
    private final String xAxisLabel;
    private final String yAxisLabel;
    private final ArrayList<Color> lineColors = new ArrayList<>();
    private int height = 1160;
    private int width = 1800;

    public Graph(String xAxisLabel, String yAxisLabel, int[] teams, ArrayList<Integer>[] data) {
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.data = data;
        this.teams = teams;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

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
    }
}
