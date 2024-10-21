package gui;

import data.Request;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Welcome implements ActionListener {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    private JLabel labelAPIKey = new JLabel("What's your TBA API Key?");
    private JTextArea apiKey = new JTextArea("API Key");

    private JLabel labelTeamNumber = new JLabel("What's a valid Team Number?");
    private JTextArea teamNumber = new JTextArea("Team Number");

    private JButton submitButton = new JButton("Submit Data");

    public Welcome() {

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
        try {
            if (Request.isValidAPIKey(apiKey.getText(), Integer.valueOf(teamNumber.getText()))) {
                Request.setAPIKey(apiKey.getText());
                new DataInputManual();
            } 
            else System.out.println("Invalid Team or API Key");
        } catch (Exception exception) {
            System.out.println("Invalid Team Number");
        }
    }
}
