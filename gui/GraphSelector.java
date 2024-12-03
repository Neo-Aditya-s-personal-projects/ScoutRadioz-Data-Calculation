package gui;

import calcs.CalculateGraph;
import calcs.Team;
import data.TeamData;
import utils.DismissablePopup;
import utils.Graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.MenuElement;

public class GraphSelector extends JPanel {
    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel();

    private final JLabel graphTypeLabel = new JLabel("What type of Graph do you want?");
    private final String[] graphTypes = {"All Teams", "Specific Team/s"};
    private final JButton graphTypeToggle = new JButton("Select Graph");
    private final DismissablePopup graphTypePopup = new DismissablePopup(graphTypeToggle);

    private final JLabel addTeamNumberLabel = new JLabel("Add Team");
    private final ArrayList<Integer> availableTeamNumbers;
    private final JButton addTeamNumberToggle = new JButton("Select Team");
    private final DismissablePopup addTeamNumberPopup = new DismissablePopup(addTeamNumberToggle);

    private final JLabel removeTeamNumberLabel = new JLabel("Remove Team");
    private final ArrayList<Integer> selectedTeamNumbers = new ArrayList<>();
    private final JButton removeTeamNumberToggle = new JButton("Select Team");
    private final DismissablePopup removeTeamNumberPopup = new DismissablePopup(removeTeamNumberToggle);

    private final JLabel teamNumbersSelectedLabel = new JLabel("");

    private final JLabel dataAnalyzedLabel = new JLabel("Which data are you analyzing?");
    private final JButton dataAnalyzedToggle = new JButton("Select Data Type");
    private final DismissablePopup dataAnalyzedPopup = new DismissablePopup(dataAnalyzedToggle);

    private final JButton submitButton = new JButton("Submit");

