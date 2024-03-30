package mainApp;

public class SelectionStrategyFactory {

    public static SelectionStrategy getSelectionStrategyOfType(SelectionType type, int percent, int numGens) {
        switch (type) {
            case STABLESTATE:
                return new SelectionStableState(percent);
            case LEARNINGCHANCE:
                return new SelectionLearningChance(numGens);
            case TRUNCATION:
                return new SelectionTruncation();
            case RANKROULETTE:
                return new SelectionRankRoulette();
            case ROULETTEWHEEL:
                return new SelectionRouletteWheel();
            case RANK:
                return new SelectionRank();
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
            default:
                return SelectionType.TRUNCATION;
        }
    }
}
