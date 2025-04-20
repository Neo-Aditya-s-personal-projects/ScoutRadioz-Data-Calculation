package gui;

import data.TeamData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.Constants;

public class DataInputUpload implements ActionListener {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    private JLabel labelUpload = new JLabel("Upload Data");
    
    private JFileChooser chooser = new JFileChooser();

    private JButton submitButton = new JButton("Submit Data");

    public DataInputUpload() {
        new ToggleScreenButtons(panel, Screen.AddDataUpload);
        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1920, 1280));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Tables", "csv");
        chooser.setFileFilter(filter);

        labelUpload.setBounds(610, 30, 400, 25);
        panel.add(labelUpload);

        chooser.setBounds(710, 60, 300, 400);
        panel.add(chooser);

        submitButton.setBounds(710, 540, 300, 25);
        submitButton.setBackground(Color.LIGHT_GRAY);
        panel.add(submitButton);
        submitButton.addActionListener(this);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Match Scouting");
        frame.pack();
        frame.setVisible(true);

        GUI.setFrame(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (chooser.getSelectedFile().isFile() && chooser.getSelectedFile().canRead()) uploadData();
        else System.out.println("Invalid input");
    }


    public void uploadData() {
        try {
            BufferedReader read = new BufferedReader(new FileReader(chooser.getSelectedFile()));
            int validity = -1;
            int teamNumber = -1;
            int[] dataLocations = new int[Constants.DATA_LABELS.length];
            for (int i = 0; i < dataLocations.length; i++) dataLocations[i] = -1;
            String dataLabels[] = read.readLine().split(",");

            for (int i = 0; i < dataLabels.length; i++) {
                for (int j = 0; j < dataLabels.length; j++) {
                    if (dataLabels[i].equals(Constants.DATA_LABELS[j])) dataLocations[j] = i;
                }
                if (dataLabels[i].equals(Constants.DATA_VALIDITY[0])) validity = i;
                if (dataLabels[i].equals("team_key")) teamNumber = i;
            }

            for (int location : dataLocations) if (location == -1) throw new Exception("Wrong File");
            if (validity == -1) throw new Exception("Wrong File");

            while(true) {
                String currentLine = read.readLine();
                if (currentLine != null && !(currentLine.isEmpty())) {
                    String[] data = currentLine.split(",");
                    double[] recordedData = new double[dataLocations.length];
                    if (!(data[validity].equals(Constants.DATA_VALIDITY[1]))) {
                        for (int i = 0; i < data.length; i++) 
                            for (String filter : Constants.FILTER_VALUES) {
                                data[i] = data[i].replaceAll(filter, "").trim();
                                data[teamNumber] = data[teamNumber].replaceAll(filter, "").trim();
                            }
                        for (int i = 0; i < dataLocations.length; i++) {
                            if(!(Constants.VALUE_NUMBER_MAP[i][0][0].equals("\uFEFF"))) {
                                for (String[] valueKey : Constants.VALUE_NUMBER_MAP[i]) {
                                    if (data[dataLocations[i]].equals(valueKey[0])) data[dataLocations[i]] = valueKey[1];
                                }
                            }
                        }
                        for (int i = 0; i < recordedData.length; i++) recordedData[i] = Integer.valueOf(data[dataLocations[i]]);

                        TeamData.appendDataUpload(Integer.valueOf(data[teamNumber]), recordedData);
                    }
                }
                else throw new Exception("Uploaded Successful");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}