package gui;

import calcs.CalculateGraph;
import calcs.Team;
import data.TeamData;
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
    private final JPopupMenu graphTypePopUp = new JPopupMenu();

    private final JLabel addTeamNumberLabel = new JLabel("Add Team");
    private final ArrayList<Integer> availableTeamNumbers;
    private final JButton addTeamNumberToggle = new JButton("Select Team");
    private final JPopupMenu addTeamNumberPopUp = new JPopupMenu();

    private final JLabel removeTeamNumberLabel = new JLabel("Remove Team");
    private final ArrayList<Integer> selectedTeamNumbers = new ArrayList<>();
    private final JButton removeTeamNumberToggle = new JButton("Select Team");
    private final JPopupMenu removeTeamNumberPopUp = new JPopupMenu();

    private final JLabel teamNumbersSelectedLabel = new JLabel("");

    private final JLabel dataAnalyzedLabel = new JLabel("Which data are you analyzing?");
    private final JButton dataAnalyzedToggle = new JButton("Select Data Type");
    private final JPopupMenu dataAnalyzedPopUp = new JPopupMenu();

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

            graphTypePopUp.add(tempMenuItem);
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

            dataAnalyzedPopUp.add(tempMenuItem);
        }

        graphTypeToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphTypePopUp.setVisible(!graphTypePopUp.isVisible());
            }
        });
        
        addTeamNumberToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeamNumberPopUp.setVisible(!addTeamNumberPopUp.isVisible());
            }
        });

        removeTeamNumberToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTeamNumberPopUp.setVisible(!removeTeamNumberPopUp.isVisible());
            }
        });

        dataAnalyzedToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataAnalyzedPopUp.setVisible(!dataAnalyzedPopUp.isVisible());
            }
        });

        graphTypeLabel.setBounds(710, 60, 300, 25);
        panel.add(graphTypeLabel);

        graphTypeToggle.setBounds(710, 90, 300, 25);
        panel.add(graphTypeToggle);

        graphTypePopUp.setBounds(710, 90 + 25, 300, 100);
        graphTypePopUp.setVisible(false);
        panel.add(graphTypePopUp);

        addTeamNumberLabel.setBounds(710, 240, 300, 25);
        panel.add(addTeamNumberLabel);

        addTeamNumberToggle.setBounds(710, 270, 300, 25);
        panel.add(addTeamNumberToggle);

        removeTeamNumberPopUp.setBounds(710, 270 + 25, 300, 100);
        removeTeamNumberPopUp.setVisible(false);
        panel.add(removeTeamNumberPopUp);

        removeTeamNumberLabel.setBounds(710, 420, 300, 25);
        panel.add(removeTeamNumberLabel);

        removeTeamNumberToggle.setBounds(710, 450, 300, 25);
        panel.add(removeTeamNumberToggle);

        removeTeamNumberPopUp.setBounds(710, 450 + 25, 300, 100);
        removeTeamNumberPopUp.setVisible(false);
        panel.add(removeTeamNumberPopUp);

        teamNumbersSelectedLabel.setBounds(710, 580, 300, 20);
        teamNumbersSelectedLabel.setFont(new Font(addTeamNumberLabel.getFont().getName(), addTeamNumberLabel.getFont().getStyle(), addTeamNumberLabel.getFont().getSize() / 2));
        panel.add(teamNumbersSelectedLabel);

        dataAnalyzedLabel.setBounds(710, 630, 300, 25);
        panel.add(dataAnalyzedLabel);

        dataAnalyzedToggle.setBounds(710, 660, 300, 25);
        panel.add(dataAnalyzedToggle);

        dataAnalyzedPopUp.setBounds(710, 660 + 25, 300, 100);
        dataAnalyzedPopUp.setVisible(false);
        panel.add(dataAnalyzedPopUp);


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
                    ArrayList<Integer>[] data = new ArrayList[teamNumbersSelectedLabel.getText().split(", ").length];
                    int[] teamNumbers = new int[data.length];
                    int indexOfTarget = 0;
                    while (!(Team.getDataNames()[indexOfTarget].equals(dataAnalyzedToggle.getText()))) indexOfTarget++;
                    for (int i = 0; i < teamNumbersSelectedLabel.getText().split(", ").length; i++) teamNumbers[i] = Integer.valueOf(teamNumbersSelectedLabel.getText().split(", ")[i]);
                    int index = 0;
                    for (Team team : TeamData.getTeams()) {
                        if (team.getTeamNumber() == teamNumbers[index]) {
                            data[index] = team.getDataHistory()[indexOfTarget];
                            index++;
                        }
                    }

                    Graph graph = new Graph("Matches", Team.getDataNames()[indexOfTarget], CalculateGraph.getTeamNumbers(TeamData.getTeams()), data, 12, 10);
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
        for (int i = 0; i < addTeamNumberPopUp.getComponentCount(); i++) addTeamNumberPopUp.remove(i);
        for (int i = 0; i < removeTeamNumberPopUp.getComponentCount(); i++) removeTeamNumberPopUp.remove(i);

        for (int teamNumber : availableTeamNumbers) {
            JMenuItem tempMenuItem = new JMenuItem("" + teamNumber);
            tempMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    teamNumbersSelectedLabel.setText(teamNumbersSelectedLabel.getText() + ", " + tempMenuItem.getText());
                    availableTeamNumbers.remove(availableTeamNumbers.lastIndexOf(teamNumber));
                    updateTeamNumber();
                }
            });

            addTeamNumberPopUp.add(tempMenuItem);
        }

        for (int teamNumber : selectedTeamNumbers) {
            JMenuItem tempMenuItem = new JMenuItem("" + teamNumber);
            tempMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    teamNumbersSelectedLabel.setText(teamNumbersSelectedLabel.getText().replaceAll(", " + tempMenuItem.getText(), ""));
                    selectedTeamNumbers.remove(selectedTeamNumbers.lastIndexOf(teamNumber));
                    updateTeamNumber();
                }
            });

            removeTeamNumberPopUp.add(tempMenuItem);
        }
    }
}
