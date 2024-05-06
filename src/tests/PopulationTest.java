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
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1010101010");
        firstGen[20].setChromosome("0000101010");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010101010",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010101010", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[95].getChromosome());
        assertEquals("0000101010",secondGen[96].getChromosome());
        assertEquals("1010101010",secondGen[99].getChromosome());
    }

    @Test
	public void testTrucation_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011000000");
        firstGen[20].setChromosome("1010000000");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[95].getChromosome());
        assertEquals("1011000000",secondGen[96].getChromosome());
        assertEquals("1010000000",secondGen[99].getChromosome());
    }

    @Test
	public void testTrucation_ConsectiveNum1s() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.CONSECONES);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011111000");
        firstGen[20].setChromosome("1010000000");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1011111000",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1011111000", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[95].getChromosome());
        assertEquals("1010000000",secondGen[96].getChromosome());
        assertEquals("1011111000",secondGen[99].getChromosome());
    }

    @Test
	public void testTrucation_FiftyFifty() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.FIFTYFIFTY);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011000000");
        firstGen[20].setChromosome("1010000000");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1011000000",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1011000000", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[95].getChromosome());
        assertEquals("1010000000",secondGen[96].getChromosome());
        assertEquals("1011000000",secondGen[99].getChromosome());
    }

    @Test
	public void testTrucation_OneMinusZeros() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.ONESMINUSZEROS);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011000000");
        firstGen[20].setChromosome("1010000000");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("0000000000",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("0000000000", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[99].getChromosome());
    }

    @Test
	public void testRank () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.RANK);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1010101010");
        firstGen[20].setChromosome("0000101010");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010101010",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010101010", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[74].getChromosome());
        assertEquals("0000101010",secondGen[75].getChromosome());
        assertEquals("0000101010",secondGen[95].getChromosome());
        assertEquals("1010101010",secondGen[99].getChromosome());
	}

    @Test
	public void testRank_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.RANK);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011000000");
        firstGen[20].setChromosome("1010000000");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[74].getChromosome());
        assertEquals("1011000000",secondGen[75].getChromosome());
        assertEquals("1011000000",secondGen[95].getChromosome());
        assertEquals("1010000000",secondGen[99].getChromosome());
    }

    @Test
	public void testRankRoulette () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.RANKROULETTE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1010101010");
        firstGen[20].setChromosome("0000101010");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010101010",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010101010", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[95].getChromosome());
        assertEquals("1010101010",secondGen[99].getChromosome());
    }

    @Test
	public void testRankRoulette_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.RANKROULETTE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011000000");
        firstGen[20].setChromosome("1010000000");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[96].getChromosome());
        assertEquals("1010000000",secondGen[99].getChromosome());
    }

    @Test
	public void testRouletteWheel () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.ROULETTEWHEEL);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1010101010");
        firstGen[20].setChromosome("0000101010");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010101010",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010101010", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000101010",secondGen[1].getChromosome());
        assertEquals("1010101010",secondGen[99].getChromosome());
    }

    @Test
	public void testRoletteWheel_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.ROULETTEWHEEL);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011010000");
        firstGen[20].setChromosome("1010000000");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[96].getChromosome());
        assertEquals("1010000000",secondGen[99].getChromosome());
    }

    // @Test
	// public void testLearningChance () {
    //     Population testPopulation = new Population();
    //     testPopulation.getEvolutionParameters().setGenomeLength(10);
    //     testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
    //     testPopulation.getEvolutionParameters().setSelection(SelectionType.LEARNINGCHANCE);
    //     testPopulation.spawnFirstGeneration();
    //     Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
    //     firstGen[10].setChromosome("1010101010");
    //     firstGen[20].setChromosome("0000101010");
    //     Organism fittestOrganism = testPopulation.getFittest();
    //     assertEquals("1010101010",fittestOrganism.getChromosome());
    //     testPopulation.nextGeneration();
    //     Organism newFittestOrganism = testPopulation.getFittest();
	//     assertEquals("1010101010", newFittestOrganism.getChromosome());
    //     Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
    //     assertEquals("0000000000",secondGen[96].getChromosome());
    //     assertEquals("1010101010",secondGen[99].getChromosome());
    // }

    // @Test
	// public void testLearningChance_TargetOrganismFitness() {
    //     Population testPopulation = new Population();
    //     testPopulation.getEvolutionParameters().setGenomeLength(10);
    //     testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
    //     testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
    //     testPopulation.getEvolutionParameters().setSelection(SelectionType.LEARNINGCHANCE);
    //     testPopulation.spawnFirstGeneration();
    //     Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
    //     firstGen[10].setChromosome("1011010000");
    //     firstGen[20].setChromosome("1010000000");
    //     Organism fittestOrganism = testPopulation.getFittest();
    //     assertEquals("1010000000",fittestOrganism.getChromosome());
    //     testPopulation.nextGeneration();
    //     Organism newFittestOrganism = testPopulation.getFittest();
	//     assertEquals("1010000000", newFittestOrganism.getChromosome());
    //     Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
    //     assertEquals("0000000000",secondGen[96].getChromosome());
    //     assertEquals("1010000000",secondGen[99].getChromosome());
    // }

    @Test
	public void testStableState () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.STABLESTATE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1010101010");
        firstGen[20].setChromosome("0000101010");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010101010",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010101010", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[96].getChromosome());
        assertEquals("0000101010",secondGen[97].getChromosome());
        assertEquals("1010101010",secondGen[99].getChromosome());
    }

    @Test
	public void testStableState_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.STABLESTATE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011000000");
        firstGen[20].setChromosome("1010000000");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[96].getChromosome());
        assertEquals("1011000000",secondGen[97].getChromosome());
        assertEquals("1010000000",secondGen[99].getChromosome());
    }

    @Test
	public void testAlternate () {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.ALTERNATE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1010101010");
        firstGen[20].setChromosome("0000101010");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010101010",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010101010", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[96].getChromosome());
        assertEquals("0000101010",secondGen[97].getChromosome());
        assertEquals("1010101010",secondGen[99].getChromosome());
    }

    @Test
	public void testAlternate_TargetOrganismFitness() {
        Population testPopulation = new Population();
        testPopulation.getEvolutionParameters().setGenomeLength(10);
        testPopulation.getEvolutionParameters().setRandomeType(RandomType.FAKEPOPULATION);
        testPopulation.getEvolutionParameters().setFitnessMethod(FitnessType.TARGETORG);
        testPopulation.getEvolutionParameters().setSelection(SelectionType.ALTERNATE);
        testPopulation.spawnFirstGeneration();
        Organism[] firstGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        firstGen[10].setChromosome("1011000000");
        firstGen[20].setChromosome("1010000000");
        Organism fittestOrganism = testPopulation.getFittest();
        assertEquals("1010000000",fittestOrganism.getChromosome());
        testPopulation.nextGeneration();
        Organism newFittestOrganism = testPopulation.getFittest();
	    assertEquals("1010000000", newFittestOrganism.getChromosome());
        Organism[] secondGen = testPopulation.getEvolutionParameters().getCurrentGeneration();
        assertEquals("0000000000",secondGen[96].getChromosome());
        assertEquals("1011000000",secondGen[97].getChromosome());
        assertEquals("1010000000",secondGen[99].getChromosome());
    }

}
