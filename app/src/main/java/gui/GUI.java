package gui;

import javax.swing.JFrame;

public class GUI {

    private JFrame frame = new JFrame();

    public GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}