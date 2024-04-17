package mainApp;

import java.awt.Graphics2D;

import javax.swing.JFrame;

public class FakePopulationVisualization implements PopulationVisualizationInterface {
    private GraphicsParameters graphicParam;
	private EvolutionParameters parameters;
	private double scaleFactorX;
	private double scaleFactorY;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawOn'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'printBestFitness'");
    }

    public EvolutionParameters getEvolutionParameters() {
        return this.parameters;
    }
    
}
