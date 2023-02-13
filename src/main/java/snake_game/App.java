package snake_game;

import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class App extends Application {

    GridPane gameBoard = new GridPane();
    final int BOARD_SIZE = 15;
    public static char keyPress = 'E';
    Snake snake;
    Scene scene = new Scene(gameBoard, 675, 675);
    public static Stage gameStage;
    

    @Override
    public void start(Stage stage) {
        SystemInfo.javaVersion();
        SystemInfo.javafxVersion();

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

        gameStage = stage;
        gameStage.setTitle("Snake Game");
        gameStage.setScene(scene);
        gameStage.show();
        

        Rectangle snakeBuilder1 = new Rectangle(45, 45);
        snakeBuilder1.setFill(Color.BLUE);
        Rectangle snakeBuilder2 = new Rectangle(45, 45);
        snakeBuilder2.setFill(Color.BLUE);
        Rectangle snakeBuilder3 = new Rectangle(45, 45);
        snakeBuilder3.setFill(Color.BLUE);
        gameBoard.add(snakeBuilder1, 3, 7);
        gameBoard.add(snakeBuilder2, 2, 7);
        gameBoard.add(snakeBuilder3, 1, 7);

        snake = new Snake();
        snake.update();

        createApple();
        
        scene.setOnKeyPressed((KeyEvent e) -> {
            System.out.println("foundkeypress");    
            if (e.getCode().equals(KeyCode.UP)) {
                keyPress = 'N';
            }
            if (e.getCode().equals(KeyCode.DOWN)) {
                keyPress = 'S';
            }
            if (e.getCode().equals(KeyCode.LEFT)) {
                keyPress = 'E';
            }
            if (e.getCode().equals(KeyCode.RIGHT)) {
                keyPress = 'W';
            }
        });
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

    public void snakeMovement(int[][] indices, Rectangle[] segments) {

        gameBoard.getChildren().clear();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                for (int[] k : indices) {
                    Rectangle builder = new Rectangle(45, 45);
                    
                    // } else if () { //check whether an apple is in this space
                    //     builder.setFill(Color.RED);
                    //     gameBoard.add(builder, i, j);
                    
                    if (i % 2 == j % 2) {
                        builder.setFill(Color.GREENYELLOW);
                        gameBoard.add(builder, i, j);
                    } else {
                        builder.setFill(Color.GREEN);
                        gameBoard.add(builder, i, j);
                    }
                }
            }
        }
        for (int i = 0; i < indices.length; i++) {
            gameBoard.add(segments[i], indices[i][1], indices[i][0]);
        }
        gameStage.setScene(scene);
        gameStage.show();

        scene.setOnKeyPressed((KeyEvent e) -> {
            System.out.println("foundkeypress");    
            if (e.getCode().equals(KeyCode.UP)) {
                keyPress = 'N';
            }
            if (e.getCode().equals(KeyCode.DOWN)) {
                keyPress = 'S';
            }
            if (e.getCode().equals(KeyCode.RIGHT)) {
                keyPress = 'E';
            }
            if (e.getCode().equals(KeyCode.LEFT)) {
                keyPress = 'W';
            }
        });
    }

    public void createApple() {
        int rand1;
        int rand2;
        int checker = 0;

        rand1 = ThreadLocalRandom.current().nextInt(0, BOARD_SIZE + 1);
        rand2 = ThreadLocalRandom.current().nextInt(0, BOARD_SIZE + 1);

        while (checker < 2) {
            for (int[] i : snake.indices) {
                if (i.equals(new int[]{rand1, rand2})) {
                    rand1 = ThreadLocalRandom.current().nextInt(0, BOARD_SIZE + 1);
                    rand2 = ThreadLocalRandom.current().nextInt(0, BOARD_SIZE + 1);
                }
                checker++;
            }
        }
        
        Apple apple = new Apple(rand1, rand2);
    }

    public static void main(String[] args) {
        launch();
    }
}