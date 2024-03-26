import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener {

    private int count = 0;
    private JButton button = new JButton("Submit");
    private JLabel label = new JLabel("This Is a Label");
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    public GUI() {
        panel.setBorder(BorderFactory.createMatteBorder(300, 300, 100, 300, Color.BLUE));
        panel.setLayout(new GridLayout(0,1));

        panel.add(button);
        button.addActionListener(this);

        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Scouting Grind");
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Submit" + count);
    }
}