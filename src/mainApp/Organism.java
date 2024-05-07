package mainApp;

/**
 * Class: Organism
 * 
 * @author R_003: Allyn Loyd and Natasa Zupanski <br>
 *         Purpose: to simulate the idea of an Organism, having genetic code, a
 *         graphic representation, a method that determines its fitness, methods
 *         that determine this fitness and change its genetic code, and methods
 *         to perform crossover. <br>
 *         Restrictions: Takes genetic code of 0s, 1s, and occasionally ?s.
 *         Other characters in the genetic code will not currently be supported
 *         for any functions of the overall simulator.
 */
public class Organism implements Comparable<Organism> {
	// mutable/changing fields
	private int constantFitness = -1;
	private String chromosome;
	private FitnessType fitnessType;
	private FitnessStrategy fitness = null;
	private int numGens = 0;
	private OrganismVisualization organismVisualization;
	private OrganismUtilites organismUtilites;
	private String targetOrganism;

	private RandomInterface r = new WrappedRandom();

	// constant fields
	private final int HEIGHT = 300;

	/**
	 * ensures: constructs an organism given the length of its chromosome, the
	 * number of alleles each organism has in its genetic code, the fitness method,
	 * the name of the process that determines how fit an organism is based on its
	 * genetic code, and a boolean that determines whether or not there should be
	 * any ?s in the genetc code of the organism. This also overrides the fitmess
	 * method. The genetic code is randomly generated.
	 * 
	 * @param length,        the number of alleles in the genetic code of the
	 *                       organism
	 * @param fitnessMethod, the method that determines the fitness of an organism
	 *                       from its genetic code
	 * @param someUnsure,    if true, there may be ?s in the genetic code of the
	 *                       organism. If false, there will only be 0s and 1s in the
	 *                       genetic code.
	 */
	public Organism(int length, FitnessType type, boolean someUnsure, RandomType random, String targetOrganism) {
		this(length, type, random, targetOrganism);
		if (someUnsure) {
			char[] randomChromosome = new char[length];
			for (int index = 0; index < length; index++) {
				int chance = r.nextInt(4);
				if (chance == 1) {
					randomChromosome[index] = '1';
				}
				if (chance == 0) {
					randomChromosome[index] = '0';
				}
				if (chance == 2 || chance == 3) {
					randomChromosome[index] = '?';
				}
			}
			this.chromosome = String.valueOf(randomChromosome);
			this.fitnessType = type;
			this.organismVisualization = new OrganismVisualization(chromosome);
			this.organismUtilites = new OrganismUtilites(chromosome);
		}
	}

	/**
	 * ensures: constructs an organism from the given genetic code as a String, with
	 * each character being an allele, and the given fitness method.
	 * 
	 * @param chromosome,    the genetic code of the organism
	 * @param fitnessMethod, the name of the process used to determine how fit an
	 *                       organism is based on their genetic code
	 */
	public Organism(String chromosome, FitnessType type, RandomType randomType, String targetOrganism, int numGens,
			int constantFitness) {
		this.chromosome = chromosome;
		this.fitnessType = type;
		this.fitness = FitnessStrategyFactory.getFitnessStrategyOfType(randomType, type, numGens, constantFitness,
				targetOrganism);
		this.r = RandomFactory.getRandomOfType(randomType);
		this.organismVisualization = new OrganismVisualization(chromosome);
		this.organismUtilites = new OrganismUtilites(chromosome);
		this.targetOrganism = targetOrganism;
		this.numGens = numGens;
		this.constantFitness = constantFitness;
	}

	/**
	 * ensures: constructs an organism from the length of its chromosome, the number
	 * of alleles in its randomly decided genetic code, and its fitness method, the
	 * process which decides how fit it is based on its genetic code.
	 * 
	 * @param length,        the number of alleles in the genetic code
	 * @param fitnessMethod, the process which decides how fit an organism is
	 */
	public Organism(int length, FitnessType type, RandomType randomType, String targetOrganism) {
		char[] randomChromosome = new char[length];
		r = RandomFactory.getRandomOfType(randomType);
		for (int i = 0; i < length; i++) {
			int chance = r.nextInt(2);
			if (chance == 1) {
				randomChromosome[i] = '0';
			} else {
				randomChromosome[i] = '1';
			}
		}
		this.fitnessType = type;
		this.chromosome = String.valueOf(randomChromosome);
		this.organismVisualization = new OrganismVisualization(chromosome);
		this.organismUtilites = new OrganismUtilites(chromosome);
		this.targetOrganism = targetOrganism;
	}

	public Organism(Organism organism) {
		this(organism.getChromosome(), organism.getFitnessType(), organism.getRandomType(),
				organism.getTargetOrganism(), organism.getNumGens(), organism.fitness());
	}

	public int getNumGens() {
		return numGens;
	}

	private String getTargetOrganism() {
		return this.targetOrganism;
	}

	/**
	 * ensures: gets and returns the length of the chromosome of the organism, the
	 * number of alleles in its genetic code
	 * 
	 * @return, the length of the chromosme
	 */
	public int length() {
		return organismVisualization.length();
	}

	/**
	 * ensures: flips the value of an allele at a specific index, from 0 to 1 or 1
	 * to 0
	 * 
	 * @param index, the index at which to flip the allele
	 */
	private void flipAllele(int index) {
		char c = chromosome.toCharArray()[index];
		char[] result = chromosome.toCharArray();
		if (c == '0') {
			c = '1';
		} else if (c == '1') {
			c = '0';
		}
		result[index] = c;
		this.chromosome = String.valueOf(result);
		this.organismVisualization.setChromosome(chromosome);
		this.organismUtilites.setChromosome(chromosome);
	}

