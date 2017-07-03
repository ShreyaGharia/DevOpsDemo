package operations;

/**
 * Created by Porwal Brother on 7/3/2017.
 */
import java.io.IOException;

public abstract class Operation {
    protected SavingHistory sh;
    protected double firstElement = 0;
    protected double secondElement = 0;
    protected double result = 0;
    protected String toFile = "";
    private boolean isNeccessaryToSaveHistory = false;
    protected String resultToString = "";
    protected String firstElementToString = "";
    protected String secondElementToString = "";

    protected final static String EQUALS_SIGN = " = ";
    protected final static String NEW_LINE = "\n";
    protected final static String COLON = ": ";
    protected static final double BAD_RESULT = Double.NaN;

    Operation() {

    }

    Operation(SavingHistory sh) {
        this.sh = sh;
    }

    abstract protected void doOperation();

    public void doOperationAndShowInformation() throws IOException {
        isNeccessaryToSaveHistory = sh.getSaveHistory();
        doOperation();
        showInformationToUser();
    }

    public void showInformationToUser() throws IOException {
        printToConsole();
        if (isNeccessaryToSaveHistory) {
            sendInformationToFile();
        }
    }

    public void sendInformationToFile() throws IOException {

        resultToString = Helper.changeResult(result);

        firstElementToString = Helper.changeElement(firstElement);
        secondElementToString = Helper.changeElement(secondElement);
        toFile = toString();
        sh.writeToFile(toFile);
    }

    public void printToConsole() {
    }

    public double getFirstElement() {
        return firstElement;
    }

    public void setFirstElement(double firstElement) {
        this.firstElement = firstElement;
    }

    public double getSecondElement() {
        return secondElement;
    }

    public void setSecondElement(double secondElement) {
        this.secondElement = secondElement;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public boolean isNeccessaryToSaveHistory() {
        return isNeccessaryToSaveHistory;
    }

    public void setNeccessaryToSaveHistory(boolean isNeccessaryToSaveHistory) {
        this.isNeccessaryToSaveHistory = isNeccessaryToSaveHistory;
    }



    public void setToFile(String toFile) {
        this.toFile = toFile;
    }

    public String getToFile() {
        return toFile;
    }

    public String getResultToString() {
        return resultToString;
    }

    public void setResultToString(String resultToString) {
        this.resultToString = resultToString;
    }

    public String getFirstElementToString() {
        return firstElementToString;
    }

    public String getSecondElementToString() {
        return secondElementToString;
    }

    public void setFirstElementToString(String firstElementToString) {
        this.firstElementToString = firstElementToString;
    }

    public void setSecondElementToString(String secondElementToString) {
        this.secondElementToString = secondElementToString;
    }
}
