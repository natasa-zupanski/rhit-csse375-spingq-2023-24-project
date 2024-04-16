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
            case FIFTYFIFTY:
                return new FitnessFiftyFifty();
            case ONESMINUSZEROS:
                return new FitnessOnesMinusZeros();
            case FAKECONSTANTFIVE:
                return new FitnessFakeConstantFive();
            default:
                return null; // this should never happen unless the change isn't made here ; would be best to
                             // replace this with an exception
        }
    }

    public static FitnessType getTypeFromString(String type) throws IllegalArgumentException {
        switch (type) {
            case "Target Organism":
                return FitnessType.TARGETORG;
            case "Num. of 1s":
                return FitnessType.NUMONES;
            case "Consec. num. of 1s":
                return FitnessType.CONSECONES;
            case "Prod. consec. num. of 1s":
                return FitnessType.PRODCONSECONES;
            case "Fifty Fifty":
                return FitnessType.FIFTYFIFTY;
            case "1s minus 0s":
                return FitnessType.ONESMINUSZEROS;
            case "Fake Five":
                return FitnessType.FAKECONSTANTFIVE;
            case "":
                return FitnessType.LEARNINGCHANCE;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static String[] getStrings() {
        String[] methods = { "Target Organism", "Num. of 1s", "Consec. num. of 1s",
                "Fifty Fifty", "1s minus 0s" };
        return methods;
    }
}
