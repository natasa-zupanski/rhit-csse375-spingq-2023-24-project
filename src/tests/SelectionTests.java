package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.*;

import mainApp.FakeSelectionLearningChance;
import mainApp.FitnessType;
import mainApp.Organism;
import mainApp.RandomType;
import mainApp.SelectionRank;
import mainApp.SelectionRankRoulette;
import mainApp.SelectionRouletteWheel;
import mainApp.SelectionStableState;
import mainApp.SelectionStrategy;
import mainApp.SelectionTruncation;
import mainApp.SelectionType;

// public enum SelectionType {
//     TRUNCATION,
//     RANK,
//     RANKROULETTE,
//     ROULETTEWHEEL,
//     STABLESTATE,
//     LEARNINGCHANCE
// }

public class SelectionTests {

    @Test
    public void selectionTruncationTestOne() {
        int genSize = 100;
        // int chromSize = 10;
        String chromOne = "1111111111";
        String chromTwo = "0000000000";
        Organism[] orgs = new Organism[genSize];
        for (int i = 0; i < genSize / 2; i++) {
            orgs[i] = new Organism(chromOne, FitnessType.NUMONES, RandomType.FAKE);
        }
        for (int j = genSize / 2; j < genSize; j++) {
            orgs[j] = new Organism(chromTwo, FitnessType.NUMONES, RandomType.FAKE);
        }
        Arrays.sort(orgs);
        SelectionStrategy selection = new SelectionTruncation();
        assertEquals(SelectionType.TRUNCATION, selection.getSelectionType());
        Organism[] selected = selection.selectFrom(orgs);
        for (int k = 0; k < genSize; k++) {
            assertEquals(10, selected[k].fitness());
            assertEquals(chromOne, selected[k].getChromosome());
        }
    }

    @Test
    public void selectionTruncationTestTwo() {
        int genSize = 99;
        // int chromSize = 10;
        String chromOne = "1111111111";
        String chromTwo = "0000000000";
        Organism[] orgs = new Organism[genSize];
        for (int i = 0; i < genSize / 2 + 1; i++) {
            orgs[i] = new Organism(chromOne, FitnessType.NUMONES, RandomType.FAKE);
        }
        for (int j = genSize / 2 + 1; j < genSize; j++) {
            orgs[j] = new Organism(chromTwo, FitnessType.NUMONES, RandomType.FAKE);
        }
        Arrays.sort(orgs);
        SelectionStrategy selection = new SelectionTruncation();
        assertEquals(SelectionType.TRUNCATION, selection.getSelectionType());
        Organism[] selected = selection.selectFrom(orgs);
        for (int k = 0; k < genSize; k++) {
            assertEquals(10, selected[k].fitness());
            assertEquals(chromOne, selected[k].getChromosome());
        }
    }

    @Test
    public void selectionRankTest() {
        int genSize = 10;
        // int chromSize = 10;
        String chromOne = "1111111111";
        String chromTwo = "0000000000";
        Organism[] orgs = new Organism[genSize];
        for (int i = 0; i < genSize / 2 + 1; i++) {
            orgs[i] = new Organism(chromOne, FitnessType.NUMONES, RandomType.FAKE);
        }
        for (int j = genSize / 2 + 1; j < genSize; j++) {
            orgs[j] = new Organism(chromTwo, FitnessType.NUMONES, RandomType.FAKE);
        }
        Arrays.sort(orgs);
        SelectionStrategy selection = new SelectionRank();
        assertEquals(SelectionType.RANK, selection.getSelectionType());
        Organism[] selected = selection.selectFrom(orgs);

        // what do we expect?
        /*
         * we have 10 organisms. total rank: 10 + 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 10
         * + 15 + 9 + 6 + 9 + 6 = 10 + 15*3 = 10 + 45 = 55
         * rank[0] = 1. by default of how it's down.
         * thus, multiplier = 55 / 4
         * numToRun = 10 * 55 / 4 = 550 / 4 = 137.5 -> 137
         * the we divide by the total --> 137 / 55 --> 2 / (1) = 2
         * first two copy over from first org which has chromOne
         * 55 * 9 / 4 -> 123
         * 123 / 55 -> 2 / 2 = 1;
         * second one copy over from second org which has chromOne
         * -- rest copy over in order, so total...
         * 7 chromOne, 3 chromTwo ??? -- TODO: redo hand math
         */
        Arrays.sort(selected);
        for (int k = 0; k < 3; k++) {
            assertEquals(0, selected[k].fitness());
            assertEquals(chromTwo, selected[k].getChromosome());
        }
        for (int l = 3; l < genSize; l++) {
            assertEquals(10, selected[l].fitness());
            assertEquals(chromOne, selected[l].getChromosome());
        }

    }

