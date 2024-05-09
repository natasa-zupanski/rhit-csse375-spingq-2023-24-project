package mainApp;

import javax.swing.JLabel;

public class WrappedLabel extends JLabel {

    public WrappedLabel(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return getText();
    }

}
