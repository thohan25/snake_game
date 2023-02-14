package snake_game;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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

    public void gameOver(Scene scene) {
        System.out.println("over");
        scene.setFill(Color.WHITE);
        Text text = new Text();
        text.setText("GAME OVER.");
        text.setX(60); 
        text.setY(337);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 80)); 
        Group root = new Group(text);
        Scene newScene = new Scene(root, 675, 675);
        gameStage.setScene(newScene);
        gameStage.show();
        gameBoard.getChildren().clear();
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

        if (indices[0][0] < 0 || indices[0][0] >= BOARD_SIZE || indices[0][1] < 0 || indices[0][1] >= BOARD_SIZE) {
            timer.stop();
            gameOver(scene);
            return;
        }

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
                    return;
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

        rand1 = (int)(Math.random() * BOARD_SIZE);
        rand2 = (int)(Math.random() * BOARD_SIZE);

        for (int[] i : snake.indices) {
            if (i[0] == rand1 && i[1] == rand2) {
                rand1 = (int)(Math.random() * BOARD_SIZE);
                rand2 = (int)(Math.random() * BOARD_SIZE);
            }
        }
        for (int[] i : snake.indices) {
            if (i[0] == rand1 && i[1] == rand2) {
                rand1 = (int)(Math.random() * BOARD_SIZE);
                rand2 = (int)(Math.random() * BOARD_SIZE);
            }
        }
        
        apples.add(new int[]{rand1, rand2});

    }

    public static void main(String[] args) {
        launch();
    }
}