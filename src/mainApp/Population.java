package mainApp;

//import java.awt.Stroke;
import java.util.Arrays;

/**
 * 
 * Class: Population
 * 
 * @author R_003: Allyn Loyd and Natasa Zupanski
 *         <br>
 *         Purpose: to simulate an evolving population of organisms, a graphic
 *         representation of the change from generation to generation expressed
 *         through a few statistics. The population is flexible, taking a
 *         variety of methods for selecting the next generation as well as for
 *         computing the fitness of an organism.
 *         <br>
 *         Restrictions: only some selection and fitness methods work with the
 *         current code.
 *
 */
public class Population {
	// constant value fields
	final private int STABLE_PERCENT = 10;
	private EvolutionParameters parameters;
	private PopulationVisualization populationVisualization;

	// changing fields
	// -- variables used for the evolution of the population
	// private ArrayList<Generation> generations = new ArrayList<>();
	// private ArrayList<GenerationHistory> history = new ArrayList();

	/**
	 * ensures: constructs a population
	 * 
	 * @parameters contains information about the population
	 */
	public Population(EvolutionParameters parameters) {
		this.parameters = parameters;
		parameters.reset();
		this.populationVisualization = new PopulationVisualization(parameters);
	}

	/**
	 * ensures: constructs a population based on default values
	 */
	public Population() {
		this(new EvolutionParameters(1, 500, 100, 100, 1, SelectionType.TRUNCATION, FitnessType.TARGETORG, false, 100,
				"1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110"));
	}

	/**
	 * ensures: tests the default population by creating a testing population and
	 * running the evolution
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Population testPop = new Population();
		testPop.runPopulationEvol();
	}

	/**
	 * ensures: starts the first generation of the population
	 */
	// public void newGen() {
	// this.generations.add(new Generation(genSize, chromosomeLength,
	// selectionMethod, fitnessType));
	// }

	public void spawnFirstGeneration() {
		for (int i = 0; i < parameters.getGenSize(); i++) {
			this.parameters.setCurrGen(i, new Organism(parameters.getChromosomeLength(), parameters.getFitnessType(),
					parameters.getUnsure(), parameters.getRandomType(), this.parameters.getTargetOrganism()));
		}
	}

	/**
	 * 
	 * ensures: sorts the generation and organisms therein at an index by their
	 * fitness
	 * 
	 * @param index, the index in the generations of the population at which to sort
	 */
	// private void sortFitness(int index) {
	// this.generations.get(index).sortFitness();
	// }

	private void sortCurr() {
		Arrays.sort(parameters.getCurrentGeneration());
	}

	/**
	 * 
	 * ensures: adds to generations the next generation as according to the
	 * selection method
	 * 
	 */
	public void nextGeneration() {

		// adds the best, worst, and average fitnesses of this generation to be used in
		// creating the GUI

		this.populationVisualization.populateData(getBestFitness(), getAvgFitness(), getWorstFitness(), getAvg1s(),
				getAvg0s(), getAvgQs());
		if (this.parameters.getSelectionType() == SelectionType.LEARNINGCHANCE) {
			resetConstantFitnesses();
		}

		// gets the number of organisms that will be carried over as accoring to elitism
		// get the number of organisms that will be mutated eventually
		int genSize = this.parameters.getGenSize();
		int elitismNum = (parameters.getElitismPercent() * genSize) / 100;
		int mutateNum = genSize - elitismNum;

		// gets the organisms of the last generation and sorts them
		Organism[] orgs = Arrays.copyOfRange(parameters.getCurrentGeneration(), 0, genSize);
		Arrays.sort(orgs);

		// initializes the variable to returned, the next generation, though currently
		// empty
		Organism[] result = new Organism[genSize];

		// takes the organisms deigned by elitism to be carried over to the next
		// generation, unmutated
		Organism[] toKeep = Arrays.copyOfRange(orgs, mutateNum, genSize);

		// takes the remaining organisms, those not selected by elitistm, those that
		// will be selected from and mutated
		Organism[] leftover = Arrays.copyOfRange(orgs, 0, mutateNum);

		// initializes those to be mutates as those to be selected from
		Organism[] toMutate = leftover;

		// selects from the leftover organisms to decide which to mutate, all according
		// to the selection method
		SelectionType type = parameters.getSelectionType();
		SelectionStrategy strategy = SelectionStrategyFactory.getSelectionStrategyOfType(type, STABLE_PERCENT,
				parameters.getNumbersOfGen(), parameters.getRandomType(), this.parameters.getTargetOrganism());
		toMutate = strategy.selectFrom(leftover);

		// applies crossover if crossover is turned on
		if (parameters.getCrossover()) {
			toMutate = this.applyCrossover(toMutate);
		}

		// mutate those that are not elite after selection
		Organism[] toAdd = this.mutate(toMutate);

		// Build final new generation
		// add those selected by elitism
		for (int i = 0; i < toKeep.length; i++) {
			result[i] = toKeep[i];
		}
		// add those previously mutated
		for (int i = toKeep.length; i < genSize; i++) {
			result[i] = toAdd[(i - toKeep.length)];
		}

		// adds the new generation to the generations
		parameters.setCurrentGeneration(result);

		// prints out the generation number and the best fitness of that generation as a
		// means to debug and provide non-graphic representation
		this.populationVisualization.printBestFitness();
	}

