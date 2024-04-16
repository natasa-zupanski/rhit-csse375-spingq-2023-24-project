package tests;

import mainApp.FitnessConsecOnes;
import mainApp.FitnessFakeConstantFive;
import mainApp.FitnessFiftyFifty;
import mainApp.FitnessLearningChance;
import mainApp.FitnessNumOfOnes;
import mainApp.FitnessOnesMinusZeros;
import mainApp.FitnessProdConsecOnes;
import mainApp.FitnessStrategy;
import mainApp.FitnessTargetOrganism;
import mainApp.FitnessType;

import static org.junit.Assert.assertEquals;

import org.junit.*;

// public enum FitnessType {
//     NUMONES,
//     CONSECONES,
//     TARGETORG,
//     PRODCONSECONES,
//     LEARNINGCHANCE,
//     FIFTYFIFTY,
//     ONESMINUSZEROS,
//     FAKECONSTANTFIVE
// }

public class FitnessTests {

    @Test
    public void fitnessOf1sTest() {
        FitnessStrategy fitness = new FitnessNumOfOnes();
        assertEquals(FitnessType.NUMONES, fitness.getFitnessType());
        assertEquals(3, fitness.getFitness("111000000"));
        assertEquals(3, fitness.getFitness("00110001000000"));
        assertEquals(4, fitness.getFitness("111000010"));
    }

    @Test
    public void fitnessConsec1sTest() {
        FitnessStrategy fitness = new FitnessConsecOnes();
        assertEquals(FitnessType.CONSECONES, fitness.getFitnessType());
        assertEquals(3, fitness.getFitness("111000000"));
        assertEquals(2, fitness.getFitness("00110001000000"));
        assertEquals(4, fitness.getFitness("1110000101111"));
    }

    @Test
    public void fitnessTargetOrganismTest() throws Exception {
        FitnessStrategy fitness = new FitnessTargetOrganism();
        assertEquals(FitnessType.TARGETORG, fitness.getFitnessType());
        assertEquals(100, fitness.getFitness(
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111110"));
        assertEquals(99, fitness.getFitness(
                "1010000000101001110001101110101001000101100101010110010110011001000010100011110101000000010011111111"));
        assertEquals(100 - 13, fitness.getFitness(
                "1010001000111000110101101010101101011101001101110110010110011101000010100011110100000000010011111110"));
    }

    @Test
    public void fitnessProdConsec1stest() {
        FitnessStrategy fitness = new FitnessProdConsecOnes();
        assertEquals(FitnessType.PRODCONSECONES, fitness.getFitnessType());
        assertEquals(3, fitness.getFitness("111000000"));
        assertEquals(2, fitness.getFitness("00110001000000"));
        assertEquals(12, fitness.getFitness("1110000101111"));
        assertEquals(0, fitness.getFitness("000000"));
    }

    @Test
    public void fitnessLearningChanceTest() throws Exception {
        FitnessStrategy fitness = new FitnessLearningChance(10, 1);
        assertEquals(FitnessType.LEARNINGCHANCE, fitness.getFitnessType());
        // assertEquals(3, fitness.getFitness("111000000"));
        // assertEquals(3, fitness.getFitness("00110001000000"));
        // assertEquals(4, fitness.getFitness("111000010"));
        throw new Exception("Not yet implemented");
    }

    @Test
    public void fitnessFiftyFiftyTest() {
        FitnessStrategy fitness = new FitnessFiftyFifty();
        assertEquals(FitnessType.FIFTYFIFTY, fitness.getFitnessType());
        assertEquals(6, fitness.getFitness("111000"));
        assertEquals(12, fitness.getFitness("1101111011001100"));
        assertEquals(0, fitness.getFitness("0000"));
    }

    @Test
    public void fitnessOnesMinusZerosTest() {
        FitnessStrategy fitness = new FitnessOnesMinusZeros();
        assertEquals(FitnessType.ONESMINUSZEROS, fitness.getFitnessType());
        assertEquals(0, fitness.getFitness("111000000"));
        assertEquals(1, fitness.getFitness("111110000"));
        assertEquals(4, fitness.getFitness("11100011001111"));
    }

    @Test
    public void fitnessFakeConstantFiveTest() {
        FitnessStrategy fitness = new FitnessFakeConstantFive();
        assertEquals(FitnessType.FAKECONSTANTFIVE, fitness.getFitnessType());
        assertEquals(5, fitness.getFitness("111000000"));
        assertEquals(5, fitness.getFitness("00110001000000"));
        assertEquals(5, fitness.getFitness("111000010"));
    }
}
