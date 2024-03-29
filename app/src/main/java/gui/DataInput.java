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

public class DataInput implements ActionListener {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    private JLabel labelTeamName = new JLabel("What's the Team's Name?");
    private JTextArea teamName = new JTextArea("Team's Name");

    private JLabel labelTeamNumber = new JLabel("What's the Team's Number?");
    private JTextArea teamNumber = new JTextArea("Team Number");

    private JLabel labelRole = new JLabel("What was the Team's Role?");
    private JTextArea role = new JTextArea("Role");

    private JLabel labelSpeakerNotes = new JLabel("How many Speaker Notes did they score in Teleop?");
    private JTextArea speakerNotes = new JTextArea("# of Speaker Notes");

    private JLabel labelAmpNotes = new JLabel("How many Amp Notes did they score in Teleop?");
    private JTextArea ampNotes = new JTextArea("# of Amp Notes");

    private JLabel labelAutoNotes = new JLabel("How many Notes did they score in Auto?");
    private JTextArea autoNotes = new JTextArea("# of Auto Notes");

    private JLabel labelNotesPassed = new JLabel("How many Notes did they pass in Teleop?");
    private JTextArea notesPassed = new JTextArea("# of Notes Passed");

    private JLabel labelTrapNotes = new JLabel("How many Trap Notes did they score in Teleop?");
    private JTextArea trapNotes = new JTextArea("# of Trap Notes");

    private JCheckBox climbed = new JCheckBox("Did they Climb?");

    private JButton submitButton = new JButton("Submit Data");

    public DataInput(GUI gui) {
        new ToggleScreenButton(panel, Screen.AddData, gui);
        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(10000, 1000));

        labelTeamName.setBounds(31, 30, 300, 25);
        panel.add(labelTeamName);

        teamName.setBounds(31, 60, 300, 25);
        panel.add(teamName);

        labelTeamNumber.setBounds(31, 90, 300, 25);
        panel.add(labelTeamNumber);

        teamNumber.setBounds(31, 120, 300, 25);
        panel.add(teamNumber);

        labelRole.setBounds(31, 150, 300, 25);
        panel.add(labelRole);

        role.setBounds(31, 180, 300, 25);
        panel.add(role);

        labelSpeakerNotes.setBounds(31, 210, 300, 25);
        panel.add(labelSpeakerNotes);

        speakerNotes.setBounds(31, 240, 300, 25);
        panel.add(speakerNotes);

        labelAmpNotes.setBounds(31, 270, 300, 25);
        panel.add(labelAmpNotes);

        ampNotes.setBounds(31, 300, 300, 25);
        panel.add(ampNotes);

        labelAutoNotes.setBounds(31, 330, 300, 25);
        panel.add(labelAutoNotes);

        autoNotes.setBounds(31, 360, 300, 25);
        panel.add(autoNotes);

        labelNotesPassed.setBounds(31, 390, 300, 25);
        panel.add(labelNotesPassed);

        notesPassed.setBounds(31, 420, 300, 25);
        panel.add(notesPassed);

        labelTrapNotes.setBounds(31, 450, 300, 25);
        panel.add(labelTrapNotes);

        trapNotes.setBounds(31, 480, 300, 25);
        panel.add(trapNotes);

        climbed.setBounds(31, 510, 300, 25);
        panel.add(climbed);

        submitButton.setBounds(31, 540, 300, 25);
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
        if (checkDataValidity()) {
            resetScreen();
        }
    }

    public void resetScreen() {
        teamName.setText("Team's Name");
        teamNumber.setText("Team Number");
        role.setText("Role");
        speakerNotes.setText("# of Speaker Notes");
        ampNotes.setText("# of Amp Notes");
        autoNotes.setText("# of Auto Notes");
        notesPassed.setText("# of Notes Passed");
        trapNotes.setText("# of Trap Notes");
    }

    private boolean checkDataValidity() {
        boolean isValid = true;
        try {
            Integer.valueOf(teamNumber.getText());
        } catch (NumberFormatException error) {
            System.out.println("Error, team number can only be numbers");
            isValid = false;
        }
        if (!(role.getText().equalsIgnoreCase("D")) && !(role.getText().equalsIgnoreCase("A")) && !(role.getText().equalsIgnoreCase("S")) && !(role.getText().equalsIgnoreCase("O"))) {
            System.out.println("Error, Role can only be D, A, S, or O");
            System.out.println(role.getText().equalsIgnoreCase("D"));
            System.out.println(role.getText());
            isValid = false;
        }
        try {
            Integer.valueOf(speakerNotes.getText());
        } catch (NumberFormatException error) {
            System.out.println("Error, Speaker Notes can only be numbers");
            isValid = false;
        }
        try {
            Integer.valueOf(ampNotes.getText());
        } catch (NumberFormatException error) {
            System.out.println("Error, Amp Notes can only be numbers");
            isValid = false;
        }
        try {
            Integer.valueOf(autoNotes.getText());
        } catch (NumberFormatException error) {
            System.out.println("Error, Auto Notes can only be numbers");
            isValid = false;
        }
        try {
            Integer.valueOf(notesPassed.getText());
        } catch (NumberFormatException error) {
            System.out.println("Error, Notes Passed can only be numbers");
            isValid = false;
        }
        try {
            Integer.valueOf(trapNotes.getText());
        } catch (NumberFormatException error) {
            System.out.println("Error, Trap Notes can only be numbers");
            isValid = false;
        }
        return isValid;
    }
}