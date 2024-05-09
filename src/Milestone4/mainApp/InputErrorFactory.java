package mainApp;

public class InputErrorFactory {

    public static String getPopUpTextFromInputType(InputType type, int lower, int upper) {
        switch (type) {
            case GENSIZE:
                return "Generation size must be greater than " + lower + " and less than or equal to " + upper + ".";
            case NONNUMERIC:
                return "Input must be an integer.";
            case NUMGENS:
                return "Number of generations to run must be greater than " + lower + " and less than or equal to "
                        + upper + ".";
            case TERMINATIONFITNESS:
                return "Termination fitness must be greater than " + lower + " and less than or equal to " + upper
                        + ".";
            case MUTATIONRATE:
                return "Mutation rate must be greater than or equal to " + (lower + 1) + " and less than or equal to "
                        + upper + ".";
            case ELITISM:
                return "Elitism percentage must be greater than or equal to " + (lower + 1)
                        + " and less than or equal to " + upper + ".";
            case GENOMELENGTH:
                return "Genome length must be greater than " + lower + " and less than or equal to " + upper + ".";
            default:
                return "Unidentified Input Error.";
        }
    }
}
