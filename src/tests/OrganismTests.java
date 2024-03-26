package tests;

import mainApp.Organism;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.*;

public class OrganismTests {

    @Test
    public void lengthTest() {
        Organism twenty = new Organism("00000000001111111111");
        int length = twenty.length();
        assertEquals(20, length);

        Organism hundred = new Organism(100, "Num. of 1s");
        // assert length = 100
        assertEquals(100, hundred.length());

        Organism thirteen = new Organism("0001111100000", "Num. of 1s");
        assertEquals(13, thirteen.length());

    }

    @Test
    public void numOf0sTest() {
        Organism one = new Organism("101111");
        assertEquals(1, one.numOf0s());

        Organism thirteen = new Organism("111010011111001110101011111010110010111");
        assertEquals(13, thirteen.numOf0s());
    }

    public void numOf1sTest() {
        Organism one = new Organism("001000000");

        Organism thirteen = new Organism("10100010010000000000001000010001000010010011000000110");
    }

    @Test
    public void flipAlleleTest() {
        try {
            Method flipAllele = Organism.class.getDeclaredMethod("flipAllele", Integer.class);
            flipAllele.setAccessible(true);
            Organism one = new Organism("111111111");
            Integer integerOne = 1;
            flipAllele.invoke(one, integerOne);
            assertEquals('0', one.getChromosome().charAt(1));
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void toIntArrTest() {
        Organism four = new Organism("0101");
        int[][] expected = { { 0, 1 }, { 0, 1 } };
        int[][] fourArr = four.toIntAr();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(expected[i][j], fourArr[i][j]);
            }
        }
    }

    public void mutatePercentTest() {
        // untestable as it is below...
    }

    @Test
    public void mutateTest() {
        // the rate is the chance to flip at each index. So, we cannot know how many
        // will be flipped. we only have that value on average of all mutate calls with
        // the same rate.

        // Organism one = new Organism("1111");
        // String before = one.getChromosome();
        // one.mutate(1);
        // String after = one.getChromosome();
        // assertEquals(1, getNumDiffsForStrings(before, after));

        // Organism four = new Organism("1111");
        // before = four.getChromosome();
        // four.mutate(4);
        // after = four.getChromosome();
        // assertEquals(4, getNumDiffsForStrings(before, after));
    }

    public void setChromosomeTest() {
        Organism one = new Organism("0000");
        one.setChromosome("1000");
        assertEquals("1000", one.getChromosome());

        Organism two = new Organism("100101");
        two.setChromosome("0");
        assertEquals("0", two.getChromosome());
    }

    public void getChromosomeTest() {
        Organism one = new Organism("0000");
        assertEquals("0000", one.getChromosome());

        Organism two = new Organism("100101");
        assertEquals("100101", two.getChromosome());
    }

    public void fitnessOf1sTest() {
        Organism one = new Organism("0010000", "Num. of 1s");
        assertEquals(1, one.fitnessOf1s());
        assertEquals(1, one.fitness());

        Organism thirteen = new Organism("10100010010000000000001000010001000010010011000000110", "Num. of 1s");
        assertEquals(13, thirteen.fitness());
        assertEquals(13, thirteen.fitnessOf1s());
    }

    public void fitnessConsec1sTest() {
        Organism one = new Organism("0101010101010101010101010101010", "Consec. num. 1s");
        assertEquals(1, one.fitness());
        assertEquals(1, one.fitnessConsec1s());

        Organism five = new Organism("010010111011111001010100100001111000001101", "Consec. num. 1s");
        assertEquals(5, five.fitness());
        assertEquals(5, five.fitnessConsec1s());
    }

    public void fitnessTargetOrganismtest() {
        Organism target = new Organism(
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                "Target Organism");
        assertEquals(100, target.fitness());
        assertEquals(100, target.fitnessTargetOrganism());

        Organism oneOff = new Organism(
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111111",
                "Target Organism");
        assertEquals(99, oneOff.fitness());
        assertEquals(99, oneOff.fitnessTargetOrganism());

        Organism thirteenOff = new Organism(
                "1010001000111000110101101010101101011101001101110110010110011101000010100011110100000000010011111110",
                "Target Organism");
        assertEquals(100 - 13, thirteenOff.fitness());
        assertEquals(100 - 13, thirteenOff.fitnessTargetOrganism());
    }

    // requires: |first| = |second|
    // private int getNumDiffsForStrings(String first, String second) {
    // int diffs = 0;
    // for (int i = 0; i < first.length(); i++) {
    // if (first.charAt(i) != second.charAt(i)) {
    // diffs++;
    // }
    // }

    // return diffs;
    // }

}
