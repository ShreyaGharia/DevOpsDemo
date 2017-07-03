package userInterface;

/**
 * Created by Porwal Brother on 7/3/2017.
 */
import javafx.geometry.Pos;
import javafx.scene.text.Font;

public class FunctionsScreen extends Screen {
    public final int functionsScreenHight = 45;
    public final int functionsScreenFont = 15;
    public final String functionsScreenId = "#func_scr";
    private int functionsScreenInputCounter = 0;

    FunctionsScreen(){
        super();
        setId(functionsScreenId);
        setPrefHeight(functionsScreenHight);
        setFont(new Font(functionsScreenFont));
        setAlignment(Pos.TOP_RIGHT);
    }

    void incrementCounter() {
        if (functionsScreenInputCounter == 0) {
            functionsScreenInputCounter++;
        }
    }

    public int getInputCounter() {
        return functionsScreenInputCounter;
    }

    public void setFunctionsScreenInputCounter(int functionsScreenInputCounter) {
        this.functionsScreenInputCounter = functionsScreenInputCounter;
    }

    public int getFunctionsScreenInputCounter() {
        return functionsScreenInputCounter;
    }
}