    @Test
    public void selectionRankRouletteTest() {
        int genSize = 100;
        // int chromSize = 10;
        String chromOne = "1111111111";
        String chromTwo = "0000000000";
        Organism[] orgs = new Organism[genSize];
        for (int i = 0; i < genSize / 2; i++) {
            orgs[i] = new Organism(chromOne, FitnessType.NUMONES, RandomType.FAKE);
        }
        for (int j = genSize / 2; j < genSize; j++) {
            orgs[j] = new Organism(chromTwo, FitnessType.NUMONES, RandomType.FAKE);
        }
        Arrays.sort(orgs);
        SelectionStrategy selection = new SelectionRankRoulette(RandomType.FAKE); // since this defaults to 0, it should
                                                                                  // return the least ranked organism
                                                                                  // each time for selection
        assertEquals(SelectionType.RANKROULETTE, selection.getSelectionType());
        Organism[] selected = selection.selectFrom(orgs);
        for (int k = 0; k < genSize; k++) {
            assertEquals(0, selected[k].fitness());
            assertEquals(chromTwo, selected[k].getChromosome());
        }
    }

    @Test
    public void selectionRouletteWheelTest() {
        int genSize = 100;
        // int chromSize = 10;
        String chromOne = "1111111111";
        String chromTwo = "0000000000";
        Organism[] orgs = new Organism[genSize];
        for (int i = 0; i < genSize / 2; i++) {
            orgs[i] = new Organism(chromOne, FitnessType.NUMONES, RandomType.FAKE);
        }
        for (int j = genSize / 2; j < genSize; j++) {
            orgs[j] = new Organism(chromTwo, FitnessType.NUMONES, RandomType.FAKE);
        }
        Arrays.sort(orgs);
        SelectionStrategy selection = new SelectionRouletteWheel(RandomType.FAKE); // since this defaults to 0, it
                                                                                   // should
                                                                                   // return the least fit organism
                                                                                   // each time for selection
        assertEquals(SelectionType.ROULETTEWHEEL, selection.getSelectionType());
        Organism[] selected = selection.selectFrom(orgs);
        for (int k = 0; k < genSize; k++) {
            assertEquals(0, selected[k].fitness());
            assertEquals(chromTwo, selected[k].getChromosome());
        }
    }

    @Test
    public void selectionStableStateTestOne() {
        int genSize = 100;
        // int chromSize = 10;
        String chromOne = "1111111111";
        String chromTwo = "0000000000";
        Organism[] orgs = new Organism[genSize];
        for (int i = 0; i < genSize / 2; i++) {
            orgs[i] = new Organism(chromOne, FitnessType.NUMONES, RandomType.FAKE);
        }
        for (int j = genSize / 2; j < genSize; j++) {
            orgs[j] = new Organism(chromTwo, FitnessType.NUMONES, RandomType.FAKE);
        }
        Arrays.sort(orgs);
        SelectionStrategy selection = new SelectionStableState(50);
        assertEquals(SelectionType.STABLESTATE, selection.getSelectionType());
        Organism[] selected = selection.selectFrom(orgs);
        for (int k = 0; k < genSize; k++) {
            assertEquals(10, selected[k].fitness());
            assertEquals(chromOne, selected[k].getChromosome());
        }
    }

