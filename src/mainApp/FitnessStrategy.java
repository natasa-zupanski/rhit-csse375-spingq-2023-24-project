package mainApp;

public interface FitnessStrategy {
    abstract int getFitness(Organism org);

    abstract FitnessType getFitnessType();
}
