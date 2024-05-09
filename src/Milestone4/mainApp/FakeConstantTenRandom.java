package mainApp;

public class FakeConstantTenRandom implements RandomInterface {

    @Override
    public int nextInt(int bound) {
        return 10;
    }

    @Override
    public RandomType getType() {
        return RandomType.FAKECONTSANTTEN;
    }

}
