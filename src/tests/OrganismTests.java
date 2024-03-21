package tests;

import mainApp.Organism;

public class OrganismTests {

    public void lengthTest() {
        Organism twenty = new Organism("00000000001111111111");
        int length = twenty.length();
        // assert length = 20

        Organism hundred = new Organism(100, "Num. of 1s");
        // assert length = 100

        Organism thirteen = new Organism("0001111100000", "Num. of 1s");

    }

    public void numOf0sTest() {
        Organism one = new Organism("101111");

        Organism thirteen = new Organism("111010011111001110101011111010110010111");

    }

    public void numOf1sTest() {
        Organism one = new Organism("001000000");

        Organism thirteen = new Organism("10100010010000000000001000010001000010010011000000110");
    }

    public void flipAlleleTest() {

    }

    public void toIntArrTest() {

    }

    public void mutatePercentTest() {

    }

    public void mutateTest() {
        Organism one = new Organism("1111");
        one.mutate(1);

        Organism four = new Organism("1111");
        four.mutate(4);
    }

    public void setChromosomeTest() {

    }

    public void getChromosomeTest() {

    }

    public void fitnessOf1sTest() {
        Organism one = new Organism("0010000", "Num. of 1s");

        Organism thirteen = new Organism("10100010010000000000001000010001000010010011000000110", "Num. of 1s");
    }

    public void fitnessConsec1sTest() {
        Organism one = new Organism("0101010101010101010101010101010", "Consec. num. 1s");

        Organism five = new Organism("010010111011111001010100100001111000001101", "Consec. num. 1s");
    }

    public void fitnessTargetOrganismtest() {
        Organism target = new Organism(
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                "Target Organism");

        Organism oneOff = new Organism(
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111111",
                "Target Organism");

        Organism thirteenOff = new Organism(
                "1010001000111000110101101010101101011101001101110110010110011101000010100011110100000000010011111110",
                "Target Organism");
    }

    // requires: |first| = |second|
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
