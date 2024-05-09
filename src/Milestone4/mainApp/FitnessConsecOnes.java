package mainApp;

public class FitnessConsecOnes implements FitnessStrategy {

    @Override
    public int getFitness(String chromosome) {
        int max = 0;
        int curCount = 0;
        for (char c : chromosome.toCharArray()) {
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

    @Override
    public FitnessType getFitnessType() {
        return FitnessType.CONSECONES;
    }

}
