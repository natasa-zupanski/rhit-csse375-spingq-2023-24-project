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
            case FAKEFLIP01:
                return new FakeFlipZeroOne();
            case FAKEPOPULATION:
                return new FakePopulationRandom();
            default:
                return null;
        }
    }
}
