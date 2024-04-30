package mainApp;

import java.util.stream.IntStream;

public class RankLibrary {
    public static int totalRank(int num) {
        return IntStream.rangeClosed(1, num).sum();
    }

    public static int[] getRanks(int num) {
        int[] result = new int[num];

        int cur = 1;
        for (int index = 0; index < num; index++) {
            result[index] = cur;
            cur += 1;
        }

        return result;
    }
}
