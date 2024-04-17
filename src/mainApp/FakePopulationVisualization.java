package mainApp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class FakePopulationVisualization implements PopulationVisualizationInterface {
    private GraphicsParameters graphicParam;
	private EvolutionParameters parameters;
	private double scaleFactorX;
	private double scaleFactorY;
    private String bestFitnessString;
    private String drawValueString;

	public FakePopulationVisualization(EvolutionParameters parameters) {
		this.graphicParam = new GraphicsParameters();
		this.parameters = parameters;
	}

    private void calculateScaleFactors(JFrame frame) {
		int screenWidth = (int) frame.getSize().getWidth();
		int screenHeight = (int) frame.getSize().getHeight();

		scaleFactorX = (double) screenWidth / 1100;
		scaleFactorY = (double) screenHeight / 600;
	}

    public void calculateScaleFactorsPublic(JFrame frame) {
        calculateScaleFactors(frame);
    }

    public double getScaleFactorX() {
        return scaleFactorX;
    }

    public double getScaleFactorY() {
        return scaleFactorY;
    }

    @Override
    public void drawOn(Graphics2D g, JFrame frame) {
        StringBuilder sb = new StringBuilder();
        calculateScaleFactors(frame);

		int numOfGens = parameters.getNumbersOfGen();
		int scale = 1000 / numOfGens;
        sb.append("SetScale: x:" + scaleFactorX + " y:" + scaleFactorY + "\n");
        sb.append("SetColor: " + Color.BLACK + "\n");

		for (int i = 0; i <= 10; i++) {
            sb.append("DrawLine: x1:" + (50 + i * scale * numOfGens / 10) + " y1:" + (350 - 5) + " x2:" + (50 + i * scale * numOfGens / 10) + "y2:" + (350 + 5) + "\n");
			sb.append("DrawString: "+ (i * numOfGens / 10) + " x:" + (50 + (i * scale * numOfGens / 10) - 10) + " y:" + (350 + 20) + "\n");
            sb.append("DrawLine: x1:" + (50 - 5) + " y1:" + (350 - i * 30) + " x2:" + (50 + 5) + "y2:" + (350 - i * 30) + "\n");
			sb.append("DrawString: "+ ( i * 10) + " x:" + (50 - 25) + " y:" + (350 - (i * 30) + 5) + "\n");
		}

        sb.append("SetStroke: BasicStroke Width: 2\n");

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

    @Override
    public int gensSoFar() {
        if (!this.parameters.getTerminated()) {
			// return generations.size();
			return this.graphicParam.bestFitSize();
		} else {
			return this.parameters.getNumbersOfGen();
		}
    }

    @Override
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

    public GraphicsParameters getGraphicParam() {
        return this.graphicParam;
    }

    @Override
    public void printBestFitness() {
        this.bestFitnessString = "Created gen #" + this.gensSoFar() + " Best fitness: "
                    + this.graphicParam.getBestFitness(graphicParam.bestFitSize() - 1);
    }

    public EvolutionParameters getEvolutionParameters() {
        return this.parameters;
    }

    public String getBestFitnessString() {
       return this.bestFitnessString;
    }

    public String getDrawValueString() {
        return this.drawValueString;
    }
    
}
