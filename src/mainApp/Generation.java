package mainApp;

import java.util.Arrays;

/**
 * 
 * Class: Generation
 * 
 * @author R_003: Allyn Loyd and Natasa Zupanski
 *         <br>
 *         Purpose: simulate a generation of organisms, quickly surveying the
 *         group of organisms that constitute it and providing important
 *         information for graphics as well as having a graphic representation.
 *         <br>
 *         Restrictions: None
 *
 */
public class Generation {
	// changing fields
	private Organism[] organisms;
	private String fitnessMethod = "Num. of 1s";
	private FitnessType defaultType = FitnessType.NUMONES;
	private String selectionMethod = "Truncation";

	/**
	 * ensures: constructs a default generation with default values, some given in
	 * the above fields
	 */
	public Generation() {
		this.organisms = new Organism[100];
		for (int i = 0; i < 100; i++) {
			this.organisms[i] = new Organism(100, defaultType);
		}
	}

	/**
	 * 
	 * ensures: constructs a generation using the size, the number of organisms in
	 * the generation, and chromosome length given so that all organisms in the
	 * generation have that number of alleles
	 * 
	 * @param genSize,          the number of organisms to be in the generation
	 * @param chromosomeLength, the number of alleles each organism in the
	 *                          generation should have
	 */
	public Generation(int genSize, int chromosomeLength) {
		this.organisms = new Organism[genSize];
		for (int i = 0; i < genSize; i++) {
			this.organisms[i] = new Organism(chromosomeLength, defaultType);
		}
	}

	/**
	 * 
	 * ensures: constructs a generation from a given array of organisms
	 * 
	 * @param orgs, the organisms from which to construct the generation
	 */
	public Generation(Organism[] orgs) {
		this.organisms = orgs;
	}

	/**
	 * 
	 * ensures: constructs a new generation from the number of organisms to be in
	 * that organism, the number of alleles each of these should have, the fitness
	 * method each should use to determine its fitness. The selection method, used
	 * to determine which organisms are selected to make up the next generation is
	 * also included.
	 * 
	 * @param genSize,          the number of organisms in the generation
	 * @param chromosomeLength, the number of alleles each of the organisms in the
	 *                          generation should have
	 * @param selectionMethod,  the name of the method used to decide which
	 *                          organisms will constitute the next generation
	 * @param fitnessMethod,    the name of the method used to calculate the fitness
	 *                          of an organism from their genetic code or chromosome
	 */
	public Generation(int genSize, int chromosomeLength, String selectionMethod, FitnessType type) {
		this.selectionMethod = selectionMethod;
		this.organisms = new Organism[genSize];
		boolean unsureChromosome = false;
		if (this.selectionMethod.equals("Learning Chance")) {
			unsureChromosome = true;
		}
		for (int i = 0; i < genSize; i++) {
			this.organisms[i] = new Organism(chromosomeLength, type, unsureChromosome);
			this.organisms[i].setFitnessMethod(fitnessMethod);
		}
	}

	/**
	 * 
	 * ensures: constructs a new generation based on an array of organisms, the
	 * selection method givenn, and the fitness method given.
	 * 
	 * @param orgs,            the organisms to populate this generation
	 * @param selectionMethod, the name of the process to be used to select the next
	 *                         generation
	 * @param fitnessMethod,   the name of the process to be used to determine how
	 *                         fit or unfit an organism is
	 */
	public Generation(Organism[] orgs, String selectionMethod, String fitnessMethod) {
		this(orgs);
		this.selectionMethod = selectionMethod;
		this.fitnessMethod = fitnessMethod;
		for (Organism o : this.organisms) {
			o.setFitnessMethod(fitnessMethod);
		}
	}

	/**
	 * 
	 * ensures: resets the constant fitnesses, used in recreating the research
	 * results, to their default value of -1
	 * 
	 */
	public void resetConstantFitnesses() {
		for (Organism o : this.organisms) {
			o.resetConstantFitness();
		}
	}

