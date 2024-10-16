package gui;

import calcs.CalculateTable;
import data.TeamsData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataTable extends JFrame {

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private String[] columnNames = {"Team Number", "Average Auto", "Average Amp", "Average Speaker", "Climb Probability", "Average Passes", "Average Trap", "Total Matches", "Defense Count"};
    private JTable table = new JTable(new DefaultTableModel(CalculateTable.getDataObjects(TeamsData.getTeams()), columnNames) {
        @Override
        public boolean isCellEditable(int row, int column) {
           return false;
        }
    });
    private JPanel buttonPanel = new JPanel(new GridLayout(1, columnNames.length));
    private int lastButtonClicked = -1;

    public DataTable(GUI gui) {
        super();

        for(int i = 0; i< columnNames.length; i++){
            JButton button = new JButton(columnNames[i]);
            final Integer inneri = i;
             button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    table.setModel(new DefaultTableModel(CalculateTable.doublestoObjects(CalculateTable.organizeData(CalculateTable.getDataDoubles(TeamsData.getTeams()), inneri, (lastButtonClicked == inneri))), columnNames)
                    {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                           return false;
                        }
                    });
                    lastButtonClicked = (lastButtonClicked == inneri) ? -1 : inneri; 
                    table.setFont((table.getFont()));
                    table.setRowHeight(table.getRowHeight() + 5);
                    table.setRowMargin(5);
                }
            });

            buttonPanel.add(button);
        }
        buttonPanel.setBounds(31, 60, (1540-61), 25);
        table.setFont((new Font(table.getFont().getName(), Font.BOLD, 20)));
        table.setRowHeight(table.getRowHeight() + 5);
        table.setRowMargin(5);
        panel.add(buttonPanel, BorderLayout.NORTH);

        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1540, 1280));

        table.setBounds(31, 90, (1540-61), 1000);
        panel.add(table);

        new ToggleScreenButtons(panel, Screen.DataTable, gui);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Scouting Results");
        frame.pack();
        frame.setVisible(true);
        gui.setFrame(frame);
    }
}
