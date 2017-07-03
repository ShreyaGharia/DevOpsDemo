package operations;

/**
 * Created by Porwal Brother on 7/3/2017.
 */
import java.text.DecimalFormat;

public class Helper {
    public final static int MAIN_SCREEN_INPUT_LENGTH = 8;
    private final static String ERROR_MESSAGE = "ERROR";
    private final static int NUMBER_OF_ZEROS_AFTER_DOT = 6;

    public static String changeResult(double result) {
        if (String.valueOf(result).equals("NaN")) {
            return ERROR_MESSAGE;
        }
        DecimalFormat formatter = new DecimalFormat(
                createStringForFormatter(NUMBER_OF_ZEROS_AFTER_DOT));
        String resultToString = String.valueOf(formatter.format(result));
        String[] resultMassif = resultToString.split("\\.");
        String wholePart = resultMassif[0];
        String decimalPart = resultMassif[1];
        int wholePartLength = wholePart.length();
        int decimalPartLength = decimalPart.length();
        if (wholePartLength > MAIN_SCREEN_INPUT_LENGTH) {
            resultToString = "ERROR";
        } else {
            if (Integer.parseInt(decimalPart) == 0) {
                resultToString = wholePart;
            }
        }
        if (Integer.parseInt(decimalPart) != 0
                && wholePartLength + 1 < MAIN_SCREEN_INPUT_LENGTH) {
            decimalPartLength = shortenDecimalPartLengthAndReturnIt(wholePart,
                    decimalPart);
            formatter = new DecimalFormat(
                    createStringForFormatter(decimalPartLength));
            resultToString = String.valueOf(formatter.format(Double
                    .parseDouble(wholePart + "." + decimalPart)));
        } else if (wholePartLength + 1 == MAIN_SCREEN_INPUT_LENGTH) {
            formatter = new DecimalFormat(createStringForFormatter(0));
            resultToString = String.valueOf(formatter.format(result));
            resultToString = resultToString.substring(0,
                    resultToString.length() - 1);
        }
        return resultToString;
    }

    private static int shortenDecimalPartLengthAndReturnIt(String wholePart,
                                                           String decimalPart) {
        decimalPart = removeZerosFromEnd(decimalPart);
        int decimalPartLength = decimalPart.length();
        if (wholePart.length() + decimalPart.length() + 1 > MAIN_SCREEN_INPUT_LENGTH) {
            decimalPartLength = calculateDecimalPartLengthWhenValueIsTooLong(
                    wholePart, decimalPart);
        }
        return decimalPartLength;
    }

    protected static int calculateDecimalPartLengthWhenValueIsTooLong(
            String wholePart, String decimalPart) {
        int difference = wholePart.length() + decimalPart.length() + 1
                - MAIN_SCREEN_INPUT_LENGTH;
        int decimalPartLength = decimalPart.length() - difference;
        return decimalPartLength;
    }

    protected static String removeZerosFromEnd(String number) {
        for (int i = number.length() - 1; i >= 0; i--) {
            if (number.charAt(i) == '0') {
                number = number.substring(0, number.length() - 1);
            } else {
                break;
            }
        }
        return number;
    }

    private static String createStringForFormatter(int numberOfZeroesAfterDot) {
        String template = "0.";
        for (int i = 0; i < numberOfZeroesAfterDot; i++) {
            template += "0";
        }
        return template;
    }

    public static String changeElement(double element) {
        DecimalFormat formatter = new DecimalFormat(
                createStringForFormatter(NUMBER_OF_ZEROS_AFTER_DOT));
        String elementToString = String.valueOf(formatter.format(element));
        String[] elementMassif = elementToString.split("\\.");
        int decimalPartLength = elementMassif[1].length();
        if (element % 1 == 0) {
            elementToString = elementMassif[0];
        } else {
            elementMassif[1] = removeZerosFromEnd(elementMassif[1]);
            decimalPartLength = elementMassif[1].length();
            formatter = new DecimalFormat(
                    createStringForFormatter(decimalPartLength));
            elementToString = String.valueOf(formatter.format(Double
                    .parseDouble(elementMassif[0] + "." + elementMassif[1])));
        }
        return elementToString;
    }
}