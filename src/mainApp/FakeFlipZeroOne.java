package mainApp;

public class FakeFlipZeroOne implements RandomInterface {
    private boolean zero = true;

    @Override
    public int nextInt(int bound) {
        if (zero) {
            zero = false;
            return 0;
        } else {
            zero = true;
            return 1;
        }
    }

    @Override
    public RandomType getType() {
        return RandomType.FAKEFLIP01;
    }

}
