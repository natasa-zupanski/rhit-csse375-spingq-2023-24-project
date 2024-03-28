package mainApp;

public class FitnessTargetOrganism implements FitnessStrategy {

    @Override
    public int getFitness(String chromosome) {
        String targetOrganism = "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110";
        return chromosome.length() - getNumDiffsForStrings(chromosome, targetOrganism);
    }

    private int getNumDiffsForStrings(String first, String second) {
        int diffs = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) {
                diffs++;
            }
        }

        return diffs;
    }

    @Override
    public FitnessType getFitnessType() {
        return FitnessType.TARGETORG;
    }

}
