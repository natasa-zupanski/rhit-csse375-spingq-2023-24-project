package mainApp;

import java.util.Arrays;

public class SelectionStableState implements SelectionStrategy {
    private int percent;

    public SelectionStableState(int percent) {
        this.percent = percent;
    }

    @Override
    public Organism[] selectFrom(Organism[] orgs) {
        // the number of organisms to be selected from the top and replaced from the
        // bottom
        int numToCopy = (orgs.length * percent) / 100;

        // creates temporary copy of the orginial organisms to be changed and sorts this
        Organism[] temp = orgs;
        Arrays.sort(temp);

        // finds the organisms to be copied and used from the top to replace the bottom
        Organism[] toCopy = Arrays.copyOfRange(temp, temp.length - numToCopy, temp.length);

        // replaced the bottom with the top organisms
        for (int index = 0; index < numToCopy; index++) {
            temp[index] = new Organism(toCopy[index].getChromosome(), orgs[index].getFitnessType());
        }

        return temp;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.STABLESTATE;
    }

}
