package mainApp;

public class FakeRandom implements RandomInterface {
    private static int DEFAULT_START = 0;

    public int nextInt(int bound) {
        return DEFAULT_START;
    }
}
