package snake_game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Apple {

    private int row;
    private int column;
    private char[][] board;

    public Apple(int r, int c, char[][] b) {
        row = r;
        column = c;
        board = b;
    }

    public void eaten() {
        
    }
    
}