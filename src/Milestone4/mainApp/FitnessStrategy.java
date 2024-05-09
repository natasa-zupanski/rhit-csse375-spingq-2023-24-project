package mainApp;

public interface FitnessStrategy {
    abstract int getFitness(String chromosome);

    abstract FitnessType getFitnessType();
}
