package tests;
import static org.junit.Assert.assertEquals;
import org.junit.*;

import mainApp.Organism;
import mainApp.Population;

public class PopulationTest {

    @Test
	public void testTrucation () {
        Population testPopulation = new Population();
        testPopulation.newGen();
        testPopulation.getFittest();
        testPopulation.nextGeneration();
        Organism fitOrganism = testPopulation.getFittest();
        String organism = fitOrganism.getChromosome();
		assertEquals(0, organism);
	}
}
