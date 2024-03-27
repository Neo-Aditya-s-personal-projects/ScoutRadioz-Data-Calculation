package gui;
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

public class TeamData implements ActionListener {

    private JButton button = new JButton("Submit");
    private JLabel label = new JLabel("This Is a Label");
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    public JTextArea teamName = new JTextArea("Team Name!");

    public TeamData() {
        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(10000, 1000));

        panel.add(button);
        button.addActionListener(this);

        label.setBounds(31, 31, 100, 25);
        panel.add(label);

        teamName.setBounds(31, 60, 200, 25);
        panel.add(teamName);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Scouting Grind");
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(teamName.getText());

    }
}