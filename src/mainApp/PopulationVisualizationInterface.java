package mainApp;

import java.awt.Graphics2D;

import javax.swing.JFrame;

public interface PopulationVisualizationInterface {
    void drawOn(Graphics2D g, JFrame frame);
    int gensSoFar();
    void populateData(Integer bestFitness, Integer avgFitness, Integer worstFitness, Integer avg1s,
    Integer avg0s, Integer avgQs);
    void printBestFitness();
}
