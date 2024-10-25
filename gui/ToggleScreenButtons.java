package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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

    public ToggleScreenButtons(JPanel panel, Screen currentScreen) {

        try {
            BufferedImage unscaledButtonIcon = ImageIO.read(new File("./pictures/settingsButtonIcon.png"));
            BufferedImage scaledButtonIcon = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
            AffineTransform transform = new AffineTransform();
            transform.scale(unscaledButtonIcon.getWidth() < unscaledButtonIcon.getHeight() ? 30.0 / unscaledButtonIcon.getHeight() : 30.0 / unscaledButtonIcon.getWidth(), unscaledButtonIcon.getWidth() < unscaledButtonIcon.getHeight() ? 30.0 / unscaledButtonIcon.getHeight() : 30.0 / unscaledButtonIcon.getWidth());
            AffineTransformOp scaleOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
            scaledButtonIcon = scaleOp.filter(unscaledButtonIcon, scaledButtonIcon);

            settingsButton = new JButton(new ImageIcon(scaledButtonIcon));
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        settingsButton.setBounds(30, 30, 30, 30);
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Settings();
            }
        });
        panel.add(settingsButton);

        uploadDataButton.setBounds(currentScreen.equals(Screen.DataTable) ? 560 : 860, currentScreen.equals(Screen.DataTable) ? 31 : 570, 300, 25);
        uploadDataButton.setBackground(Color.BLUE);
        uploadDataButton.setForeground(Color.RED);
        uploadDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataInputUpload();
            }
        });

        manualDataButton.setBounds(860, currentScreen.equals(Screen.DataTable) ? 31 : 570, 300, 25);
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
