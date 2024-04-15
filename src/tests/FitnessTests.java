package tests;

import mainApp.FitnessConsecOnes;
import mainApp.FitnessNumOfOnes;
import mainApp.FitnessStrategy;
import mainApp.FitnessType;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class FitnessTests {

    @Test
    public void FitnessOf1sTest() {
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

}
