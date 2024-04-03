package mainApp;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Color;

public class PopulationVisualization {
	private GraphicsParameters graphicParam;
	private EvolutionParameters parameters;

	public PopulationVisualization(EvolutionParameters parameters) {
		this.graphicParam = new GraphicsParameters();
		this.parameters = parameters;
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
			g.drawLine(50 + i * scale, 350 - (graphicParam.getBestFitness(i) - graphicParam.getLowFitness(i)),
					50 + (i + 1) * scale,
					350 - ((graphicParam.getBestFitness(i + 1) - graphicParam.getLowFitness(i + 1))));
			g.setColor(Color.BLACK);
			g.drawLine(50, 50 - graphicParam.getBestFitness(i) * 3, 50 + (i + 1) * scale,
					50 - graphicParam.getBestFitness(i) * 3);
			if (this.parameters.getSelectionType() == SelectionType.LEARNINGCHANCE) {
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

	public void populateData(Integer bestFitness, Integer avgFitness, Integer worstFitness, Integer avg1s,
			Integer avg0s, Integer avgQs) {
		this.graphicParam.addBestFitnesses(bestFitness);
		this.graphicParam.addAvgFitnesses(avgFitness);
		this.graphicParam.addLowFitnesses(worstFitness);

		if (this.parameters.getSelectionType() == SelectionType.LEARNINGCHANCE) {
			this.graphicParam.addAvgNum1s(avg1s);
			this.graphicParam.addAvgNum0s(avg0s);
			this.graphicParam.addAvgNumQs(avgQs);
		}
	}

	public void printBestFitness() {
		System.out.println(
				"Created gen #" + this.gensSoFar() + " Best fitness: "
						+ this.graphicParam.getBestFitness(graphicParam.bestFitSize() - 1));
	}

}
