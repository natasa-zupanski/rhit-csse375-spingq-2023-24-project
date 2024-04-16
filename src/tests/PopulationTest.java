package tests;
import static org.junit.Assert.*;

import org.junit.*;

import mainApp.Organism;
import mainApp.Population;
import mainApp.RandomType;
import mainApp.SelectionType; 

public class PopulationTest {

    @Test
	public void testTrucation () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[10].getChromosome());
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[99].getChromosome());
    }

    @Test
	public void testRank () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKE);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.RANK);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010", newFittestOrganism.getChromosome());
	}
}
