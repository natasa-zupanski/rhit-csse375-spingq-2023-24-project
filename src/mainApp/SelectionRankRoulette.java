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
        int total = RankLibrary.totalRank(orgs.length); // this.totalRank(orgs);

        int[] ranks = RankLibrary.getRanks(orgs.length); // this.getRanks(orgs);

        // Random r = new Random();
        int chance = r.nextInt(total);

        int sum = 0;
        for (int index = 0; index < orgs.length; index++) {
            int lastSum = sum;
            sum += ranks[index];
            if (chance >= lastSum && chance <= sum) {
                return new Organism(orgs[index].getChromosome(), orgs[index].getFitnessType(),
                        orgs[index].getRandomType());
            }
        }

        return null;

    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.RANKROULETTE;
    }

}
