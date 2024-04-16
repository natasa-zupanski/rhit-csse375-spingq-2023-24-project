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
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010");
        firstGen[20].setChromosome("0000101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[96].getChromosome());
        assertEquals("0000101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[97].getChromosome());
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[99].getChromosome());
    }

    @Test
	public void testRank () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.RANK);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010");
        firstGen[20].setChromosome("0000101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[74].getChromosome());
        assertEquals("0000101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[75].getChromosome());
        assertEquals("0000101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[96].getChromosome());
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[99].getChromosome());
	}

    @Test
	public void testRankRoulette () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.RANKROULETTE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010");
        firstGen[20].setChromosome("0000101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[74].getChromosome());
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[99].getChromosome());
    }
}
