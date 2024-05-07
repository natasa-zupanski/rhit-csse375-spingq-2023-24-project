package mainApp;

import java.util.Arrays;

public class SelectionRouletteWheel implements SelectionStrategy {
    private RandomInterface r;
    private String targetOrganism;

    public SelectionRouletteWheel(RandomType randomType, String targetOrganism) {
        r = RandomFactory.getRandomOfType(randomType);
        this.targetOrganism = targetOrganism;
    }

    @Override
    public Organism[] selectFrom(Organism[] orgs) {
        Organism[] result = new Organism[orgs.length];

        for (int index = 0; index < orgs.length; index += 2) {
            Organism selected = selectedByChancePercents(orgs);
            result[index] = selected;
            if (index + 1 != orgs.length) {
                result[index + 1] = selected;
            }
        }

        return result;
    }

    private Organism selectedByChancePercents(Organism[] orgs) {
        int[] fitnesses = this.getFitnesses(orgs);
        // Random r = new Random();
        int total = fitnesses[orgs.length];
        int chance = r.nextInt(total);

        int sum = 0;
        for (int index = 0; index < orgs.length; index++) {
            int lastSum = sum;
            sum += fitnesses[index];
            if (chance >= lastSum && chance <= sum) {
                return new Organism(orgs[index].getChromosome(), orgs[index].getFitnessType(),
                        orgs[index].getRandomType(), this.targetOrganism, orgs[index].getNumGens(),
                        orgs[index].fitness());
            }
        }
        return null;
    }

    private int[] getFitnesses(Organism[] orgs) {
        Arrays.sort(orgs);
        int[] result = new int[orgs.length + 1];
        int total = 0;

        for (int i = 0; i < orgs.length; i++) {
            int fitness = orgs[i].fitness();
            result[i] = fitness;
            total += fitness;
        }
        result[orgs.length] = total;

        return result;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.ROULETTEWHEEL;
    }

}
