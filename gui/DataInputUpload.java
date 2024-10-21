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

public class DataInputUpload implements ActionListener {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    private JLabel labelUpload = new JLabel("Upload Data");
    
    private JFileChooser chooser = new JFileChooser();

    private JButton submitButton = new JButton("Submit Data");

    public DataInputUpload() {
        new ToggleScreenButtons(panel, CurrentScreen.AddDataUpload);
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
            String dataLabels[] = read.readLine().split(",");
            int validity = -1;
            int teamNumber = -1;
            int role = -1;
            int speakerNotes = -1;
            int ampNotes = -1;
            int autoNotes = -1;
            int notesPassed = -1;
            int trapNotes = -1;
            int climbed = -1;

            for (int i = 0; i < dataLabels.length; i++) {
                if (dataLabels[i].equals("preMatchStart")) validity = i;
                else if (dataLabels[i].equals("team_key")) teamNumber = i;
                else if (dataLabels[i].equals("playedDefense")) role = i;
                else if (dataLabels[i].equals("totalSpeakerNotes")) speakerNotes = i;
                else if (dataLabels[i].equals("totalAmpNotes")) ampNotes = i;
                else if (dataLabels[i].equals("totalAutoNotes")) autoNotes = i;
                else if (dataLabels[i].equals("teleopPassedNotes")) notesPassed = i;
                else if (dataLabels[i].equals("teleopTrap")) trapNotes = i;
                else if (dataLabels[i].equals("endgameStage")) climbed = i;
            }

            if (validity == -1 || teamNumber == -1 || role == -1 || speakerNotes == -1 || ampNotes == -1 || autoNotes == -1 || notesPassed == -1 || trapNotes == -1 || climbed == -1) throw new Exception("Wrong File");

            while(true) {
                String currentLine = read.readLine();
                if (currentLine != null && !(currentLine.isEmpty())) {
                    String[] data = currentLine.split(",");
                    if (!(data[validity].equals("Not There"))) {
                        data[teamNumber] = data[teamNumber].replaceAll("frc", "").trim().replaceAll("\uFEFF", "");
                        data[speakerNotes] = data[speakerNotes].replaceAll("\"", "").trim().replaceAll("\uFEFF", "");
                        data[ampNotes] = data[ampNotes].replaceAll("\"", "").trim().replaceAll("\uFEFF", "");
                        data[notesPassed] = data[notesPassed].replaceAll("\"", "").trim().replaceAll("\uFEFF", "");
                        data[autoNotes] = data[autoNotes].replaceAll("\"", "").trim().replaceAll("\uFEFF", "");
                        data[trapNotes] = data[trapNotes].replaceAll("\"", "").trim().replaceAll("\uFEFF", "");
                        if(data[role].equals("1")) data[role] = "D";
                        else if(
                            (Integer.valueOf(data[ampNotes]) > (Integer.valueOf(data[speakerNotes]) - 2)) && 
                            (Integer.valueOf(data[ampNotes]) > (Integer.valueOf(data[notesPassed]) - 2)) && 
                            (Integer.valueOf(data[ampNotes]) > 0)
                            ) data[role] = "A";
                        else if(Integer.valueOf(data[notesPassed]) > (Integer.valueOf(data[speakerNotes]) + 2)) data[role] = "S";
                        else data[role] = "O";

                        TeamData.appendDataUpload(
                            Integer.valueOf(data[teamNumber]), 
                            data[role],
                            Integer.valueOf(data[speakerNotes]),
                            Integer.valueOf(data[ampNotes]),
                            Integer.valueOf(data[autoNotes]),
                            Integer.valueOf(data[notesPassed]),
                            Integer.valueOf(data[trapNotes]),
                            data[climbed].equals("1")
                            );
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