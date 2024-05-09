package mainApp;

public class FitnessOnesMinusZeros implements FitnessStrategy {

    @Override
    public int getFitness(String chromosome) {
        int sum0 = 0;
        int sum1 = 0;
        for (int i = 0; i < chromosome.length(); i++) {
            if (chromosome.charAt(i) == '1') {
                sum1++;
            } else if (chromosome.charAt(i) == '0') {
                sum0++;
            } else {
                System.out.println("Nope");
            }
        }
        int num = sum1 - sum0;
        if (num < 0) {
            num = 0;
        }
        return num;
    }

    @Override
    public FitnessType getFitnessType() {
        return FitnessType.ONESMINUSZEROS;
    }

}
