package tests;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;

import mainApp.FitnessType;
import mainApp.Organism;
import mainApp.Population;
import mainApp.PopulationComponent;
import mainApp.RandomType;
import mainApp.SelectionType;

public class PoppulationComponetTest {
    // Testing all methods directly effected by refactoring
    @Test
    public void testPopulationConstructor() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation, new JFrame());

        assertEquals("1", pComponent.handleGetMutationRate());
        assertEquals("500", pComponent.handleGetNumGens());
        assertEquals("100", pComponent.handleGetGenSize());
        assertEquals("100", pComponent.handleGetGenomeLength());
        assertEquals(false, pComponent.handleGetCrossover());
        assertEquals("1", pComponent.handleGetElitism()); 
        assertEquals(0, pComponent.handleGetGensSoFar());
        assertEquals("100", pComponent.handleGetTermination());
    }

    @Test
    public void testPopulationSetFields() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation, new JFrame());

        assertEquals("1", pComponent.handleGetMutationRate());
        pComponent.handleSetMutationRate(2);
        assertEquals("2", pComponent.handleGetMutationRate());

        assertEquals("500", pComponent.handleGetNumGens());
        pComponent.handleSetNumGens(200);
        assertEquals("200", pComponent.handleGetNumGens());

        assertEquals(false, pComponent.handleGetCrossover());
        pComponent.handleSetCrossover(true);
        assertEquals(true, pComponent.handleGetCrossover());

        assertEquals("100", pComponent.handleGetGenSize());
        pComponent.handleSetGenSize(105);
        assertEquals("105", pComponent.handleGetGenSize());

        assertEquals("100", pComponent.handleGetGenomeLength());
        pComponent.handleSetGenomeLength(200);
        assertEquals("200", pComponent.handleGetGenomeLength());

        assertEquals("1", pComponent.handleGetElitism());
        pComponent.handleSetElitism(2);
        assertEquals("2", pComponent.handleGetElitism());

        assertEquals("100", pComponent.handleGetTermination());
        pComponent.handleSetTermination(50);
        assertEquals("50", pComponent.handleGetTermination());

        assertEquals(FitnessType.TARGETORG, pComponent.getPopulation().getEvolutionParameters().getFitnessType());
        pComponent.handleSetFitness(FitnessType.NUMONES);
        assertEquals(FitnessType.NUMONES, pComponent.getPopulation().getEvolutionParameters().getFitnessType());

        assertEquals(SelectionType.TRUNCATION, pComponent.getPopulation().getEvolutionParameters().getSelectionType());
        pComponent.handleSetSelection(SelectionType.RANK);
        assertEquals(SelectionType.RANK, pComponent.getPopulation().getEvolutionParameters().getSelectionType());
        assertEquals(FitnessType.NUMONES, pComponent.getPopulation().getEvolutionParameters().getFitnessType());
        pComponent.handleSetSelection(SelectionType.LEARNINGCHANCE);
        assertEquals(SelectionType.LEARNINGCHANCE, pComponent.getPopulation().getEvolutionParameters().getSelectionType());
        assertEquals(FitnessType.LEARNINGCHANCE, pComponent.getPopulation().getEvolutionParameters().getFitnessType());

    }

    @Test
    public void testGetGenSoFar() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation, new JFrame());

        assertEquals(0, pComponent.handleGetGensSoFar());

        testPopulation.spawnFirstGeneration();
        testPopulation.nextGeneration();
        assertEquals(1, pComponent.handleGetGensSoFar());
        assertEquals(10, pComponent.getColumnNum());

        testPopulation.nextGeneration();
        assertEquals(2, pComponent.handleGetGensSoFar());
        assertEquals(10, pComponent.getColumnNum());

        testPopulation.nextGeneration();
        assertEquals(3, pComponent.handleGetGensSoFar());
        assertEquals(10, pComponent.getColumnNum());
    }

    //Integration testing
    @Test
    public void testHandleRunPopulationEvol() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation, new JFrame());
        assertArrayEquals(new Organism[100],pComponent.handleGetLatestGen());
        pComponent.handleRunPopulationEvol();
        assertTrue(new Organism[100] != pComponent.handleGetLatestGen());
    }

    @Test
    public void testCreateNewPopulation() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation, new JFrame());
        pComponent.getPopulation().getEvolutionParameters().setUnsure(true);
        pComponent.handleRunPopulationEvol();
        pComponent.getPopulation().getEvolutionParameters().terminate();
        Organism[] currentGen = pComponent.handleGetLatestGen();
        pComponent.createNewPopulation();
        assertFalse(pComponent.getPopulation().getEvolutionParameters().getUnsure());
        assertFalse(pComponent.getPopulation().getEvolutionParameters().getTerminated());
        assertArrayEquals(new Organism[100],pComponent.handleGetLatestGen());
        assertTrue(currentGen != pComponent.handleGetLatestGen());
    }

    @Test
    public void testCreateNewPopulation_LearningChance() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation, new JFrame());
        pComponent.getPopulation().getEvolutionParameters().setSelection(SelectionType.LEARNINGCHANCE);
        pComponent.getPopulation().getEvolutionParameters().setUnsure(true);
        pComponent.handleRunPopulationEvol();
        pComponent.getPopulation().getEvolutionParameters().terminate();
        Organism[] currentGen = pComponent.handleGetLatestGen();
        pComponent.createNewPopulation();
        assertTrue(pComponent.getPopulation().getEvolutionParameters().getUnsure());
        assertFalse(pComponent.getPopulation().getEvolutionParameters().getTerminated()); 
        assertArrayEquals(new Organism[100],pComponent.handleGetLatestGen());
        assertTrue(currentGen != pComponent.handleGetLatestGen());
    }

    @Test
    public void testUpdateState() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation, new JFrame());
        pComponent.getPopulation().getEvolutionParameters().setGenomeLength(10);
        pComponent.getPopulation().getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        pComponent.handleRunPopulationEvol();
        Organism[] firstGen = pComponent.handleGetLatestGen();
        assertEquals("0000000000", pComponent.getPopulation().getFittest().getChromosome());
        firstGen[10].setChromosome("1010000000");
        pComponent.updateState();
        Organism[] secondGen = pComponent.handleGetLatestGen();
        assertEquals("1010000000", pComponent.getPopulation().getFittest().getChromosome());
        assertTrue(firstGen != secondGen);
    }

    @Test
    public void testHadleTermination() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation, new JFrame());
        assertFalse(pComponent.getPopulation().getEvolutionParameters().getTerminated());
        pComponent.handleRunPopulationEvol();
        pComponent.handleTerminate();
        assertTrue(pComponent.getPopulation().getEvolutionParameters().getTerminated());
    }

    @Test
    public void testSetTargetOrganism() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation, new JFrame());
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110", pComponent.getPopulation().getEvolutionParameters().getTargetOrganism());
        pComponent.setTargetOrganism("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        assertEquals("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111", pComponent.getPopulation().getEvolutionParameters().getTargetOrganism());
    }

}
