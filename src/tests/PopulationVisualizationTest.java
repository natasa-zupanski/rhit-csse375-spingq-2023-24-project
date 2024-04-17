package tests;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;

import mainApp.EvolutionParameters;
import mainApp.FakePopulationVisualization;
import mainApp.FitnessType;
import mainApp.SelectionType;

public class PopulationVisualizationTest {

    @Test
    public void TestCalculateScaleFactors_DefaultScreen() {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 500, 100, 100, 1, SelectionType.TRUNCATION, FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        JFrame frame = new JFrame();
        frame.setSize(1313, 600);
        populationVisualization.calculateScaleFactorsPublic(frame);
        assertEquals(1.1936363636363636, populationVisualization.getScaleFactorX(), 0.0);
        assertEquals(1, populationVisualization.getScaleFactorY(), 0.0);
    }

    @Test
    public void TestCalculateScaleFactors_LargerScreen() {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 500, 100, 100, 1, SelectionType.TRUNCATION, FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        JFrame frame = new JFrame();
        frame.setSize(2000, 700);
        populationVisualization.calculateScaleFactorsPublic(frame);
        assertEquals(1.81, populationVisualization.getScaleFactorX(), 0.01);
        assertEquals(1.16, populationVisualization.getScaleFactorY(), 0.01);
    }

    @Test
    public void TestCalculateScaleFactors_SmallerScreen() {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 500, 100, 100, 1, SelectionType.TRUNCATION, FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        populationVisualization.calculateScaleFactorsPublic(frame);
        assertEquals(0.90, populationVisualization.getScaleFactorX(), 0.01);
        assertEquals(0.83, populationVisualization.getScaleFactorY(), 0.01);
    }

    @Test
    public void TestPopulationData() {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 500, 100, 100, 1, SelectionType.TRUNCATION, FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        populationVisualization.populateData(50, 10, 5, 50, 50, 5);
        assertEquals(50, populationVisualization.getGraphicParam().getBestFitness(0));
        assertEquals(50, populationVisualization.getGraphicParam().getBestFitness(0));
        assertEquals(50, populationVisualization.getGraphicParam().getBestFitness(0));
        assertTrue(50, populationVisualization.getGraphicParam().getBestFitness(0));
        assertTrue(50, populationVisualization.getGraphicParam().getBestFitness(0));
        assertTrue(50, populationVisualization.getGraphicParam().getBestFitness(0));
    
       
    }
}
