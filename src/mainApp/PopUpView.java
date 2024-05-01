package mainApp;

import java.awt.BorderLayout;
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

        frame.add(label, BorderLayout.CENTER);
    }

}
