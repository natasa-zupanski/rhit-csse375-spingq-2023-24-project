package mainApp;

public class SelectionStrategyFactory {

    public static SelectionStrategy getSelectionStrategyOfType(SelectionType type, int percent, int numGens,
            RandomType randomType, String targetOrganism) {
        switch (type) {
            case STABLESTATE:
                return new SelectionStableState(percent);
            case LEARNINGCHANCE:
                return new SelectionLearningChance(numGens, randomType);
            case TRUNCATION:
                return new SelectionTruncation();
            case RANKROULETTE:
                return new SelectionRankRoulette(randomType, targetOrganism);
            case ROULETTEWHEEL:
                return new SelectionRouletteWheel(randomType, targetOrganism);
            case RANK:
                return new SelectionRank();
            case ALTERNATE:
                return new SelectionAlternate();
            default:
                return null;
        }
    }

    public static SelectionType getSelectionTypeFromString(String name) {
        switch (name) {
            case "Truncation":
                return SelectionType.TRUNCATION;
            case "Roulette Wheel":
                return SelectionType.ROULETTEWHEEL;
            case "Rank":
                return SelectionType.RANK;
            case "Stable State":
                return SelectionType.STABLESTATE;
            case "Rank Roulette":
                return SelectionType.RANKROULETTE;
            case "Learning Chance":
                return SelectionType.LEARNINGCHANCE;
            case "Alternate":
                return SelectionType.ALTERNATE;
            default:
                return SelectionType.TRUNCATION;
        }
    }

    public static String getSelectionStringFromType(SelectionType type) {
        switch (type) {
            case TRUNCATION:
                return "Truncation";
            case ROULETTEWHEEL:
                return "Roulette Wheel";
            case RANK:
                return "Rank";
            case RANKROULETTE:
                return "Rank Roulette";
            case STABLESTATE:
                return "Stable State";
            case LEARNINGCHANCE:
                return "Learning Chance";
            case ALTERNATE:
                return "Alternate";
            default:
                return "";
        }

    }

    public static String[] getStrings() {
        String[] methods = { "Truncation", "Roulette Wheel", "Rank", "Rank Roulette", "Stable State",
                "Learning Chance", "Alternate" };
        return methods;
    }
}
