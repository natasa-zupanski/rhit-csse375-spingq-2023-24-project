package mainApp;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;

public class PopulationVisualization implements PopulationVisualizationInterface {
	private GraphicsParameters graphicParam;
	private EvolutionParameters parameters;
	private double scaleFactorX;
	private double scaleFactorY;

	public PopulationVisualization(EvolutionParameters parameters) {
		this.graphicParam = new GraphicsParameters();
		this.parameters = parameters;
	}

	private void calculateScaleFactors(JFrame frame) {
		int screenWidth = (int) frame.getSize().getWidth();
		int screenHeight = (int) frame.getSize().getHeight();

		scaleFactorX = (double) screenWidth / 1100;
		scaleFactorY = (double) screenHeight / 600;
	}

	/**
	 * TODO inner comments
	 * ensures: draws the population at a point and time, the line of best fitness,
	 * that of average fitness, that of worst fitness and, if the research results
	 * are attempting to be replicated, the average number of 1s, 0s, and ?s as they
	 * change in the population over time.
	 * 
	 * @param frame
	 * 
	 * @param g,    the 2D graphics on which to draw these lines
	 */
	public void drawOn(Graphics2D g, JFrame frame) {
		calculateScaleFactors(frame);

		int numOfGens = parameters.getNumbersOfGen();
		int scale = 1000 / numOfGens;
		g.scale(scaleFactorX, scaleFactorY);

		g.setColor(Color.BLACK);

		for (int i = 0; i <= 10; i++) {
			g.drawLine(70 + i * scale * numOfGens / 10, 345, 70 + i * scale * numOfGens / 10, 355);
			g.drawString("" + i * numOfGens / 10, 70 + (i * scale * numOfGens / 10) - 10, 370);
			g.drawLine(65, 350 - i * 30, 75, 350 - i * 30);
			g.drawString("" + i * 10, 45, 350 - (i * 30) + 5);
		}
		g.setFont(new Font("Ariel", Font.BOLD, 12));
		g.drawString("Generation", 550, 400);
		String yLabel = "Fitness";
		int y = 150;
		for (int i = 0; i < yLabel.length(); i++) {
            String character = String.valueOf(yLabel.charAt(i));
            g.drawString(character, 20, y);
            y += g.getFontMetrics().getHeight(); 
        }

		g.setStroke(new BasicStroke(2));

		g.drawLine(70, 350, 1070, 350);
		g.drawLine(70, 50, 70, 350);

		g.setStroke(new BasicStroke(4));
		g.setColor(Color.GREEN);
		g.drawLine(100, 415, 110, 415);
		g.setColor(Color.ORANGE);
		g.drawLine(220, 415, 230, 415);
		g.setColor(Color.RED);
		g.drawLine(340, 415, 350, 415);
		g.setColor(Color.MAGENTA);
		g.drawLine(450, 415, 460, 415);
		g.setColor(Color.BLUE);
		g.drawLine(570, 415, 580, 415);
		g.setColor(Color.CYAN);
		g.drawLine(720, 415, 730, 415);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(870, 415, 880, 415);

		g.setFont(new Font("Ariel", Font.PLAIN, 12));
		g.setColor(Color.BLACK);
		g.drawString("Greatest Fitness", 120, 420);
		g.drawString("Average Fitness", 240, 420);
		g.drawString("Lowest Fitness", 360, 420);
		g.drawString("Range of Fitness", 470, 420);
		g.drawString("Average Number of 1s", 590, 420);
		g.drawString("Average Number of 0s", 740, 420);
		g.drawString("Average Number of Qs", 890, 420);

		g.setColor(Color.GRAY);
		g.setStroke(new BasicStroke(1));
		if (gensSoFar() > 2) {
			g.drawLine(70, 350 - graphicParam.getBestFitness(gensSoFar() - 2) * 3, 70 + (gensSoFar() - 2) * scale,
					350 - graphicParam.getBestFitness(gensSoFar() - 2) * 3);
		}

		g.setStroke(new BasicStroke(2));
		for (int i = 0; i < gensSoFar() - 2; i++) {
			g.setColor(Color.GREEN);
			g.drawLine(70 + i * scale, (350 - graphicParam.getBestFitness(i) * 3), 70 + (i + 1) * scale,
					(350 - graphicParam.getBestFitness(i + 1) * 3));
			g.setColor(Color.ORANGE);
			g.drawLine(70 + i * scale, 350 - graphicParam.getAvgFitness(i) * 3, 70 + (i + 1) * scale,
					350 - graphicParam.getAvgFitness(i + 1) * 3);
			g.setColor(Color.RED);
			g.drawLine(70 + i * scale, 350 - graphicParam.getLowFitness(i) * 3, 70 + (i + 1) * scale,
					350 - graphicParam.getLowFitness(i + 1) * 3);
			g.setColor(Color.MAGENTA);
			g.drawLine(70 + i * scale, 350 - (graphicParam.getBestFitness(i) - graphicParam.getLowFitness(i)),
					70 + (i + 1) * scale,
					350 - ((graphicParam.getBestFitness(i + 1) - graphicParam.getLowFitness(i + 1))));
			if (this.parameters.getSelectionType() == SelectionType.LEARNINGCHANCE) {
				g.setColor(Color.BLUE);
				g.drawLine(70 + i * scale, 350 - graphicParam.getAvgNum1s(i) * 3, 70 + (i + 1) * scale,
						350 - graphicParam.getAvgNum1s(i + 1) * 3);
				g.setColor(Color.CYAN);
				g.drawLine(70 + i * scale, 350 - graphicParam.getAvgNum0s(i) * 3, 70 + (i + 1) * scale,
						350 - graphicParam.getAvgNum0s(i + 1) * 3);
				g.setColor(Color.LIGHT_GRAY);
				g.drawLine(70 + i * scale, 350 - graphicParam.getAvgNumQs(i) * 3, 70 + (i + 1) * scale,
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

	public void terminate() {
		//Saver saver = new Saver();
		// saver.addDataLine();
		// throw new UnsupportedOperationException("Unimplemented method 'terminate'");
	}

}
