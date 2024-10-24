package gui;

import Constants.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Settings implements ActionListener {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    public static String tableCalcSetting = "Average";

    private JLabel labelTableOrganization = new JLabel("What should the value displayed on the table be?");
    private JRadioButton[] tableOrganizationOptions = new JRadioButton[Constants.TABLE_SETTINGS_OPTIONS.length];
    private ButtonGroup tableOrganizationButtonGroup = new ButtonGroup();

    private JButton submitButton = new JButton("Save Settings");

    public Settings() {

        new ToggleScreenButtons(panel, CurrentScreen.AddDataManual);
        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1920, 1280));

        labelTableOrganization.setBounds(710, 30, 300, 25);
        panel.add(labelTableOrganization);

        for (int i = 0; i < tableOrganizationOptions.length; i++) {
            tableOrganizationOptions[i] = new JRadioButton(Constants.TABLE_SETTINGS_OPTIONS[i]);
            tableOrganizationOptions[i].setBounds((710 - (tableOrganizationOptions.length * 30 / 2) + i * 30), 60, 300, 25);
            panel.add(tableOrganizationOptions[i]);
            tableOrganizationButtonGroup.add(tableOrganizationOptions[i]);
        }

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
        tableCalcSetting = getOption(tableOrganizationOptions);
        if (tableCalcSetting.equals(null)) {
            System.out.println("Something broke");
            new DataInputManual();
        }
    }

    private String getOption(JRadioButton[] options) {
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected()) return Constants.TABLE_SETTINGS_OPTIONS[i];
        }
        return null;
    }
}