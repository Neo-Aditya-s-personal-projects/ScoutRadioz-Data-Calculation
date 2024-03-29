package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        panel.add(table);

        new ToggleScreenButton(panel, Screen.DataTable, gui);

        gui.setFrame(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
