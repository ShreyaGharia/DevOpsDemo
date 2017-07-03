package userInterface;

/**
 * Created by Porwal Brother on 7/3/2017.
 */
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;

public class BoardWithButtons extends TilePane {
    Calculator calculator;
    public final int verticalGap = 8;
    public final int horizontalGap = 8;
    public final int numberOfColumns = 4;
    public final String boardId = "#btn_board";
    public final String[][] calculatorButtons = {

            { "\u207F\u221A", "x\u207F", "\u221A", "x\u00B2" },
            { "%", "1/x", "C", "\u00F7" }, { "7", "8", "9", "\u00D7" },
            { "4", "5", "6", "-" }, { "1", "2", "3", "+" },
            { "=", "0", "\u00B7", "\u00B1" }, { "M+", "M-", "A", "B" },

    };

    BoardWithButtons(Calculator calculator) throws IOException{
        super();
        this.calculator = calculator;
        setId(boardId);
        setAlignment(Pos.BOTTOM_CENTER);
        setVgap(verticalGap);
        setHgap(horizontalGap);
        setPrefColumns(numberOfColumns);
        for (String[] buttonsRow : calculatorButtons) {
            for (String buttonName : buttonsRow) {
                if (buttonName == "A" || buttonName == "B") {
                    continue;
                }
                getChildren().add(new CalculatorButton(buttonName,calculator));
            }
        }
    }
}