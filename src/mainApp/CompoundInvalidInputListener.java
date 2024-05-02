package mainApp;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
            int value = Integer.parseInt(component.getText());
            if (value > lower && value <= upper) {
                handleSetValue(value);
            } else {
                handleBadInput();
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
        handleGeneralError();
    }

    private void handleNonNumeric() {
        running = new PopUpView(InputErrorFactory.getPopUpTextFromInputType(InputType.NONNUMERIC, 0, 0));
        handleGeneralError();
    }

    private void handleGeneralError() {
        running.setUpViewer();
        running.runApp();
        component.setBackground(Color.red);
    }

    private void handleSetValue(int value) {
        System.out.println("Attempting to set value " + value + " for type " + type + ".");
        switch (type) {
            case GENSIZE:
                pop.handleSetGenSize(value);
                return;
            case ELITISM:
                pop.handleSetElitism(value);
                // System.out.println(pop.handleGetElitism());
                return;
            case GENOMELENGTH:
                pop.handleSetGenomeLength(value);
                return;
            case NUMGENS:
                pop.handleSetNumGens(value);
                return;
            case MUTATIONRATE:
                pop.handleSetMutationRate(value);
                return;
            case TERMINATIONFITNESS:
                pop.handleSetTermination(value);
                return;
            default:
                System.out.println("Failed to set value " + value + " for unknown type " + type + ".");
        }
    }

}
