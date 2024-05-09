package mainApp;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopUpView extends Views {
    private String text;

    public PopUpView(String text) {
        this.text = text;
    }

    void setUpViewer() {
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel(text);

        int width = 6 * text.length() + 2 * 10;

        Dimension dims = new Dimension(width, 100);

        frame.add(label, BorderLayout.CENTER);
        frame.setSize(dims);
        this.frame.setLocation(600, 400);
        // frame.setLocation(0, 0);
        // frame.setFocusCycleRoot(true);
    }

}
