package mainApp;

public class FitnessStrategyFactory {
    public static FitnessStrategy getFitnessStrategyOfType(FitnessType type, Integer numGens, Integer constantFitness) {
        switch (type) {
            case NUMONES:
                return new FitnessNumOfOnes();
            case CONSECONES:
                return new FitnessConsecOnes();
            case TARGETORG:
                return new FitnessTargetOrganism();
            case PRODCONSECONES:
                return new FitnessProdConsecOnes();
            case LEARNINGCHANCE:
                return new FitnessLearningChance(numGens, constantFitness);
            default:
                return null; // this should never happen unless the change isn't made here ; would be best to
                             // replace this with an exception
        }
    }

    public static FitnessType getTypeFromString(String type) {
        switch (type) {
            case "Target Organism":
                return FitnessType.TARGETORG;
            case "Num. of 1s":
                return FitnessType.NUMONES;
            case "Consec. num. of 1s":
                return FitnessType.CONSECONES;
            case "":
                return FitnessType.LEARNINGCHANCE;
            default:
                return FitnessType.NUMONES;
        }
    }

    public static String[] getStrings() {
        String[] methods = { "Target Organism", "Num. of 1s", "Consec. num. of 1s" };
        return methods;
    }
}
