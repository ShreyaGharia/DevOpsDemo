package userInterface;

/**
 * Created by Porwal Brother on 7/3/2017.
 */
import java.io.IOException;

import javafx.scene.control.Button;

public class CalculatorButton extends Button {
    Calculator calculator;
    public final int buttonFontSize = 17;
    public final int buttonBackgroundRadius = 2;
    public final int buttonBackgroundInsets = 3;
    public final String buttonID = "#btn";


    CalculatorButton(String function, Calculator calculator) throws IOException{
        super(function);
        this.calculator = calculator;
        setId(buttonID + "_" + function);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setStyle("-fx-font-size: " + buttonFontSize + ";"
                + "-fx-background-radius: " + buttonBackgroundRadius + ";"
                + "-fx-background-insets: " + buttonBackgroundInsets + ";");
        //setOnAction(e -> calculator.performTask(getText()));

    }

}