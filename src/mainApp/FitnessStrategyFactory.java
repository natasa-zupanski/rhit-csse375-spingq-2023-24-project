package mainApp;

public class FitnessStrategyFactory {
    public static FitnessStrategy getFitnessStrategyOfType(FitnessType type) {
        switch (type) {
            case NUMONES:
                return new FitnessNumOfOnes();
            case CONSECONES:
                return new FitnessConsecOnes();
            case TARGETORG:
                return new FitnessTargetOrganism();
            case PRODCONSECONES:
                return new FitnessProdConsecOnes();
            default:
                return null; // this should never happen unless the change isn't made here ; would be best to
                             // replace this with an exception
        }
    }
}
