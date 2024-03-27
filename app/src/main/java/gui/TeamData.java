package gui;
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

public class TeamData implements ActionListener {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    private JLabel labelTeamName = new JLabel("What's the Team's Name");
    private JTextArea teamName = new JTextArea("Blank Text");

    private JLabel labelTeamNumber = new JLabel("What's the Team's Number");
    private JTextArea teamNumber = new JTextArea("Team Number");

    private JLabel labelRole = new JLabel("What was the Team's Role?");
    private JTextArea teamRole = new JTextArea("Role");

    private JLabel labelSpeakerNotes = new JLabel("How many Speaker ");
    private JTextArea speakerNotes = new JTextArea("Team Name!");

    private JLabel labelAmpNotes = new JLabel("This Is a Label");
    private JTextArea ampNotes = new JTextArea("Team Name!");

    private JLabel labelAutoNotes = new JLabel("This Is a Label");
    private JTextArea autoNotes = new JTextArea("Team Name!");

    private JLabel labelNotesPassed = new JLabel("This Is a Label");
    private JTextArea notesPassed = new JTextArea("Team Name!");

    private JLabel labelTrapNotes = new JLabel("This Is a Label");
    private JTextArea trapNotes = new JTextArea("Team Name!");

    private JCheckBox climbed = new JCheckBox("climbed");

    private JButton button = new JButton("Submit");

    public TeamData(GUI gui) {
        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(10000, 1000));

        panel.add(button);
        button.addActionListener(this);

        labelTeamName.setBounds(31, 31, 100, 25);
        panel.add(labelTeamName);

        teamName.setBounds(31, 60, 200, 25);
        panel.add(teamName);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Match Scouting");
        frame.pack();
        frame.setVisible(true);

        gui.setFrame(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(teamName.getText());

    }
}