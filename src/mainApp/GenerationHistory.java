package mainApp;

public class GenerationHistory {
    private int bestFitness;
    private int averageFitness;
    private int worstFitness;

    public GenerationHistory(int best, int avg, int worst) {
        this.bestFitness = best;
        this.averageFitness = avg;
        this.worstFitness = worst;
    }

    public int getBestFitness() {
        return bestFitness;
    }

    public int getAverageFitness() {
        return averageFitness;
    }

    public int getWorstFitness() {
        return worstFitness;
    }
}
