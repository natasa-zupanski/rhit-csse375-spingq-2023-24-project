package mainApp;

public class FitnessFakeConstantFive implements FitnessStrategy {

    @Override
    public int getFitness(String chromosome) {
        return 5;
    }

    @Override
    public FitnessType getFitnessType() {
        return FitnessType.FAKECONSTANTFIVE;
    }

}
