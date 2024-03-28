package mainApp;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

abstract class Views{
    protected JFrame frame = new JFrame();
    protected JPanel panel = new JPanel();
    void setUpViewer() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new BorderLayout());
    }
    void runApp() {
        frame.setVisible(true);
    }
}