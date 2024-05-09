package mainApp;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FramePassingActionListener implements ActionListener {
    JFrame frame;

    public FramePassingActionListener(JFrame frame) {
        this.frame = frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        Views popView = new PopulationViewer();
        popView.setUpViewer();
        popView.runApp();
        frame.dispose();
    }
}