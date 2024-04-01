package mainApp;


public class EvolutionParameters {
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

    /**
     * ensures: constructs a populations parameters
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

    /**
     * 
     * ensures: gets and returns the generation size, an integer
     * 
     * @return the generation size
     */
    public int getGenSize() {
        return this.genSize;
    }

    /**
     * 
     * ensures: gets and returns the chromosome length, the number of alleles each
     * organis is supposed to have
     * 
     * @return the current chromosome length
     */
    public int getChromosomeLength() {
        return this.chromosomeLength;
    }

    /**
     * 
     * ensures: gets the selection method, the process used to select from one
     * generation the organisms used to create the next
     * 
     * @return, the selection method in use
     */
    public String getSelectionMethod() {
        return this.selectionMethod;
    }

    public SelectionType getSelectionType() {
        return this.selectionType;
    }

    /**
     * 
     * ensures: gets and retruns the number of generations to run
     * 
     * @return the number of generations to run
     */
    public int getNumbersOfGen() {
        return this.numOfGens;
    }

    public boolean getUnsure() {
        return isUnsure;
    }

    public FitnessType getFitnessType() {
        return fitnessType;
    }

    /**
     * 
     * ensures: gets and returns the elitism percent, the percent of organisms to be
     * copied over to the next generation unchanged
     * 
     * @return the elitism percent of the population
     */
    public int getElitismPercent() {
        return elitismPercent;
    }

    /**
     * 
     * ensures: gets and returns whether of not crossover is implemented and being
     * used
     * 
     * @return, true if crossover is applied, false if not
     */
    public boolean getCrossover() {
        return crossover;
    }

    /**
     * 
     * ensures: gets and returns the mutation rate, modeled as a percent
     * 
     * @return
     */
    public int getMutationRate() {
        return mutationRate;
    }

    public String getFitnessMethod() {
        return fitnessMethod;
    }

    /**
     * 
     * ensures: sets the mutation rate of the population, modeled as a percent, to
     * the given variable
     * 
     * @param r, the rate, as a percent, to set the mutation rate to
     */
    public void setMutationRate(int r) {
        mutationRate = r;
    }

    /**
     * 
     * ensures: sets the generation size to some given integer, s
     * 
     * @param s, the integer to set the generation size to
     */
    public void setGenSize(int s) {
        genSize = s;
    }

    /**
     * 
     * ensures: sets the number of generations to run to the given integer, g
     * 
     * @param g, the integer to set the number of generations to run to
     */
    public void setNumGens(int g) {
        this.numOfGens = g;
    }

    /**
     * 
     * ensures: sets the chromosome length, the number of alleles each organism is
     * supposed to have, to the given integer l
     * 
     * @param l, the integer to set the chromosome length to
     */
    public void setGenomeLength(int l) {
        this.chromosomeLength = l;
    }

    /**
     * 
     * ensures: sets the elitism percent, the percent of organisms to be copied over
     * to the next generation unchanged
     * 
     * @param r, the percent given as an integer
     */
    public void setElitism(int r) {
        this.elitismPercent = r;
    }

    /**
     * 
     * ensures: sets the crossover, true if crossover is applied, false if it is
     * not, in accordance to the value of the given c
     * 
     * @param c, the true or false boolean value to set the crossover to
     */
    public void setCrossover(boolean c) {
        this.crossover = c;
    }

    // /**
    // *
    // * ensures: sets the fitness method, the process which extracts a fitness
    // value,
    // * a phenotype, from the genetic code or genotype of an organism
    // *
    // * @param fitnessMethod, the name of the method to be used
    // */
    public void setFitnessMethod(String fitnessMethod2) {
        this.fitnessMethod = fitnessMethod2;
    }

    /**
     * 
     * ensures: sets the termination condition, the fitness value at which the user
     * wishes the program to terminate and the population to stop evolving
     * 
     * @param t, the integer value to set the termination condition to
     */
    public void setTermination(int t) {
        this.terminationCondition = t;
    }

    /**
     * 
     * ensures: gets and returns the current termination condition, the fitness
     * value at which the user wishes the program to terminate
     * 
     * @return, the current termination condition
     */
    public int getTermination() {
        return terminationCondition;
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

    /**
     * 
     * ensures: sets the boolean value, terminated, to true. Terminated helps
     * determine when the program should stop the evolution of a population
     * 
     */
    public void terminate() {
        this.termination = true;
    }
}
