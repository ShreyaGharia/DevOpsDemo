package userInterface;

/**
 * Created by Porwal Brother on 7/3/2017.
 */
import javafx.scene.control.TextField;

public class Screen extends TextField{
    public final String screenBackgroundColor = "honeydew";
    public final int screenBackgroundRadius = 5;

    public Screen(){
        setEditable(false);
        setStyle("-fx-background-color: "+screenBackgroundColor+";"
                + "-fx-background-radius: "+screenBackgroundRadius+";");
        setFocusTraversable(false);
    }
 public static void main(String args[]){
        Screen screen=new Screen();
 }

}