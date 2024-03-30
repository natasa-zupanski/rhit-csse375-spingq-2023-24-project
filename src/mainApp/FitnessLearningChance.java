package mainApp;

import java.util.Random;

public class FitnessLearningChance implements FitnessStrategy {
    private int days;
    private int constantFitness;

    public FitnessLearningChance(int days, int constantFitness) {
        this.days = days;
        this.constantFitness = constantFitness;
    }

    @Override
    public int getFitness(String chromosome) {
        if (this.constantFitness == -1) {
            int fitness = 0;
            int count = 0;

            while (fitness != chromosome.length() && count != days) {
                fitness = this.getFitnessForDay(chromosome);
                count += 1;
            }

            this.constantFitness = 19 * (days - count);
            return constantFitness;
        } else {
            return this.constantFitness;
        }
    }

    private int getFitnessForDay(String chromosome) {
        return this.fitnessOf1s(chromosome) + this.determineUnresolvedFitnesses(chromosome);
    }

    private int fitnessOf1s(String chromosome) {
        int sum = 0;

        for (char c : chromosome.toCharArray()) {
            if (c == '1') {
                sum += 1;
            }
        }

        return sum;
    }

    private int determineUnresolvedFitnesses(String chromosome) {
        Random r = new Random();
        int sum = 0;
        for (char c : chromosome.toCharArray()) {
            if (c == '?') {
                int chance = r.nextInt(2);
                if (chance == 1) {
                    sum += 1;
                }
            }
        }
        return sum;
    }

    @Override
    public FitnessType getFitnessType() {
        return FitnessType.LEARNINGCHANCE;
    }

}
