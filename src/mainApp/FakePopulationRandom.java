package mainApp;

public class FakePopulationRandom implements RandomInterface {
    private static int DEFAULT_START = 1;

    @Override
    public int nextInt(int bound) {
        return DEFAULT_START;
    }

    @Override
    public RandomType getType() {
        return RandomType.FAKE;
    }
}
