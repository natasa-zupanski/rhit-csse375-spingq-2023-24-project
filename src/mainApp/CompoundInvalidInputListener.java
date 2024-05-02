package mainApp;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class CompoundInvalidInputListener implements KeyListener, FocusListener {
    private Views running = null;
    private JTextField component;
    private InputType type;
    private int upper;
    private int lower;
    private PopulationComponent pop;

    public CompoundInvalidInputListener(InputType type, JTextField component, int lowerLimit, int upperLimit,
            PopulationComponent pop) {
        this.component = component;
        this.type = type;
        this.upper = upperLimit;
        this.lower = lowerLimit;
        this.pop = pop;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        component.setBackground(null);
        if (running != null) {
            running.frame.dispose();
            running = null;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            int size = Integer.parseInt(component.getText());
            if (size > lower && size <= upper) {
                pop.handleSetGenSize(size);
            } else {
                handleBadInput();
                component.setBackground(Color.red);
            }
        } catch (Exception ex) {
            if (!component.getText().equals("")) {
                handleNonNumeric();
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        component.setBackground(null);
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    private void handleBadInput() {
        running = new PopUpView(InputErrorFactory.getPopUpTextFromInputType(type, lower, upper));
        running.setUpViewer();
        running.runApp();
    }

    private void handleNonNumeric() {
        running = new PopUpView(InputErrorFactory.getPopUpTextFromInputType(InputType.NONNUMERIC, 0, 0));
        running.setUpViewer();
        running.runApp();
    }

}
