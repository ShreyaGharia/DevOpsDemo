package userInterface;

/**
 * Created by Porwal Brother on 7/3/2017.
 */
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import operations.Additiona;
import operations.SavingHistory;
import operations.Subtraction;
import operations.Helper;


public class Calculator extends Application {
    protected String[] patterns = { "\\s\\+\\s", "\\s\\-\\s", "\\s\u00D7\\s",
            "\\s\u00F7\\s", "\\s\u207F\u221A\\s", "\\s\\*\\*\\s" };
    protected int operationsCounter = 0;
    protected boolean isFirstElement = true;
    protected String prepareForInput = "";
    protected boolean appendAtTheBeginning = false;
    protected boolean clearScreenBeforeNextInput = false;
    protected String input = "";
    private SavingHistory savingHistory = new SavingHistory();
    private Additiona addition = new Additiona(savingHistory);
    private Subtraction subtraction = new Subtraction(savingHistory);


    protected StackPane root;
    protected FunctionsScreen functionsScreen;
    protected MainScreen mainScreen;
    protected MemoryScreen memoryScreen;
    protected BoardWithButtons buttons;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new StackPane();
        functionsScreen = new FunctionsScreen();
        mainScreen = new MainScreen();
        memoryScreen = new MemoryScreen();
        buttons = new BoardWithButtons(this);

        root.setStyle("-fx-background-color:silver;-fx-font-size: 30px;");

        root.setMargin(functionsScreen, new Insets(20, 15, 20, 15));
        root.setMargin(mainScreen, new Insets(45, 15, 20, 15));
        root.setMargin(memoryScreen, new Insets(90, 15, 20, 15));
        root.setMargin(buttons, new Insets(20, 15, 20, 15));

