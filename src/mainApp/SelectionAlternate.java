package mainApp;

public class SelectionAlternate implements SelectionStrategy {

    @Override
    public Organism[] selectFrom(Organism[] orgs) {
        int mutateNum = orgs.length;

        // initializes an empty array for the selected organisms to be placed in.
        Organism[] result = new Organism[mutateNum];

 
        for (int i = 0, j = 0; i < mutateNum ; i+=2, j++) {
            result[j] = new Organism(orgs[mutateNum - i - 1]);
            result[mutateNum / 2 + j] = new Organism(orgs[mutateNum - i - 1]);
        }
        if (mutateNum % 2 != 0) {
            result[mutateNum - 1] = new Organism(orgs[mutateNum - 1]);
        }

        return result;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.ALTERNATE;
    }

}
