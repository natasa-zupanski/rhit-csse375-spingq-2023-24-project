package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
/**
 * Class: PopulationComponent
 * @author R_003: Allyn Loyd and Natasa Zupanski
 * </br>Purpose: Used to handle the drawing of the evolution graph and updates to the population parameters
 * </br>Restrictions: None
 * </br> For example: PopulationComponent pop = new PopulationComponent(new Population());
 */
public class PopulationComponent extends JComponent{
	
	private Population population;
	public PopulationComponent(Population p) {
		this.population = p;
	}
	
	public void updateState() {
		this.population.nextGeneration();
				
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		this.population.drawOn(g2d);
		
	}
	
	public void drawScreen() {
		this.repaint();
	}
	
	public void createNewPopulation(int mutationRate, int numOfGens, int genSize, int chromosomeLength, int elitismPercent, String selectionMethod, String fitnessMethod, boolean crossover, int termination) {
		EvolutionParameters parameters = new EvolutionParameters(mutationRate, numOfGens, genSize, chromosomeLength, elitismPercent, selectionMethod, fitnessMethod, crossover, termination);
		this.population = new Population(parameters);
	}

	public String handleGetMutationRate() {
		return "" + this.population.getMutationRate();
	}
	public void handleSetMutationRate(int r) {
		this.population.setMutationRate(r);
	}
	public void handleSetSelection(String selection) {
		this.population.setSelection(selection);
	}
	public boolean handleGetCrossover() {
		return this.population.getCrossover();
	}
	public void handleSetCrossover(boolean crossover) {
		this.population.setCrossover(crossover);
	}

	public String handleGetGenSize() {
		return String.valueOf(this.population.getGenSize());
	}

	public void handleSetGenSize(int size) {
		this.population.setGenSize(size);
	}

	public String handleGetNumGens() {
		return String.valueOf(this.population.getNumGens());
	}

	public void handleSetNumGens(int num) {
		this.population.setNumGens(num);
	}

	public String handleGetGenomeLength() {
		return String.valueOf(this.population.getGenomeLength());
	}

	public void handleSetGenomeLength(int length) {
		this.population.setGenomeLength(length);
	}

	public String handleGetElitism() {
		return String.valueOf(this.population.getElitism());
	}

	public void handleSetElitism(int rate) {
		this.population.setElitism(rate);
	}
	
	public int handleGetGensSoFar() {
		return this.population.gensSoFar();
	}

	public void handleRunPopulationEvol() {
		this.population.newGen();
	}

	public void handleSetFitness(String fitnessMethod) {
		this.population.setFitnessMethod(fitnessMethod);		
	}

	public Organism handleGetFittest() {
		return this.population.getFittest();
	}
	
	public void handleSetTermination(int t) {
		this.population.setTermination(t);
	}
	
	public Generation handleGetLatestGen() {
		return this.population.getLatestGen();
	}
	
	public String handleGetTermination() {
		return "" + this.population.getTermination();
	}
	
	public void handleTerminate() {
		this.population.terminate();
	}
	
	

	
	
}
