package mainApp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
//import java.awt.Stroke;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

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

	// changing fields
	// -- variables used for the evolution of the population
	// private ArrayList<Generation> generations = new ArrayList<>();
	// private ArrayList<GenerationHistory> history = new ArrayList();
	
	private EvolutionParameters parameters;
	private GraphicsParameters graphicParam = new GraphicsParameters();
	

	/**
	 * ensures: constructs a population
	 * 
	 * @param mutationRate,         percent chance for mutation for each allele in
	 *                              the chromosome of an organism
	 * @param numOfGens,            the max numsber of generations a population
	 *                              should evolve for
	 * @param genSize,              the amount of organisms in a generation
	 * @param chromosomeLength,     the length of the chromosome, the number of
	 *                              alleles it has, of each organism
	 * @param elitism,              the percent value of the population that'll copy
	 *                              over to the next generation unmutated
	 * @param selectionMethod,      the method used for deciding which organisms of
	 *                              each generation will be used to make the next
	 *                              and how
	 * @param fitnessMethod,        the method used for evaluating whether an
	 *                              organism is considered fit or not, depending on
	 *                              its chromosome
	 * @param crossover,            if true crossover is implemented, every two
	 *                              organisms producing two offspring for the next
	 *                              generation
	 * @param terminationCondition, specifies which fitness level needs to be
	 *                              achieved for the program and evolution of the
	 *                              population to end
	 */
	public Population(EvolutionParameters parameters) {
		this.parameters = parameters;
	}

	/**
	 * ensures: constructs a population based on default values
	 */
	public Population() {
        this(new EvolutionParameters(1, 500, 100, 100, 1, "Truncation", "Num. of 1s", true, 100));
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
			this.parameters.setCurrGen(i, new Organism(parameters.getChromosomeLength(), parameters.getFitnessType(), parameters.getUnsure()));
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
	 * ensures: returns the amount of generations completed if the population is not
	 * terminated. A terminated generation returns the max gens to complete
	 * 
	 * @return
	 */
	public int gensSoFar() {
		if (!this.parameters.getTerminated()) {
			// return generations.size();
			return this.graphicParam.bestFitSize();
		} else {
			return this.parameters.getNumbersOfGen();
		}
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
		this.graphicParam.addBestFitnesses(getBestFitness());
		this.graphicParam.addAvgFitnesses(getAvgFitness());
		this.graphicParam.addLowFitnesses(getWorstFitness());

		if (this.parameters.getSelectionMethod().equals("Learning Chance")) {
			this.graphicParam.addAvgNum1s(getAvg1s());
			this.graphicParam.addAvgNum0s(getAvg0s());
			this.graphicParam.addAvgNumQs(getAvgQs());
			resetConstantFitnesses();
		}
		// gets the last index, that of the generation to evolve from
		// int lastIndex = this.gensSoFar() - 1;

		// gets the generation from which to evolve and sorts
		// Generation lastGen = this.generations.get(lastIndex);
		// lastGen.sortFitness();

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
		String selectionMethod = parameters.getSelectionMethod();
		if (selectionMethod.equals("Truncation")) {
			toMutate = this.getToMutateTruncation(leftover);
		}
		if (selectionMethod.equals("Roulette Wheel")) {
			toMutate = this.selectByChancePercents(leftover);
		}
		if (selectionMethod.equals("Rank")) {
			toMutate = this.selectByRank(leftover);
		}
		if (selectionMethod.equals("Stable State")) {
			toMutate = this.selectByStableState(leftover);
		}
		if (selectionMethod.equals("Rank Roulette")) {
			toMutate = this.selectByRankRoulette(leftover);
		}
		if (selectionMethod.equals("Learning Chance")) {
			resetConstantFitnesses();
			toMutate = this.selectByLearningChances(leftover);
		}

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
		// this.generations.add(new Generation(result, this.selectionMethod,
		// this.fitnessMethod));
		parameters.setCurrentGeneration(result);

		// prints out the generation number and the best fitness of that generation as a
		// means to debug and provide non-graphic representation
		System.out.println(
				"Created gen #" + this.gensSoFar() + " Best fitness: " +  this.graphicParam.getBestFitness(graphicParam.bestFitSize()-1));
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
			sum += parameters.getCurrentGeneration(i).numOfQs();
		}
		return sum / genSize;
	}

	private Integer getAvg0s() {
		int genSize = parameters.getGenSize();
		int sum = 0;
		for (int i = 0; i < genSize; i++) {
			sum += parameters.getCurrentGeneration(i).numOf0s();
		}
		return sum / genSize;
	}

	private Integer getAvg1s() {
		int genSize = parameters.getGenSize();
		int sum = 0;
		for (int i = 0; i < genSize; i++) {
			sum += parameters.getCurrentGeneration(i).fitnessOf1s();
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
			Organism intermediate = new Organism(toMutate[index].getChromosome(), this.parameters.getFitnessType());
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
	 * <br>
	 * ensures: selects from a given array of organisms which will be used to
	 * produce the next generation as in accordance with stable state selection.
	 * <br>
	 * Stable state selection takes the top 10% of the organisms of a generation and
	 * replaces the bottom 10% with them, leaving the rest untouched.
	 * 
	 * @param orgs, the organisms from which to select
	 * @return the selected organisms
	 */
	private Organism[] selectByStableState(Organism[] orgs) {
		// the number of organisms to be selected from the top and replaced from the
		// bottom
		int numToCopy = (orgs.length * STABLE_PERCENT) / 100;

		// creates temporary copy of the orginial organisms to be changed and sorts this
		Organism[] temp = orgs;
		Arrays.sort(temp);

		// finds the organisms to be copied and used from the top to replace the bottom
		Organism[] toCopy = Arrays.copyOfRange(temp, temp.length - numToCopy, temp.length);

		// replaced the bottom with the top organisms
		for (int index = 0; index < numToCopy; index++) {
			temp[index] = new Organism(toCopy[index].getChromosome(), this.parameters.getFitnessType());
		}

		return temp;
	}

	/**
	 * <br>
	 * ensures: selects the organisms to be mutated based on the selection method of
	 * truncation.
	 * <br>
	 * Truncation takes the upper 50% of the organisms to be selected from and
	 * replaces the bottom 50% with them, effectively killing of the least fit half
	 * of the population.
	 * 
	 * @param leftover
	 * @return the organisms selected by Truncation
	 */
	private Organism[] getToMutateTruncation(Organism[] leftover) {
		// takes the length of the array of organisms passed.
		// this being presumably those to be selected from is also of the amount to be
		// mutated eventually.
		int mutateNum = leftover.length;

		// initializes an empty array for the selected organisms to be placed in.
		Organism[] result = new Organism[mutateNum];

		// selects organisms and adds those to the result, evaluating when odd or even
		// lengths and potential indices occur to avoid index out of bounds errors
		for (int i = 0; i < mutateNum; i++) {
			if (mutateNum % 2 == 0) {
				if (i % 2 == 0) {
					result[i] = leftover[mutateNum - (i / 2) - 1];
				} else {
					result[i] = leftover[mutateNum - ((i + 1) / 2) - 1];
				}
			} else {
				if (i % 2 == 0) {
					result[i] = leftover[mutateNum - ((i + 1) / 2) - 1];
				} else {
					result[i] = leftover[mutateNum - (i / 2) - 1];
				}
			}
		}

		return result;

	}

	/**
	 * 
	 * ensures: sums the fitnesses of an array of organisms, finding the total
	 * 
	 * @param orgs, the array of organisms passed, from which the fitnesses will be
	 *              found
	 * @return the sum of the fitnesses of these organisms
	 */
	private int totalFitness(Organism[] orgs) {
		int[] fitnesses = this.getFitnesses(orgs);
		int sum = 0;
		for (int i : fitnesses) {
			sum += i;
		}

		return sum;
	}

	/**
	 * 
	 * ensures: selects from the given array of organisms those of which will be
	 * carried on to the next generation, albeit perhaps mutated and crossedover.
	 * This selection occurs in accordance to the process desribed in the research
	 * paper.
	 * 
	 * @param orgs, the organisms from which the next generation will be selected
	 *              @return, the selected organisms
	 */
	private Organism[] selectByLearningChances(Organism[] orgs) {
		HashMap<Organism, Integer> map = new HashMap<>();

		for (int i = 0; i < orgs.length; i++) {
			map.putIfAbsent(orgs[i], orgs[i].getFitnessAfterDays(this.parameters.getNumbersOfGen()) + 1);
		}

		Organism[] result = new Organism[orgs.length];
		for (int i = 0; i < orgs.length; i++) {
			result[i] = this.selectedByLearningChances(map);
		}

		return result;

	}

	/**
	 * 
	 * ensures: selects one organism from those given to be added to those passed to
	 * the next generation. This selection occurs in accordance to the process
	 * desribed in the research paper to be replicated.
	 * 
	 * @param map, a Hashmap storing organisms and their respective fitnesses, used
	 *             to ensure that each organism is associated with the correct
	 *             fitness
	 *             @return, the organism selected
	 */
	private Organism selectedByLearningChances(HashMap<Organism, Integer> map) {
		int sum = 0;
		Integer[] values = new Integer[map.size()];
		int index = 0;
		for (Organism o : map.keySet()) {
			values[index] = map.get(o);
			index += 1;
		}

		int total = this.totalLearningFitnesses(values);
		Random r = new Random();
		int chance = r.nextInt(total);

		for (Organism o : map.keySet()) {
			int lastSum = sum;
			sum += map.get(o);

			if (chance <= sum && chance >= lastSum) {
				return o;
			}
		}

		return null;

	}

	/**
	 * 
	 * ensures: sums and returns the sum of the given array of integers
	 * 
	 * @param fitnesses, the integers to sum
	 *                   @return, the sum of the inetegers in fitnesses
	 */
	private int totalLearningFitnesses(Integer[] fitnesses) {
		int sum = 0;
		for (int i : fitnesses) {
			sum += i;
		}
		return sum;
	}

	/**
	 * 
	 * ensures: gets and returns the fitnesses of an array of organisms
	 * 
	 * @param orgs, the organisms from which to pull the fitnesses
	 *              @return, the fitnesses of the organisms passed
	 */
	private int[] getFitnesses(Organism[] orgs) {
		Arrays.sort(orgs);
		int[] result = new int[orgs.length];

		for (int i = 0; i < orgs.length; i++) {

			orgs[i].setFitnessMethod(this.parameters.getFitnessMethod());
			result[i] = orgs[i].fitness();
		}

		return result;
	}

	/**
	 * 
	 * ensures: selects the organisms to make up the next generation from a given
	 * array of organisms. This selection occurs in accordance with the roulette
	 * wheel chance in which the probability of an organism being selected is
	 * proportional to their fitness.
	 * 
	 * @param orgs, the organisms from which to select the next generation
	 *              @return, the organisms selected
	 */
	private Organism[] selectByChancePercents(Organism[] orgs) {
		Organism[] result = new Organism[orgs.length];

		for (int index = 0; index < orgs.length; index += 2) {
			Organism selected = selectedByChancePercents(orgs);
			result[index] = selected;
			if (index + 1 != orgs.length) {
				result[index + 1] = selected;
			}
		}

		return result;
	}

	/**
	 * 
	 * ensures: selects an organism from the given organisms based on a probability
	 * proportional to its fitness
	 * 
	 * @param orgs, the organisms from which to select
	 *              @return, the selected organism
	 */
	private Organism selectedByChancePercents(Organism[] orgs) {
		int[] fitnesses = this.getFitnesses(orgs);
		Random r = new Random();
		int total = this.totalFitness(orgs);
		int chance = r.nextInt(total);

		int sum = 0;
		for (int index = 0; index < orgs.length; index++) {
			int lastSum = sum;
			sum += fitnesses[index];
			if (chance >= lastSum && chance <= sum) {
				return new Organism(orgs[index].getChromosome(), this.parameters.getFitnessType());
			}
		}
		return null;
	}

	/**
	 * 
	 * ensures: ranks the organisms of a given array of organisms, returning these
	 * ranks in an array of ints, sorted from least to most. The worst rank is 1.
	 * 
	 * @param orgs, the organisms to rank
	 *              @return, the ranks of the organisms from least to greatest
	 */
	private int[] getRanks(Organism[] orgs) {
		int[] result = new int[orgs.length];

		int cur = 1;
		for (int index = 0; index < orgs.length; index++) {
			result[index] = cur;
			cur += 1;
		}

		return result;
	}

	/**
	 * 
	 * ensures: takes the organisms to rank, uses a helper function to get these
	 * ranks and totals them, returning the total
	 * 
	 * @param orgs, the organisms to rank and find the total ranks of
	 *              @return, the total of the ranks
	 */
	private int totalRank(Organism[] orgs) {
		int[] ranks = this.getRanks(orgs);

		int sum = 0;
		for (int i : ranks) {
			sum += i;
		}
		return sum;
	}

	/**
	 * 
	 * ensures: selects which organisms to pass on to the next generation based on
	 * their rank as according to the selection method named Rank.
	 * 
	 * @param orgs, the organisms from which to select the next generation
	 *              @return, the selected organisms
	 */
	private Organism[] selectByRank(Organism[] orgs) {
		Organism[] result = new Organism[orgs.length];
		Organism[] temp = orgs;
		Arrays.sort(temp);
		int[] ranks = this.getRanks(orgs);
		int total = this.totalRank(orgs);
		int fill = 0;
		int index = 0;

		double multiplier = (double) total / (double) 4 * ranks[0]; // example: 10 organisms, each fitness. 1 fitness
																	// 100. 1 fitness 1. rest fitness 50. Mult =
																	// 100+400+0=500/4*100 = 500/4 = 125?

		while (fill < orgs.length) {
			int rank = ranks[orgs.length - 1 - index]; // The 100?
			int numToRun = (int) (rank * multiplier); // 100 * 125 = 12,500 -- would completely fill with the fitness
														// 100.
			numToRun /= total;
			numToRun /= (index + 1);
			if (numToRun == 0) { // 1
				numToRun += 1;
			}
			for (int i = 0; i < numToRun; i++) {
				if (fill == orgs.length) {
					break;
				} else {
					result[fill] = temp[orgs.length - 1 - index]; // add 0...,
					fill += 1;
				}
			}
			index += 1;

		}

		return result;

	}

	/**
	 * 
	 * ensures: selects organisms for the next generation based on a probability
	 * proportional to their rank as in accordance to the selection method of Rank
	 * Roulette.
	 * 
	 * @param orgs, the organisms from which the next generation is selected
	 *              @return, the selected organisms
	 */
	private Organism[] selectByRankRoulette(Organism[] orgs) {
		Organism[] result = new Organism[orgs.length];

		for (int index = 0; index < orgs.length; index += 2) {
			Organism selected = this.selectedByRank(orgs);
			result[index] = selected;
			if (index + 1 != orgs.length) {
				result[index + 1] = selected;
			}
		}

		return result;

	}

	/**
	 * 
	 * ensures: selects an organism from the passed array of organisms as in
	 * accordance to the Rank Roulette method of selection, which means the
	 * probability of selection is proportional to the rank of an organism.
	 * 
	 * @param orgs, the organisms from which to select one
	 *              @return, the selected organism
	 */
	private Organism selectedByRank(Organism[] orgs) {
		Arrays.sort(orgs);
		int total = this.totalRank(orgs);

		int[] ranks = this.getRanks(orgs);

		Random r = new Random();
		int chance = r.nextInt(total);

		int sum = 0;
		for (int index = 0; index < orgs.length; index++) {
			int lastSum = sum;
			sum += ranks[index];
			if (chance >= lastSum && chance <= sum) {
				return new Organism(orgs[index].getChromosome(), this.parameters.getFitnessType());
			}
		}

		return null;

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
		// this.newGen();
		// this.sortFitness(0);

		for (int count = 0; count < this.parameters.getNumbersOfGen(); count++) {
			this.nextGeneration();
			// this.sortFitness(this.gensSoFar() - 1);
			sortCurr();
		}

	}

	/**
	 * TODO inner comments
	 * ensures: draws the population at a point and time, the line of best fitness,
	 * that of average fitness, that of worst fitness and, if the research results
	 * are attempting to be replicated, the average number of 1s, 0s, and ?s as they
	 * change in the population over time.
	 * 
	 * @param g, the 2D graphics on which to draw these lines
	 */
	public void drawOn(Graphics2D g) {

		int numOfGens = parameters.getNumbersOfGen();
		int scale = 1000 / numOfGens;

		g.setColor(Color.BLACK);

		for (int i = 0; i <= 10; i++) {
			g.drawLine(50 + i * scale * numOfGens / 10, 350 - 5, 50 + i * scale * numOfGens / 10, 350 + 5);
			g.drawString("" + i * numOfGens / 10, 50 + (i * scale * numOfGens / 10) - 10, 350 + 20);
			g.drawLine(50 - 5, 350 - i * 30, 50 + 5, 350 - i * 30);
			g.drawString("" + i * 10, 50 - 25, 350 - (i * 30) + 5);
		}

		g.setStroke(new BasicStroke(2));

		g.drawLine(50, 350, 50 + 1000, 350);
		g.drawLine(50, 50, 50, 350);

		g.setColor(Color.GRAY);
		g.setStroke(new BasicStroke(1));
		if (gensSoFar() > 2) {
			g.drawLine(50, 350 - graphicParam.getBestFitness(gensSoFar() - 2) * 3, 50 + (gensSoFar() - 2) * scale,
					350 - graphicParam.getBestFitness(gensSoFar() - 2) * 3);
		}

		g.setStroke(new BasicStroke(2));
		for (int i = 0; i < gensSoFar() - 2; i++) {
			g.setColor(Color.GREEN);
			g.drawLine(50 + i * scale, (350 - graphicParam.getBestFitness(i) * 3), 50 + (i + 1) * scale,
					(350 - graphicParam.getBestFitness(i + 1) * 3));
			g.setColor(Color.ORANGE);
			g.drawLine(50 + i * scale, 350 - graphicParam.getAvgFitness(i) * 3, 50 + (i + 1) * scale,
					350 - graphicParam.getAvgFitness(i + 1) * 3);
			g.setColor(Color.RED);
			g.drawLine(50 + i * scale, 350 - graphicParam.getLowFitness(i) * 3, 50 + (i + 1) * scale,
					350 - graphicParam.getLowFitness(i + 1) * 3);
			g.setColor(Color.MAGENTA);
			g.drawLine(50 + i * scale, 350 - (graphicParam.getBestFitness(i) - graphicParam.getLowFitness(i)), 50 + (i + 1) * scale,
					350 - ((graphicParam.getBestFitness(i + 1) - graphicParam.getLowFitness(i + 1))));
			g.setColor(Color.BLACK);
			g.drawLine(50, 50 - graphicParam.getBestFitness(i) * 3, 50 + (i + 1) * scale, 50 - graphicParam.getBestFitness(i) * 3);
			if (this.parameters.getSelectionMethod().equals("Learning Chance")) {
				g.setColor(Color.BLUE);
				g.drawLine(50 + i * scale, 350 - graphicParam.getAvgNum1s(i) * 3, 50 + (i + 1) * scale,
						350 - graphicParam.getAvgNum1s(i + 1) * 3);
				g.setColor(Color.CYAN);
				g.drawLine(50 + i * scale, 350 - graphicParam.getAvgNum0s(i) * 3, 50 + (i + 1) * scale,
						350 - graphicParam.getAvgNum0s(i + 1) * 3);
				g.setColor(Color.LIGHT_GRAY);
				g.drawLine(50 + i * scale, 350 - graphicParam.getAvgNumQs(i) * 3, 50 + (i + 1) * scale,
						350 - graphicParam.getAvgNumQs(i + 1) * 3);

			}
		}
	}

	/**
	 * 
	 * ensures: sets the mutation rate of the population, modeled as a percent, to
	 * the given variable
	 * 
	 * @param r, the rate, as a percent, to set the mutation rate to
	 */
	public void setMutationRate(int r) {
		this.parameters.setMutationRate(r);
	}

	/**
	 * 
	 * ensures: gets and returns the mutation rate, modeled as a percent
	 * 
	 * @return
	 */
	public int getMutationRate() {
		return this.parameters.getMutationRate();
	}

	/**
	 * 
	 * ensures: sets the generation size to some given integer, s
	 * 
	 * @param s, the integer to set the generation size to
	 */
	public void setGenSize(int s) {
		this.parameters.setGenSize(s);
	}

	/**
	 * 
	 * ensures: gets and returns the generation size, an integer
	 * 
	 * @return the generation size
	 */
	public int getGenSize() {
		return this.parameters.getGenSize();
	}

	/**
	 * 
	 * ensures: sets the number of generations to run to the given integer, g
	 * 
	 * @param g, the integer to set the number of generations to run to
	 */
	public void setNumGens(int g) {
		this.parameters.setNumGens(g);
	}

	/**
	 * 
	 * ensures: gets and retruns the number of generations to run
	 * 
	 * @return the number of generations to run
	 */
	public int getNumGens() {
		return this.parameters.getNumbersOfGen();
	}

	/**
	 * 
	 * ensures: sets the chromosome length, the number of alleles each organism is
	 * supposed to have, to the given integer l
	 * 
	 * @param l, the integer to set the chromosome length to
	 */
	public void setGenomeLength(int l) {
		this.parameters.setGenomeLength(l);
	}

	/**
	 * 
	 * ensures: gets and returns the chromosome length, the number of alleles each
	 * organis is supposed to have
	 * 
	 * @return the current chromosome length
	 */
	public int getGenomeLength() {
		return this.parameters.getChromosomeLength();
	}

	/**
	 * 
	 * ensures: sets the elitism percent, the percent of organisms to be copied over
	 * to the next generation unchanged
	 * 
	 * @param r, the percent given as an integer
	 */
	public void setElitism(int r) {
		this.parameters.setElitism(r);
	}

	/**
	 * 
	 * ensures: gets and returns the elitism percent, the percent of organisms to be
	 * copied over to the next generation unchanged
	 * 
	 * @return the elitism percent of the population
	 */
	public int getElitism() {
		return this.parameters.getElitismPercent();
	}

	/**
	 * 
	 * ensures: sets the termination condition, the fitness value at which the user
	 * wishes the program to terminate and the population to stop evolving
	 * 
	 * @param t, the integer value to set the termination condition to
	 */
	public void setTermination(int t) {
		this.parameters.setTermination(t);
	}

	/**
	 * 
	 * ensures: gets and returns the current termination condition, the fitness
	 * value at which the user wishes the program to terminate
	 * 
	 * @return, the current termination condition
	 */
	public int getTermination() {
		return this.parameters.getTermination();
	}

	/**
	 * 
	 * ensures: sets the selection method, the process used to decide which
	 * organisms are carried on to the next generation, alebeit possibly mutated and
	 * crossover over, and by what amount they will be carried over
	 * 
	 * @param m, the name of the selection method to use
	 */
	public void setSelection(String m) {
		this.parameters.setSelection(m);
	}

	/**
	 * 
	 * ensures: gets the selection method, the process used to select from one
	 * generation the organisms used to create the next
	 * 
	 * @return, the selection method in use
	 */
	public String getSelction() {
		return this.parameters.getSelectionMethod();
	}

	/**
	 * 
	 * ensures: sets the crossover, true if crossover is applied, false if it is
	 * not, in accordance to the value of the given c
	 * 
	 * @param c, the true or false boolean value to set the crossover to
	 */
	public void setCrossover(boolean c) {
		this.parameters.setCrossOver(c);
	}

	/**
	 * 
	 * ensures: gets and returns whether of not crossover is implemented and being
	 * used
	 * 
	 * @return, true if crossover is applied, false if not
	 */
	public boolean getCrossover() {
		return this.parameters.getCrossover();
	}

	/**
	 * 
	 * ensures: sets the fitness method, the process which extracts a fitness value,
	 * a phenotype, from the genetic code or genotype of an organism
	 * 
	 * @param fitnessMethod, the name of the method to be used
	 */
	public void setFitnessMethod(String fitnessMethod) {
		this.parameters.setFitnessMethod(fitnessMethod);
	}

	/**
	 * 
	 * ensures: gets and returns the fittest organism in the last generation
	 * 
	 * @return, the fittest organism of the last generation
	 */
	public Organism getFittest() {
		// return generations.get(gensSoFar() - 1).getFittest();
		sortCurr();
		return parameters.getCurrentGeneration(parameters.getGenSize() - 1);
	}

	/**
	 * 
	 * ensures: sets the boolean value, terminated, to true. Terminated helps
	 * determine when the program should stop the evolution of a population
	 * 
	 */
	public void terminate() {
		this.parameters.setTerminate(true);
	}

	/**
	 * 
	 * ensures: gets and returns the lastest generation to be made
	 * 
	 * @return, the latest generation
	 */
	public Generation getLatestGen() {
		return new Generation(parameters.getCurrentGeneration(), parameters.getSelectionMethod(), parameters.getFitnessMethod());
		// return this.generations.get(generations.size() - 1);
	}
}
