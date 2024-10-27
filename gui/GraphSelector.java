package gui;

import calcs.CalculateGraph;
import calcs.Team;
import data.TeamData;
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
    private final JToggleButton graphTypeToggle = new JToggleButton("Select Graph");
    private final JPopupMenu graphTypePopUp = new JPopupMenu();

    private final JLabel addTeamNumberLabel = new JLabel("Add Team");
    private final ArrayList<Integer> availableTeamNumbers;
    private final JToggleButton addTeamNumberToggle = new JToggleButton("Select Team");
    private final JPopupMenu addTeamNumberPopUp = new JPopupMenu();

    private final JLabel removeTeamNumberLabel = new JLabel("Remove Team");
    private final ArrayList<Integer> selectedTeamNumbers = new ArrayList<>();
    private final JToggleButton removeTeamNumberToggle = new JToggleButton("Select Team");
    private final JPopupMenu removeTeamNumberPopUp = new JPopupMenu();

    private final JLabel teamNumbersSelectedLabel = new JLabel("");

    private final JLabel dataAnalyzedLabel = new JLabel("Which data are you analyzing?");
    private final JToggleButton dataAnalyzedToggle = new JToggleButton("Select Data Type");
    private final JPopupMenu dataAnalyzedPopUp = new JPopupMenu();

    private final JButton submitButton = new JButton("Submit");

    public GraphSelector() {
        super();

        new ToggleScreenButtons(panel, Screen.DataTable);
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
                graphTypePopUp.setVisible(graphTypeToggle.isEnabled());
            }
        });
        
        addTeamNumberToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeamNumberPopUp.setVisible(addTeamNumberToggle.isEnabled());
            }
        });

        removeTeamNumberToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTeamNumberPopUp.setVisible(removeTeamNumberToggle.isEnabled());
            }
        });

        dataAnalyzedToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataAnalyzedPopUp.setVisible(dataAnalyzedToggle.isEnabled());
            }
        });

        graphTypeLabel.setBounds(710, 60, 300, 25);
        panel.add(graphTypeLabel);

        graphTypeToggle.setBounds(710, 90, 300, 25);
        graphTypeToggle.setEnabled(false);
        panel.add(graphTypeToggle);

        graphTypePopUp.setBounds(710, 90 + 25, 300, 100);
        graphTypePopUp.setVisible(false);
        panel.add(graphTypePopUp);

        addTeamNumberLabel.setBounds(710, 240, 300, 25);
        panel.add(addTeamNumberLabel);

        addTeamNumberToggle.setBounds(710, 270, 300, 25);
        addTeamNumberToggle.setEnabled(false);
        panel.add(addTeamNumberToggle);

        removeTeamNumberPopUp.setBounds(710, 270 + 25, 300, 100);
        removeTeamNumberPopUp.setVisible(false);
        panel.add(removeTeamNumberPopUp);

        removeTeamNumberLabel.setBounds(710, 420, 300, 25);
        panel.add(removeTeamNumberLabel);

        removeTeamNumberToggle.setBounds(710, 450, 300, 25);
        removeTeamNumberToggle.setEnabled(false);
        panel.add(removeTeamNumberToggle);

        removeTeamNumberPopUp.setBounds(710, 450 + 25, 300, 100);
        removeTeamNumberPopUp.setVisible(false);
        panel.add(removeTeamNumberPopUp);

        teamNumbersSelectedLabel.setBounds(710, 580, 300, 20);
        teamNumbersSelectedLabel.setFont(new Font(addTeamNumberLabel.getFont().getName(), addTeamNumberLabel.getFont().getStyle(), addTeamNumberLabel.getFont().getSize() / 2));
        panel.add(teamNumbersSelectedLabel);

        addTeamNumberLabel.setBounds(710, 630, 300, 25);
        panel.add(addTeamNumberLabel);

        addTeamNumberToggle.setBounds(710, 660, 300, 25);
        addTeamNumberToggle.setEnabled(false);
        panel.add(addTeamNumberToggle);

        addTeamNumberPopUp.setBounds(710, 660 + 25, 300, 100);
        addTeamNumberPopUp.setVisible(false);
        panel.add(addTeamNumberPopUp);

        submitButton.setBounds(710, 810, 300, 25);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        panel.add(submitButton);
        
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
