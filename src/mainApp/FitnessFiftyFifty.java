package mainApp;

public class FitnessFiftyFifty implements FitnessStrategy {

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
        int half = chromosome.length() / 2;
        int num = chromosome.length() - (Math.abs(half - sum0) + Math.abs(half - sum1));
        System.out.println(num);
        return num;
    }

    @Override
    public FitnessType getFitnessType() {
        return FitnessType.FIFTYFIFTY;
    }

}
