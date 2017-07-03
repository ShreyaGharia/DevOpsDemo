package userInterface;

/**
 * Created by Porwal Brother on 7/3/2017.
 */
import javafx.geometry.Pos;
import javafx.scene.text.Font;

public class MemoryScreen extends Screen {
    public final int memoryScreenHight = 45;
    public final int memoryScreenFont = 15;
    public final String memoryScreenBackgroundColor = "honeydew";
    public final int memoryScreenBackgroundRadius = 5;
    public final String memoryScreenTextColor = "red";
    public final String memoryScreenInitialText = "M-";
    public final String memoryScreenId = "#mem_scr";

    MemoryScreen() {
        super();
        setId(memoryScreenId);
        setPrefHeight(memoryScreenHight);
        setFont(new Font(memoryScreenFont));
        setStyle("-fx-background-color: " + memoryScreenBackgroundColor + ";"
                + "-fx-background-radius: " + memoryScreenBackgroundRadius
                + ";" + "-fx-text-fill: " + memoryScreenTextColor );
        setAlignment(Pos.BOTTOM_LEFT);
        appendText(memoryScreenInitialText);
    }
}