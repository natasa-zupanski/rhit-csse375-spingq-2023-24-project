package mainApp;

import java.util.Arrays;

public class SelectionRankRoulette implements SelectionStrategy {
    private RandomInterface r;
    private String targetOrganism;

    public SelectionRankRoulette(RandomType randomType, String targetOrganism) {
        r = RandomFactory.getRandomOfType(randomType);
        this.targetOrganism = targetOrganism;
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
        int total = RankLibrary.totalRank(orgs.length);

        int[] ranks = RankLibrary.getRanks(orgs.length);

        int chance = r.nextInt(total);

        int sum = 0;
        for (int index = 0; index < orgs.length; index++) {
            int lastSum = sum;
            sum += ranks[index];
            if (chance >= lastSum && chance <= sum) {
                return new Organism(orgs[index].getChromosome(), orgs[index].getFitnessType(),
                        orgs[index].getRandomType(), this.targetOrganism, orgs[index].getNumGens(),
                        orgs[index].fitness());
            }
        }

        return null;

    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.RANKROULETTE;
    }

}
