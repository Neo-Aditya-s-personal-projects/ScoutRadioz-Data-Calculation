package gui;

import calcs.CalculateTable;
import data.TeamData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Constants.Constants;

public class DataTable extends JFrame {

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();
    private final String[] columnNames = {"Team Number", "Auto", "Amp", "Speaker", "Climb", "Passes", "Trap", "Matches", "Defense"};
    private static String tableCalcSetting = "Average";

    private final JRadioButton[] tableOrganizationOptions = new JRadioButton[Constants.TABLE_SETTINGS_OPTIONS.length];
    private final ButtonGroup tableOrganizationButtonGroup = new ButtonGroup();
    private JTable table = new JTable(new DefaultTableModel(CalculateTable.getDataObjects(TeamData.getTeams(), tableCalcSetting), columnNames) {
        @Override
        public boolean isCellEditable(int row, int column) {
           return false;
        }
    });
    private JPanel buttonPanel = new JPanel(new GridLayout(1, columnNames.length));
    private int lastButtonClicked = -1;

    public DataTable() {
        super();

        new ToggleScreenButtons(panel, Screen.DataTable);
        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1920, 1280));

        for(int i = 0; i< columnNames.length; i++) {
            JButton button = new JButton(columnNames[i]);
            final Integer inneri = i;
             button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    table.setModel(new DefaultTableModel(CalculateTable.doublestoObjects(CalculateTable.organizeData(CalculateTable.getDataDoubles(TeamData.getTeams(), tableCalcSetting), inneri, (lastButtonClicked == inneri))), columnNames)
                    {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                           return false;
                        }
                    });
                    lastButtonClicked = (lastButtonClicked == inneri) ? -1 : inneri; 
                    table.setFont((table.getFont()));
                }
            });

            buttonPanel.add(button);
        }
        buttonPanel.setBounds(31, 90, (1540-61), 25);
        table.setFont((new Font(table.getFont().getName(), Font.BOLD, 20)));
        table.setRowHeight(table.getRowHeight() + 5);
        table.setRowMargin(5);

        for (int i = 0; i < tableOrganizationOptions.length; i++) {
            tableOrganizationOptions[i] = new JRadioButton(Constants.TABLE_SETTINGS_OPTIONS[i]);
            tableOrganizationOptions[i].setSelected(Constants.TABLE_SETTINGS_OPTIONS[i].equals(tableCalcSetting));
            tableOrganizationOptions[i].setBounds(710 - (tableOrganizationOptions.length * 100 / 2) + ((i + 1) * 100), 60 , 100, 25);
            final int temp = i;
            tableOrganizationOptions[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tableCalcSetting = Constants.TABLE_SETTINGS_OPTIONS[temp];
                    new DataTable();
                }
            });
            panel.add(tableOrganizationOptions[i]);

            tableOrganizationButtonGroup.add(tableOrganizationOptions[i]);
        }

        panel.add(buttonPanel, BorderLayout.NORTH);

        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1540, 1280));

        table.setBounds(31, 120, (1540-61), 1000);
        panel.add(table);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Scouting Results");
        frame.pack();
        frame.setVisible(true);
        GUI.setFrame(frame);
    }
}
