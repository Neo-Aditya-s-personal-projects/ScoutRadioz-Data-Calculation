package gui;

import javax.swing.JFrame;

public class GUI {

    private static JFrame frame = new JFrame();

    public static void open() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void setFrame(JFrame newFrame) {
        frame.dispose();
        frame = newFrame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static JFrame getFrame() {
        return frame;
    }
}