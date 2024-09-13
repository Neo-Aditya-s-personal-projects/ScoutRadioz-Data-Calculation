package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.io.File;

import data.AttendingTeamsData;

public class DataInputUpload implements ActionListener {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    private JLabel labelUpload = new JLabel("Upload Data");
    
    private JFileChooser chooser = new JFileChooser();

    private JButton submitButton = new JButton("Submit Data");

    public DataInputUpload(GUI gui) {
        new ToggleScreenButton(panel, Screen.AddDataUpload, gui);
        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1920, 1280));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Tables", "csv");
        chooser.setFileFilter(filter);

        labelUpload.setBounds(710, 30, 300, 25);
        panel.add(labelUpload);

        chooser.setBounds(710, 60, 300, 25);
        panel.add(chooser);

        submitButton.setBounds(710, 90, 300, 25);
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
        if (chooser.getSelectedFile().isFile()) {
            
        }
    }
}