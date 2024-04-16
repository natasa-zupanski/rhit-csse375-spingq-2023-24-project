package mainApp;

public class FakeRandom implements RandomInterface {
    private static int DEFAULT_START = 1;

    public int nextInt(int bound) {
        return DEFAULT_START;
    }

    @Override
    public RandomType getType() {
        return RandomType.FAKE;
    }
}
