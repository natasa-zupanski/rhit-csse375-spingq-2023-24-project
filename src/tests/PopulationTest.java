package tests;
import static org.junit.Assert.*;

import org.junit.*;

import mainApp.FitnessType;
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
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[95].getChromosome());
        assertEquals("0000101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[96].getChromosome());
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[99].getChromosome());
    }

    @Test
	public void testTrucation_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        firstGen[20].setChromosome("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[95].getChromosome());
        assertEquals("1011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[96].getChromosome());
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[99].getChromosome());
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
        assertEquals("0000101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[95].getChromosome());
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[99].getChromosome());
	}

    @Test
	public void testRank_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.RANK);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        firstGen[20].setChromosome("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[74].getChromosome());
        assertEquals("1011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[75].getChromosome());
        assertEquals("1011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[95].getChromosome());
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[99].getChromosome());
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
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[95].getChromosome());
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[99].getChromosome());
    }

    @Test
	public void testRankRoulette_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.RANKROULETTE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        firstGen[20].setChromosome("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[95].getChromosome());
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[99].getChromosome());
    }

    @Test
	public void testRouletteWheel () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.ROULETTEWHEEL);
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
        assertEquals("0000101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[1].getChromosome());
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[99].getChromosome());
    }

    @Test
	public void testRoletteWheel_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.ROULETTEWHEEL);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        firstGen[20].setChromosome("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[96].getChromosome());
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[99].getChromosome());
    }

    @Test
	public void testLearningChance () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.LEARNINGCHANCE);
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
        assertEquals("1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010",secondGen[99].getChromosome());
    }

    @Test
	public void testLearningChance_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.LEARNINGCHANCE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        firstGen[20].setChromosome("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[96].getChromosome());
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[99].getChromosome());
    }

    @Test
	public void testStableState () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.STABLESTATE);
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
	public void testStableState_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.STABLESTATE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        firstGen[20].setChromosome("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("1000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[96].getChromosome());
        assertEquals("0011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[97].getChromosome());
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[99].getChromosome());
    }

    @Test
	public void testAlternate () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.ALTERNATE);
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
	public void testAlternate_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.ALTERNATE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        firstGen[20].setChromosome("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",secondGen[96].getChromosome());
        assertEquals("1011010000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[97].getChromosome());
        assertEquals("1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",secondGen[99].getChromosome());
    }

}
