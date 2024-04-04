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

        double multiplier = (double) total / (double) 4 * ranks[0];

        while (fill < orgs.length) {
            int rank = ranks[orgs.length - 1 - index];
            int numToRun = (int) (rank * multiplier);

            numToRun /= total;
            numToRun /= (index + 1);
            if (numToRun == 0) { // 1
                numToRun += 1;
            }
            for (int i = 0; i < numToRun; i++) {
                if (fill == orgs.length) {
                    break;
                } else {
                    result[fill] = temp[orgs.length - 1 - index];
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
