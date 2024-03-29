package mainApp;

import java.util.Arrays;

public class SelectionRank implements SelectionStrategy {

    @Override
    public Organism[] selectFrom(Organism[] orgs) {
        Organism[] result = new Organism[orgs.length];
        Organism[] temp = orgs;
        Arrays.sort(temp);
        int[] ranks = this.getRanks(orgs);
        int total = this.totalRank(orgs);
        int fill = 0;
        int index = 0;

        double multiplier = (double) total / (double) 4 * ranks[0]; // example: 10 organisms, each fitness. 1 fitness
                                                                    // 100. 1 fitness 1. rest fitness 50. Mult =
                                                                    // 100+400+0=500/4*100 = 500/4 = 125?

        while (fill < orgs.length) {
            int rank = ranks[orgs.length - 1 - index]; // The 100?
            int numToRun = (int) (rank * multiplier); // 100 * 125 = 12,500 -- would completely fill with the fitness
                                                      // 100.
            numToRun /= total;
            numToRun /= (index + 1);
            if (numToRun == 0) { // 1
                numToRun += 1;
            }
            for (int i = 0; i < numToRun; i++) {
                if (fill == orgs.length) {
                    break;
                } else {
                    result[fill] = temp[orgs.length - 1 - index]; // add 0...,
                    fill += 1;
                }
            }
            index += 1;

        }

        return result;
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
        return SelectionType.RANK;
    }

}
