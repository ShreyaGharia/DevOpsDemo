package operations;

/**
 * Created by Porwal Brother on 7/3/2017.
 */
public class Subtraction extends Operation {
    protected static final String OPERATION_NAME = "subtraction";
    protected static final String SIGN = " - ";

    public Subtraction(SavingHistory sh) {
        super(sh);
    }

    @Override
    public void doOperation() {
        result = firstElement - secondElement;
    }

    @Override
    public String toString() {
        if (secondElement < 0) {
            toFile = OPERATION_NAME + COLON + firstElementToString + SIGN + "("
                    + secondElementToString + ")" + EQUALS_SIGN + resultToString + NEW_LINE;
        } else {
            toFile = OPERATION_NAME + COLON + firstElementToString + SIGN
                    + secondElementToString + EQUALS_SIGN + resultToString + NEW_LINE;
        }
        return toFile;
    }
}