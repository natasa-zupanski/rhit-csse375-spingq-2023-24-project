package mainApp;

import java.util.Arrays;
import java.util.Random;

public class SelectionRankRoulette implements SelectionStrategy {
    private RandomInterface r;

    public SelectionRankRoulette(RandomType randomType) {
        r = RandomFactory.getRandomOfType(randomType);
    }

    @Override
    public Organism[] selectFrom(Organism[] orgs) {
        Organism[] result = new Organism[orgs.length];

        for (int index = 0; index < orgs.length; index += 2) {
            Organism selected = this.selectedByRank(orgs);
            result[index] = selected;
            if (index + 1 != orgs.length) {
                result[index + 1] = selected;
            }
        }

        return result;
    }

    private Organism selectedByRank(Organism[] orgs) {
        Arrays.sort(orgs);
        int total = this.totalRank(orgs);

        int[] ranks = this.getRanks(orgs);

        // Random r = new Random();
        int chance = r.nextInt(total);

        int sum = 0;
        for (int index = 0; index < orgs.length; index++) {
            int lastSum = sum;
            sum += ranks[index];
            if (chance >= lastSum && chance <= sum) {
                return new Organism(orgs[index].getChromosome(), orgs[index].getFitnessType());
            }
        }

        return null;

    }

    private int totalRank(Organism[] orgs) {
        int[] ranks = this.getRanks(orgs);

        int sum = 0;
        for (int i : ranks) {
            sum += i;
        }
        return sum;
    }

    private int[] getRanks(Organism[] orgs) {
        int[] result = new int[orgs.length];

        int cur = 1;
        for (int index = 0; index < orgs.length; index++) {
            result[index] = cur;
            cur += 1;
        }

        return result;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.RANKROULETTE;
    }

}