        root.setAlignment(functionsScreen, Pos.TOP_CENTER);
        root.setAlignment(mainScreen, Pos.TOP_CENTER);
        root.setAlignment(memoryScreen, Pos.TOP_CENTER);
        root.getChildren().addAll(functionsScreen, mainScreen, memoryScreen,
                buttons);
        primaryStage.setTitle("LOLA");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 248, 470));
        primaryStage.show();

    }

    public void performTask(String task) {

        if (task == "C") {
            clearScreenWhenCIsPushed();
        }

        if (task == "M+" || task == "M-") {
            performMemoryTask(task);
        }

        if (task == "=" || task == "+" || task == "-" || task == "\u00D7"
                || task == "\u00F7" || task == "x\u207F"
                || task == "\u207F\u221A") {

            try {
                performFunctionTask(task);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (task == "1" || task == "2" || task == "3" || task == "4"
                || task == "5" || task == "6" || task == "7" || task == "8"
                || task == "9" || task == "0" || task == "\u00B1"
                || task == "\u00B7") {

            performNumberTask(task);
        }

    }

    public void clearScreenWhenCIsPushed() {
        clearScreens("totally");
        addText();
        renewVariables("totally");
    }

    public void clearScreens(String degree) {
        if (degree == "totally") {
            memoryScreen.clear();
        }
        functionsScreen.clear();
        mainScreen.clear();
    }

    public void addText() {
        mainScreen.appendText("0");
        memoryScreen.appendText("M-");
    }

    public void renewVariables(String degree) {
        if (degree == "totally") {
            savingHistory.setSaveHistory(false);
        }
        input = "";
        mainScreen.setMainScreenInputCounter(0);
        functionsScreen.setFunctionsScreenInputCounter(0);
        mainScreen.setMainScreenInputLength(Helper.MAIN_SCREEN_INPUT_LENGTH);
        isFirstElement = true;
        prepareForInput = "";
        appendAtTheBeginning = false;
    }

    public void performMemoryTask(String task) {
        if (task == "M+") {
            savingHistory.setSaveHistory(true);
        }

        if (task == "M-") {
            savingHistory.setSaveHistory(false);
        }
        memoryScreen.setText(task);
    }

    public void performFunctionTask(String task) throws IOException {
        if (task == "=" || task == "\u207F\u221A" || task == "\u00D7"
                || task == "+" || task == "-" || task == "\u00F7"
                || task == "x\u207F") {
            if (prepareForInput == "") {
                return;
            }
        }

        input += prepareForInput;
        prepareForInput = "";
        String textOnScreen = mainScreen.getText();
        String textOnFunctionsScreen = functionsScreen.getText();
        operationsCounter++;

        if (task == "\u207F\u221A" || task == "\u00D7"
                || task == "+" || task == "-" || task == "\u00F7"
                || task == "x\u207F") {
            if (operationsCounter == 2) {
                clearMainScreen();
                functionsScreen.appendText(textOnScreen + " = ");
                isFirstElement = true;
                clearScreenBeforeNextInput = true;
                doMath(input);
                return;
            }
        }

        if (task == "\u207F\u221A") {
            appendAtTheBeginning = true;
            if (textOnScreen.charAt(0) == '-') {
                textOnScreen = "(" + textOnScreen + ")";
            }
            textOnScreen = "(?)" + task.substring(1) + textOnScreen;
        } else if (task == "x\u207F") {
            textOnScreen = textOnScreen + " ** ";
        } else {
            if (appendAtTheBeginning == true) {
                functionsScreen.clear();
                if (textOnScreen.charAt(0) == '-') {
                    textOnScreen = "(" + textOnScreen + ")";
                }
                textOnScreen = textOnScreen
                        + textOnFunctionsScreen.substring(3);
                appendAtTheBeginning = false;
            }
            if (isFirstElement == false && textOnScreen.charAt(0) == '-') {
                textOnScreen = "(" + textOnScreen + ")";
            }
            textOnScreen += " " + task + " ";
        }

        clearMainScreen();

        functionsScreen.appendText(textOnScreen);
        if (isFirstElement == true) {
            isFirstElement = false;
        }

        if (task != "=") {
            if (task == "x\u207F") {
                this.input += " ** ";
            } else {
                input += " " + task + " ";
            }
            clearScreenBeforeNextInput = false;
        } else if (task == "=") {
            isFirstElement = true;
            clearScreenBeforeNextInput = true;
            doMath(input);
        }
    }

    public void clearMainScreen() {
        mainScreen.clear();
        mainScreen.setMainScreenInputLength(Helper.MAIN_SCREEN_INPUT_LENGTH);
    }

    public void doMath(String input) throws IOException {
        String result = "";
        String pattern = "";
        String[] elements = new String[2];
        Pattern r = null;
        Matcher m = null;
        for (String p : patterns) {
            r = Pattern.compile(p);
            m = r.matcher(input);
            if (m.find()) {
                pattern = p;
                elements = input.split(p);
                break;
            }
        }

        if (pattern == "\\s\\+\\s") {
            addition.setFirstElement(Double.parseDouble(elements[0]));
            addition.setSecondElement(Double.parseDouble(elements[1]));
            addition.doOperationAndShowInformation();
            result = Helper.changeResult(addition.getResult());
        }

        if (pattern == "\\s\\-\\s") {
            subtraction.setFirstElement(Double.parseDouble(elements[0]));
            subtraction.setSecondElement(Double.parseDouble(elements[1]));
            subtraction.doOperationAndShowInformation();
            result = Helper.changeResult(subtraction.getResult());
        }

        mainScreen.appendText(result);
    }

    public void performNumberTask(String task) {
        String textOnScreen = mainScreen.getText();
        if(task.equals("0")){
            if(textOnScreen.equals("0")){
                return;
            }
        }
        if (clearScreenBeforeNextInput == true) {
            clearScreen();
        }

        if (mainScreen.getMainScreenInputLength() == 0) {
            return;
        }

        if (mainScreen.getMainScreenInputCounter() == 0) {
            if (task == "\u00B1") {
                return;
            } else {
                mainScreen.clear();
            }
        }
        if (task == "\u00B7") {
            addDotToMainScreen();
        } else if (task == "\u00B1") {
            WhenPlusMinusIsPushed();
        } else {
            prepareForInput += task;
            mainScreen.appendText(task);
            mainScreen.incrementCounter();
            mainScreen.setMainScreenInputLength(mainScreen
                    .getMainScreenInputLength() - 1);
            clearScreenBeforeNextInput = false;
            return;
        }
    }

    public void clearScreen() {
        clearScreens("partly");
        renewVariables("partly");
    }

    public void addDotToMainScreen() {
        String textOnScreen = mainScreen.getText();
        if (textOnScreen.length() > 0) {
            addDotToMainScreenWhenScreenHasText(textOnScreen);
        } else {
            addDotToMainScreenWhenScreenIsBlank();
        }
    }

    public void addDotToMainScreenWhenScreenHasText(String textOnScreen) {
        if (textOnScreen.indexOf('.') == -1) {
            mainScreen.appendText(".");
            prepareForInput += ".";
            clearScreenBeforeNextInput = false;
        } else {
            return;
        }
    }

    public void addDotToMainScreenWhenScreenIsBlank() {
        mainScreen.clear();
        mainScreen.appendText("0.");
        prepareForInput += "0.";
        mainScreen.incrementCounter();
        mainScreen.setMainScreenInputLength(mainScreen
                .getMainScreenInputLength() - 2);
        clearScreenBeforeNextInput = false;
    }

    public void WhenPlusMinusIsPushed() {
        String textOnScreen = mainScreen.getText();
        if (textOnScreen.charAt(0) == '-') {
            changeNegativeToPositive(textOnScreen);
        } else {
            changePositiveToNegative(textOnScreen);
        }
    }

    public void changePositiveToNegative(String textOnScreen) {
        textOnScreen = "-" + textOnScreen;

        prepareForInput = textOnScreen;
        mainScreen.clear();
        mainScreen.appendText(textOnScreen);
        mainScreen.setMainScreenInputLength(mainScreen
                .getMainScreenInputLength() + 1);
    }

    public void changeNegativeToPositive(String textOnScreen) {
        textOnScreen = textOnScreen.substring(1);
        prepareForInput = textOnScreen;
        mainScreen.clear();
        mainScreen.appendText(textOnScreen);
        mainScreen.setMainScreenInputLength(mainScreen
                .getMainScreenInputLength() - 1);
    }

    public boolean isFirstElement() {
        return isFirstElement;
    }

    public String getPrepareForInput() {
        return prepareForInput;
    }


    public boolean isAppendAtTheBeginning() {
        return appendAtTheBeginning;
    }

    public void setAppendAtTheBeginning(boolean appendAtTheBeginning) {
        this.appendAtTheBeginning = appendAtTheBeginning;
    }

    public boolean isClearScreenBeforeNextInput() {
        return clearScreenBeforeNextInput;
    }

    public String getInput() {
        return input;
    }


    public FunctionsScreen getFunctionsScreen() {
        return functionsScreen;
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    public MemoryScreen getMemoryScreen() {
        return memoryScreen;
    }


    public SavingHistory getSavingHistory() {
        return savingHistory;
    }

}
