package userInterface;

/**
 * Created by Porwal Brother on 7/3/2017.
 */
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import operations.Helper;

public class MainScreen extends Screen {
    public final int mainScreenHight = 65;
    public final int mainScreenFont = 30;
    public final String mainScreenInitialText = "0";
    public final String mainScreenId = "#m_scr";
    private int inputLength;
    private int mainScreenInputCounter = 0;

    MainScreen(){
        super();
        setId(mainScreenId);
        setFont(new Font(mainScreenFont));
        setPrefHeight(mainScreenHight);
        setAlignment(Pos.CENTER_RIGHT);
        appendText(mainScreenInitialText);
        inputLength = Helper.MAIN_SCREEN_INPUT_LENGTH;
    }

    void incrementCounter() {
        if (mainScreenInputCounter == 0) {
            mainScreenInputCounter++;
        }
    }

    public int getMainScreenInputCounter() {
        return mainScreenInputCounter;
    }

    public int getMainScreenInputLength() {
        return inputLength;
    }

    public void setMainScreenInputLength(int length) {
        this.inputLength = length;
    }

    public void setMainScreenInputCounter(int mainScreenInputCounter) {
        this.mainScreenInputCounter = mainScreenInputCounter;
    }

}
