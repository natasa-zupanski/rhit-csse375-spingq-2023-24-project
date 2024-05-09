package mainApp;

import java.util.ArrayList;

public class GraphicsParameters {
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