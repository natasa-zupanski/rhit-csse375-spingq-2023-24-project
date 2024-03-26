package mainApp;

public class FitnessProdConsecOnes implements FitnessStrategy {

    @Override
    public int getFitness(Organism org) {
        int prod = 1;
        int curCount = 0;
        boolean existsOne = false;
        String chromosome = org.getChromosome();
        for (int i = 0; i < org.length(); i++) {
            char c = chromosome.charAt(i);
            if (c == '1') {
                curCount++;
                existsOne = true;
            } else {
                if (i > 1 && chromosome.charAt(i - 1) == '1') {
                    prod *= curCount;
                }
                curCount = 0;
            }
        }
        if (!existsOne) {
            return 0;
        }
        return prod;
    }

}
