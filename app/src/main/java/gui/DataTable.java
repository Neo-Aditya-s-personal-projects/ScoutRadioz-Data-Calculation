package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;


public class DataTable implements ActionListener{

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    private final JTable dataTable = new JTable(TeamsAttendingTemp.getTeams().length, 12);

    public DataTable(GUI gui) {
        
        new ToggleScreenButton(panel, Screen.DataTable, gui);

        gui.setFrame(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
