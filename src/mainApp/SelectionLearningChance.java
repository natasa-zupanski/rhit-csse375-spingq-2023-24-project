package mainApp;

import java.util.HashMap;
import java.util.Random;

public class SelectionLearningChance implements SelectionStrategy {
    private int numOfGens;

    public SelectionLearningChance(int numOfGens) {
        this.numOfGens = numOfGens;
    }

    @Override
    public Organism[] selectFrom(Organism[] orgs) {
        resetConstantFitnesses(orgs);
        HashMap<Organism, Integer> map = new HashMap<>();

        for (int i = 0; i < orgs.length; i++) {
            orgs[i].setNumGens(numOfGens);
            map.putIfAbsent(orgs[i], orgs[i].fitness() + 1);
        }

        Organism[] result = new Organism[orgs.length];
        for (int i = 0; i < orgs.length; i++) {
            result[i] = this.selectedByLearningChances(map);
        }

        return result;
    }

    private void resetConstantFitnesses(Organism[] orgs) {
        for (Organism o : orgs) {
            o.resetConstantFitness();
        }
    }

    private Organism selectedByLearningChances(HashMap<Organism, Integer> map) {
        int sum = 0;
        Integer[] values = new Integer[map.size()];
        int index = 0;
        for (Organism o : map.keySet()) {
            values[index] = map.get(o);
            index += 1;
        }

        int total = this.totalLearningFitnesses(values);
        Random r = new Random();
        int chance = r.nextInt(total);

        for (Organism o : map.keySet()) {
            int lastSum = sum;
            sum += map.get(o);

            if (chance <= sum && chance >= lastSum) {
                return o;
            }
        }

        return null;

    }

    private int totalLearningFitnesses(Integer[] fitnesses) {
        int sum = 0;
        for (int i : fitnesses) {
            sum += i;
        }
        return sum;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.LEARNINGCHANCE;
    }

}
