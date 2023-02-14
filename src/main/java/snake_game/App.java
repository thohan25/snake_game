package snake_game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import javafx.animation.AnimationTimer;
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
    public static Snake snake = new Snake();
    Scene scene = new Scene(gameBoard, 675, 675);
    public static Stage gameStage;
    public static ArrayList<int[]> apples = new ArrayList<>();

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
        System.out.println("over");
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

    public void snakeMovement(int[][] indices, Rectangle[] segments, int[] tail, AnimationTimer timer) {

        gameBoard.getChildren().clear();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Rectangle builder = new Rectangle(45, 45);
                Boolean apple = false;
                
                for (int[] l : apples) {
                    if (l[0] == i && l[1] == j) { 
                        builder.setFill(Color.RED);
                        gameBoard.add(builder, i, j);
                        apple = true;
                        if (l[1] == indices[0][0] && l[0] == indices[0][1]) {
                            snake.eatingApple(tail);
                        }
                    }
                }

                if (!apple && i % 2 == j % 2) {
                    builder.setFill(Color.GREENYELLOW);
                    gameBoard.add(builder, i, j);
                } else if (!apple) {
                    builder.setFill(Color.GREEN);
                    gameBoard.add(builder, i, j);
                }
            }
        }

        for (int i = 0; i < indices.length; i++) {
            gameBoard.add(segments[i], indices[i][1], indices[i][0]);
        }

        gameStage.setScene(scene);
        gameStage.show();

        for (int i = 0; i < indices.length; i++) {
            for (int j = i+1; j < indices.length; j++) {
                if (indices[i][0] == indices[j][0] && indices[i][1] == indices[j][1]) {
                    timer.stop();
                    gameOver(scene);
                }
            }
        }

        scene.setOnKeyPressed((KeyEvent e) -> {
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
        apples.clear();
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
        
        apples.add(new int[]{rand1, rand2});

    }

    public static void main(String[] args) {
        launch();
    }
}