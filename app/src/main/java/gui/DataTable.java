package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import calcs.CalculateTable;
import data.TeamsAttendingTemp;

public class DataTable implements ActionListener{

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private String[] columnNames = {"Team Number", "Average Auto", "Average Amp", "Average Speaker", "Climb Probability", "Average Passes", "Average Trap", "Total Matches", "Defense Count"};
    DefaultTableModel model = new DefaultTableModel(CalculateTable.getDataObjects(TeamsAttendingTemp.getTeams()), columnNames);
    private final JTable table = new JTable(model);

    public DataTable(GUI gui) {
        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1540, 1280));
        table.setBounds(31, 60, (1540-61), 1000);
        panel.add(table);

        new ToggleScreenButton(panel, Screen.DataTable, gui);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Match Scouting");
        frame.pack();
        frame.setVisible(true);
        gui.setFrame(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
