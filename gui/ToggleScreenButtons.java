package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToggleScreenButtons {
    private final JButton manualDataButton = new JButton("Enter Data");
    private final JButton uploadDataButton = new JButton("Upload Data");
    private final JButton dataTableButton = new JButton("View Data");
    private JButton settingsButton = new JButton("Settings");

    public ToggleScreenButtons(JPanel panel, CurrentScreen currentScreen) {

        try {
            BufferedImage buttonIcon = ImageIO.read(new File("\\pictures\\image.png"));
            settingsButton = new JButton(new ImageIcon(buttonIcon));
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        settingsButton.setBounds(30, 30, 30, 30);
        panel.add(settingsButton);

        uploadDataButton.setBounds(currentScreen.equals(CurrentScreen.DataTable) ? 560 : 860, currentScreen.equals(CurrentScreen.DataTable) ? 31 : 570, 300, 25);
        uploadDataButton.setBackground(Color.BLUE);
        uploadDataButton.setForeground(Color.RED);
        uploadDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataInputUpload();
            }
        });

        manualDataButton.setBounds(860, currentScreen.equals(CurrentScreen.DataTable) ? 31 : 570, 300, 25);
        manualDataButton.setBackground(Color.RED);
        manualDataButton.setForeground(Color.BLUE);
        manualDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataInputManual();
            }
        });

        dataTableButton.setBounds(560, 570, 300, 25);
        dataTableButton.setBackground(Color.GREEN);
        dataTableButton.setForeground(Color.YELLOW);
            dataTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataTable();
            }
        });

        switch (currentScreen) {
            case AddDataManual -> {
                panel.add(uploadDataButton);
                panel.add(dataTableButton);
            }
            case AddDataUpload -> {
                panel.add(manualDataButton);
                panel.add(dataTableButton);
            }
            case DataTable -> {
                panel.add(uploadDataButton);
                panel.add(manualDataButton);
            }
        }
    }
}
