package gui;

import data.Request;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Constants.Constants;

public class Welcome implements ActionListener {
    private GUI gui;

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    private JLabel labelAPIKey = new JLabel("What's your TBA API Key?");
    private JTextArea apiKey = new JTextArea("API Key");

    private JLabel labelTeamNumber = new JLabel("What's a valid Team Number?");
    private JTextArea teamNumber = new JTextArea("Team Number");

    private JCheckBox enableTBA = new JCheckBox("Do you want to use TBA for checking?");
    private JButton submitButton = new JButton("Submit");

    public Welcome(GUI gui) {
        this.gui = gui;

        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1920, 1280));

        labelAPIKey.setBounds(710, 30, 300, 25);
        panel.add(labelAPIKey);

        apiKey.setBounds(710, 60, 300, 25);
        panel.add(apiKey);

        labelTeamNumber.setBounds(710, 90, 300, 25);
        panel.add(labelTeamNumber);

        teamNumber.setBounds(710, 120, 300, 25);
        panel.add(teamNumber);

        enableTBA.setBounds(710, 150, 300, 25);
        panel.add(enableTBA);

        submitButton.setBounds(710, 540, 300, 25);
        submitButton.setBackground(Color.LIGHT_GRAY);
        panel.add(submitButton);
        submitButton.addActionListener(this);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Match Scouting");
        frame.pack();
        frame.setVisible(true);

        gui.setFrame(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(enableTBA.isSelected()) {
            try {
                if (Request.isValidAPIKey(apiKey.getText(), Integer.valueOf(teamNumber.getText()))) {
                    Request.setAPIKey(apiKey.getText());
                    Constants.USE_TBA = true;
                    new DataInputManual(gui);
                } 
                else System.out.println("Invalid Team or API Key");
            } catch (Exception exception) {
                System.out.println("Invalid Team Number");
            }
        }
        else {
            Constants.USE_TBA = false;
            new DataInputManual(gui);
        }
    }
}
