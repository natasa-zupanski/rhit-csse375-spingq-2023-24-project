package tests;

import mainApp.FitnessType;
import mainApp.Organism;
import mainApp.RandomType;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.*;

public class OrganismTests {

    @Test
    public void lengthTest() {
        Organism twenty = new Organism("00000000001111111111", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        int length = twenty.length();
        assertEquals(20, length);

        Organism hundred = new Organism(100, FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110");
        // assert length = 100
        assertEquals(100, hundred.length());

        Organism thirteen = new Organism("0001111100000", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(13, thirteen.length());

    }

    @Test
    public void numOf0sTest() {
        Organism one = new Organism("101111", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(1, one.getOrganismUtilites().numOf0s());

        Organism thirteen = new Organism("111010011111001110101011111010110010111", FitnessType.NUMONES,
                RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(13, thirteen.getOrganismUtilites().numOf0s());
    }

    @Test
    public void numOf1sTest() {
        Organism one = new Organism("001000", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(1, one.getOrganismUtilites().numOf1s());

        Organism thirteen = new Organism("000010010011001010101010000010000010101", FitnessType.NUMONES,
                RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(13, thirteen.getOrganismUtilites().numOf1s());
    }

    @Test
    public void flipAlleleTest() {
        try {
            Method flipAllele = Organism.class.getDeclaredMethod("flipAllele", int.class);
            flipAllele.setAccessible(true);
            Organism one = new Organism("111111111", FitnessType.NUMONES, RandomType.FAKE,
                    "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                    0, 0);
            int integerOne = 1;
            flipAllele.invoke(one, integerOne);
            assertEquals('0', one.getChromosome().charAt(1));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            assertEquals(true, false);
        } catch (SecurityException e) {
            e.printStackTrace();
            assertEquals(true, false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            assertEquals(true, false);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            assertEquals(true, false);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            assertEquals(true, false);
        }
    }

    @Test
    public void toIntArrTest() {
        Organism four = new Organism("0101", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        int[][] expected = { { 0, 1 }, { 0, 1 } };
        int[][] fourArr = four.getOrganismVisualization().toIntAr();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(expected[i][j], fourArr[i][j]);
            }
        }
    }

    @Test
    public void mutatePercentTest() {
        Organism organism = new Organism("0101", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        organism.mutatePercent(1);
        assertEquals("1010", organism.getChromosome());
        Organism organism2 = new Organism("0101", FitnessType.NUMONES, RandomType.FAKEPOPULATION,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        organism2.mutatePercent(1);
        assertEquals("0101", organism2.getChromosome());
        Organism organism3 = new Organism("1111", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        organism3.mutatePercent(1);
        assertEquals("0000", organism3.getChromosome());
    }

    @Test
    public void mutateTest() {

        Organism one = new Organism("1111", FitnessType.NUMONES, RandomType.FAKEPOPULATION,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        one.mutate(1);
        String after = one.getChromosome();
        assertEquals("1111", after);

        Organism one2 = new Organism("1001", FitnessType.NUMONES, RandomType.FAKEPOPULATION,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        one2.mutate(1);
        after = one2.getChromosome();
        assertEquals("1001", after);

        Organism four = new Organism("1111", FitnessType.NUMONES, RandomType.FAKEPOPULATION,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        four.mutate(4);
        after = four.getChromosome();
        assertEquals("0000", after);

        Organism four2 = new Organism("1001", FitnessType.NUMONES, RandomType.FAKEPOPULATION,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        four2.mutate(4);
        after = four2.getChromosome();
        assertEquals("0110", after);
    }

    @Test
    public void getChromosomeTest() {
        Organism one = new Organism("0000", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals("0000", one.getChromosome());

        Organism two = new Organism("100101", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals("100101", two.getChromosome());
    }

    @Test
    public void fitnessOf1sTest() {
        Organism one = new Organism("0010000", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        // assertEquals(1, one.fitnessOf1s());
        assertEquals(1, one.fitness());

        Organism thirteen = new Organism("10100010010000000000001000010001000010010011000000110", FitnessType.NUMONES,
                RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(13, thirteen.fitness());
        // assertEquals(13, thirteen.fitnessOf1s());
    }

    @Test
    public void fitnessConsec1sTest() {
        Organism one = new Organism("0101010101010101010101010101010", FitnessType.CONSECONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(1, one.fitness());
        // assertEquals(1, one.fitnessConsec1s());

        Organism five = new Organism("010010111011111001010100100001111000001101", FitnessType.CONSECONES,
                RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(5, five.fitness());
        // assertEquals(5, five.fitnessConsec1s());
    }

    @Test
    public void fitnessTargetOrganismtest() {
        Organism target = new Organism(
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                FitnessType.TARGETORG, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(100, target.fitness());
        // assertEquals(100, target.fitnessTargetOrganism());

        Organism oneOff = new Organism(
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111111",
                FitnessType.TARGETORG, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(99, oneOff.fitness());
        // assertEquals(99, oneOff.fitnessTargetOrganism());

        Organism thirteenOff = new Organism(
                "1010001000111000110101101010101101011101001101110110010110011101000010100011110100000000010011111110",
                FitnessType.TARGETORG, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(100 - 13, thirteenOff.fitness());
        // assertEquals(100 - 13, thirteenOff.fitnessTargetOrganism());
    }

    @Test
    public void fitnessProdConsecOnesTest() {
        Organism one = new Organism("010101010101", FitnessType.PRODCONSECONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(1, one.fitness());

        Organism six = new Organism("1100001000111", FitnessType.PRODCONSECONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(6, six.fitness());
    }

    @Test
    public void flipAlleleCoordTest() {
        Organism one = new Organism("111111111", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        one.flipAlleleCoord(2, 1, 420, 360);
        assertEquals("011111111", one.getChromosome());
        one.flipAlleleCoord(200, 200, 420, 360);
        assertEquals("011101111", one.getChromosome());
    }

    @Test
    public void length() {
        Organism one = new Organism("111111111", FitnessType.NUMONES, RandomType.FAKE,
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110",
                0, 0);
        assertEquals(9, one.length());
        one.setChromosome("0000111110110");
        assertEquals(13, one.length());
    }
}
