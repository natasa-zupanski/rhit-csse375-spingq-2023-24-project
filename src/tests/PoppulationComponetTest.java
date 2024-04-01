package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mainApp.Population;
import mainApp.PopulationComponent;

public class PoppulationComponetTest {
    // Testing all methods directly effected by refactoring
    @Test
    public void testCreateNewPopulation() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation);

        pComponent.createNewPopulation(2, 200, 105, 200, 1, "Rank", "Target Organism", false, 50);
        assertEquals("2", pComponent.handleGetMutationRate());
        assertEquals("200", pComponent.handleGetNumGens());
        assertEquals("105", pComponent.handleGetGenSize());
        assertEquals("200", pComponent.handleGetGenomeLength());
        assertEquals(false, pComponent.handleGetCrossover());
        assertEquals("1", pComponent.handleGetElitism());
        assertEquals(0, pComponent.handleGetGensSoFar());
        assertEquals("50", pComponent.handleGetTermination());
    }

    @Test
    public void testPopulationConstructor() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation);

        assertEquals("1", pComponent.handleGetMutationRate());
        assertEquals("500", pComponent.handleGetNumGens());
        assertEquals("100", pComponent.handleGetGenSize());
        assertEquals("100", pComponent.handleGetGenomeLength());
        assertEquals(true, pComponent.handleGetCrossover());
        assertEquals("1", pComponent.handleGetElitism());
        assertEquals(0, pComponent.handleGetGensSoFar());
        assertEquals("100", pComponent.handleGetTermination());
    }

    @Test
    public void testPopulationSetFields() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation);

        assertEquals("1", pComponent.handleGetMutationRate());
        pComponent.handleSetMutationRate(2);
        assertEquals("2", pComponent.handleGetMutationRate());

        assertEquals("500", pComponent.handleGetNumGens());
        pComponent.handleSetNumGens(200);
        assertEquals("200", pComponent.handleGetNumGens());

        assertEquals(true, pComponent.handleGetCrossover());
        pComponent.handleSetCrossover(false);
        assertEquals(false, pComponent.handleGetCrossover());

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

        assertEquals("Num. of 1s", pComponent.getPopulation().getEvolutionParameters().getFitnessMethod());
        pComponent.handleSetFitness("Target Organism");
        assertEquals("Target Organism", pComponent.getPopulation().getEvolutionParameters().getFitnessMethod());

        assertEquals("Truncation", pComponent.getPopulation().getEvolutionParameters().getSelectionMethod());
        pComponent.handleSetSelection("Rank");
        assertEquals("Rank", pComponent.getPopulation().getEvolutionParameters().getSelectionMethod());

    }

    @Test
    public void testGetGenSoFar() {
        Population testPopulation = new Population();
        PopulationComponent pComponent = new PopulationComponent(testPopulation);

        assertEquals(0, pComponent.handleGetGensSoFar());

        testPopulation.spawnFirstGeneration();
        testPopulation.nextGeneration();
        assertEquals(1, pComponent.handleGetGensSoFar());
        assertEquals(10, pComponent.handleGetLatestGen().getColumnNum());

        testPopulation.nextGeneration();
        assertEquals(2, pComponent.handleGetGensSoFar());
        assertEquals(10, pComponent.handleGetLatestGen().getColumnNum());

        testPopulation.nextGeneration();
        assertEquals(3, pComponent.handleGetGensSoFar());
        assertEquals(10, pComponent.handleGetLatestGen().getColumnNum());
    }
}
	