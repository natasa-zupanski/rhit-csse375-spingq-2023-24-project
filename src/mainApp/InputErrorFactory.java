package mainApp;

public class InputErrorFactory {

    public static String getPopUpTextFromInputType(InputType type, int lower, int upper) {
        switch (type) {
            case GENSIZE:
                return "Generation Size must be greater than " + lower + " and less than or equal to " + upper + ".";
            case NONNUMERIC:
                return "Input must be a number.";
            default:
                return "Unidentified Input Error.";
        }
    }
}
