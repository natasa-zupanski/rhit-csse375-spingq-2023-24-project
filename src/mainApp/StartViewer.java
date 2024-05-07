package mainApp;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

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

        this.frame.add(startButton, BorderLayout.CENTER);

    }
}
