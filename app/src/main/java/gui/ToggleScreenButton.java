package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ToggleScreenButton implements ActionListener {
    private final JButton toggleScreen = new JButton("Toggle Screen");
    private final GUI gui;

    public ToggleScreenButton(JPanel panel, Screen currentScreen, GUI gui) {
        this.gui = gui;
        if (currentScreen.equals(Screen.DataTable)) {
            toggleScreen.setText("Add Data");
            toggleScreen.setBounds(31, 570, 300, 25);
            toggleScreen.setBackground(Color.RED);
            toggleScreen.setForeground(Color.BLUE);
        } else {
            toggleScreen.setText("Data Table");
            toggleScreen.setBounds(31, 570, 300, 25);
            toggleScreen.setBackground(Color.BLUE);
            toggleScreen.setForeground(Color.RED);
        }
        panel.add(toggleScreen);
        toggleScreen.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (toggleScreen.getText().equals("Add Data")) {
            new DataInput(gui);
        } else {
            new DataTable(gui);
        }
    }

}
