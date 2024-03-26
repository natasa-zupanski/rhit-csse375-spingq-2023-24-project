package mainApp;

public class FitnessConsecOnes implements FitnessStrategy {

    @Override
    public int getFitness(Organism org) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getFitness'");
        int max = 0;
        int curCount = 0;
        for (char c : org.getChromosome().toCharArray()) {
            if (c == '1') {
                curCount++;
                if (curCount > max) {
                    max = curCount;
                }
            } else {
                curCount = 0;
            }
        }
        return max;
    }

}
