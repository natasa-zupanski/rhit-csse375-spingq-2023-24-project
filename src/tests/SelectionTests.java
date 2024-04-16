package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.*;

import mainApp.FitnessType;
import mainApp.Organism;
import mainApp.RandomType;
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
}
