package mainApp;

public class FitnessNumOfOnes implements FitnessStrategy {

    @Override
    public int getFitness(String chromosome) {
        int count = 0;
        for (int i = 0; i < chromosome.length(); i++) {
            if (chromosome.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    @Override
    public FitnessType getFitnessType() {
        return FitnessType.NUMONES;
    }

}
