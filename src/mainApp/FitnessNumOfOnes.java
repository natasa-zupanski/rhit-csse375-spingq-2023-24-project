package mainApp;

public class FitnessNumOfOnes implements FitnessStrategy {

    @Override
    public int getFitness(Organism org) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getFitness'");
        String chromosome = org.getChromosome();
        int count = 0;
        for (int i = 0; i < chromosome.length(); i++) {
            if (chromosome.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

}