    @Test
    public void selectionStableStateTestTwo() {
        int genSize = 100;
        // int chromSize = 10;
        String chromOne = "1111111111";
        String chromTwo = "0000000000";
        Organism[] orgs = new Organism[genSize];
        for (int i = 0; i < 40; i++) {
            orgs[i] = new Organism(chromOne, FitnessType.NUMONES, RandomType.FAKE);
        }
        for (int j = 40; j < genSize; j++) {
            orgs[j] = new Organism(chromTwo, FitnessType.NUMONES, RandomType.FAKE);
        }
        Arrays.sort(orgs);
        SelectionStrategy selection = new SelectionStableState(50);
        assertEquals(SelectionType.STABLESTATE, selection.getSelectionType());
        Organism[] selected = selection.selectFrom(orgs);
        for (int k = 60; k < genSize; k++) {
            assertEquals(10, selected[k].fitness());
            assertEquals(chromOne, selected[k].getChromosome());
        }
        for (int k = 10; k < 50; k++) {
            assertEquals(10, selected[k].fitness());
            assertEquals(chromOne, selected[k].getChromosome());
        }
        for (int k = 0; k < 10; k++) {
            assertEquals(0, selected[k].fitness());
            assertEquals(chromTwo, selected[k].getChromosome());
        }
        for (int k = 50; k < 60; k++) {
            assertEquals(0, selected[k].fitness());
            assertEquals(chromTwo, selected[k].getChromosome());
        }
        for (int j = 0; j < 50; j++) {
            assertEquals(selected[j].fitness(), selected[50 + j].fitness());
            assertEquals(selected[j].getChromosome(), selected[50 + j].getChromosome());
        }
    }

    @Test
    public void selectionStableStateTestThree() {
        int genSize = 100;
        // int chromSize = 10;
        Organism[] orgs = new Organism[genSize];
        for (int i = 0; i < genSize; i++) {
            orgs[i] = new Organism(10, FitnessType.NUMONES, RandomType.FAKE);
        }
        Arrays.sort(orgs);
        SelectionStrategy selection = new SelectionStableState(50);
        assertEquals(SelectionType.STABLESTATE, selection.getSelectionType());
        Organism[] selected = selection.selectFrom(orgs);
        for (int j = 0; j < 50; j++) {
            assertEquals(selected[j].fitness(), selected[50 + j].fitness());
            assertEquals(selected[j].getChromosome(), selected[50 + j].getChromosome());
        }
    }

    @Test
    public void selectionStableStateTestFour() {
        int genSize = 100;
        // int chromSize = 10;
        Organism[] orgs = new Organism[genSize];
        for (int i = 0; i < genSize; i++) {
            orgs[i] = new Organism(10, FitnessType.NUMONES, RandomType.FAKE);
        }
        Arrays.sort(orgs);
        SelectionStrategy selection = new SelectionStableState(10);
        assertEquals(SelectionType.STABLESTATE, selection.getSelectionType());
        Organism[] selected = selection.selectFrom(orgs);
        for (int j = 0; j < 10; j++) {
            assertEquals(selected[j].fitness(), selected[90 + j].fitness());
            assertEquals(selected[j].getChromosome(), selected[90 + j].getChromosome());
        }
        for (int k = 10; k < 90; k++) {
            assertEquals(selected[k].fitness(), orgs[k].fitness());
            assertEquals(selected[k].getChromosome(), orgs[k].getChromosome());
        }
    }

    @Test
    public void selectionLearningChanceTest() {
        int genSize = 100;
        // int chromSize = 10;
        String chromOne = "11111111??";
        String chromTwo = "00000000??";
        Organism[] orgs = new Organism[genSize];
        for (int i = 0; i < genSize / 2; i++) {
            orgs[i] = new Organism(chromOne, FitnessType.NUMONES, RandomType.FAKE);
        }
        for (int j = genSize / 2; j < genSize; j++) {
            orgs[j] = new Organism(chromTwo, FitnessType.NUMONES, RandomType.FAKE);
        }
        Arrays.sort(orgs);
        SelectionStrategy selection = new FakeSelectionLearningChance(1, RandomType.FAKE); // the original learning
                                                                                           // chance loops through a
                                                                                           // hashmap which has no
                                                                                           // apparent predictable
                                                                                           // order. To test what we
                                                                                           // get, we need to create a
                                                                                           // fake selection method that
                                                                                           // simply orders the keySet
                                                                                           // of that hastmap by the
                                                                                           // organism fitness.
        assertEquals(SelectionType.LEARNINGCHANCE, selection.getSelectionType());
        Organism[] selected = selection.selectFrom(orgs);
        for (int k = 0; k < genSize; k++) {
            assertEquals(0, selected[k].fitness());
            assertEquals(chromTwo, selected[k].getChromosome());
        }
    }

}
