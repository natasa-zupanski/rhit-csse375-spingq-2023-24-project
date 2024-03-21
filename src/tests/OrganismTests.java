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

    }

    public void setChromosomeTest() {

    }

    public void getChromosomeTest() {

    }

}
