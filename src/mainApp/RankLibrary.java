package mainApp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RankLibrary {
    public static int totalRank(int num) {
        return IntStream.rangeClosed(1, num).sum();
    }

    public static int[] getRanks(int num) {
        // List<Integer> list = IntStream.rangeClosed(1,
        // num).boxed().collect(Collectors.toList());
        // return new Integer[1];
        int[] result = new int[num];

        int cur = 1;
        for (int index = 0; index < num; index++) {
            result[index] = cur;
            cur += 1;
        }

        return result;
    }
}
