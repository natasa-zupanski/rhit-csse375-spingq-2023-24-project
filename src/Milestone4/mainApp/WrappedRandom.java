package mainApp;

import java.util.Random;

public class WrappedRandom implements RandomInterface {
    private static Random r = null;

    @Override
    public int nextInt(int bound) {
        if (r != null) {
            return r.nextInt(bound);
        } else {
            init();
            return nextInt(bound);
        }
    }

    private void init() {
        r = new Random();
    }

    @Override
    public RandomType getType() {
        return RandomType.TRUE;
    }

}
