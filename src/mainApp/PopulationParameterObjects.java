package mainApp;

import java.util.ArrayList;

class EvolutionParameters {
    private int mutationRate;
    private int numOfGens;
    private int genSize;
    private int chromosomeLength;
    private int elitismPercent;
    private int terminationCondition;
    private String selectionMethod;
    private String fitnessMethod;
    private boolean crossover;
	private SelectionType selectionType;
	private boolean isUnsure;
	private FitnessType fitnessType;
	private Organism[] currentGeneration;
    private boolean termination;

	public EvolutionParameters(int mutationRate, int numOfGens, int genSize, int chromosomeLength, int elitism,
	String selectionMethod, String fitnessMethod, boolean crossover, int terminationCondition) {
        this.mutationRate = mutationRate;
		this.numOfGens = numOfGens;
		this.genSize = genSize;
		this.chromosomeLength = chromosomeLength;
		this.elitismPercent = elitism;
		this.selectionMethod = selectionMethod;
		this.fitnessMethod = fitnessMethod;
		this.crossover = crossover;
		this.termination = false;
		this.terminationCondition = terminationCondition;
		this.selectionType = SelectionStrategyFactory.getSelectionTypeFromString(selectionMethod);
		if (selectionType == SelectionType.LEARNINGCHANCE) {
			isUnsure = true;
		}
		this.fitnessType = FitnessStrategyFactory.getTypeFromString(fitnessMethod);
		this.currentGeneration = new Organism[genSize];
    }

    public int getGenSize() {
        return this.genSize;
    }

    public int getChromosomeLength() {
       return this.chromosomeLength;
    }

    public String getSelectionMethod() {
       return this.selectionMethod;
    }

    public int getNumbersOfGen() {
        return this.numOfGens;
    }

    public boolean getUnsure() {
        return isUnsure;
    }

    public FitnessType getFitnessType() {
        return fitnessType;
    }

    public int getElitismPercent() {
        return elitismPercent;
    }

    public boolean getCrossover() {
        return crossover;
    }

    public int getMutationRate() {
        return mutationRate;
    }

    public String getFitnessMethod() {
        return fitnessMethod;
    }

    public void setMutationRate(int r) {
        mutationRate = r;
    }

    public void setGenSize(int s) {
        genSize = s;
    }

    public void setNumGens(int g) {
        chromosomeLength = g;
    }

    public void setGenomeLength(int l) {
       this.chromosomeLength = l;
    }

    public void setElitism(int r) {
        this.elitismPercent = r;
    }

    public void setCrossOver(boolean c) {
        this.crossover = c;
    }

    public void setFitnessMethod(String fitnessMethod2) {
        this.fitnessMethod = fitnessMethod2;
    }

    public void setTermination(int t) {
        this.terminationCondition = t;
    }

    public int getTermination() {
        return terminationCondition;
    }

    public void setSelection(String m) {
        this.selectionMethod = m;
    }

    public void setCurrGen(int i, Organism organism) {
        this.currentGeneration[i] = organism;
    }

    public Organism[] getCurrentGeneration() {
        return this.currentGeneration;
    }

    public boolean getTerminated() {
       return this.termination;
    }

    public void setCurrentGeneration(Organism[] result) {
        this.currentGeneration = result;
    }

    public Organism getCurrentGeneration(int i) {
        return this.currentGeneration[i];
    }

    public void setTerminate(boolean b) {
        this.termination = true;
    }
}

class GraphicsParameters {
    private ArrayList<Integer> bestFitnesses = new ArrayList<>();
    private ArrayList<Integer> avgFitnesses = new ArrayList<>();
    private ArrayList<Integer> lowFitnesses = new ArrayList<>();
    private ArrayList<Integer> avgNum1s = new ArrayList<>();
    private ArrayList<Integer> avgNum0s = new ArrayList<>();
    private ArrayList<Integer> avgNumQs = new ArrayList<>();
	
    public int bestFitSize() {
        return bestFitnesses.size();
    }

    public void addBestFitnesses(Integer bestFitness) {
        this.bestFitnesses.add(bestFitness);
    }

    public void addAvgFitnesses(Integer avgFitness) {
        this.avgFitnesses.add(avgFitness);
    }

    public void addLowFitnesses(Integer worstFitness) {
       this.lowFitnesses.add(worstFitness);
    }

    public void addAvgNum1s(Integer avg1s) {
        this.avgNum1s.add(avg1s);
    }

    public void addAvgNum0s(Integer avg0s) {
        this.avgNum0s.add(avg0s);
    }

    public void addAvgNumQs(Integer avgQs) {
        this.avgNumQs.add(avgQs);
    }

    public int getBestFitness(int i) {
       return this.bestFitnesses.get(i);
    }

    public int getAvgFitness(int i) {
        return this.avgFitnesses.get(i);
    }

    public int getLowFitness(int i) {
        return this.lowFitnesses.get(i);
    }

    public int getAvgNum1s(int i) {
        return this.avgNum1s.get(i);
    }

    public int getAvgNum0s(int i) {
       return this.avgNum0s.get(i);
    }

    public int getAvgNumQs(int i) {
        return this.avgNumQs.get(i);
    }

    // Methods to manipulate graphics parameters
}