	private void resetConstantFitnesses() {
		for (Organism o : parameters.getCurrentGeneration()) {
			o.resetConstantFitness();
		}
	}

	private Integer getAvgQs() {
		int genSize = parameters.getGenSize();
		int sum = 0;
		for (int i = 0; i < genSize; i++) {
			sum += parameters.getCurrentGeneration(i).getOrganismUtilites().numOfQs();
		}
		return sum / genSize;
	}

	private Integer getAvg0s() {
		int genSize = parameters.getGenSize();
		int sum = 0;
		for (int i = 0; i < genSize; i++) {
			sum += parameters.getCurrentGeneration(i).getOrganismUtilites().numOf0s();
		}
		return sum / genSize;
	}

	private Integer getAvg1s() {
		int genSize = parameters.getGenSize();
		int sum = 0;
		for (int i = 0; i < genSize; i++) {
			sum += parameters.getCurrentGeneration(i).getOrganismUtilites().numOf1s();
		}
		return sum / genSize;
	}

	private Integer getWorstFitness() {
		sortCurr();
		return parameters.getCurrentGeneration(0).fitness();
	}

	private Integer getAvgFitness() {
		int genSize = parameters.getGenSize();
		int sum = 0;
		for (int i = 0; i < genSize; i++) {
			sum += parameters.getCurrentGeneration(i).fitness();
		}
		return sum / genSize;
	}

	private Integer getBestFitness() {
		sortCurr();
		return parameters.getCurrentGeneration(parameters.getGenSize() - 1).fitness();
	}

	/**
	 * 
	 * ensures: mutates the chromosomes of any given array of organisms, with a
	 * chance determined by the mutation rate of the population, each allele having
	 * that chance to flip
	 * 
	 * @param toMutate, the organisms whose chromosomes should be mutated
	 * @return the new, mutated organisms
	 */
	private Organism[] mutate(Organism[] toMutate) {
		// initializes an empty array to be filled with the mutated organisms
		Organism[] result = new Organism[toMutate.length];

		for (int index = 0; index < toMutate.length; index++) {
			// creates a new organism from the one at the current index
			Organism intermediate = new Organism(toMutate[index].getChromosome(), this.parameters.getFitnessType(),
					parameters.getRandomType(), this.parameters.getTargetOrganism());
			// mutates that new organism
			intermediate.mutate(this.parameters.getMutationRate());
			// adds it to the result
			result[index] = intermediate;
		}

		return result;

	}

	/**
	 * ensures: applies crossover to the organisms passed
	 * 
	 * @param toCrossover, the organisms to be crossed, to which crossover should
	 *                     apply
	 * @return the new, crossed over organisms
	 */
	private Organism[] applyCrossover(Organism[] toCrossover) {
		if (toCrossover.length == 0) {
			return new Organism[0];
		}

		if (toCrossover.length == 2) {
			return new Organism[] { toCrossover[0].newCrossover(toCrossover[1]),
					toCrossover[1].newCrossover(toCrossover[0]) };
		}
		if (toCrossover.length == 1) {
			return new Organism[] { toCrossover[0] };
		}

		Organism[] result = new Organism[toCrossover.length];
		Organism o1 = toCrossover[0];
		Organism o2 = toCrossover[toCrossover.length - 1];

		Organism first = o1.newCrossover(o2);
		Organism second = o2.newCrossover(o1);

		Organism[] toPass = Arrays.copyOfRange(toCrossover, 1, toCrossover.length - 1);

		Organism[] toAdd = applyCrossover(toPass);

		result[0] = first;
		result[1] = second;

		for (int index = 0; index < toCrossover.length - 2; index++) {
			result[2 + index] = toAdd[index];
		}

		return result;
	}

	/**
	 * 
	 * ensures: creates the first generation of the population, sorting it and
	 * running a loop to evolve the generation until it reaches the numer of
	 * generations it means to complete
	 * 
	 */
	public void runPopulationEvol() {
		spawnFirstGeneration();
		sortCurr();
		for (int count = 0; count < this.parameters.getNumbersOfGen(); count++) {
			this.nextGeneration();
			sortCurr();
		}

	}

	public EvolutionParameters getEvolutionParameters() {
		return this.parameters;
	}

	public PopulationVisualization getPopulationVisualization() {
		return this.populationVisualization;
	}

	/**
	 * 
	 * ensures: gets and returns the fittest organism in the last generation
	 * 
	 * @return, the fittest organism of the last generation
	 */
	public Organism getFittest() {
		sortCurr();
		return parameters.getCurrentGeneration(parameters.getGenSize() - 1);
	}
}