package mainApp;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartViewer extends Views {

    PopulationViewer popView;

    public void setUpViewer() {
        super.setUpViewer();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final int frameWidth = 600;
        final int frameHeight = 600;

        this.frame.setTitle("StartViewer");
        this.frame.setSize(frameWidth, frameHeight);
        this.frame.setLocation(0, 0);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new FramePassingActionListener(frame));
        startButton.setSize(new Dimension(100, 50));

        JLabel icon = new JLabel();
        ImageIcon img = new ImageIcon("Logo.png");
        System.out.println(img.getImage());
        icon.setIcon(img);
        icon.setSize(600, 300);

        JPanel first = new JPanel();
        first.add(icon);
        frame.add(first, BorderLayout.NORTH);
        JPanel second = new JPanel();
        second.add(startButton);
        frame.add(second, BorderLayout.CENTER);
        JPanel third = new JPanel();
        third.add(new JLabel("created by Allyn Loyd, Hailey Steward, and Natasa Zupanski"));
        frame.add(third, BorderLayout.SOUTH);

    }
}