    public GraphSelector() {
        super();

        new ToggleScreenButtons(panel, Screen.GraphSelector);
        panel.setBorder(BorderFactory.createMatteBorder(30, 30, 30, 30, Color.BLUE));
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1920, 1280));

        availableTeamNumbers = new ArrayList<>();
        for (int teamNumber : CalculateGraph.getTeamNumbers(TeamData.getTeams())) availableTeamNumbers.add(teamNumber);
        
        for (String graphType : graphTypes) {
            JMenuItem tempMenuItem = new JMenuItem(graphType);
            tempMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    graphTypeToggle.setText(tempMenuItem.getText());
                }
            });

            graphTypePopup.add(tempMenuItem);
        }

        updateTeamNumber();

        for (String dataName : Team.getDataNames()) {
            JMenuItem tempMenuItem = new JMenuItem(dataName);
            tempMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dataAnalyzedToggle.setText(tempMenuItem.getText());
                }
            });

            dataAnalyzedPopup.add(tempMenuItem);
        }

        graphTypeLabel.setBounds(710, 60, 300, 25);
        panel.add(graphTypeLabel);

        graphTypeToggle.setBounds(710, 90, 300, 25);
        panel.add(graphTypeToggle);

        graphTypePopup.setPopupSize(300, 100);
        graphTypePopup.setVisible(false);
        graphTypePopup.setMaxVisibleRows(4);
        panel.add(graphTypePopup);

        addTeamNumberLabel.setBounds(710, 240, 300, 25);
        panel.add(addTeamNumberLabel);

        addTeamNumberToggle.setBounds(710, 270, 300, 25);
        panel.add(addTeamNumberToggle);

        addTeamNumberPopup.setPopupSize(300, 100);
        addTeamNumberPopup.setVisible(false);
        addTeamNumberPopup.setMaxVisibleRows(4);
        panel.add(addTeamNumberPopup);

        removeTeamNumberLabel.setBounds(710, 420, 300, 25);
        panel.add(removeTeamNumberLabel);

        removeTeamNumberToggle.setBounds(710, 450, 300, 25);
        panel.add(removeTeamNumberToggle);

        removeTeamNumberPopup.setPopupSize(300, 100);
        removeTeamNumberPopup.setVisible(false);
        removeTeamNumberPopup.setMaxVisibleRows(4);
        panel.add(removeTeamNumberPopup);

        teamNumbersSelectedLabel.setBounds(710, 580, 300, 20);
        teamNumbersSelectedLabel.setFont(new Font(addTeamNumberLabel.getFont().getName(), addTeamNumberLabel.getFont().getStyle(), addTeamNumberLabel.getFont().getSize()));
        panel.add(teamNumbersSelectedLabel);

        dataAnalyzedLabel.setBounds(710, 630, 300, 25);
        panel.add(dataAnalyzedLabel);

        dataAnalyzedToggle.setBounds(710, 660, 300, 25);
        panel.add(dataAnalyzedToggle);

        dataAnalyzedPopup.setPopupSize(300, 100);
        dataAnalyzedPopup.setVisible(false);
        dataAnalyzedPopup.setMaxVisibleRows(4);
        panel.add(dataAnalyzedPopup);


        submitButton.setBounds(710, 810, 300, 25);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graphTypeToggle.getText().equals("Select Graph")) System.out.println("Error: Graph Type Not Selected");
                else if (dataAnalyzedToggle.getText().equals("Select Data Type")) System.out.println("Error: Data Type Not Selected");
                else if (graphTypeToggle.getText().equals("All Teams")) {
                    ArrayList<Integer>[] data = new ArrayList[CalculateGraph.getTeamNumbers(TeamData.getTeams()).length];
                    int indexOfTarget = 0;
                    while (!(Team.getDataNames()[indexOfTarget].equals(dataAnalyzedToggle.getText()))) indexOfTarget++;
                    for (int i = 0; i < data.length; i++) data[i] = TeamData.getTeams()[i].getDataHistory()[indexOfTarget];

                    Graph graph = new Graph("Matches", Team.getDataNames()[indexOfTarget], CalculateGraph.getTeamNumbers(TeamData.getTeams()), data, 12, 10);
                    JFrame frameTemp = new JFrame("Scout Graph");

                    graph.setPreferredSize(new Dimension(1920, 1280));
                    frameTemp.add(graph);
                    frameTemp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frameTemp.setTitle("Match Scouting");
                    frameTemp.pack();
                    frameTemp.setVisible(true);
                }
                else {
                    ArrayList<Integer>[] data = new ArrayList[selectedTeamNumbers.size()];
                    int indexOfTarget = 0;
                    while (!(Team.getDataNames()[indexOfTarget].equals(dataAnalyzedToggle.getText()))) indexOfTarget++;
                    for (Team team : TeamData.getTeams()) {
                        for (int i = 0; i < selectedTeamNumbers.size(); i++) {
                        if (team.getTeamNumber() == selectedTeamNumbers.get(i)) data[i] = team.getDataHistory()[indexOfTarget];
                        }
                    }

                    Graph graph = new Graph("Matches", Team.getDataNames()[indexOfTarget], selectedTeamNumbers, data, 12, 10);
                    JFrame frameTemp = new JFrame("Scout Graph");

                    graph.setPreferredSize(new Dimension(1920, 1280));
                    frameTemp.add(graph);
                    frameTemp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frameTemp.setTitle("Match Scouting");
                    frameTemp.pack();
                    frameTemp.setVisible(true);
                }
            }
        });
        panel.add(submitButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Scouting Results");
        frame.pack();
        frame.setVisible(true);
        GUI.setFrame(frame);
    }

    private void updateTeamNumber() {
        addTeamNumberPopup.removeAll();
        removeTeamNumberPopup.removeAll();
        
        boolean firstNumberAdded = false;
        teamNumbersSelectedLabel.setText("");
        for (Integer num : selectedTeamNumbers) {
            if (num != null) {
                if (firstNumberAdded) teamNumbersSelectedLabel.setText(teamNumbersSelectedLabel.getText() + ", ");
                else firstNumberAdded = true;
                teamNumbersSelectedLabel.setText(teamNumbersSelectedLabel.getText() + String.valueOf(num));
            }
        }

        for (int teamNumber : availableTeamNumbers) {
            JMenuItem tempMenuItem = new JMenuItem("" + teamNumber);
            tempMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedTeamNumbers.add(teamNumber);
                    availableTeamNumbers.remove(availableTeamNumbers.indexOf(teamNumber));
                    updateTeamNumber();
                }
            });

            addTeamNumberPopup.add(tempMenuItem);
        }

        for (int teamNumber : selectedTeamNumbers) {
            JMenuItem tempMenuItem = new JMenuItem("" + teamNumber);
            tempMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedTeamNumbers.remove(selectedTeamNumbers.lastIndexOf(teamNumber));
                    availableTeamNumbers.add(teamNumber);
                    updateTeamNumber();
                }
            });

            removeTeamNumberPopup.add(tempMenuItem);
        }
    }
}
