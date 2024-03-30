package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * Class: PopulationComponent
 * 
 * @author R_003: Allyn Loyd and Natasa Zupanski
 *         </br>
 *         Purpose: Used to handle the drawing of the evolution graph and
 *         updates to the population parameters
 *         </br>
 *         Restrictions: None
 *         </br>
 *         For example: PopulationComponent pop = new PopulationComponent(new
 *         Population());
 */
public class PopulationComponent extends JComponent {

	private Population population;
	private EvolutionParameters evolutionParameters;

	public PopulationComponent(Population p) {
		this.population = p;
		this.evolutionParameters = p.getEvolutionParameters();
	}

	public void updateState() {
		this.population.nextGeneration();

	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		this.population.getPopulationVisualization().drawOn(g2d);

	}

	public void drawScreen() {
		this.repaint();
	}
	
	public void createNewPopulation(int mutationRate, int numOfGens, int genSize, int chromosomeLength, int elitismPercent, String selectionMethod, String fitnessMethod, boolean crossover, int termination) {
		EvolutionParameters parameters = new EvolutionParameters(mutationRate, numOfGens, genSize, chromosomeLength, elitismPercent, selectionMethod, fitnessMethod, crossover, termination);
		this.population = new Population(parameters);
		this.evolutionParameters = population.getEvolutionParameters();
	}

	public String handleGetMutationRate() {
		return "" + this.evolutionParameters.getMutationRate();
	}

	public void handleSetMutationRate(int r) {
		this.evolutionParameters.setMutationRate(r);
	}

	public void handleSetSelection(String selection) {
		this.evolutionParameters.setSelection(selection);
	}

	public boolean handleGetCrossover() {
		return this.evolutionParameters.getCrossover();
	}

	public void handleSetCrossover(boolean crossover) {
		this.evolutionParameters.setCrossover(crossover);
	}

	public String handleGetGenSize() {
		return String.valueOf(this.evolutionParameters.getGenSize());
	}

	public void handleSetGenSize(int size) {
		this.evolutionParameters.setGenSize(size);
	}

	public String handleGetNumGens() {
		return String.valueOf(this.evolutionParameters.getNumbersOfGen());
	}

	public void handleSetNumGens(int num) {
		this.evolutionParameters.setNumGens(num);
	}

	public String handleGetGenomeLength() {
		return String.valueOf(this.evolutionParameters.getChromosomeLength());
	}

	public void handleSetGenomeLength(int length) {
		this.evolutionParameters.setGenomeLength(length);
	}

	public String handleGetElitism() {
		return String.valueOf(this.evolutionParameters.getElitismPercent());
	}

	public void handleSetElitism(int rate) {
		this.evolutionParameters.setElitism(rate);
	}

	public int handleGetGensSoFar() {
		return this.population.getPopulationVisualization().gensSoFar();
	}

	public void handleRunPopulationEvol() {
		this.population.spawnFirstGeneration();
	}

	public void handleSetFitness(String fitnessMethod) {
		this.evolutionParameters.setFitnessMethod(fitnessMethod);
	}

	public Organism handleGetFittest() {
		return this.population.getFittest();
	}

	public void handleSetTermination(int t) {
		this.evolutionParameters.setTermination(t);
	}

	public Generation handleGetLatestGen() {
		return this.population.getLatestGen();
	}

	public String handleGetTermination() {
		return "" + this.evolutionParameters.getTermination();
	}

	public void handleTerminate() {
		this.evolutionParameters.terminate();
	}

	public Population getPopulation() {
		return this.population;
	}

}
