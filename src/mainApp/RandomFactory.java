package mainApp;

public class RandomFactory {
    public static RandomInterface getRandomOfType(RandomType type) {
        switch (type) {
            case FAKE:
                return new FakeRandom();
            case TRUE:
                return new WrappedRandom();
            case FAKECONTSANTTEN:
                return new FakeConstantTenRandom();
            default:
                return null;
        }
    }
}
