package mainApp;

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
        sb.append("SetColor: BLACK\n");

		for (int i = 0; i <= 10; i++) {
            sb.append("DrawLine: x1:" + (50 + i * scale * numOfGens / 10) + " y1:" + (350 - 5) + " x2:" + (50 + i * scale * numOfGens / 10) + "y2:" + (350 + 5) + "\n");
			sb.append("DrawString: "+ (i * numOfGens / 10) + " x:" + (50 + (i * scale * numOfGens / 10) - 10) + " y:" + (350 + 20) + "\n");
            sb.append("DrawLine: x1:" + (50 - 5) + " y1:" + (350 - i * 30) + " x2:" + (50 + 5) + "y2:" + (350 - i * 30) + "\n");
			sb.append("DrawString: "+ ( i * 10) + " x:" + (50 - 25) + " y:" + (350 - (i * 30) + 5) + "\n");
		}

        sb.append("SetStroke: BasicStroke Width: 2\n");

        sb.append("DrawLine: x1:" + 50 + " y1:" + 350 + " x2:" + (50 + 1000) + "y2:" + 350 + "\n");
        sb.append("DrawLine: x1:" + 50 + " y1:" + 50 + " x2:" + 50 + "y2:" + 350 + "\n");
		
        sb.append("SetColor: GRAY\n");

        sb.append("SetStroke: BasicStroke Width: 1\n");

		if (gensSoFar() > 2) {
            sb.append("DrawLine: x1:" + 50 + " y1:" + (350 - graphicParam.getBestFitness(gensSoFar() - 2) * 3) + " x2:" + (50 + (gensSoFar() - 2) * scale) 
            + "y2:" + (350 - graphicParam.getBestFitness(gensSoFar() - 2) * 3) + "\n");
		}

		sb.append("SetStroke: BasicStroke Width: 2\n");
		for (int i = 0; i < gensSoFar() - 2; i++) {
            sb.append("SetColor: GREEN\n");
            sb.append("DrawLine: x1:" + (50 + i * scale) + " y1:" + ((350 - graphicParam.getBestFitness(i) * 3)) + " x2:" + (50 + (i + 1) * scale) 
            + "y2:" + (350 - graphicParam.getBestFitness(i + 1) * 3) + "\n");
            sb.append("SetColor: ORANGE\n");
            sb.append("DrawLine: x1:" + (50 + i * scale) + " y1:" + (350 - graphicParam.getAvgFitness(i) * 3) + " x2:" + (50 + (i + 1) * scale) 
            + "y2:" + (350 - graphicParam.getAvgFitness(i + 1) * 3) + "\n");
			sb.append("SetColor: RED\n");
            sb.append("DrawLine: x1:" + (50 + i * scale) + " y1:" + (350 - graphicParam.getLowFitness(i) * 3) + " x2:" + (50 + (i + 1) * scale) 
            + "y2:" + (350 - graphicParam.getLowFitness(i + 1) * 3) + "\n");
            sb.append("SetColor: MAGENTA\n");
            sb.append("DrawLine: x1:" + (50 + i * scale) + " y1:" + (350 - (graphicParam.getBestFitness(i) - graphicParam.getLowFitness(i))) + " x2:" + (50 + (i + 1) * scale) 
            + "y2:" + (350 - ((graphicParam.getBestFitness(i + 1) - graphicParam.getLowFitness(i + 1)))) + "\n");
            sb.append("SetColor: BLACK\n");
            sb.append("DrawLine: x1:" + 50 + " y1:" + (50 - graphicParam.getBestFitness(i) * 3) + " x2:" + (50 + (i + 1) * scale) 
            + "y2:" + (50 - graphicParam.getBestFitness(i + 1) * 3) + "\n");
			if (this.parameters.getSelectionType() == SelectionType.LEARNINGCHANCE) {
				sb.append("SetColor: BLUE\n");
                sb.append("DrawLine: x1:" + (50 + i * scale) + " y1:" + (350 - graphicParam.getAvgNum1s(i) * 3) + " x2:" + (50 + (i + 1) * scale) 
                + "y2:" + (350 - graphicParam.getAvgNum1s(i + 1) * 3) + "\n");
                sb.append("SetColor: CYAN\n");
                sb.append("DrawLine: x1:" + (50 + i * scale) + " y1:" + (350 - graphicParam.getAvgNum0s(i) * 3) + " x2:" + (50 + (i + 1) * scale) 
                + "y2:" + (350 - graphicParam.getAvgNum0s(i + 1) * 3) + "\n");
                sb.append("SetColor: LIGHT_GRAY\n");
                sb.append("DrawLine: x1:" + (50 + i * scale) + " y1:" + (350 - graphicParam.getAvgNumQs(i) * 3) + " x2:" + (50 + (i + 1) * scale) 
                + "y2:" + (350 - graphicParam.getAvgNumQs(i + 1) * 3) + "\n");
			}
            this.drawValueString = sb.toString();
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
