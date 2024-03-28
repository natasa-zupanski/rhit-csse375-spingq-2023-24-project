package mainApp;

public class FitnessNumOfOnes implements FitnessStrategy {

    @Override
    public int getFitness(Organism org) {
        String chromosome = org.getChromosome();
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