	/**
	 * 
	 * ensures: sorts the organisms of the generation as according to their fitness
	 * 
	 */
	public void sortFitness() {
		Arrays.sort(this.organisms);
	}

	/**
	 * 
	 * ensures: gets and returns the fittest organism of the generation, that
	 * organism with the greatest fitness
	 * 
	 * @return, the fittest organism of the generation
	 */
	public Organism getFittest() {
		this.sortFitness();
		return organisms[organisms.length - 1];
	}

	/**
	 *
	 * ensures: gets and returns the size of the generation, the number of organisms
	 * in the generation
	 * 
	 * @return, the size of the generation
	 */
	private int size() {
		return this.organisms.length;
	}

	/**
	 * 
	 * ensures: sets the fitness method of the generation to the given fitness
	 * method. This new fitness method will be used to determine the fitness of each
	 * organism from their genetic code.
	 * 
	 * @param method, the name of the method the generation will hence for use
	 */
	public void setFitnessMethod(String method) {
		this.fitnessMethod = method;
	}

	/**
	 * 
	 * ensures: gets and returns the organisms of the generation
	 * 
	 * @return, the organisms of the generation
	 */
	public Organism[] getOrganisms() {
		return this.organisms;
	}

	/**
	 * 
	 * ensures: gets and returns the fitness of the organism with the best fitness
	 * in the generation.
	 * 
	 * @return, the fitness of the organism with the best fitness in the generation.
	 */
	public int getBestFitness() {
		return this.getFittest().fitness();
	}

	/**
	 * 
	 * ensures: gets and returns the fitness of the least fit organism in the
	 * generation, this being the lowest fitness in the generation.
	 * 
	 * @return, the fitness of the least fit organism in the generation.
	 */
	public int getLowFitness() {
		Arrays.sort(organisms);
		return organisms[0].fitness();
	}

	/**
	 * 
	 * ensures: calculates and returns the average fitness of the generation as an
	 * integer
	 * 
	 * @return, the average fitness of the generation
	 */
	public int getAvgFitness() {
		return this.getTotalFitness() / organisms.length;
	}

	/**
	 * 
	 * ensures: calculates and returns the average number of 1s in the genetic code
	 * of the organisms of the generation
	 * 
	 * @return, the average number of 1s in the generation
	 */
	public int getAvg1s() {
		int sum = 0;
		for (Organism o : this.organisms) {
			sum += o.fitnessOf1s();
		}
		return sum / this.size();
	}

	/**
	 * 
	 * ensures: calculates and returns the average number of 0s in the genetic code
	 * of the organisms of the generation.
	 * 
	 * @return, the average number of 0s in the generation.
	 */
	public int getAvg0s() {
		int sum = 0;
		for (Organism o : this.organisms) {
			sum += o.numOf0s();
		}
		return sum / this.size();
	}

	/**
	 *
	 * ensures: calculates and returns the average number of ?s in the genetic code
	 * of the organisms of the generation.
	 * 
	 * @return, the average number of ?s in the generation.
	 */
	public int getAvgQs() {
		int sum = 0;
		for (Organism o : this.organisms) {
			sum += o.numOfQs();
		}
		return sum / this.size();
	}

	/**
	 * 
	 * ensures: calculates and returns the sum of the fitnesses of the generation
	 * 
	 * @return, the sum of the fitnesses of the generation
	 */
	public int getTotalFitness() {
		int sum = 0;
		for (Organism o : organisms) {
			sum += o.fitness();
		}
		return sum;
	}

	/**
	 *
	 * ensures: gets and returns the number of columns to use to represent the
	 * generation in a 2D grid
	 * 
	 * @return, the number of columns
	 */
	public int getColumnNum() {
		return (int) Math.sqrt(this.size());
	}

	/**
	 * 
	 * ensures: gets and returns the number of rows to use to represent the
	 * generation in a 2D grid
	 * 
	 * @return, the number of rows
	 */
	public int getRowNum() {
		return this.size() / this.getColumnNum();
	}
}
