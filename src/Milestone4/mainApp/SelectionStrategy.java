package mainApp;

public interface SelectionStrategy {
    abstract Organism[] selectFrom(Organism[] orgs);

    abstract SelectionType getSelectionType();
}