	/**
	 * ensures: mutates the chromosome. This means there is a chance for each allele
	 * in the genetic code to flip. This chance is the given rate which is taken to
	 * be a percent.
	 * 
	 * @param rate, the chance as a percent of each allele to flip
	 */
	public void mutatePercent(int rate) {
		char[] arr = chromosome.toCharArray();
		for (int index = 0; index < this.length(); index++) {
			int chance = r.nextInt(100);
			if (chance < rate) {
				if (arr[index] == '0') {
					arr[index] = '1';
				} else if (arr[index] == '1') {
					arr[index] = '0';
				}
			}
		}
		this.chromosome = String.valueOf(arr);
		this.organismVisualization.setChromosome(chromosome);
		this.organismUtilites.setChromosome(chromosome);
	}

	/**
	 * ensures: mutates the organism, having a chance to flip each allele of the
	 * organism. On average, this method flips a number of alleles equal to the
	 * given rate.
	 * 
	 * @param rate, the number of alleles that flip on average during mutation, the
	 *              given rate of mutation
	 */
	public void mutate(int rate) {
		// Random r = new Random();
		for (int i = 0; i < this.length(); i++) {
			int chance = r.nextInt(this.length());
			if (chance < rate) {
				flipAllele(i);
			}
		}
	}

	/**
	 * ensures: gets and returns the chromosme of the organism, its genetic code
	 * 
	 * @return, the chromosome
	 */
	public String getChromosome() {
		return this.chromosome;
	}

	/**
	 * ensures: flips an allele at a given x,y coordinate, calculating first which
	 * row and column this allele is in and, from this, getting the index of that
	 * allele. The allele is then flipped at that index.
	 * 
	 * @param x, the x coordinate of the allele to flip
	 * @param y, the y coordinate of the allele to flip
	 */
	public void flipAlleleCoord(int x, int y, int height, int widths) {
		if (height < 0) {
			height = this.HEIGHT;
		}
		int rows = (int) Math.sqrt(this.length());
		int boxSide = height / rows;
		int cols = this.length() / rows;
		int width = widths / cols;
		if (y < height) {

			int row = y / boxSide;
			int col = x / width;

			int index = this.organismVisualization.rowColToIndex(row, col);
			this.flipAllele(index);
		}
	}

	/**
	 * ensures: calculates and returns the fitness of an organism based on the
	 * fitness method in use
	 * 
	 * @return, the fitness of the organism
	 */
	public int fitness() {
		fitness = FitnessStrategyFactory.getFitnessStrategyOfType(r.getType(), fitnessType, numGens, constantFitness,
				targetOrganism);
		int num = fitness.getFitness(chromosome);
		this.constantFitness = num;
		return num;
	}

	/**
	 * ensures: sets the constant fitness of the organism to -1, effectively
	 * resetting it so it can be determined again.
	 * 
	 */
	public void resetConstantFitness() {
		this.constantFitness = -1;
	}

	/**
	 * 
	 * ensures: creates and returns a new organism that is the crossover of the
	 * genetic code of this organism and that of another, given organism. The point
	 * at which this returned organisms code stops being this organisms code and is
	 * the other organisms code is randomly determined. In other words, the
	 * crossover point is randomly determined.
	 * 
	 * @param other, the organism with which this organism will create the new
	 *               organisms genetic code
	 *               @return, the new organism, its genetic code a crossover between
	 *               this organism and other, the other organism
	 */
	public Organism newCrossover(Organism other) {
		if (other.length() == this.length()) {

			// Random r = new Random();
			int crossoverPoint = r.nextInt(this.length());
			int whichFirst = r.nextInt(2);

			String first = "";
			String second = "";

			if (whichFirst == 0) {
				first = this.chromosome;
				second = other.getChromosome();
			} else {
				first = other.getChromosome();
				second = this.chromosome;
			}

			String result = "";
			result += first.substring(0, crossoverPoint);
			result += second.substring(crossoverPoint);

			return new Organism(result, this.fitness.getFitnessType(), r.getType(), targetOrganism, numGens, -1);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * ensures: compares this organism to another organism, arg0, based on their
	 * fitness. Determines which organism has the greater and lesser fitness or if
	 * both have the same fitness.
	 * 
	 * @param arg0, the other organism to which this organism is compared
	 *              @return, positive if this organisms fitness is greater, negative
	 *              if this organisms fitness os lesser, and zero if both organisms
	 *              have the same fitness.
	 */
	@Override
	public int compareTo(Organism arg0) {
		Organism toCompare = (Organism) arg0;
		int to = 0;
		int score = 0;
		to = toCompare.fitness();
		score = this.fitness();
		if (score > to) {
			// return positive if greater
			return 1;
		}
		if (score == to) {
			return 0; // if equal
		}
		if (score < to) {
			return -1; // negative number if less than
		}

		return 0;
	}

	/**
	 *
	 * ensures: sets the fitness method of the organisms which is used to decide how
	 * to determine the fitness of the organism from its genetic code
	 * 
	 * @param method, the name of the fitness method to set
	 */
	public void setFitnessMethod(FitnessType method) {
		this.fitnessType = method;
	}

	public FitnessType getFitnessType() {
		return this.fitness.getFitnessType();
	}

	public void setNumGens(int numGens) {
		this.numGens = numGens;
	}

	public RandomType getRandomType() {
		return r.getType();
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
		this.organismVisualization.setChromosome(chromosome);
		this.organismUtilites.setChromosome(chromosome);
	}

	public OrganismVisualization getOrganismVisualization() {
		return this.organismVisualization;
	}

	public OrganismUtilites getOrganismUtilites() {
		return this.organismUtilites;
	}
}