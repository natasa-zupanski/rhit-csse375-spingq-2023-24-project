package mainApp;

public class FitnessTargetOrganism implements FitnessStrategy {

    @Override
    public int getFitness(Organism org) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getFitness'");
        Organism targetOrganism = new Organism(
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        // int sum = 0;
        // for (int i = 0; i < org.length(); i++) {
        // if (org.getChromosome().substring(i, i +
        // 1).equals(targetOrganism.getChromosome().substring(i, i + 1))) {
        // sum++;
        // }
        // }
        // return sum;
        return org.length() - getNumDiffsForStrings(org.getChromosome(), targetOrganism.getChromosome());
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

}
