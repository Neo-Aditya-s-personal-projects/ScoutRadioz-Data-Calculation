package gui;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class GUI {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    public GUI() {
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getPanel() {
        return panel;
    }
}