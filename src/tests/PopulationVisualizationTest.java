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

    @Test
    public void TestDrawOn ()
    {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 10, 100, 100, 1, SelectionType.TRUNCATION, FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        populationVisualization.populateData(50, 10, 5, 50, 50, 5);
        populationVisualization.populateData(40, 20, 10, 48, 52, 0);
        populationVisualization.populateData(30, 40, 20, 48, 52, 0);
        populationVisualization.populateData(50, 10, 5, 50, 50, 5);
        populationVisualization.populateData(40, 20, 10, 48, 52, 0);
        JFrame frame = new JFrame();
        frame.setSize(1313, 600);
        populationVisualization.drawOn(null, frame);
        String expectedString = "SetScale: x:1.1936363636363636 y:1.0\n" +
        "SetColor: BLACK\n" +
        "DrawLine: x1:50 y1:345 x2:50y2:355\n" +
        "DrawString: 0 x:40 y:370\n" +
        "DrawLine: x1:45 y1:350 x2:55y2:350\n" +
        "DrawString: 0 x:25 y:355\n" +
        "DrawLine: x1:150 y1:345 x2:150y2:355\n" +
        "DrawString: 1 x:140 y:370\n" +
        "DrawLine: x1:45 y1:320 x2:55y2:320\n" +
        "DrawString: 10 x:25 y:325\n" +
        "DrawLine: x1:250 y1:345 x2:250y2:355\n" +
        "DrawString: 2 x:240 y:370\n" +
        "DrawLine: x1:45 y1:290 x2:55y2:290\n" +
        "DrawString: 20 x:25 y:295\n" +
        "DrawLine: x1:350 y1:345 x2:350y2:355\n" +
        "DrawString: 3 x:340 y:370\n" +
        "DrawLine: x1:45 y1:260 x2:55y2:260\n" +
        "DrawString: 30 x:25 y:265\n" +
        "DrawLine: x1:450 y1:345 x2:450y2:355\n" +
        "DrawString: 4 x:440 y:370\n" +
        "DrawLine: x1:45 y1:230 x2:55y2:230\n" +
        "DrawString: 40 x:25 y:235\n" +
        "DrawLine: x1:550 y1:345 x2:550y2:355\n" +
        "DrawString: 5 x:540 y:370\n" +
        "DrawLine: x1:45 y1:200 x2:55y2:200\n" +
        "DrawString: 50 x:25 y:205\n" +
        "DrawLine: x1:650 y1:345 x2:650y2:355\n" +
        "DrawString: 6 x:640 y:370\n" +
        "DrawLine: x1:45 y1:170 x2:55y2:170\n" +
        "DrawString: 60 x:25 y:175\n" +
        "DrawLine: x1:750 y1:345 x2:750y2:355\n" +
        "DrawString: 7 x:740 y:370\n" +
        "DrawLine: x1:45 y1:140 x2:55y2:140\n" +
        "DrawString: 70 x:25 y:145\n" +
        "DrawLine: x1:850 y1:345 x2:850y2:355\n" +
        "DrawString: 8 x:840 y:370\n" +
        "DrawLine: x1:45 y1:110 x2:55y2:110\n" +
        "DrawString: 80 x:25 y:115\n" +
        "DrawLine: x1:950 y1:345 x2:950y2:355\n" +
        "DrawString: 9 x:940 y:370\n" +
        "DrawLine: x1:45 y1:80 x2:55y2:80\n" +
        "DrawString: 90 x:25 y:85\n" +
        "DrawLine: x1:1050 y1:345 x2:1050y2:355\n" +
        "DrawString: 10 x:1040 y:370\n" +
        "DrawLine: x1:45 y1:50 x2:55y2:50\n" +
        "DrawString: 100 x:25 y:55\n" +
        "SetStroke: BasicStroke Width: 2\n" +
        "DrawLine: x1:50 y1:350 x2:1050y2:350\n" +
        "DrawLine: x1:50 y1:50 x2:50y2:350\n" +
        "SetColor: GRAY\n" +
        "SetStroke: BasicStroke Width: 1\n" +
        "DrawLine: x1:50 y1:200 x2:350y2:200\n" +
        "SetStroke: BasicStroke Width: 2\n" +
        "SetColor: GREEN\n" +
        "DrawLine: x1:50 y1:200 x2:150y2:230\n" +
        "SetColor: ORANGE\n" +
        "DrawLine: x1:50 y1:320 x2:150y2:290\n" +
        "SetColor: RED\n" +
        "DrawLine: x1:50 y1:335 x2:150y2:320\n" +
        "SetColor: MAGENTA\n" +
        "DrawLine: x1:50 y1:305 x2:150y2:320\n" +
        "SetColor: BLACK\n" +
        "DrawLine: x1:50 y1:-100 x2:150y2:-70\n" +
        "SetColor: GREEN\n" +
        "DrawLine: x1:150 y1:230 x2:250y2:260\n" +
        "SetColor: ORANGE\n" +
        "DrawLine: x1:150 y1:290 x2:250y2:230\n" +
        "SetColor: RED\n" +
        "DrawLine: x1:150 y1:320 x2:250y2:290\n" +
        "SetColor: MAGENTA\n" +
        "DrawLine: x1:150 y1:320 x2:250y2:340\n" +
        "SetColor: BLACK\n" +
        "DrawLine: x1:50 y1:-70 x2:250y2:-40\n" +
        "SetColor: GREEN\n" +
        "DrawLine: x1:250 y1:260 x2:350y2:200\n" +
        "SetColor: ORANGE\n" +
        "DrawLine: x1:250 y1:230 x2:350y2:320\n" +
        "SetColor: RED\n" +
        "DrawLine: x1:250 y1:290 x2:350y2:335\n" +
        "SetColor: MAGENTA\n" +
        "DrawLine: x1:250 y1:340 x2:350y2:305\n" +
        "SetColor: BLACK\n" +
        "DrawLine: x1:50 y1:-40 x2:350y2:-100\n";
        assertEquals(expectedString, populationVisualization.getDrawValueString());
    }

    @Test
    public void TestDrawOn_LearningChance ()
    {
        EvolutionParameters evolutionParameters = new EvolutionParameters(1, 10, 100, 100, 1, SelectionType.LEARNINGCHANCE, FitnessType.NUMONES, true, 100);
        FakePopulationVisualization populationVisualization = new FakePopulationVisualization(evolutionParameters);
        populationVisualization.populateData(50, 10, 5, 50, 50, 5);
        populationVisualization.populateData(40, 20, 10, 48, 52, 0);
        populationVisualization.populateData(30, 40, 20, 48, 52, 0);
        populationVisualization.populateData(50, 10, 5, 50, 50, 5);
        populationVisualization.populateData(40, 20, 10, 48, 52, 0);
        JFrame frame = new JFrame();
        frame.setSize(1313, 600);
        populationVisualization.drawOn(null, frame);
        String expectedString = "SetScale: x:1.1936363636363636 y:1.0\n" +
        "SetColor: BLACK\n" +
        "DrawLine: x1:50 y1:345 x2:50y2:355\n" +
        "DrawString: 0 x:40 y:370\n" +
        "DrawLine: x1:45 y1:350 x2:55y2:350\n" +
        "DrawString: 0 x:25 y:355\n" +
        "DrawLine: x1:150 y1:345 x2:150y2:355\n" +
        "DrawString: 1 x:140 y:370\n" +
        "DrawLine: x1:45 y1:320 x2:55y2:320\n" +
        "DrawString: 10 x:25 y:325\n" +
        "DrawLine: x1:250 y1:345 x2:250y2:355\n" +
        "DrawString: 2 x:240 y:370\n" +
        "DrawLine: x1:45 y1:290 x2:55y2:290\n" +
        "DrawString: 20 x:25 y:295\n" +
        "DrawLine: x1:350 y1:345 x2:350y2:355\n" +
        "DrawString: 3 x:340 y:370\n" +
        "DrawLine: x1:45 y1:260 x2:55y2:260\n" +
        "DrawString: 30 x:25 y:265\n" +
        "DrawLine: x1:450 y1:345 x2:450y2:355\n" +
        "DrawString: 4 x:440 y:370\n" +
        "DrawLine: x1:45 y1:230 x2:55y2:230\n" +
        "DrawString: 40 x:25 y:235\n" +
        "DrawLine: x1:550 y1:345 x2:550y2:355\n" +
        "DrawString: 5 x:540 y:370\n" +
        "DrawLine: x1:45 y1:200 x2:55y2:200\n" +
        "DrawString: 50 x:25 y:205\n" +
        "DrawLine: x1:650 y1:345 x2:650y2:355\n" +
        "DrawString: 6 x:640 y:370\n" +
        "DrawLine: x1:45 y1:170 x2:55y2:170\n" +
        "DrawString: 60 x:25 y:175\n" +
        "DrawLine: x1:750 y1:345 x2:750y2:355\n" +
        "DrawString: 7 x:740 y:370\n" +
        "DrawLine: x1:45 y1:140 x2:55y2:140\n" +
        "DrawString: 70 x:25 y:145\n" +
        "DrawLine: x1:850 y1:345 x2:850y2:355\n" +
        "DrawString: 8 x:840 y:370\n" +
        "DrawLine: x1:45 y1:110 x2:55y2:110\n" +
        "DrawString: 80 x:25 y:115\n" +
        "DrawLine: x1:950 y1:345 x2:950y2:355\n" +
        "DrawString: 9 x:940 y:370\n" +
        "DrawLine: x1:45 y1:80 x2:55y2:80\n" +
        "DrawString: 90 x:25 y:85\n" +
        "DrawLine: x1:1050 y1:345 x2:1050y2:355\n" +
        "DrawString: 10 x:1040 y:370\n" +
        "DrawLine: x1:45 y1:50 x2:55y2:50\n" +
        "DrawString: 100 x:25 y:55\n" +
        "SetStroke: BasicStroke Width: 2\n" +
        "DrawLine: x1:50 y1:350 x2:1050y2:350\n" +
        "DrawLine: x1:50 y1:50 x2:50y2:350\n" +
        "SetColor: GRAY\n" +
        "SetStroke: BasicStroke Width: 1\n" +
        "DrawLine: x1:50 y1:200 x2:350y2:200\n" +
        "SetStroke: BasicStroke Width: 2\n" +
        "SetColor: GREEN\n" +
        "DrawLine: x1:50 y1:200 x2:150y2:230\n" +
        "SetColor: ORANGE\n" +
        "DrawLine: x1:50 y1:320 x2:150y2:290\n" +
        "SetColor: RED\n" +
        "DrawLine: x1:50 y1:335 x2:150y2:320\n" +
        "SetColor: MAGENTA\n" +
        "DrawLine: x1:50 y1:305 x2:150y2:320\n" +
        "SetColor: BLACK\n" +
        "DrawLine: x1:50 y1:-100 x2:150y2:-70\n" +
        "SetColor: BLUE\n" +
        "DrawLine: x1:50 y1:200 x2:150y2:206\n" +
        "SetColor: CYAN\n" +
        "DrawLine: x1:50 y1:200 x2:150y2:194\n" +
        "SetColor: LIGHT_GRAY\n" +
        "DrawLine: x1:50 y1:335 x2:150y2:350\n" +
        "SetColor: GREEN\n" +
        "DrawLine: x1:150 y1:230 x2:250y2:260\n" +
        "SetColor: ORANGE\n" +
        "DrawLine: x1:150 y1:290 x2:250y2:230\n" +
        "SetColor: RED\n" +
        "DrawLine: x1:150 y1:320 x2:250y2:290\n" +
        "SetColor: MAGENTA\n" +
        "DrawLine: x1:150 y1:320 x2:250y2:340\n" +
        "SetColor: BLACK\n" +
        "DrawLine: x1:50 y1:-70 x2:250y2:-40\n" +
        "SetColor: BLUE\n" +
        "DrawLine: x1:150 y1:206 x2:250y2:206\n" +
        "SetColor: CYAN\n" +
        "DrawLine: x1:150 y1:194 x2:250y2:194\n" +
        "SetColor: LIGHT_GRAY\n" +
        "DrawLine: x1:150 y1:350 x2:250y2:350\n" +
        "SetColor: GREEN\n" +
        "DrawLine: x1:250 y1:260 x2:350y2:200\n" +
        "SetColor: ORANGE\n" +
        "DrawLine: x1:250 y1:230 x2:350y2:320\n" +
        "SetColor: RED\n" +
        "DrawLine: x1:250 y1:290 x2:350y2:335\n" +
        "SetColor: MAGENTA\n" +
        "DrawLine: x1:250 y1:340 x2:350y2:305\n" +
        "SetColor: BLACK\n" +
        "DrawLine: x1:50 y1:-40 x2:350y2:-100\n" +
        "SetColor: BLUE\n" +
        "DrawLine: x1:250 y1:206 x2:350y2:200\n" +
        "SetColor: CYAN\n" +
        "DrawLine: x1:250 y1:194 x2:350y2:200\n" +
        "SetColor: LIGHT_GRAY\n" +
        "DrawLine: x1:250 y1:350 x2:350y2:335\n";
        assertEquals(expectedString, populationVisualization.getDrawValueString());
    }
}
