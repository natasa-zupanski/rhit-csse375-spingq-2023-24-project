package mainApp;

public class SelectionTruncation implements SelectionStrategy {

    @Override
    public Organism[] selectFrom(Organism[] orgs) {
        int mutateNum = orgs.length;

        // initializes an empty array for the selected organisms to be placed in.
        Organism[] result = new Organism[mutateNum];

        // selects organisms and adds those to the result, evaluating when odd or even
        // lengths and potential indices occur to avoid index out of bounds errors
        // for (int i = 0; i < mutateNum; i++) {
        // if (mutateNum % 2 == 0) {
        // if (i % 2 == 0) {
        // result[i] = orgs[mutateNum - (i / 2) - 1];
        // } else {
        // result[i] = orgs[mutateNum - ((i + 1) / 2) - 1];
        // }
        // } else {
        // if (i % 2 == 0) {
        // result[i] = orgs[mutateNum - ((i + 1) / 2) - 1];
        // } else {
        // result[i] = orgs[mutateNum - (i / 2) - 1];
        // }
        // }
        // }
        for (int i = 0; i < mutateNum / 2; i++) {
            result[i] = orgs[mutateNum - i - 1];
            result[mutateNum / 2 + i] = orgs[mutateNum - i - 1];
        }
        if (mutateNum % 2 != 0) {
            result[mutateNum - 1] = orgs[mutateNum - 1];
        }

        return result;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.TRUNCATION;
    }

}
