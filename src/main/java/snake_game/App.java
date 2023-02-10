package snake_game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class App extends Application {

    GridPane gameBoard = new GridPane();

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        final int BOARD_SIZE = 15;

        Scene scene = new Scene(gameBoard, 690, 690);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

                Rectangle tile = new Rectangle(46, 46);
                if (i % 2 == j % 2) {
                    tile.setFill(Color.GREENYELLOW);
                } else {
                    tile.setFill(Color.GREEN);
                }
                
                gameBoard.getChildren().addAll(tile);

                GridPane.setRowIndex(tile, i);
                GridPane.setColumnIndex(tile, j);
            }
        }
        stage.setTitle("Snake Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void gameOver(Scene scene) {
        scene.setFill(Color.WHITE);
    }

    public void changeTile(char type, int row, int column) {
        Rectangle tile = new Rectangle(45, 45);
        tile.setStroke(Color.BLACK);
        switch (type) {
            case 's':
                tile.setFill(Color.BLUE);
                break;
            case 'a':
                tile.setFill(Color.RED);
                break;
            case 'e':
                tile.setFill(Color.GREENYELLOW);
                break;
        }
        
        gameBoard.getChildren().addAll(tile);

        GridPane.setRowIndex(tile, row);
        GridPane.setColumnIndex(tile, column);
    }

    public static void main(String[] args) {
        launch();
    }
}