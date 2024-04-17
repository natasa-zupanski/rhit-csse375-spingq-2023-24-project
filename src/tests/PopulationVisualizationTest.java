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
    public void TestCalculateScaleFactors_DefaultScreen () {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 500, 100, 100, 1, SelectionType.TRUNCATION,
                FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        JFrame frame = new JFrame();
        frame.setSize(1313, 600);
        populationVisualization.calculateScaleFactorsPublic(frame);
        assertEquals(1.1936363636363636, populationVisualization.getScaleFactorX(), 0.0);
        assertEquals(1, populationVisualization.getScaleFactorY(), 0.0);
    }

    @Test
    public void TestCalculateScaleFactors_LargerScreen () {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 500, 100, 100, 1, SelectionType.TRUNCATION,
                FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        JFrame frame = new JFrame();
        frame.setSize(2000, 700);
        populationVisualization.calculateScaleFactorsPublic(frame);
        assertEquals(1.81, populationVisualization.getScaleFactorX(), 0.01);
        assertEquals(1.16, populationVisualization.getScaleFactorY(), 0.01);
    }

    @Test
    public void TestCalculateScaleFactors_SmallerScreen () {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 500, 100, 100, 1, SelectionType.TRUNCATION,
                FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        populationVisualization.calculateScaleFactorsPublic(frame);
        assertEquals(0.90, populationVisualization.getScaleFactorX(), 0.01);
        assertEquals(0.83, populationVisualization.getScaleFactorY(), 0.01);
    }

    @Test
    public void TestPopulationData () {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 500, 100, 100, 1, SelectionType.TRUNCATION,
                FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        populationVisualization.populateData(50, 10, 5, 50, 50, 5);
        populationVisualization.populateData(40, 20, 10, 50, 50, 5);
        assertEquals(50, populationVisualization.getGraphicParam().getBestFitness(0));
        assertEquals(10, populationVisualization.getGraphicParam().getAvgFitness(0));
        assertEquals(5, populationVisualization.getGraphicParam().getLowFitness(0));
        assertEquals(40, populationVisualization.getGraphicParam().getBestFitness(1));
        assertEquals(20, populationVisualization.getGraphicParam().getAvgFitness(1));
        assertEquals(10, populationVisualization.getGraphicParam().getLowFitness(1));
    }

    @Test
    public void TestPopulationData_SelectionTypeLearningChange () {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 500, 100, 100, 1,
                SelectionType.LEARNINGCHANCE, FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        populationVisualization.populateData(50, 10, 5, 50, 50, 5);
        populationVisualization.populateData(40, 20, 10, 48, 52, 0);
        assertEquals(50, populationVisualization.getGraphicParam().getBestFitness(0));
        assertEquals(10, populationVisualization.getGraphicParam().getAvgFitness(0));
        assertEquals(5, populationVisualization.getGraphicParam().getLowFitness(0));
        assertEquals(50, populationVisualization.getGraphicParam().getAvgNum0s(0));
        assertEquals(50, populationVisualization.getGraphicParam().getAvgNum1s(0));
        assertEquals(5, populationVisualization.getGraphicParam().getAvgNumQs(0));
        assertEquals(40, populationVisualization.getGraphicParam().getBestFitness(1));
        assertEquals(20, populationVisualization.getGraphicParam().getAvgFitness(1));
        assertEquals(10, populationVisualization.getGraphicParam().getLowFitness(1));
        assertEquals(52, populationVisualization.getGraphicParam().getAvgNum0s(1));
        assertEquals(48, populationVisualization.getGraphicParam().getAvgNum1s(1));
        assertEquals(0, populationVisualization.getGraphicParam().getAvgNumQs(1));
    }

    @Test
    public void TestGensSoFar ()
    {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 3, 100, 100, 1, SelectionType.LEARNINGCHANCE, FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        populationVisualization.populateData(50, 10, 5, 50, 50, 5);
        populationVisualization.populateData(40, 20, 10, 48, 52, 0);
        assertEquals(2, populationVisualization.gensSoFar());
        populationVisualization.getEvolutionParameters().terminate();
        assertEquals(3, populationVisualization.gensSoFar());
    }

    @Test
    public void TestPrintBestFitness () 
    {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 3, 100, 100, 1, SelectionType.LEARNINGCHANCE, FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        populationVisualization.populateData(50, 10, 5, 50, 50, 5);
        populationVisualization.printBestFitness();
        assertEquals("Created gen #1 Best fitness: 50", populationVisualization.getBestFitnessString());
        populationVisualization.populateData(40, 20, 10, 48, 52, 0);
        populationVisualization.printBestFitness();
        assertEquals("Created gen #2 Best fitness: 40", populationVisualization.getBestFitnessString());
        populationVisualization.populateData(30, 20, 10, 48, 52, 0);
        populationVisualization.getEvolutionParameters().terminate();
        populationVisualization.printBestFitness();
        assertEquals("Created gen #3 Best fitness: 30", populationVisualization.getBestFitnessString());
    }

    


}
