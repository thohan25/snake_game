package snake_game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class App extends Application {

    GridPane gameBoard = new GridPane();
    final int BOARD_SIZE = 15;
    char keyPress;

    @Override
    public void start(Stage stage) {
        SystemInfo.javaVersion();
        SystemInfo.javafxVersion();

        Scene scene = new Scene(gameBoard, 675, 675);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

                Rectangle tile = new Rectangle(45, 45);
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

        Rectangle snakeBuilder1 = new Rectangle(45, 45);
        snakeBuilder1.setFill(Color.BLUE);
        Rectangle snakeBuilder2 = new Rectangle(45, 45);
        snakeBuilder2.setFill(Color.BLUE);
        Rectangle snakeBuilder3 = new Rectangle(45, 45);
        snakeBuilder3.setFill(Color.BLUE);
        gameBoard.add(snakeBuilder1, 3, 7);
        gameBoard.add(snakeBuilder2, 2, 7);
        gameBoard.add(snakeBuilder3, 1, 7);
    }

    public static void gameOver(Scene scene) {
        scene.setFill(Color.WHITE);
    }

    public void changeTile(char type, int row, int column) { // types: a = apple, e = empty
        Rectangle tile = new Rectangle(45, 45);
        switch (type) {
            case 'a':
                tile.setFill(Color.RED);
                break;
            case 'e':
                if (row % 2 == column % 2) {
                    tile.setFill(Color.GREENYELLOW);
                } else {
                    tile.setFill(Color.GREEN);
                }
                break;
        }
        
        gameBoard.getChildren().addAll(tile);

        GridPane.setRowIndex(tile, row);
        GridPane.setColumnIndex(tile, column);
    }

    public Character checkFacing() {
        gameBoard.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.UP) {
                keyPress = 'N';
            }
            if (e.getCode() == KeyCode.DOWN) {
                keyPress = 'S';
            }
            if (e.getCode() == KeyCode.LEFT) {
                keyPress = 'E';
            }
            if (e.getCode() == KeyCode.RIGHT) {
                keyPress = 'W';
            }
        });
        return keyPress;
    }

    public void snakeMovement(int[][] indices, Rectangle[] segments) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {

                Rectangle tile = new Rectangle(45, 45);
                if (false) { //check whether an apple is in this space
                    tile.setFill(Color.RED);
                } else if (i % 2 == j % 2) {
                    tile.setFill(Color.GREENYELLOW);
                } else {
                    tile.setFill(Color.GREEN);
                }
                
                gameBoard.getChildren().addAll(tile);

                GridPane.setRowIndex(tile, i);
                GridPane.setColumnIndex(tile, j);
            }
        }
        for (int i = 0; i < indices.length; i++) {
            gameBoard.add(segments[i], indices[i][0], indices[i][1]);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}